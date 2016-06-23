package Validations;

import com.Custom_Parameter.CustParam_Params;
import com.Custom_Parameter.Cust_param;
import com.Genaral.Driver;
import com.weather.excel.ExcelData;
import com.weather.excel.Write_result;

public class Custparam_floc extends Driver{

	
	int floc;
	public static String Result =null;
	public void floc() throws Exception{


        
		String flocvalue =	CustParam_Params.flocvalue1;
				//Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell[2]/UIATableView[1]/UIATableCell[2]/UIAStaticText[2]").getText();
		int floc = Integer.parseInt(flocvalue);
				System.out.println("int floc is ::"+floc );
		System.out.println("floc value is ::"+flocvalue);
		Cust_param cp = new Cust_param();
		if(cp.ParamType.equals("floc")){

			//Read Excel
			String[][] data = new String[10][10];
			ExcelData er = new ExcelData();
			data = er.excelread("Cust_Param_Result","floc");
          
				Write_result wrResult = new Write_result();
				for(int filln = 1;filln<=3;filln++){
					wrResult.WriteResult("floc","n",filln,4);
				}
			//	String v1=data[1][2].toString();
				int val1 = Integer.parseInt(data[1][2]);
				int val2 = Integer.parseInt(data[2][2]);
				int val3 = Integer.parseInt(data[3][2]);
               // int val3 = Integer.parseInt(data[3][2]);
				if(floc<=val2 && floc >=val1){
					
					System.out.println("param limit is ::"+val2);
					System.out.println("Param Value is ::"+ data[2][3]);

					wrResult.WriteResult("floc",flocvalue,2,4);
					cp.Param_val = "Pass";
					

				}else if(floc<val1){
					
					System.out.println("param limit is ::"+val1);
					System.out.println("Param Value is ::"+ data[1][3]);

					wrResult.WriteResult("floc",flocvalue,1,4);
					cp.Param_val = "Pass";

					
				}else if(floc>=val3){
					
					System.out.println("param limit is ::"+val3);
					System.out.println("Param Value is ::"+ data[3][3]);

					wrResult.WriteResult("floc",flocvalue,3,4);
					cp.Param_val = "Pass";

					
				}else
				{
					wrResult.WriteResult("floc","Nodata",1,4);
					cp.Param_val = "Fail";
					
				}
			



		}

	}
}
