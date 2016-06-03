package twc.Automation.SmokeTestCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import twc.Automation.Driver.Drivers;
import twc.Automation.General.cxtgFunctions;
import twc.Automation.General.loginModule;
import twc.Automation.HandleWithApp.AppFunctions;
import twc.Automation.HandleWithAppium.AppiumFunctions;
import twc.Automation.HandleWithCharles.CharlesFunctions;
import twc.Automation.General.Functions;

public class somkeTestCases extends Drivers{
	

	/* Pull To Refresh Test Case */
	@Test(priority=0)
	public void PulltoRefresh_Test_Case_Using_Charles() throws Exception{
		System.out.println("================= Pull To Refresh Test Case Started =========================");
		CharlesFunctions.openCharlesBrowser();
		AppiumFunctions.LaunchApp();
		AppFunctions.Pull_To_Refresh("Pulltorefresh");
		CharlesFunctions.StopExportSessionXMLFile();
		Functions.bb_call_validation("Pulltorefresh");
		System.out.println("================= Pull To Refresh Test Case  End =========================");
	}
	/* Clean Launch  Test Case */
	@Test(priority=1)
	public void Clean_App_Launch() throws Exception{
		System.out.println("================= Clean App Launch Test Case Started =========================");
		AppiumFunctions.UnInstallApp();
		CharlesFunctions.openCharlesBrowser();
		AppiumFunctions.LaunchApp();
		AppFunctions.CleanLaunch_launch("General");
		CharlesFunctions.StopExportSessionXMLFile();
		Functions.clean_App_Launch("CleanLaunch");
		System.out.println("================= Clean App Launch Test Case End =========================");
	}
	/* CXTG  Values Test Case */
	@Test(priority=2)
	public void Verify_CXTG_Values() throws Exception{
		System.out.println("================= CXTG Values Test Case Started =========================");
		AppiumFunctions.UnInstallApp();
		CharlesFunctions.openCharlesBrowser();
		AppiumFunctions.LaunchApp();
		loginModule.login();
		AppFunctions.Kill_Launch_App();
		cxtgFunctions.verifySavedAddressList();
		CharlesFunctions.StopExportSessionXMLFile();
		cxtgFunctions.validateCXTGResultsFromPubadAndTriggerCalls("WFXTrigger");
		System.out.println("================= CXTG Values Test Case End =========================");
		
	}
	/* Factual Call Test Case */
	@Test(priority=3)
	public void Factual_Test_Case_Using_Charles() throws Exception{
		System.out.println("================= Factual Call Test Case Started =========================");
//		CharlesFunctions.openCharlesBrowser();
//		AppiumFunctions.LaunchApp();
//		CharlesFunctions.StopExportSessionXMLFile();
		Functions.validate_API_Call_With_PubAds_Call("LocationWFX");
		System.out.println("================= Factual Call Test Case End =========================");
	}
	/* Lotame Call Test Case */
	@Test(priority=4)
	public void Lotame_Test_Case_Using_Charles() throws Exception{
		System.out.println("================= Lotame Call Test Case Started =========================");
//		CharlesFunctions.openCharlesBrowser();
//		AppiumFunctions.LaunchApp();
//		CharlesFunctions.StopExportSessionXMLFile();
		Functions.validate_API_Call_With_PubAds_Call("Lotame");
		System.out.println("================= Lotame Call Test Case End =========================");
	}
	/* Hourly Module Extended Page Ad Test Case */
	@Test(priority=5)
	public void Verify_Ad_Present_On_HourlyExtended_page() throws Exception {
		System.out.println("================= Hourly Module Test Case Started =========================");
		AppiumFunctions.LaunchApp();
		AppFunctions.verify_adpresent_onextendedHourly_page("Hourly");
		System.out.println("================= Hourly Module Test Case End =========================");
	}
	/* Daily Module Extended Page Ad Test Case */
	@Test(priority=6)
	public void Verify_Ad_Present_On_DailyExtended_page() throws Exception {
		System.out.println("================= Daily Module Test Case Started =========================");
		//AppiumFunctions.LaunchApp();
		AppFunctions.verify_adpresent_onextendedTenday_page("Daily");
		System.out.println("================= Daily Module Test Case End =========================");
	}
	/* RADAR And MAPS Module Extended Page Ad Test Case */
	@Test(priority=7)
	public void Verify_Ad_Present_On_MapsExtended_page() throws Exception {
		System.out.println("================= MAP Module Test Case Started =========================");
		//AppiumFunctions.LaunchApp();
		AppFunctions.verify_adpresent_onextendedMap_page("Map");
		System.out.println("================= MAP Module Test Case End =========================");
	}
	/* News Module Extended Page Ad Test Case */
	@Test(priority=8)
	public void Verify_Ad_Present_On_NewsExtended_page() throws Exception {
		System.out.println("================= NEWS Module Test Case Started =========================");
		//AppiumFunctions.LaunchApp();
		AppFunctions.verify_adpresent_onextendedNews_page("News");
		System.out.println("================= NEWS Module Test Case End =========================");
	}

	/* Test Mode BB Call Test Case */
	@Test(priority=9)
	public void Verify_TestMode_BBCall() throws Exception{
		System.out.println("================= Verify BB Call In Test Mode Test Case Started =========================");
		CharlesFunctions.openCharlesBrowser();
		AppiumFunctions.LaunchApp();
		loginModule.login();
		AppFunctions.Change_to_Test_Mode("TestMode");
		AppFunctions.verifyBBCallLocationFromListInTestMode("TestMode");
		CharlesFunctions.StopExportSessionXMLFile();
		Functions.bb_call_validation("TestMode");
		System.out.println("================= Verify BB Call In Test Mode Test Case End =========================");
	}
	
	@Test(priority=10)
	public void Verify_ThirdParty_Beacons_Call() throws Exception{
		System.out.println("================= ThirdpartyBecon Test Case Started =========================");
		Functions.thirdParty_beacons_validation("ThirdpartyBecon");
		System.out.println("================= ThirdpartyBecon Test Case End =========================");
	}
	
	@Test(priority=11)
	public void Verify_PreLoad_PubAd_Call() throws Exception{
		System.out.println("================= PreLoad BB Call Test Case Started =========================");
		CharlesFunctions.openCharlesBrowser();
		AppiumFunctions.LaunchApp();
		AppFunctions.verify_Vedio_Module_Click_On_Forecast_Video("PreRollVideo");
		CharlesFunctions.StopExportSessionXMLFile();
		Functions.bb_call_validation("PreRollVideo");
		System.out.println("================= PreLoad BB Call Test Case End =========================");
	}
	
	@BeforeTest
	public void Before_Test() throws Exception{
		CharlesFunctions.charlesOpen();
		AppiumFunctions.UnInstallApp();
		
	}
}
