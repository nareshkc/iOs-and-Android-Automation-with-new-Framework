package Validations;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.Custom_Parameter.CustParam_Params;
import com.Custom_Parameter.Cust_param;
import com.Genaral.Driver;
import com.weather.excel.ExcelData;
import com.weather.excel.Excelread_Validation;
import com.weather.excel.WriteResultintoExcel;
import com.weather.excel.Write_result;


public class Custparam_tmp extends Driver{
	String tmp_val=null;
	public static String Result =null;
	
	public void tmp() throws Exception{
		
		tmp_val=CustParam_Params.tmp_val2;
		Cust_param cp = new Cust_param();
		//System.out.println("tmp value is ::"+tmp_val);
		//System.out.println("Param Type is ::"+cp.ParamType);
		String ActualTemp = tmp_val.replace("°", "");
		System.out.println("Actual Temp is :: "+ActualTemp);
		cp.Param_val = "Fail";
		int tempval=Integer.parseInt(ActualTemp);
		if(cp.ParamType.equals("tmp")){

			//Read Excel
			String[][] data = new String[10][10];
			ExcelData er = new ExcelData();
			data = er.excelread("Cust_Param_Result","tmp");
			
			Write_result wrResult = new Write_result();
			
			//fill n in last column
			for(int filln = 1;filln<=18;filln++){
				wrResult.WriteResult("tmp","n",filln,3);
			}
			
			
			if(ActualTemp.contains("-") ||tempval>100 ){
				
				System.out.println("param limit is ::"+data[1][1]);
				System.out.println("Param Value is ::"+ data[1][2]);

				wrResult.WriteResult("tmp",tmp_val,1,3);
				cp.Param_val = "Pass";
				
		}else{
			for(int i =1;i<=18;i++){

				if(data[i][1].contains(ActualTemp)){
					System.out.println("param limit is ::"+data[i][1]);
					System.out.println("Param Value is ::"+ data[i][2]);

					wrResult.WriteResult("tmp",tmp_val,i,3);
					cp.Param_val = "Pass";
					break;

				}else 
					{
						System.out.println("Element not found");
						wrResult.WriteResult("tmp",tmp_val,18,3);
						
					}
			}
				}
			}



		}

	}

//}
