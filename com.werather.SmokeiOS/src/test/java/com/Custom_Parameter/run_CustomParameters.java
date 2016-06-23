package com.Custom_Parameter;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.Functions.Functions;

import com.Genaral.Driver;

public class run_CustomParameters extends Driver {
  @Test
  public void Verify_CustomParameters() throws Exception {
	  System.out.println("==============================================");
		System.out.println("****** Cust_Param Verification and Validation Started");
		Functions.Clear_Exceldata("SMOKE");
		Functions.delete_folder();
	
		Cust_param cp= new Cust_param();
		cp.custparam();
		
	  
  }
  @BeforeTest
  public void beforeTest() throws Exception {
	//Preconditions

			//test.main();
			Functions.startCharlesSession();
			Functions.charles_Stop();
			//STR
//			Functions.uninstallApp();
//			Functions.installApp();
			Functions.Appium_Autostart();
			Functions.capabilities();
			Functions.launchtheApp();
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("After Test");

		driver.close();
  }

}
