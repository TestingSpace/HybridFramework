package com.selenium.HybridFramework.helper.Browser;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.selenium.HybridFramework.helper.logger.LoggerHelper;

public class BrowserHelper {

	//private WebDriver driver;
	public WebDriver driver;
	private Logger Log = LoggerHelper.getLogger(LoggerHelper.class);

	 
	public BrowserHelper(WebDriver driver) {
		this.driver = driver;
		Log.debug("BrowserHelper : " + this.driver.hashCode());
	}
	
	public void getBack() {
		driver.navigate().back();
		Log.info("");
	}
	
	public void goForward() {
		driver.navigate().forward();
		Log.info("");
	}
	
	public void refresh() {
		driver.navigate().refresh();
		Log.info("");
	}
	
	public Set<String> getWindowHandles(){
		Log.info("");
		return driver.getWindowHandles();
	}
	
	public void SwitchToWindow(int index) {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandles());
		if (index<0 || index > windowsId.size()) {
			throw new IllegalArgumentException("Illegal index :"+ index);
		}
		driver.switchTo().window(windowsId.get(index));
		Log.info("");
	}
	 
	public void switchToParentWindow() {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandles());
		driver.switchTo().window(windowsId.get(0));
		Log.info("");
	}
	
	public void switchToParentWithChildClose() {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandles());

		for (int i = 1; i < windowsId.size(); i++) {
			Log.info(windowsId.get(i));
			driver.switchTo().window(windowsId.get(i));
			driver.close();
		}
		switchToParentWindow();
	}
	
	public void switchToFrame(String nameorId) {
		driver.switchTo().frame(nameorId);
		Log.info("");
	}
}
