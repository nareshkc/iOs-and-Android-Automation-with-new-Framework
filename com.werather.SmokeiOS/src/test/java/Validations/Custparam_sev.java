package Validations;

import com.Custom_Parameter.CustParam_Params;
import com.Custom_Parameter.Cust_param;
import com.Genaral.Driver;
import com.weather.excel.ExcelData;
import com.weather.excel.Write_result;

public class Custparam_sev extends Driver{

	int sev;
	public static String Result =null;

	public void sev() throws Exception{

		String sevvalue =	CustParam_Params.sevvalue1;
		//Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell[2]/UIATableView[1]/UIATableCell[2]/UIAStaticText[2]").getText();

		System.out.println("sev value is ::"+sevvalue);

		Cust_param cp = new Cust_param();
		cp.Param_val = "fail";
		if(cp.ParamType.equals("sev")){

			//Read Excel
			String[][] data = new String[10][10];
			ExcelData er = new ExcelData();
			data = er.excelread("Cust_Param_Result","sev");

			Write_result wrResult = new Write_result();
			
			//fill n in last column
			for(int filln = 1;filln<=7;filln++){
				wrResult.WriteResult("sev","n",filln,4);
			}
			

			for(int i=1;i<=7;i++){
				// int val3 = Integer.parseInt(data[3][2]);
				if(sevvalue.contains(data[i][2])){

					System.out.println("param limit is ::"+sevvalue);
					System.out.println("Param Value is ::"+ data[i][2]);

					wrResult.WriteResult("sev",sevvalue,i,4);
					cp.Param_val = "Pass";
					break;
				}else if(sevvalue.contains("No allert"))
				{
					System.out.println("param limit is ::"+sevvalue);
					System.out.println("Param Value is ::"+ data[6][2]);

					wrResult.WriteResult("sev",sevvalue,6,4);
					cp.Param_val = "Pass";
					break;
				}else if (i==7)
				{
					System.out.println("param limit is ::"+sevvalue);
					System.out.println("Param Value is ::"+ data[7][2]);

					wrResult.WriteResult("sev",sevvalue,7,4);
					cp.Param_val = "Pass";
				}

			}

		}

	}

}




