package com.cigniti.utilities.java;

public class ApplicationLib extends ExpireSuit{
	
	public static Property configProps=new Property("config.properties");
	GenericUtils action=new GenericUtils();
	boolean isScriptFail=false;
	
	public static boolean  Applicationclose(String name) throws Throwable{
		try{

			client.applicationClose(name);
			return true;

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static boolean launchApplication(String name,Boolean  b1,Boolean b2) throws Throwable{
		try{
			
			client.launch(name, b1,b2);
			return true;

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public void navigateToMainMenuScreen() throws Throwable
	{
		//Navigate to Heartland_homescreen
		if(action.waitForElement("Mobuyle", "Heartland_homescreen", 0, 10000)){
			Reporters.report("Navigate to Heartland_homescreen","Navigated to Heartland_homescreen", true, this.getClass().getName());
		}
		else{
			Reporters.report("Navigate to Heartland_homescreen","Unable to navigate Heartland_homescreen", false, this.getClass().getName());
			isScriptFail=true;
			return;
		}
		//Verify the Heartland_homescreen
		if(action.isElementFound("Mobuyle", "Heartland_homescreen", 0)==false){
			Reporters.report("Validate Heartland_homescreen","Unable to validate Heartland_homescreen", false, this.getClass().getName());
			isScriptFail=true;
			return;
		}
		//Validate  Welcome Screen
		if(action.waitForElement("Mobuyle", "No_icon", 0, 10000)==false){
			Reporters.report("Validate Welcome Screen","Failed to validate welcome screen", false, this.getClass().getName());
			isScriptFail=true;
			return;
		}
		//Verify No Button
		if(action.isElementFound("Mobuyle", "No_icon", 0)==false){
			Reporters.report("Validate No Button","Unable to validate No button", false, this.getClass().getName());
			isScriptFail=true;
			return;
		}
		// Click No Button
		if(action.click("Mobuyle", "No_icon", 0, 1)){
			Reporters.report("Click No Button","Clicked No button of Heartland merchant verification screen", true, this.getClass().getName());
		}
		else{
			Reporters.report("Click No Button","Unanble to Click No button of Heartland merchant verification screen", false, this.getClass().getName());
			isScriptFail=true;
			return;
		}
		//Wait for TestDrive Screen
		if(action.waitForElement("Mobuyle", "Seeappin Demomode", 0, 10000)==false){
			Reporters.report("Validate TestDrive Screen"," Unable to validate TestDrive Screen", false, this.getClass().getName());
			isScriptFail=true;
			return;
		}
	}
	
	public boolean validateEmail() throws Throwable
	{
		try
		{
			//Verify the E-Mail
	        if(action.isElementFound("Mobuyle", "Mail verification", 0)==false){
				Reporters.report("Validate E-Mail", "Failed to Validate E-Mail", false,this.getClass().getName());
			}
	        //Launch  Mail
			if(action.click("Mobuyle", "Mail verification", 0, 1)==false){
				Reporters.report("Launch Mail", "Failed to launch E-Mail", false,this.getClass().getName());
			}
			Thread.sleep(50000);
			//Verify E-Mail ID
			if(action.isElementFound("Mobuyle", "Merchant Name", 0)==false){
				Reporters.report("verify the email ID ", "Unable to Verify Email", false,this.getClass().getName());
			}	
			//Click Email Id
			if(action.click("Mobuyle", "Merchant Name", 0, 1)==false){
				Reporters.report("Click E-mai", "Unable to Click  E-Mail", false,this.getClass().getName());
			}
			//Wait for Demo Identification Screen
			if(action.waitForElement("Mobuyle", "Demo identification",0, 10000)==false){
					Reporters.report("Validate Demo identification", "Unable to Validate Demo identification", false,this.getClass().getName());
				}
			//Swipe UP DEMO TRANSACTION Link
			if(action.drag("Mobuyle", "Demo identification", 0, 35, -380)==false){
				Reporters.report("Swipe UP DEMO TRANSACTION Link","Unable to Swipe DEMO TRANSACTION Link ", false,this.getClass().getName());
			}
			//Verify Available Balance
			if(action.isElementFound("Mobuyle", "Available Balance", 0)==false){
				Reporters.report("verify the Available Balance ", "Unable to Verify Available Balance", false,this.getClass().getName());
			}
			//Click the Inbox Button
			if(action.click("Mobuyle", "Inbox", 0, 1)==false){
				Reporters.report("Navigate to Inbox Page ", "Unable to Navigate Inbox Page", false,this.getClass().getName());
				}
		}
		catch(Exception ex)
		{
				System.out.println(ex.getMessage());
				return false;
		}
		finally
		{
			if(action.SendText(configProps.getProperty("Home"))==false){
				Reporters.report("Navigate to Home page ", "Failed to Navigate  home page", false,this.getClass().getName());
			}
		}
		return true;
	}

}
