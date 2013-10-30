package com.cigniti.automation.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

/**
 * Contains all the objects related to IciciHomePage
 * @author Cigniti
 * @Date  19/02/2013
 * @Revision History
 * 
 */
public class IciciPage extends Actiondriver{
	public static By lblUserName() {
		//return By.name("CorporateSignonCorpId");
		return By.xpath("//span[text()='User ID :']//..//input");
	}
	public static By lblPassword() {
		//return By.name("CorporateSignonPassword");
		return By.xpath("//span[text()='Password :']//..//input");
	}
	public static By btnLogin() {
		return By.id("arcotsubmit");
	}
	public static By lnkLogout() {
		return By.linkText("Logout");
	}
	public static By lnkFundTransfer() {
		return By.xpath("//a[contains(text(),'Funds Transfer')]");
	}
	public static By lnkMyaccounts() {
		return By.partialLinkText("My Accounts");
	}
	public static By lnkPuregold() {
		return By.xpath("//a[contains(text(),'Buy/Gift Pure Gold')]");
	}
	public static By lnkReceiveFunds() {
		return By.xpath("//a[contains(text(),'Receive Funds')]");
	}
	public static By lnkPrepaidRec() {
		return By.xpath("//a[contains(text(),'Pre-paid Recharge')]");
	}
	public static By lnkAccntNo(){
		return By.xpath("//font[text()='007601524498']");
	}
	public static By lnkAccntNoinStatusPage(){
		return By.xpath("//a[contains(text(),'007601524498')]");
	}
	public static By valueAccountStatus(){
		return By.xpath("//form[@method='"+methodName()+"']/table/tbody/tr[5]/td/table/tbody/tr/td/table/tbody/tr[3]/td[3]");
	}
	public static By radioTransfer(){
		return By.id("fundtransfer");
	}
	public static By btnRegistration(){
		return By.name("Action.Bills.INEFT.Register");
	}
	public static By msgWelcome(){
		return By.xpath("//td[@class='menu15']");
	}
}
