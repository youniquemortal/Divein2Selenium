package com.cigniti.utilities.java;

public class Reporters {

	static Property config = new Property("config.properties");
	
	public static void reportCreater() throws Throwable{
		int intReporterType=Integer.parseInt(config.getProperty("ReporterType"));
		switch (intReporterType) {
			case 1:
				ExcelReporter.excelTestReportCreator();
				break;
			case 2:
				HtmlReporters.htmlCreateReport();
				HtmlReporters.createDetailedReport();
				
				break;
			default:
				ExcelReporter.excelTestReportCreator();
				HtmlReporters.htmlCreateReport();
				break;
		}
	}
	
	public static void report(String strStepName, String strDescription, boolean blnStatus, String strClassName) throws Throwable{
		int intReporterType=Integer.parseInt(config.getProperty("ReporterType"));
		switch (intReporterType) {
			case 1:
				//ExcelReporter.excelReoprt(strStepName, strDescription, blnStatus, strClassName);
				break;
			case 2:
				/*if(! blnStatus)
				{
					
				}*/
				HtmlReporters.htmlReport(strStepName, strDescription, blnStatus, strClassName);
				break;

			default:
				//ExcelReporter.excelReoprt( strStepName, strDescription, blnStatus, strClassName);
				HtmlReporters.htmlReport(strStepName, strDescription, blnStatus, strClassName);
				break;
		}
	}
	
}
