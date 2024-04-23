package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {

	private WebDriver driver;
	ElementUtil eleutil;
	//1. private locators
	
	private By productSearch=By.cssSelector("div.product-thumb");
	
	
	//2.public constructor
	public SearchResultPage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	} 
	
	public int getSearchProductCount() {
		return eleutil.waitForElementsVisible(productSearch, 5).size();
	}
	public ProductInfoPage selectproduct(String productName) {
		System.out.println("Searching for product :  "+productName);
		eleutil.waitForElementVisible(By.linkText(productName), 10).click();
		return new ProductInfoPage(driver);
		
	}
}
