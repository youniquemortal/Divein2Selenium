package com.cigniti.automation.accelerators;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.cigniti.automation.utilities.Accessories;
import com.cigniti.automation.utilities.HtmlReporters;
import com.cigniti.automation.utilities.Property;
import com.cigniti.automation.utilities.Reporters;


public class Base {

	public static Property configProps=new Property("config.properties");
	public static String currentSuite="";
	public static String method="";
	public static String timeStamp=Accessories.timeStamp().replace(" ","_").replace(":","_").replace(".", "_");
	public static WebDriver driver;

	/**
	 *Initializing browser requirements, Test Results file path and Database requirements from the configuration file
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */

	@BeforeSuite(alwaysRun = true) 

	public static void setupSuite(ITestContext ctx) throws Throwable {
		if(configProps.getProperty("browserType").equalsIgnoreCase("firefox"))
		{

			driver= new FirefoxDriver();
			method="POST";
		}

		else if(configProps.getProperty("browserType").equals("chrome"))
		{

			System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");				
			driver=new ChromeDriver();
		}

		else
		{		
			File file = new File("Drivers\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

			driver= new InternetExplorerDriver();
			method="post";
		}
		driver.get(configProps.getProperty("URL"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Reporters.reportCreater();
		HtmlReporters.currentSuit = ctx.getCurrentXmlTest().getSuite().getName();

	}

	
/**
	 *  De-Initializing and closing all the connections
	 * @throws Throwable
	 */
	@AfterSuite(alwaysRun = true) 
	public void tearDown(ITestContext ctx) throws Throwable {
		HtmlReporters.createHtmlSummaryReport();
		//driver.close();
		driver.quit();
		System.out.println(ctx.getFailedConfigurations());

	}
	/**
	 * Write results to Browser specific path
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	//@Parameters({"browserType"})
	public static String filePath()
	{
		String strDirectoy="";
		if(configProps.getProperty("browserType").equals("ie"))
		{
			strDirectoy="IE\\IE";	

		}
		else if(configProps.getProperty("browserType").equals("firefox"))
		{
			strDirectoy="Firefox\\Firefox";

		}
		else
		{
			strDirectoy="Chrome\\Chrome";

		}

		if(strDirectoy!="")
		{
			new File(configProps.getProperty("screenShotPath")+strDirectoy+"_"+timeStamp).mkdirs();
		}

		return configProps.getProperty("screenShotPath")+strDirectoy+"_"+timeStamp+"\\";

	}
	/**
	 * Browser type prefix for Run ID
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static String result_browser()
	{
		if(configProps.getProperty("browserType").equals("ie"))
		{
			return "IE";
		}
		else if(configProps.getProperty("browserType").equals("firefox"))
		{
			return "Firefox";
		}
		else
		{
			return "Chrome";
		}
	}
	/**
	 * Related to Xpath
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static String methodName()
	{
		if(configProps.getProperty("browserType").equals("ie"))
		{
			return "post";
		}
		else
		{
			return "POST";
		}
	}




	@BeforeMethod(alwaysRun=true)
	public void reportHeader(Method method){
		HtmlReporters.tc_name=method.getName().toString();
		String[] ts_Name=this.getClass().getName().toString().split("\\.");
		HtmlReporters.packageName=ts_Name[0];
		HtmlReporters.testHeader(ts_Name[ts_Name.length-2].concat(" : ").concat(HtmlReporters.tc_name));
	}
}