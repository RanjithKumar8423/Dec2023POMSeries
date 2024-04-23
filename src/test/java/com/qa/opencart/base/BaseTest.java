package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.Accountspage;
import com.qa.opencart.pages.Loginpage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterationPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {
	
	
	DriverFactory df;
	protected Properties prop;
	WebDriver driver;
	protected Loginpage loginpage;
	protected Accountspage accpage;
	protected SearchResultPage searchResultPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterationPage registerationPage;
	protected SoftAssert softAssert;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName) {
		df=new DriverFactory();
		prop=df.initprop();
		
		if (browserName!=null) {
			prop.setProperty("browser", browserName); //(key, value)-->key("browser") is updating to testing regressing parameter browser so it will give preference to testrunners
		}
		
		driver=df.initDriver(prop);
		loginpage=new Loginpage(driver);
		softAssert=new SoftAssert();
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
