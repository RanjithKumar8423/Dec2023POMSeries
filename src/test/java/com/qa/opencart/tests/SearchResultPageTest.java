package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchResultPageTest extends BaseTest{
	
	@BeforeClass
	public void searchResultPageSetup() {
		accpage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@DataProvider
	public Object [][] getProductCountData() {
		return new Object [][] {
			{"macbook",3},
			{"Samsung",2},
			{"imac",1}
		};
	}

	@Test(dataProvider = "getProductCountData")
	public void doSearchTest(String productName, int productCount) {
		searchResultPage = accpage.dosearch(productName);
		Assert.assertEquals(searchResultPage.getSearchProductCount(), productCount); 
	}

}
