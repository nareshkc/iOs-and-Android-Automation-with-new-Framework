package com.Functions;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.Genaral.Driver;

public class Smoke_Run extends Driver {


		//Pull to refresh Test case
		@Test(priority =0,enabled = true)
		public void pullto_refresh() throws Exception {
			System.out.println("==============================================");
			//pull to refresh
			System.out.println("****** Pull To Refresh test cases Started");
			Functions.pulltorefresh();
			Functions.downloadXMLFile();
			Functions.readXML();
			Functions.verifyPubadCal("Smoke","Pulltorefresh");
	
	
		}
	
	
		//Verify Clean launch the app Test case
		@Test(priority =1,enabled = true)
		public void cleanlaunch() throws Exception {
			System.out.println("==============================================");
			System.out.println("****** Clean launch test cases Started");
			Functions.delete_folder();
			Functions.clear_session();
			//Functions.close_launchApp();
			Functions.Scroll_end();
			Functions.downloadXMLFile();
			Functions.Verify_feedcals("CleanLaunch");
			//Functions.close_launchApp();
	
	
		}
	
		@Test(priority =2,enabled = true)
		public void WFXTriggers_cxtg() throws Exception {
			System.out.println("==============================================");
			System.out.println("****** WFX Trigger CXTG Cal test cases Started");
			Functions.delete_folder();
			Functions.navigatetoSettingPage();
			Functions.verifyuserloggedIn();
			Functions.logIn();
			//Functions.close_launchApp();
			Functions.clear_session();
			Functions.close_launchApp();
			Functions.Kill_realaunch();
			//Ad.launchApp();
			Functions.downloadXMLFile();
			Functions.readXML();
			Functions.verifyAPICal("WFXTrigger");
			//Functions.readwfxTriggers("WFXTrigger") ;
			Functions.navigatetoAddressPage();
			Functions.verifysavedAddresses();
			Functions.selectsavedAddresses(2);
			Functions.readwfxTriggers("WFXTrigger") ;
			//Functions.verifyPubadCal("WFXTrigger");
			Functions.verify_wfxtg("WFXTrigger");
	
		}
	
		//FACTUAL Test cases
		@Test(priority =3,enabled = true)
		public void WFXTriggers_Factuals() throws Exception {
			System.out.println("==============================================");
			System.out.println("****** Factua Cal test cases Started");
			//			Functions.delete_folder();
			//			Functions.clear_session();
			//			Functions.Kill_realaunch();
			//			Functions.downloadXMLFile();
			Functions.readXML();
			Functions.verifyPubadCal("Smoke","LocationWFX");
			Functions.verifyAPICal("LocationWFX");
			Functions.readlocation_wfxTriggers("LocationWFX") ;
	
		}
		//Lotame Test case
		@Test(priority =4,enabled = true)
		public void ad_crwd() throws Exception {
			System.out.println("==============================================");
			System.out.println("****** Lotame Cal test cases Started");
			//		Functions.delete_folder();
			//		Functions.clear_session();
			//		Functions.Kill_realaunch();
			//		Functions.downloadXMLFile();
			//		Functions.readXML();
			Functions.verifyPubadCal("Smoke","Lotame");
			Functions.verifyAPICal("Lotame");
			Functions.readlocation_wfxTriggers("Lotame") ;
	
		}
	
		//Verify extended Hourly page ad presence Test case
		@Test(priority =5,enabled = true)
		public void extenedHourlyPage_ads() throws Exception {
			System.out.println("==============================================");
			System.out.println("****** Hourly Module test cases Started");
			//Functions.close_launchApp();
			Functions.Verify_selectedPages("Hourly");
			Functions.Navigate_extendedPages("Hourly");
			Functions.Verify_Adpresenton_extendedPages("Hourly");
	
	
		}
		//Verify extended Daily page ad presence Test case
		@Test(priority =6,enabled = true)
		public void extenedDailyPage_ads() throws Exception {
			System.out.println("==============================================");
			System.out.println("****** Daily Module test cases Started");
			Functions.Verify_selectedPages("Daily");
			Functions.Navigate_extendedPages("Daily");
			Functions.Verify_Adpresenton_extendedPages("Daily");
	
	
		}
		//Verify extended Map page ad presence Test case
		@Test(priority =7,enabled = true)
		public void extenedMapPage_ads() throws Exception {
			System.out.println("==============================================");
			System.out.println("****** Map Module test cases Started");
			Functions.Verify_selectedPages("Map");
			Functions.Navigate_extendedPages("Map");
			Functions.Verify_Adpresenton_extendedPages("Map");
	
	
		}
		//Verify extended News page ad presence Test case
		@Test(priority =8,enabled = true)
		public void extenedNewsPage_ads() throws Exception {
			System.out.println("==============================================");
			System.out.println("****** News Module test cases Started");	
			Functions.Verify_selectedPages("News");
			Functions.Navigate_extendedPages("News");
			Functions.Verify_Adpresenton_extendedPages("News");
	
	
		}


