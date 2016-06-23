package com.Functions;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import atu.testng.reports.ATUReports;

import com.Appium.Start_Stop_AppiumServer;
import com.Custom_Parameter.Cust_param;
import com.Genaral.Driver;
import com.Genaral.readExcelValues;
import com.relevantcodes.extentreports.LogStatus;
import com.weather.excel.ExcelData;
import com.weather.excel.WriteResultintoExcel;
import com.weather.excel.Write_result;


public class Functions extends Driver{

	static DesiredCapabilities capabilities = new DesiredCapabilities();
	static int Cap =1;
	static String UserStatus = null;
	static List<MobileElement> Addresseslist =null;
	static MobileElement TempEle =null;
	static MobileElement Settings =null;
	static MobileElement SelectAddress =null;
	public static StringBuffer sb = new StringBuffer("");
	public static String req =null;
	static String pubreq=null; 
	public static String pubreq1 = null;
	public static String adreq1 = null;
	static List<String> container=null;
	static String SecondParamValue =null;
	public static String firstParamValue =null;
	public static String AdParams =null;
	static String zipCode = null;
	static String[] splitPubvalues = null;
	static String pubadcal=null;
	static String adcal=null;
	public static String seg = null;
	public static String Xpth =null;
	public static String VerifypubadValues=null;
	//	public static ArrayList<String> firstParamValue = new ArrayList<String>();
	public static ArrayList<String> pubads = new ArrayList<String>();
	public static ArrayList<String> ads = new ArrayList<String>();
	public static ArrayList<String> pubadvalues = new ArrayList<String>();
	public static ArrayList<String> advalues = new ArrayList<String>();
	public static ArrayList<String> pubvalues = new ArrayList<String>();
	public static ArrayList<String> fgeolist = new ArrayList<String>();
	public static ArrayList<String> faudlist = new ArrayList<String>();
	public static ArrayList<String> cxtgcontainer = new ArrayList<String>();
	public static ArrayList<String> wfxtgcontainer = new ArrayList<String>();
	//Appium Start
	public static void startAppiumServer() throws Exception { 
		readExcelValues.excelValues("Smoke","Appium");
		CommandLine command = new CommandLine(readExcelValues.data[1][Cap]);
		command.addArgument(readExcelValues.data[2][Cap], false);
		command.addArgument("--address", false);
		command.addArgument(readExcelValues.data[3][Cap]);
		command.addArgument("--port", false);
		command.addArgument(readExcelValues.data[4][Cap]);
		command.addArgument("--no-reset", true);
		command.addArgument("--log-level", true);
		command.addArgument("error");
		//		command.addArgument("--log");
		//		command.addArgument("/Users/aparna/Documents/sys11.log");


		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		executor.execute(command, resultHandler);
		Thread.sleep(20000);


	} 

	//Stop Appium Server
	public static  void stopAppiumServer() throws IOException {  
		String[] command ={"/usr/bin/killall","-KILL","node"};  
		Runtime.getRuntime().exec(command);  
		//System.out.println("Appium server stop");  
	} 

	//Appium Autostart
	public static void Appium_Autostart() throws IOException, Exception{
		//Auto start Appium
		Start_Stop_AppiumServer appiumStart = new Start_Stop_AppiumServer();
		System.out.println("Stopping the appium server");
		stopAppiumServer();
		System.out.println("Appium server is stopped");
		//Thread.sleep(10000);
		System.out.println("Starting the appium server");
		startAppiumServer();
		System.out.println("Appium server is started and running");
	}

