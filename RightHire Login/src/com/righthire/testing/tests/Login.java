package com.righthire.testing.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import com.righthire.testing.pageobjects.LoginPage;

public class Login {
	
	@Test
	public void testLogin()
	{
		WebDriver driver=new ChromeDriver();
		driver.get("http://50.17.211.226/RHPreAsmtSIT/Common/Login.aspx");
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.ContentPlaceHolder2_Login1_UserName.sendKeys("admin@brightfish.com");
		loginPage.ContentPlaceHolder2_Login1_Password.sendKeys("rfhh8ek1");
		loginPage.ContentPlaceHolder2_Login1_LoginButton.click();
		driver.close();
	}

}
