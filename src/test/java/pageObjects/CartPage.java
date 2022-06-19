package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	public WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	By productPrice = By.cssSelector("td.product-price ");
	
	By quantity = By.xpath("//input[@type='number']");
	
	By removeProduct = By.cssSelector("td.product-remove a");
	
	public List<WebElement> checkProductPrice() {
		return driver.findElements(productPrice);
	}
	
	public List<WebElement> cartproduct(WebDriverWait wait) {
		
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(productPrice)));
		System.out.println("***************************"+driver.findElements(productPrice).size());
		return driver.findElements(productPrice);
	}
	
	public List<WebElement> checkProducts() {
		return driver.findElements(quantity);
	}
	
	public List<WebElement> removeProducts() {
		return driver.findElements(removeProduct);
	}

}
