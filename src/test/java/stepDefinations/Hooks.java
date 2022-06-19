package stepDefinations;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.plugin.event.PickleStepTestStep;
import utils.TestCaseUtils;

public class Hooks {

	TestCaseUtils testCaseUtils;
	ExtentTest extentTest = null;
	PickleStepTestStep currentStep;

	public Hooks(TestCaseUtils testCaseUtils) {
		this.testCaseUtils = testCaseUtils;
	}

	@Given("^Launch browser$")
	public void launch_browser() throws IOException {
		assertEquals("Katalon Shop – Katalon Ecommerce", testCaseUtils.testBase.getWebDriver().getTitle());
		PropertyConfigurator.configure("log4j.properties");
	}

	@After
	public void AfterScenario() throws IOException {
		testCaseUtils.testBase.getWebDriver().quit();
	}

	@AfterStep
	public void addScreenshot(Scenario scenario) throws IOException {
		WebDriver driver = testCaseUtils.testBase.getWebDriver();
		if (scenario.isFailed()) {
			File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			byte[] data = FileUtils.readFileToByteArray(sourceFile);
			scenario.attach(data, "image/jpeg", "image");
		}
	}
}
