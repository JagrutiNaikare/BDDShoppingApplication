package pageObjects;

import org.openqa.selenium.WebDriver;

import utils.GenericUtils;

public class PageObjectManager {
	CheckoutPage checkoutPage;
	CartPage cartPage;
	GenericUtils genericUtils;
	public WebDriver driver;

	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public CheckoutPage getCheckoutPage() {

		checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}

	public CartPage getCartPage() {
		cartPage = new CartPage(driver);
		return cartPage;
	}

	public GenericUtils getGenericUtils() {
		genericUtils = new GenericUtils(driver);
		return genericUtils;
	}

}
