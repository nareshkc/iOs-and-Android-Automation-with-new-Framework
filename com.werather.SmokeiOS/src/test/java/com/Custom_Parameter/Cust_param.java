package com.Custom_Parameter;


import io.appium.java_client.MobileElement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.jetty.html.Page;
import org.openqa.selenium.JavascriptExecutor;

import Validations.Custparam_UV;
import Validations.Custparam_cnd;
import Validations.Custparam_fhic;
import Validations.Custparam_floc;
import Validations.Custparam_fltmpc;
import Validations.Custparam_hmid;
import Validations.Custparam_sev;
import Validations.Custparam_st;
import Validations.Custparam_tmp;
import Validations.Custparam_tmpr;
import Validations.Custparam_wind;

import com.Functions.Functions;
import com.Genaral.Driver;
import com.Genaral.readExcelValues;
import com.weather.excel.ExcelData;
import com.weather.excel.WriteResultintoExcel;

public class Cust_param extends Driver {

	public static String ParamValue =null;
	public static String ParamType = null;
	public static String Param_val = null;
	public void custparam() throws Exception{



		System.out.println("Case Started");

		//	//Ad.resetApp();

		//Verify File Exist or not
		String originalContext = Ad.getContext();
		Ad.context("NATIVE_APP");
		Thread.sleep(2000);
		//	NavigatetoNowPage
		readExcelValues.excelValues("Cust_Param","General");
		//Finding Screen size for scroll
		MobileElement Screen = (MobileElement) Ad.findElementByXPath(readExcelValues.data[2][1]);

		List<MobileElement> pages = Screen.findElementsByClassName(readExcelValues.data[3][1]);
		System.out.println("page Size is "+ pages.size());

		readExcelValues.excelValues("Cust_Param","NavigatetoNowPage");
		//Call Capabilities
//		MobileElement el = (MobileElement) Ad.findElementByXPath(readExcelValues.data[1][1]);
//		el.click();
		Functions.scroll_Down();

		Functions.downloadXMLFile();

		CustParam_Params cpp = new CustParam_Params();
		cpp.params("Values");
		Functions.readXML();

		Map<String, String> mapkeys = new HashMap<String, String>();
		//Verifying feed data in loop
		for(int feed =1;feed<=5;feed++){
			System.out.println("Requesting ad: /7646/app_iphone_us/display/feed/feed_"+feed);
			readExcelValues.excelValues("Cust_Param","General");
			if(Functions.sb.toString().contains(readExcelValues.data[5][1]+feed)){

				Functions.verifyPubadCal_params("Smoke",feed, "AllFeeds");

				String Content = Functions.pubreq1;
				System.out.println("Content is ;"+Content.toString());
				System.out.println("pubads is :"+Functions.pubads);
				//Read Excel
				String[][] data = new String[10][10];
				ExcelData er = new ExcelData();
				data = er.excelread("Cust_Param_Result","SMOKE");

				for(String pubreq2:Functions.pubads){
					String str[] = pubreq2.split("&");
				}

				String[] arrays = Content.split("&");


				// verifying and validating Test Cases using Excel data
				for(int testcase=1;testcase<=44;testcase++)
				{
					Param_val=null;
					String param = data[testcase][4].toString();
					System.out.println("The param is :"+ param);
					// Loop to read all lines one by one from file and print It.

					// return mbrLastName;// Getting ord value from Cust_param
					System.out.println("Verifing the "+Content);
					for(String keys : arrays){
						System.out.println(keys);
						if(keys.contains("=")){
							String[] key = keys.split("=");
							// System.out.println(key[0] + "---"+key[1]);
							mapkeys.put(key[0], key[1]);

							//Verifying parameters from Excel
							if (param.trim().equals(key[0].trim())) {
								String ExactValue = key[key.length - 1];
								System.out.println("The value is :" + ExactValue);
								ParamType=key[0].trim();
								ParamValue=key[1].trim();
								System.out.println("Param Type is ::"+ParamType);
								System.out.println("Param Value is :;"+ParamValue);


								//************Validation for tmp param********//
								if(ParamType.equals("tmp"))
								{
									Custparam_tmp ctp = new Custparam_tmp();
									ctp.tmp();
								}else

									//************Validation for tmpr param*******//
									if(ParamType.equals("tmpr"))
									{
										Custparam_tmpr ctpr = new Custparam_tmpr();
										ctpr.tmpr();
									}else

										//************Validation for hmid param*******//
										if(ParamType.equals("hmid"))
										{
											Custparam_hmid hmid = new Custparam_hmid();
											hmid.hmid();
										}else

											//************Validation for hmid param*******//
											if(ParamType.equals("wind"))
											{
												Custparam_wind wind = new Custparam_wind();
												wind.wind();
											}else

												//************Validation for UV param*******//
												if(ParamType.equals("uv"))
												{
													Custparam_UV uv = new Custparam_UV();
													uv.uv();
												}else
													//************Validation for sev param*******//
													if(ParamType.equals("sev"))
													{
														Custparam_sev sev = new Custparam_sev();
														sev.sev();
													}else
														//************Validation for fltmpc param*******//
														if(ParamType.equals("fltmpc"))
														{
															Custparam_fltmpc fltmpc = new Custparam_fltmpc();
															fltmpc.fltmpc();
														}else
															//************Validation for floc param*******//
															if(ParamType.equals("floc"))
															{
																Custparam_floc floc = new Custparam_floc();
																floc.floc();
															}else
																//************Validation for fhic param*******//
																if(ParamType.equals("fhic"))
																{
																	Custparam_fhic fhic = new Custparam_fhic();
																	fhic.fhic();
																}
																else
																	//************Validation for cnd param*******//
																	if(ParamType.equals("cnd"))
																	{
																		Functions.readDSX_call(feed,"readTruboApi","readPubads");
																	}
								//																else
								//																	//************Validation for st param*******//
								//																	if(ParamType.equals("st"))
								//																	{
								//																		Custparam_st st = new Custparam_st();
								//																		st.st();
								//																	}

								//Functions.readDSX_call("readTruboApi","readPubads");
								//Write results into Excel
								WriteResultintoExcel wResult = new WriteResultintoExcel();

								int Getresult = feed*2;
								//Change values for entering result into all the feeds
								int ResultColumn_1=7+Getresult;
								int ResultColumn_2=8+Getresult;

								//Validating Parameters 
								System.out.println("Param_Val is::"+Param_val);
								if(ParamType==data[testcase][4])
								{
									if(Param_val.equals("Pass")){
										//If Value Size is NA
										if(data[testcase][6].contains("NA"))
										{
											if (data[testcase][8].contains(ExactValue.toString())&&ExactValue!="") {
												System.out.println("NO Values found for "+param);
												wResult.enterResult("SMOKE", "Fail", ExactValue, testcase, ResultColumn_1, ResultColumn_2);
											}else if(data[testcase][7].contains(ExactValue)||data[testcase][5].contains("Fixed")){
												System.out.println(param+" vales is :" + keys.contains(data[testcase][4].toString()));
												System.out.println(keys);
												wResult.enterResult("SMOKE", "Pass", ExactValue, testcase, ResultColumn_1, ResultColumn_2);
											}

											break;
										}


										//Verify Value Size is not NA data
										if(!data[testcase][6].contains("NA")){
											System.out.println("Exact Value is "+ ExactValue + " Data in Excel " + data[testcase][8]+" Size is : "+ExactValue.length());
											if (data[testcase][8].contains(ExactValue)&&(ExactValue.length() !=data[testcase][6].length())&&ExactValue!="") {
												System.out.println("NO Values found for "+param);
												wResult.enterResult("SMOKE", "Fail", ExactValue, testcase, ResultColumn_1, ResultColumn_2);

											} else

												if(data[testcase][7].contains(ExactValue)||data[testcase][5].contains("Not Fixed")){
													System.out.println(data[testcase][4]+" vales is :" + keys.contains(param));
													System.out.println(keys);
													wResult.enterResult("SMOKE", "Pass", ExactValue, testcase, ResultColumn_1, ResultColumn_2);
												}else if(data[testcase][7].contains(ExactValue)||data[testcase][5].contains("Fixed")){
													System.out.println(data[testcase][4]+" vales is :" + keys.contains(param));
													System.out.println(keys);
													wResult.enterResult("SMOKE", "Pass", ExactValue, testcase, ResultColumn_1, ResultColumn_2);
												}

										}
									}else if(Param_val.equals("Fail")){
										System.out.println("Values are not matched");
										wResult.enterResult("SMOKE", "Not matched", ExactValue, testcase, ResultColumn_1, ResultColumn_2);
										break;
									}
								}else if(ParamType!=data[testcase][4])
								{
									if(data[testcase][6].contains("NA"))
									{
										if (data[testcase][8].contains(ExactValue.toString())&&ExactValue!="") {
											System.out.println("NO Values found for "+param);
											wResult.enterResult("SMOKE", "Fail", ExactValue, testcase, ResultColumn_1, ResultColumn_2);
										}else if(data[testcase][7].contains(ExactValue)||data[testcase][5].contains("Fixed")){
											System.out.println(param+" vales is :" + keys.contains(data[testcase][4].toString()));
											System.out.println(keys);
											wResult.enterResult("SMOKE", "Pass", ExactValue, testcase, ResultColumn_1, ResultColumn_2);
										}

										break;
									}


									//Verify Value Size is not NA data
									if(!data[testcase][6].contains("NA")){
										System.out.println("Exact Value is "+ ExactValue + " Data in Excel " + data[testcase][8]+" Size is : "+ExactValue.length());
										if (data[testcase][8].contains(ExactValue)&&(ExactValue.length() !=data[testcase][6].length())&&ExactValue!="") {
											System.out.println("NO Values found for "+param);
											wResult.enterResult("SMOKE", "Fail", ExactValue, testcase, ResultColumn_1, ResultColumn_2);

										} else

											if(data[testcase][7].contains(ExactValue)||data[testcase][5].contains("Not Fixed")){
												System.out.println(data[testcase][4]+" vales is :" + keys.contains(param));
												System.out.println(keys);
												wResult.enterResult("SMOKE", "Pass", ExactValue, testcase, ResultColumn_1, ResultColumn_2);
											}else if(data[testcase][7].contains(ExactValue)||data[testcase][5].contains("Fixed")){
												System.out.println(data[testcase][4]+" vales is :" + keys.contains(param));
												System.out.println(keys);
												wResult.enterResult("SMOKE", "Pass", ExactValue, testcase, ResultColumn_1, ResultColumn_2);
											}

									}
								}


							}


						}
					}
				}
			}	else{
				System.out.println("Feed not found");
			}
		}



		System.out.println("Case Ended");
	}

}

