package utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import pageObjects.PageObjectManager;

public class TestCaseUtils {
	public WebDriver driver;
	public PageObjectManager pageObjectManager;
	public TestBase testBase;
	public GenericUtils genericUtils;
	int productItemCount;

	public int getProductItemCount() {
		return productItemCount;
	}

	public void setProductItemCount(int productItemCount) {
		this.productItemCount = productItemCount;
	}

	public TestCaseUtils() throws IOException {
		testBase = new TestBase();
		pageObjectManager = new PageObjectManager(testBase.getWebDriver());
		genericUtils = new GenericUtils(testBase.getWebDriver());

	}
}
