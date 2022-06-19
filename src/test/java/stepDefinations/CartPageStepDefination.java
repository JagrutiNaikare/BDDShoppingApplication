package stepDefinations;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import utils.GenericUtils;
import utils.TestCaseUtils;

public class CartPageStepDefination {
	TestCaseUtils testCaseUtils;
	CartPage cartPage;
	GenericUtils genericUtils;
	int lowestPriceIndex;
	int totalProducts;

	public Logger logger = Logger.getLogger(CartPageStepDefination.class.getName());

	public CartPageStepDefination(TestCaseUtils testCaseUtils) {
		this.testCaseUtils = testCaseUtils;
		cartPage = testCaseUtils.pageObjectManager.getCartPage();
		genericUtils = testCaseUtils.pageObjectManager.getGenericUtils();
	}

	@Then("^I found total \"([^\"]*)\" items listed in my cart$")
	public void i_found_total_something_items_listed_in_my_cart(String totalItems) throws IOException {
		try {
			logger.info("Execution Started : I found total \"3\" items listed in my cart");
			int itemSize = Integer.parseInt(totalItems);
			int product_Quantity = checkProductCountInCart();
			assertEquals("TC Failed- Added cart items and total found items shoud be same ",
					testCaseUtils.getProductItemCount(), itemSize);
			assertEquals("TC Failed- cart items count is different from added items", itemSize, product_Quantity);
			logger.info("Execution Finished: I found total \"3\" items listed in my cart ");
		} catch (Exception e) {
			logger.error("Error " + e.getMessage());
		}
	}

	public int checkProductCountInCart() {
		List<WebElement> productSizeinCart = cartPage.checkProducts();
		int product_Quantity = 0;
		for (WebElement element : productSizeinCart) {
			product_Quantity += Integer.parseInt(element.getAttribute("value"));
		}
		return product_Quantity;
	}

	@Then("^I am able to verify \"([^\"]*)\" items in my cart$")
	public void i_am_able_to_verify_something_items_in_my_cart(String productCount) {
		try {
			logger.info("Execution Started : I am able to verify \"2\" items in my cart");
			assertEquals("TC Failed- For verification items count should less than one from total items",
					testCaseUtils.getProductItemCount() - 1, Integer.parseInt(productCount));
			int product_Quantity = checkProductCountInCart();
			assertEquals(
					"TC Failed- Cart items are" + product_Quantity
							+ ",but after removing lowest price item remaining items should be" + productCount,
					+Integer.parseInt(productCount), product_Quantity);
		} catch (Exception e) {
			logger.error("Error " + e.getMessage());

		}

	}

	@And("^I am able to remove lowest price item from my cart$")
	public void i_am_able_to_remove_lowest_price_item_from_my_cart() {
		try {
			logger.info("Execution Started : I am able to remove lowest price item from my cart");
			List<WebElement> removeProductList = cartPage.removeProducts();
			removeProductList.get(lowestPriceIndex).click();

			WebDriverWait wait = genericUtils.getExplicitWait(5);
			wait.until(ExpectedConditions.invisibilityOf(removeProductList.get(lowestPriceIndex)));
		} catch (Exception e) {
			logger.error("Error " + e.getMessage());
		}
	}

	@When("^I search for lowest price item$")
	public void i_search_for_lowest_price_item() {
		try {
			logger.info("Execution Started :I search for lowest price item");
			List<WebElement> productSizeinCart = cartPage.checkProductPrice();
			lowestPriceIndex = 0;
			int minimumPrice = getPrice(productSizeinCart, 0);
			int price = 0;
			for (int i = 1; i < productSizeinCart.size(); i++) {
				price = getPrice(productSizeinCart, i);
				if (minimumPrice > price) {
					minimumPrice = price;
					lowestPriceIndex = i;
				}
			}
			logger.info("Execution Finished :I search for lowest price item");
		} catch (Exception e) {
			logger.error("Error " + e.getMessage());
		}

	}

	public int getPrice(List<WebElement> productSizeinCart, int minIndex) {
		String priceString = productSizeinCart.get(minIndex).getText().replaceAll("[^0-9]", "");
		int minimumPrice = Integer.parseInt((priceString.substring(0, priceString.length() - 2)).trim());
		return minimumPrice;
	}

}
