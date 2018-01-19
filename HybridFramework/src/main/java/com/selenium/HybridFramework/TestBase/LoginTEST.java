package com.selenium.HybridFramework.TestBase;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.HybridFramework.extent.ExtentManager;
import com.selenium.HybridFramework.helper.Browser.BrowserHelper;
import com.selenium.HybridFramework.helper.Javascript.JavaScriptHelper;
 


public class LoginTEST {
	ExtentReports report = ExtentManager.getInstance();
	ExtentTest test ;
	static WebDriver driver;
	public static Properties OR;
	@Test
	public void openURLtest() {
		test = report.startTest("Reg Test");
		test.log(LogStatus.INFO, "-------Test Started-------");
		TestBase TB =new TestBase();
		TB.getBrowser("firefox");
		driver = TB.driver;
		BrowserHelper test1 = new BrowserHelper(driver);
	
		test.log(LogStatus.INFO, "Statring Browser");
		test.log(LogStatus.INFO, "Getting URL ");
		test1.driver.get("https://www.youtube.com/");
		try {
			TB.getScreenShot("Youtube screen");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//test1.refresh();
		test.log(LogStatus.INFO, "Waiting .......");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.INFO, "Getting URL ");
		test1.driver.get("https://www.google.co.in");
		test.log(LogStatus.INFO, "Waiting ........");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.log(LogStatus.INFO, "Back Button selected");
		test1.getBack();
		test.log(LogStatus.INFO, "Closing browser");
		test1.driver.close();
		test.log(LogStatus.INFO, "-------Test Ended-------");
		test.log(LogStatus.PASS, "Test was Successful  ");
		//test.log(LogStatus.FAIL, "Test was Failed :) ");
		report.endTest(test);
		report.flush();
	}
	@Test
	public void TestSecond(){
		test = report.startTest("JavaScript Helper Tests");
		test.log(LogStatus.INFO, "-------Test Started-------");
		JavaScriptHelper JS = new JavaScriptHelper();
		test.log(LogStatus.INFO, "checking all the functions in Javascript Helper");
		
		
		
		report.endTest(test);
		report.flush();
	}
	@AfterClass(alwaysRun = true)
	public void endTest() {
		//driver.quit();
		report.endTest(test);
		report.flush();
	}
	

}
