package com.qa.veeva.pages.coreproducts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.veeva.constants.AppConstants;
import com.qa.veeva.utils.ElementUtil;
import com.qa.veeva.utils.JavaScriptUtil;

public class VideosFeedPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsutil;
	private By links = By.xpath("//a[contains(@href, 'videos') and contains(@class, 'TileArticle_tileLink')]");

	public VideosFeedPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		jsutil = new JavaScriptUtil(driver);
	}

	public int countTotalVideos() {
		int totalcount = eleUtil.waitForElementsPresence(links, AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println(totalcount);
		return totalcount;

	}

}
