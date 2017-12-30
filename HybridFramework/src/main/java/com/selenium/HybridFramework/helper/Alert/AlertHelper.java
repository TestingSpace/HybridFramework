package com.selenium.HybridFramework.helper.Alert;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.selenium.HybridFramework.helper.logger.LoggerHelper;

public class AlertHelper {

	private WebDriver driver;
	private Logger oLog = LoggerHelper.getLogger(AlertHelper.class);
	
	public AlertHelper(WebDriver driver) {
		this.driver = driver;
		oLog.debug("AlertHelper : "+this.driver.hashCode());
	}
	public Alert getAlert() {
		oLog.debug("");
		return driver.switchTo().alert();
	}
	public void AcceptAlert() {
		oLog.info("");
		getAlert().accept();
	}
	public void DissmissAlert() {
		oLog.info("");
		getAlert().dismiss();
	}
	public String getAlertText() {
		String text = getAlert().getText();
		oLog.info(text);
		return text;
	}
	public boolean isAlertPresent() {
		try {
		driver.switchTo().alert();
		oLog.info("true");
		return true;
		}
		catch(NoAlertPresentException e) {
			oLog.info("false");
			return false;
		}
	}
	public void AcceptAlertPresent() {
		if (!isAlertPresent())
			return;
			AcceptAlert();
			oLog.info("");
	}
	public void DismissAlertPresent() {
		if (!isAlertPresent())
			return;
			DissmissAlert();
			oLog.info("");
	}
	public void AcceptPrompt(String text) {
		if (!isAlertPresent())
			return;
		Alert alert = getAlert();
		alert.sendKeys(text);
		alert.accept();
		oLog.info(text);
		
	}
}
