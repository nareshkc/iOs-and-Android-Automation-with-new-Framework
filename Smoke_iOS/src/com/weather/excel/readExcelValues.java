package com.weather.excel;

import com.weather.excel.ExcelData;

public class readExcelValues {
	 public static String[][] data =null;
	 public static String[][] nextdata =null;
	public static void excelValues(String Sheetname) throws Exception{
		 data = new String[10][10];
		ExcelData er = new ExcelData();
		data = er.excelread(Sheetname);
        //System.out.println("data is :"+ data);
	}
	
	public static void excelValuesnextSheet(String nextSheetname) throws Exception{
		nextdata = new String[10][10];
		ExcelData er = new ExcelData();
		nextdata = er.excelread(nextSheetname);
       //System.out.println("data is :"+ data);
	}

}
