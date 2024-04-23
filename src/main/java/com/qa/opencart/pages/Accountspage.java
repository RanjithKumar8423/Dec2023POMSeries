package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppsConstants;
import com.qa.opencart.utils.ElementUtil;

public class Accountspage {
	
	private WebDriver driver;
	ElementUtil eleutil;
	//1. private locators
	private By logoutLink = By.linkText("Logout");
	private By myAccountLink=By.linkText("My Account");
	private By headers=By.cssSelector("div#content h2");
	private By search=By.name("search");
	private By SearchIcon=By.cssSelector("div#search button");
	
	
	//2.public constructor
	public Accountspage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	} 
	//3.public page action/method
		public String getAccountpageTitle() {
			String title = eleutil.waitForTitleIs(AppsConstants.ACCOUNTS_PAGE_TITLE, 5);
			System.out.println("Login page Title:  "+title);
			return title;
		}
		public String getAccountPageURL() {
			String currentUrl =eleutil.waitForURLContains(AppsConstants.ACC_PAGE_URL_FRACTION, 5);
			System.out.println("Account page url is:  "+currentUrl);
			return currentUrl;
		}
		public boolean islogoutLinkDisplayed() {
			return eleutil.waitForElementVisible(logoutLink, 5).isDisplayed();
		}
		public boolean isMyaccountLinkDisplayed() {
			return eleutil.waitForElementVisible(myAccountLink, 5).isDisplayed();
		}
		public List<String> accountpageHeaders() {
			List<String> headerList=new ArrayList<String>();
			List<WebElement> headerEleList = eleutil.getElements(headers);
			for (WebElement e : headerEleList) {
				String text = e.getText();
				headerList.add(text);
			}
			return headerList;
		}
		
		
		public SearchResultPage dosearch(String searchkey) {
			System.out.println("Searching product name is  "+searchkey);
			eleutil.doSendKeys(search, searchkey);
			eleutil.doClick(SearchIcon);
			return new SearchResultPage(driver);
			
			
		}
}
