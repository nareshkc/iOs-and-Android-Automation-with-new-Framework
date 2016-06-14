package com.Appium;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

public class Start_Stop_AppiumServer  {
	
	private  Process process;  
	private  String APPIUMSERVERSTART = "/usr/local/bin/appium";

	public void startAppiumServer() throws IOException, InterruptedException { 
 
		CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");
		command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js", false);
		command.addArgument("--address", false);
		command.addArgument("127.0.0.1");
		command.addArgument("--port", false);
		command.addArgument("4723");
		//command.addArgument("--no-reset", false);
		command.addArgument("--log-level", true);
		command.addArgument("error");
		command.addArgument("--log");
		command.addArgument("/Users/aparna/Documents/sys113.log");

		
//		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
//		DefaultExecutor executor = new DefaultExecutor();
//		executor.setExitValue(1);
//		executor.execute(command, resultHandler);
		
		String[] str ={"/bin/bash", "-c", "/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js --address 127.0.0.1 --chromedriver-port 9517 --bootstrap-port 4721 --no-reset --local-timezone"};
		Process p = Runtime.getRuntime().exec(str);
		Thread.sleep(20000);
      
    }  
      
    public  void stopAppiumServer() throws IOException {  
        String[] command ={"/usr/bin/killall","-KILL","node"};  
        Runtime.getRuntime().exec(command);  
        //System.out.println("Appium server stop");  
    }  

}
