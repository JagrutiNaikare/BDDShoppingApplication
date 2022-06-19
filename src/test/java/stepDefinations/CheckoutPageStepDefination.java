package stepDefinations;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.CheckoutPage;
import utils.GenericUtils;
import utils.TestCaseUtils;

public class CheckoutPageStepDefination {
	TestCaseUtils testCaseUtils;
	CheckoutPage checkoutPage;
	GenericUtils genericUtils;
	public Logger logger = Logger.getLogger(CheckoutPageStepDefination.class.getName());

	public CheckoutPageStepDefination(TestCaseUtils testCaseUtils) {
		this.testCaseUtils = testCaseUtils;
		checkoutPage = testCaseUtils.pageObjectManager.getCheckoutPage();
		genericUtils = testCaseUtils.pageObjectManager.getGenericUtils();
	}

	
	@Given("^I add \"([^\"]*)\" random items to my cart$")
	public void i_add_something_random_items_to_my_cart(String addItemCount) throws Throwable {
		try {
			logger.info("Execution Started : I add \"4\" random items to my cart");
			List<WebElement> addtoCartList = checkoutPage.addToCartProductList();
			int addProductsCount = Integer.parseInt(addItemCount);
			testCaseUtils.setProductItemCount(addProductsCount);
			for (int i = 0; i < addProductsCount; i++) {
				addtoCartList.get(i).click();
				WebDriverWait wait = genericUtils.getExplicitWait(5);
				wait.until(ExpectedConditions.attributeContains(addtoCartList.get(i), "class",
						"button product_type_simple add_to_cart_button ajax_add_to_cart added"));
			}
			logger.info("Execution Finished : I add \"4\" random items to my cart");
		} catch (Exception e) {
			logger.error("Error " + e.getMessage());
		}
	}

	@When("I view my cart")
	public void i_view_my_cart() throws InterruptedException {
		try {
			logger.info("Execution Started : I view my cart");
			checkoutPage.clickOnCart();
			logger.info("Execution Finished : I view my cart");
		} catch (Exception e) {
			logger.error("Error " + e.getMessage());
		}
	}

}
