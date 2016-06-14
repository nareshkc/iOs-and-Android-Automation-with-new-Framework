package Validations;

import com.Custom_Parameter.CustParam_Params;
import com.Custom_Parameter.Cust_param;
import com.Functions.Functions;
import com.Genaral.Driver;
import com.Genaral.readExcelValues;
import com.weather.excel.ExcelData;
import com.weather.excel.Write_result;

public class Custparam_cnd extends Driver{

	public static void cnd() throws Exception{
		//VerifypubadValues
		if(Functions.VerifypubadValues.toString().contains("sky")){
			//Functions.readXML();
			//readExcelValues.excelValues("Cust_Param","readDSX(MOData)");
			Functions.readDSX_call("readDSX(MOData)");
		}
		String cndvalue =	CustParam_Params.cndValue1;
		System.out.println("cnd value is ::"+cndvalue);
		Cust_param cp = new Cust_param();
		if(cp.ParamType.equals("cnd")){

			//Read Excel
			String[][] data = new String[10][10];
			ExcelData er = new ExcelData();
			data = er.excelread("Cust_Param_Result","cnd");

			Write_result wrResult = new Write_result();

			for(int filln = 1;filln<=3;filln++){
				wrResult.WriteResult("cnd","n",filln,3);
			}

			for(int i=1;i<=11;i++){
				if(data[i][1].toString().contains(Functions.firstParamValue.toString())){
					
					System.out.println("param limit is ::"+data[i][1].toString());
					System.out.println("Param Value is ::"+ data[i][2]);

					wrResult.WriteResult("cnd",cndvalue,i,3);
					cp.Param_val = "Pass";
					break;

				}
				int test=1;
				test=test+i;
				if(test==12){
					wrResult.WriteResult("cnd","Nodata",i,3);
					cp.Param_val = "Fail";
				}
			}


		}
	}
}
