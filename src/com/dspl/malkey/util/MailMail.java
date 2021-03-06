package com.dspl.malkey.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.crystaldecisions.sdk.occa.report.application.DatabaseController;
import com.crystaldecisions.sdk.occa.report.application.ParameterFieldController;
import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.exportoptions.ReportExportFormat;
import com.dspl.malkey.domain.Fexpirationlistingrpt;
import com.dspl.malkey.domain.Fgatepass;
import com.dspl.malkey.domain.Fmaintenance;
import com.dspl.malkey.domain.Freservationdiaryrpt;
import com.dspl.malkey.service.FgatepassSRV;
import com.dspl.malkey.service.FmaintenanceSRV;
import com.dspl.malkey.service.FreservationSRV;
import com.dspl.malkey.service.FvehicleSRV;
import com.dspl.malkey.view.FreservationView;

import flex.messaging.io.amf.ASObject;



public class MailMail
{
	private JavaMailSender mailSender;
	private SimpleMailMessage simpleMailMessage;
	
	@Resource(name="freservationSRV")
	FreservationSRV freservationSRV;
	@Resource(name="fvehicleSRV")
	FvehicleSRV fvehicleSRV;
	@Resource(name="fmaintenanceSRV")
	FmaintenanceSRV fmaintenanceSRV;
	@Resource(name="fgatepassSRV")
	FgatepassSRV fgatepassSRV;
	
	@Value( "${report.export.filename1}")
	String EXPORT_FILE1 = "";
	@Value( "${report.export.filename2}")
	String EXPORT_FILE2 = "";
	@Value( "${report.export.filename3}")
	String EXPORT_FILE3 = "";
	@Value( "${report.export.filename4}")
	String EXPORT_FILE4 = "";
	@Value( "${report.export.filename5}")
	String EXPORT_FILE5 = "";
	@Value( "${report.export.location}")
	String EXPORT_LOC = "";
	
	@Value( "${email.title.lt}")
	String LT_EMAIL_TITLE = "";
	@Value( "${email.title.mr}")
	String MR_EMAIL_TITLE = "";
	@Value( "${email.title.le}")
	String LE_EMAIL_TITLE = "";
	@Value( "${email.title}")
	String EMAIL_TITLE = "";
	String EMAIL_TITLE_VEHICLE_MOVEMENT_ALERT = "Vehicle Movement Alert Yesterday";
	
