package com.qa.veeva.pages.coreproducts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.veeva.constants.AppConstants;
import com.qa.veeva.utils.ElementUtil;
import com.qa.veeva.utils.JavaScriptUtil;

public class Shop_Men_LandingPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsutil;

	private By productsearchbox_landingPage = By.xpath("//*[@id='typeahead-input-desktop']");
	private By searchbutton = By.xpath("//button[@aria-label = 'Search Product']");

	private By pagination_link = By.xpath("(//*[@aria-label='next page'])[position() = 1]");
	private By priceProduct = By.xpath(
			"//*[@class='product-card row']//div[@class='price-card']/parent::*/parent::*//div[@class='product-card-title']//preceding-sibling::div/div[@class='price-card']//span[@class='lowest']//span[@class='sr-only']\r\n"
					+ "");

	private By titleProduct = By.xpath(
			"//*[@class='product-card row']//div[@class='price-card']/parent::*/parent::*//div[@class='product-card-title']/a");

	public Shop_Men_LandingPage(WebDriver driver) {

		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		jsutil = new JavaScriptUtil(driver);
	}

	public HashMap<String, String> getProductDetails(String productName) {
		eleUtil.waitForElementPresence(productsearchbox_landingPage, AppConstants.DEFAULT_MEDIUM_TIMEOUT);

		eleUtil.doSendKeys(productsearchbox_landingPage, productName);
		eleUtil.doClick(searchbutton);

		String state = eleUtil.getElementAttribute(pagination_link, "aria-disabled");

		List<List<String>> productpricedetails = new ArrayList<List<String>>();
		List<List<String>> producttitledetails = new ArrayList<List<String>>();

		while (state.equals("false")) {

			eleUtil.waitForElementPresence(pagination_link, AppConstants.DEFAULT_MEDIUM_TIMEOUT);

			eleUtil.doClick(pagination_link);
			state = eleUtil.getElementAttribute(pagination_link, "aria-disabled");

			List<String> pricedetails = eleUtil.getElementsTextList(priceProduct);
			List<String> productitle = eleUtil.getElementsTextList(titleProduct);
			productpricedetails.add(pricedetails);
			producttitledetails.add(productitle);

			System.out.println(state);

		}

		System.out.println(productpricedetails.size());
		System.out.println(producttitledetails.size());

		HashMap<String, String> hs = new HashMap<String, String>();

		for (int i = 0; i < productpricedetails.size(); i++) {

			for (int j = 0; j < productpricedetails.get(i).size(); j++) {
				hs.put(producttitledetails.get(i).get(j), productpricedetails.get(i).get(j));
			}
		}
	return hs;
	}

}
