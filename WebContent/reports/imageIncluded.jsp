

<!--




Tutorial report
This report was done for as a sample for 
1.Dynamic image support feature 
2.Multiple Datasource Support feature
of crystal-eclipse integration
follow the steps bellow
1.create report using domain
2.add picture to report body
3.right click on picure add conditional formula->grapic Location-> then give the url field of your domain
finally,
create jsp set data source and run the JSP
and that's All :) 
Author :sanka









-->








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


<%@page import="com.dspl.malkey.service.FaccessorySRV"%>
<%@page import="com.dspl.malkey.domain.Faccessory"%>
<%@page import="com.dspl.malkey.domain.Fothersrv"%>
<%@page import="com.dspl.malkey.service.FothersrvSRV"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%------------Beginning of 'Open and View Report' snippet----------------%>
<%
	//Use the relative path to the report; the physical or full qualified URL cannot be used.
	
		
	String reportName = "reports/imageIncluded.rpt";
	Object reportSource =null; //session.getAttribute("Report7");
		//---------- Create a ReportClientDocument -------------
		com.crystaldecisions.sdk.occa.report.application.ReportClientDocument reportClientDocument = new com.crystaldecisions.sdk.occa.report.application.ReportClientDocument();
		reportClientDocument.setReportAppServer(com.crystaldecisions.sdk.occa.report.application.ReportClientDocument.inprocConnectionString);
		reportClientDocument.open(reportName, 0);
		DatabaseController dbc = reportClientDocument.getDatabaseController();
		
		
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
	    try
	    {
	    	FaccessorySRV srv = (FaccessorySRV) wac.getBean("faccessorySRV");
	    	FothersrvSRV srv2 = (FothersrvSRV) wac.getBean("fothersrvSRV");
		/*Following code retrive data from service*/
		List<Faccessory> lst= srv.ListAll();
		List<Fothersrv> lst2= srv2.ListAll();
		/*Following code set data to report*/
		dbc.setDataSource(lst, Faccessory.class, "Faccessory","Faccessory");
		dbc.setDataSource(lst2, Fothersrv.class, "Fothersrv","Fothersrv");
		
		
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
		
		
		reportSource = reportClientDocument.getReportSource();

	//---------- Create the viewer and render the report -------------

	//Create the CrystalReportViewer object
	com.crystaldecisions.report.web.viewer.CrystalReportViewer crystalReportViewer = new com.crystaldecisions.report.web.viewer.CrystalReportViewer();

	//Set the reportsource property of the viewer
	crystalReportViewer.setReportSource(reportSource);

	//Set viewer attributes
	crystalReportViewer.setOwnPage(true);

	//Set the CrystalReportViewer print mode
	crystalReportViewer
			.setPrintMode(com.crystaldecisions.report.web.viewer.CrPrintMode.PDF);

	//Process the report
	crystalReportViewer.processHttpRequest(request, response,
			getServletConfig().getServletContext(), null);
%><%------------End of 'Open and View Report' snippet----------------%>

</body>
</html>