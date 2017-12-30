package com.selenium.HybridFramework.helper.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.selenium.HybridFramework.utility.ResourceHelper;

 
 

@SuppressWarnings("rawtypes")
public class LoggerHelper {
  
	private static boolean root = false;
	public static Logger getLogger(Class clas) {
		if(root) {
			return Logger.getLogger(clas);
		}
		PropertyConfigurator.configure(ResourceHelper.getResourcePath("/log4j.properties"));
		
		root = true;
		return Logger.getLogger(clas);
	}
  
}
