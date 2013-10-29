package com.righthire.testing.tests;

import org.testng.annotations.*;

import com.righthire.testing.pageobjects.LoginPage3;

public class Login3 {
	@Test
	public void testLogin()
	{
		LoginPage3 loginPage3=new LoginPage3();
		loginPage3.get();
		loginPage3.Login("admin@brightfish.com","rfhh8ek1");
		loginPage3.close();
	}
}