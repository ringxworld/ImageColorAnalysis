import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import com.wijkuy.imagecoloranalysis.ImageColorAnalysisApplication;
import com.wijkuy.imagecoloranalysis.ImageColorAnalysisApplicationBuilder;
import com.wijkuy.imagecoloranalysis.images.images.images.Images;
import com.wijkuy.imagecoloranalysis.images.images.images.ImagesImpl;
import com.wijkuy.imagecoloranalysis.images.images.images.ImagesManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.TermCriteria;
import org.opencv.imgcodecs.Imgcodecs;

@Slf4j

/**
 * Takes a text file of image links and finds the three most common colors in the images
 * Will store the url and the three primary colors in a sql database handled by speedment
 * This project will run in parallel and use all the system resources available
 *
 * This project outputs a CSV file with the unique links and their primary colors
 * there is a filemanager to handle that
 *
 * @author Shawn Anderson
 * @version 1.0
 * @since 2018-04-01
 */
public class Cluster {

    //The amount of available threads on the system
    private static final int MAX_THREADS = Runtime.getRuntime().availableProcessors();

    //FileManager handles the output file and given the scale of the project the filename is hardcoded
    private final FileManager output = new FileManager("ImageColors.csv");

    //Handles task scheduling for threads asynchronously
    private final ExecutorService service = Executors.newFixedThreadPool(MAX_THREADS);
    ImagesManager images;


    /**
     * @Data anotation refers to lombok data class. This is used to allow parallelstreams to run on each line
     *
     * This class will create a OpenCV mat foreach link and cluster the 3 most common colors and save to db
     * Since it implements runnable it will do this through the run method
     */
    @Data
    private class Output implements Runnable {
        private final String url;

        @Override
        public void run() {
            String line = url + ",";
            try {
                Mat img = BufferedImage2Mat(url);

                //Speedment API usage built around java stream api
                //https://github.com/speedment
                if(images.stream().filter(Images.URL.equal(url)).count()<1) {
                    //line += cluster(img, 3) + "\n";
                    String[] temp = cluster(img,3);
                    System.out.println(url+" "+ Arrays.toString(temp));
                    //output.addEntry(line);
                    //equivelent sql statement
                    //INSERT INTO Images (url,color1,color2,color3) VALUES (url,temp[0],temp[1],temp[2]);
                    images.persist(new ImagesImpl()
                            .setUrl(url)
                            .setColor1(temp[0])
                            .setColor2(temp[1])
                            .setColor3(temp[2]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //More speedment setup
    public Cluster() {
        ImageColorAnalysisApplication app = new ImageColorAnalysisApplicationBuilder()
                .withPassword("password")
                .build();
       images = app.getOrThrow(ImagesManager.class);
    }

    /**
     * Implemented by the Runnable Interface
     * This uses parallel streams to parallelize the job
     * I was able to get away with this so I didn't need to have to use synchronized keywords
     * or handle a lot of the threading problems myself
     *
     * I did use lombok to handle some issues though where it wasn't actually utilizing every core
     */
    public void run() {
        nu.pattern.OpenCV.loadShared();
        FileManager outputFile = new FileManager("ImageColors.csv");

        //urls.txt has a store of the urls to check
        try (InputStream resource = getClass().getResourceAsStream("/urls.txt")) {
            new BufferedReader(
                    new InputStreamReader(resource, StandardCharsets.UTF_8)
            ).lines().forEach(line -> service.submit(new Output(line)));


        } catch (IOException e) {
            e.printStackTrace();
        }

        //This does not work as expected without lombok
        //TODO: Are race conditions possible?
        images.stream().parallel().forEach(e -> {
            try {
                outputFile.addEntry(e.getUrl() +","+e.getColor1() +","+e.getColor2() +","+e.getColor3()+"\n");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    /**
     * Converts a java BufferedImage obj to a OpenCV mat
     *
     * The middleman bufferedimage just made it so much easier to do a standard java ImageIO call
     * And just convert that to a OpenCV Mat instead of trying to do so directly
     *
     * TODO: Is this the best approach? Can I remove the middleman?
     *
     * @param imageUrl - Assume you parse a URL from a text file. ImageIO should check if its a valid param
     * @return - OpenCV Mat which is the OpenCV equivelent of a Java BufferedImage
     * @throws IOException
     */
    public static Mat BufferedImage2Mat(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        BufferedImage image = ImageIO.read(url.openStream());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
    }

    /*
     * https://github.com/badlogic/opencv-fun/blob/master/src/pool/tests/Cluster
     * .java
     */
    public static String[] cluster(Mat image, int k) {
        Mat samples = image.reshape(1, image.cols() * image.rows());
        Mat samples32f = new Mat();
        samples.convertTo(samples32f, CvType.CV_32F, 1.0 / 255.0);

        Mat labels = new Mat();
        TermCriteria criteria = new TermCriteria(TermCriteria.COUNT, 100, 1);
        Mat centers = new Mat();
        Core.kmeans(samples32f, k, labels, criteria, 1, Core.KMEANS_PP_CENTERS, centers);
        return showClusters(image, labels, centers,k);
    }

    /**
     * Apply kmeans result to the image and redraw it with reduced colorspace
     * Then iterate over the image and return the most common colors
     * TODO: Perhaps theres a better heiuristic that isn't n^2?
     * @param image - the image created from the URL
     * @param labels - the labels recieved from kmeans
     * @param centers - of the clusters recieved from kmeans
     * @param k - the amount of groups to make
     * @return - the 3 most common RGB colors from image as a string
     */
    private static String[] showClusters(Mat image, Mat labels, Mat centers,int k) {
        centers.convertTo(centers, CvType.CV_8UC1, 255.0);
        centers.reshape(3);

        List<Mat> clusters = new ArrayList<Mat>();

        for (int i = 0; i < centers.rows(); i++) {
            clusters.add(Mat.zeros(image.size(), image.type()));
        }

        Map<Integer, String> counts = new HashMap<>();
        for (int i = 0; i < centers.rows(); i++)
            counts.put(i, "");

        int rows = 0;
        for (int y = 0; y < image.rows(); y++) {
            for (int x = 0; x < image.cols(); x++) {
                int label = (int) labels.get(rows, 0)[0];

                //Assume RGB
                int r = (int) centers.get(label, 2)[0];
                int g = (int) centers.get(label, 1)[0];
                int b = (int) centers.get(label, 0)[0];
                counts.put(label, Integer.toHexString((r << 16) | (g << 8) | b));
                rows++;
            }
        }
        List<String> output = counts.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        return output.toArray(new String[output.size()]);
    }

    /**
     * The driver start Cluster
     * @param args - system args unused
     */
    public static void main(String[] args) {
        Cluster cluster = new Cluster();
        cluster.run();
    }

}
