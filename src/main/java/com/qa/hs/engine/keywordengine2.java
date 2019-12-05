package com.qa.hs.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hs.kw.base.Base;

public class keywordengine2 {
	
	public WebDriver d;
	public Properties prop;
	public static Workbook book;
	public Sheet sheet; //poi ss user model package
	public Base base;
	public WebElement element;

	public final String SCENARIO_SHEET_PATH="D:\\nagneon\\keyworddriven\\src\\main\\java\\com\\qa\\hs\\scenario\\keyworddriven.xlsx";
	public void startexecution(String sheetname){
		FileInputStream file=null;
		try {
			file=new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			book=WorkbookFactory.create(file);//providing workbook ref here
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet=book.getSheet(sheetname);//providing sheet ref here bcoz getsheet method returns
		                                //sheetwith given name
		int k=0; //for column iteration in excel
		for(int i =0; i<sheet.getLastRowNum(); i++){
			try{
			String locatorType=sheet.getRow(i+1).getCell(k+1).toString().trim(); 
			String locatorValue=sheet.getRow(i+1).getCell(k+2).toString().trim();
			/*if(!locatorcolvalue.equalsIgnoreCase("NA")){
				locatorName=locatorcolvalue.split("=")[0].trim(); //id
				locatorValue=locatorcolvalue.split("=")[1].trim(); // username
				
			}*/
			String actioncol=sheet.getRow(i+1).getCell(k+3).toString().trim();
			String valuecol=sheet.getRow(i+1).getCell(k+4).toString().trim();
			switch (actioncol) {
			case "open browser":
				base = new Base();
				prop=base.intialiseprop();
				if(valuecol.isEmpty()||valuecol.equals("NA")){
					d=base.initDriver(prop.getProperty("browser")); //if Value column in excel sheet is empty
					                                                //or NA then it will search for browser in 
					                                                //config.properties file 
				}
				else{
					d=base.initDriver(valuecol);
				}
				break;
			case "enter url":
				if(valuecol.isEmpty()||valuecol.equals("NA")){
					d.get(prop.getProperty("url"));
				}
				else{
					d.get(valuecol);
					Thread.sleep(5000);
				}
				break;
			case "quit":
				d.quit();
				break;
				
			default:
				break;
			}
			
			switch (locatorType) {
			case "id":
				 element=d.findElement(By.id(locatorValue));
				if(actioncol.equalsIgnoreCase("sendkeys")){
					element.clear();
					element.sendKeys(valuecol);
					
				}else if(actioncol.equalsIgnoreCase("click")){
					element.click();
				}else if(actioncol.equalsIgnoreCase("getText")){
					String eletext = element.getText();
					System.out.println(eletext); //storing and printing text value of an elemnt
				}
				break;
			case "xpath":
				element=d.findElement(By.xpath(locatorValue));
				if(actioncol.equalsIgnoreCase("sendkeys")){
					element.clear();
					element.sendKeys(valuecol);
					
				}else if(actioncol.equalsIgnoreCase("click")){
					element.click();
				}else if(actioncol.equalsIgnoreCase("isDisplayed")){
					element.isDisplayed();
				}else if(actioncol.equalsIgnoreCase("getText")){
					String eletext = element.getText();
					System.out.println(eletext); //storing and printing text value of an elemnt
				}
				break;
			case "className":
				element=d.findElement(By.className(locatorValue));
				if(actioncol.equalsIgnoreCase("sendkeys")){
					element.clear();
					element.sendKeys(valuecol);
					
				}else if(actioncol.equalsIgnoreCase("click")){
					element.click();
				}else if(actioncol.equalsIgnoreCase("isDisplayed")){
					element.isDisplayed();
				}else if(actioncol.equalsIgnoreCase("getText")){
					String eletext = element.getText();
					System.out.println(eletext); //storing and printing text value of an elemnt
				}
				break;	
			case "name":
				element=d.findElement(By.name(locatorValue));
				if(actioncol.equalsIgnoreCase("sendkeys")){
					element.clear();
					element.sendKeys(valuecol);
					
				}else if(actioncol.equalsIgnoreCase("click")){
					element.click();
				}else if(actioncol.equalsIgnoreCase("isDisplayed")){
					element.isDisplayed();
				}else if(actioncol.equalsIgnoreCase("getText")){
					String eletext = element.getText();
					System.out.println(eletext); //storing and printing text value of an elemnt
				}
				break;	
			case "cssSelector":
				element=d.findElement(By.cssSelector(locatorValue));
				if(actioncol.equalsIgnoreCase("sendkeys")){
					element.clear();
					element.sendKeys(valuecol);
					
				}else if(actioncol.equalsIgnoreCase("click")){
					element.click();
				}else if(actioncol.equalsIgnoreCase("isDisplayed")){
					element.isDisplayed();
				}else if(actioncol.equalsIgnoreCase("getText")){
					String eletext = element.getText();
					System.out.println(eletext); //storing and printing text value of an elemnt
				}
				break;		
			case "partialLinkText":
				element = d.findElement(By.partialLinkText(locatorValue));
				element.click();
				break;
			
				
			case "linkText":	
				element=d.findElement(By.linkText(locatorValue));
                element.click();
                Thread.sleep(3000);
				break;
				
			default:
				break;
			}
		}catch(Exception e){
				}
		
		
	  }
	}



}
