package com.hs.kw.base;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	public WebDriver d;
	public Properties prop;
	public WebDriver initDriver(String browserName) throws Exception{
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "D:\\QAplanet\\chromedriver.exe");
			if(prop.getProperty("headless").equals("yes")){
				//headless mode
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				d = new ChromeDriver(options);
				
			}
			else{
				d=new ChromeDriver();
			}
			
		}
	  return d;	//bcoz of this we have give method return type as webdriver above
	}

	public Properties intialiseprop(){
		prop=new Properties();
		try{
		FileInputStream ip=new FileInputStream("D:\\nagneon\\hubspot.com\\src\\main\\java\\com\\qa\\hs\\kw\\config\\config.properties");
		prop.load(ip);
		}catch(Exception e){
			e.printStackTrace();
		}
		return prop;  //bcoz of this we have give method return type as Properties above
	}


}