	//Decide connected device
	public static void capabilities() throws Exception {
		//Read Device Platform
		readExcelValues.excelValues("Smoke","Device");

		if(readExcelValues.data[1][1].equals("Android")){
			Cap = Cap+1;
		}else
		{
			Cap=Cap;
		}


	}
	//Scroll Down
	public static void scrolldown() throws InterruptedException{
		//Thread.sleep(1000);
		//Scroll JS
		JavascriptExecutor js = (JavascriptExecutor) Ad ;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);


	}
	//App Kill Relaunch
	public static void Kill_realaunch() throws Exception
	{

		//Close the app
		Ad.closeApp();
		Thread.sleep(1000);
		System.out.println("App closed successfully");

		//Relaunch the app
		Ad.launchApp();
		System.out.println("App launched successfully");
		Thread.sleep(2000);
	}
	//Assert Equlist
	public static boolean equalLists(List<String> segmentJsonArray, List<String> container2) {

		if (segmentJsonArray == null && container2 == null) {
			return true;
		}

		if ((segmentJsonArray == null && container2 != null) || segmentJsonArray != null && container2 == null
				|| segmentJsonArray.size() != container2.size()) {
			System.out.println("Pubad request triggers and trigger call triggers are not matched");
			return false;

		}

		return segmentJsonArray.equals(container2);

	}

	//launch the app
	public static void launchtheApp() throws Exception{	

		readExcelValues.excelValues("Smoke","Capabilities");
		DesiredCapabilities capabilities = new DesiredCapabilities();

		//Capabilities for IOS and Android Based on Selected on Device Selection
		capabilities.setCapability(readExcelValues.data[1][0], readExcelValues.data[1][Cap]);
		capabilities.setCapability(readExcelValues.data[2][0], readExcelValues.data[2][Cap]);
		capabilities.setCapability(readExcelValues.data[3][0], readExcelValues.data[3][Cap]);
		capabilities.setCapability(readExcelValues.data[5][0], readExcelValues.data[5][Cap]);
		capabilities.setCapability(readExcelValues.data[6][0], readExcelValues.data[6][Cap]);
		capabilities.setCapability(readExcelValues.data[7][0], "="+readExcelValues.data[7][Cap]);
		capabilities.setCapability(readExcelValues.data[8][0], readExcelValues.data[8][Cap]);
		capabilities.setCapability(readExcelValues.data[9][0], readExcelValues.data[9][Cap]);
		capabilities.setCapability(readExcelValues.data[10][0], readExcelValues.data[10][Cap]);
		capabilities.setCapability(readExcelValues.data[12][0], readExcelValues.data[12][Cap]);
		capabilities.setCapability(readExcelValues.data[13][0], readExcelValues.data[13][Cap]);
		capabilities.setCapability(readExcelValues.data[14][0], readExcelValues.data[14][Cap]);
		capabilities.setCapability(readExcelValues.data[16][0],readExcelValues.data[16][Cap]);
		capabilities.setCapability("--session-override",true);
		System.out.println("Reading capabilities done");
		//Wait time for Execution of node.js
		Thread.sleep(70000);

		Ad = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Ad.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		try{
			Ad.findElementByName("close_button").click();
		}catch(Exception e){

		}
	}

	//Verifi User Loggedin
	public static void verifyuserloggedIn() throws Exception{	
		readExcelValues.excelValues("Smoke","Login");

		//		Settings =(MobileElement) Ad.findElementByXPath(readExcelValues.data[1][Cap]);
		//		Settings.click();
		UserStatus= Ad.findElementByXPath(readExcelValues.data[2][Cap]).getText();

		if(UserStatus.equals(readExcelValues.data[9][Cap])){
			System.out.println("User Already LoggedIn");
		}else{
			System.out.println("User not LoggedIn");
		}


	}

	//Login
	public static void logIn() throws Exception{	

		readExcelValues.excelValues("Smoke","Login");

		Settings =(MobileElement) Ad.findElementByXPath(readExcelValues.data[1][Cap]);
		//		Settings.click();
		UserStatus= Ad.findElementByXPath(readExcelValues.data[2][Cap]).getText();
		//if User already logged in navigate to cc page
		if(UserStatus.equals(readExcelValues.data[9][Cap])){
			System.out.println("User Already LoggedIn");
			Thread.sleep(3000);
			Settings.click();
			System.out.println("User on CC page");
		}else{
			System.out.println("User not LoggedIn");
			//If user not logged in enter username and password and try to loggin
			Ad.findElementByName(readExcelValues.data[7][Cap]).click();
			Thread.sleep(1000);
			Ad.findElementByXPath(readExcelValues.data[3][Cap]).clear();
			Ad.findElementByXPath(readExcelValues.data[3][Cap]).sendKeys(readExcelValues.data[4][Cap]);
			Ad.findElementByXPath(readExcelValues.data[5][Cap]).clear();
			Ad.findElementByXPath(readExcelValues.data[5][Cap]).sendKeys(readExcelValues.data[6][Cap]);
			Ad.findElementByName(readExcelValues.data[8][Cap]).click();
			Ad.findElementByName(readExcelValues.data[7][Cap]).click();
			System.out.println("User logged in successfully");
			Thread.sleep(7000);

			WebDriverWait wait = new WebDriverWait(Ad,10);
			wait.until(ExpectedConditions.visibilityOf(Settings));


			Settings.click();

			Thread.sleep(3000);
			System.out.println("User on CC page");
		}
		Thread.sleep(3000);
	}


	//Kill The app
	public void killtheApp() throws Exception
	{
		Ad.closeApp();
		Thread.sleep(1000);
		System.out.println("App closed successfully");
	}

	//Relaunch the app
	public void relaunchtheApp() throws Exception
	{
		Ad.launchApp();
		System.out.println("App launched successfully");
	}

	public static void uninstall_installApp() throws Exception{
		readExcelValues.excelValues("Smoke","Paths");

		if(Ad.isAppInstalled(readExcelValues.data[10][Cap])){
			System.out.println("App installed in the device and trying to uninstall");
			Ad.removeApp(readExcelValues.data[10][Cap]);
			System.out.println("App was uninstalled succesfully and trying to install");
			Ad.installApp(readExcelValues.data[14][Cap]);
			System.out.println("App was installed Successfully");
		}else{
			System.out.println("App was not ther in the device, installing the appa");
			Ad.installApp(readExcelValues.data[14][Cap]);
			System.out.println("App was installed Successfully");
			Thread.sleep(5000);
			Ad.launchApp();
		}
	}
	//Uninstall the app
	public static void uninstallApp() throws Exception{
		readExcelValues.excelValues("Smoke","Paths");
		Thread.sleep(3000);
		String line = "";
		String allLine = "";
		String[] str1 ={"/bin/bash", "-c", readExcelValues.data[13][Cap]+" " +readExcelValues.data[1][Cap]};
		Process p1 = Runtime.getRuntime().exec(str1);
		Thread.sleep(2000);
		BufferedReader r = new BufferedReader(new FileReader(readExcelValues.data[1][Cap]));
		Thread.sleep(3000);
		while((line=r.readLine()) != null)
		{
			//System.out.println("Sys data is ::"+line);
			if(line.contains(readExcelValues.data[10][Cap])){
				String[] str2 ={"/bin/bash", "-c", readExcelValues.data[11][Cap] +readExcelValues.data[10][Cap]};
				Process p2 = Runtime.getRuntime().exec(str2);
				System.out.println("App uninstalled in the device and trying to install the app");
				break;
			}


		}
		//System.out.println("App not installed in the device and trying to install the app");
	}

	//install the app
	public static void installApp() throws Exception{
		readExcelValues.excelValues("Smoke","Paths");
		String[] str ={"/bin/bash", "-c", readExcelValues.data[12][Cap]+" " +readExcelValues.data[14][Cap]};
		Process p = Runtime.getRuntime().exec(str);

		Thread.sleep(50000);
		System.out.println("App was installed in the device successfully");
	}

	//navigatetoSettingPage or manage locations
	public static void navigatetoSettingPage() throws Exception{
		readExcelValues.excelValues("Smoke","Login");
		MobileElement Settings =null;
		Settings =(MobileElement) Ad.findElementByXPath(readExcelValues.data[1][Cap]);
		Settings.click();
	}

	//navigatetoSettingPage or manage locations
	public static void navigatetoAddressPage() throws Exception{
		readExcelValues.excelValues("Smoke","AddressPage");
		MobileElement addressPage =null;
		addressPage =(MobileElement) Ad.findElementByName(readExcelValues.data[1][Cap]);
		addressPage.click();
		MobileElement Address =(MobileElement) Ad.findElementByXPath(readExcelValues.data[2][Cap]);
		Addresseslist = Address.findElementsByClassName(readExcelValues.data[3][Cap]);


	}

	//Add New addresses
	public static void addnewAddress(String zip) throws Exception{
		readExcelValues.excelValues("Smoke","AddressPage");
		MobileElement Address =(MobileElement) Ad.findElementByXPath(readExcelValues.data[2][Cap]);
		Addresseslist = Address.findElementsByClassName(readExcelValues.data[3][Cap]);
		if(Addresseslist.size()<=10){
			TempEle=(MobileElement) Ad.findElementByClassName("UIASearchBar");
			TempEle.click();
			TempEle.sendKeys(zip);
			//down Keyboad
			Thread.sleep(2000);
			Ad.findElementByName("Search").click();
			//select first name in the list
			Ad.findElementByXPath(readExcelValues.data[5][Cap]).click();
			Thread.sleep(2000); 

		}else
		{
			System.out.println("Saved address limite excied");
		}

	}

	//Enter New addresses
	public static void enternewAddress(String zip) throws Exception{
		readExcelValues.excelValues("Smoke","AddressPage");
		try{
			TempEle=(MobileElement) Ad.findElementByClassName(readExcelValues.data[9][Cap]);
			if(!TempEle.isDisplayed()){
				Ad.findElementByName(readExcelValues.data[1][Cap]).click();
				TempEle=(MobileElement) Ad.findElementByClassName(readExcelValues.data[9][Cap]);
			}else{
				System.out.println("User on Address select page");	
			}
		}catch(Exception e){
			Ad.findElementByName(readExcelValues.data[1][Cap]).click();
			TempEle=(MobileElement) Ad.findElementByClassName(readExcelValues.data[9][Cap]);

		}
		TempEle.click();
		TempEle.sendKeys(zip);
		//down Keyboad
		Thread.sleep(2000);
		Ad.findElementByName("Search").click();
		//select first name in the list
		Ad.findElementByXPath(readExcelValues.data[8][Cap]).click();
		Thread.sleep(2000); 

	}
	//Verify saved address list
	public static void verifysavedAddresses() throws Exception{
		readExcelValues.excelValues("Smoke","AddressPage");
		int savedlistcount =Addresseslist.size()-2;
		System.out.println("Saved address list count is :"+savedlistcount);
		if(Addresseslist.size()>2){
			for(int addresslist=1;addresslist<=Addresseslist.size()-2;addresslist++){
				int Count =addresslist+2; 
				//int TempEle = addresslist+1;
				String xyz = readExcelValues.data[4][Cap];
				//System.out.println("String xyz is "+xyz);
				String[] AddrestSplit =xyz.split("Count");
				xyz=AddrestSplit[0]+Count+AddrestSplit[1];

				SelectAddress = (MobileElement) Ad.findElementByXPath(xyz);
				//readExcelValues.data[4][Cap]);
				String SavedAddress=SelectAddress.getText();
				//System.out.println("Saved Address is  - "+SavedAddress);


			}	
		}


	}

	//Select multiple saved address one by one using addresscount value
	public static void selectsavedAddresses(int addresscount) throws Exception{


		if(Addresseslist.size()>2){
			pubads.clear();
			for(int addresslist=1;addresslist<=addresscount;addresslist++){

				Functions.delete_folder();
				Functions.clear_session();

				readExcelValues.excelValues("Smoke","AddressPage");
				int Count =addresslist+2; 
				//int TempEle = addresslist+1;
				String xyz = readExcelValues.data[4][Cap];
				//System.out.println("String xyz is "+xyz);
				String[] AddrestSplit =xyz.split("Count");
				xyz=AddrestSplit[0]+Count+AddrestSplit[1];

				SelectAddress = (MobileElement) Ad.findElementByXPath(xyz);
				//readExcelValues.data[4][Cap]);
				String SavedAddress=SelectAddress.getText();
				System.out.println("Selected saved Address is  - "+SavedAddress);
				SelectAddress.click();

				//Functions.charles_Stop();


				Functions.downloadXMLFile();
				Functions.readXML();
				Functions.verifyPubadCalwithselectedAddress("Smoke","WFXTrigger");

				if(addresscount>1 && addresslist!=addresscount){
					navigatetoAddressPage();
				}else{
					System.out.println("");
				}


			}
		}

	}

	public static void delete_folder() throws Exception{
		readExcelValues.excelValues("Smoke","Paths");


		String downloadPath = readExcelValues.data[4][Cap];

		File index = new File(downloadPath);

		if (!index.exists()) {
			System.out.println("Specified folder is not exist and creating the same folder now");
			index.mkdir();
		} else {
			System.out.println("Specified folder is exist and deleting the same folder");
			FileUtils.cleanDirectory(index);
			System.out.println("Deleted all the files in the specified folder");
		}
	}

	//Open Charless session and control from Browser
	public static void startCharlesSession() throws Exception{

		//Driver.property();
		//Open Charles Using Terminal
		String[] openCharles_str ={"/bin/bash", "-c", "open -a charles"};
		Runtime.getRuntime().exec(openCharles_str);	
		Thread.sleep(10000);

		delete_folder();
		//		readExcelValues.excelValues("Smoke","Paths");
		//
		//
		String downloadPath = readExcelValues.data[4][Cap];
		//
		//		File index = new File(downloadPath);
		//
		//		if (!index.exists()) {
		//			System.out.println("Specified folder is not exist and creating the same folder now");
		//			index.mkdir();
		//		} else {
		//			System.out.println("Specified folder is exist and deleting the same folder");
		//			FileUtils.cleanDirectory(index);
		//			System.out.println("Deleted all the files in the specified folder");
		//		}

		FirefoxProfile profile = new FirefoxProfile();

		profile.setPreference("browser.download.folderList", 2);

		profile.setPreference("browser.download.dir", downloadPath);

		profile.setPreference("browser.helperApps.neverAsk.openFile","text/xml");
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/xml");


		driver = new FirefoxDriver(profile);
		Thread.sleep(2000);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		//driver.get("http://mohantestengg:123456@control.charles");
		driver.get(readExcelValues.data[9][1]);

		readExcelValues.excelValues("Smoke","Charlesdeatils");
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[1][0])).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[2][0])).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[3][0])).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[4][0])).click();

		//Start the Charles session
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[5][0])).click();

	}
	//Open Charles controller for Stop
	public static void clear_session() throws Exception{
		readExcelValues.excelValues("Smoke","Charlesdeatils");
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[2][0])).click();
		Thread.sleep(1000);
	}


	//Open Charles controller for Stop
	public static void charles_Stop() throws Exception{
		readExcelValues.excelValues("Smoke","Charlesdeatils");
		Thread.sleep(1000);
		//driver.findElement(By.linkText(readExcelValues.data[6][0])).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[3][0])).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[1][0])).click();
	}

	//Download XML file
	public static void downloadXMLFile() throws Exception{
		readExcelValues.excelValues("Smoke","Charlesdeatils");
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[7][0])).click();
		Thread.sleep(2000);
		System.out.println("Export file");
		//driver.navigate().to(readExcelValues.data[3][0]);
		Thread.sleep(5000);
		//driver.close();
	}

	//Read XML File
	public static void readXML() throws Exception{

		readExcelValues.excelValues("Smoke","Paths");

		//Read the file name from the folder
		File folder = new File(readExcelValues.data[4][Cap]);
		File[] listOfFiles = folder.listFiles();
		String Filename = null;
		for (File file : listOfFiles) {
			if (file.isFile()) {
				Filename = file.getName();
				System.out.println("File Name is : "+Filename);
			}
		}

		File file = new File(readExcelValues.data[4][Cap]+Filename);

		BufferedReader r = new BufferedReader(new FileReader(file));

		String line = "";
		String allLine = "";


		while((line=r.readLine()) != null)
		{
			sb.append(line.trim());
			//System.out.println(line);
		}
		String	req =null;
		String pubreq=null; 
		String pubreq1 = null;
		//System.out.println(sb);
		System.out.println("data read finished");

	}	


	//Verify single Param Value from pubad call from XML File
	public static void verifyParamsFromPubadCal(String Excelname,String sheetName) throws Exception{
		//container=null;
		readExcelValues.excelValues(Excelname,sheetName);
		VerifypubadValues =readExcelValues.data[16][Cap];
		System.out.println("Pubad values are -"+VerifypubadValues);
		for(String pubreq2:pubads){
			String str[] = pubreq2.split("&");
			if(readExcelValues.data[16][Cap].contains(",")){
				String[] splitPubvalues= VerifypubadValues.split(",");
				for(int i=0;i<=splitPubvalues.length-1;i++){
					System.out.println("Pubad value is -"+splitPubvalues[i].toString()+"----Size is"+splitPubvalues.length);
					for (String ssss : str) {
						String s[] = ssss.split("=");

						if (s[0].equals(splitPubvalues[i].toString())) {
							if(i==0){
								firstParamValue = s[1].toString();
								System.out.println(splitPubvalues[i]  +" Param value is :" + firstParamValue);
								pubadvalues.add(firstParamValue);
								Thread.sleep(1000);
								System.out.println("all first Param value is :" + pubadvalues);
							}else if(s[0].equals("cxtg"))
							{

								seg = s[1].toString();
								System.out.println("seg is :"+seg);
								seg=seg.replaceAll(",",", ");
								cxtgcontainer.add(seg);
								System.out.println("CXTG Container is:" + cxtgcontainer);

							}else if(s[0].equals("wfxtg"))
							{

								seg = s[1].toString();
								System.out.println("seg is :"+seg);
								seg=seg.replaceAll(",",", ");
								wfxtgcontainer.add(seg);
								System.out.println("WFXTG Container is:" + wfxtgcontainer);

							}else
							{
								seg = s[1].toString();
								String[] items = seg.split(",");
								container = Arrays.asList(items);
								System.out.println("Container is:" + container);
								System.out.println("Container size is:" + container.toString().length());
							}
						}
					}
				}
			}
			else
			{
				for (String ssss : str) {
					String s[] = ssss.split("=");

					if (s[0].equals(VerifypubadValues.toString())) {
						firstParamValue = s[1].toString();
						System.out.println(VerifypubadValues +"Param value is :" + firstParamValue);
					}

				}

			}
		}
	}
	//Verify pubad call from XML File
	public static void verifyPubadCalwithselectedAddress(String Excelname,String sheetName) throws Exception{
		readExcelValues.excelValues(Excelname,sheetName);
		//Get Pubad call from 


		if(sb.toString().contains(readExcelValues.data[17][Cap])){
			System.out.println("bb ad call is pressent");

			pubadcal = sb.toString().substring(sb.toString().lastIndexOf(readExcelValues.data[17][Cap]));
			//System.out.println("pubad call is :"+pubadcal);

			pubreq1 = pubadcal.toString().substring(pubadcal.toString().indexOf(readExcelValues.data[7][Cap]));

			pubreq1= pubreq1.toString().replaceAll(readExcelValues.data[8][Cap], "=");
			pubreq1= pubreq1.toString().replaceAll(readExcelValues.data[9][Cap], "&");
			pubreq1= pubreq1.toString().replaceAll(readExcelValues.data[10][Cap], ",");
			int sizeparam = readExcelValues.data[14][Cap].length();
			pubreq1 = pubreq1.substring(pubreq1.indexOf(readExcelValues.data[14][Cap])+sizeparam,pubreq1.indexOf(readExcelValues.data[15][Cap]));
			//System.out.println("feed call zip is "+ pubreq1.toString());
			pubads.add(pubreq1.toString());
			System.out.println("feed call zip is "+ pubads);

		}
		else{
			System.out.println("bb ad call is not pressent");
			Assert.fail("bb ad call is not pressent");
		}


		verifyParamsFromPubadCal(Excelname,sheetName);
	}

	//Verify pubad call from XML File
	public static void verifyPubadCal(String Excelname, String sheetName) throws Exception{
		readExcelValues.excelValues(Excelname,sheetName);
		//Get Pubad call from 
		pubads.clear();

		if(Functions.sb.toString().contains(readExcelValues.data[17][Cap])){
			System.out.println("ad call is pressent");

			pubadcal = sb.toString().substring(sb.toString().lastIndexOf(readExcelValues.data[17][Cap]));
			//System.out.println("pubad call is :"+pubadcal);

			pubreq1 = pubadcal.toString().substring(pubadcal.toString().indexOf(readExcelValues.data[7][Cap]));

			if(pubreq1.contains(readExcelValues.data[8][Cap]) || pubreq1.contains(readExcelValues.data[9][Cap])||pubreq1.contains(readExcelValues.data[10][Cap])){
				pubreq1= pubreq1.toString().replaceAll(readExcelValues.data[8][Cap], "=");
				pubreq1= pubreq1.toString().replaceAll(readExcelValues.data[9][Cap], "&");
				pubreq1= pubreq1.toString().replaceAll(readExcelValues.data[10][Cap], ",");
			}else{
				System.out.println("not a ad call");
			}
			int sizeparam = readExcelValues.data[14][Cap].length();
			pubreq1 = pubreq1.substring(pubreq1.indexOf(readExcelValues.data[14][Cap])+sizeparam,pubreq1.indexOf(readExcelValues.data[15][Cap]));
			//System.out.println("feed call zip is "+ pubreq1.toString());
			if(Excelname=="Smoke"){
				pubreq1=pubreq1.toString();
			}else{
				pubreq1=pubreq1.replaceAll("^\"|:\"$","");
				pubreq1=pubreq1.replaceAll("\"","");
				pubreq1=pubreq1.replaceAll(",","&");
				//pubreq1=pubreq1.replaceAll(":","=");
			}

			pubads.add(pubreq1.toString());
			System.out.println("feed call zip is "+ pubads);

		}
		else{
			System.out.println("bb ad call is not pressent");
			Assert.fail("bb ad call is not pressent");
		}


		verifyParamsFromPubadCal(Excelname,sheetName);
	}

	//	//Verify single Param Value from pubad call from XML File
	//	public static void verifySingleParamFromPubadCal(String firstParam) throws Exception{
	//
	//		String str[] = pubreq1.split("&");
	//		for (String ssss : str) {
	//			String s[] = ssss.split("=");
	//			if (s[0].equals(firstParam)) {
	//				firstParamValue = s[1].toString();
	//				System.out.println(firstParam +"Param value is :" + firstParamValue);
	//
	//			}
	//		}
	//	}

	//	//Verify Two Param Values from pubad call from XML File
	//	public static void verifyTwoParamsFromPubadCal(String firstParam,String SecondParam) throws Exception{
	//		String str[] = pubreq1.split("&");
	//		for (String ssss : str) {
	//			String s[] = ssss.split("=");
	//			if (s[0].equals(firstParam)) {
	//				firstParamValue = s[1].toString();
	//				System.out.println(firstParam +"Param value is :" + firstParamValue);
	//
	//			}
	//			if (s[0].equals(SecondParam)) {
	//
	//				SecondParamValue  = s[1].toString();
	//				String[] items = SecondParamValue.split(",");
	//				container = Arrays.asList(items);
	//				System.out.println("Container is:" + container);
	//			}
	//		}
	//	}

	//Verify API call from XML File
	public static void verifyAPICal(String SheetName) throws Exception{

		//readXML();
		readExcelValues.excelValues("Smoke",SheetName);
		//Get Pubad call from 
		//for(int APIlist=1;APIlist<=whichAPI;APIlist++){
		String ApiCall = sb.toString().substring(sb.toString().lastIndexOf(readExcelValues.data[2][Cap]));
		String req1 = ApiCall.toString().substring(ApiCall.toString().indexOf(readExcelValues.data[3][Cap]));
		//System.out.println("Req1 data is "+req1.toString());
		req = req1.toString().substring(req1.indexOf(readExcelValues.data[4][Cap])+7,req1.indexOf(readExcelValues.data[5][Cap])-2);
		System.out.println("file is "+ req.toString());
		//}
	}

	//Verify WFXTG From Api
	public static void verify_wfxtg(String sheetName) throws Exception{
		readExcelValues.excelValues("Smoke",sheetName);

		String WfxTrigeer = req.toString().substring(req.indexOf("current"),req.indexOf("scatterSegs"));
		WfxTrigeer = WfxTrigeer.replaceAll("^\"|:\"$","");
		System.out.println("WfxTrigeer is :"+WfxTrigeer.toString());
		String [] wfxcontainer = wfxtgcontainer.toString().split(",");
		for(String wfxcontainerkey : wfxcontainer ){
			if(WfxTrigeer.toString().contains(wfxcontainerkey.toString())){
				System.out.println("Values are matched with : "+wfxcontainerkey.toString()+"----"+WfxTrigeer.toString());
			}else if(wfxcontainerkey.equals("nl")){
				System.out.println("Values are matched with : "+wfxcontainerkey.toString()+"----"+WfxTrigeer.toString());
			}else{
				System.out.println("Values are not matched with : "+wfxcontainerkey.toString()+"----"+WfxTrigeer.toString());
			}

		}

	}



	//Verify API call from XML File
	public static void readwfxTriggers(String sheetName) throws Exception{
		readExcelValues.excelValues("Smoke",sheetName);

		String Content = "";
		// Json file
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new String(req.toString()));
		JSONObject jsonObject = (JSONObject) obj;

		List<String> segmentList = new ArrayList<String>();
		JSONObject mainTag = (JSONObject) jsonObject.get(readExcelValues.data[11][Cap]);
		JSONArray scatterSegs = (JSONArray) mainTag.get(readExcelValues.data[12][Cap]);
		Iterator<JSONObject> iterator = scatterSegs.iterator();
		while (iterator.hasNext()) {

			JSONObject zipMainJsonObject = (JSONObject) iterator.next();

			JSONArray zcszipJson = (JSONArray) zipMainJsonObject.get("zcs");
			for(String Pubadzip :pubadvalues){
				if (zcszipJson != null) {
					Iterator<JSONObject> zcszipIterator = zcszipJson.iterator();
					while (zcszipIterator.hasNext()) {
						JSONObject zcszipObject = (JSONObject) zcszipIterator.next();

						zipCode = zcszipObject.get("zip").toString();
						if (zipCode.contains(Pubadzip)) {
							System.out.println("Value is :" + Pubadzip);
							JSONArray segmentJsonArray = (JSONArray) zcszipObject.get("cxtg");
							System.out.println(zipCode + " zip code segemtn list : " + segmentJsonArray);

							segmentList.addAll(segmentJsonArray.subList(0, segmentJsonArray.size()));
							List<String> firstList = new ArrayList<String>();
							List<String> secondList = new ArrayList<String>();
							for (Object firstString : segmentList) {
								//System.out.println("firstString: " + firstString);
								firstList.add(firstString.toString().trim());
							}
							//cxtgcontainer.addAll(cxtgcontainer.subList(0, cxtgcontainer.size()));
							for (Object secondString : cxtgcontainer) {
								//System.out.println("secondString : " + secondString);
								secondList.add(secondString.toString().trim());
							}
							System.out.println("segments :" + firstList);
							System.out.println("Containe :" + cxtgcontainer);
							//System.out.println("File name is :"+Filename);

							for(String flist:firstList){
								if(cxtgcontainer.toString().contains(flist)){
									//System.out.println("Matched");
								}else{
									System.out.println("Not Matched");
									Assert.fail("cxtg Values are not matched");
								}
							}

						}
					}
				}
			}
		}
		System.out.println("cxtg Testcase passed");
	}

	//Read Location.wfxtriggers API
	public static void readlocation_wfxTriggers(String sheetName) throws Exception{
		readExcelValues.excelValues("Smoke",sheetName);
		String fgeoActual=null;
		String Pubadparmascount = readExcelValues.data[16][Cap];
		String pubparam[] = Pubadparmascount.split(",");
		System.out.println("Size of Pub param is :"+pubparam.length);
		if(req.toString().contains(readExcelValues.data[11][Cap])){
			System.out.println("req :"+req.toString());
			if(req.toString().contains("false")||req.toString().contains("Mnl")||req.toString().contains("[]")){
				fgeoActual="nl";
			}else{


				String factualreq1 = req.substring(req.indexOf("[")+1,req.indexOf("]")-1);
				System.out.println("FactualReq1 ::"+factualreq1);
				String[] factualarrays = factualreq1.split("},");
				String[] factualVal = null;
				for(String factualKeys:factualarrays)
				{
					factualVal =factualKeys.split(",");
					//System.out.println("factualVal :"+factualVal);
					for(String FactualValues:factualVal)
					{
						if(FactualValues.contains(readExcelValues.data[12][Cap]))
						{
							String Factualval = FactualValues.toString().replace("{", "").trim();
							String[] fgeofilter = Factualval.toString().split(",");
							Factualval = fgeofilter[0].replaceAll("^\"|\"$","");
							String[]fgeoval = Factualval.split(":");
							String fgeovalues =fgeoval[1].replaceFirst("^\"|\"$","");
							fgeolist.add(fgeovalues);
							if(Factualval.contains(""))
							{
								Assert.assertNotNull(Factualval);
							}
						}
					}
				}
				fgeoActual = fgeolist.toString().replace("[", "").replace("]", "");
				System.out.println("sg values are :" + fgeoActual.toString());
			}

			System.out.println("firstParamValue is :"+firstParamValue);
			String[] Factualarrays = firstParamValue.toString().split(",");

			for(String Factualkey : Factualarrays){
				Factualkey=Factualkey.toString().replaceAll("^\"|\"$", "");	
				Factualkey=Factualkey.toString().replace(" ", "");

				//Fgeo Comparison
				if(fgeoActual.toString().contains(Factualkey.toString())){
					System.out.println("Values are matched --"+firstParamValue +"--"+Factualkey);

				}else
				{
					System.out.println("Values are not matched for :"+ Factualkey);
					Assert.fail("Values are not matched for :"+ Factualkey);
				}
			}
		}

		if(Pubadparmascount.contains(",")){
			//Faud values in to Container
			System.out.println("container  is "+container);
			String FaudActual = container.toString().replace("[", "").replace("]", "");
			if(req.toString().contains("set")&&req.toString().contains("true")){
				String faudreq1 = req.substring(req.indexOf("set")+7,req.indexOf("]}"));
				if(faudreq1.contains("group"))
				{
					String Faudval = faudreq1.toString().replace("{", "").trim();
					Faudval =Faudval.replaceAll("}","");
					String[] faudgroup = Faudval.toString().split(",");
					for(String Faudval1:faudgroup){
						//System.out.println("Faudval1 :"+Faudval1);
						String[]faudval = Faudval1.split(":");
						String faudvalues =faudval[1].replaceFirst("^\"|\"$","");
						faudvalues =faudvalues.replaceAll("^\"|\"$","");
						faudlist.add(faudvalues);

						System.out.println("faudlist :"+faudlist);
						//System.out.println("container::"+container);

					}
				}
			}else{
				faudlist.add("nl");
			}
			String faudValues = faudlist.toString().replace("[", "").replace("]", "");
			//faued values split from Pubads
			String Expected = container.toString().replace("[", "").replace("]", "");
			//Expected = Expected.toString().replaceAll("\"", "");	
			System.out.println("Expected vales are :"+Expected.toString());
			System.out.println("faudValu vales are :"+faudValues.toString());
			//Fgeo Comparison
			String[] Faudlarrays = seg.toString().split(",");
			for(String Faudlkey : Faudlarrays){
				Faudlkey=Faudlkey.toString().replaceAll("^\"|\"$", "");	
				Faudlkey=Faudlkey.toString().replace(" ", "");

				//Fgeo Comparison
				if(faudValues.toString().contains(Faudlkey.toString())){
					System.out.println("Values are matched --"+seg +"--"+Faudlkey);

				}else
				{
					System.out.println("Values are not matched for :"+ Faudlkey);
					Assert.fail();
				}
			}

		}
	}


	//Pull to refresh
	public static void pulltorefresh() throws Exception{
		readExcelValues.excelValues("Smoke","pulltorefresh");
		//pull to refresh
		MobileElement el = (MobileElement) Ad.findElementByXPath(readExcelValues.data[1][Cap]);
		//MobileElement el1 = (MobileElement) Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAStaticText[3]");
		TouchAction action1 = new TouchAction(Ad);

		action1.longPress(el).moveTo(2, 400).release().perform();
		Thread.sleep(1000);

	}
	//SCROLL DOWN
	public static void scroll_Down() throws Exception{
		//Scroll JS
		JavascriptExecutor js = (JavascriptExecutor) Ad ;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);
	}

	//Move to selected page
	public static void Verify_selectedPages(String Pagename) throws Exception{

		readExcelValues.excelValues("Smoke",Pagename);
		Functions.verify_neednavBack(Pagename);
		String Counts =readExcelValues.data[2][Cap].toString();
		int Count =Integer.parseInt(Counts);
		int x;
		Xpth = readExcelValues.data[4][Cap].toString();
		if(Pagename=="Hurricane(MainPage)"){
			x=3;
			String[]xpt = Xpth.split("xpath");
			String xp=null;
			int xpath;
			WebElement Hurricane = null;
			for(int i = 10; i<=13;i++){
				Xpth=xpt[0]+i+xpt[1];
				Hurricane = Ad.findElementByXPath(Xpth);
				String HurricaneText = Hurricane.getText();
				System.out.println("HurricaneText"+HurricaneText);
				try{
					if(HurricaneText.toString().contains("Trop")||HurricaneText.toString().contains("HURRICANE CENTRAL")){
						if(Hurricane.isDisplayed()){
							break;
						}
						i=i-1;
						Functions.scroll_Down();
					}
					else{
						Functions.scroll_Down();
					}
				}catch(Exception e){
					System.out.println("News Extended option not found still on search");
				}
			}



		}else{
			x=3;
		}

		String elemntType = readExcelValues.data[x][Cap].toString();
		if(readExcelValues.data[4][Cap].contains("NEWS")){
			for(int i =1;i<=Count;i++){
				try{
					if(Ad.findElement(By.name(readExcelValues.data[4][Cap])).isDisplayed()){
						System.out.println("News element found");

					}
					break;
				} catch (Exception e) {
					System.out.println("News element Not fount");
				}	
				scroll_Down();
			}
		}
		for(int i =1;i<=Count;i++){

			if(elemntType.contains("name")){
				if(Ad.findElement(By.name(readExcelValues.data[4][Cap])).isDisplayed()){
					System.out.println(readExcelValues.data[1][Cap] +"page is displayed");
					break;
				}else{
					scroll_Down();
				}
			}else if(elemntType.contains("xpath")){
				if(Ad.findElement(By.xpath(Xpth)).isDisplayed()){
					System.out.println(readExcelValues.data[1][Cap]+"page is displayed");
					break;
				}else{
					scroll_Down();
				}
			}else if(elemntType.contains("classname")){
				if(Ad.findElement(By.className(readExcelValues.data[4][Cap])).isDisplayed()){
					System.out.println(readExcelValues.data[1][Cap] + " page is displayed");
					break;
				}else{
					scroll_Down();
				}
			}else if(elemntType.contains("css")){
				if(Ad.findElement(By.cssSelector("//input[contains(@name, '"+readExcelValues.data[4][Cap]+"')]")).isDisplayed()){
					System.out.println(readExcelValues.data[1][Cap] + " page is displayed");
					break;
				}else{
					scroll_Down();
				}
				break;

			}

		}
	}

	//Get attribute
	public static void get_attribute(){

	}

	//Navigate to selected exteneded page
	public static void Navigate_extendedPages(String Pagename) throws Exception{
		readExcelValues.excelValues("Smoke",Pagename);
		String Xpth = readExcelValues.data[5][Cap].toString();

		int x;
		if(Pagename=="PreRollVideo"){
			x=18;

			String[]xpt = Xpth.split("xpath");
			String xp=null;
			int xpath;
			//Xpth=xpt[0]+xpath+xpt[1];
			try{
				xpath=2;
				Xpth=xpt[0]+xpath+xpt[1];
				if(Ad.findElementByXPath(Xpth).isDisplayed()){
					System.out.println("Video's page is present");
				}


			}catch(Exception e){
				xpath=3;
				Xpth=xpt[0]+xpath+xpt[1];
				if(Ad.findElementByXPath(Xpth).isDisplayed()){
					System.out.println("Video's page is present");
				}
			}
		}else if(Pagename=="News"){
			x=18;
			String[]xpt = Xpth.split("xpath");
			String xp=null;
			int xpath;

			for(int i = 13; i<=16;i++){
				Xpth=xpt[0]+i+xpt[1];
				try{
					if(Ad.findElementByXPath(Xpth).isDisplayed()){
						break;
					}
				}catch(Exception e){
					System.out.println("News Extended option not found still on search");
				}
			}



		}else if(Pagename=="Hurricane(MainPage)"){
			x=13;
			String[]xpt = Xpth.split("xpath");
			String xp=null;
			int xpath;

			for(int i = 10; i<=13;i++){
				Xpth=xpt[0]+i+xpt[1];
				try{
					if(Ad.findElementByXPath(Xpth).isDisplayed()){
						break;
					}
				}catch(Exception e){
					System.out.println("News Extended option not found still on search");
				}
			}

			//			Functions.Xpth=Xpth.toString();

		}else{
			x=10;
		}



		String ElemntType = readExcelValues.data[x][Cap].toString();
		if(ElemntType.contains("name")){
			Ad.findElementByName(readExcelValues.data[5][Cap]).click();
		}else if(ElemntType.contains("xpath")){

			Ad.findElementByXPath(Xpth).click();

		}else if(ElemntType.contains("classname")){
			Ad.findElementByClassName(readExcelValues.data[5][Cap]).click();
		}else{
			System.out.println("User Still on Home page element not fount");
			Assert.fail();
		}

		if(x==18){
			try{
				String originalContext = Ad.getContext();
				System.out.println("Context is :"+originalContext.toString());

				//Ad.context("NATIVE_APP");
				//Ad.context("WebView");
				Ad.findElement(By.name("video messagecard close")).click();
				Ad.findElementByName(readExcelValues.data[6][Cap]).click();
				if(ElemntType.contains("name")){
					Ad.findElementByName(readExcelValues.data[5][Cap]).click();
				}else if(ElemntType.contains("xpath")){
					Ad.findElementByXPath(Xpth).click();
				}else if(ElemntType.contains("classname")){
					Ad.findElementByClassName(readExcelValues.data[5][Cap]).click();
				}else{
					System.out.println("User Still on Home page element not fount");
					Assert.fail();
				}
			}catch(Exception e){
				System.out.println("User on Videos page and continue");
			}

		}

		//Verify the  user on Extended page or not
		Functions.Verify_Extenedpage(Pagename);

	}

	//Verify User on Extened page
	public static void  Verify_Extenedpage(String Pagename) throws Exception{
		readExcelValues.excelValues("Smoke",Pagename);
		//Verify the  user on Extended page or not
		String navElemntType = readExcelValues.data[3][Cap].toString();
		if(navElemntType.contains("name")){
			if(Ad.findElementByName(readExcelValues.data[6][Cap]).isDisplayed()){
				System.out.println("User Navigate to Selected "+readExcelValues.data[1][Cap] +" page");
			}else{
				System.out.println("User Still on Home page");
			}
		}else if(navElemntType.contains("xpath")){
			if(Ad.findElementByXPath(readExcelValues.data[6][Cap]).isDisplayed()){
				System.out.println("User Navigate to Selected "+readExcelValues.data[1][Cap] +" page");
			}else{
				System.out.println("User Still on Home page");
			}
		}else if(navElemntType.contains("classname")){
			if(Ad.findElementByClassName(readExcelValues.data[6][Cap]).isDisplayed()){
				System.out.println("User Navigate to Selected "+readExcelValues.data[1][Cap] +" page");
			}else{
				System.out.println("User Still on Home page");
			}

		}
	}

	//Verify If User need to navigate back from extened page
	public static void verify_neednavBack(String Pagename) throws Exception{
		readExcelValues.excelValues("Smoke",Pagename);
		try{
			if(Ad.findElementByName(readExcelValues.data[6][Cap]).isDisplayed()){
				if(readExcelValues.data[12][Cap].toString().contains("Yes"))
				{
					navBack_fromExtendedPage(Pagename);
					navBack_fromExtendedPage(Pagename);
				}else{

					//System.out.println("User Navigate to Selected "+readExcelValues.data[1][Cap] +" page");
					navBack_fromExtendedPage(Pagename);

				}
			}
		}catch(Exception e){
			System.out.println("User on Main page");
		}
	}

	//Navigate to SubModule page
	public static void Navigate_Submodule(String Pagename) throws Exception{
		readExcelValues.excelValues("Smoke",Pagename);
		String navElemntType = readExcelValues.data[3][Cap].toString();
		if(navElemntType.contains("xpath")){
			if(Ad.findElementByName(readExcelValues.data[4][Cap]).isDisplayed()){
				System.out.println("User Navigate to Selected "+readExcelValues.data[1][Cap] +" page");
				//Navigate to Submodule page
				Ad.findElementByXPath(readExcelValues.data[5][Cap]).click();
				//Functions.Verify_Extenedpage(Pagename);

			}else{
				System.out.println("User Still on Extened page");
			}
		}
	}

	//Move to selected page
	public static void Verify_Adpresenton_extendedPages(String Pagename) throws Exception{
		readExcelValues.excelValues("Smoke",Pagename);
		String Adc = readExcelValues.data[11][Cap].toString();
		int Adcount =Integer.parseInt(Adc);
		String Adp = readExcelValues.data[7][Cap].toString();
		String[] AdPath = null;
		if(Adp.contains(",")){
			AdPath = Adp.split(",");
		}

		String Adx = readExcelValues.data[8][Cap].toString();
		String[]Adxvl=null;
		String Ady = readExcelValues.data[9][Cap].toString();
		String[]Adyvl=null;
		if(Adx.contains(",")){
			Adxvl = Adx.split(",");
		}
		if(Ady.contains(",")){
			Adyvl = Ady.split(",");
		}

		//int AdxVal = Integer.parseInt(Adx);
		int AdxVal;
		int AdyVal;
		int path ;
		if(AdPath==null){
			path =1;
		}else
		{
			path = AdPath.length;
		}

		System.out.println("ADpatha is :"+path);
		for(int ads =0;ads<=path-1;ads++){
			if(path>1){
				Adp = AdPath[ads].toString();
				AdxVal = Integer.parseInt(Adxvl[ads]);
				AdyVal = Integer.parseInt(Adyvl[ads]);
			}else{
				AdxVal = Integer.parseInt(Adx);
				AdyVal = Integer.parseInt(Ady);
			}
			if(ads>0){
				for(int i =1;i<=3;i++){

					if(Ad.findElementByXPath(AdPath[ads].toString()).isDisplayed()){
						//Ad.findElementByName("ADVERTISEMENT").isDisplayed()){
						break;
					}
					scroll_Down();
				}

			}
			//try{
			WebDriverWait wait = new WebDriverWait(Ad, 4);
			wait.until(ExpectedConditions.visibilityOf(Ad.findElementByXPath(Adp)));
			MobileElement AdEle = (MobileElement) Ad.findElementByXPath(Adp);
			//				String xVal=AdxVal.toString().trim();
			//				System.out.println("xVal is :"+xVal.trim());
			int x= AdxVal;
			String yVal=readExcelValues.data[9][Cap].toString().trim();
			int y= AdyVal;


			if(AdEle.isDisplayed())
			{
				Dimension ActualSize = AdEle.getSize();
				System.out.println("Size of the ad is ::"+ActualSize);
				System.out.println("Height  of the ad is ::"+ActualSize.getHeight());
				System.out.println("Width of the ad is ::"+ActualSize.getWidth());
				if(ActualSize.getHeight() == y && ActualSize.getWidth()==x){

					System.out.println("Ad present on Extended"+ Pagename +"page");
					System.out.println("Ad sizes are matched");
					//				ATUReports.add("Ad is displayed on Extended"+ Pagename +"page",false);
					//				logger.log(LogStatus.PASS, "Ad is displayed on Extended 10 Days page");
					//Ad.findElementByName(readExcelValues.data[6][Cap]).click();


				}else
				{

					System.out.println("Ad present but sizes are not matched");
					Ad.findElementByName(readExcelValues.data[6][Cap]).click();
					Assert.fail();
				}
				//Ad.findElementByName(readExcelValues.data[6][Cap]).click();
				Thread.sleep(2000);
			}
			//			}catch(Exception e){
			//				navBack_fromExtendedPage(Pagename);
			//
			//			}
		}
		if(readExcelValues.data[12][Cap].toString().contains("No")){

			navBack_fromExtendedPage(Pagename);
		}else{
			System.out.println("try to Navigate submodule");
		}
	}


	//Navigate back to home page from Submodule
	public static void navBackfrom_Submodule(String Pagename) throws Exception{
		readExcelValues.excelValues("Smoke",Pagename);
		if(readExcelValues.data[12][Cap].toString().contains("Yes")){
			navBack_fromExtendedPage(Pagename);
			navBack_fromExtendedPage(Pagename);
		}else{
			System.out.println("try to Navigate submodule");
		}
	}

	//Close the app and launch the app
	public static void close_launchApp(){
		Ad.closeApp();
		Ad.launchApp();
	}

	//Navigate Back from Extended page
	public static void navBack_fromExtendedPage(String sheetName) throws Exception{
		//Verify the  user on Extended page or not
		readExcelValues.excelValues("Smoke",sheetName);
		String navElemntType = readExcelValues.data[10][Cap].toString();
		if(navElemntType.contains("name")){
			if(Ad.findElementByName(readExcelValues.data[6][Cap]).isDisplayed()){
				Ad.findElementByName(readExcelValues.data[6][Cap]).click();
			}else{

			}
		}else if(navElemntType.contains("xpath")){
			if(Ad.findElementByXPath(readExcelValues.data[6][Cap]).isDisplayed()){
				Ad.findElementByName(readExcelValues.data[6][Cap]).click();
			}else{
				System.out.println("User Still on Home page");
			}
		}else if(navElemntType.contains("classname")){
			if(Ad.findElementByClassName(readExcelValues.data[6][Cap]).isDisplayed()){
			}else{
				System.out.println("User Still on Home page");
			}

		}
	}

	//Verify Feed calls
	public static void Verify_feedcals(String sheetName) throws Exception{
		//Functions.downloadXMLFile();
		readXML();
		readExcelValues.excelValues("Smoke",sheetName);
		String feedVal=readExcelValues.data[3][Cap].toString().trim();
		System.out.println("xVal is :"+feedVal.trim());
		int feedcount=Integer.parseInt(feedVal);
		for(int Feed=0;Feed<=feedcount;Feed++){


			String pubadcal;

			if(Feed==0){
				pubadcal = sb.toString().substring(sb.toString().lastIndexOf(readExcelValues.data[1][Cap]));
				if(pubadcal.toString().contains(readExcelValues.data[1][Cap])){
					System.out.println("bb ad call is pressent");
				}else{
					System.out.println("bb call not presented");
					Assert.fail();
				}
			}else
			{
				String feedcall = readExcelValues.data[2][Cap]+Feed;
				pubadcal = sb.toString().substring(sb.toString().lastIndexOf(readExcelValues.data[2][Cap]+Feed));
				if(pubadcal.toString().contains(feedcall)){
					System.out.println("Feed_"+Feed +"ad call is pressent");
				}else{
					System.out.println("Feed_"+Feed +"ad call is not pressent");
					Assert.fail("Feed_"+Feed +"ad call is not pressent");
				}
			}

		}
	}



	//Verify pubad params call from XML File
	public static void verifyPubadCal_params(String Excelname,int feedval,String sheetName) throws Exception{
		readExcelValues.excelValues("Smoke",sheetName);
		pubadcal=null;
		pubreq1=null;
		//Get Pubad call from 
		pubads.clear();

		if(sb.toString().contains(readExcelValues.data[17][Cap]+feedval)){
			System.out.println("ad call is pressent");

			pubadcal = sb.toString().substring(sb.toString().lastIndexOf(readExcelValues.data[17][Cap]+feedval));
			//System.out.println("pubad call is :"+pubadcal);

			pubreq1 = pubadcal.toString().substring(pubadcal.toString().indexOf(readExcelValues.data[7][Cap]));

			pubreq1= pubreq1.toString().replaceAll(readExcelValues.data[8][Cap], "=");
			pubreq1= pubreq1.toString().replaceAll(readExcelValues.data[9][Cap], "&");
			pubreq1= pubreq1.toString().replaceAll(readExcelValues.data[10][Cap], ",");
			int sizeparam = readExcelValues.data[14][Cap].length();
			pubreq1 = pubreq1.substring(pubreq1.indexOf(readExcelValues.data[14][Cap])+sizeparam,pubreq1.indexOf(readExcelValues.data[15][Cap]));
			//System.out.println("feed call zip is "+ pubreq1.toString());
			pubads.add(pubreq1.toString());
			System.out.println("feed call zip is "+ pubads);

		}
		else{
			System.out.println("bb ad call is not pressent");
			Assert.fail("bb ad call is not pressent");
		}


		verifyParamsFromPubadCal(Excelname,sheetName);
	}


	//Scroll app till end
	public static void Scroll_end() throws Exception{
		readExcelValues.excelValues("Smoke","General");
		for(int scrollend=1;scrollend<=12;scrollend++){
			if(Ad.findElementByName(readExcelValues.data[1][Cap]).isDisplayed()){
				System.out.println("User done scrolling");
				break;
			}else{
				scroll_Down();
			}

		}

	}

	//Set app  into TestMode

	public static void Setappinto_TestMode() throws Exception
	{
		readExcelValues.excelValues("Smoke","TestMode");

		MobileElement el = null;
		//Ad.findElementByXPath(readExcelValues.data[2][Cap]).click();
		Ad.findElementByName(readExcelValues.data[1][Cap]).click();
		Thread.sleep(1000);
		try{
			el =(MobileElement) Ad.findElementByName(readExcelValues.data[3][Cap]);
		}catch (Exception e){
			el =(MobileElement) Ad.findElementByName(readExcelValues.data[4][Cap]);
		}
		MobileElement el1 =(MobileElement) Ad.findElementByName(readExcelValues.data[5][Cap]);

		TouchAction action1 = new TouchAction(Ad);
		action1.longPress(el1).moveTo(el).release().perform();
		Thread.sleep(1000);
		MobileElement el2=(MobileElement) Ad.findElementByName(readExcelValues.data[6][Cap]);
		el2.click();
		Thread.sleep(1000);
		for(int i=1;i<=10;i++)
		{
			Ad.findElementByName(readExcelValues.data[11][Cap]).click();
		}

		Ad.findElementByName(readExcelValues.data[12][Cap]).click();
		Ad.findElementByName(readExcelValues.data[16][Cap]).click();

		Ad.findElementByXPath(readExcelValues.data[18][Cap]).click();
		Ad.findElementByName(readExcelValues.data[19][Cap]).click();

		Thread.sleep(3000);
		try{
			Ad.closeApp();
			Ad.launchApp();
		}catch(Exception e){
			Functions.launchtheApp();
		}
		//app set to Test mode

		readExcelValues.excelValues("Smoke","TestMode");
		Thread.sleep(5000);
		try{
			System.out.println("excel data :"+readExcelValues.data[1][Cap]);
			if(Ad.findElementByName(readExcelValues.data[1][Cap]).isDisplayed()){
				//Select Addresss
				Ad.findElementByName(readExcelValues.data[20][Cap]).click();
			}else{
				System.out.println("User already on address search page");
			}
			System.out.println("Searching for address");
			//			Ad.findElementByClassName("UIASearchBar").click();
			//			Ad.findElementByClassName("UIASearchBar").sendKeys("08302");
			//
			//			Thread.sleep(2000);
			//			Ad.findElementByName("Search").click();
			//			//Ad.navigate().back();
			//
			//			Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]").click();
			//			Thread.sleep(2000);
			//			//Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]").click();
			//			Thread.sleep(3000);    
			//	Functions.enternewAddress(readExcelValues.data[21][Cap]);
			System.out.println("Address entered and searched");
		}catch (Exception e){
			System.out.println("Address already presented");
		}



	}

	//Verify Thirdparty Beacon
	public static void thirdPart_Beacon() throws Exception{
		readExcelValues.excelValues("Smoke","ThirdpartyBecon");
		System.out.println("pubadcal :"+pubadcal);
		if(pubadcal.contains(readExcelValues.data[1][Cap])){
			String Pubcal=pubadcal.toString().substring(pubadcal.toString().indexOf(readExcelValues.data[2][Cap]),pubadcal.toString().indexOf(readExcelValues.data[3][Cap]));
			System.out.println("pubcal is :"+Pubcal.toString());
			Pubcal =Pubcal.replace("thirdPartyBeacon", "&thirdPartyBeacon");
			String [] Creativetext=Pubcal.split(readExcelValues.data[4][Cap]);
			System.out.println("string 1 :"+Creativetext[0]);
			System.out.println("string 2 :"+Creativetext[1]);
			String Cidtext =Creativetext[0].toString().substring(Creativetext[0].indexOf(readExcelValues.data[2][Cap]),Creativetext[0].indexOf(readExcelValues.data[5][Cap]));
			System.out.println("Cidtext :"+Cidtext);
			String CreativeID[] = Cidtext.split(readExcelValues.data[6][Cap]);
			if(CreativeID[1].isEmpty()){
				Assert.fail("Creative id showing Empty");
			}else{
				System.out.println("CreativeId is:"+CreativeID[1].toString());
			}

			String Ttext = Creativetext[1].replace("thirdPartySurvey", "&thirdPartySurvey");
			String[] Tpartytext =Ttext.split(readExcelValues.data[7][Cap]);
			for(String TPtext:Tpartytext){
				String[] thirdpartytext =TPtext.split(readExcelValues.data[8][Cap]);
				if(thirdpartytext[1].isEmpty()){
					Assert.fail("Thirdparty beacon/Survey shows Empty");
				}else{
					System.out.println("Thirdparty beacon/Survey urls is --:"+thirdpartytext[1].toString());
				}
			}
		}else{
			Assert.fail("Thirdparty Beacons are not present");
		}
	}


	//Verify Video ads
	public static void Verify_videoads() throws Exception{
		long start1 = System.currentTimeMillis();
		System.out.println("Start Time :" + start1+" millisec");
		MobileElement clickvideo = (MobileElement) Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]");
		List<MobileElement> numberOfVideos = clickvideo.findElementsByClassName("UIATableCell");

		System.out.println("Size is :"+numberOfVideos.size());
		int x=2;
		for(int n =1;n<=numberOfVideos.size();n++){
			//Thread.sleep(5000);
			try{
				WebElement Learnmore = Ad.findElementByName("Learn More");
				//Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell["+n+"]/UIAWebView[1]/UIAStaticText[1]");
				//System.out.println(Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAWebView[1]/UIAStaticText[1]").getText());
				//				WebDriverWait wait = new WebDriverWait(Ad, 10);
				//				wait.until(ExpectedConditions.visibilityOf(Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAWebView[1]")));
				//MobileElement WebView = (MobileElement) Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAWebView[1]");
				//List<WebElement> listofAdelements = Learnmore.findElements(By.className("UIAStaticText"));
				System.out.println("Learn More Present");

				if(n==x){
					scroll_Down();
					x=x+n;
				}

			}catch(Exception e){
				System.out.println("Learn more not displayed");
			}

		}

	}

	//Clear excel data
	public static void Clear_Exceldata(String Pagename) throws Exception{
		readExcelValues.excelValues("Cust_Param_Result",Pagename);

		for(int feeds=1;feeds<=6;feeds++){

			String[][] data1 = new String[10][10];
			ExcelData er1 = new ExcelData();
			data1 = er1.excelread("Cust_Param_Result",Pagename);

			int Getresult1 = feeds*2;
			//Change values for entering result into all the feeds
			int ResultColumn_n1=7+Getresult1;
			int ResultColumn_n2=8+Getresult1;

			//Write results into Excel
			WriteResultintoExcel wResult1 = new WriteResultintoExcel();
			for(int testcase=1;testcase<=43;testcase++)
			{
				wResult1.enterResult("SMOKE", "n", "n", testcase, ResultColumn_n1, ResultColumn_n2);

			}

		}
	}




	//Verify pubad call from XML File
	public static void verifyPubadCal_CustParam(int feed,String Excelname, String sheetName) throws Exception{
		readExcelValues.excelValues(Excelname,sheetName);
		//Get Pubad call from 


		if(Functions.sb.toString().contains(readExcelValues.data[17][Cap])){
			System.out.println("ad call is pressent");

			int sheetVal;
			if(sheetName=="readTruboApi"){
				adcal = sb.toString().substring(sb.toString().lastIndexOf(readExcelValues.data[17][Cap]));
				adcal = sb.toString().substring(sb.toString().lastIndexOf(readExcelValues.data[17][Cap]));
				adreq1 = adcal.toString().substring(adcal.toString().indexOf(readExcelValues.data[7][Cap]));

				if(adreq1.contains(readExcelValues.data[8][Cap]) || adreq1.contains(readExcelValues.data[9][Cap])||adreq1.contains(readExcelValues.data[10][Cap])){
					adreq1= adreq1.toString().replaceAll(readExcelValues.data[8][Cap], "=");
					adreq1= adreq1.toString().replaceAll(readExcelValues.data[9][Cap], "&");
					adreq1= adreq1.toString().replaceAll(readExcelValues.data[10][Cap], ",");
				}else{
					System.out.println("not a ad call");
				}


				int sizeparam = readExcelValues.data[14][Cap].length();
				adreq1 = adreq1.substring(adreq1.indexOf(readExcelValues.data[14][Cap])+sizeparam,adreq1.indexOf(readExcelValues.data[15][Cap]));
				//System.out.println("feed call zip is "+ pubreq1.toString());
				if(Excelname=="Smoke"){
					adreq1=adreq1.toString();
				}else{
					adreq1=adreq1.replaceAll("^\"|:\"$","");
					adreq1=adreq1.replaceAll("\"","");
					adreq1=adreq1.replaceAll(",","&");
					adreq1=adreq1.replaceAll("=&","nl&");
					
					//pubreq1=pubreq1.replaceAll(":","=");
				}

				ads.add(adreq1.toString());
				//System.out.println("feed call Values is "+ ads);

				System.out.println("ads data is :"+ads);
				System.out.println("***********************************");

			}else{
				//for(int feed=1;feed<=5;feed++){
					pubads.clear();

					pubadcal = sb.toString().substring(sb.toString().lastIndexOf(readExcelValues.data[17][Cap])+feed);
					pubadcal = sb.toString().substring(sb.toString().lastIndexOf(readExcelValues.data[17][Cap]));
					pubreq1 = pubadcal.toString().substring(pubadcal.toString().indexOf(readExcelValues.data[7][Cap]));

					if(pubreq1.contains(readExcelValues.data[8][Cap]) || pubreq1.contains(readExcelValues.data[9][Cap])||pubreq1.contains(readExcelValues.data[10][Cap])){
						pubreq1= pubreq1.toString().replaceAll(readExcelValues.data[8][Cap], "=");
						pubreq1= pubreq1.toString().replaceAll(readExcelValues.data[9][Cap], "&");
						pubreq1= pubreq1.toString().replaceAll(readExcelValues.data[10][Cap], ",");
					}else{
						System.out.println("not a ad call");
					}

					int sizeparam = readExcelValues.data[14][Cap].length();
					pubreq1 = pubreq1.substring(pubreq1.indexOf(readExcelValues.data[14][Cap])+sizeparam,pubreq1.indexOf(readExcelValues.data[15][Cap]));
					//System.out.println("feed call zip is "+ pubreq1.toString());
					if(Excelname=="Smoke"){
						pubreq1=pubreq1.toString();
					}else{
						pubreq1=pubreq1.replaceAll("^\"|:\"$","");
						pubreq1=pubreq1.replaceAll("\"","");
						pubreq1=pubreq1.replaceAll(",","&");
						pubreq1=pubreq1.replaceAll("_"," ");
					}

					pubads.add(pubreq1.toString());
					System.out.println("Pubads data is :"+pubads.toString());
					System.out.println("=================================");
					Cust_param cp = new Cust_param();
					//Read Excel
					String[][] data = new String[10][10];
					ExcelData er = new ExcelData();
					data = er.excelread("Cust_Param_Result","AllParams");
					Write_result wrResult = new Write_result();

					
					for(int filln = 1;filln<=43;filln++){
						wrResult.WriteResult("AllParams","n",filln,2);
						wrResult.WriteResult("AllParams","n",filln,3);
					}

					for(String pubreq2:pubads){
						String str[] = pubreq2.split("&");
						
						for(String pubreq3:ads){
							String str1[] = pubreq3.split("&");
							for (String ssss : str) {
								String s[] = ssss.split("=");
							
								for (String sss : str1) {
									String s1[] = sss.split("=");
									if(s[0].equals(s1[0])){
										
										if(s[1].equals(s1[1])){
											System.out.println("Pubad Param :"+s[0] +"-----"+ "Pubad Value :"+s[1]);
											System.out.println("ad Param :"+s1[0] +"-----"+ " ad Value :"+s1[1]);
											System.out.println("Values are matched ");
											for(int paramtot =1;paramtot<=43;paramtot++){
												//System.out.println("Exceldata is :"+data[paramtot][0].toString());
												if(s[0].equals(data[paramtot][0])){
													if(data[paramtot][1].toString().contains(s1[1])){
													wrResult.WriteResultAllParams("AllParams",s1[1].toString(),"Passed",paramtot,2,3);
													cp.Param_val = "Pass";
													
													break;
													}else
													{
														wrResult.WriteResultAllParams("AllParams",s1[1].toString(),"MisMatched",paramtot,2,3);
														cp.Param_val = "Fail";
														break;
													}
												}
												
											}
											
											//cp.Param_val = "Pass";
											
											System.out.println("==================================");
										}else{
											System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
											System.out.println("Pubad Param :"+s[0] +"-----"+ "Pubad Value :"+s[1]);
											System.out.println("ad Param :"+s1[0] +"-----"+ " ad Value :"+s1[1]);
											System.out.println("Values are not matched");
											for(int paramtot =1;paramtot<=43;paramtot++){
												if(s[0].equals(data[paramtot][0])){
													wrResult.WriteResultAllParams("AllParams",s1[1].toString(),"Failed",paramtot,2,3);
													cp.Param_val = "Fail";
													break;
												}
												
											}
											
											System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
										}
									}else{
										
									}
								}
							}
						}
					}
				//}
			}





		}
		else{
			System.out.println("bb ad call is not pressent");
			Assert.fail("bb ad call is not pressent");
		}


;
	}
	//Verify Cust_Params from Pubad

	public static void verifyParamsFromPubadCal_CustParam(String Excelname,String sheetName) throws Exception{
		//container=null;
		readExcelValues.excelValues(Excelname,sheetName);
		VerifypubadValues =readExcelValues.data[16][Cap];
		System.out.println("Pubad values are -"+VerifypubadValues);
		for(String pubreq2:pubads){
			String str[] = pubreq2.split("&");
			if(readExcelValues.data[16][Cap].contains(",")){
				String[] splitPubvalues= VerifypubadValues.split(",");
				for(int i=0;i<=splitPubvalues.length-1;i++){
					System.out.println("Pubad value is -"+splitPubvalues[i].toString()+"----Size is"+splitPubvalues.length);
					for (String ssss : str) {
						String s[] = ssss.split("=");
						int sheetVal;
						if(sheetName=="readTruboApi"){
							sheetVal=1;
						}else{
							sheetVal=2;
						}

						if (s[0].equals(splitPubvalues[i].toString())) {
							if(i>=0){
								if(sheetVal==1){
									AdParams = s[1].toString();
									System.out.println(splitPubvalues[i]  +" Param value is :" + AdParams);
									advalues.add(AdParams);
									Thread.sleep(1000);
									System.out.println("all ad Param value is :" + advalues);

								}else
								{

									AdParams = s[1].toString();
									System.out.println(splitPubvalues[i]  +" Param value is :" + AdParams);
									pubvalues.add(AdParams);
									Thread.sleep(1000);
									System.out.println("all ad Param value is :" + pubvalues);
								}

							}else if(s[0].equals("cxtg"))
							{

								seg = s[1].toString();
								System.out.println("seg is :"+seg);
								seg=seg.replaceAll(",",", ");
								cxtgcontainer.add(seg);
								System.out.println("CXTG Container is:" + cxtgcontainer);

							}else if(s[0].equals("wfxtg"))
							{

								seg = s[1].toString();
								System.out.println("seg is :"+seg);
								seg=seg.replaceAll(",",", ");
								wfxtgcontainer.add(seg);
								System.out.println("WFXTG Container is:" + wfxtgcontainer);

							}else
							{
								seg = s[1].toString();
								String[] items = seg.split(",");
								container = Arrays.asList(items);
								System.out.println("Container is:" + container);
								System.out.println("Container size is:" + container.size());
							}
						}
					}
				}

			}
			else
			{
				for (String ssss : str) {
					String s[] = ssss.split("=");

					if (s[0].equals(VerifypubadValues.toString())) {
						firstParamValue = s[1].toString();
						System.out.println(VerifypubadValues +"Param value is :" + firstParamValue);
					}

				}

			}
		}
	}

	//read DSX Call from Logs
	public static void readDSX_call(int feed,String Pagename1,String Pagename2) throws Exception{

		readExcelValues.excelValues("Cust_Param",Pagename1);
		Functions.verifyPubadCal_CustParam(1,"Cust_Param",Pagename1);

		readExcelValues.excelValues("Cust_Param",Pagename2);
		Functions.verifyPubadCal_CustParam(feed,"Cust_Param",Pagename2);

	}


	public static void main(String[] args) throws Exception
	{
//		Functions.startCharlesSession();
//		Functions.charles_Stop();
//		//STR
////		Functions.uninstallApp();
////		Functions.installApp();
//		
//		Functions.Appium_Autostart();
//		Functions.delete_folder();
//		Functions.clear_session();
//		Functions.capabilities();
//		Functions.launchtheApp();
//		Functions.scroll_Down();
//		Functions.downloadXMLFile();
		Functions.readXML();
		//readExcelValues.excelValues("Cust_Param","readDSX(MOData)");
		Functions.readDSX_call(5,"readTruboApi","readPubads");
	}
}