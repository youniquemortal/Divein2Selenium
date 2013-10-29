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
public class Angrybirds {
    
    //private String projectBaseDirectory = "C:\\Users\\ctl-user\\Downloads\\GameAutomationDemo\\project2";
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

    @Test
    public void testscenario() throws Throwable{
    	//Set the device
    	 //client.setDevice("adb:xperia");
    	client.setApplicationTitle(configProps.getProperty("Device"));
    		//Swiping while element is found 
    	if(client.swipeWhileNotFound("Right", 0, 2000, "default", "AngryBirds_Icon", 1000, 7, true))    	    	    
    	 
    	 {
             Reporters.report("swiping while element is found","clicked on icon", true, this.getClass().getName());
    	 }
             else
     		{
     			Reporters.report("swiping while element is found","clicking on icon is:Failed", false, this.getClass().getName());
             	isScriptFail=true;
     			return;	
     		}
    	 
    	 // Make the screen as Landscape mode
        client.sendText("{Landscape}");
        // wait for Rio image while game is loading 
        if(client.waitForElement("default", "Rio-image", 0, 20000)){
        	Reporters.report("waiting some time for image","Image is exists", true, this.getClass().getName());
        }
        	else
        	{
        		Reporters.report("waiting some time for image","Image doesnot exists:Failed", false, this.getClass().getName());
             	isScriptFail=true;
     			return;
        	}
        // Verify the Image in screen
        client.verifyElementFound("default", "Rio-image", 0);
        //wait for the play icon
        if(client.waitForElement("default", "Play_Icon", 0, 10000)){
        	Reporters.report("waiting for the play icon","icon is exists", true, this.getClass().getName());
        }
        	else
        	{
        		Reporters.report("waiting for the play icon","Icon doesnot exists:Failed", false, this.getClass().getName());
             	isScriptFail=true;
     			return;
        	}
        // Clicking on Play_icon	
        client.longClick("default", "Play_Icon", 0, 1, 0, 0);
        //waiting for the smugler denimage
        if(client.waitForElement("default", "smugler_denimage", 0, 20000)){
        	Reporters.report("waiting for the smugler_denimage","Image is exists", true, this.getClass().getName());
        }
        	else
        	{
        		Reporters.report("waiting for the smugler_denimage","Image doesnot exists:Failed", false, this.getClass().getName());
             	isScriptFail=true;
     			return;
        	}

        // Click on the smuygler_denimage
        client.longClick("default", "smugler_denimage", 0, 1, 0, 0);
        //waiting for the Level Image
        if(client.waitForElement("default", "level_image", 0, 20000)){
        	Reporters.report("waiting for the Level 2 Image","Image is exists", true, this.getClass().getName());
        }
        	else
        	{
        		Reporters.report("waiting for the Level 2 Image","Image doesnot exists:Failed", false, this.getClass().getName());
             	isScriptFail=true;
     			return;
        	}

        //Clicking on the Level 2 Image
        client.longClick("default", "level_image", 0, 1, 0, 0);
        //wait for the game loads
        if(client.waitForElement("default", "all_birds", 0, 20000)){
        	Reporters.report("waiting for the game loads","Game Loads", true, this.getClass().getName());
        }
        	else
        	{
        		Reporters.report("waiting for the game loads","Loading game is:Failed", false, this.getClass().getName());
             	isScriptFail=true;
     			return;
        	}
        //Drag the bird from its position to other position
        client.dragCoordinates(267, 372, 203, 411, 2000);
        //client.drag("default", "needto_drag", 0, -20, 25);
        if(client.waitForElement("default", "bird_readytofly", 0, 30000)){
        	Reporters.report("Ater drag the bird waiting for the other bird is ready to fly","Other bird is ready to fly", true, this.getClass().getName());
        }
        	else
        	{
        		Reporters.report("Ater drag the bird waiting for the other bird is ready to fly","Bird ready to fly:Failed", false, this.getClass().getName());
             	isScriptFail=true;
     			return;
        	}
        //Verify the bird is ready to fly
        client.verifyElementFound("default", "bird_readytofly", 0);
        //Verify the birds count image after previous one flew
        client.verifyElementFound("default", "2_birds", 0);
        //client.dragCoordinates(267, 372, 203, 411, 2000);
        //if(client.waitForElement("default", "completed", 0, 10000)){
            
        //}
        //client.verifyElementFound("default", "completed", 0);    	
    	
    }
    @AfterTest
    public void tearDown(){
        client.generateReport();
    }
}
