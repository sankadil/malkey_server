<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page	import="com.crystaldecisions.sdk.occa.report.application.DatabaseController"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.beans.factory.BeanFactory"%>
<%@page import="org.springframework.beans.factory.xml.XmlBeanFactory"%>
<%@page import="org.springframework.core.io.FileSystemResource"%>
<%@page import="java.util.List"%>

<%@ page import="
com.crystaldecisions.report.web.viewer.CrystalReportViewer,
com.crystaldecisions.sdk.occa.report.application.OpenReportOptions,
com.crystaldecisions.sdk.occa.report.application.ReportClientDocument,
com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase,
java.util.ArrayList,
java.util.List" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Calendar"%>


<%@page import="com.dspl.malkey.service.FreservationSRV"%>
<%@page import="com.crystaldecisions.reports.queryengine.collections.Fields"%>
<%@page import="com.crystaldecisions.sdk.occa.report.data.ParameterField"%>
<%@page import="com.businessobjects12.prompting.objectmodel.common.Values"%>
<%@page import="com.crystaldecisions.sdk.occa.report.data.ParameterFieldDiscreteValue"%>

<%@page import="com.dspl.malkey.util.CRJavaHelper"%>
<%@page import="com.dspl.malkey.domain.Freservationdiaryrpt"%>
<%@page import="com.crystaldecisions.sdk.occa.report.application.ParameterFieldController"%>
<%@page import="com.dspl.malkey.util.PublishCR"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reservation Diary</title>
</head>
<body>
<%------------Beginning of 'Open and View Report' snippet----------------%>
<%
	//Use the relative path to the report; the physical or full qualified URL cannot be used.
	
	if(request.getParameter("df")==null || request.getParameter("dt")==null  || request.getParameter("hs")==null )
	{
		
		out.println("Please click the view button in Application.");
		System.out.println("catch exception");
		return;
		}
	/*
	if(request.getParameter("df").equals(null) || request.getParameter("dt").equals(null)  || request.getParameter("hs").equals(null) )
	{
		System.out.println("catch exception");
		return;
		}*/
		
	String reportName = "reports/ReservationDiary.rpt";
	Object reportSource =null; //session.getAttribute("Report7");
		//---------- Create a ReportClientDocument -------------
		com.crystaldecisions.sdk.occa.report.application.ReportClientDocument reportClientDocument = new com.crystaldecisions.sdk.occa.report.application.ReportClientDocument();
		reportClientDocument.setReportAppServer(com.crystaldecisions.sdk.occa.report.application.ReportClientDocument.inprocConnectionString);
		reportClientDocument.open(reportName, 0);
		DatabaseController dbc = reportClientDocument.getDatabaseController();
		
		
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
	    try
	    {
	    
	    FreservationSRV srv = (FreservationSRV) wac.getBean("freservationSRV");
		/*Following code retrive data from service*/
		//Fgatepass fgatepass= srv.findByPassNo(request.getParameter("pn").toString());
		System.out.println("before exception");
		List<Freservationdiaryrpt> fresdiaryrecs= srv.getResDiaryData(request.getParameter("df").toString(),request.getParameter("dt").toString(),"",request.getParameter("hs").toString());
		System.out.println("after exception");
		//List<Freservationdiaryrpt> fresdiaryrecs= srv.getResDiaryData(request.getParameter("df").toString(),request.getParameter("dt").toString(),"","'CONFIRMED','PREPARED'");
		/*Following code set data to report*/
		//dbc.setDataSource(lst, CommMF.class, "Fmasterlist","Fmasterlist");
		if(fresdiaryrecs==null || fresdiaryrecs.size()==0)
		{
			out.println("No results Found");
			System.out.println("No result Validation exception");
			return;
		}
		for(Freservationdiaryrpt template :fresdiaryrecs)
		{
			if(template.getHirestatus().equalsIgnoreCase("CONFIRMED")){template.setHirestatus("C");}
			else if(template.getHirestatus().equalsIgnoreCase("PREPARED")){template.setHirestatus("P");}
			else if(template.getHirestatus().equalsIgnoreCase("BOOKED")){template.setHirestatus("B");}
			
		}
		dbc.setDataSource(fresdiaryrecs, Freservationdiaryrpt.class, "Freservationdiaryrpt", "Freservationdiaryrpt");
		
		ParameterFieldController paramController;
   		paramController = reportClientDocument.getDataDefController().getParameterFieldController();
   		paramController.setCurrentValue("", "df", request.getParameter("df").toString());
   		paramController.setCurrentValue("", "dt", request.getParameter("dt").toString());
   		
		//CRJavaHelper cr = new CRJavaHelper();
		//cr.exportPDF(reportClientDocument,response,false);
   		PublishCR cr=new PublishCR();
		cr.publishToPDF(reportClientDocument, session, response, out);
	    }
	    catch(Exception e)
	    {
	    	System.out.println("JSP Error: " + e.getMessage());
	    	e.printStackTrace();
	    }
	    finally
	    {
	    	reportClientDocument.close();
	    }

	
%><%------------End of 'Open and View Report' snippet----------------%>

</body>
</html>