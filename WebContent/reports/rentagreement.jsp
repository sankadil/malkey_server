<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="com.crystaldecisions.sdk.occa.report.application.DatabaseController,
	com.crystaldecisions.report.web.viewer.CrystalReportViewer,
	com.crystaldecisions.report.web.viewer.CrPrintMode,
	com.crystaldecisions.sdk.occa.report.application.OpenReportOptions,
	com.crystaldecisions.sdk.occa.report.application.ReportClientDocument,
	com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext,
	org.springframework.beans.factory.BeanFactory,
	org.springframework.beans.factory.xml.XmlBeanFactory,
	org.springframework.core.io.FileSystemResource,
	org.springframework.web.context.support.WebApplicationContextUtils,
	org.springframework.web.context.WebApplicationContext"%>
<%@page import="com.dspl.malkey.service.FreservationSRV"%>
<%@page import="com.crystaldecisions.reports.queryengine.collections.Fields"%>
<%@page import="com.crystaldecisions.sdk.occa.report.data.ParameterField"%>
<%@page import="com.businessobjects12.prompting.objectmodel.common.Values"%>
<%@page import="com.crystaldecisions.sdk.occa.report.data.ParameterFieldDiscreteValue"%>
<%@page import="com.dspl.malkey.util.CRJavaHelper"%>
<%@page import="com.dspl.malkey.domain.Freservationdiaryrpt"%>
<%@page import="com.crystaldecisions.sdk.occa.report.application.ParameterFieldController"%>
<%@page import="java.util.List"%>
<%@page import="com.dspl.malkey.service.FreservationSRV,
		com.dspl.malkey.report.FrentagreementRPT,
		com.dspl.malkey.report.FrentagrvehinvRPT"%>
<%@page import="com.dspl.malkey.util.PublishCR"%>
<%@page import="com.crystaldecisions.sdk.occa.report.data.Alert"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.File"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rent Agreement</title>
</head>

<body>
	<%
		String reportName;
		String sHireType = request.getParameter("hiretypeid").trim();
				
		if (sHireType.matches("SD"))
			reportName = "reports/rentagreement.rpt";		// Self Drive
		else
			reportName = "reports/rentagreementwd.rpt";		// With Driver or Wedding
		
		Object reportSource = null; 
		//---------- Create a ReportClientDocument -------------//
		ReportClientDocument reportClientDocument = new ReportClientDocument();
		reportClientDocument.setReportAppServer(ReportClientDocument.inprocConnectionString);
		reportClientDocument.open(reportName, 0);
		DatabaseController dbc = reportClientDocument.getDatabaseController();
		reportSource = reportClientDocument.getReportSource();
		
		/* Retrieve required data from server by use of the service(s) */
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		FreservationSRV srv = (FreservationSRV) wac.getBean("freservationSRV");
		String resno=request.getParameter("resno");//RS/1310/00527
		List<FrentagreementRPT> lstRentAgreement = srv.listRptRentAgreement(resno,request.getRequestURL().toString());
		resno=resno.replaceAll("/", "");
			
		/* Vehicle Inventroy items list */
		List<FrentagrvehinvRPT> lstRentVehiInv = srv.listRptRentVehiclesInventry(request.getParameter("resno"));
		String otherServiceList = srv.getOtherServiceList(request.getParameter("resno"));
		/* Assign the data to relevant report data source */
		dbc.setDataSource(lstRentAgreement, FrentagreementRPT.class, "FrentagreementRPT","FrentagreementRPT");
		dbc.setDataSource(lstRentVehiInv, FrentagrvehinvRPT.class, "FrentagrvehinvRPT","FrentagrvehinvRPT");
		
		if(sHireType.matches("SD")){
		ParameterFieldController paramController;
   		paramController = reportClientDocument.getDataDefController().getParameterFieldController();
   		try{
   			String urlPrefix=request.getRequestURL().toString()+request.getContextPath()+"/image/fuelLevel/";
   			String urlSufix=".gif";
	   		paramController.setCurrentValue("", "cofuel",urlPrefix+lstRentAgreement.get(0).getCofuellevel()+urlSufix);
	   		paramController.setCurrentValue("", "cifuel",urlPrefix+lstRentAgreement.get(0).getCifuellevel()+urlSufix);
	   		paramController.setCurrentValue("", "otherSerivice",otherServiceList);
	   		//System.out.println("F url :"+urlPrefix+lstRentAgreement.get(0).getCofuellevel()+urlSufix);
   		}
   		catch(Exception e)
   		{
   			e.printStackTrace();
   		}
		}
			
		
    	try {
    		java.io.ByteArrayInputStream byteArrayInputStream;
			int bytesRead = 256;
			String reportTitle;
			reportTitle = reportClientDocument.getReportSource().getReportTitle();
			if (reportTitle==null)
				reportTitle="";
			else
				reportTitle = reportTitle.replaceAll("\"", "");			// Remove invalid chars
			
			response.setHeader("Content-disposition", "inline;filename=" + resno + ".pdf\"");		// Display the PDF in the browser
			response.setContentType("application/pdf");	
	
			
			//=================================================================
	    	  List<java.io.InputStream> sourcePDFs = new ArrayList<java.io.InputStream>();
	    	  // initialize the Merger utility and add pdfs to be merged
	    	  String fileName="C:/temp/"+resno+".pdf";
	    	  org.apache.pdfbox.util.PDFMergerUtility mergerUtility = new org.apache.pdfbox.util.PDFMergerUtility();
	    	  mergerUtility.setDestinationFileName(fileName);
	    	  sourcePDFs.add(reportClientDocument.getPrintOutputController().export(com.crystaldecisions.sdk.occa.report.exportoptions.ReportExportFormat.PDF));
	    	  if (sHireType.matches("SD")){
	    		  sourcePDFs.add(new java.io.FileInputStream(new File("C:/SelfDriverAgreementDraft.pdf")));}
	    	  else{
	    	  sourcePDFs.add(new java.io.FileInputStream(new File("C:/WithDriverAgreementDraft.pdf")));}
	    	  mergerUtility.addSources(sourcePDFs);
	    	  mergerUtility.mergeDocuments();
	    	  InputStream in = new BufferedInputStream(new FileInputStream(fileName)); 
	    	  
	    	//=================================================================
	    		
			// Write the PDF content
			while ((bytesRead) >= 0) {
				bytesRead = in.read();
				out.write(bytesRead);
			}
	    	try{in.close();} catch(Exception e){}
	    	try{out.flush();}catch(Exception e){}
			

    	} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage() + " : " + ex.getLocalizedMessage());
		}
    	finally{
    	try{
    		reportClientDocument.close();
    		} 
    	catch(Exception e)
    	{
    		System.out.println("ERROR: " + e.getMessage() + " : " + e.getLocalizedMessage());
    	}
    	}
		
		
	%>
</body>
</html>