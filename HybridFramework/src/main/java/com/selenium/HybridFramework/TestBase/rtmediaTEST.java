package com.selenium.HybridFramework.TestBase;

import java.io.IOException;  

public class rtmediaTEST {

	public static void main(String[] args) throws Exception {
		 
		TestFunctions TF = new TestFunctions();
		TF.login();
		TF.uploadfilemediaref();
		TF.logout();
		
	}

}
