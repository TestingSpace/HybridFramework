package com.selenium.HybridFramework.TestBase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.HybridFramework.extent.ExtentManager;
import com.selenium.HybridFramework.helper.Browser.BrowserHelper;
 

public class RegTEST {
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	SoftAssert softAssert = new SoftAssert();
	ExtentTest test1;
	@Test
	public void doRegisterTest() {
		test = rep.startTest("Reg Test");
	
		test.log(LogStatus.INFO, "Starting the test");
		String expectedTitle = "xx";
		String actualTitle = "xx";
		System.out.println("A");
		test.log(LogStatus.INFO, "Executing assertion/validation");
		softAssert.assertEquals(actualTitle, expectedTitle);
		test.log(LogStatus.PASS, "Sub test1.1 passed ");
		softAssert.assertEquals("a", "b");
		test.log(LogStatus.FAIL, "SUB TESt 1.2 failed");
		test.log(LogStatus.PASS, "Test Passed");
		
	// critical = stop/failure  - Assert
		
	// Non critical = stop/failure -continue - softAssert
	rep.endTest(test);
	rep.flush();
	}
	@Test
	public void voidbn() {
		test = rep.startTest("test 2");
		System.out.println("test2");
		test.log(LogStatus.PASS, "Test 2 is passed ");
		rep.endTest(test);
		rep.flush();
	}
	@Test
	public void bbn() {
		test = rep.startTest("Test 3 ( gonna fail )");
		try {
			Assert.assertEquals("2", "4");
		} catch (Throwable t) {
			test.log(LogStatus.FAIL, "Test Case 3 Failed = "+ t.getMessage());
		}
		
		rep.endTest(test);
		rep.flush();
	}
	@Test 
	public void newtest() {
		test = rep.startTest("Test 4 < successful >");
		System.out.println("Test 4 exected ");
		test.log(LogStatus.FAIL, "Test Case 4 done ");
		test.log(LogStatus.PASS, "Test 4 done");
		rep.endTest(test);
		rep.flush();

	}
	@Test
	public void BrowserHelperTest() throws Exception {
		test1=rep.startTest("New test browser launcher");
		// first get browser
		TestBase test = new TestBase();
		test.getBrowser("firefox");
		test.driver.get("https://www.google.co.in");
		String locator = "//*[@id=\"tsf\"]/div[2]/div[3]/center/input[1]";
		test.getLocator(locator);
		
		WebDriver driver = null;
		BrowserHelper bh = new BrowserHelper(driver);
		bh.refresh();
		test.endtest();

		rep.endTest(test1);
		rep.flush();
	
	}
	@AfterMethod
	public void quit() {
		rep.endTest(test);
		rep.flush();
	}
	

}
