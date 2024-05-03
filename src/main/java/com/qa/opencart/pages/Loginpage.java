package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppsConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;

public class Loginpage {
	private WebDriver driver;
	ElementUtil eleutil;
	//1. private locators
	private By emailID = By.id("input-email");
	private By passwordID = By.id("input-password");
	private By forgotLink=By.linkText("Forgotten Password");
	private By button=By.xpath("//input[@value='Login']");
	private By registertLink=By.linkText("Register");
	private By ranjith=By.linkText("ranjith");


	
	//2.public constructor
	public Loginpage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	} 
	//3.public page action/method
	public String getLoginpageTitle() {
		String title = eleutil.waitForTitleIs(AppsConstants.LOGIN_PAGE_TITLE, 5);
		//System.out.println("Login page Title:  "+title);
		Log.info("Login page Title:   "+title);
		return title;
	}
	public String getCurrentPageURL() {
		String currentUrl =eleutil.waitForURLContains(AppsConstants.LOGIN_PAGE_URL_FRACTION, 5);
		//System.out.println("current page url is:  "+currentUrl);
		Log.info("current page url is:  "+currentUrl);
		return currentUrl;
	}
	public boolean isforgotLinkExist() {
		return eleutil.isElementDisplayed(forgotLink);
	}
	public Accountspage doLogin(String username, String pwsd) {
		System.out.println("these my username and password"+username+": "+pwsd);
		eleutil.waitForElementVisible(emailID, 5).sendKeys(username);
		eleutil.doSendKeys(passwordID, pwsd);
		eleutil.doClick(button);
		return new Accountspage(driver);
	}
	public RegisterationPage navigateToRegisterPage() {
		eleutil.waitForElementVisible(registertLink, 10).click();
		return new RegisterationPage(driver);

	}



}
