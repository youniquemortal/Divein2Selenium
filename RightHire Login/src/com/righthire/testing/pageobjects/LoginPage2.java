package com.righthire.testing.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.chrome.ChromeDriver;
/* Testing */
public class LoginPage2 {
	private WebElement ContentPlaceHolder2_Login1_UserName;
	private WebElement ContentPlaceHolder2_Login1_Password;
	private WebElement ContentPlaceHolder2_Login1_LoginButton;
	private WebDriver driver;
	private String url="http://50.17.211.226/RHPreAsmtSIT/Common/Login.aspx";
	
    public LoginPage2(){
    	driver=new ChromeDriver();
       	PageFactory.initElements(driver, this);
    }
    public void load(){
    	this.driver.get(url);
    }
    public void close(){
    	this.driver.close();
    }
    
    public void Login(String uname,String pwd){
    	ContentPlaceHolder2_Login1_UserName.sendKeys(uname);
    	ContentPlaceHolder2_Login1_Password.sendKeys(pwd);
    	ContentPlaceHolder2_Login1_LoginButton.click();  	
    }
}
