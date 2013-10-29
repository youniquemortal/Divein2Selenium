package com.cigniti.utilities.java;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.cigniti.utilities.java.HtmlReporters;
import com.cigniti.utilities.java.Property;
import com.experitest.client.Client;

public class ExpireSuit  {
	
	public static Property configProps=new Property("config.properties");
	
    public static String host = configProps.getProperty("host");
    public static int port = Integer.parseInt(configProps.getProperty("port"));
    public String projectBaseDirectory = configProps.getProperty("projectBaseDirectory");
    public  String activeDevice ="";
    
    public  static Client client = new Client(host, port);
    
	
    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Throwable{
    	
    	if(configProps.getProperty("Device").equals("iphone"))
    	{
    		activeDevice="ios:usb:iphone";
    	}
    	else if(configProps.getProperty("Device").equals("android"))
    	{
    		activeDevice="adb:android";
    	}
    	 client.setApplicationTitle(activeDevice);
    	 client.setReporter("xml", "reports");
      
    }
  
    @AfterSuite(alwaysRun = true)
	public void tearDown() throws Throwable {
        
    	System.out.println("--Summary Path::"+configProps.getProperty("SummaryPath"));
		HtmlReporters.createHtmlSummaryReport();
		
		
    }
    

}
