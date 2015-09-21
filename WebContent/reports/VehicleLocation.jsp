

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


<%@page import="com.dspl.malkey.service.FvehicleSRV"%>
<%@page import="com.crystaldecisions.reports.queryengine.collections.Fields"%>
<%@page import="com.crystaldecisions.sdk.occa.report.data.ParameterField"%>
<%@page import="com.businessobjects12.prompting.objectmodel.common.Values"%>
<%@page import="com.crystaldecisions.sdk.occa.report.data.ParameterFieldDiscreteValue"%>

<%@page import="com.dspl.malkey.util.CRJavaHelper"%>
<%@page import="com.dspl.malkey.domain.Freservationdiaryrpt"%>
<%@page import="com.crystaldecisions.sdk.occa.report.application.ParameterFieldController"%>
<%@page import="com.dspl.malkey.domain.Fexpirationlistingrpt"%>
<%@page import="com.dspl.malkey.domain.Fvehicle"%>
<%@page import="com.dspl.malkey.util.PublishCR"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%------------Beginning of 'Open and View Report' snippet----------------%>
<%
	//Use the relative path to the report; the physical or full qualified URL cannot be used.
	
		
	String reportName = "reports/VehicleSummaryLocation.rpt";
	Object reportSource =null; //session.getAttribute("Report7");
		//---------- Create a ReportClientDocument -------------
		com.crystaldecisions.sdk.occa.report.application.ReportClientDocument reportClientDocument = new com.crystaldecisions.sdk.occa.report.application.ReportClientDocument();
		reportClientDocument.setReportAppServer(com.crystaldecisions.sdk.occa.report.application.ReportClientDocument.inprocConnectionString);
		reportClientDocument.open(reportName, 0);
		DatabaseController dbc = reportClientDocument.getDatabaseController();
		
		
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
	    try
	    {
	    
	    FvehicleSRV srv = (FvehicleSRV) wac.getBean("fvehicleSRV");
		/*Following code retrive data from service*/
		//Fgatepass fgatepass= srv.findByPassNo(request.getParameter("pn").toString());
		//List<Fexpirationlistingrpt> list= srv.getExpirationList(request.getParameter("df").toString(),request.getParameter("dt").toString(),"L");
		List<Fvehicle> list= srv.getVehicleList(request.getParameter("l").toString());
		/*Following code set data to report*/
		//dbc.setDataSource(lst, CommMF.class, "Fmasterlist","Fmasterlist");
			
		dbc.setDataSource(list, Fvehicle.class, "Fvehicle", "Fvehicle");
		
		//ParameterFieldController paramController;
   		//paramController = reportClientDocument.getDataDefController().getParameterFieldController();
   		//paramController.setCurrentValue("", "df", request.getParameter("df").toString());
   		//paramController.setCurrentValue("", "dt", request.getParameter("dt").toString());
		
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
		//reportSource = reportClientDocument.getReportSource();
		
	//---------- Create the viewer and render the report -------------

	//Create the CrystalReportViewer object
	//com.crystaldecisions.report.web.viewer.CrystalReportViewer crystalReportViewer = new com.crystaldecisions.report.web.viewer.CrystalReportViewer();

	//Set the reportsource property of the viewer
	//crystalReportViewer.setReportSource(reportSource);

	//Set viewer attributes
	//crystalReportViewer.setOwnPage(true);

	//Set the CrystalReportViewer print mode
	//crystalReportViewer
			//.setPrintMode(com.crystaldecisions.report.web.viewer.CrPrintMode.PDF);
	
	
	//Following code line set the parameter value to report
	// Passing parameters
	//Fields fields = new Fields();
	
	//ParameterField param1 = new ParameterField();
	//param1.setReportName("");
	//param1.setName("dn");
	//Values vals1 = new Values();
	//ParameterFieldDiscreteValue val1 = new ParameterFieldDiscreteValue();
	//String str1 = request.getParameter("dn");
	//val1.setValue(str1);
	//vals1.add(0,val1)
	//param1.setCurrentValues(vals1);
	//fields.add(param1);

	//crystalReportViewer.setParameterFields(fields);
	
   //ParameterFieldController paramController;
   //paramController = reportClientDocument.getDataDefController().getParameterFieldController();
   //paramController.setCurrentValue("", "df", request.getParameter("df").toString());
   //paramController.setCurrentValue("", "dt", request.getParameter("dt").toString());
   //paramController.setCurrentValue("", "CurrencyParam", Float.valueOf("1000.00"));
   //paramController.setCurrentValue("", "BooleanParam", Boolean.valueOf("true"));

	//Process the report
	//crystalReportViewer.processHttpRequest(request, response,
			//getServletConfig().getServletContext(), null);
	
%><%------------End of 'Open and View Report' snippet----------------%>

</body>
</html>