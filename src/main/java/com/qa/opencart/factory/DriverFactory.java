package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameWorkExpection;
import com.qa.opencart.logger.Log;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tLDriver=new ThreadLocal<WebDriver>();
	public WebDriver initDriver(Properties prop) {
		String BrowserName=prop.getProperty("browser");
		//System.out.println("These is the Browser:  "+BrowserName);
		Log.info("My Scripts is running on these"+BrowserName+" Browser");
		optionsManager=new OptionsManager(prop);
		
		switch (BrowserName.trim().toLowerCase()) {
		case "chrome":
			//driver=new ChromeDriver(optionsManager.getChromeOptions());
			tLDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "edge":
		//	driver=new EdgeDriver(optionsManager.getEdgeOptions());
			tLDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "firefox":
			tLDriver.set(new FirefoxDriver());
			//driver=new FirefoxDriver();
			break;

		default:
			//System.out.println("Browser not found"+BrowserName);
			Log.error("Browser not found"+BrowserName);
			throw new BrowserException("No Browser found"+BrowserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		 return tLDriver.get();
	}
	
	
	public Properties initprop() {
		
		//envName=qa,dev,stage,prod
		//mvn clean install -Denv="qa"
		FileInputStream ip=null; // do cmd in cd.. and enter-->D: and enter-->navigate to D driver
		prop=new Properties();

		
		String envName = System.getProperty("env");//u can write any thing instead of env but u need to define in command prompt
		System.out.println("Running on tests env :  "+envName);
		
		try {
		if (envName==null) {
			System.out.println("No env given hence running on QA enviroment");
			 ip=new FileInputStream("./src/test/resource/config/config.qa.properties");
		}
		else {
		
		switch (envName.toLowerCase().trim()) {
		case "qa":
			 ip=new FileInputStream("./src/test/resource/config/config.qa.properties");
			break;
		case "dev":
			 ip=new FileInputStream("./src/test/resource/config/config.dev.properties");
			break;
		case "stage":
			 ip=new FileInputStream("./src/test/resource/config/config.stage.properties");
			break;
		case "uat":
			 ip=new FileInputStream("./src/test/resource/config/config.uat.properties");
			break;
		case "prod":
			 ip=new FileInputStream("./src/test/resource/config/config.properties");
			break;

		default:
			System.out.println("Pls pass the right env:  "+envName);
			throw new FrameWorkExpection(AppError.ENV_NAME_NOT_FOUND+" :"+envName);
		}
	}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		
	}
	
	/**
	 * take screenshot
	 */
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);//temp directory
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

	

}
