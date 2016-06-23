package Validations;

import com.Custom_Parameter.CustParam_Params;
import com.Custom_Parameter.Cust_param;
import com.Genaral.Driver;
import com.weather.excel.ExcelData;
import com.weather.excel.Write_result;

public class Custparam_UV extends Driver{
	int uv;
	public static String Result =null;
	public void uv() throws Exception{

		String uvvalue =	CustParam_Params.uvvalue2;
				//Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell[2]/UIATableView[1]/UIATableCell[5]/UIAStaticText[2]").getText();
		
		String uvvalue1[]=uvvalue.split(" ");
		uvvalue = uvvalue1[0].toString();
		System.out.println("UV value is ::"+uvvalue);
		uv = Integer.parseInt(uvvalue);
		Cust_param cp = new Cust_param();
		
		System.out.println("Actual UV is :: "+uv);
		if(cp.ParamType.equals("uv")){

			//Read Excel
			String[][] data = new String[10][10];
			ExcelData er = new ExcelData();
			data = er.excelread("Cust_Param_Result","uv");
          
				Write_result wrResult = new Write_result();
				
				//fill n in last column
				for(int filln = 1;filln<=2;filln++){
					wrResult.WriteResult("uv","n",filln,4);
				}
				
				
				int val1=Integer.parseInt(data[1][2]);
                int val2 = Integer.parseInt(data[2][2]);
               // int val3 = Integer.parseInt(data[3][2]);
				if(uv>=val2){
					
					System.out.println("param limit is ::"+val2);
					System.out.println("Param Value is ::"+ data[2][3]);

					wrResult.WriteResult("uv",uvvalue,2,4);
					cp.Param_val = "Pass";

				}else if(uv<val1 || uv==' '){
					
					System.out.println("param limit is ::"+val1);
					System.out.println("Param Value is ::"+ data[1][3]);

					wrResult.WriteResult("uv",uvvalue,1,4);
					cp.Param_val = "Pass";
					
				}else
				{
					wrResult.WriteResult("uv","No value found",1,4);
					cp.Param_val = "Fail";
				}
			



		}

	}


}
