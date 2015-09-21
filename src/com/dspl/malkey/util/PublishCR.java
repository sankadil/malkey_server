package com.dspl.malkey.util;

import java.io.ByteArrayInputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.exportoptions.ReportExportFormat;


public class PublishCR {
	
	 /**
     * Publishes a given Crystal Report to PDF and assigns it to the return value enabling the end user to either download or view PDF
     * 
     * @param clientRptDoc		The reportClientDocument representing the report being used
     * @param httpSession		The HttpSession object
     * @param response   The HttpServletResponse object
     * 
     * 
     */
    public void publishToPDF(ReportClientDocument clientRptDoc, HttpSession httpSession, HttpServletResponse response, java.io.Writer out){

    	//---------- Publish the report to PDF -------------//
    	try {
			ByteArrayInputStream byteArrayInputStream;
			int bytesRead = 256;
			String reportTitle;
			
			httpSession.setAttribute("ReportSource", clientRptDoc.getReportSource());
			
			// Convert the PDF into a stream and write it to the response in order to make the file viewable at the clients end
			byteArrayInputStream = (ByteArrayInputStream) clientRptDoc.getPrintOutputController().export(ReportExportFormat.PDF);
			
			// Derive the report title
			reportTitle = clientRptDoc.getReportSource().getReportTitle();
			if (reportTitle==null)
				reportTitle="";
			else
				reportTitle = reportTitle.replaceAll("\"", "");			// Remove invalid chars
			
			/**response.setHeader("Content-disposition", "attachment;filename=Rent_Agreement.pdf\""); // Prompt the user to download the PDF file */
//			response.setHeader("Content-disposition", "inline;filename=report");		// Display the PDF in the browser
			response.setHeader("Content-disposition", "inline;filename=" + reportTitle + ".pdf");		// Display the PDF in the browser
			response.setContentType("application/pdf");	
	
			// Write the PDF content
			while ((bytesRead) >= 0) {
				bytesRead = byteArrayInputStream.read();
				out.write(bytesRead);
			}
			byteArrayInputStream.close();
			out.flush();

    	} catch (Exception ex) {
    		ex.printStackTrace();
			System.out.println("ERROR: " + ex.getMessage() + " : " + ex.getLocalizedMessage());
		}
    }
    
	 /**
     * Publishes a given Crystal Report to PDF and assigns it to the return value enabling the end user to either download or view PDF
     * 
     * @param clientRptDoc		The reportClientDocument representing the report being used
     * @param httpSession		The HttpSession object
     * @param response   The HttpServletResponse object
     * 
     * 
     */
    public void publishToPDF1(ReportClientDocument clientRptDoc, HttpSession httpSession, HttpServletResponse response, java.io.Writer out){

    	//---------- Publish the report to PDF -------------//
    	try {
			ByteArrayInputStream byteArrayInputStream=null;
			int bytesRead = 256;
			String reportTitle;
			System.out.println("before close stream.1");
			//httpSession.setAttribute("ReportSource", clientRptDoc.getReportSource());
			System.out.println("before close stream.");
			// Convert the PDF into a stream and write it to the response in order to make the file viewable at the clients end
			try{
				System.out.println("aaaaaaaaaaa");
			byteArrayInputStream = (ByteArrayInputStream) clientRptDoc.getPrintOutputController().export(ReportExportFormat.PDF);
			System.out.println("ddddddddd");
			clientRptDoc.close();
			System.out.println("fffffffffff");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			System.out.println("before close stream.2");
			// Derive the report title
			reportTitle = clientRptDoc.getReportSource().getReportTitle();
			if (reportTitle==null)
				reportTitle="";
			else
				reportTitle = reportTitle.replaceAll("\"", "");			// Remove invalid chars
			System.out.println("before close stream.3");
			/**response.setHeader("Content-disposition", "attachment;filename=Rent_Agreement.pdf\""); // Prompt the user to download the PDF file */
//			response.setHeader("Content-disposition", "inline;filename=report");		// Display the PDF in the browser
			response.setHeader("Content-disposition", "inline;filename=" + reportTitle + ".pdf");		// Display the PDF in the browser
			response.setContentType("application/pdf");	
			System.out.println("before close stream.4");
			// Write the PDF content
			while ((bytesRead) >= 0) {
				bytesRead = byteArrayInputStream.read();
				out.write(bytesRead);
			}
			System.out.println("before close stream.5");
			try{
				
				if(byteArrayInputStream!=null)
					byteArrayInputStream.close();
				
			System.out.println("before close stream.6");
			
			if(out!=null)
				out.flush();
			}
			catch(Exception exc)
			{
				exc.printStackTrace();
				System.out.println("PublishCR.publishToPDF() CLOSE ERROR: " + exc.getMessage() + " : " + exc.getLocalizedMessage());
			}

    	} catch (Exception ex) {
    		ex.printStackTrace();
			System.out.println("PublishCR.publishToPDF() ERROR: " + ex.getMessage() + " : " + ex.getLocalizedMessage());
		}
    }
    public void publishToDOC(ReportClientDocument clientRptDoc, HttpSession httpSession, HttpServletResponse response, java.io.Writer out){
    	
    	//---------- Publish the report to PDF -------------//
    	try {
    		ByteArrayInputStream byteArrayInputStream;
    		int bytesRead = 256;
    		String reportTitle;
    		
    		httpSession.setAttribute("ReportSource", clientRptDoc.getReportSource());
    		
    		// Convert the PDF into a stream and write it to the response in order to make the file viewable at the clients end
    		byteArrayInputStream = (ByteArrayInputStream) clientRptDoc.getPrintOutputController().export(ReportExportFormat.RTF);
    		
    		// Derive the report title
    		reportTitle = clientRptDoc.getReportSource().getReportTitle();
    		if (reportTitle==null)
    			reportTitle="";
    		else
    			reportTitle = reportTitle.replaceAll("\"", "");			// Remove invalid chars
    		
    		/**response.setHeader("Content-disposition", "attachment;filename=Rent_Agreement.pdf\""); // Prompt the user to download the PDF file */
//			response.setHeader("Content-disposition", "inline;filename=report");		// Display the PDF in the browser
    		response.setHeader("Content-disposition", "inline;filename=" + reportTitle + ".doc");		// Display the PDF in the browser
    		response.setContentType("application/msword");	
    		
    		// Write the PDF content
    		while ((bytesRead) >= 0) {
    			bytesRead = byteArrayInputStream.read();
    			out.write(bytesRead);
    		}
    		byteArrayInputStream.close();
    		out.flush();
    		
    	} catch (Exception ex) {
    		System.out.println("ERROR: " + ex.getMessage() + " : " + ex.getLocalizedMessage());
    	}
    }
    
}
