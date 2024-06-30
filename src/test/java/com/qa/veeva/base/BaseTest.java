package com.qa.veeva.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qa.veeva.extent.ExtentReportListener;
import com.qa.veeva.factory.DriverFactory;
import com.qa.veeva.pages.coreproducts.CoreProduct_HomePage;
import com.qa.veeva.pages.coreproducts.VideosFeedPage;
import com.qa.veeva.utils.CustomFileWriter;

public class BaseTest {

	DriverFactory df;
	WebDriver driver;

	protected Properties prop;
	protected SoftAssert softAssert;
	protected CoreProduct_HomePage coreProduct_HomePage;
	protected VideosFeedPage videosFeedPage;
	protected CustomFileWriter customFileWriter;

	@Parameters({ "browser" })
	@BeforeTest
	public void setup(String browser) {
		df = new DriverFactory();
		prop = df.initProp();

		if (browser != null) {
			prop.setProperty("browser", browser);

		}

		driver = df.initDriver(prop);
		softAssert = new SoftAssert();
		customFileWriter = new CustomFileWriter();
		coreProduct_HomePage = new CoreProduct_HomePage(driver);

	}

	@AfterTest

	public void tearDown() {
		driver.quit();
	}
}
