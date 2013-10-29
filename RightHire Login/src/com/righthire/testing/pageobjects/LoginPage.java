package com.righthire.testing.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebElement ContentPlaceHolder2_Login1_UserName;
	public WebElement ContentPlaceHolder2_Login1_Password;
	public WebElement ContentPlaceHolder2_Login1_LoginButton;
		
    public LoginPage(WebDriver driver){
    	PageFactory.initElements(driver, this);
    }
}
