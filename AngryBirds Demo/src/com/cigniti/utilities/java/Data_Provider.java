package com.cigniti.utilities.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class Data_Provider {

	public static Property configProps=new Property("config.properties");
	/**
	 * Read data from excel sheet using data provider
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */

	public static String[][] getTableArray( String sheetName, String testCaseName) throws Exception{
		try{
			Workbook workbook = Workbook.getWorkbook(new File("TestData\\TestData.xls"));
			Sheet sheet = workbook.getSheet(sheetName); 
			Cell tableStart=sheet.findCell(testCaseName);
			int startRow=tableStart.getRow();
			int startCol=tableStart.getColumn();

			Cell tableEnd= sheet.findCell(testCaseName, startCol+1,startRow+1, 100, 64000,  false);                

			int endRow=tableEnd.getRow();
			int endCol=tableEnd.getColumn();
			System.out.println("startRow="+startRow+", endRow="+endRow+", " +
					"startCol="+startCol+", endCol="+endCol);
			String[][] tabArray=new String[endRow-startRow][endCol-startCol-1];
			int ci=0;

			for (int i=startRow;i<=endRow-1;i++,ci++){
				int cj=0;
				for (int j=startCol+1;j<endCol;j++,cj++){
					tabArray[ci][cj]=sheet.getCell(j,i).getContents();
				}
			}

			return(tabArray);
		}
		catch (Exception e) {
			System.out.println(e+Thread.currentThread().getStackTrace()[1].getClassName()+" dataprovider");
			return null;
		}

	}
	/**
	 * Append data to CSV File
	 * @param fileName
	 * @param scriptName
	 * @param appendRowData
	 * @throws Exception
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static void appendDataToCSVFile(String fileName,String scriptName,String appendRowData) throws Exception
	{
		BufferedReader readCSVFile = new BufferedReader(new FileReader(configProps.getProperty("TestData")+fileName+".csv"));
		String dataRow = readCSVFile.readLine();
		ArrayList<String> row=new ArrayList<String>();

		while (dataRow != null){

			if(dataRow.contains(scriptName))
			{
				row.add(dataRow);
				dataRow = readCSVFile.readLine();
				row.add(dataRow);
				dataRow = readCSVFile.readLine();
				row.add(appendRowData);
				while (!dataRow.contains(scriptName)){
					row.add(dataRow);
					dataRow = readCSVFile.readLine();
				}
			}

			row.add(dataRow);
			dataRow = readCSVFile.readLine();              
		}

		readCSVFile.close();

		FileWriter  writeCSVFile = new FileWriter (configProps.getProperty("TestData")+fileName+".csv",false);
		for(String str:row)
		{
			writeCSVFile.write(str);
			writeCSVFile.write("\n");
			writeCSVFile.flush();
		}

		writeCSVFile.close();
	}

}
