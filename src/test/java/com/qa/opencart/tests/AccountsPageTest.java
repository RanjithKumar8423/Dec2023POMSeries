package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppsConstants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetup() {
		accpage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accpageTitleTest() {
		String actTitle = accpage.getAccountpageTitle();
		Assert.assertEquals(actTitle, AppsConstants.ACCOUNTS_PAGE_TITLE);
	}
	@Test
	public void accpageURLTest() {
		String actURL = accpage.getAccountPageURL();
		Assert.assertTrue(actURL.contains(AppsConstants.ACC_PAGE_URL_FRACTION));
	}
	@Test
	public void isMyAccountLinkExistTest() {
		Assert.assertTrue(accpage.isMyaccountLinkDisplayed());
	}
	@Test
	public void accpageHeaderListTest() {
		List<String> actualHeadersList = accpage.accountpageHeaders();
		System.out.println(actualHeadersList);
	}
	@Test
	public void doSearchTest() {
		accpage.dosearch("macbook");
	}

}
