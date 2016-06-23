package Validations;

import io.appium.java_client.MobileElement;

import com.Custom_Parameter.CustParam_Params;
import com.Custom_Parameter.Cust_param;
import com.Genaral.Driver;
import com.weather.excel.ExcelData;
import com.weather.excel.Write_result;

public class Custparam_st extends Driver{

	public void st() throws Exception{

		String stvalue =	CustParam_Params.stvalue1;
		//Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell[2]/UIATableView[1]/UIATableCell[2]/UIAStaticText[2]").getText();
		String[]St=stvalue.split(",");
		
		String st_value = St[1].toString();
		
		System.out.println("int st is ::"+st_value );
		
		Cust_param cp = new Cust_param();
		if(cp.ParamType.equals("st")){

			//Read Excel
			String[][] data = new String[10][10];
			ExcelData er = new ExcelData();
			data = er.excelread("Cust_Param_Result","st");

			Write_result wrResult = new Write_result();

			for(int filln = 1;filln<=1;filln++){
				wrResult.WriteResult("st","n",filln,4);
				wrResult.WriteResult("st","n",filln,3);
				wrResult.WriteResult("st","n",filln,2);
				wrResult.WriteResult("st",stvalue,filln,2);
				wrResult.WriteResult("st",st_value,filln,3);
			}

			//	String v1=data[1][2].toString();
			String val2 = data[1][3];
			String val1 = data[1][2];
			
			
			// int val3 = Integer.parseInt(data[3][2]);
			if(val1.contains(val2)){

				System.out.println("param limit is ::"+val2);
				System.out.println("Param Value is ::"+ data[1][3]);

				wrResult.WriteResult("st",st_value,1,4);
				cp.Param_val = "Pass";


			}else
			{
				wrResult.WriteResult("st","Nodata",1,4);
				cp.Param_val = "Fail";

			}




		}

	}


}
