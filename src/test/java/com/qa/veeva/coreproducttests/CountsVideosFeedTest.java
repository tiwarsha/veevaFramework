package com.qa.veeva.coreproducttests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.qa.veeva.base.BaseTest;
import com.qa.veeva.extent.ExtentReportListener;
import com.qa.veeva.pages.coreproducts.VideosFeedPage;

public class CountsVideosFeedTest extends BaseTest {

	@Test(dataProvider = "videoFeedDataProvider")
	public void testVideoFeedPage(String placeholder, String category, int expectedVideoCount) {
		// Open core product landing page
		coreProduct_HomePage.openHomeCoreProductLandingPage();
		ExtentReportListener.addLog(Status.INFO, "Opened Home Core Product Landing Page.");

		// Navigating to Videos Feed Page
		VideosFeedPage videosFeedPage = coreProduct_HomePage.gotoVideosFeedPage(placeholder, category);
		ExtentReportListener.addLog(Status.INFO, "Navigated to Videos Feed Page.");

		// Counting total videos on the page
		int totalVideos = videosFeedPage.countTotalVideos();
		ExtentReportListener.addLog(Status.INFO, "Counted total videos: " + totalVideos);

		// Asserting total video count
		Assert.assertEquals(totalVideos, expectedVideoCount, "Element count doesn't match");
		ExtentReportListener.addLog(Status.INFO, "Assertion: Total videos count matches expected value.");
	}

	@DataProvider(name = "videoFeedDataProvider")
	public Object[][] videoFeedDataProvider() {
		return new Object[][] { { "...", "News & Features", 22 },
				// Add more test cases as needed
		};
	}
}
