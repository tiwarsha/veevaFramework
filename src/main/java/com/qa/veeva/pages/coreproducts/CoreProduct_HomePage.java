package com.qa.veeva.pages.coreproducts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qa.veeva.constants.AppConstants;
import com.qa.veeva.utils.CustomRuntimeException;
import com.qa.veeva.utils.ElementUtil;
import com.qa.veeva.utils.JavaScriptUtil;

public class CoreProduct_HomePage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsutil;

	public CoreProduct_HomePage(WebDriver driver) {

		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		jsutil = new JavaScriptUtil(driver);
	}

	public void openHomeCoreProductLandingPage() {

		driver.get(AppConstants.CORE_PRODUCT_URL);
		try {
			jsutil.refreshBrowserByJS();
		}

		catch (Throwable e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			jsutil.refreshBrowserByJS();
		}

		catch (Throwable e) {
			e.printStackTrace();
		}

	}

	public VideosFeedPage gotoVideosFeedPage(String menu, String subMenu) {
		hoverAndClickSubMenu(menu, subMenu);
		String pageTitle = eleUtil.switchWindow();

		System.out.println(pageTitle);
		return new VideosFeedPage(driver);

	}

	public Shop_Men_LandingPage gotoShop_Men_LandingPage(String menu, String subMenu) {
		hoverAndClickSubMenu(menu, subMenu);
		String pageTitle = eleUtil.switchWindow();
		if (pageTitle.equalsIgnoreCase(AppConstants.SHOP_MEN_LANDINGPAGE)) {
			System.out.println("Swithched to men landing page");
		}

		else {

			throw new CustomRuntimeException("Unable to navigate to shop men landing page");
		}
		return new Shop_Men_LandingPage(driver);
	}

	public void hoverAndClickSubMenu(String mainMenutext, String submenutext) {
		// Find the main menu element
		// WebElement mainMenu = driver.findElement(By.xpath("//li/a/span[(text()='" +
		// mainMenutext + "')]"));

		WebElement mainMenu = eleUtil.waitForElementPresence(By.xpath("//li/a/span[(text()='" + mainMenutext + "')]"),
				AppConstants.DEFAULT_LONG_TIMEOUT);

		// Initialize Actions class

		Actions actions = new Actions(driver);
////li/a/span[(text()='Shop')]/parent::*/following-sibling::nav/ul/li/a[contains(@title,'Men')]
		// Hover over the main menu

		actions.moveToElement(mainMenu).perform();

		// Wait for the submenu to be visible and clickable
		// WebElement subMenu = driver.findElement(By.xpath("//li/a/span[(text()='" +
		// mainMenutext
		// + "')]/parent::*/following-sibling::nav/ul/li/a[contains(@title,'" +
		// submenutext + "')]"));
		WebElement subMenu = eleUtil.waitForElementPresence(By.xpath(
				"//li/a/span[text()='" + mainMenutext + "']/following::nav//a[contains(@title,'" + submenutext + "')]"),
				AppConstants.DEFAULT_LONG_TIMEOUT);

		actions.moveToElement(subMenu).click().perform();
	}
}
