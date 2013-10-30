package com.cigniti.automation.utilities;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cigniti.automation.accelerators.Base;


public class PushResultsToDB{

	public static List<String> stepStatus=new ArrayList<String>();
	static Map<String, String> summary=new LinkedHashMap<String, String>();
	Property config=new Property("config.properties");
	String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";  
	String serverName = config.getProperty("DBhost");// "localhost";  
	String portNumber = config.getProperty("DBport");//"1433";  
	String databaseName=config.getProperty("DBname");//"Sample";
	String username=config.getProperty("DBUsername");//
	String password=config.getProperty("DBPassword");//
	Connection con=null;
	public static String runId="";

	static String timeStamp=Accessories.timeStamp().replace(":", "_").replace(".", "_");
	/**
	 * Connecting to DB 
	 * @param serverName
	 * @param portNumber
	 * @param databaseName
	 * @param username
	 * @param password
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	// @Test
	public Statement connectionString(String serverName,String portNumber,String databaseName,String username,String password){
		try{

			String mydatabase = serverName + ":" + portNumber + ";databaseName="+databaseName; 

			String url = "jdbc:sqlserver://" + mydatabase + ";"; // a JDBC url 

			Class.forName(driverName);  

			con = DriverManager.getConnection(url,username,password);  

			Statement statement = con.createStatement();

			return statement;
		}
		catch(Exception e){
			System.out.println("unable to establesh connection to database "+e);
			return null;
		}

	}
	/**
	 * Close DB Connection
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public void closeDBConnection(){
		try{
			if(!con.isClosed())
			{
				con.close();
			}
		}
		catch(Exception e)
		{
			System.out.println("closeDBConnection : "+e);
		}
	}

	/**
	 * Push Detailed Report to Database
	 * @param scriptName
	 * @param tC_NAME
	 * @throws Exception
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public void pushDetailReportToDatabase(String scriptName,String tC_NAME) throws Exception
	{

		summary.put(tC_NAME,"PASS");
		try{
			Statement st=connectionString(serverName, portNumber, databaseName, username, password);
			Iterator<String> itr=stepStatus.iterator();
			while(itr.hasNext())
			{

				String[] str=itr.next().toString().split(",");
				if((str[2].trim().equals("false")))
				{
					summary.put(tC_NAME, "FAIL");

				}
				st.executeUpdate("INSERT INTO Detailed_Report(RUN_ID, SCRIPT_NAME, TC_NAME, STEP_NAME, STEP_DESC, STEP_STATUS)" + "VALUES('"+runId+"','"+scriptName+"','"+tC_NAME+"','"+str[0]+"','"+str[1]+"','"+str[2]+"')");
			}
			stepStatus.clear();
		}
		catch(Exception e)
		{
			System.out.println("pushDetailReportToDatabase : "+e);
		}
		finally
		{
			try
			{
				closeDBConnection();
			}
			catch(Exception ex)
			{

			}
		}
	}
	/**
	 * Push summary to Data base
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public void pushSummaryToDB(){
		try{
			Iterator iterator=summary.entrySet().iterator();
			while(iterator.hasNext())
			{
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				String key = mapEntry.getKey().toString();
				String value= mapEntry.getValue().toString();
				Statement str=connectionString(serverName, portNumber, databaseName, username, password);
				str.executeUpdate("INSERT INTO Summary_Report(RUN_ID, TC_NAME, TC_STATUS)" + "VALUES('"+runId+"','"+key+"','"+value+"')");

			}

			summary.clear();
		}

		catch (Exception e) {
			System.out.println("pushSummaryToDB : "+e);
		}
		finally
		{
			closeDBConnection();
		}
	}
	/**
	 * Creating summary tables
	 * @param run_ID
	 * @throws SQLException
	 * @throws Exception
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public void summaryTable(String run_ID) throws SQLException, Exception{
		ResultSet resultSet;
		ResultSet detailedResultSet;
		Map result=new LinkedHashMap<String, String>();
		try
		{

			Statement stt=connectionString(serverName, portNumber, databaseName, username, password);
			resultSet = stt.executeQuery("SELECT TC_NAME,TC_STATUS FROM Summary_Report  WHERE RUN_ID IN('"+run_ID+"')	ORDER BY S_NO"); 


			while(resultSet.next())
			{
				result.put(resultSet.getString("TC_NAME"),resultSet.getString("TC_STATUS"));

			}
			System.out.println(result);
		}
		finally
		{
			closeDBConnection();
		}
		File file = new File("Results//"+Base.filePath()+"SummaryResults"+"_"+timeStamp+".html");//"SummaryReport.html"

		Writer writer=null;

		if(file.exists())
		{
			file.delete();
		}
		System.out.println(result);
		writer = new FileWriter(file);
		System.out.println(111111111);	
		try{
			writer.write("<HTML>");
			writer.write("<HEAD>");
			writer.write("<script type='text/javascript' src='excanvas.js'></script>");
			writer.write("<script type='text/javascript' src='piecanvas.js'></script>");
			writer.write("</HEAD>");
			writer.write("<BODY>");
			writer.write("<TABLE border=0 cellSpacing=1 cellPadding=1 width='100%'>");
			writer.write("<TR><TD align='left'><img src='file://"+config.getProperty("Logos")+"//BankofTheWest.png' alt='Bank of the West' height='75' width='150'> </TD>");
			writer.write("<TD align='center'><H4 align='center'><font color='660066' face='arial' color='#E0E0E0' size=5><b>Automation Test Results</b></H4></TD>");
			writer.write("<TD align='right'><img src='file://"+config.getProperty("Logos")+"//cigniti_logo.png' alt='Cigniti' height='75' width='150'>  </TD></TR>");
			writer.write("<TABLE border=1 cellSpacing=1 cellPadding=1 width='35%' font='arial'>");		 
			writer.write("<tr><td colspan='2' align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2><b>Environment</b></td></tr>");
			writer.write("<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>Host Name</b></td>");
			writer.write("<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"+Accessories.getHostName()+"</b></td></tr>");
			writer.write("<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>OS Name</b></td>");
			writer.write("<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"+System.getProperty("os.name")+"</b></td></tr>");
			writer.write("<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>OS Version</b></td>");
			writer.write("<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"+System.getProperty("os.version")+"</b></td></tr>");
			writer.write("<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>RUN_ID</b></td>");
			writer.write("<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"+run_ID+"</b></td></tr>");
			writer.write("</table>");
			writer.write("<table>");
			writer.write("<TABLE border=1 cellSpacing=1 cellPadding=1 width='35%' font='arial' align='left' >");		 
			writer.write("<tr><td colspan='2' align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2><b>Execution Status</b></td></tr>");
			int pCount=0;
			int fCount=0;
			Iterator iterator=result.entrySet().iterator();
			while(iterator.hasNext()) 
			{
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				String value= mapEntry.getValue().toString();
				if(value.equals("PASS"))
				{
					pCount+=1;
				}
				else
				{
					fCount+=1;       			  
				}

				writer.write("</tr>");
			}
			writer.write("<tr>");
			writer.write("<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>No. of Executed Scripts</b></td>");
			writer.write("<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"+result.size()+"</b></td></tr>");
			writer.write("<tr>");
			writer.write("<tr><td width=150 align='left' ><FONT 'FACE='Arial' SIZE=1.85><b>No. of Passed Scripts</b></td>");
			writer.write("<td width=150 align='left' color='#E0E0E0'><FONT FACE='Arial' SIZE=1.85><b>"+ pCount +"</b></td></tr>");
			writer.write("<tr>");
			writer.write("<tr><td width=150 align='left'><FONT  FACE='Arial' SIZE=1.85><b>No. of Failed Scripts</b></td>");
			writer.write("<td width=150 align='left'color='red' ><FONT FACE='Arial' SIZE=1.85><b>"+ fCount +"</b></td></tr>");
			writer.write("</tr></table>");	 
			writer.write("<br/><br/><br/>");
			writer.write("</table><br/><br/><br/>");
			writer.write("<TABLE border=1 cellSpacing=1 cellPadding=1 width='50%' font='arial'>");		 
			writer.write("<tr><td colspan='2' align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2><b>Summary Report</b></td></tr>"); 
			Iterator iterator1=result.entrySet().iterator();
			while(iterator1.hasNext())
			{
				Map.Entry mapEntry1 = (Map.Entry) iterator1.next();
				String key = mapEntry1.getKey().toString();
				String value= mapEntry1.getValue().toString();
				writer.write("<tr>");
				writer.write("<td><FONT color=#153e7e size=1 face=Arial><B>"+key+"</B></td>");//config.getProperty("ResultsPath")+".html"    +config.getProperty("ResultsPath")+
				if(value.equals("PASS"))
				{
					writer.write("<TD width='30%' bgcolor=green align=center><FONT color=white size=1 face=Arial><B><a href='Results"+"_"+timeStamp+".html#"+key+"'>"+value+"</a></B></td>"); 
				}
				else
				{
					writer.write("<TD width='30%' bgcolor=red align=center><FONT color=white size=1 face=Arial><B><a href='Results"+"_"+timeStamp+".html#"+key+"'>"+value+"</a></B></td>");	
				}

				writer.write("</tr>");
			}

			writer.write("</table></BODY></HTML>");
			writer.flush();  
			writer.close();

			writer = new FileWriter(file,true);
			result.clear();
		}

		catch (Exception e) {
			System.out.println(e);
			writer.flush();
			writer.close();
		}
	}

	/**
	 * Detailed Report
	 * @param run_ID
	 * @throws Exception
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public void detailedreport(String run_ID) throws Exception
	{
		ResultSet detailedResultSet;
		try
		{
			Statement std=connectionString(serverName, portNumber, databaseName, username, password);
			detailedResultSet = std.executeQuery("SELECT  SCRIPT_NAME,TC_NAME,STEP_NAME ,STEP_DESC, STEP_STATUS FROM Detailed_Report  " +
					"WHERE RUN_ID IN('"+run_ID+"') ORDER BY S_NO"); 


			while(detailedResultSet.next())
			{

				//HtmlReporters.htmlReport(detailedResultSet.getString("STEP_NAME"),detailedResultSet.getString("STEP_DESC"),
				//Boolean.valueOf(detailedResultSet.getString("STEP_STATUS")),detailedResultSet.getString("TC_NAME"),timeStamp);

			}
		}
		finally
		{
			closeDBConnection();
		}
	}
}






