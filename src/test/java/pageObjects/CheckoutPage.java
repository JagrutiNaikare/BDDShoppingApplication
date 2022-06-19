package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
	public WebDriver driver;
	public static List<WebElement> list = new ArrayList<WebElement>();

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	By sortByDropDown = By.cssSelector("span[id='select2-orderby-ny-container']");

	By addToCart = By.xpath("//div[@class='ellie-thumb-wrapper']/a[text()='Add to cart']");
	By cart = By.cssSelector("a[href='https://cms.demo.katalon.com/cart/']");
	
	By viewCart = By.cssSelector("a[title='View cart']");

	public void clickOnCart() {
		driver.findElement(cart).click();
	}

	public void sortBy() {
		driver.findElement(sortByDropDown).click();
	}

	public List<WebElement> addToCartProductList() {
		return driver.findElements(addToCart);
	}
	
	public WebElement viewCart() {
		return driver.findElement(viewCart);
	}
	
}
