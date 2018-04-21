import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.opencv.core.Mat;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class StepDefinition {

	WebDriver driver;
	FileManager filemanager;
	BufferedImage image;

	@Given("^I go to \"([^\"]*)\"$")
	public void i_go_to(String arg1) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.navigate().to("https://i.redd.it/0qow2dksb34z.jpg");
	}

	@Then("^I should be on \"([^\"]*)\"$")
	public void i_should_be_on(String arg1) throws Throwable {
		Assert.assertTrue(this.driver.getTitle().contains(arg1));
		this.driver.close();
	}

	@Then("^File extension is \"([^\"]*)\"$")
	public void file_extension_is(String arg1) throws Throwable {
		Assert.assertTrue(this.driver.getTitle().contains(arg1));
		this.driver.close();
	}

	@Given("^I create output file \"([^\"]*)\"$")
	public void i_create_output_file(String arg1) throws Throwable {
		this.filemanager = new FileManager(arg1);

	}

	@Then("^\"([^\"]*)\" file exists$")
	public void file_exists(String arg1) throws Throwable {
		Assert.assertTrue(FileManager.files.exists());
	}

	@Given("^I have a valid \"([^\"]*)\" that can be streamed$")
	public void i_have_a_valid_that_can_be_streamed(String arg1) throws Throwable {
		URL url = new URL(arg1);
		this.image = ImageIO.read(url.openStream());
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", byteArrayOutputStream);
		byteArrayOutputStream.flush();
	}

	@Then("^\"([^\"]*)\" bufferedimage exists$")
	public void bufferedimage_exists(String arg1) throws Throwable {
		Assert.assertNotNull(this.image);
	}

}
