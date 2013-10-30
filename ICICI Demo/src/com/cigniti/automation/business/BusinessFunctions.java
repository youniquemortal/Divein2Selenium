package com.cigniti.automation.business;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cigniti.automation.accelerators.Actiondriver;
import com.cigniti.automation.objectrepository.AccountPage;
import com.cigniti.automation.objectrepository.CCPage;
import com.cigniti.automation.objectrepository.IciciPage;
import com.cigniti.automation.utilities.Reporters;

/**
 * All the business functions related to Application
 * @author Cigniti
 * @Date  19/02/2013
 * @Revision History
 * 
 */

public class BusinessFunctions extends Actiondriver{

	//static Property config = new Property("config.properties");
	/**
	 * To verify User Login
	 * @return True on successful login
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static boolean iciciLogin() throws Throwable {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			driver.get(configProps.getProperty("URL"));
			type(IciciPage.lblUserName(),configProps.getProperty("UserName"));
			type(IciciPage.lblPassword(), configProps.getProperty("Password"));
			wait.until(ExpectedConditions.elementToBeClickable(IciciPage.btnLogin()));
			click(IciciPage.btnLogin());
			Thread.sleep(8000);
			if( asserttitle("Home Page"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e) {
			return false;
		}
	}
	/**
	 * To verify User Logout
	 * @return True on successful Logout
	 * @author Cigniti
	 * @Date  19/02/2013 
	 * @Revision History
	 * 
	 */
	public static boolean iciciLogout() throws Throwable {
		Thread.sleep(3000);
		try {
			waitForElementPresent(IciciPage.lnkLogout());
			mouseover(IciciPage.lnkLogout());
			click(IciciPage.lnkLogout());
			Thread.sleep(5000);
			if( asserttitle("ICICI Bank : Logout"))
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * To Verifying various links in ICICI Application
	 * @author Cigniti
	 * @Date  19/02/2013 
	 * @Revision History
	 * 
	 */
	public static void iciciLinks() throws Throwable {
		Thread.sleep(5000);
		if (clickLink(IciciPage.lnkFundTransfer())) {
			Reporters.SuccessReport("Click Funds Transfer", "Clicked on the link : Funds Transfer");
		}
		else {
			Reporters.failureReport("Click Funds Transfer", "Could not click on the link : Funds Transfer");
		}
		Thread.sleep(3000);
		if (isElementPresent(IciciPage.radioTransfer())) {
			Reporters.SuccessReport("Verify Funds Transfer Page", "Funds Transfer : Across Bank Accounts Page is available" );
		}
		else {
			Reporters.failureReport("Verify Funds Transfer Page", "Funds Transfer : Across Bank Accounts Page is not available");
		}
		
		Thread.sleep(2000);
		if ( clickLink(IciciPage.lnkMyaccounts())) {
			Reporters.SuccessReport("Click My Accounts", "Clicked on the link : My Accounts");
		}
		else {
			Reporters.failureReport("Click My Accounts", "Could not click on the link : My Accounts");
		}
		Thread.sleep(3000);
		mouseover(IciciPage.lnkPuregold());
		Thread.sleep(2000);
		if (clickLink(IciciPage.lnkPuregold())) {
			Reporters.SuccessReport("Click Buy/Gift Pure Gold/Silver", "Clicked on the link : Buy Gift Pure Gold or Silver");
		}
		else {
			Reporters.failureReport("Click Buy/Gift Pure Gold/Silver", "Could not click on the link : Buy Gift Pure Gold or Silver");
		}
		Thread.sleep(5000);
		if ( verifyTextPresent("Buy Gold/Silver Online")) {
			Reporters.SuccessReport("Verify Text Buy Gold/Silver Online", "Buy Gold/Silver Online text is available" );
		}
		else {
			Reporters.failureReport("Verify Text Buy Gold/Silver Online", "Buy Gold or Silver Online text is not available" );
		}
	
		Thread.sleep(2000);
		if ( clickLink(IciciPage.lnkMyaccounts())) {
			Reporters.SuccessReport("Click My Accounts", "Clicked on the link : My Accounts" );
		}
		else {
			Reporters.failureReport("Click My Accounts", "Could not click on the link : My Accounts" );
		}
		Thread.sleep(3000);
		mouseover(IciciPage.lnkReceiveFunds());
		
		if ( clickLink(IciciPage.lnkReceiveFunds())) {
			Reporters.SuccessReport("Click Receive Funds", "Clicked on the link : Receive Funds" );
		}
		else {
			Reporters.failureReport("Click Receive Funds", "Could not click on the link : Receive Funds" );
		}
		Thread.sleep(3000);
		if ( isElementPresent(IciciPage.btnRegistration())){
			Reporters.SuccessReport("Verify Receive Funds  Page", "Receive Funds Page is available" );
		}
		else {
			Reporters.failureReport("Verify Receive Funds  Page", "Receive Funds Page is not available" );
		}
		
		

		if ( clickLink(IciciPage.lnkMyaccounts())) {
			Reporters.SuccessReport("Click My Accounts", "Clicked on the link : My Accounts" );
		}
		else {
			Reporters.failureReport("Click My Accounts", "Could not click on the link : My Accounts" );
		}
		Thread.sleep(3000);
		mouseover(IciciPage.lnkPrepaidRec());
		if ( click(IciciPage.lnkPrepaidRec())) {
			Reporters.SuccessReport("Click Pre-Paid Recharge", "Clicked on the link : Pre-Paid Recharge" );
		}
		else {
			Reporters.failureReport("Click Pre-Paid Recharge", "Could not click on the link : Pre Paid Recharge" );
		}
		Thread.sleep(3000);
		if ( verifyTextPresent("Pre-Paid Recharge")) {
			Reporters.SuccessReport("Verify Text Pre-Paid Recharge", "Pre-Paid Recharge text is available" );	
		}
		else {
			Reporters.failureReport("Verify Text Pre-Paid Recharge", "Pre-Paid Recharge text is not available" );
		}
		
	}
	/**
	 * Verifying Account Status
	 * @param aNumber --Account number should get from excel sheet
	 * @author Cigniti
	 * @Date  19/02/2013 
	 * @Revision History
	 * 
	 */
	public static void accountStatus(String aNumber) throws Throwable{
		mouseover(IciciPage.lnkAccntNo());

		// Verifying Account number with the Account Number given in TestData sheet (status)
		if( getText(IciciPage.lnkAccntNo()).equalsIgnoreCase(aNumber))
		{
			String accNum=getText(IciciPage.lnkAccntNo());
			Reporters.SuccessReport("Check Account Nnumber", "Account number :"+accNum+ " matched with "+ aNumber );
			{
				mouseover(IciciPage.lnkAccntNo());
				Thread.sleep(2000);
				if (click(IciciPage.lnkAccntNo())) {
					Reporters.SuccessReport("Click on Account Number", "Clicked on the Account Number link : " +accNum);
				}
				else {
					Reporters.failureReport("Click on Account Number", "Could not find the Account Number link : "+accNum);
				}
				waitForElementPresent(IciciPage.lnkAccntNoinStatusPage());
				mouseover(IciciPage.lnkAccntNoinStatusPage());
				Thread.sleep(5000);
				if ( click(IciciPage.lnkAccntNoinStatusPage())) {
					Reporters.SuccessReport("Click Account Number in status Page", "Clicked on the Account Number link :"+accNum+" in status page" );
				}
				else {
					Reporters.failureReport("Click Account Number in status Page", "Could not find Account Number link :"+accNum+" in status page" );
				}
				Thread.sleep(3000);
				// Verifying Account Status Active or Not
				if ( assertText("Active", IciciPage.valueAccountStatus())) {
					Reporters.SuccessReport("Verify Account Status", "Account : "+accNum +"  status is Active" );
				}
				else {
					Reporters.failureReport("Verify Account Status", "Account : "+accNum+" status is not Active" );
				}
			}
		}

		else
		{
			Reporters.failureReport("Check Account Number", "Account number :"+getText(IciciPage.lnkAccntNo())+ "did not match with "+aNumber);
		}
	}
	/**
	 * Verify Statement Period and AmountDue in Credit Cards page
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 *  
	 */
	public static void ccStatus()throws Throwable{
		mouseover(CCPage.lnkCreditCards());
		Thread.sleep(3000);
		if ( click(CCPage.lnkCreditCards())) {
			Reporters.SuccessReport("Click on Credit Cards", "Clicked on the link : Credit Cards" );
			if( getText(CCPage.valueStatementPeriod()) ==null)
			{
				Reporters.failureReport("Check Statement Period", "Statement Period not found" );
			}
			else
			{
				Reporters.SuccessReport("Check Statement Period", "Statement period : "+ getText(CCPage.valueStatementPeriod()) + " is available");	
			}

			Reporters.SuccessReport("Print Total Amount Due", "Total Amount Due Is : "+ getText(CCPage.valueTotalAmountDue()) );
		}
		else {
			Reporters.failureReport("Click on Credit Cards", "Could not click on the link : Credit Cards" );
		}
		Thread.sleep(3000);
	}
	/**
	 * Verify Available Balance in MiniStatement 
	 * @param miniBal --Minimum balance should get from excel sheet
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 *  
	 */
	public static void miniStmt(String miniBal)throws Throwable{
		String aBal;
		mouseover(AccountPage.lnkMini());
		Thread.sleep(2000);
		if ( click(AccountPage.lnkMini())) {
			Reporters.SuccessReport("Click on Mini", "Clicked on the link : Mini" );


			// Verifying Available Balance with miniBal in Test Data sheet (myaccount) 
			aBal= getText(AccountPage.valueAvailableBal());
			if (miniBal.equalsIgnoreCase(getText(AccountPage.valueAvailableBal()))) {
				Reporters.SuccessReport("Available Balance Check", "Available Balance " +aBal +" matched with Minimum Balance "+ miniBal );
			}
			else {
				Reporters.failureReport("Available Balance Check", "Available Balance " +aBal +" did not match with Minimum Balance "+ miniBal );
			}
		}
		else {
			Reporters.failureReport("Click on Mini", "Could not click on hte link:Mini" );
		}
	}
	/**
	 * Verify Available Balance in Detailed Statement
	 * @param fromDate -- From date 
	 * @param toDate -- To date
	 * @param DetailBal ---Balance
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static void detailedStmt(String fromDate,String toDate, String DetailBal)throws Throwable{
		String[] DetailAvailableBalance;
		String detailAvailBal;
		mouseover(AccountPage.lnkDetailed());
		Thread.sleep(2000);
		if ( click(AccountPage.lnkDetailed())) {
			Reporters.SuccessReport("Click Detailed", "Clicked on the link :Detailed" );
		}
		else {
			Reporters.failureReport("Click Detailed", "Could not click on the link : Detailed" );
		}
		type(AccountPage.txnSrcFromDate(), fromDate);
		type(AccountPage.txnSrcToDate(), toDate);
		mouseover(AccountPage.btnStatement());
		if ( click(AccountPage.btnStatement())) {
			Reporters.SuccessReport("Click Statement", "Clicked on the link : Statement Button" );
			Reporters.SuccessReport("Statement Range Criteria", "Statement Range : "+ getText(AccountPage.valueStatementCriteria()) );
			DetailAvailableBalance= getText(AccountPage.valueBal()).split(",");
			detailAvailBal=DetailAvailableBalance[0]+DetailAvailableBalance[1];

			// Verifying Available Balance with DetailBal in Test Data sheet (my account)
			if (DetailBal.trim().equalsIgnoreCase(detailAvailBal.trim())) {
				Reporters.SuccessReport("Detail Available Balance Check", "Detail Available Balance " +detailAvailBal +" is matched with expected balance: "+DetailBal );
							}
			else {
				Reporters.failureReport("Check Detail Available Balance", "Detail Available Balance " +detailAvailBal +" did not match with expected balance: "+DetailBal );

			}
		}
		else {
			Reporters.failureReport("Click Statement", "Could not click on the link : Statement Button" );
		}
	}
}