	@Test(priority =9,enabled = true)
	public void setto_TestMode() throws Exception {
		System.out.println("==============================================");
		System.out.println("****** Set Test mode and verify BB Cal test cases Started");
		Functions.Setappinto_TestMode();
		Functions.enternewAddress("08302");
		Functions.downloadXMLFile();
		Functions.readXML();
		Functions.verifyPubadCal("Smoke","TestMode");


	}

	@Test(priority =10,enabled = false)
	public void ThirdParty_beacons() throws Exception {
		System.out.println("==============================================");
		System.out.println("****** Thirdparty Beacon test cases Started");

		Functions.thirdPart_Beacon();

	}


	@Test(priority =11,enabled = true)
	public void Verify_PrerollAd() throws Exception {
		System.out.println("==============================================");
		System.out.println("****** Prerol-video ad test cases Started");
		//Functions.Setappinto_TestMode();
		Functions.delete_folder();
		Functions.clear_session();
		Functions.enternewAddress("48467");
		Functions.Verify_selectedPages("PreRollVideo");
		Functions.Navigate_extendedPages("PreRollVideo");
		Functions.downloadXMLFile();
		Functions.readXML();
		Functions.verifyPubadCal("Smoke","PreRollVideo");
		Functions.navBack_fromExtendedPage("PreRollVideo");
		//Functions.Verify_videoads();

	}

	//Verify extended Hurricane Central page ad presence Test case
	@Test(priority =12,enabled = true)
	public void extenedHurricane_ads() throws Exception {
		System.out.println("==============================================");
		System.out.println("****** Haricane test cases Started");	
		Functions.Verify_selectedPages("Hurricane(MainPage)");
		Functions.Navigate_extendedPages("Hurricane(MainPage)");
		Functions.Verify_Adpresenton_extendedPages("Hurricane(MainPage)");
		Functions.Navigate_Submodule("Hurricane(SubModule)");
		Functions.Verify_Adpresenton_extendedPages("Hurricane(SubModule)");
		Functions.navBackfrom_Submodule("Hurricane(SubModule)");


	}

	//Verify extended LSModule page ad presence Test case
	@Test(priority =13,enabled = true)
	public void extenedLSModule_ads() throws Exception {
		System.out.println("==============================================");
		System.out.println("****** LS Module test cases Started");	
		Functions.Verify_selectedPages("LSModule(Allergy)");
		Functions.Navigate_extendedPages("LSModule(Allergy)");
		Functions.Verify_Adpresenton_extendedPages("LSModule(Allergy)");
		Functions.Navigate_extendedPages("LSModule(ColdAndFlu)");
		Functions.Verify_Adpresenton_extendedPages("LSModule(ColdAndFlu)");


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
		Functions.startCharlesSession();
		Functions.charles_Stop();
		Functions.delete_screenshots();

		Functions.delete_IPA();
		Functions.app_download_from_hockeyapp();

		//		Functions.startCharlesSession();
		//		Functions.charles_Stop();
		//STR
		Functions.uninstallApp();
		Functions.installApp();
		Functions.Appium_Autostart();
		Functions.capabilities();
		Functions.launchtheApp();
//		Functions.uninstall_installApp();


	}

	@AfterTest
	public void afterTest() throws Exception {

		System.out.println("After Test");
		//		String[] command ={"/usr/bin/killall","-KILL","node"};  
		//		Runtime.getRuntime().exec(command);  

		driver.close();
	}

}
