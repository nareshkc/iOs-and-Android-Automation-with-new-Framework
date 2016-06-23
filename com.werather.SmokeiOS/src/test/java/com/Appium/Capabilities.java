package com.Appium;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.TouchAction;
import io.appium.java_client.TouchShortcuts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.jetty.html.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.Genaral.Driver;


import org.openqa.selenium.remote.RemoteWebDriver;

public class Capabilities extends Driver  {

//	public void startAppium() throws Exception{
//		Process p = Runtime.getRuntime().exec("cmd /c echo off & FOR /F \"usebackq tokens=5\" %a in (`netstat -nao ^| findstr /R /C:\"4723\"`) do (FOR /F \"usebackq\" %b in (`TASKLIST /FI \"PID eq %a\" ^| findstr /I node.exe`) do taskkill /F /PID %a)");
//		}
//	
	

	public void dcap() throws Exception {

		// MobileDriver driver;

		// Desired Capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		System.out.println("Started");
		// capabilities.setCapability(CapabilityType.BROWSER_NAME,"Chrome");
		capabilities.setCapability("appium-version", "1.0");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", "8.4");
		capabilities.setCapability("deviceName", "iPhone");
		//capabilities.setCapability("app", "/Users/aparna/Downloads/TheWeather.app");


		capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
		capabilities.setCapability(CapabilityType.VERSION, "8.4");
		capabilities.setCapability(CapabilityType.PLATFORM, "Mac");
		capabilities.setCapability("device", "iPhone");
		capabilities.setCapability("udid","707cc1d9c294b37fe5e70b7584163a100ae6166f");
		//capabilities.setCapability("udid","c87e18b1235f1480f32f2725762260fb893492b9");
		capabilities.setCapability("app", "/Users/aparna/Documents/Naresh/com.Weather.TWC/Build/TheWeather.ipa");
		capabilities.setCapability("appPackage", "com.weather.Weather");
		capabilities.setCapability("appActivity", "com.weather.Weather.app.SplashScreenActivity");
		capabilities.setCapability("newCommandTimeout", 100000);

		//Driver found
		System.out.println("Found the Device/Simulator");
        
		//Communicate with Remote web driver
		//WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Ad = new IOSDriver (new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("Verify for networkapp launched");
		Ad.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


//		System.out.println("Verify for time wait launched");
//
//		
//		String originalContext = Ad.getContext();
//		Ad.context("NATIVE_APP");
//		
//		//scroll
//		MobileElement Screen = (MobileElement) Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[1]");
//		//is number of cells/rows inside table correct
//		List<MobileElement> pages = Screen.findElementsByClassName("UIACollectionView");
//
//		System.out.println("page Size is "+ pages.size());
//
//
//		JavascriptExecutor js = (JavascriptExecutor) Ad ;
//		HashMap<String, String> scrollObject = new HashMap<String, String>();
//		scrollObject.put("direction", "down");
//		for(int i=1;i<=2;i++)
//		{
//			
//			js.executeScript("mobile: scroll", scrollObject);
//
//		}
//
//
//		//click on Flex menu
//		Ad.findElement(By.name("menu")).click();
//	
//		Ad.findElementByName("📡  Network History").click();
//		//click on search and search for pubad call
//		Ad.findElementByClassName("UIASearchBar").sendKeys("feed_1");
//		Ad.findElement(By.name("Search")).click();
//		
//
//		//find total pub ad cells
//		//first view in UICatalog is a table
//		IOSElement table = (IOSElement) Ad.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIATableView[1]"));
//
//		//is number of cells/rows inside table correct
//		List<MobileElement> row = table.findElementsByClassName("UIATableCell");
//
//		System.out.println("Row Size is "+ row.size());
//
//
//		//		for (int i=1;i<= row.size();i++)
//		//		{
//		//System.out.println("i valuw is :"+i);
//		String adcalname = Ad.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).getText();
//		String Cust_param;
//		if(adcalname.contains("adx?iu"))
//		{
//			Ad.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
//			Cust_param = Ad.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIATableView[1]/UIATableCell[21]/UIAStaticText[1]")).getText();
//			System.out.println("Cust_Param adx Value is :" + Cust_param);
//		}else
//		{
//			System.out.println("adcalname :"+adcalname);
//			Ad.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
//			Cust_param = Ad.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIATableView[1]/UIATableCell[27]//UIAStaticText[1]")).getText();
//			System.out.println("Cust_Param ads Value is :" + Cust_param);
//		}
//
//
//
//		String Content = Cust_param;
//
//		//Read Excel
//		String[][] data = new String[10][10];
//		ExcelData er = new ExcelData();
//		data = er.excelread();
//
//		//First Test Cases
//
//		for(int testcase=1;testcase<=13;testcase++)
//		{
//
//			String param = data[testcase][4].toString();
//			System.out.println("The param is :"+ param);
//			// Loop to read all lines one by one from file and print It.
//
//			// return mbrLastName;// Getting ord value from Cust_param
//			String entriesTxt = Content;
//			String[] splitColon = entriesTxt.split(":");
//			//System.out.println("firt word:" + splitColon[0]);
//			String[] aEntriesText = splitColon[1].split("&");
//			String totalEntriesText = aEntriesText[aEntriesText.length - 1];
//
//			// return Integer.parseInt(totalEntriesText);
//			int totalLength = aEntriesText.length;
//           
//			
//			
//			
//			for (int i = 1; i <= totalLength; i++) {
//				String FindText = aEntriesText[aEntriesText.length - i];
//				// System.out.println("Lenth number is : "+(totalLength-i)+ ":"
//				// + FindText);
//				//System.out.println("param value::"+param);
//				String[] Value = FindText.split("=");
//				//System.out.println("zip value value::"+Value[0]);
//                
//				
//				if (param.trim().equals(Value[0].trim())) {
//					String ExactValue = Value[Value.length - 1];
//					System.out.println("The value is :" + ExactValue);
//
//					WriteResultintoExcel wResult = new WriteResultintoExcel();
//					
//					//String Compare
//					
//					if(data[testcase][4].contains("wind"))
//					{
//						String WindText=Ad.findElementByName("ENE 8 MPH").getText();
//						String windarray[] = WindText.split(" ",3);
//						System.out.println("Wind value is ::"+ windarray[1]);
//						
//						
//					}
//                    
//					
//						//If Value Size is NA
//						if(data[testcase][6].contains("NA"))
//						{if (data[testcase][8].contains(ExactValue.toString())&&ExactValue!="") {
//							System.out.println("NO Values found for "+param);
//							wResult.enterResult("SMOKE", "Fail", ExactValue, testcase, 9, 10);
//						}else if(data[testcase][7].contains(ExactValue)||data[testcase][5].contains("Fixed")){
//							System.out.println(param+" vales is :" + FindText.contains(data[testcase][4].toString()));
//							System.out.println(FindText);
//							wResult.enterResult("SMOKE", "Pass", ExactValue, testcase, 9, 10);
//						}
//
//						break;
//						}
//
//
//						//Verify Value Size is not NA data
//						if(!data[testcase][6].contains("NA")){
//							System.out.println("Exact Value is "+ ExactValue + " Data in Excel " + data[testcase][8]+" Size is : "+ExactValue.length());
//							if (data[testcase][8].contains(ExactValue)&&(ExactValue.length() !=data[testcase][6].length())&&ExactValue!="") {
//								System.out.println("NO Values found for "+param);
//								wResult.enterResult("SMOKE", "Fail", ExactValue, testcase, 9, 10);
//
//							} else
//
//								if(data[testcase][7].contains(ExactValue)||data[testcase][5].contains("Not Fixed")){
//									System.out.println(data[testcase][4]+" vales is :" + FindText.contains(param));
//									System.out.println(FindText);
//									wResult.enterResult("SMOKE", "Pass", ExactValue, testcase, 9, 10);
//								}
//						}
//
//					
//				}
//			}
//		}	
	}
}


