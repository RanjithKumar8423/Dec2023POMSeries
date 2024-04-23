package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppsConstants;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

public class RegisterationPageTest extends BaseTest {
	
	@BeforeClass
	public void regSetup() {
		registerationPage=loginpage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object [][] getuserRegTestData() {
		return new Object [][] {
			{"Test","kumar","0987563212","test48","yes"},
			{"Test1","kumar1","0987563512","test49","no"},
			{"Test2","kumar2","0987563142","test50","yes"},
		};
	}
	
	@DataProvider
	public Object [][] getuserRegTestDataFromExcel() {
		return ExcelUtil.getTestData(AppsConstants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider ="getuserRegTestDataFromExcel" )
	public void userRegTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		Assert.assertTrue(registerationPage.userRegister(firstName, lastName, 
				StringUtils.getRandomEmailId(),
				telephone, password, subscribe));
	}

}
