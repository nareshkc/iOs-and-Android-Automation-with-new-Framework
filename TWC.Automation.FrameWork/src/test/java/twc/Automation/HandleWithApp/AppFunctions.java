package twc.Automation.HandleWithApp;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import twc.Automation.Driver.Drivers;
import twc.Automation.General.DeviceStatus;
import twc.Automation.ReadDataFromFile.read_excel_data;

public class AppFunctions extends Drivers{
	
	static int startY;
	static int endY;
	public static void Pull_To_Refresh(String excel_sheet_name) throws Exception{
		
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
		System.out.println("Pull the screen to REFRESH is Start");
		
		WebDriverWait wait = new WebDriverWait(Ad, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(exceldata[1][Cap])));

		//Temperature element
		MobileElement temp = (MobileElement) Ad.findElementById(exceldata[1][Cap]);
		System.out.println("Temp : "+temp.getText());

		//HILO element
		MobileElement hilo = (MobileElement) Ad.findElementById(exceldata[18][Cap]);
		System.out.println("hilo : "+hilo.getText());
		TouchAction action = new TouchAction(Ad);
		action.longPress(temp).moveTo(hilo).release().perform();

		System.out.println("Pull the screen to REFRESH is done");
	}
	
	public static void Swipe(){
		Dimension dimensions = Ad.manage().window().getSize();
		Double startY1 = dimensions.getHeight() * 0.7;  
		startY = startY1.intValue();
		Double endY1 = (double) (dimensions.getHeight()/40);  //  dimensions.getHeight()  0.2;  == 512.0
		endY = endY1.intValue();
		Ad.swipe(0, startY, 0, endY,2000);
	}
	
	public static void Scroll_Upto_Feed_1(){
		
		WebDriverWait wait0 = new WebDriverWait(Ad, 10);
		wait0.until(ExpectedConditions.presenceOfElementLocated(By.id("com.weather.Weather:id/location_actionbar_name")));
		
		Swipe();
		Swipe();
		
		WebDriverWait wait = new WebDriverWait(Ad, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.weather.Weather:id/ad_view_holder")));
		
		MobileElement AdEle =(MobileElement) Ad.findElementById("com.weather.Weather:id/ad_view_holder");
		
		if(AdEle.isDisplayed())
		{
			System.out.println("Scrolling ");
		}
		
	}
	
	
	public static void verify_adpresent_onextendedHourly_page(String excel_sheet_name) throws Exception{
		
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
		int swipe = Integer.parseInt(exceldata[2][Cap]);
		System.out.println("===================================================");
		for(int i=1;i<=swipe ;i++){
			Swipe();
			Thread.sleep(1000);
		}
		
		WebDriverWait wait0 = new WebDriverWait(Ad, 10);
		wait0.until(ExpectedConditions.presenceOfElementLocated(By.id(exceldata[4][Cap])));
		
		MobileElement hourly = (MobileElement) Ad.findElementById(exceldata[4][Cap]);
		
		int MAX_SWIPES = 10;
		
		for (int i = 0; i < MAX_SWIPES; i++) {
			
			if (hourly != null && hourly.isDisplayed()) {
				System.out.println("Hourly module is displayed on the screen");
				Ad.findElementById(exceldata[4][Cap]).click();
				String extendhourly = Ad.findElementById("toolbar_title").getText(); //com.weather.Weather:id/toolbar_title
				System.out.println("Text : "+extendhourly);
				
				if(extendhourly.contains("Hourly"))
				{
					System.out.println("On Extended Hourly page");
				}
				
				WebDriverWait wait = new WebDriverWait(Ad, 10);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(exceldata[7][Cap])));
				
				MobileElement extendeHourlyAd =(MobileElement) Ad.findElementById(exceldata[7][Cap]);
				
				WebDriverWait wait1 = new WebDriverWait(Ad, 10);
	
				wait1.until(ExpectedConditions.visibilityOf(extendeHourlyAd));
				
				if (extendeHourlyAd.isDisplayed()) {
					
					System.out.println("Ad is present on Extended_Hourly page");
					Thread.sleep(2000);
					Ad.findElementByClassName(exceldata[5][Cap]).click();
					break;
				}
	
			} 
			else {
				System.out.println("Hourly module is not present and scrolling down");
				Swipe();
	
			}
		}
		System.out.println("===================================================");
	}
	
	public static void verify_adpresent_onextendedTenday_page(String excel_sheet_name) throws Exception {
		System.out.println("===================================================");
		System.out.println("Searching for Daily module");
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
		int swipe = Integer.parseInt(exceldata[2][Cap]);
		System.out.println("===================================================");
		for(int i=1;i<=swipe ;i++){
			Swipe();
			Thread.sleep(1000);
		}
		
		int MAX_SWIPES = 10;
		
		for (int i = 0; i < MAX_SWIPES; i++) {

			MobileElement tenday = null;

			try {
				WebDriverWait wait0 = new WebDriverWait(Ad, 10);
				wait0.until(ExpectedConditions.visibilityOf(Ad.findElementById(exceldata[5][Cap])));
				tenday = (MobileElement) Ad.findElementById(exceldata[5][Cap]);
			} catch (Exception e) {
				// System.out.println(e);
			}
		
			if (tenday != null && tenday.isDisplayed()) {
				
				System.out.println("Daily module is present and tap on 15Days button");
				Ad.findElementById(exceldata[5][Cap]).click();
				String extendDaily = Ad.findElementById(exceldata[6][Cap]).getText(); 
				
				System.out.println("Text : "+extendDaily);
				if(extendDaily.contains("Daily"))
				{
					System.out.println("On Extended Daily page");
				}

				MobileElement AdEle =(MobileElement) Ad.findElementById(exceldata[7][Cap]);
				
				WebDriverWait wait1 = new WebDriverWait(Ad, 10);
				wait1.until(ExpectedConditions.visibilityOf(AdEle));

				if (AdEle.isDisplayed()) {
					System.out.println("Ad is present on Extended_Daily_page");
					Thread.sleep(2000);
					Ad.findElementByClassName(exceldata[10][Cap]).click();
					break;
				}
				

			} else {
				System.out.println("Daily module is not present and scrolling down");
				Swipe();
			}
		}
		System.out.println("===================================================");
	}
	
	public static void verify_adpresent_onextendedMap_page(String excel_sheet_name) throws Exception {
		System.out.println("===================================================");
		System.out.println("Searching for Radar & Maps Module");
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
		int swipe = Integer.parseInt(exceldata[2][Cap]);
		System.out.println("===================================================");
		for(int i=1;i<=swipe ;i++){
			Swipe();
			Thread.sleep(1000);
		}
		
		int MAX_SWIPES = 10;
		
		for (int i = 0; i< MAX_SWIPES; i++) {
			
			MobileElement maps = null;

			try {
				
				WebDriverWait wait0 = new WebDriverWait(Ad, 10);
				wait0.until(ExpectedConditions.visibilityOf(Ad.findElementByName(exceldata[5][Cap])));
				maps = (MobileElement) Ad.findElementByName(exceldata[5][Cap]);

			} catch (Exception e) {
				// System.out.println(e);
			}
			
			if (maps != null && maps.isDisplayed()) {
				
				System.out.println("Radar and Maps module is present on Map Image");
				Ad.findElementByName(exceldata[5][Cap]).click();
				
				MobileElement extendMaps = (MobileElement) Ad.findElementById(exceldata[6][Cap]);
				
				if(extendMaps.isDisplayed())
				{
					System.out.println("On Extended Maps page");
				}

				MobileElement AdEle =(MobileElement) Ad.findElementById(exceldata[7][Cap]);
				
				WebDriverWait wait1 = new WebDriverWait(Ad, 10);
				wait1.until(ExpectedConditions.visibilityOf(AdEle));

				if (AdEle.isDisplayed()) {
					System.out.println("Ad is present on Extended Radar & Maps page");
					Thread.sleep(2000);
					Ad.findElementByClassName(exceldata[10][Cap]).click();
					break;
				}
				

			} else {
				System.out.println("Radar & Maps module is NOT present and scrolling down");
				Swipe();
			}
		}
		System.out.println("===================================================");
	}
	
	
	public static void verify_adpresent_onextendedNews_page(String excel_sheet_name) throws Exception
	{
		System.out.println("===================================================");
		System.out.println("Searching for News module");
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
		int swipe = Integer.parseInt(exceldata[2][Cap]);
		System.out.println("===================================================");
		for(int i=1;i<=swipe ;i++){
			Swipe();
			Thread.sleep(1000);
		}
		
		int MAX_SWIPES = 10;
		
		for (int i = 0; i<MAX_SWIPES; i++) {

			MobileElement news = null;

			try {
				WebDriverWait wait0 = new WebDriverWait(Ad, 10);
				wait0.until(ExpectedConditions.visibilityOf(Ad.findElementByName(exceldata[4][Cap])));
				news = (MobileElement) Ad.findElementByName(exceldata[4][Cap]);
				
			} catch (Exception e) {
				// System.out.println("Exception message :: "+e);	
			}

			if(news!=null && news.isDisplayed())
			{  
				System.out.println("News module is present and tap on News Image");
				Ad.findElementById(exceldata[5][Cap]).click();
				String extendNews = Ad.findElementById(exceldata[6][Cap]).getText(); //com.weather.Weather:id/toolbar_title
				System.out.println("Text : "+ extendNews);
					
				if(extendNews.contains("News"))
					{
						System.out.println("On Extended News page");
					}
				MobileElement AdEle =(MobileElement) Ad.findElementById(exceldata[7][Cap]);
					
				WebDriverWait wait1 = new WebDriverWait(Ad, 10);
				wait1.until(ExpectedConditions.visibilityOf(AdEle));
					if(AdEle.isDisplayed())
					{
						System.out.println("Ad is present on Extended News page");
						Thread.sleep(2000);
						Ad.findElementByClassName(exceldata[10][Cap]).click();
						break;
					}
					

			}else
			{
               System.out.println("News module is NOT present and scrolling down");
			   Swipe();
			}
		}
		System.out.println("===================================================");
	}
	
	public static void CleanLaunch_launch() throws Exception
	{
		
		System.out.println("===================================================");
		
		for(int i=1;i<=12 ;i++){
			Swipe();
			Thread.sleep(1000);
		}
		
			
		MobileElement skiSlopes = (MobileElement) Ad.findElementById("com.weather.Weather:id/ski_module_header");

		if (skiSlopes!=null && skiSlopes.isDisplayed()) {
				
				 System.out.println("Ski module is present");
				 Swipe();
		} 
		else {
				System.out.println("Ski module is NOT present,scrolling down");
				Swipe();
		}
		
		//System.out.println("===================================================");
	}
	
	public static void Change_to_Test_Mode() throws Exception{
		
		String setting_menu = "android.widget.ImageButton";
		
		WebDriverWait wait = new WebDriverWait(Ad, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className(setting_menu)));
		// Clicking on Menu Options
		MobileElement menu = (MobileElement) Ad.findElement(By.className(setting_menu));
		menu.click();
		Ad.findElementByName("Settings").click();
		MobileElement aboutThisAPP = (MobileElement) Ad.findElementByName("About this app");
		aboutThisAPP.click();
		
			for (int i=1; i<=8; i++){
				Ad.findElementById("com.weather.Weather:id/about_version").click();
			}
				Ad.findElementByName("TEST MODE SETTINGS").click();
				Ad.findElementByName("Ads").click();
				Ad.findElementByName("Test").click();
				System.out.println("Changed to Test Mode");
				Thread.sleep(1000);
				Ad.closeApp();
				Ad.launchApp();
		
		verifySavedTestLocationList();
	}
	public static void verifySavedTestLocationList() throws Exception{
		
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
			/* --- Start For Android Device --- */
			if(Cap == 2){
			String[][] addressdata = read_excel_data.exceldataread("CheckAddress");
			
			WebDriverWait wait4 = new WebDriverWait(Ad, 10);
			wait4.until(ExpectedConditions.presenceOfElementLocated(By.id(addressdata[4][Cap])));
			
			//Root Location Element
			String location = Ad.findElementById(addressdata[4][Cap]).getText();
			if(location.contains("New York")){
				System.out.println("Location "+location);
			}
			else{
				System.out.println("Spoof Need to Change to New York Location");
				Assert.fail("Spoof Need to Change to New York Location");
			}
			
		}/* --- End For Android Device --- */
		
	}
}
