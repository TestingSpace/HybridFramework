package com.selenium.HybridFramework.TestBase;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import javax.swing.table.TableStringConverter;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.xml.dtm.ref.ExpandedNameTable;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	public static final Logger logger = Logger.getLogger(TestBase.class.getName());
	public WebDriver driver;
	public Properties OR;
	public File f1;
	public FileInputStream file;
	private Object calendar;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public ITestResult result;
	static {
		Calendar calendar = Calendar.getInstance();
		
		SimpleDateFormat formater= new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir")+"/src/main/java/com/selenium/HybridFrameWork/Report"+formater.format(calendar.getTime())+".html",false);
	}

	public void getBrowser(String browser){
		if(System.getProperty("os.name").contains("Window")){
			if(browser.equalsIgnoreCase("firefox")){
				//https://github.com/mozilla/geckodriver/releases
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/driver/geckodriver.exe");
				driver = new FirefoxDriver();
				//driver.get(url);
			}
			else if(browser.equalsIgnoreCase("chrome")){
				//https://chromedriver.storage.googleapis.com/index.html
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driver/chromedriver.exe");
				driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("htmlunit")){
				//https://chromedriver.storage.googleapis.com/index.html
				//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driver/chromedriver.exe");
				 driver = new HtmlUnitDriver();
			}
		}
		else if(System.getProperty("os.name").contains("Mac")){
			System.out.println(System.getProperty("os.name"));
			if(browser.equalsIgnoreCase("firefox")){
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/driver/geckodriver.exe");
				driver = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver");
				driver = new ChromeDriver();
			}
		}
	}
	
	public void loadpropertiesFiles() throws IOException {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		 OR = new Properties();
		f1 = new File(System.getProperty("user.dir")+"/src/main/java/com/HybridFramework/Config/config.properties");
		file = new FileInputStream(f1);
		OR.load(file);
		logger.info("loading cofig.properties");
		
		f1 = new File(System.getProperty("user.dir")+"/src/main/java/com/HybridFramework/Config/or.properties");
		file = new FileInputStream(f1);
		OR.load(file);
		logger.info("loading or.properties");
		
		f1 = new File(System.getProperty("user.dir")+"/src/main/java/com/selenium/HybridFrameWork/Properties/homepage.properties");
		file = new FileInputStream(f1);
		OR.load(file);
		logger.info("loading homepage.properties");
	}
	public String getScreenShot(String imageName) throws IOException {
		if(imageName.equals("")) {
			imageName="blank";
		}
		Object image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String imageLocation=System.getProperty("user.dir"+"/src/main/java/com/selenium/HybridFrameWork/ScreenShot");
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater= new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String actualImageName=imageLocation+imageName+"_"+formater.format(calender.getTime())+".png";
		File destFile=new File(actualImageName);
		FileUtils.copyFile((File) image, destFile);
		return actualImageName;
	}
	public WebElement waitForElement(WebDriver driver,long time,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));	
	}
	public WebElement waitForElementWithPollingInterval(WebDriver driver,long time,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.pollingEvery(5, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.elementToBeClickable(element));	
	}
	public void implicitlywait(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	public void getResult(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName()+"Test is passed");
		} else if(result.getStatus()==ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName()+"Test is skipped and reason is :-"+result.getThrowable());
		}else if(result.getStatus()==ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getName()+"Test is skipped and reason is :-"+result.getThrowable());
			String screen =getScreenShot("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		}else if(result.getStatus()==ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName()+"Test is started");
		}
	}
	@AfterMethod()
	public void afterMethod(ITestResult result) throws IOException {
		getResult(result);
	}
	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName()+"Test started");
	}
	@AfterClass(alwaysRun=true)
	public void endtest() {
		driver.quit();
		extent.endTest(test);
		extent.flush();
	}
	
	public List<WebElement> getLocators(String locator) throws Exception {
		String[] split = locator.split(":");
		String locatorType =split[0];
		String locatorValue =split[1];
		
			
		
		if (locatorType.toLowerCase().equals("id"))	
			return driver.findElements(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))	
			return driver.findElements(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname"))	
			||  (locatorType.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname"))	
				||  (locatorType.toLowerCase().equals("tag")))
				return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktesxt"))	
				||  (locatorType.toLowerCase().equals("link")))
				return driver.findElements(By.linkText(locatorValue));

		else if (locatorType.toLowerCase().equals("partialinktext"))	
			return driver.findElements(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("csssector"))	
				||  (locatorType.toLowerCase().equals("css")))
				return driver.findElements(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))	
			return driver.findElements(By.xpath(locatorValue));
		else throw new Exception("Unknown locator type'"+locatorType+"'");
	
	}
	public WebElement getLocator(String locator) throws Exception {
		String[] split = locator.split(":");
		String locatorType =split[0];
		String locatorValue =split[1];
		System.out.println(locator);
		System.out.println("locatorType:-"+locatorType);
		System.out.println("locatorValue:-"+locatorValue);
		if (locatorType.toLowerCase().equals("id"))	
			return driver.findElement(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))	
			return driver.findElement(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname"))	
			||  (locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname"))	
				||  (locatorType.toLowerCase().equals("tag")))
				return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktesxt"))	
				||  (locatorType.toLowerCase().equals("link")))
				return driver.findElement(By.linkText(locatorValue));

		else if (locatorType.toLowerCase().equals("partialinktext"))	
			return driver.findElement(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("csssector"))	
				||  (locatorType.toLowerCase().equals("css")))
				return driver.findElement(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))	
			return driver.findElement(By.xpath(locatorValue));
		else throw new Exception("Unknown locator type'"+locatorType+"'");
	
	}
	public WebElement getWebElement(String locator) throws Exception{
		return getLocator(OR.getProperty(locator));
	}

	public List<WebElement> getWebElements(String locator) throws Exception{
		return getLocators(OR.getProperty(locator));
	}
	public void getpropertiesData() {
		
	}
	public static void main(String[] args) throws Exception {
		TestBase test = new TestBase();
		/* Function list 
		 *  1)  getBrowser - open browser
		 *  2)  getScreenShot - capture screen shot
		 *  3)  loadpropertiesFiles - load properties file to get data from properties files
		 *  4)  getProperty - get properties from file
		 *  5)  getLocators - select locator out of (id , name ,tagname , classname , linktext ,css ,xpath)
		 *  6)  getWebElement - get element .
		 *  7)  getWebElements - list of elements
		 *  8)  implicitlywait(*Time) - waiting of mentioned time
		 *  9)  getResult 0
		 */ 
		//test.getBrowser("Firefox","https://github.com/TestingSpace");
		//test.getBrowser("htmlunit");
		//WebDriver driver = new HtmlUnitDriver();
		test.driver.get("https://github.com/TestingSpace");
		System.out.println("Success-------------");
		test.getScreenShot("image1");
		//test.loadpropertiesFiles();
		//System.out.println(test.OR.getProperty("username"));
		//test.getWebElement(test.OR.getProperty("username"));
		//test.getWebElement("username");
		//test.getLocators(test.OR.getProperty("username"));
		//test.getWebElement("password");
		System.out.println(test.OR.getProperty("url"));
		//
		//System.out.println(test.OR.getProperty("password"));

		 
	}
}
