package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	ElementUtil eleutil;
	private Map<String, String> productmap=new HashMap<String, String>();
	//1. private locators
	private By productHeader = By.tagName("h1");
	private By images=By.cssSelector("ul.thumbnails img");
	private By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	
	//2.public constructor
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	} 
	public String getProductHeader() {
		String header = eleutil.doGetElementText(productHeader);
		System.out.println("this is my product header:  "+header);
		return header;
	}
	public int getProductImageCount() {
		int totalImages = eleutil.waitForElementsVisible(images, 10).size();
		System.out.println("Image count for :"+getProductHeader()+" :"+totalImages);
		return totalImages;
	}
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	private void getProductMetaData() {
		List<WebElement> metalist = eleutil.getElements(productMetaData);
		for (WebElement e : metalist) {
			String text = e.getText();
			String metakey = text.split(":")[0].trim();
			String metavalue = text.split(":")[1].trim();
			productmap.put(metakey, metavalue);
		}
	}
//	$2,000.00
//	Ex Tax: $2,000.00
	private void getProductPriceData() {
		List<WebElement> pricedata = eleutil.getElements(productPriceData);
		String productprice = pricedata.get(0).getText();
		String extaxprice = pricedata.get(1).getText().split(":")[1].trim();
		productmap.put("Product price", productprice);
		productmap.put("extaxprice", extaxprice);
	}
	
	public Map<String, String> getProductDetailMap() {
		productmap.put("Header", getProductHeader());
		productmap.put("Product Images", String.valueOf(getProductImageCount()));
		getProductMetaData();
		getProductPriceData();
		System.out.println("product Details: \n"+productmap);
		return productmap;
	}
	
	
	
	
	

}
