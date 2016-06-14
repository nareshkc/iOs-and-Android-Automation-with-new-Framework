package com.Genaral;

import com.weather.excel.ExcelData;

public class readExcelValues {
	 public static String[][] data =null;
	 public static String[][] nextdata =null;
	public static void excelValues(String Excelname,String Sheetname) throws Exception{
		 data = new String[10][10];
		ExcelData er = new ExcelData();
		data = er.excelread(Excelname, Sheetname);
        //System.out.println("data is :"+ data);
	}
	
//	public static void excelValuesnextSheet(String nextSheetname) throws Exception{
//		nextdata = new String[10][10];
//		ExcelData er = new ExcelData();
//		nextdata = er.excelread(nextSheetname, nextSheetname);
//       //System.out.println("data is :"+ data);
//	}

}
