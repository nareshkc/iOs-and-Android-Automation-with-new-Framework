package com.Functions;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.Genaral.Driver;
import com.Genaral.PropertyFile;

public class Smoke_Run extends Driver {


	//Pull to refresh Test case
	@Test(priority =0)
	public void pullto_refresh() throws Exception {
		//pull to refresh
		System.out.println("****** Pull To Refresh test cases Started");
		Functions.pulltorefresh();
		Functions.downloadXMLFile();
		Functions.readXML();
		Functions.verifyPubadCal("pulltorefresh");
		System.out.println("==============================================");

	}


	//Verify Clean launch the app Test case
	@Test(priority =1)
	public void cleanlaunch() throws Exception {
		System.out.println("****** Clean launch test cases Started");
		Functions.delete_folder();
		Functions.clear_session();
		Functions.close_launchApp();
		Functions.Scroll_end();
		Functions.downloadXMLFile();
		Functions.Verify_feedcals("CLT");
		//Functions.close_launchApp();
		System.out.println("==============================================");

	}

	@Test(priority =2)
	public void WFXTriggers_cxtg() throws Exception {
		System.out.println("****** WFX Trigger CXTG Cal test cases Started");
		Functions.delete_folder();
		Functions.navigatetoSettingPage();
		Functions.verifyuserloggedIn();
		Functions.logIn();
		//Functions.close_launchApp();
		Functions.clear_session();
		Functions.Kill_realaunch();
		Functions.downloadXMLFile();
		Functions.verifyAPICal("cxtg");
		Functions.navigatetoAddressPage();
		Functions.verifysavedAddresses();
		Functions.selectsavedAddresses(2);
		Functions.readwfxTriggers("cxtg") ;
		System.out.println("==============================================");
	}

	//FACTUAL Test cases
	@Test(priority =3)
	public void WFXTriggers_Factuals() throws Exception {

		System.out.println("****** Factua Cal test cases Started");
		Functions.delete_folder();
		Functions.clear_session();
		Functions.Kill_realaunch();
		Functions.downloadXMLFile();
		Functions.readXML();
		Functions.verifyPubadCal("location");
		Functions.verifyAPICal("location");
		Functions.readlocation_wfxTriggers("location") ;
		System.out.println("==============================================");
	}
	//Lotame Test case
	@Test(priority =4)
	public void ad_crwd() throws Exception {
		System.out.println("****** Lotame Cal test cases Started");
//		Functions.delete_folder();
//		Functions.clear_session();
//		Functions.Kill_realaunch();
//		Functions.downloadXMLFile();
//		Functions.readXML();
		Functions.verifyPubadCal("ad");
		Functions.verifyAPICal("ad");
		Functions.readlocation_wfxTriggers("ad") ;
		System.out.println("==============================================");
	}

	//Verify extended Hourly page ad presence Test case
	@Test(priority =5)
	public void extenedHourlyPage_ads() throws Exception {
		System.out.println("****** Hourly Module test cases Started");
		Functions.close_launchApp();
		Functions.Verify_selectedPages("Hourly");
		Functions.Navigate_extendedPages("Hourly");
		Functions.Verify_Adpresenton_extendedPages("Hourly");
		System.out.println("==============================================");

	}
	//Verify extended Daily page ad presence Test case
	@Test(priority =6)
	public void extenedDailyPage_ads() throws Exception {
		System.out.println("****** Daily Module test cases Started");
		Functions.Verify_selectedPages("Daily");
		Functions.Navigate_extendedPages("Daily");
		Functions.Verify_Adpresenton_extendedPages("Daily");
		System.out.println("==============================================");

	}
	//Verify extended Map page ad presence Test case
	@Test(priority =7)
	public void extenedMapPage_ads() throws Exception {
		System.out.println("****** Map Module test cases Started");
		Functions.Verify_selectedPages("Map");
		Functions.Navigate_extendedPages("Map");
		Functions.Verify_Adpresenton_extendedPages("Map");
		System.out.println("==============================================");

	}
	//Verify extended News page ad presence Test case
	@Test(priority =8)
	public void extenedNewPage_ads() throws Exception {
		System.out.println("****** News Module test cases Started");	
		Functions.Verify_selectedPages("News");
		Functions.Navigate_extendedPages("News");
		Functions.Verify_Adpresenton_extendedPages("News");
		System.out.println("==============================================");

	}


	@Test(priority =9)
	public void setto_TestMode() throws Exception {
		System.out.println("****** Set Test mode and verify BB Cal test cases Started");
		Functions.Setappinto_TestMode();
		Functions.downloadXMLFile();
		Functions.readXML();
		Functions.verifyPubadCal("TestMode");
		System.out.println("==============================================");

	}

	@Test(priority =10)
	public void ThirdParty_beacons() throws Exception {
		System.out.println("****** Thirdparty Beacon test cases Started");
		Functions.thirdPart_Beacon();
		System.out.println("==============================================");
	}


	@BeforeMethod
	public void beforeMethod() {


	}

	@AfterMethod
	public void afterMethod() {
		//System.out.println("After Method");
	}

	@BeforeTest
	public void beforeTest() throws Exception {
		//Preconditions
		//Functions.Appium_Autostart();
		//test.main();
				Functions.startCharlesSession();
				Functions.charles_Stop();
				//STR
				Functions.uninstallApp();
				Functions.installApp();
				Functions.capabilities();
				Functions.launchtheApp();



	}

	@AfterTest
	public void afterTest() {
		System.out.println("After Method");
		driver.close();
	}

}
