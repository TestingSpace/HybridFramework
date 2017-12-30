package com.selenium.HybridFramework.helper.genericHelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class GenericHelper {

	private static final Logger Log = Logger.getLogger(GenericHelper.class);
	
	public String realValueFromElement(WebElement element) {
		if (null == element) {
			Log.info("WebElement is null");
			return null;
		}
		boolean displayed = false;
		try {
			displayed = isDisplayed(element);
		}catch(Exception e) {
			Log.info(e);
			Reporter.log(e.fillInStackTrace().toString());
			return null;
		}
		if (!displayed){
			return null;
		}
		String text = element.getText();
		Log.info("WebElement value is :" + text);
		return text;
	}
	public boolean isDisplayed(WebElement element) {
		try {
		element.isDisplayed();
		Log.info("Element is displayed .."+element);
		return true;
		}catch(Exception e) {
			Log.info(e);
			Reporter.log(e.fillInStackTrace().toString());
			return false;
		}
	}
	public boolean isNotDisplayed(WebElement element) {
		try {
		element.isDisplayed();
		Log.info("Element is displayed .."+element);
		return false;
		}catch(Exception e) {
			Log.info(e);
			Reporter.log(e.fillInStackTrace().toString());
			return true;
		}
	}
	public String getDisplatText(WebElement element) {
		if (null == element) {
			return null;
		}
		if(!isDisplayed(element)) {
			return null;
		}
		return element.getText();
	}
	public static synchronized String getElementText(WebElement element) {
		if(null == element) {
			Log.info(" Webelement is null");
			return null;
		}
		String elementText = null;
		try {
			elementText = element.getText();
			return null;
		}catch(Exception ex) {
			Log.info("Element not found "+ex);
			Reporter.log(ex.fillInStackTrace().toString());
		}
		return elementText;
	}
	
}
