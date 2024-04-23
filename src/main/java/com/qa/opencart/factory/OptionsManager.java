package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.opencart.logger.Log;

public class OptionsManager {
	
	private Properties prop;
	
	private ChromeOptions co;
	private EdgeOptions eo;
	private FirefoxOptions fo;
	
	public OptionsManager(Properties prop) {
		this.prop=prop;
	}
	
	public ChromeOptions getChromeOptions() {
		co=new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			Log.info("Running chrome in headless mode");
		co.addArguments("--headless");
	    }
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			co.addArguments("--incognito");
			Log.info("Running chrome in Incognito mode");
		    }
		return co;
	}
	public EdgeOptions getEdgeOptions() {
		eo=new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			Log.info("Running Edge in headless mode");
		eo.addArguments("--headless");
	    }
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			eo.addArguments("--inprivate");
			Log.info("Running Edge in Incognito mode");
		    }
		return eo;
	}



}
