package com.cigniti.automation.utilities;

import com.cigniti.automation.accelerators.Actiondriver;
import com.cigniti.automation.accelerators.Base;

public class Reporters{

	public static Property configProps=new Property("config.properties");
	static String  timeStamp=Accessories.timeStamp().replace(":", "_").replace(".", "_");

	public static void reportCreater() throws Throwable{
		int intReporterType=Integer.parseInt(configProps.getProperty("reportsType"));

		switch (intReporterType) {
		case 1:
			//ExcelReporter.excelTestReportCreator();
			break;
		case 2:
			HtmlReporters.htmlCreateReport();
			HtmlReporters.createDetailedReport();

			break;
		default:
			//ExcelReporter.excelTestReportCreator();
			HtmlReporters.htmlCreateReport();
			break;
		}
	}

	public static void SuccessReport(String strStepName, String strStepDes) throws Throwable{
		int intReporterType=Integer.parseInt(configProps.getProperty("reportsType"));
		switch (intReporterType) {
		case 1:

			break;
		case 2:

			HtmlReporters.onSuccess(strStepName, strStepDes);

			break;

		default:

			HtmlReporters.onSuccess(strStepName, strStepDes);
			break;
		}
	}	

	public static void failureReport(String strStepName, String strStepDes) throws Throwable{
		int intReporterType=Integer.parseInt(configProps.getProperty("reportsType"));
		switch (intReporterType) {
		case 1:

			break;
		case 2:

			Actiondriver.screenShot(Base.filePath()+strStepDes.replace(" ", "_").replace(":", "_")+"_"+Base.timeStamp+".jpeg");

			HtmlReporters.onFailure(strStepName, strStepDes);

			break;

		default:
			Actiondriver.screenShot(Base.filePath()+strStepDes.replace(" ", "_")+"_"+Base.timeStamp+".jpeg");				
			HtmlReporters.onFailure(strStepName, strStepDes);
			break;
		}
	}
}
