package com.righthire.testing.tests;

import org.testng.annotations.*;

import com.righthire.testing.pageobjects.LoginPage2;

public class Login2 {
	@Test
	public void testLogin()
	{
		LoginPage2 loginPage2=new LoginPage2();
		loginPage2.load();
		loginPage2.Login("admin@brightfish.com","rfhh8ek1");
		loginPage2.close();
	}
}
