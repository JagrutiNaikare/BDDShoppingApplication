package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;

public class TestBase {

	public WebDriver driver;
	public ExtentReports test;
	public Logger logger = Logger.getLogger(TestBase.class.getName());
	public WebDriver getWebDriver() throws IOException {
		try {
		if (driver == null) {
			logger.info("Browser Initilizing!!");
			System.out.println("inside driver launch");
			FileInputStream fileInputStream = new FileInputStream(
					new File(System.getProperty("user.dir") + "\\src\\test\\resources\\globalProperties.properties"));
			Properties properties = new Properties();
			properties.load(fileInputStream);
			String url = properties.getProperty("url");
			String browser = (null == System.getProperty("browser")) ? properties.getProperty("browser")
					: System.getProperty("browser");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
			if ("chrome".equalsIgnoreCase(browser)) {
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.get(url);
			}
		}
		} catch (Exception e) {
			logger.error("Error occured while init browser"+e.getMessage());
		}
		return driver;
	}
	

	public String getScreenshots(WebDriver driver) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		String destFile = System.getProperty("user.dir") + "\\reports\\" + new Date() + ".jpeg";
		FileUtils.copyFile(source, new File(destFile));
		return destFile;
	}

}
