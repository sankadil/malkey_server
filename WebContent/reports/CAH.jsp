<!--Author :sanka-->
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
<%@page import="com.dspl.malkey.util.PublishCR"%>
<%@page import="com.dspl.malkey.view.FreservationView"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Agreement History</title>
</head>
<body>
<%------------Beginning of 'Open and View Report' snippet----------------%>
<%
	String reportName = "reports/CAH.rpt";
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
		List<FreservationView> fresdiaryrecs= srv.loadCAH2(request.getParameter("debcode"));
		dbc.setDataSource(fresdiaryrecs, FreservationView.class, "FreservationView", "FreservationView");
   		PublishCR cr=new PublishCR();
		cr.publishToPDF(reportClientDocument, session, response, out);
	    }
	    catch(Exception e)
	    {
	    //	System.out.println("JSP Error: " + e.getMessage());
	    //	e.printStackTrace();
	    }
	    finally
	    {
	    	reportClientDocument.close();
	    }
%><%------------End of 'Open and View Report' snippet----------------%>

</body>
</html>