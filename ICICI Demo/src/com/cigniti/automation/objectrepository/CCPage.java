package com.cigniti.automation.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

/**
 * Contains all the objects related to Credit card page
 * @author Cigniti
 * @Date  19/02/2013
 * @Revision History
 * 
 */
public class CCPage extends Actiondriver{

	public static By lnkCreditCards() {
		return By.xpath("//a[contains(text(),'Credit Cards')]");
	}
	public static String lnkCreditCards1() {
		return By.xpath("//a[contains(text(),'Credit Cards')]").toString();
	}
	public static By valueStatementPeriod() {
		return By.xpath("//tr/td[contains(text(),'Statement Period')]//..//td[2]");
	}
	public static By valueTotalAmountDue() {
		return By.xpath("//tr/td[contains(text(),'Total Amount Due')]//..//td[2]");
	}
}
