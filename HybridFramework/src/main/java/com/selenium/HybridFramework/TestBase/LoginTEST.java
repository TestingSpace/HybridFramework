package com.selenium.HybridFramework.TestBase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import com.selenium.HybridFramework.extent.ExtentManager;


public class LoginTEST {
	ExtentReports report = ExtentManager.getInstance();
	ExtentTest test ;
	static WebDriver driver;
	
	@Test
	public void openURLtest() {
		test = report.startTest("Reg Test");
		
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/driver/geckodriver1.exe");
		driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.google.co.in");
		driver.close();
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
