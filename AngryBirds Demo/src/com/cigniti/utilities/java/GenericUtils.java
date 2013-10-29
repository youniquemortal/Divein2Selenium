package com.cigniti.utilities.java;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GenericUtils extends ExpireSuit{
	public boolean click(String zone,String element,int index,int count) throws Throwable
	{

		try
		{
			Thread.sleep(2000);
			client.click(zone, element, index, count);
			return true;

		}
		catch(Exception ex)
		{
			System.out.println("Unable to click");
			System.out.println("Error: "+ex.getMessage());
			return false;
		}
	}
	public boolean clickIn(String zone,String elementname,int index, String direction,String elementzone,String number,int width,int hight) throws Throwable{
		try{

			client.clickIn(zone, elementname, index, direction, elementzone, number,width, hight);
			return true;

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean swipeWhileNotFound() throws Throwable
	{
		if(client.swipeWhileNotFound("Right", 20, 2000, "default", "ICICI Icon", 1000, 5, true)){
			return true;

		}
		else
		{
			return false;
		}
	}
	
	
	
	/*
	 *   client.swipe("Down", 200, 500);*/
	public boolean swipeDown(int x, int y) throws Throwable
	{
		try{
			client.swipe("Down", x, y);
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	//swipe left
	public boolean swipeLeft(int x, int y) throws Throwable
	{
		try{
			client.swipe("Left", x, y);
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean waitForElement(String zone,String element,int index,int count) throws Throwable
	{
		if(client.waitForElement(zone,element,index,count)){
			return true;

		}
		else
		{
			return false;
		}
		
	}

	public boolean SendText(String text) throws Throwable
	{
		try{

			client.sendText(text);
			return true;

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	public boolean drag(String zone,String name,int int1,int int2,int int3) throws Throwable{
		try{
			client.drag(zone, name, int1, int2, int3);
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	public int GetElementCount(String zone,String name) throws Throwable
	{

		return client.getElementCount(zone, name);
	}

	public void sleep(int time) throws Throwable
	{
		client.sleep(time);
	}

	public boolean Home() throws Throwable{
		try{
			client.sendText("{HOME}");
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	public boolean isElementFound(String zone,String name,int index ) throws Throwable{

		try{
			client.isElementFound(zone, name, index);
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean applicationClose() throws Throwable{
		try{

			client.applicationClose(configProps.getProperty("applicationClose")) ;;
			return true;

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static void getScreenOnFail(String imageName) throws IOException
	{

		for(Object o:client.getLastCommandResultMap().keySet())
		{
			if(o.toString().equals("outFile"))
			{
				System.out.println(client.getLastCommandResultMap().get(o).toString());
				/* Create new Image File Object */
				File origionalImage = new File(client.getLastCommandResultMap().get(o).toString());

				/* Read the image */
				BufferedImage bufferedImage = ImageIO.read(origionalImage);

				/* Creating an Instance for different Image types */
				File myPngImage  = new File("C:\\Users\\ctl-user\\Desktop\\Suresh\\HPS_Mobile_Demo\\Results\\"+imageName+".png");



				/* Writing different images using write() method of ImageIO class that takes three parameters
               Origional Image Buffer, type of image and new Image file which have to change */
				ImageIO.write(bufferedImage, "png", myPngImage);
				break;

			}
		}
	}
}
