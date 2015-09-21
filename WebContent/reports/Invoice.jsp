
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

<%@page import="com.dspl.malkey.service.FarfdtfSRV"%>
<%@page import="com.crystaldecisions.reports.queryengine.collections.Fields"%>
<%@page import="com.crystaldecisions.sdk.occa.report.data.ParameterField"%>
<%@page import="com.businessobjects12.prompting.objectmodel.common.Values"%>
<%@page import="com.crystaldecisions.sdk.occa.report.data.ParameterFieldDiscreteValue"%>

<%@page import="com.dspl.malkey.util.CRJavaHelper"%>
<%@page import="com.crystaldecisions.sdk.occa.report.application.ParameterFieldController"%>
<%@page import="com.dspl.malkey.domain.Finvhed"%>
<%@page import="com.dspl.malkey.report.FinvdetRPT"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.dspl.malkey.util.PublishCR"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Invoice</title>
</head>
<body>
<%------------Beginning of 'Open and View Report' snippet----------------%>
<%
	//Use the relative path to the report; the physical or full qualified URL cannot be used.
	
					//System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXS");
	String reportName = "reports/Invoice.rpt";
	Object reportSource =null; //session.getAttribute("Report7");
		//---------- Create a ReportClientDocument -------------
			//System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXD");
		DatabaseController dbc;
		com.crystaldecisions.sdk.occa.report.application.ReportClientDocument reportClientDocument = new com.crystaldecisions.sdk.occa.report.application.ReportClientDocument();
		reportClientDocument.setReportAppServer(com.crystaldecisions.sdk.occa.report.application.ReportClientDocument.inprocConnectionString);

		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
	    try
	    {
			//System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXF");
	    	//InvoiceTax.rpt
	    	
	    FarfdtfSRV srv = (FarfdtfSRV) wac.getBean("farfdtfSRV");
		/*Following code retrive data from service*/
		//Fgatepass fgatepass= srv.findByPassNo(request.getParameter("pn").toString());
		Finvhed finvhed=srv.getInvHedByInvNo(request.getParameter("in").toString());
		List<Finvhed> hedList=new ArrayList<Finvhed>();
		hedList.add(finvhed);
		//System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXG");
		if(finvhed!=null && finvhed.getVattyp()!=null){
			if(finvhed.getVattyp().equalsIgnoreCase("T"))
			{
				reportName="reports/InvoiceTax.rpt";
			}
			else if(finvhed.getVattyp().equalsIgnoreCase("S"))
			{
				reportName="reports/InvoiceSVAT.rpt";
			}
		}
		//System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXH");
		reportClientDocument.open(reportName, 0);
		dbc = reportClientDocument.getDatabaseController();
		
		
		List<FinvdetRPT> detList=srv.getInvDetByInvNoSVAT(request.getParameter("in").toString());
		/*Following code set data to report*/
		//dbc.setDataSource(lst, CommMF.class, "Fmasterlist","Fmasterlist");
				//System.out.println("ERRRR XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXK");
		if(finvhed==null || detList==null || detList.size()<=0){
			PrintWriter os = response.getWriter();
			os.write("<script type=\"text/javascript\"> alert('Sorry, Invoice Not Found'); window.close();</script>");
			os.flush();
			os.close();
		}
		else
		{
			hedList.get(0).setTaxOrderText(detList.get(0).getTaxOrderText());
			//detList.get(0).getTaxOrderText();
		}
		//System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXK");
		dbc.setDataSource(hedList, Finvhed.class, "Finvhed_1", "Finvhed_1");
		dbc.setDataSource(detList, FinvdetRPT.class, "FinvdetRPT", "FinvdetRPT");
		//System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXC");	
		PublishCR cr=new PublishCR();
		cr.publishToPDF(reportClientDocument, session, response, out);
		//System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXV");
	    }
	    catch(Exception e)
	    {
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXL");
	    	System.out.println("JSP Error: " + e.getMessage());
	    	e.printStackTrace();
			PrintWriter os = response.getWriter();
			os.write("<script type=\"text/javascript\"> alert('Sorry, Error Occured While Displaying The Invoice.\r Error Description: "+ e.getMessage() +"'); window.close();</script>");
			os.flush();
			os.close();
	    }
	    finally
	    {
			//System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXB");
	    	reportClientDocument.close();
	    }

	
%><%------------End of 'Open and View Report' snippet----------------%>

</body>
</html>