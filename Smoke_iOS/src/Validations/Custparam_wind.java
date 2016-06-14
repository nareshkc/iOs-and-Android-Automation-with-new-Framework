package Validations;

import com.Custom_Parameter.CustParam_Params;
import com.Custom_Parameter.Cust_param;
import com.Genaral.Driver;
import com.weather.excel.ExcelData;
import com.weather.excel.Write_result;

public class Custparam_wind extends Driver {

	public static String Result =null;
	int wind;
	public void wind() throws Exception{
		//public static void main(String[] args) throws Exception {

		//String windvalue = "s 5 MPH";
		String windvalue =	CustParam_Params.windvalue2;
		Cust_param cp = new Cust_param();
				//Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell[2]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]").getText();
	if(windvalue.contains("MPH")){
		
		windvalue = windvalue.replace(" MPH", "");
		String windvalue1[]=windvalue.split(" ");
		windvalue = windvalue1[1].toString();

		System.out.println("wind value is ::"+windvalue);
		wind = Integer.parseInt(windvalue);
		
		System.out.println("wind values is ::"+wind);
		System.out.println("Actual wind is :: "+wind);
	}else
	{
		windvalue = CustParam_Params.windvalue2;
	}
	
		if(cp.ParamType.equals("wind")){

			//Read Excel
			String[][] data = new String[10][10];
			ExcelData er = new ExcelData();
			data = er.excelread("Cust_Param_Result","wind");

			Write_result wrResult = new Write_result();
			//fill n in last column
			for(int filln = 1;filln<=4;filln++){
				wrResult.WriteResult("wind","n",filln,4);
			}
			
			
			int	val1= Integer.parseInt(data[1][2].trim());
			int val2 = Integer.parseInt(data[2][2].trim());
			int val3 = Integer.parseInt(data[3][2].trim());
			//int val4 = Integer.parseInt(data[4][2].trim());
			
			if(wind<val1){

				System.out.println("param limit is ::"+val1);
				System.out.println("Param Value is ::"+ data[1][3]);

				wrResult.WriteResult("wind",windvalue,1,4);
				cp.Param_val = "Pass";


			}else if(wind>=val1 && wind<val2){

				System.out.println("param limit is ::"+val2);
				System.out.println("Param Value is ::"+ data[2][3]);

				wrResult.WriteResult("wind",windvalue,2,4);
				cp.Param_val = "Pass";

			}else if(wind>=val3){

				System.out.println("param limit is ::"+val3);
				System.out.println("Param Value is ::"+ data[3][3]);

				wrResult.WriteResult("wind",windvalue,3,4);

				cp.Param_val = "Pass";
			}else if(!windvalue.contains("MPH")||windvalue==""){

				System.out.println("param limit is ::"+data[4][2]);
				System.out.println("Param Value is ::"+ data[4][3]);

				wrResult.WriteResult("wind","nl",3,4);

				cp.Param_val = "Pass";
			}else
			{
				wrResult.WriteResult("wind","No data",3,4);
				cp.Param_val = "Fail";
			}





		}

	}
}
