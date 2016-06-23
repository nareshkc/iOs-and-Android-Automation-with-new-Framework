package com.Appium;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.Genaral.Driver;
import com.Genaral.PropertyFile;
import com.Genaral.readExcelValues;




public class appiumnew extends Driver{

	static DesiredCapabilities capabilities = new DesiredCapabilities();
	
	
	public static void Capabilities() throws Exception {

//		//Auto start Appium
//		Start_Stop_AppiumServer appiumStart = new Start_Stop_AppiumServer();
//		System.out.println("Stopping the appium server");
//		appiumStart.stopAppiumServer();
//		System.out.println("Appium server is stopped");
//		//Thread.sleep(10000);
//		System.out.println("Starting the appium server");
//		appiumStart.startAppiumServer();
//		System.out.println("Appium server is started and running");
		
		//Read Device Platform
		readExcelValues.excelValues("Smoke","Device");
        int Cap =1;
        
        if(readExcelValues.data[1][1].equals("Android")){
        	Cap = Cap+1;
        }else
        {
        	Cap=Cap;
        }
        readExcelValues.excelValues("Smoke","Capabilities");

		//Capabilities for IOS and Android Based on Selected on Device Selection
		
		
		capabilities.setCapability(readExcelValues.data[1][0], readExcelValues.data[1][Cap]);
		capabilities.setCapability(readExcelValues.data[2][0], readExcelValues.data[2][Cap]);
		capabilities.setCapability(readExcelValues.data[3][0], readExcelValues.data[3][Cap]);
		capabilities.setCapability(readExcelValues.data[5][0], readExcelValues.data[5][Cap]);
		capabilities.setCapability(readExcelValues.data[6][0], readExcelValues.data[6][Cap]);
		capabilities.setCapability(readExcelValues.data[7][0], "="+readExcelValues.data[7][Cap]);
		capabilities.setCapability(readExcelValues.data[8][0], readExcelValues.data[8][Cap]);
		capabilities.setCapability(readExcelValues.data[9][0], readExcelValues.data[9][Cap]);
		//capabilities.setCapability("udid","707cc1d9c294b37fe5e70b7584163a100ae6166f");
		capabilities.setCapability(readExcelValues.data[10][0], readExcelValues.data[10][Cap]);
		capabilities.setCapability(readExcelValues.data[12][0], readExcelValues.data[12][Cap]);
		//capabilities.setCapability("appActivity", "com.weather.Weather.app.SplashScreenActivity");
		capabilities.setCapability(readExcelValues.data[13][0], readExcelValues.data[13][Cap]);
		capabilities.setCapability(readExcelValues.data[14][0], readExcelValues.data[14][Cap]);
		
		System.out.println("Reading capabilities done");
	
	}

	public static void LaunchTheApp() throws Exception{	
		//Wait time for Execution of node.js
		Thread.sleep(50000);
		//launch the app
		Ad = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Ad.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("App Launched Successfully");
}
	



}
