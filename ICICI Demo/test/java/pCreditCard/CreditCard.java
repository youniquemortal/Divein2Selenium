package pCreditCard;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import com.cigniti.automation.business.BusinessFunctions;
import com.cigniti.automation.utilities.Reporters;

/**
 *  Verifying Credit Card Statement Period and Amount Due
 * @author Cigniti
 * @Date Modified 19/02/2013
 * @Revision History
 * 
 */
public class CreditCard extends BusinessFunctions{
	
	
	@Test(groups ={"functional"}, priority=1)
	
	public void verifyCreditCardDue() throws Throwable{
		try {
			if(iciciLogin()==true)
			{
				Reporters.SuccessReport("Login" , "LogIn is successful with UserID: "+configProps.getProperty("UserName"));
			}
			else
			{
				Reporters.failureReport("Login", "LogIn not successful with UserID: "+configProps.getProperty("UserName") + ", home page not displayed");			
			}
			ccStatus();
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
