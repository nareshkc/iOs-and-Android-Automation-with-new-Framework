package twc.Automation.HandleWithApp;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	public static void Kill_Launch_App(){

		Ad.closeApp();
		Ad.launchApp();
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
	
	public static void verify_adpresent_onextended_page(String excel_sheet_name) throws Exception{
		
		
		
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
		
		System.out.println("Searching for "+exceldata[1][Cap]+" module");
		
		int swipe = Integer.parseInt(exceldata[2][Cap]);
		
		for(int i=1;i<=swipe ;i++){
			Swipe();
			Thread.sleep(1000);
		}
		
		int MAX_SWIPES = 10;
		
		for (int i = 0; i < MAX_SWIPES; i++) {

			MobileElement module = null;

			try {
				
				WebDriverWait wait0 = new WebDriverWait(Ad, 10);
				wait0.until(ExpectedConditions.visibilityOf(Ad.findElementById(exceldata[5][Cap])));
				module = (MobileElement) Ad.findElementById(exceldata[5][Cap]);
				
				
			} catch (Exception e) {
				// System.out.println(e);
			}
		
			if (module != null && module.isDisplayed()) {
				
				System.out.println( exceldata[1][Cap] + " module is present on page");
				Ad.findElementById(exceldata[5][Cap]).click();
				
				if(excel_sheet_name.equals("Map")){
					
					MobileElement extendModule = (MobileElement) Ad.findElementById(exceldata[6][Cap]);
					if(extendModule.isDisplayed())
					{
						System.out.println("On Extended "+exceldata[1][Cap]+" page");
					}
				}
				else{
					
					String extendModuleText = Ad.findElementById(exceldata[6][Cap]).getText();
					System.out.println("Text : "+extendModuleText);
					if(extendModuleText.contains(exceldata[1][Cap]))
					{
						System.out.println("On Extended "+exceldata[1][Cap]+" page");
					}
				}
				

				MobileElement AdEle =(MobileElement) Ad.findElementById(exceldata[7][Cap]);
				
				WebDriverWait wait1 = new WebDriverWait(Ad, 10);
				wait1.until(ExpectedConditions.visibilityOf(AdEle));

				if (AdEle.isDisplayed()) {
					System.out.println("Ad is present on Extended page");
					Thread.sleep(2000);
					Ad.findElementByClassName(exceldata[10][Cap]).click();
					break;
				}
				

			} else {
				System.out.println(exceldata[1][Cap] + " module is not present and scrolling down");
				Swipe();
			}
		}
	}
	
	public static void CleanLaunch_launch(String excel_sheet_name) throws Exception
	{
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
		
		for(int i=1;i<=12 ;i++){
			Swipe();
			Thread.sleep(3000);
		}
		
		MobileElement skiSlopes = (MobileElement) Ad.findElementById(exceldata[1][Cap]);

		if (skiSlopes!=null && skiSlopes.isDisplayed()) {
				
				 System.out.println("Last module is present");
				 Swipe();
		} 
		else {
				System.out.println("Last module is NOT present,scrolling down");
				Swipe();
		}
	}
	
	public static void Change_to_Test_Mode(String excel_sheet_name) throws Exception{
		
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
		
		WebDriverWait wait = new WebDriverWait(Ad, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className(exceldata[2][Cap])));
		// Clicking on Menu Options
		MobileElement menu = (MobileElement) Ad.findElement(By.className(exceldata[2][Cap]));
		menu.click();
		Ad.findElementByName(exceldata[5][Cap]).click();
		MobileElement aboutThisAPP = (MobileElement) Ad.findElementByName(exceldata[6][Cap]);
		aboutThisAPP.click();
		
			for (int i=1; i<=8; i++){
				Ad.findElementById(exceldata[18][Cap]).click();
			}
				Ad.findElementByName(exceldata[19][Cap]).click();
				Ad.findElementByName(exceldata[20][Cap]).click();
				Ad.findElementByName(exceldata[16][Cap]).click();
				System.out.println("Changed to Test Mode");
				Thread.sleep(1000);
				Ad.closeApp();
				Ad.launchApp();
	}
	public static void verifyBBCallLocationFromListInTestMode(String excel_sheet_name) throws Exception{
		
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
			/* --- Start For Android Device --- */
			if(Cap == 2){
				String[][] addressdata = read_excel_data.exceldataread("AddressPage");
				
				WebDriverWait wait4 = new WebDriverWait(Ad, 10);
				wait4.until(ExpectedConditions.presenceOfElementLocated(By.id(addressdata[4][Cap])));
				
				//Root Location Element
				Ad.findElementById(addressdata[4][Cap]).click();
				
				WebDriverWait wait5 = new WebDriverWait(Ad, 20);
				wait5.until(ExpectedConditions.presenceOfElementLocated(By.id(addressdata[6][Cap])));
				
				//List Location Element
				@SuppressWarnings("unchecked")
				List<MobileElement> loclist = Ad.findElements(By.id(addressdata[6][Cap]));
				
				String firsteleXpath = addressdata[5][Cap];
				String[] parts = firsteleXpath.split("Count");
				/* --- Start For Loop For Location Click --- */
				for(int i=2;i<= loclist.size();i++){
					
					String element = null;
					
					try {
						
						element = parts[0]+i+parts[1];
								   
						MobileElement ele = (MobileElement) Ad.findElementByXPath(element);
						System.out.println("For This Location ====>"+ele.getText());
						String location = ele.getText();
						
						if(location.contains(exceldata[21][Cap])){
							System.out.println("Location "+location);
							Thread.sleep(8000);
							
							WebDriverWait wait12 = new WebDriverWait(Ad, 10);
							wait12.until(ExpectedConditions.presenceOfElementLocated(By.xpath(parts[0]+1+parts[1])));
							
							Ad.findElementByXPath(parts[0]+1+parts[1]).click();
							break;
						}
						else
						{
							
						WebDriverWait wait9 = new WebDriverWait(Ad, 20);
						wait9.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
						
						Ad.findElementByXPath(element).click();
						
						WebDriverWait wait10 = new WebDriverWait(Ad, 20);
						wait10.until(ExpectedConditions.presenceOfElementLocated(By.id(addressdata[4][Cap])));
						
						Ad.findElementById(addressdata[4][Cap]).click();
						}
					} catch (Exception e) {
						System.out.println(exceldata[21][Cap]+" is not found in the location list. So need to set the Location for Test Mode");
					}
				}
		}/* --- End For Android Device --- */
	}
	
	public static void verify_Vedio_Module_Click_On_Forecast_Video(String excel_sheet_name) throws Exception{
		
	System.out.println("Searching for Video module");
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	
	String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
	int swipe = Integer.parseInt(exceldata[2][Cap]);
	
	for(int i=1;i<=swipe ;i++){
		Swipe();
		Thread.sleep(1000);
	}
	
	int MAX_SWIPES = 10;
	
		for (int i = 0; i<MAX_SWIPES; i++) {
	
			MobileElement video = null;
	
			try {
				WebDriverWait wait0 = new WebDriverWait(Ad, 10);
				wait0.until(ExpectedConditions.visibilityOf(Ad.findElementById(exceldata[5][Cap])));
				video = (MobileElement) Ad.findElementById(exceldata[5][Cap]);
				
			} catch (Exception e) {
				// System.out.println("Exception message :: "+e);	
			}
	
			if(video!=null && video.isDisplayed())
			{  
				System.out.println("Video module is present ");
				Ad.findElementById(exceldata[5][Cap]).click();
				Thread.sleep(2000);
				Ad.findElementByClassName(exceldata[6][Cap]).click();
				break;
			}else
			{
	           System.out.println("Video module is NOT present and scrolling down");
			   Swipe();
			}
		}
	}
	
}