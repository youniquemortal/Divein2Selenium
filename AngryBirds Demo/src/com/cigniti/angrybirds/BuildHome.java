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
public class BuildHome {
    
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

    @Test(groups = {"seetest"})
    public void testscenario1() throws Throwable{
    		//Set the device
	        client.setApplicationTitle(configProps.getProperty("Device"));
	        
	        //Set the application for launch
	        client.launch(configProps.getProperty("launch"), true, true);
	        if(client.waitForElement("default", "Loading", 0, 300000)){
	        	Reporters.report("Launch Application Dragon of Atlantis","Application Launched", true, this.getClass().getName());
	        }
	        else
	        {
	        	Reporters.report("Launch Application Dragon of Atlantis","Application Launch:Failed", false, this.getClass().getName());
	        	isScriptFail=true;
				return;	        	
	        }
	        //Check if the network error is displayed
	        if(client.waitForElement("default", "Retry", 0, 1000))
	        {
	        	Reporters.report("Check for network connectivity error","Network connectivity error:Check failed", false, this.getClass().getName());
	        	isScriptFail=true;
	        	//Close the application
	        	client.applicationClose(configProps.getProperty("applicationClose"));
				return;	 
	        }
	        //Wait for the Signing in with Google
	        if(client.waitForElement("default", "Signing in with Google", 0, 100000)){
	        	Reporters.report("Verify that the application signs in with google","Application signing in", true, this.getClass().getName());
	        }
	        else
	        {
	        	Reporters.report("Verify that the application signs in with google","Application Sign in:Failed", true, this.getClass().getName());
	        	isScriptFail=true;
				return;	        	
	        }
	        
	        //Click on the screen to close Signing in with Google box
	        client.clickCoordinate(20, 20, 1);
	        //Click on Close for the offer
	        if(client.waitForElement("default", "Buy Now", 0, 10000))
	        {
	        	client.click("default", "Close", 0, 1);
	        	Reporters.report("Click on close for the offer","Offer closed", true, this.getClass().getName());
	        }
	        //Verify that the game screen is displayed
	        if(client.waitForElement("default", "Block", 0, 10000))
	        {
	        	Reporters.report("Verify that the game screen is displayed","Game screen displayed", true, this.getClass().getName());
	        }
	        else
	        {
	        	Reporters.report("Verify that the game screen is displayed","Game screen display:Failed", false, this.getClass().getName());
	        	isScriptFail=true;
				return;	
	        }
	        //Click on a block and build a home
	        for(int iBlocks=0;iBlocks<2;iBlocks++)
	        {
	        	client.click("default", "Block", 0, 1);
	        	
	        	//Select Home from the window
	        	client.click("TEXT", "Home", 0, 1);
	        	
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
	        	//Wait for the construction to finish, repeat it twice
	        	if(client.waitForElement("default", "Start", 0, 300000))
	        	{
	        		Reporters.report("Verify that building the Home is complete","Building home complete", true, this.getClass().getName());
	        	}
	        	else
	        	{
	        		Reporters.report("Verify that building the Home is complete","Building Home:Failed", false, this.getClass().getName());
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
