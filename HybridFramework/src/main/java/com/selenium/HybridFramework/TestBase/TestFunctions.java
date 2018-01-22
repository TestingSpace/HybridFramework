package com.selenium.HybridFramework.TestBase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gargoylesoftware.htmlunit.WebConsole.Logger;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.selenium.HybridFramework.extent.ExtentManager;
import com.selenium.HybridFramework.helper.Browser.BrowserHelper;
import com.selenium.HybridFramework.helper.DropDown.DropDownHelper;

public class TestFunctions {
	// Instance and variables declared
	TestBase TB =new TestBase();
	ExtentReports report = ExtentManager.getInstance();
	ExtentTest test ;
	static WebDriver driver;
	//method to open browser
	public void openbrowser(String browser){
		TB.getBrowser(browser);
		driver = TB.driver;
	}
	
	//method to open URl
	public void openurl(){
		TB.geturl();
	}
	// login function
	public void login() throws Exception{
		 TB.getBrowser("firefox");
		 TB.geturl();
		 TB.getWebElement("username").sendKeys("demo");
		 TB.getWebElement("password").sendKeys("demo");
		 TB.getWebElement("submitbutton").click();
	}
	// logout
	public void logout() throws Exception{
		TB.getWebElement("logoutbutton").click();
	}
	public void invalidlogin() throws Exception{
		 TB.getBrowser("firefox");
		 TB.geturl();
		 TB.getWebElement("username").sendKeys("invalid");
		 TB.getWebElement("password").sendKeys("demo");
		 TB.getWebElement("submitbutton").click();
		 // 
	}
	public void uploadmedia() throws Exception{
		TB.getWebElement("postmedia").sendKeys("Test image uplaod");
		Thread.sleep(4000);
		TB.getWebElement("mediabutton").sendKeys("/home/abc/Pictures/Wallpapers/mbuntu-3.jpg");
		TB.getWebElement("postbutton").click();
	}
	public void uploadmediabyurl() throws Exception{
		TB.getWebElement("postmedia").sendKeys("Test media uplaod \n https://www.youtube.com/watch?v=6v2L2UGZJAM");
		Thread.sleep(4000);
		TB.getWebElement("postbutton").click();
	}
	public void checkpostprivacy() throws Exception{
			TestFunctions TM =new TestFunctions();
			TM.login();
			TB.logger.info("selecting private menu from drop down");
			WebElement privacybtn = TB.getWebElement("privacybutton");
			System.out.println("click");
			privacybtn.click();
			DropDownHelper DDH = new DropDownHelper(driver);
			
			TB.logger.info("selecting friends menu from drop down");
			DDH.selectbyvalue(privacybtn, "private");
			TB.getWebElement("activitystream").getText().contains("privacy updated successfully.");
			
			TB.logger.info("selecting private menu from drop down");
			DDH.selectbyvalue(privacybtn, "friends");
			TB.getWebElement("activitystream").getText().contains("privacy updated successfully.");
			
			TB.logger.info("selecting Logged in users menu from drop down");
			DDH.selectbyvalue(privacybtn, "Logged in users");
			TB.getWebElement("activitystream").getText().contains("privacy updated successfully.");
			
			TB.logger.info("selecting public menu from drop down");
			DDH.selectbyvalue(privacybtn, "public");
			TB.getWebElement("activitystream").getText().contains("privacy updated successfully.");
			TM.logout();
	}
	public void checkfrmfile(String privacymenu) throws Exception{
		TestFunctions TM =new TestFunctions();
		TM.login();
		TB.logger.info("selecting private menu from drop down");
		WebElement privacybtn = TB.getWebElement("privacybutton");
		DropDownHelper DDH = new DropDownHelper(driver);
		TB.logger.info("selecting "+privacymenu+" menu from drop down");
		DDH.selectbyvalue(privacybtn, privacymenu);
		TB.getWebElement("activitystream").getText().contains("privacy updated successfully.");
		
	}
	public void uploadfilemediaref() throws Exception{
		TB.geturlmedia();
		TB.getWebElement("uploadbutton").click();
		//TB.getWebElement("mediadropdown").click();
		//TB.getWebElement("mediaprivacy").click();
		Thread.sleep(4000);
		TB.getWebElement("dragarea").sendKeys("/home/abc/Pictures/Wallpapers/mbuntu-3.jpg");
		TB.getWebElement("termscheckbox").click();
		WebElement element = TB.getWebElement("startuploadbutton");
		TB.waitForElement(TB.driver, 4, element);
		TB.getWebElement("startuploadbutton").click();
		
		
	}
}