	String LT_EMAIL_TITLE_A = "";
	String MR_EMAIL_TITLE_A = "";
	String LE_EMAIL_TITLE_A = "";
	String EMAIL_TITLE_A = "";
	String EMAIL_TITLE_VEHICLE_MOVEMENT_ALERT_A = "Vehicle Movement Alert Yesterday";
	
	
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
		this.simpleMailMessage.setCc(new String[]{"sankadil@gmail.com","rasangarocks@yahoo.com","erangankumara@gmail.com","malkeydms@gmail.com","accounts@malkey.lk"});
		this.simpleMailMessage.setBcc(new String[]{"sankadil@gmail.com","rasangarocks@yahoo.com"});
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	/***
	 * Email Send method
	 * 
	 * @param dear
	 * @param content
	 */
	public void sendMail2(String dear, String content, String title,String emailAddress[]) {
	
		MimeMessage message = mailSender.createMimeMessage();
		try{
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(emailAddress);
			helper.setBcc("sankadil@gmail.com");
			helper.setSubject(title);
			helper.setText(String.format(simpleMailMessage.getText(), dear, content));
//			FileSystemResource file = new FileSystemResource(fileURL);
//			helper.addAttachment(file.getFilename(), file);
		}catch (MessagingException e) {
			e.printStackTrace();
			throw new MailParseException(e);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		mailSender.send(message);
		
	}
	
	public void sendMail(String dear, String content) {
		
//		System.getProperties().put("mail.smtp.auth", "true");
//		System.getProperties().put("mail.smtp.starttls.enable", "true");
		
		/*		System.getProperties().put("proxySet", "true");
		System.getProperties().put("http.proxyHost", "192.168.0.235");
		System.getProperties().put("http.proxyPort", "8080");
		System.getProperties().put("http.proxyUser", "serverproxy");
		System.getProperties().put("http.proxyPassword", "serverproxy");
		
		System.out.println("http.proxyHost "+System.getProperties().get("http.proxyHost"));
		System.out.println("http.proxyUser "+System.getProperties().get("http.proxyUser"));
		System.out.println("http.proxyPort "+System.getProperties().get("http.proxyPort"));
		System.out.println("http.proxyPassword "+System.getProperties().get("http.proxyPassword"));

		
		System.getProperties().put("mail.smtp.auth", "true");
		System.getProperties().put("mail.smtp.starttls.enable", "true");
		System.getProperties().put("mail.smtp.host", "smtp.gmail.com");
		System.getProperties().put("mail.smtp.port", "587");*/
		
		
/*		sendMailVehicleDueCheckin(dear,content,new String[]{"Info@malkey.lk","erangankumara@gmail.com","milindum@gmail.com"},EMAIL_TITLE);
		sendMailLTAlert(dear,content,new String[]{"Info@malkey.lk","accounts@malkey.lk","erangankumara@gmail.com","milindum@gmail.com"},LT_EMAIL_TITLE);
		sendLicenseExpirationListing(dear,content,new String[]{"Info@malkey.lk","erangankumara@gmail.com","milindum@gmail.com"},LE_EMAIL_TITLE);
		sendMaintenanceReminder(dear,content,new String[]{"Info@malkey.lk","erangankumara@gmail.com","milindum@gmail.com"},MR_EMAIL_TITLE);
		sendVehicleMovementReminder(dear,content,new String[]{"Info@malkey.lk","erangankumara@gmail.com","milindum@gmail.com"},"Vehicle Movement Alert Yesterday");
*/		
//		test
 		sendMailVehicleDueCheckin(dear,content,new String[]{"sankadil@gmail.com"},EMAIL_TITLE_A);
		sendMailLTAlert(dear,content,new String[]{"sankadil@gmail.com"},LT_EMAIL_TITLE_A);
		sendLicenseExpirationListing(dear,content,new String[]{"sankadil@gmail.com"},LE_EMAIL_TITLE_A);
		sendMaintenanceReminder(dear,content,new String[]{"sankadil@gmail.com"},MR_EMAIL_TITLE_A);
		sendVehicleMovementReminder(dear,content,new String[]{"sankadil@gmail.com"},EMAIL_TITLE_VEHICLE_MOVEMENT_ALERT_A); 
	}
	
	/***
	 * Email Send method
	 * 
	 * @param dear
	 * @param content
	 */
	public void sendMailCommon(String dear, String content,String emailAddress[],FileSystemResource file,String title) {
	
		MimeMessage message = mailSender.createMimeMessage();
		try{
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(emailAddress);
			helper.setBcc("sankadil@gmail.com");
			helper.setSubject(title/*simpleMailMessage.getSubject()*/);
			helper.setText(String.format(simpleMailMessage.getText(), dear, content));
			//FileSystemResource file = new FileSystemResource(generateReport());
			helper.addAttachment(file.getFilename(), file);
		}catch (MessagingException e) {
			e.printStackTrace();
			throw new MailParseException(e);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		mailSender.send(message);
		
	}
	
	/***
	 * Email Send method
	 * due check in
	 * @param dear
	 * @param content
	 */
	public void sendMailVehicleDueCheckin(String dear, String content,String emailAddress[],String title) {
		String path="";
		try {
			path=generateReport();
			if(path==null || path.trim()=="")
				return;
		} catch (Exception e) {
			//e.printStackTrace();
			return;
		}
		FileSystemResource file = new FileSystemResource(path);
		sendMailCommon(dear, content, emailAddress, file,EMAIL_TITLE_A);
	}
	
	
	/***
	 * Email Send method
	 * Long term alert
	 * @param dear
	 * @param content
	 */
	public void sendMailLTAlert(String dear, String content,String emailAddress[],String title) {
		String path="";
		try {
			path=generateReport_longterm_monthly_alert();
			if(path==null || path.trim()=="")
				return;
		} catch (Exception e) {
			//e.printStackTrace();
			return;
		}
		FileSystemResource file = new FileSystemResource(path);
		
		//FileSystemResource file = new FileSystemResource(generateReport_longterm_monthly_alert());
		sendMailCommon(dear, content, emailAddress, file,LT_EMAIL_TITLE_A);
	}
	
	/***
	 * Email Send method
	 * License Expiration
	 * @param dear
	 * @param content
	 */
	public void sendLicenseExpirationListing(String dear, String content,String emailAddress[],String title) {
		String path="";
		try {
			path=generateReportLicenseExpirationListing(null, null);
			if(path==null || path.trim()=="")
				return;
		} catch (Exception e) {
			//e.printStackTrace();
			return;
		}
		FileSystemResource file = new FileSystemResource(path);
//		FileSystemResource file = new FileSystemResource(generateReportLicenseExpirationListing(null, null));
		sendMailCommon(dear, content, emailAddress, file,LE_EMAIL_TITLE_A);
	}
	/***
	 * Email Send method
	 * Maintenance
	 * @param dear
	 * @param content
	 */
	public void sendMaintenanceReminder(String dear, String content,String emailAddress[],String title) {
		String path="";
		try {
			path=generateReportMaintenanceReminder();
			if(path==null || path.trim()=="")
				return;
		} catch (Exception e) {
			//e.printStackTrace();
			return;
		}
		FileSystemResource file = new FileSystemResource(path);
//		FileSystemResource file = new FileSystemResource(generateReportMaintenanceReminder());
		sendMailCommon(dear, content, emailAddress, file,MR_EMAIL_TITLE_A);
	}
	/***
	 * Email Send method
	 * VehicleMovement
	 * @param dear
	 * @param content
	 */
	public void sendVehicleMovementReminder(String dear, String content,String emailAddress[],String title) {
		String path="";
		try {
			path=generateReportVehicleMovement();
			if(path==null || path.trim()=="")
				return;
		} catch (Exception e) {
			//e.printStackTrace();
			return;
		}
		FileSystemResource file = new FileSystemResource(path);
		
//		FileSystemResource file = new FileSystemResource(generateReportVehicleMovement());
		sendMailCommon(dear, content, emailAddress, file,EMAIL_TITLE_VEHICLE_MOVEMENT_ALERT_A);
	}

    
	/***
	 * 
	 * Generate report and return location of saved pdf file
	 * @return file location
	 */
    public String generateReport_longterm_monthly_alert()  throws Exception
    {
		
    	try {
    		String reportName = "reports/CHDAlert.rpt";
    		ReportClientDocument reportClientDocument = new ReportClientDocument();
    		reportClientDocument.setReportAppServer(ReportClientDocument.inprocConnectionString);
    		reportClientDocument.open(reportName, 0);
    		DatabaseController dbc = reportClientDocument.getDatabaseController();

    		List<FreservationView> fresdiaryrecs= freservationSRV.loadCHDAlert();
    		//if(fresdiaryrecs.size()==0)return null;//throw new Exception("no result");
    		dbc.setDataSource(fresdiaryrecs, FreservationView.class, "FreservationView", "FreservationView");
    		
            ByteArrayInputStream byteArrayInputStream = (ByteArrayInputStream) reportClientDocument.getPrintOutputController().export(ReportExportFormat.PDF);
            reportClientDocument.close();
            
            LT_EMAIL_TITLE_A=LT_EMAIL_TITLE+" [ "+new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())+" ]";
        	//Write file to disk...
        	String EXPORT_OUTPUT = EXPORT_LOC +new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())+ EXPORT_FILE1;
        	System.out.println("Exporting to " + EXPORT_OUTPUT);
        	writeToFileSystem(byteArrayInputStream, EXPORT_OUTPUT);
            return EXPORT_OUTPUT;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
        
    }
    
    
    /***
     * 
     * Generate report and return location of saved pdf file
     * @return file location
     */
    public String generateReportLicenseExpirationListing(String df,String dt)  throws Exception
    {
    	
    	try {
    		Calendar c=Calendar.getInstance();
    		c.add(Calendar.DATE, 7);
    		df=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    		dt=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    		String reportName = "reports/LicenseExpirationListing.rpt";
    		ReportClientDocument reportClientDocument = new ReportClientDocument();
    		reportClientDocument.setReportAppServer(ReportClientDocument.inprocConnectionString);
    		reportClientDocument.open(reportName, 0);
    		DatabaseController dbc = reportClientDocument.getDatabaseController();
    		System.out.println("df "+df);
    		System.out.println("dt "+dt);
    		LE_EMAIL_TITLE_A=LE_EMAIL_TITLE+" [ "+df+" to "+dt+" ]";
    		List<Fexpirationlistingrpt> list= fvehicleSRV.getExpirationList(df,dt,"L");
    		//if(list.size()==0)return null;//throw new Exception("no result");
    		dbc.setDataSource(list, Fexpirationlistingrpt.class, "Fexpirationlistingrpt", "Fexpirationlistingrpt");
    		
    		com.crystaldecisions.sdk.occa.report.application.ParameterFieldController paramController;
       		paramController = reportClientDocument.getDataDefController().getParameterFieldController();
       		paramController.setCurrentValue("", "df", df);
       		paramController.setCurrentValue("", "dt", dt);
    		
    		ByteArrayInputStream byteArrayInputStream = (ByteArrayInputStream) reportClientDocument.getPrintOutputController().export(ReportExportFormat.PDF);
    		reportClientDocument.close();
    		
    		
    		//Write file to disk...
    		String EXPORT_OUTPUT = EXPORT_LOC +new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())+ EXPORT_FILE3;
    		System.out.println("Exporting to " + EXPORT_OUTPUT);
    		writeToFileSystem(byteArrayInputStream, EXPORT_OUTPUT);
    		return EXPORT_OUTPUT;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    }
	
    /***
     * 
     * Generate report and return location of saved pdf file
     * @return file location
     */
    public String generateReportMaintenanceReminder()  throws Exception
    {
    	//?df=2014.10.11&dt=2014.10.11&mf=All&ms=All
    	try {

    		String dateFrom="2014.10.11";
    		String dateTo="2014.10.11";
    		String maintFrequency="All";
    		String maintType="";
    		String maintStatus="ONHOLD' or m.statusid = 'PENDING";
    		
    		String reportName = "reports/MaintenanceReminder.rpt";
    		ReportClientDocument reportClientDocument = new ReportClientDocument();
    		reportClientDocument.setReportAppServer(ReportClientDocument.inprocConnectionString);
    		reportClientDocument.open(reportName, 0);
    		DatabaseController dbc = reportClientDocument.getDatabaseController();
    		
    		Calendar c=Calendar.getInstance();
    		c.add(Calendar.DATE, -30);
    		dateTo=new SimpleDateFormat("yyyy.MM.dd").format(Calendar.getInstance().getTime());
    		dateFrom=new SimpleDateFormat("yyyy.MM.dd").format(c.getTime());
    		MR_EMAIL_TITLE_A=MR_EMAIL_TITLE+" [ "+dateFrom+" - "+dateTo+" ]";
    		ArrayList paraList=new ArrayList();
    		ASObject aso;
    		
    		if(maintFrequency!=null && maintFrequency.trim().length()>0 && maintFrequency.toLowerCase().equals("all")==false){
    			aso=new ASObject();
    			aso.put("frequency",maintFrequency);
    			paraList.add(aso);
    		}
    		
    		if(maintType!=null && maintType.trim().length()>0 && maintType.toLowerCase().equals("all")==false){
    			aso=new ASObject();
    			aso.put("typeid",maintType);
    			paraList.add(aso);
    		}
    		
    		if(maintStatus!=null && maintStatus.trim().length()>0 && maintStatus.toLowerCase().equals("all")==false){
    			aso=new ASObject();
    			aso.put("statusid",maintStatus);
    			paraList.add(aso);
    		}
    		
    		List<Fmaintenance> list= fmaintenanceSRV.getMaintenanceReminder(dateFrom,dateTo,paraList);
    		//if(list.size()==0)return null;//throw new Exception("no result");
    		dbc.setDataSource(list, Fmaintenance.class, "Fmaintenance", "Fmaintenance");
    		
    		ParameterFieldController paramController;
       		paramController = reportClientDocument.getDataDefController().getParameterFieldController();
       		paramController.setCurrentValue("", "df", dateFrom);
       		paramController.setCurrentValue("", "dt", dateTo);
    		
    		ByteArrayInputStream byteArrayInputStream = (ByteArrayInputStream) reportClientDocument.getPrintOutputController().export(ReportExportFormat.PDF);
    		reportClientDocument.close();
    		
    		
    		//Write file to disk...
    		String EXPORT_OUTPUT = EXPORT_LOC +new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())+ EXPORT_FILE4;
    		System.out.println("Exporting to " + EXPORT_OUTPUT);
    		writeToFileSystem(byteArrayInputStream, EXPORT_OUTPUT);
    		return EXPORT_OUTPUT;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    }
    
    
    /***
     * new report created on 2015.10.03
     * Generate report and return location of saved pdf file
     * @return file location
     */
    public String generateReportVehicleMovement() throws Exception
    {
    	//?df=2014.10.11&dt=2014.10.11&mf=All&ms=All
    	try {
    		
    		String dateFrom="2014.10.11";
    		String dateTo="2014.10.11";
    		String maintFrequency="All";
    		String maintType="";
    		String maintStatus=null;
    		
    		String reportName = "reports/VehicleMovement.rpt";
    		ReportClientDocument reportClientDocument = new ReportClientDocument();
    		reportClientDocument.setReportAppServer(ReportClientDocument.inprocConnectionString);
    		reportClientDocument.open(reportName, 0);
    		DatabaseController dbc = reportClientDocument.getDatabaseController();
       		
    		Calendar c=Calendar.getInstance();
    		c.add(Calendar.DATE, -1);
    		dateTo=new SimpleDateFormat("yyyy.MM.dd").format(c.getTime());
    		dateFrom=new SimpleDateFormat("yyyy.MM.dd").format(c.getTime());
    		EMAIL_TITLE_VEHICLE_MOVEMENT_ALERT_A=EMAIL_TITLE_VEHICLE_MOVEMENT_ALERT+" [ "+dateFrom+" - "+dateTo+" ] ";

    		List<Fgatepass> fgatepasses= fgatepassSRV.getGatePassList(dateFrom,dateTo,maintStatus);
    		//if(fgatepasses.size()==0)return null;//throw new Exception("no result");
    		dbc.setDataSource(fgatepasses, Fgatepass.class, "Fgatepass", "Fgatepass");
    		
    		ParameterFieldController paramController;
    		paramController = reportClientDocument.getDataDefController().getParameterFieldController();
    		paramController.setCurrentValue("", "df", dateFrom);
    		paramController.setCurrentValue("", "dt", dateTo);
    		
    		ByteArrayInputStream byteArrayInputStream = (ByteArrayInputStream) reportClientDocument.getPrintOutputController().export(ReportExportFormat.PDF);
    		reportClientDocument.close();
    		
    		//Write file to disk...
    		String EXPORT_OUTPUT = EXPORT_LOC +new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())+ EXPORT_FILE5;
    		System.out.println("Exporting to " + EXPORT_OUTPUT);
    		writeToFileSystem(byteArrayInputStream, EXPORT_OUTPUT);
    		return EXPORT_OUTPUT;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    }
    
    
    /***
     * 
     * Generate report and return location of saved pdf file
     * @return file location
     */
    public String generateReport()  throws Exception
    {
    	
    	String ht = "";//''
    	String hireStatus="'CHECKOUT'";
    	String fromDate="2012.01.31";
    	String toDate="2013.04.27";
    	
    	
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, -30);
    	Calendar calY = Calendar.getInstance();
    	calY.add(Calendar.DATE, -1);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd"); 
    	fromDate=sdf.format(cal.getTime());
    	toDate=sdf.format(calY.getTime());
    	System.out.println(sdf.format(cal.getTime()));
    	System.out.println("fromDate: "+fromDate);
    	System.out.println("toDate: "+toDate);
    	EMAIL_TITLE_A=EMAIL_TITLE+" [ "+fromDate+"-"+toDate+" ]";
    	try {
    		String reportName = "reports/RegistryOfVehicleDueIn.rpt";
    		ReportClientDocument reportClientDocument = new ReportClientDocument();
    		reportClientDocument.setReportAppServer(ReportClientDocument.inprocConnectionString);
    		reportClientDocument.open(reportName, 0);
    		DatabaseController dbc = reportClientDocument.getDatabaseController();
    		
    		List<Freservationdiaryrpt> fresdiaryrecs = freservationSRV.getResDiaryData(fromDate, toDate, ht,hireStatus);
    		//if(fresdiaryrecs.size()==0)return null;//throw new Exception("no result");
    		dbc.setDataSource(fresdiaryrecs, Freservationdiaryrpt.class,"Freservationdiaryrpt", "Freservationdiaryrpt");
    		
    		ByteArrayInputStream byteArrayInputStream = (ByteArrayInputStream) reportClientDocument.getPrintOutputController().export(ReportExportFormat.PDF);
    		reportClientDocument.close();
    		
    		//Write file to disk...
    		String EXPORT_OUTPUT = EXPORT_LOC +new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())+ EXPORT_FILE2;
    		System.out.println("Exporting to " + EXPORT_OUTPUT);
    		writeToFileSystem(byteArrayInputStream, EXPORT_OUTPUT);
    		return EXPORT_OUTPUT;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    }
    
	
    /***
     * Export report and save file 
     * 
     * @param byteArrayInputStream
     * @param exportFile
     * @throws Exception
     */
	private void writeToFileSystem(ByteArrayInputStream byteArrayInputStream, String exportFile) throws Exception {
		
		//Use the Java I/O libraries to write the exported content to the file system.
		byte byteArray[] = new byte[byteArrayInputStream.available()];

		//Create a new file that will contain the exported result.
		File file = new File(exportFile);
		FileOutputStream fileOutputStream = new FileOutputStream(file);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(byteArrayInputStream.available());
		int x = byteArrayInputStream.read(byteArray, 0, byteArrayInputStream.available());

		byteArrayOutputStream.write(byteArray, 0, x);
		byteArrayOutputStream.writeTo(fileOutputStream);

		//Close streams.
		byteArrayInputStream.close();
		byteArrayOutputStream.close();
		fileOutputStream.close();
		
	}
	
	
	
	
	
	/***
	 * Email Send method
	 * 
	 * @param dear
	 * @param content
	 */
	public void sendPlainMail(String dear, String content) {
	
		MimeMessage message = mailSender.createMimeMessage();
		try{
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(new String[]{"sankadil@gmail.com"}/*simpleMailMessage.getCc()*/);
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(String.format(simpleMailMessage.getText(), dear, content));
			//FileSystemResource file = new FileSystemResource(generateReport());
			//helper.addAttachment(file.getFilename(), file);
		}catch (MessagingException e) {
			e.printStackTrace();
			throw new MailParseException(e);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		mailSender.send(message);
		
	}
	
	
	
}
