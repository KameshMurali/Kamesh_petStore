package com.kamesh.ui.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Table {

	private WebDriver driver;
	public Table(WebDriver driver) {
		this.driver=driver;
	}
	public List<WebElement> getHeaders(){
		return driver.findElements(By.xpath("//thead/tr/th"));
	}
	public List<WebElement> getRows(){
		return driver.findElements(By.xpath("//tbody/tr"));
	}
	public int getRowPosition(String text){
		List<WebElement> rows = getRows();
		for(int i=0;i<rows.size();i++) {
			String row = rows.get(i).getText();
			if(row.contains(text)) {
				return i+1;
			}
		}
		return 0;
	}
	
	public int getHeaderPosition(String text){
		List<WebElement> headers = getRows();
		for(int i=0;i<headers.size();i++) {
			String header = headers.get(i).getText();
			if(header.contains(text)) {
				return i+1;
			}
		}
		return 0;
	}
	
	public List<WebElement> getElementsByHeader(String text){
		String xpath = String.format("//tbody/td[%d]", getHeaderPosition(text));
		return driver.findElements(By.xpath(xpath));
	}
	
	public List<WebElement> getElementsByHeaderAndRow(String header, String row){
		String xpath = String.format("//tbody/td[%d]", getHeaderPosition(header));
		return driver.findElements(By.xpath(xpath));
	}
}
