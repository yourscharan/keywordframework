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

public class keywordengine {
	
	
	public WebDriver d;
	public Properties prop;
	public static Workbook book;
	public Sheet sheet; //poi ss user model package
	public Base base;
	public WebElement element;
	String locatorName=null;
	String locatorValue=null;
	public final String SCENARIO_SHEET_PATH="D:\\nagneon\\hubspot.com\\src\\main\\java\\com\\qa\\hs\\kw\\scenario\\keyworddriven.xlsx";
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
		for(int i =0;i<sheet.getLastRowNum();i++){
			try{
			String locatorcolvalue=sheet.getRow(i+1).getCell(k+1).toString().trim(); //means that 2nd row and 2nd column
			if(!locatorcolvalue.equalsIgnoreCase("NA")){
				locatorName=locatorcolvalue.split("=")[0].trim(); //id
				locatorValue=locatorcolvalue.split("=")[1].trim(); // username
				
			}
			String actioncol=sheet.getRow(i+1).getCell(k+2).toString().trim();
			String valuecol=sheet.getRow(i+1).getCell(k+3).toString().trim();
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
			
			switch (locatorName) {
			case "id":
				 element=d.findElement(By.id(locatorValue));
				if(actioncol.equalsIgnoreCase("sendkeys")){
					element.clear();
					element.sendKeys(valuecol);
					
				}else if(actioncol.equalsIgnoreCase("click")){
					element.click();
				}
				break;
				
			case "linkText":	
				element=d.findElement(By.linkText(locatorValue));
                element.click();

			default:
				break;
			}
		}catch(Exception e){
				}
		
		
	  }
	}

	

}
