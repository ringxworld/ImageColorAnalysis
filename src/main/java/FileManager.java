import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * 
 * @author Shawn
 *
 */
public class FileManager {

	public static File file;
	public static File files;
	private String filename = "";

	FileManager(String filename) {
		this.filename = filename;
		file = new File(System.getProperty("user.home") + File.separatorChar + "My Documents" + File.separatorChar + "Temp");
		files = new File(file, this.filename);
		try {
			clear();
			create();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean create() throws IOException {

		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}

		if (!files.exists()) {
			if (files.createNewFile()) {
				System.out.println("Multiple directories are created!");
			} else {
				System.out.println("Failed to create multiple directories!");
			}
		}
		return true;
	}

	public void clear() throws IOException {
		Path csvFile = files.toPath();
		Files.deleteIfExists(csvFile);
	}

	public void addEntry(String s) throws IOException {
		Files.write(FileManager.files.toPath(), s.getBytes(), StandardOpenOption.APPEND);
	}

}
