package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppsConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void infopageSetup() {
		accpage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@DataProvider
	public Object [][] getProductSearchData() {
		return new Object [][] {
			{"macbook","MacBook Pro"},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"imac","iMac"},
			{"Samsung","Samsung Galaxy Tab 10.1"},
		};
	}
	
	@Test(priority = -1,dataProvider ="getProductSearchData" )
	public void productHeaderTest(String searchProduct, String selectProduct) {
		searchResultPage = accpage.dosearch(searchProduct);
		productInfoPage=searchResultPage.selectproduct(selectProduct);
		Assert.assertEquals(productInfoPage.getProductHeader(), selectProduct);
	}
	@DataProvider
	public Object [][] getProductImageCount() {
		return new Object [][] {
			{"macbook","MacBook Pro", 4},
			{"Samsung","Samsung SyncMaster 941BW", 1},
			{"imac","iMac", 3},
			{"Samsung","Samsung Galaxy Tab 10.1", 7},
		};
	}
	@DataProvider(name="excelproductdata")
	public Object [][] getProductSearchDatafromExcel() {
		return ExcelUtil.getTestData(AppsConstants.PRODUCT_SHEET_NAME);
	}
	
	
	@Test(dataProvider ="excelproductdata" )
	public void ProductImageCountTest(String searchProduct, String selectProduct, String imageCount) {
		searchResultPage = accpage.dosearch(searchProduct);
		productInfoPage=searchResultPage.selectproduct(selectProduct);
		Assert.assertEquals(productInfoPage.getProductImageCount(), Integer.parseInt(imageCount));
	}
	@Test
	public void productInfoTest() {
		searchResultPage = accpage.dosearch("macbook");
		productInfoPage=searchResultPage.selectproduct("MacBook Pro");
		Map<String, String> productActDetailMap = productInfoPage.getProductDetailMap();
		softAssert.assertEquals(productActDetailMap.get("Brand"), "Apple");
		softAssert.assertEquals(productActDetailMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productActDetailMap.get("Reward Points"), "800");
		softAssert.assertEquals(productActDetailMap.get("Product price"), "$2,000.00");
		softAssert.assertEquals(productActDetailMap.get("extaxprice"), "$2,000.00");
		softAssert.assertAll();
	}
	
	
}
