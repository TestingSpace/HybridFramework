package com.selenium.HybridFramework.helper.Javascript;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.selenium.HybridFramework.helper.logger.LoggerHelper;
 
public class JavaScriptHelper {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(JavaScriptHelper.class);
	public void JavaScipthelper(WebDriver driver) {
		this.driver = driver;
		log.debug("JavaScriptHelper : " + this.driver.hashCode());
	}

	public Object executeScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		log.info(script);
		return exe.executeScript(script, args);
	}

	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		log.info(script);
		return exe.executeScript(script);
	}
	public void scrollToElement(WebElement element) {
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
		log.info(element);
	}
	

	public void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		element.click();
		log.info(element);
	}

	public void scrollIntoView(WebElement element) {
		executeScript("arguments[0].scrollIntoView()", element);
		log.info(element);
	}

	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		log.info(element);
	}

	public void scrollDownVertically() {
		executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollUpVertically() {
		executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	public void scrollDownByPixel() {
		executeScript("window.scrollBy(0,1500)");
	}

	public void scrollUpByPixel() {
		executeScript("window.scrollBy(0,-1500)");
	}

	public void ZoomInBypercentage() {
		executeScript("document.body.style.zoom='40%'");
	}

	public void ZoomBy100percentage() {
		executeScript("document.body.style.zoom='100%'");
	}
	
}
