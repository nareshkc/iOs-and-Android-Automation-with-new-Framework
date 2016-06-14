package com.weather.excel;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.Genaral.Driver;
import com.Genaral.PropertyFile;



public class ExcelData extends Driver {

    public String[][] excelread(String ExcelName,String Type) throws Exception {
    	
    	Driver.property();
		PropertyFile.property();
		String TestSheetName = null;
		
		if(ExcelName.equals("Smoke"))
		{
			TestSheetName = "ExcelFilePath";
		}else if(ExcelName.equals("Cust_Param")){
			TestSheetName = "ExcelFilePath_CustParam";
		}else if(ExcelName.equals("Cust_Param_Result")){
			TestSheetName = "ExcelFilePath_CustParam_Result";
		}
		
	File f = new File(properties.getProperty(TestSheetName));
	FileInputStream fis = new FileInputStream(f);
	HSSFWorkbook wb = new HSSFWorkbook(fis);
	HSSFSheet ws = wb.getSheet(Type);

	int rownum = ws.getLastRowNum() + 1;
	int colnum = ws.getRow(0).getLastCellNum();
	String data[][] = new String[rownum][colnum];

	for (int i = 0; i < rownum; i++) {
	    HSSFRow row = ws.getRow(i);

	    for (int j = 0; j < colnum; j++) {
		HSSFCell cell = row.getCell(j);
		String value = CellToString.ctos(cell);
		data[i][j] = value;
		//System.out.println("Values are :" + value + " : data[" + i + "][" + j + "]");

	    }
	}
	// wb.close();
	return data;

    }
    
//    public String[][] excelread_CustParam(String Type) throws Exception {
//    	
//    	Driver.property();
//		PropertyFile.property();
//	File f = new File(properties.getProperty("ExcelFilePath_CustParam"));
//	FileInputStream fis = new FileInputStream(f);
//	HSSFWorkbook wb = new HSSFWorkbook(fis);
//	HSSFSheet ws = wb.getSheet(Type);
//
//	int rownum = ws.getLastRowNum() + 1;
//	int colnum = ws.getRow(0).getLastCellNum();
//	String data[][] = new String[rownum][colnum];
//
//	for (int i = 0; i < rownum; i++) {
//	    HSSFRow row = ws.getRow(i);
//
//	    for (int j = 0; j < colnum; j++) {
//		HSSFCell cell = row.getCell(j);
//		String value = CellToString.ctos(cell);
//		data[i][j] = value;
//		//System.out.println("Values are :" + value + " : data[" + i + "][" + j + "]");
//
//	    }
//	}
//	// wb.close();
//	return data;
//
//    }

}
