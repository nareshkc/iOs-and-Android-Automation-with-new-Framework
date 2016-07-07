package com.Genaral;

import java.util.NoSuchElementException;







import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;

public class Driver extends PropertyFile {

	@SuppressWarnings("rawtypes")
	protected static AppiumDriver Ad ;
	public static WebDriver driver = null;
	public static WebDriver driver_ipa =null;
	protected static ExtentReports reporter;
	//protected static ExtentTest logger;
	public static String Excel_Path =null;
	public static ExtentReports getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	
//	public void Dr()
//	{
//		driver=null;
//		Ad=null;
//		
//	}
//	
//	public boolean isElemenetPresent(By by) throws Exception
//	{
//		
//		try{
//			driver.findElement(by);
//			Ad.findElement(by);
//		return true;
//		}catch (NoSuchElementException e){
//		return false;
//		}
	//}
}
