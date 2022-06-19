package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = "stepDefinations", monochrome = true, plugin = {
		"html:target/reports/report.html", "json:target/reports/report.json",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })
public class TestRunner {

}
