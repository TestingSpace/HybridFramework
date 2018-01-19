package com.selenium.HybridFramework.extent;

import java.io.File;

import com.beust.jcommander.Strings;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;


	static String reportPath = (("Report")+"/ExtReport.html");
	public static ExtentReports getInstance() {
		if (extent == null) {
			extent = new ExtentReports(reportPath, true,DisplayOrder.NEWEST_FIRST);
			extent.loadConfig(new File(System.getProperty("user.dir")+"//extent-config.xml"));
			extent.addSystemInfo("Selenium Version" ,"3.0.1").addSystemInfo("Environment","QA");
		}
		return extent;
		
	} 
}
