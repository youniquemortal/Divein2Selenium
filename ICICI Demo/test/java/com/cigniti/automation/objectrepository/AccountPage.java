package com.cigniti.automation.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

/**
 * Contains all the objects related to AccountPage
 * @author Cigniti
 * @Date  19/02/2013
 * @Revision History
 * 
 */
public class AccountPage extends Actiondriver{

	public static By lnkMini() {
		return By.xpath("//font[contains(text(),'Mini')]");
	}
	public static By valueAvailableBal(){		
		return By.xpath("//form[@method='"+methodName()+"']/table/tbody/tr[5]/td/table/tbody/tr/td/table/tbody/tr/td[4]");
	}
	public static By lnkDetailed() {
		return By.xpath("//font[contains(text(),'Detailed')]");
	}
	public static By txnSrcFromDate() {
		return By.name("txnSrcFromDate");
	}
	public static By txnSrcToDate() {
		return By.name("txnSrcToDate");
	}
	public static By btnStatement() {
		return By.xpath("//input[@class='button' and @value='Statement']");
	}
	public static By valueStatementCriteria() {		
		return By.xpath("//form[@method='"+methodName()+"']/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/table/tbody/tr/td/table/tbody/tr[2]/td[4]");
	}
	public static By valueBal() {
		return By.xpath("//form[@method='"+methodName()+"']/table/tbody/tr/td[2]/table/tbody/tr[8]/td/table/tbody/tr[2]/td[8]");
	}
}
