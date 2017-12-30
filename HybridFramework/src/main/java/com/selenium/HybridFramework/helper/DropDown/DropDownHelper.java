package com.selenium.HybridFramework.helper.DropDown;



import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.selenium.HybridFramework.helper.logger.LoggerHelper;

public class DropDownHelper {
	private WebDriver driver;
	private Logger Log = LoggerHelper.getLogger(DropDownHelper.class);
	public DropDownHelper(WebDriver driver) {
		this.driver = driver;
		Log.debug("DropDownHelper : " + this.driver.hashCode());
	}
	public void selectUsingVisibleValue(WebElement element, String VisibleValue) {
		Select select = new Select(element);
		select.selectByVisibleText(VisibleValue);
		Log.info("Locator :" + element + " value :" + VisibleValue);
	}
	public String getSelectedValue(WebElement element) {
		String value = new Select(element).getFirstSelectedOption().getText();
		Log.info("Locator :" + element + " value :" + value );
		return value;
	}
	public void selectedIndex(WebElement element, int intval) {
		Select select = new Select(element);
		select.selectByIndex(intval);
		Log.info("Locator :" + element + " value :" + intval );
	}
	public void selectUsingVisibleText(WebElement element , String text) {
		Select select = new Select(element);
		select.deselectByVisibleText(text);
		Log.info("Locator :" + element + " value :" + text );
	}
	public List<String> getAllDropdownValues(WebElement locator ) {
		Select select = new Select(locator);
		List<WebElement> elementlist = select.getOptions();
		List<String> valuelist = new LinkedList<String>();
		for(WebElement element : elementlist) {
			Log.info(element.getText());
			valuelist.add(element.getText());
		}
		return valuelist;
	}
}
