package twc.Automation.General;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import twc.Automation.Driver.Drivers;
import twc.Automation.ReadDataFromFile.read_excel_data;
import twc.Automation.ReadDataFromFile.read_xml_data_into_buffer;

public class Functions extends Drivers{
	
	public static void validate_API_Call_With_PubAds_Call(String excel_sheet_name) throws Exception{
		
		String apicall_results=null;
		String pubadscall_results=null;
		
		Map<String, String> api_call_results = read_API_Call_Data(excel_sheet_name);
		Map<String, String> pubads_call_results = read_Pub_Ad_Call_Data(excel_sheet_name);
		//System.out.println(api_call_results);
		//System.out.println(pubads_call_results);
		if(api_call_results.keySet().size() == 1){
			
			for (String key : api_call_results.keySet()) {
				//System.out.println("key: " + key + " value: " + api_call_results.get(key));
				apicall_results = api_call_results.get(key).toString().replace("[", "").replace("]", "");
				//System.out.println(apicall_results);
			}
			for (String pubkey : pubads_call_results.keySet()) {
				//System.out.println("key: " + pubkey + " value: " + pubads_call_results.get(pubkey));
				pubadscall_results = pubads_call_results.get(pubkey).toString().replace("[", "").replace("]", "");
				//System.out.println(pubadscall_results);
			}
			
			String[] pubadsresults = pubadscall_results.split(",");
			for(int i=0;i<pubadsresults.length;i++){
				if(apicall_results.contains(pubadsresults[i])){
					System.out.println("Matched With "+ pubadscall_results +" :::: " + pubadsresults[i]);
				}
				else{
					System.out.println("Does Not Matched With "+ pubadscall_results +" :::: " + pubadsresults[i]);
				}
			}

		}
		else{
			
			for (String key : api_call_results.keySet()) {
				//System.out.println("key: " + key + " value: " + api_call_results.get(key));
				apicall_results = api_call_results.get(key).toString().replace("[", "").replace("]", "");
				//ystem.out.println(apicall_results);
			}
			for (String pubkey : pubads_call_results.keySet()) {
				//System.out.println("key: " + pubkey + " value: " + pubads_call_results.get(pubkey));
				pubadscall_results = pubads_call_results.get(pubkey).toString().replace("[", "").replace("]", "");
				//System.out.println(pubadscall_results);
				
				String[] pubadsresults = pubadscall_results.split(",");
				//////////////////////////////////////////
				for(int i=0;i<pubadsresults.length;i++){
					if(!pubads_call_results.get(pubkey).equals("nl")){
						if(api_call_results.get(pubkey).contains(pubadsresults[i])){
							System.out.println("Matched With "+ pubads_call_results.get(pubkey) +" :::: " + pubadsresults[i]);
						}
						else{
							System.out.println("Does Not Matched With "+ pubads_call_results.get(pubkey) +" :::: " + pubadsresults[i]);
							Assert.fail("Does Not Matched With "+ pubads_call_results.get(pubkey) +" :::: " + pubadsresults[i]);
						}
					}
					else{
						System.out.println("Getting nl value for "+pubkey+" from pubads call");
					}
				}
			}
		}
	}
	public static Map<String , String>  read_API_Call_Data(String excel_sheet_name) throws Exception{
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		Map<String , String> expected_map_results = new HashMap<String, String>();
		ArrayList<String> expected_Values_List = new ArrayList<String>();
		
		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
		
		String validateValues = exceldata[11][Cap];
		String[] validate_Values = validateValues.split(",");
		
		
		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
		
		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[2][Cap]));
		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[3][Cap]));
		
		String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[4][Cap])+7,required_info.indexOf(exceldata[5][Cap]));
		String expectedValues = expected_data.toString();
		
		if(validate_Values.length == 1){
		
		if(expected_data.toString().contains(exceldata[11][Cap])){
			
			String expecteddata = expected_data.substring(expected_data.indexOf("[")+1,expected_data.indexOf("]")-1);
			System.out.println("Expected Data ::"+expecteddata);
			
			String[] expecteddata_into_arrays = expecteddata.split("},");
			String[] expectedValue = null;
			for(String dataKeys:expecteddata_into_arrays)
			{
				
				expectedValue =dataKeys.split(",");
				
				for(String ExpectedValuesKey:expectedValue)
				{
					if(ExpectedValuesKey.contains(exceldata[12][Cap]))
					{
						String replaceWith = ExpectedValuesKey.toString().replace("{", "").trim();
						
						String[] contentkey = replaceWith.toString().split(",");
						String expected_key = contentkey[0].replaceAll("^\"|\"$","");
						String[] contentvalue = expected_key.split(":");
						String expected_results =contentvalue[1].replaceFirst("^\"|\"$","");
						expected_Values_List.add(expected_results);
						if(expected_key.contains(""))
						{
							Assert.assertNotNull(expected_key);
						}
					}
				}
			}
		}
		expected_map_results.put(exceldata[12][Cap], expected_Values_List.toString());
		}
		else{
			
			String validateSecondValues = exceldata[12][Cap];
			String[] validate_Second_Values = validateSecondValues.split(",");
			List<String> fgeo_res = new ArrayList<String>();
			List<String> faud_res = new ArrayList<String>();
			
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(expectedValues);
			JSONObject jsonObject = (JSONObject) obj;
			
			JSONArray fgeoval = (JSONArray) jsonObject.get(validate_Values[0]);
			for(int i=0;i< fgeoval.size();i++){
				
				JSONObject filter = (JSONObject) fgeoval.get(i);
				if(filter.containsKey(validate_Second_Values[0])){
					fgeo_res.add(filter.get(validate_Second_Values[0]).toString());
				}
			}
			
			JSONArray faudval = (JSONArray) jsonObject.get(validate_Values[1]);
			for(int i=0;i< faudval.size();i++){
				
				JSONObject filter = (JSONObject) faudval.get(i);
				if(filter.containsKey(validate_Second_Values[1])){
					faud_res.add(filter.get(validate_Second_Values[1]).toString());
				}
			}
			
			expected_map_results.put("fgeo", fgeo_res.toString());
			expected_map_results.put("faud", faud_res.toString());
		}
		return expected_map_results;
	}
	
	public static Map<String , String> read_Pub_Ad_Call_Data(String excel_sheet_name) throws Exception{
		
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		Map<String , String> expected_results = new HashMap<String, String>();
		
		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
		
		String validateValues = exceldata[16][Cap];
		String[] validate_Values = validateValues.split(",");
		
		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
		
		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[17][Cap]));
		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[7][Cap]));
		
		required_info= required_info.toString().replaceAll(exceldata[8][Cap], "=");
		required_info= required_info.toString().replaceAll(exceldata[9][Cap], "&");
		required_info= required_info.toString().replaceAll(exceldata[10][Cap], ",");
		
		required_info = required_info.substring(required_info.indexOf(exceldata[14][Cap]),required_info.indexOf(exceldata[15][Cap]));
		
		
		String pubad_cust_params_data = required_info.toString();
		
		String[] pubadvalue = pubad_cust_params_data.split(exceldata[13][Cap]);
		
			for(String pubadkey:pubadvalue){
				
				String[] key = pubadkey.split("=");
				
				for(int i=0;i<validate_Values.length;i++){	
					
					if(key[0].equals(validate_Values[i])){
						expected_results.put(validate_Values[i], key[1].toString());
					}
				}
			}
		return expected_results;
	}
	
	public static void clean_App_Launch(String excel_sheet_name) throws Exception{
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		
		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
		
		String feedVal=exceldata[3][Cap].toString().trim();

		System.out.println("Feeds Val are :"+feedVal.trim());

		int feedcount=Integer.parseInt(feedVal);
		
		for(int Feed=0;Feed<=feedcount;Feed++){
			
			String pubadcal;

			if(Feed==0){
			pubadcal = sb.toString().substring(sb.toString().lastIndexOf(exceldata[1][Cap]));

				if(pubadcal.toString().contains(exceldata[1][Cap])){
					System.out.println("BB Ad call is pressent");
				}else{
					System.out.println("BB Ad call not presented");
					Assert.fail("BB Ad call not presented");
				}

			}
			else
			{
				String feedcall = exceldata[2][Cap]+Feed;
				pubadcal = sb.toString().substring(sb.toString().lastIndexOf(exceldata[2][Cap]+Feed));
				if(pubadcal.toString().contains(feedcall)){
					System.out.println("Feed_"+Feed +" Ad call is pressent");
				}else{
					System.out.println("Feed_"+Feed +" Ad call is not pressent");
					Assert.fail();
				}

			}
		}
	}
	
	public static void bb_call_validation(String excel_sheet_name) throws Exception{
		
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
		
		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[17][Cap]));
		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[17][Cap]));
		
		String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[14][Cap]),required_info.indexOf(exceldata[15][Cap]));
		String expectedValues = expected_data.toString();
		
		System.out.println("BB Call Value is : "+expectedValues);
		if(expectedValues.contains(exceldata[17][Cap])){
			System.out.println("BB Call generated");
		}
		else{
			System.out.println("BB Call not generated");
			Assert.fail("BB Call not generated ");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void thirdParty_beacons_validation(String excel_sheet_name) throws Exception{
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		Thread.sleep(4000);
		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
		
		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
		
		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[2][Cap]));
		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[2][Cap]));
		String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[2][Cap]),required_info.indexOf(exceldata[3][Cap]));
		String expectedValues = expected_data.toString();
		
		@SuppressWarnings("rawtypes")
		Map map = new HashMap();
		String[] keypairs = expectedValues.split(exceldata[4][Cap]);
		
		for (String keyvalue : keypairs)
		{
		    String[] key_value = keyvalue.split(exceldata[5][Cap]);
		    map.put(key_value[0], key_value[1]);
		}
		
		if(!empty(map.get(exceldata[6][Cap])) && !empty(map.get(exceldata[7][Cap])) && !empty(map.get(exceldata[8][Cap]))){
			System.out.println(exceldata[6][Cap]+" Value is "+map.get(exceldata[6][Cap]));
			System.out.println(exceldata[7][Cap]+" Value is "+map.get(exceldata[7][Cap]));
			System.out.println(exceldata[8][Cap]+" Value is "+map.get(exceldata[8][Cap]));
		}
		else{
			System.out.println(exceldata[1][Cap] +" not available.");
			Assert.fail(exceldata[1][Cap] +" not available.");
		}
		
	}
	private static boolean empty(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

}
