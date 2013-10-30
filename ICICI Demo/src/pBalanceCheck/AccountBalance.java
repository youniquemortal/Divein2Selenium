package pBalanceCheck;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.automation.business.BusinessFunctions;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

/**
 * Verifying Available Balance in Ministatement and Detailed Statement by calling BusinessFunctions
 * @author Cigniti
 * @Date  19/02/2013
 * @Revision History 
 *  
 */
public class AccountBalance extends BusinessFunctions{

	/**
	 * To read detailed statement test data
	 * @return
	 * @throws Exception
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History 
	 *  
	 */
	@DataProvider
	public Object[][] dp_detailedStatement() throws Exception
	{
		return Data_Provider.getTableArray("myaccount","AccountBalance");
	}
	/**
	 * Verifying Available Balance in MiniStatement
	 * @param fromDate
	 * @param toDate
	 * @param miniBal
	 * @param DetailBal
	 * @throws Throwable
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History 
	 *  
	 */
	@Test(groups = {"regression"},priority=2, dataProvider="dp_detailedStatement")
	
	public void verifyMiniStatement(String fromDate,String toDate,String miniBal, String DetailBal) throws Throwable{
		try {
			if(iciciLogin()==true)
			{
				Reporters.SuccessReport("Login" , "LogIn is successful with UserID: "+configProps.getProperty("UserName"));
			}
			else
			{
				Reporters.failureReport("Login", "LogIn not successful with UserID: "+configProps.getProperty("UserName") + ", home page not displayed");
			}
			Thread.sleep(4000);
			miniStmt(miniBal);
			if (iciciLogout()==true) {
				Reporters.SuccessReport("Logout", "Logged out successfully" );
			}
			
		}  catch (Exception e) {
			// TODO: handle exception
			 flag=true;
				System.out.println(e.getMessage());
		}
		
		finally{
			if(flag)
			{
				//throw new ElementNotFoundException("", "", "");
				throw new NoSuchElementException("Element not found");
			}
		}
}
		
	
	/**
	 * Verifying Available Balance in Detailed Statement
	 * @param fromDate
	 * @param toDate
	 * @param miniBal
	 * @param DetailBal
	 * @throws Throwable
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision Histo
	 *  
	 */
	@Test(groups = {"regression"},priority=1, dataProvider="dp_detailedStatement")
	
	public static void verifyDetailedStatement(String fromDate,String toDate,String miniBal, String DetailBal) throws Throwable{
		try {
			if(iciciLogin()==true)
			{
				Reporters.SuccessReport("Login" , "LogIn is successful with UserID: "+configProps.getProperty("UserName"));
			}
			else
			{
				Reporters.failureReport("Login", "LogIn not successful with UserID: "+configProps.getProperty("UserName") + ", home page not displayed");
			}
			Thread.sleep(2000);
			detailedStmt(fromDate, toDate,DetailBal);

			if (iciciLogout()==true) {
				Reporters.SuccessReport("Logout", "Logged out successfully" );
			}
		
		}
			
		catch (Exception e) {
			// TODO: handle exception
			 flag=true;
				System.out.println(e.getMessage());
		}
		
		finally{
			if(flag)
			{
				//throw new ElementNotFoundException("", "", "");
				throw new NoSuchElementException("Element not found");
			}
		}
}
}