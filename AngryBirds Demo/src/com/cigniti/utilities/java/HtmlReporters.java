package com.cigniti.utilities.java;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;


public class HtmlReporters  {
	
	public static String strTestName="";
	
	public static String startedAt=Accessories.timeStamp().replace(":", "_").replace(" ","_").replace(".","");
	
	 static Property config = new Property("config.properties");
	 
	 static String workingDir = "";
	 public static void createDetailedReport() throws Exception
	 {
		 String[] s=Accessories.timeStamp().split(":");
			for(int i=0;i<s.length-1;i++){
				startedAt=startedAt+"-"+s[i];
			}
			startedAt=startedAt.substring(1,startedAt.length());
			startedAt=startedAt.replace(" ", "_");
		 File file = new File(config.getProperty("ResultsPath")+"_"+startedAt+".html"); 
		 System.out.println("FR : 1 "+file);
		 Writer writer = new FileWriter(file,true);
		 writer.write("<HTML>");
		 writer.write("<BODY>");
		 writer.write("<TABLE border=0 cellSpacing=1 cellPadding=1 width='100%'>");
		 workingDir ="C://Demo//HPS_Mobile_Demo";
		 writer.write("<TR><TD align='left'><img src='file:///"+workingDir+"/Logos/"+config.getProperty("Clint_logo")+".png' alt='"+config.getProperty("Clint_logo")+"' height='75' width='150'> </TD>");
			writer.write("<TD align='center'><H4 align='center'><font color='660066' face='arial' color='#E0E0E0' size=5><b>Detailed report</b></H4></TD>");
			writer.write("<TD align='right'><img src='file:///"+workingDir+"/Logos/cigniti.png' alt='Cigniti' height='75' width='150'>  </TD></TR>");
		 
		 writer.write("</TABLE>");
		 writer.write("</BODY>");
		 writer.write("</HTML>");
		 writer.flush();  
		 writer.close();
	 }
	 
