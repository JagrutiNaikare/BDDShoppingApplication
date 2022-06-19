package utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CheckoutPage;

public class GenericUtils {
	public WebDriver driver;
	List<WebElement> viewCartTextList = new ArrayList<WebElement>();
	CheckoutPage checkoutPage;

	public GenericUtils(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriverWait getExplicitWait(long timeinSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeinSeconds));
		return wait;
	}
	

}
