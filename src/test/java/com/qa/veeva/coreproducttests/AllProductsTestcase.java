package com.qa.veeva.coreproducttests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import com.aventstack.extentreports.Status;
import com.qa.veeva.base.BaseTest;
import com.qa.veeva.extent.ExtentReportListener;
import com.qa.veeva.pages.coreproducts.Shop_Men_LandingPage;

public class AllProductsTestcase extends BaseTest {

	@Test(dataProvider = "shopDataProvider")
	public void testProductDetails(String section, String category, String productCategory) {
		// Open core product landing page
		coreProduct_HomePage.openHomeCoreProductLandingPage();
		ExtentReportListener.addLog(Status.INFO, "Opened Home Core Product Landing Page.");

		// Navigate to Men's Shop Landing Page and retrieve product details
		Shop_Men_LandingPage shop_Men_LandingPage = coreProduct_HomePage.gotoShop_Men_LandingPage(section, category);
		ExtentReportListener.addLog(Status.INFO, "Navigated to Men's Shop Landing Page.");

		HashMap<String, String> productDetails = shop_Men_LandingPage.getProductDetails(productCategory);

		// Assert that productDetails is not empty
		Assert.assertFalse(productDetails.isEmpty(), "Product details for " + productCategory + " should not be empty");
		ExtentReportListener.addLog(Status.INFO,
				"Assertion: Product details for " + productCategory + " is not empty.");

		// Write product details to a file and generate file path
		String path = customFileWriter.fileWriter(productDetails);

		Path filePath = Paths.get(path);
		String absolutePath = filePath.toAbsolutePath().toString();

		// Log the file path as a hyperlink
		ExtentReportListener.addLog(Status.INFO,
				"File containing product details: <a href=\"file:///" + absolutePath + "\">View File Content</a>");

		// Optionally, read and log the file content if needed
		try {
			String fileContent = new String(Files.readAllBytes(filePath));
			// ExtentReportListener.addLog(Status.INFO, "Read file content: \n" +
			// fileContent);

			// Assert that the file content is not empty
			Assert.assertFalse(fileContent.isEmpty(), "File content should not be empty");
			ExtentReportListener.addLog(Status.INFO, "Assertion: File content is not empty.");
		} catch (IOException e) {
			e.printStackTrace();
			ExtentReportListener.addLog(Status.FAIL, "Error reading file content: " + e.getMessage());
		}
	}

	/*
	 * @DataProvider(name = "productDataProvider") public Object[][]
	 * productDataProvider() { return new Object[][] { { "Jackets" }, { "Shirts" },
	 * { "Trousers" } }; }
	 */

	/*
	 * @DataProvider(name = "productDataProvider") public Object[][]
	 * productDataProvider() { return new Object[][] { { "Jackets" } }; }
	 */

	/*
	 * @DataProvider(name = "shopDataProvider") public Object[][] shopDataProvider()
	 * { return new Object[][] { { "Shop", "Men", "Jackets" }, { "Shop", "Men",
	 * "Shirts" }, { "Shop", "Men", "Trousers" } }; }
	 */

	@DataProvider(name = "shopDataProvider")
	public Object[][] shopDataProvider() {
		return new Object[][] { { "Shop", "Men", "Jackets" } };
	}

}
