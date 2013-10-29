package com.righthire.testing.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import static org.testng.Assert.*;


public class LoginPage3 extends LoadableComponent<LoginPage3> {
	private WebElement ContentPlaceHolder2_Login1_UserName;
	private WebElement ContentPlaceHolder2_Login1_Password;
	private WebElement ContentPlaceHolder2_Login1_LoginButton;
	private WebDriver driver;
	private String url="http://50.17.211.226/RHPreAsmtSIT/Common/Login.aspx";
	private String title="RightHire";
	
    public LoginPage3(){
    	driver=new ChromeDriver();
       	PageFactory.initElements(driver, this);
    }
    @Override
	protected void load(){
    	this.driver.get(url);
    }
    
    @Override
    protected void isLoaded(){
    	assertTrue(driver.getTitle().equals(title));
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
