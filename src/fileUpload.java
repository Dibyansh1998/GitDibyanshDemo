import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class fileUpload {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException, IOException {

// TODO Auto-generated method stub

		String downloadPath = System.getProperty("user.dir");

		WebDriverManager.chromedriver().setup();

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

		chromePrefs.put("profile.default_content_settings.popups", 0);

		chromePrefs.put("download.default_directory", downloadPath);

		ChromeOptions options = new ChromeOptions();

		options.setExperimentalOption("prefs", chromePrefs);

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		driver.get("https://www.ilovepdf.com/pdf_to_jpg");

		driver.findElement(By.cssSelector("a[id='pickfiles'] span")).click();

		Thread.sleep(3000);

		Runtime.getRuntime().exec("C:\\Users\\52304535\\OneDrive - Conduent\\Documents\\fileupload.exe");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#processTaskTextBtn")));

		driver.findElement(By.cssSelector("#processTaskTextBtn")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='pickfiles']")));

		driver.findElement(By.xpath("//a[@id='pickfiles']")).click();

		Thread.sleep(5000);

		File f = new File(downloadPath + "/converted.zip");

		if (f.exists())
			System.out.println("file Upload");

		{

			Assert.assertTrue(f.exists());

			if (f.delete())

				System.out.println("file deleted");

		}

	}

}