package Validations;

import com.Custom_Parameter.CustParam_Params;
import com.Custom_Parameter.Cust_param;
import com.weather.excel.ExcelData;
import com.weather.excel.Write_result;

public class Custparam_tmpc {

	
	int tmpc;
	public static String Result =null;
	public void tmpc(String Path) throws Exception{


        
		String tmpcvalue =	CustParam_Params.tmpcvalue1;
				//Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell[2]/UIATableView[1]/UIATableCell[2]/UIAStaticText[2]").getText();
		int tmpc = Integer.parseInt(tmpcvalue);
				System.out.println("int tmpc is ::"+tmpc );
		System.out.println("tmpc value is ::"+tmpcvalue);
		Cust_param cp = new Cust_param();
		if(cp.ParamType.equals("tmpc")){

			//Read Excel
			String[][] data = new String[10][10];
			ExcelData er = new ExcelData();
			data = er.excelread("Cust_Param_Result","tmpc");
          
				Write_result wrResult = new Write_result();
				for(int filln = 1;filln<=3;filln++){
					wrResult.WriteResult("tmpc","n",filln,4);
				}
			//	String v1=data[1][2].toString();
				int val1 = Integer.parseInt(data[1][2]);
				int val2 = Integer.parseInt(data[2][2]);
				int val3 = Integer.parseInt(data[3][2]);
               
				if(tmpc<=val2 && tmpc >=val1){
					
					System.out.println("param limit is ::"+val2);
					System.out.println("Param Value is ::"+ data[2][3]);

					wrResult.WriteResult("tmpc",tmpcvalue,2,4);
					cp.Param_val = "Pass";
					

				}else if(tmpc<val1){
					
					System.out.println("param limit is ::"+val1);
					System.out.println("Param Value is ::"+ data[1][3]);

					wrResult.WriteResult("tmpc",tmpcvalue,1,4);
					cp.Param_val = "Pass";

					
				}else if(tmpc>=val3){
					
					System.out.println("param limit is ::"+val3);
					System.out.println("Param Value is ::"+ data[3][3]);

					wrResult.WriteResult("tmpc",tmpcvalue,3,4);
					cp.Param_val = "Pass";

					
				}else
				{
					wrResult.WriteResult("tmpc","Nodata",1,4);
					cp.Param_val = "Fail";
					
				}
			



		}

	}
}
