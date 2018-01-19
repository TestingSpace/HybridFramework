package com.selenium.HybridFramework.TestBase;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.selenium.HybridFramework.extent.ExtentManager;
import com.selenium.HybridFramework.helper.Browser.BrowserHelper;

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
		BrowserHelper test1 = new BrowserHelper(driver);
		
	}
	//method to open URl
	public void openurl(String url){
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
