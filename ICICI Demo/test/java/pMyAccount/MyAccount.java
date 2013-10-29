package pMyAccount;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.automation.accelerators.Actiondriver;
import com.cigniti.automation.business.BusinessFunctions;
import com.cigniti.automation.objectrepository.IciciPage;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

/**
 * Verify Links in home page, Status of the Account 
 * @author Cigniti
 * @Date  19/02/2013
 * @Revision History
 * 
 */
public class MyAccount extends BusinessFunctions{

	/**
	 * To read Account status test data
	 * @throws Exception
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	@DataProvider
	public Object[][] dp_accountStatus() throws Exception
	{
		return Data_Provider.getTableArray("status","MyAccount");

	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getGroupNames(String methodName) throws Exception
	{
		String groupNames="";
		MyAccount app=new MyAccount();
		Class cls=app.getClass();
		Method method=cls.getMethod(methodName);
		Annotation[] a=method.getAnnotations();
		for(int i=0;i<a.length;i++)
		{
			String[] groups=a[i].toString().split("groups=");
			groupNames= groups[1].replace("(", "");
		}
		return groupNames;
	}
	/**
	 *  Verifying different links in ICICI Application
	 * @param Number
	 * @param WelcomeMsg
	 * @throws Throwable
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	@Test(groups = {"smoke"},dataProvider="dp_accountStatus")
	
	public void verifyHomePageLinks(String Number, String WelcomeMsg) throws Throwable{
		//boolean flag =false;
		try {
			if(iciciLogin()==true)
			{
				Reporters.SuccessReport("Login" , "LogIn is successful with UserID: "+configProps.getProperty("UserName"));
				if (Actiondriver.getText(IciciPage.msgWelcome()).trim().contains(WelcomeMsg.trim())) {
					Reporters.SuccessReport("Welcome Message Check", "Welcome Message is displayed");	
				}
				else {
					
					Reporters.failureReport("Welcome Message Check", "Welcome Message is not displayed");
				}
			}
			iciciLinks();
			Thread.sleep(3000);
			if (iciciLogout()==true) {
				Reporters.SuccessReport("Logout", "Logged out successfully" );
			}
			
		} catch (Exception e) {
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
	 * Verifying Account Status Active or Not
	 * @param Number
	 * @param WelcomeMsg
	 * @throws Throwable
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	@Test(groups = {"functional"},dataProvider="dp_accountStatus")
	
	public void verifyMyAccountStatus(String Number, String WelcomeMsg) throws Throwable{
		if(iciciLogin()==true)
		{
			Reporters.SuccessReport("Login" , "LogIn is successful with UserID: "+configProps.getProperty("UserName"));
		}
		accountStatus(Number);
		if (iciciLogout()==true) {
			Reporters.SuccessReport("Logout", "Logged out successfully" );
		}

	}

}
