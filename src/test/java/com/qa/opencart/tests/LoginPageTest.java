package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppsConstants;

public class LoginPageTest extends BaseTest{

	@Test(priority = 1)
	public void getLoginpageTitleTest() {
		String actTitle = loginpage.getLoginpageTitle();
		Assert.assertEquals(actTitle, AppsConstants.LOGIN_PAGE_TITLE);
	}
	@Test(priority = 2)
	public void getCurrentPageURLTest() {
		String actURL = loginpage.getCurrentPageURL();
		Assert.assertTrue(actURL.contains(AppsConstants.LOGIN_PAGE_URL_FRACTION));
	}
	@Test(priority = 3)
	public void isforgotLinkExistTest() {
		Assert.assertTrue(loginpage.isforgotLinkExist());
	}
	@Test(priority = 4)
	public void doLoginTest()  {
		
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		String accountpageTitle = accpage.getAccountpageTitle();
		Assert.assertEquals(accountpageTitle, AppsConstants.ACCOUNTS_PAGE_TITLE);
	}
	
}
