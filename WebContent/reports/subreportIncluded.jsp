

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
<%@page import="com.dspl.malkey.service.FothersrvSRV"%>
<%@page import="com.crystaldecisions.sdk.occa.report.definition.SubreportLinks"%>
<%@page import="com.crystaldecisions.sdk.occa.report.application.SubreportController"%>

<%@page
	import="com.crystaldecisions.sdk.occa.report.application.DatabaseController"%>
<%@page import="javax.annotation.Resource"%>

<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.beans.factory.BeanFactory"%>
<%@page import="org.springframework.beans.factory.xml.XmlBeanFactory"%>
<%@page import="org.springframework.core.io.FileSystemResource"%>
<%@page
	import="flex.messaging.io.amf.client.exceptions.ClientStatusException"%>
<%@page
	import="flex.messaging.io.amf.client.exceptions.ServerStatusException"%>
<%@page import="flex.messaging.io.amf.client.AMFConnection"%>
<%@page import="java.util.List"%>

<%@ page import="com.crystaldecisions.report.web.viewer.*,
com.crystaldecisions.reports.reportengineinterface.*,
com.crystaldecisions.sdk.occa.report.data.*,
com.crystaldecisions.sdk.occa.report.reportsource.* " %>




<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%------------Beginning of 'Open and View Report' snippet----------------%>
<%
	//Use the relative path to the report; the physical or full qualified URL cannot be used.
	
		
	String reportName = "reports/subreportIncluded.rpt";
	Object reportSource =null; //session.getAttribute("Report7");
		//---------- Create a ReportClientDocument -------------
		com.crystaldecisions.sdk.occa.report.application.ReportClientDocument reportClientDocument = new com.crystaldecisions.sdk.occa.report.application.ReportClientDocument();
		reportClientDocument.setReportAppServer(com.crystaldecisions.sdk.occa.report.application.ReportClientDocument.inprocConnectionString);
		reportClientDocument.open(reportName, 0);
		DatabaseController dbc = reportClientDocument.getDatabaseController();
		DatabaseController sdbc = reportClientDocument.getSubreportController().getSubreport("Subreport2").getDatabaseController();
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
		sdbc.setDataSource(lst, Faccessory.class, "Faccessory","Faccessory");
		
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

	
	
	
	
	
	
	
	
	
	
	
	//Create a Fields object collection to store the parameter fields in.
	Fields parameterFields = new Fields();

	//Create a ParameterField object for each field that you wish to set.
	ParameterField pfield1 = new ParameterField();
	ParameterField pfield2 = new ParameterField();

	//You must set the report name. Set the report name to an empty string if your report does not contain a subreport; otherwise, the report name will be the name of the subreport
	pfield1.setReportName("");
	pfield2.setReportName("Subreport2");

	//Create a Values object and a ParameterFieldDiscreteValue object for each parameter field you wish to set.
	//If a ranged value is being set, a ParameterFieldRangeValue object should be used instead of the discrete value object.
	Values vals1 = new Values();
	Values vals2 = new Values();
	ParameterFieldDiscreteValue pfieldDV1 = new ParameterFieldDiscreteValue();
	ParameterFieldDiscreteValue pfieldDV2 = new ParameterFieldDiscreteValue();

	//----------- Initialize the parameter fields ----------

	//Set the name and value for each parameter field that is added.
	//Values for parameter fields are represented by a ParameterFieldDiscreteValue or ParameterFieldRangeValue object.
	String Country = new String("ac1");
	pfield1.setName("MyParameter1");
	pfieldDV1.setValue(Country);

	String p1 = new String("ac2");
	pfield2.setName("MyParameter");
	pfieldDV2.setValue(p1);

	//Add the parameter field values to the Values collection object.
	vals1.add(pfieldDV1);
	vals2.add(pfieldDV2);

	//Set the current Values collection for each parameter field.
	pfield1.setCurrentValues(vals1);
	pfield2.setCurrentValues(vals2);

	//Add each parameter field to the Fields collection.
	//The Fields object is now ready to be used with the viewer.
	parameterFields.add(pfield1);
	parameterFields.add(pfield2);

	 //set the parameters into the viewer
	 crystalReportViewer.setParameterFields( parameterFields ); 
	 
	 
	 
	 
	 
	 
	 
	//Process the report
	crystalReportViewer.processHttpRequest(request, response,
			getServletConfig().getServletContext(), null);
%><%------------End of 'Open and View Report' snippet----------------%>

</body>

<%@page import="java.sql.ResultSet"%>
<%@page import="com.crystaldecisions.sdk.occa.report.lib.IResultFieldController"%>
<%@page import="com.crystaldecisions.sdk.occa.report.application.ResultFieldController"%></html>