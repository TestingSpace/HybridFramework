package com.selenium.HybridFramework.helper.assertionHelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.selenium.HybridFramework.helper.logger.LoggerHelper;

public class VerificationHelper {

	private static final Logger log = LoggerHelper.getLogger(LoggerHelper.class);
			public static synchronized  boolean verifyElementPresent(WebElement element) {
				boolean isDisplayed = false;
				try {
					isDisplayed =element.isDisplayed();
					log.info(element.getText() +" is Displayed ");
				}
				catch(Exception ex){
					log.error("Element not found "+ ex);
				}
				return isDisplayed;
	}
			public static synchronized boolean verifyElementNotPresent(WebElement element) {
				boolean isDisplayed = false;
				try {
					element.isDisplayed();
					log.info(element.getText() +" is Displayed ");
				}
				catch(Exception ex){
					log.error("Element not found "+ ex);
					isDisplayed = true;
				}
				return isDisplayed;
			}
			
			public static synchronized boolean verifyTextEqual(WebElement element , String expectedText) {
				boolean flag = false;
				try {
					String actualText= element.getText();
					
					if(actualText.equals(expectedText)) {
						log.info("actual Text is :"+ actualText+"Expected Text is "+expectedText);
						return flag =true;
					}
					else {
						log.info("actual Text is :"+ actualText+"Expected Text is "+expectedText);
						return flag;
					}
				}
				catch(Exception ex) {
					log.info("actual Text is :"+ element.getText()+"Expected Text is "+expectedText);
					log.info("text not matching "+ ex);
					return flag;
				}
			}
}
