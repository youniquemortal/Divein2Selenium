package com.cigniti.angrybirds;

//package <set your test package>;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.cigniti.utilities.java.GenericUtils;
import com.cigniti.utilities.java.Property;
import com.cigniti.utilities.java.Reporters;
import com.experitest.client.Client;
/**
 *
 *
*/
public class BuildHomeWhenConInProgress {
    
    
    protected Client client = null;
    public static Property configProps=new Property("config.properties");
    GenericUtils action=new GenericUtils();
	boolean isScriptFail=false;
    @BeforeTest
    public void setUp(){
        client = new Client(configProps.getProperty("host"), Integer.parseInt(configProps.getProperty("port")));
        client.setProjectBaseDirectory( configProps.getProperty("projectBaseDirectory"));
        client.setReporter("xml", "reports");
    }

    @SuppressWarnings("deprecation")
	@Test(groups = {"seetest"})
    public void testscenario1() throws Throwable{
    		//Set the device
	        client.setApplicationTitle(configProps.getProperty("Device"));
	        
	        //Click on block to build something other than Home
	        client.click("default", "Block", 0, 1);
        	
        	//Select Home from the window
        	if(client.waitForElement("TEXT", "Garrison", 0, 5000))
        		client.click("TEXT", "Garrison", 0, 1);
        	else
        	{
        		Reporters.report("Click on the building to go to Build screen","Garrison option:Failed", false, this.getClass().getName());
	        	isScriptFail=true;
				return;	
        	}
        	
        	//Wait for the build button
        	if(!client.waitForElement("default", "Build", 0, 10000))
        	{
        		Reporters.report("Click on the building to go to Build screen","Build screen display:Failed", false, this.getClass().getName());
	        	isScriptFail=true;
				return;	
        	}
        	
        	//Click on Build
        	client.click("default", "Build", 0, 1);
        	
        	//Wait for the game screen to be displayed
        	if(!client.waitForElement("default", "Block", 0, 10000))
        	{
	        	Reporters.report("Verify that the game screen is displayed","Game screen display:Failed", false, this.getClass().getName());
	        	isScriptFail=true;
				return;	
	        }
        	
        	//Click on another block and verify that error is displayed
        	 client.click("default", "Block", 0, 1);
         	
         	
         	//Select to build home
         	client.click("TEXT", "Home", 0, 1);
         	
         	//Wait for the build button
        	if(!client.waitForElement("default", "Build", 0, 10000))
        	{
        		String error = client.getText("default");
        		if(error.contains("You cannot build more than one building at the same time"))
        		{
        			Reporters.report("Click on the building and verify that error is displayed that more than one building cannot be built at same time","Appropriate error is displayed", true, this.getClass().getName());
        		}
        		else
        		{
        			Reporters.report("Click on the building and verify that error is displayed that more than one building cannot be built at same time","Appropriate error not displayed:Failed", false, this.getClass().getName());
    	        	isScriptFail=true;
    				return;	
        		}
        		
        	}
        	
    	
    }
    @AfterTest
    public void tearDown(){
        client.generateReport();
    }
}