	@SuppressWarnings("unchecked")
	public static void createHtmlSummaryReport() throws Exception{
		 File file = new File(config.getProperty("SummaryPath")+"_"+startedAt+".html");//"SummaryReport.html"
		Writer writer = null;
		
		if(file.exists())
		{
			file.delete();
		}
		
		//Create Summary report first table
		
		 writer = new FileWriter(file,true); 
		 try{
		 writer.write("<HTML>");
		 writer.write("<HEAD>");
		 writer.write("<script type='text/javascript' src='excanvas.js'></script>");
		 writer.write("<script type='text/javascript' src='piecanvas.js'></script>");
		 writer.write("</HEAD>");
		 writer.write("<BODY>");
		 writer.write("<TABLE border=0 cellSpacing=1 cellPadding=1 width='100%'>");
		
		 writer.write("<TR><TD align='left'><img src='"+workingDir+config.getProperty("Logos")+"\\cigniti_logo.png' alt='HPS' height='75' width='150'> </TD>");
		 writer.write("<TD align='center'><H4 align='center'><font color='660066' face='arial' color='#E0E0E0' size=5><b>Automation Test Results</b></H4></TD>");
		 writer.write("<TD align='right'><img src='"+workingDir+config.getProperty("Logos")+"\\cigniti_logo.png' alt='Cigniti' height='75' width='150'>  </TD></TR>");
		 writer.write("<TABLE border=1 cellSpacing=1 cellPadding=1 width='35%' font='arial'>");		 
		 writer.write("<tr><td colspan='2' align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2><b>Environment</b></td></tr>");
		 writer.write("<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>Mobile</b></td>");
		 writer.write("<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>IPhone 4S</b></td></tr>");
		 writer.write("<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>OS Name</b></td>");
		 writer.write("<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>IOS</b></td></tr>");
		 writer.write("<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>OS Version</b></td>");
		 writer.write("<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b> 5.1.1 (9B206)</b></td></tr>");
		 writer.write("<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>Suite</b></td>");
		 writer.write("<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>HPS_Mobile</b></td></tr>");
		 
		 writer.write("</table>");

		 
		 BufferedReader br = new BufferedReader( new FileReader("tempSummary.csv"));
		 String strLine = "";
		 
		 Dictionary dictSummary = new Hashtable();
		 		 		 
		 while( (strLine = br.readLine()) != null)
         {			 
			 String[] str=strLine.split(",");
			 
			 int keyFound=0;
			 
			 for (Enumeration e = dictSummary.keys(); e.hasMoreElements();) 
			 {
				 String key = e.nextElement().toString();
				 if(key.equals(str[0]))
				 {
					 if(str[1].equals("FAIL"))
					 {
						 dictSummary.remove(key);
						 dictSummary.put(str[0],str[1]);						 
					 }
					 keyFound=1;
				 }				 
			 }
			 
			 if(keyFound==0 || dictSummary.size()==0)
			 {
				 dictSummary.put(str[0],str[1]);
			 }
         }
		 
		 br.close();	
		 
		//Create Summary report Second table
		 writer.write("<table>");
		 writer.write("<TABLE border=1 cellSpacing=1 cellPadding=1 width='35%' font='arial' align='left' >");		 
		 writer.write("<tr><td colspan='2' align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2><b>Execution Status</b></td></tr>");
		 
		 int pCount=0;
		 int fCount=0;
		 for (Enumeration e1 = dictSummary.keys(); e1.hasMoreElements();) 
		 {
//                         
			 String key = e1.nextElement().toString();
			// String value=dictSummary.get(key).toString();
			 
			 if(dictSummary.get(key).toString().equals("PASS"))
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
		 writer.write("<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"+dictSummary.size()+"</b></td></tr>");
		 writer.write("<tr>");
		 writer.write("<tr><td width=150 align='left' ><FONT 'FACE='Arial' SIZE=1.85><b>No. of Passed Scripts</b></td>");
		 writer.write("<td width=150 align='left' color='#E0E0E0'><FONT FACE='Arial' SIZE=1.85><b>"+ pCount +"</b></td></tr>");
		 writer.write("<tr>");
		 writer.write("<tr><td width=150 align='left'><FONT  FACE='Arial' SIZE=1.85><b>No. of Failed Scripts</b></td>");
		 writer.write("<td width=150 align='left'color='red' ><FONT FACE='Arial' SIZE=1.85><b>"+ fCount +"</b></td></tr>");
		 writer.write("</tr></table>");	 
		 writer.write("<br/><br/><br/>");
		 writer.write("</table><br/><br/><br/>");
		 
		//Create Summary report third table
		 writer.write("<TABLE border=1 cellSpacing=1 cellPadding=1 width='50%' font='arial'>");		 
		 writer.write("<tr><td colspan='2' align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2><b>Summary Report</b></td></tr>"); 
		  
		 
		 for (Enumeration e1 = dictSummary.keys(); e1.hasMoreElements();) 
		 {
			 String key = e1.nextElement().toString();
			 String value=dictSummary.get(key).toString();
			 writer.write("<tr>");
			 writer.write("<td><FONT color=#153e7e size=1 face=Arial><B>"+key+"</B></td>");//config.getProperty("ResultsPath")+".html"    +config.getProperty("ResultsPath")+
			 if(dictSummary.get(key).toString().equals("PASS"))
			 {
				writer.write("<TD width='30%' bgcolor=green align=center><FONT color=white size=1 face=Arial><B><a href='Results_"+startedAt+".html#"+key+"'>"+value+"</a></B></td>"); 
			 }
			 else
			 {
				writer.write("<TD width='30%' bgcolor=red align=center><FONT color=white size=1 face=Arial><B><a href='Results_"+startedAt+".html#"+key+"'>"+value+"</a></B></td>");	
			 }
				
			 writer.write("</tr>");
		 }
		 
		 writer.write("</table></BODY></HTML>");
		 writer.flush();  
		 writer.close();
		 
		 File filecsv= new File("tempSummary.csv");
		 if(filecsv.exists())
			{
				filecsv.delete();
			}
			 writer = new FileWriter(file,true);
		 }
		 catch (Exception e) {
			 System.out.println(e);
			 writer.flush();
			 writer.close();
		}
	}
	 
	 //Crate a report file
	public static void htmlCreateReport() throws Exception
	{
		File file = new File(config.getProperty("ResultsPath")+"_"+startedAt+".html");//"Results.html"
		
		if(file.exists())
		{
			file.delete();
		}

	}

	public static void htmlReport(String strStep, String strDescription, Boolean blnStatus, String strClassName)throws Exception 
	{
		Writer writer = null;
		
		System.out.println(strClassName);
		
		try
		{
			File file = new File(config.getProperty("ResultsPath")+"_"+startedAt+".html");//"Results.html"
			writer = new FileWriter(file,true);
			
			if(strClassName.contains(".")){
				String[] ss=strClassName.split("\\.");
				strClassName=ss[ss.length-2]+" : "+ss[ss.length-1];
			}
			System.out.println(strClassName);
			System.out.println(strTestName);
			if(!strClassName.equals(strTestName))
			{
				writer.write("<div name='"+strClassName+"'><TABLE border=1 cellSpacing=1 cellPadding=1 width='80%' font='arial'>");
				writer.write("<tr ><td colspan=3><H4 align=center><FONT color=black size=4 face=Arial><B>" +strClassName + "</B></H4></td></tr>");
				writer.write("<tr><TD bgColor='#153e7e' width='30%' align=middle><FONT color='#e0e0e0' size=2 face='Arial'><B>Step</b></td>" 						
						+"<TD bgColor=#153e7e align=middle><FONT color=#e0e0e0 size=2 face=Arial><B>" +"Description</b></td>"						 
						+"<TD bgColor=#153e7e width='10%' align=middle><FONT color=#e0e0e0 size=2 face=Arial><B>Status</b></td></tr>");
				strTestName=strClassName;
			}
			
			
			FileWriter fw = new FileWriter("tempSummary.csv",true);
	   		PrintWriter out = new PrintWriter(fw);
	   		
			if (blnStatus)
			{
				
				writer.write("<tr><TD width='30%'><FONT color=#153e7e size=1 face=Arial><B>" + strStep
						+ " </b></td><TD><FONT color=#153e7e size=1 face=Arial><B>" + strDescription + "</B></td>"
						+ " </td><TD width='10%' bgcolor=green align=center><FONT color=white size=1 face=Arial><B>" + "Passed" + "</td></tr>");
				
				
				
				out.println(strClassName+",PASS");
				out.flush();
				
				
						
			}
			if(!blnStatus)
			{
				String strDesc=strDescription.substring(0,strDescription.indexOf(":")).replace(" ","_")+"_"+startedAt;
				GenericUtils.getScreenOnFail(strDesc);
						
				writer.write("<tr><TD width='30%'><FONT color=#153e7e size=1 face=Arial><B>" + strStep
						+ " </b></td><TD><FONT color=#153e7e size=1 face=Arial><B>" + strDescription + "</B></td>"
						+ " </td><TD width='10%' bgcolor=red align=center><FONT color=white size=1 face=Arial><B><a href='"+strDesc+ ".png'>Failed" + "</a></td></tr>");
				
				 
				
				out.println(strClassName+",FAIL");
				out.flush();
				
			}
			
			out.close();
			fw.close();
			writer.flush();  
			writer.close(); 
	
		}	
			

		catch(FileNotFoundException e)
		{
			e.toString();
		}
	
    }
	
	
}

