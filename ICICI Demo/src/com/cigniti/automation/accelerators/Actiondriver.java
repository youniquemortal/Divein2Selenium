package com.cigniti.automation.accelerators;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cigniti.automation.utilities.Reporters;

/**
 * All customized/ Generic functions 
 * @author Cigniti
 * @Date  19/02/2013
 * @Revision History
 *
 */
public class Actiondriver extends Base{
	
	//public static Property repository=new Property("ÖbjectRepository.properties");
	public static WebDriverWait wait;
	public static boolean flag =false;

	/**
	 * This  Method is to perform click operation on (link,button,check box,radio button) 
	 * Before click the element it will wait until the element present.
	 * @param locator --Element locator
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static boolean click(By locator) throws Throwable
    {
           boolean flag=false;
           try {
        	   	  isElementPresent(locator);
                  driver.findElement(locator).click();
                  return true;
           } catch (Exception e) {
                  System.out.println(e.getMessage());
                  flag=true;
                  return false;
           }
           finally
           {
                  if(flag)
                  {
                        Reporters.failureReport("click", "Not clicked on :" +locator);
                        //throw new ElementNotFoundException("", "", "");
                        throw new NoSuchElementException("Element not found");
                  }
           }
    }
	

	
	public static boolean click1(WebElement locator)
	{
	
		try {
			//waitForElementPresent(locator);
			
			//wait.until(ExpectedConditions.elementToBeClickable(locator));
			//driver.findElement(locator).click();
			locator.click();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}
		
	}	
	/**
	 * Verify element present or not
	 * @param locator --Element locator
	 * @return TrueIf the element is Present, False other wise
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	
	/**
	 * This method for type in to text box or text area
	 * @param locator --Element locator
	 * @param testdata -- input text
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static boolean type(By locator, String testdata)
	{
		try {
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(testdata);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	/**
	 * Moves the mouse to the middle of the element. The element is scrolled into view and its
	 * location is calculated using getBoundingClientRect.
	 * @param locator element to move to.
	 * @return A self reference.
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static  void mouseover(By locator) throws Exception {

		WebElement mo=driver.findElement(locator);
		new Actions(driver).moveToElement(mo).perform();
		/*WebElement mo=driver.findElement(locator);
		Action hover = new Actions(driver).moveToElement(mo).build(); 
		Action click = new Actions(driver).click().build(); 
		hover.perform(); 
		click.perform(); */
		
	}
	
	public static void mouseHoverByJavaScript(By locator)
    {
		WebElement mo=driver.findElement(locator);
        String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                            "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                            "arguments[0].dispatchEvent(evObj);";
        JavascriptExecutor js = (JavascriptExecutor) driver; 
        js.executeScript(javaScript, mo);
      }
	/**
	 * A convenience method that performs click-and-hold at the location of the source element,
	 * moves by a given offset, then releases the mouse.
	 * @param source element to emulate button down at.
	 * @param xOffset horizontal move offset.
	 * @param yOffset vertical move offset.
	 * @return A self reference.
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static void draggable(By source, int x,int y) throws Exception {

		WebElement dragitem=driver.findElement(source);

		new Actions(driver).dragAndDropBy(dragitem, x, y).build().perform();

		Thread.sleep(5000);
	}
	/**
	 * A convenience method that performs click-and-hold at the location of the source element,
	 * moves to the location of the target element, then releases the mouse.
	 *
	 * @param source element to emulate button down at.
	 * @param target element to move to and release the mouse at.
	 * @return A self reference.
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static void draganddrop(By source, By target) throws Exception {

		WebElement from = driver.findElement(source);
		WebElement to = driver.findElement(target);
		new Actions(driver).dragAndDrop(from,to).perform();
	}

	/**
	 * To slide an object to some distance
	 * @param slider
	 * @throws Exception
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static void slider(By slider) throws Exception {
		WebElement dragitem=driver.findElement(slider);
		new Actions(driver).dragAndDropBy(dragitem, 300, 1).build().perform();
		Thread.sleep(5000);
	}
	/**
	 * To right click on an element
	 * @param by
	 * @throws Exception
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */

	public static void rightclick(By by) throws Exception {
		WebElement elementToRightClick = driver.findElement(by);
		Actions clicker = new Actions(driver);
		clicker.contextClick(elementToRightClick).perform();
		//driver.findElement(by1).sendKeys(Keys.DOWN);
	}

	/**
	 * Wait for an element
	 * @param locator --Element locator
	 * @throws InterruptedException 
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */

	public static void waitForElementPresent(By locator) throws InterruptedException
	{
		for(int i=0; i<200; i++)
		{
			if (isElementPresent(locator))
			{
				break;
			} else
			{
				Thread.sleep(50);
			}
			{
				try
				{
					driver.wait(5000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Click and wait for an element
	 * @param locator -- Element locator
	 * @param waitElement  -- Element which you are waiting for
	 * @author Cigniti
	 * @throws Throwable 
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static void clickAndWaitForElementPresent(By locator, By waitElement) throws Throwable
	{
		click(locator);
		waitForElementPresent(waitElement);
	}
	/**
	 * Select a value from Dropdown using send keys
	 * @param locator -- Element locator
	 * @param value
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static void select(By locator, String value)
	{
		driver.findElement(locator).sendKeys(value);
	}

	/**
	 * select value from DD by using selectByIndex
	 * @param locator ---- Element locator
	 * @param index -- Drop down value index
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public void selectByIndex(By locator,int index)
	{
		Select s=new Select(driver.findElement(locator));
		s.selectByIndex(index);
	}

	/**
	 * select value from DD by using value
	 * @param locator ---- Element locator
	 * @param value -- Drop down value 
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */

	public void selectByValue(By locator,String value)
	{
		Select s=new Select(driver.findElement(locator));
		s.selectByValue(value);
	}
	/**
	 * select value from DD by using selectByVisibleText
	 * @param locator ---- Element locator
	 * @param visibletext --Visible text of Drop down value
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */

	public void selectByVisibleText(By locator,String visibletext)
	{
		Select s=new Select(driver.findElement(locator));
		s.selectByVisibleText(visibletext);
	}
	/**
	 *SWITCH TO WINDOW BY USING TITLE 
	 * @param windowTitle --Title of the child widow
	 * @param count --Number of the window
	 * @throws Exception
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 *  
	 */
	//
	public Boolean switchWindowByTitle(String windowTitle, int count) throws Exception
	{
		Set<String> windowList=driver.getWindowHandles();
		int windowCount = windowList.size();

		//DateTime timeout = DateTime.Now.AddSeconds(30);
		Calendar calendar = new GregorianCalendar();
		int second = calendar.get(Calendar.SECOND); ///to get current time
		int timeout=second+40;
		// calendar.add(Calendar.SECOND, 40);
		while (windowCount != count && second< timeout)
		{
			Thread.sleep(500);
			windowList = driver.getWindowHandles();
			windowCount = windowList.size();

		}

		String[] array = windowList.toArray(new String[0]);

		for (int i = 0; i < windowCount; i++)
		{

			driver.switchTo().window(array[i]);
			if(driver.getTitle().contains(windowTitle))
				return true;
		}
		return false;
	}
	/**
	 * Function To get column count and print data in Columns
	 * @param locator ---- Element locator
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public void getColumncount(By locator) throws Exception {

		WebElement tr =driver.findElement(locator);
		List<WebElement> columns = tr.findElements(By.tagName("td"));
		System.out.println(columns.size());
		for (WebElement column : columns) {
			System.out.print(column.getText());
			System.out.print("|");
		}

	}
	/**
	 *Function To get row count and print data in rows
	 * @param locator ---- Element locator
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public  int getRowCount(By locator) throws Exception {

		WebElement table =driver.findElement(locator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		//System.out.println(rows.size()-1);
		int a=rows.size()-1;
		for (WebElement row : rows) {
			System.out.println(row.getText());

		}
		return a;
	}
	/**
	 * Verify alert present or not
	 * @return True if alert is present false other wise
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public boolean isAlertPresent() {

		boolean presentFlag = false;

		try {

			// Check the presence of alert
			Alert alert = driver.switchTo().alert();
			// Alert present; set the flag
			presentFlag = true;
			// if present consume the alert
			alert.accept();

		} catch (NoAlertPresentException ex) {
			// Alert not present
			ex.printStackTrace();
		}

		return presentFlag;
	}
	/**
	 * To launch URL
	 * @param url
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public void launchUrl(String url)
	{
		driver.navigate().to(url);
	}
	/**
	 * check box checked or not
	 * @param locator ---- Element locator
	 * @return True if the element is enabled, false otherwise.
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public Boolean isChecked(By locator)
	{
		Boolean value = false;
		if (driver.findElement(locator).isSelected())
		{
			value = true;
		}
		return value;
	}
	/**
	 * Element is editable or not
	 * @param locator ---- Element locator
	 * @return True if the element is enabled, false otherwise.
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */

	public Boolean isEditable(By locator)
	{
		Boolean value = false;
		if (driver.findElement(locator).isEnabled())
		{
			value = true;
		}
		return value;
	}
	/**
	 *Element visible or not 
	 * @param locator ---- Element locator 
	 * @return True if the element is visible false other wise
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */

	public Boolean IsVisible(By locator)
	{
		Boolean value = false;
		if (isElementPresent(locator))
		{
			value = driver.findElement(locator).isDisplayed();
		}
		return value;
	}
	/**
	 * Get the CSS value of an element	
	 * @param locator ---- Element locator
	 * @return CSS value of the element
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */

	public String GetValue(By locator)
	{
		String value = "";
		if (isElementPresent(locator))
		{
			value = driver.findElement(locator).getCssValue(value);
		}
		return value;
	}
	/**
	 * Check the expected value is available or not
	 * @param expvalue 
	 * @param locator
	 * @param attribute
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History

	 * 
	 */
	public static boolean assertValue(String expvalue, By locator, String attribute)
	{
		Assert.assertEquals(expvalue, getAttribute(locator, attribute));
		return true;
	}
	/**
	 * Check the text is presnt or not
	 * @param text
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public void assertTextPresent(String text)
	{
		Assert.assertTrue( isTextPresent(text),text + " Not present");
	}

	/**
	 * Assert element present or not
	 * @param text
	 * @param by
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	/*public static boolean assertElementPresent(String text,By by)
	{
		try {
			Assert.assertTrue( isElementPresent(by),by + text);
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
		
	}*/
	
	public static boolean assertElementPresent(By by)
	 {
	  try {
	   //assertTrue(isElementPresent(by));
	   Assert.assertTrue(isElementPresent(by));
	   return true;
	  } catch (Error e) {
	   return false;
	  }
	 }

	/**
	 * Assert text
	 * @param text
	 * @param by
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */

	public static boolean assertText(String text,By by)
	{
		try
		{
			Assert.assertEquals(text, getText(by));
			System.out.println("Expected Text is available");
			return true;
		}
		catch(Throwable e)
		{

			System.out.println("Expected text is not available");
		}
		return false;

	}

	/**
	 * assert title
	 * @param title
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static boolean asserttitle(String title)
	{
		try
		{
		Assert.assertEquals(title, driver.getTitle());
		return true;
		}
		
		catch(Error e)
		{
			System.out.println("Title does not match");
			return false;
		}
		
		
	}

	/**
	 * Verify text present or not
	 * @param text
	 * @return  true if text is present false other wise
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static boolean verifyTextPresent(String text)
	{
		try {
			Assert.assertTrue(driver.getPageSource().contains(text));
			System.out.println(text + " is Available");
			return true;
		} catch (Error e) {
			System.out.println(text +"Not avaliable");

		}
		return false;
	}
	/**
	 * Verify the 404 error or broken links
	 * @param text
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static void verify404(String text)

	{

		if(driver.getPageSource().contains("404"))
		{
			System.out.println("404 error");
		}

	}
	/**
	 * Get the value of a the given attribute of the element. 
	 * @param by
	 * @param attribute
	 * @return  Will return the current value
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */

	public static String getAttribute(By by, String attribute)
	{
		String value = "";
		if (isElementPresent(by))
		{
			value = driver.findElement(by).getAttribute(attribute);
		}
		return value;
	}

	/**
	 *Text present or not 
	 * @param text
	 * @return
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */

	public boolean isTextPresent(String text) {

		return driver.getPageSource().contains("sometext");
	}
	/**
	 *The innerText of this element.
	 * @param locator
	 * @return
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */

	public static String getText(By locator)
	{
		String text = "";
		if (isElementPresent(locator))
		{
			text = driver.findElement(locator).getText();
		}
		return text;
	}

	/**
	 * Capture Screenshot
	 * @param fileName
	 * @author Cigniti
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static void screenShot(String fileName) 
	{
		flag=true;
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			// Now you can do whatever you need to do with it, for example copy somewhere
			FileUtils.copyFile(scrFile, new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Click on the Link
	 * @param Locator
	 * @author Cigniti
	 * @Date  04/04/2013
	 * @Revision History
	 * 
	 */
	public static boolean clickLink(By locator) throws Throwable
    {
    
           try {
                  isElementPresent(locator);
                  //wait.until(ExpectedConditions.elementToBeClickable(locator));
                  driver.findElement(locator).click();
                  Thread.sleep(5000);
                  return true;
           } catch (Exception e) {
                  // TODO: handle exception
                  System.out.println(e.getMessage());
                  
                  return false;
           }
           
    }

}

