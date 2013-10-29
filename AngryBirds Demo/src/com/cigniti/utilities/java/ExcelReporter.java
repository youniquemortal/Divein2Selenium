package com.cigniti.utilities.java;

import java.io.File;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.Pattern;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelReporter {
	
	 public static String strTestName="";
	 static WritableWorkbook workbook;
	 static WritableSheet sheet;
	 static Property config = new Property("config.properties");
	 public static String strFilePath=config.getProperty("ResultsPath")+".xls";
	 static File file=new File(strFilePath);
//	 To create excel sheet for reports
	public static void excelTestReportCreator() throws Throwable{
		try
		{
			
			if(file.exists()){
				file.delete();
			}
			workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet("Detailed report", 1);
			WritableSheet sheet2=workbook.createSheet("Summary report", 0);
			sheet.mergeCells(0,0, 15,0);
			sheet.mergeCells(0,3,10,3);
			sheet.mergeCells(0,5,5,5);
			sheet2.mergeCells(0,0, 15,0);
			sheet.addCell(new jxl.write.Label(0,0,"Test Results",Excel.getCellFormat(Colour.AQUA, Colour.WHITE, Pattern.getPattern(3),17,Alignment.CENTRE)));
			sheet2.addCell(new jxl.write.Label(0,0,"Test Summary",Excel.getCellFormat(Colour.AQUA, Colour.WHITE, Pattern.getPattern(3),17,Alignment.CENTRE)));
			sheet2.addCell(new jxl.write.Label(0,6,"Test case Name"));
			sheet2.addCell(new jxl.write.Label(1,6,"Result"));
			sheet2.addCell(new jxl.write.Label(6,1,"Summary Overview",Excel.getCellFormat(Colour.ICE_BLUE, Colour.BLACK, Pattern.getPattern(2),13,Alignment.CENTRE)));
			sheet.addCell(new jxl.write.Label(0,3,Accessories.osEnvironment()));
			sheet.addCell(new jxl.write.Label(0,5,"Start Time:"+Accessories.timeStamp()));
			workbook.write();
			workbook.close();
		}
		catch (Exception e) {
			System.out.println(e);
			workbook.write();
			workbook.close();
		}		
	}
		
//	To create header 
	public static void testHeaderDesign(String strTestName) throws Throwable{
		try
		{	
			if(file.exists()==false)
			{
				excelTestReportCreator();
			}
			Workbook workbook1 = Workbook.getWorkbook(file);
			WritableWorkbook copy = Workbook.createWorkbook(file, workbook1);
			sheet = copy.getSheet(1);
			int row=sheet.getRows()+1; 
			sheet.mergeCells(1,row+2, 3, row+2);
			sheet.addCell(new jxl.write.Label(0,row+2,"TestName : "));
			sheet.addCell(new jxl.write.Label(1,row+2,strTestName));
			sheet.addCell(new jxl.write.Label(0,row+3,"Action"));
			sheet.addCell(new jxl.write.Label(1,row+3,"Step Name"));
			sheet.addCell(new jxl.write.Label(2,row+3,"Description"));
			sheet.addCell(new jxl.write.Label(3,row+3,"Date"));
//			sheet.addCell(new jxl.write.Label(4,row+3,"Start Time"));
			sheet.addCell(new jxl.write.Label(4,row+3,"Time"));
			sheet.addCell(new jxl.write.Label(5,row+3,"Status"));
			copy.write();
			copy.close();
			}
		catch (Exception e) {
			System.out.println( e);
			workbook.write();
			workbook.close();
		}
	}
	

	public static void onSuccess(String action,String stepName, String description) throws Exception{
		try
		{
			if(file.exists()==false)
			{
				workbook = Workbook.createWorkbook(file);
				sheet = workbook.createSheet("Detailed report",1); 
				workbook.write();
				workbook.close();
			}
			Workbook workbook1 = Workbook.getWorkbook(file);
			WritableWorkbook copy = Workbook.createWorkbook(file, workbook1);
			sheet = copy.getSheet(1);
			int row=sheet.getRows();
			sheet.addCell(new jxl.write.Label(0,row,action,Excel.getCellFormat(Colour.LIGHT_GREEN,Colour.BLACK,Pattern.getPattern(1), 11, Alignment.LEFT)));
			sheet.addCell(new jxl.write.Label(1,row,stepName,Excel.getCellFormat(Colour.LIGHT_GREEN,Colour.BLACK,Pattern.getPattern(1), 11, Alignment.LEFT)));
			sheet.addCell(new jxl.write.Label(2,row,description,Excel.getCellFormat(Colour.LIGHT_GREEN,Colour.BLACK,Pattern.getPattern(1), 11, Alignment.LEFT)));
			sheet.addCell(new jxl.write.Label(3,row,Accessories.dateStamp(),Excel.getCellFormat(Colour.LIGHT_GREEN,Colour.BLACK,Pattern.getPattern(1), 11, Alignment.LEFT)));
//			sheet.addCell(new jxl.write.Label(4,row,time,Excel.getCellFormat(Colour.GREEN,Colour.BLACK,Pattern.getPattern(1), 11, Alignment.LEFT)));
			sheet.addCell(new jxl.write.Label(4,row,Accessories.timeStamp(),Excel.getCellFormat(Colour.LIGHT_GREEN,Colour.BLACK,Pattern.getPattern(1), 11, Alignment.LEFT)));
			sheet.addCell(new jxl.write.Label(5,row,"Passed",Excel.getCellFormat(Colour.LIGHT_GREEN,Colour.BLACK,Pattern.getPattern(1), 13, Alignment.LEFT)));
			copy.write();
			copy.close();
		}
		catch (Exception e) {
			System.out.println(e);
			workbook.write();
			workbook.close();
		}
	}
	
	
		

	public static void onFailure(String action,String stepName, String description) throws Exception{//
		try
		{
			if(file.exists()==false)
			{
				workbook = Workbook.createWorkbook(file);
				sheet = workbook.createSheet("Detailed report",1); 
				workbook.write();
				workbook.close();
			}
			Workbook workbook1 = Workbook.getWorkbook(file);
			WritableWorkbook copy = Workbook.createWorkbook(file, workbook1);
			sheet = copy.getSheet(1);
			int row=sheet.getRows();
			sheet.addCell(new jxl.write.Label(0,row,action,Excel.getCellFormat(Colour.RED,Colour.BLACK,Pattern.getPattern(1), 11, Alignment.LEFT)));
			sheet.addCell(new jxl.write.Label(1,row,stepName,Excel.getCellFormat(Colour.RED,Colour.BLACK,Pattern.getPattern(1), 11, Alignment.LEFT)));
			sheet.addCell(new jxl.write.Label(2,row,description,Excel.getCellFormat(Colour.RED,Colour.BLACK,Pattern.getPattern(1), 11, Alignment.LEFT)));
			sheet.addCell(new jxl.write.Label(3,row,Accessories.dateStamp(),Excel.getCellFormat(Colour.RED,Colour.BLACK,Pattern.getPattern(1), 11, Alignment.LEFT)));
//			sheet.addCell(new jxl.write.Label(4,row,startTime,Excel.getCellFormat(Colour.RED,Colour.BLACK,Pattern.getPattern(1), 11, Alignment.LEFT)));
			sheet.addCell(new jxl.write.Label(4,row,Accessories.timeStamp(),Excel.getCellFormat(Colour.RED,Colour.BLACK,Pattern.getPattern(1), 11, Alignment.LEFT)));
			sheet.addCell(new jxl.write.Label(5,row,"Failed",Excel.getCellFormat(Colour.RED,Colour.BLACK,Pattern.getPattern(1), 13, Alignment.LEFT)));
			copy.write();
			copy.close();
		}
		catch (Exception e) {
			System.out.println(e);
			workbook.write();
			workbook.close();
		}
	}
	public static void excelReoprt(String strAction,String strStepName, String strDescription ,Boolean blnStatus,String strClassName) throws Throwable{		
		if(!strClassName.equals(strTestName)||strAction.equals("Action1")){
			strTestName=strClassName;
			testHeaderDesign(strClassName);
			System.out.println(Excel.usedRows(strFilePath, "Summary report")+strTestName);
			Excel.WriteToCell(strFilePath, "Summary report", strTestName, 0, Excel.usedRows(strFilePath, "Summary report"), Colour.WHITE, 11,Pattern.getPattern(0));
			if(blnStatus){
				onSuccess(strAction, strStepName, strDescription);
				Excel.WriteToCell(strFilePath, "Summary report", "Passed", 1, Excel.usedRows(strFilePath, "Summary report")-1, Colour.GREEN, 11,Pattern.getPattern(1));
			}
			if(!blnStatus){
				onFailure(strAction, strStepName, strDescription);
				Excel.WriteToCell(strFilePath, "Summary report", "Failed", 1, Excel.usedRows(strFilePath, "Summary report")-1, Colour.RED, 11,Pattern.getPattern(1));
			}
		}
		else{
			if(blnStatus){
				
				onSuccess(strAction, strStepName, strDescription);
				if(Excel.getCellData(strFilePath, "Summary report", Excel.usedRows(strFilePath, "Summary report"), 1)!="Failed"){
					Excel.WriteToCell(strFilePath, "Summary report", "passed", 1, Excel.usedRows(strFilePath, "Summary report")-1, Colour.GREEN, 11,Pattern.getPattern(1));	
				}
			}
			if(!blnStatus){
				onFailure(strAction, strStepName, strDescription);
				Excel.WriteToCell(strFilePath, "Summary report", "Failed", 1, Excel.usedRows(strFilePath, "Summary report")-1, Colour.RED, 11,Pattern.getPattern(1));
			}
		}
		
	}

	
}
