package com.dspl.malkey.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.PersistenceException;

/*import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;*/
import org.springframework.beans.factory.annotation.Value;

import com.dspl.malkey.dao.FreservationDAO;
import com.dspl.malkey.dao.MessageDAO;
import com.dspl.malkey.domain.Fresaccrate;
import com.dspl.malkey.domain.Fresaccs;
import com.dspl.malkey.domain.Fresaddcharge;
import com.dspl.malkey.domain.Fresclientdriver;
import com.dspl.malkey.domain.Fresdriver;
import com.dspl.malkey.domain.Fresdriverrate;
import com.dspl.malkey.domain.Freservation;
import com.dspl.malkey.domain.Freservationdiaryrpt;
import com.dspl.malkey.domain.Freshed;
import com.dspl.malkey.domain.Fresothersrvrate;
import com.dspl.malkey.domain.Fresothsrv;
import com.dspl.malkey.domain.Fresvehicle;
import com.dspl.malkey.domain.Fresvehiclerate;
import com.dspl.malkey.domain.Fresvehinv;
import com.dspl.malkey.domain.Fvehicledamage;
import com.dspl.malkey.report.FrentagreementRPT;
import com.dspl.malkey.report.FrentagrvehinvRPT;
import com.dspl.malkey.report.Reservation;
import com.dspl.malkey.util.MailMail;
import com.dspl.malkey.view.FreservationView;
import com.dspl.malkey.view.VehicleAvailabilityView;
public class FreservationSRVImpl implements FreservationSRV {

	@Resource(name="freservationDAO")
	FreservationDAO freservationDAO;

	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Resource(name="messageDAO")
	MessageDAO messageDAO;

	@Value( "${resource.location}")
	String resource_location = "C:\\";
	
	@Resource(name="mailMail")
	MailMail mailMail;
	
	@Value( "${email.alert.dear}")
	String contect1 = "";
	@Value( "${email.alert.content}")
	String contect2 = "";
	
	@Override
	public List<Freservation> List(int startIndex, int numItems) {
		return freservationDAO.List(startIndex, numItems);
	}

	@Override
	public List<Freservation> ListAll() {
		return freservationDAO.ListAll();
	}

	@Override
	public int count() {
		return freservationDAO.count();
	}

//	@Override
//	public Boolean create(Freservation freservation) {
//		try {
//			freservationDAO.create(freservation);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}

	@Override
	public Freservation findByID(String resno) {
		return freservationDAO.findByID(resno);
	}

	@Override
	public Boolean removeByID(String resno) {
		try {
			freservationDAO.removeByID(resno);
			return true;
		} 
		catch (PersistenceException e) 
		{
			throw new RuntimeException(messageDAO.getMessage(e));
		}
		catch (Exception e) 
		{
			throw new RuntimeException("Runtime Exception Occured.");
		}
	}

/*	@Override
	public Boolean udpate(Freservation freservation) {
		try {
			freservationDAO.udpate(freservation);
			return true;
		} catch (Exception e) {
			return false;
		}
	}*/

	@Override
	public String create(Freservation freservation,
			Freshed freshed,
			List<Fresvehicle> lstfresvehicle, 
			List<Fresaccs> lstfresaccs,
			List<Fresdriver> lstfresdriver, 
			List<Fresothsrv> lstfresothsrv,
			Fresvehiclerate fresvehiclerate, 
			List<Fresaccrate> lstfresaccrate,
			Fresdriverrate fresdriverrate,
			List<Fresothersrvrate> lstfresothersrvrate,
			List<Fresaddcharge> lstfresaddcharge,
			List<Fresclientdriver> lstfresclientdriver,
			Boolean ignoreAvailability
			) {
		String userMachine="";
		String addUser="";
		freservation.setAddmach(userInfoSRV.getMachineName());
		freservation.setAdduser(userInfoSRV.getUser());
		freshed.setAddmach(userInfoSRV.getMachineName());
		freshed.setAdduser(userInfoSRV.getUser());
		try{
		return freservationDAO.create(freservation,freshed, lstfresvehicle, lstfresaccs, lstfresdriver, lstfresothsrv, fresvehiclerate, lstfresaccrate, fresdriverrate, lstfresothersrvrate,lstfresaddcharge,lstfresclientdriver,ignoreAvailability);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}


/*	@Override
	public Boolean update(Freservation freservation,
			List<Fresvehicle> lstfresvehicle, List<Fresaccs> lstfresaccs,
			List<Fresdriver> lstfresdriver, List<Fresothsrv> lstfresothsrv,
			Fresvehiclerate fresvehiclerate, List<Fresaccrate> lstfresaccrate,
			Fresdriverrate fresdriverrate,
			List<Fresothersrvrate> lstfresothersrvrate,List<Fresaddcharge> lstfresaddcharge) {
		System.out.println("service call\nFreservation.update");
		try {
			freservation.setAddmach(userInfoSRV.getHostName());
			freservationDAO.update(freservation, lstfresvehicle, lstfresaccs, lstfresdriver, lstfresothsrv, fresvehiclerate, lstfresaccrate, fresdriverrate, lstfresothersrvrate,lstfresaddcharge);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}*/

	@Override
	public Reservation listAllByResno(String resno)
	{
		return freservationDAO.listAllByResno(resno);
	}
	
	
	// FUZULI
	@Override
	public List<FrentagreementRPT> listRptRentAgreement(String resNo,String lsJustServerName) {
		return freservationDAO.listRptRentAgreement(resNo,lsJustServerName);
	}

	@Override
	public List<FrentagrvehinvRPT> listRptRentVehiclesInventry(String resNo) {
		return freservationDAO.listRptRentVehiclesInventry(resNo);
	}

	@Override
	public String changeStatus(Freservation freservation,
			Freshed freshed,
			List<Fresvehicle> lstfresvehicle, List<Fresaccs> lstfresaccs,
			List<Fresdriver> lstfresdriver, List<Fresothsrv> lstfresothsrv,
			Fresvehiclerate fresvehiclerate, List<Fresaccrate> lstfresaccrate,
			Fresdriverrate fresdriverrate,
			List<Fresothersrvrate> lstfresothersrvrate,
			List<Fresaddcharge> lstfresaddcharge,
			List<Fresclientdriver> lstfresclientdriver,
			List<Fvehicledamage> lstFvehicledamage,List<Fresvehinv> lstFresvehinv
			) {
		System.out.println("changeStatus:"+freservation.getCohirestsid());
		try {
			sendChangedChanrgableDurationEmail(freservation);
			sendChangedDiscountEmail(freservation, fresvehiclerate);
			return freservationDAO.changeStatus(freservation,freshed, lstfresvehicle, lstfresaccs, lstfresdriver, lstfresothsrv, fresvehiclerate, lstfresaccrate, fresdriverrate, lstfresothersrvrate, lstfresaddcharge,lstfresclientdriver,lstFvehicledamage,lstFresvehinv);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}


	
	public boolean sendChangedChanrgableDurationEmail(Freservation freservation)
	{
		boolean flag=false;
		if(freservation.getCohirestsid().equals("CHECKOUT") && freservation.getChargdays()!=freservation.getNoofday())
		{
			flag=true;
			
			StringBuilder message=new StringBuilder();
			message.append("\n");
			message.append("FYI");
			message.append("\n");
			message.append("\n");
			message.append("Chargable Days Changed Notice");
			message.append("\n");
			message.append("Aggrement No: "+freservation.getResno()+" "+freservation.getAgrno());
			message.append("\n");
			message.append("By Whom Officer/User :"+userInfoSRV.getUser());
			message.append("\n");
			message.append("Actual Days :"+freservation.getNoofday());
			message.append("\n");
			message.append("Modified Chargable Days :"+freservation.getChargdays());
			message.append("\n");
			if(freservation.getBooked()!=null){
			message.append("Booked By :"+freservation.getBooked());
			message.append("\n");
			}
			if(freservation.getConfirmed()!=null){
			message.append("Confirmed By :"+freservation.getConfirmed());
			message.append("\n");
			}
			message.append("Net Total :"+freservation.getNettotal());
			message.append("\n");
			message.append("\n");
			message.append("Visit the following URL for more information");
			message.append("\n");
			message.append("http://220.247.244.141:8400/malkey_server/reports/rentagreement.jsp?resno="+freservation.getResno()+"&hiretypeid="+freservation.getHiretypeid() );
			message.append("\n");
			message.append("\n");
			//String fileURL="/malkey_server/reports/rentagreement.jsp?resno="+freservation.getResno()+"&hiretypeid="+freservation.getHiretypeid();
			mailMail.sendMail2(contect1,message+contect2,"Chargable Days Changed Notice",new String[]{"erangankumara@gmail.com","milindum@gmail.com"});
		}
		return flag;
	}
	
	
	public boolean sendChangedDiscountEmail(Freservation freservation,Fresvehiclerate fresvehiclerate)
	{
		boolean flag=false;
		if(freservation.getCohirestsid().equals("CHECKIN") && (freservation.getDiscount_xmile().doubleValue()>0 || fresvehiclerate.getDiscount().doubleValue()>0))
		{
			flag=true;
			
			StringBuilder message=new StringBuilder();
			message.append("\n");
			message.append("FYI");
			message.append("\n");
			message.append("\n");
			message.append("Discount Given Notice");
			message.append("\n");
			message.append("Aggrement No: "+freservation.getResno()+" "+freservation.getAgrno());
			message.append("\n");
			message.append("By Whom Officer/User :"+userInfoSRV.getUser());
			message.append("\n");
			message.append("Excess Mileage Discount :"+freservation.getDiscount_xmile().doubleValue()+" %");
			message.append("\n");
			message.append("Vehicle Rate Discount :"+fresvehiclerate.getDiscount().doubleValue()+" %");
			message.append("\n");
			if(freservation.getBooked()!=null){
				message.append("Booked By :"+freservation.getBooked());
				message.append("\n");
			}
			if(freservation.getConfirmed()!=null){
				message.append("Confirmed By :"+freservation.getConfirmed());
				message.append("\n");
			}
			message.append("Net Total :"+freservation.getNettotal());
			message.append("\n");
			message.append("Rate Parammeters : [Client Type: "+fresvehiclerate.getClienttype() +"] - [Hire Type: "+fresvehiclerate.getHiretypeid()  +"] - [Rate Type: "+ fresvehiclerate.getRatetype() +"] - [Vehicle Model: "+fresvehiclerate.getVehimodelid()+"]");
			message.append("\n");
			message.append("\n");
			message.append("Visit the following URL for more information");
			message.append("\n");
			message.append("http://220.247.244.141:8400/malkey_server/reports/rentagreement.jsp?resno="+freservation.getResno()+"&hiretypeid="+freservation.getHiretypeid() );
			message.append("\n");
			message.append("\n");
			//String fileURL="/malkey_server/reports/rentagreement.jsp?resno="+freservation.getResno()+"&hiretypeid="+freservation.getHiretypeid();
			mailMail.sendMail2(contect1,message+contect2,"Discount Given Notice",new String[]{"erangankumara@gmail.com","milindum@gmail.com"});
		}
		return flag;
	}
	
	@Override
	public List<Freservationdiaryrpt> getRentedVehicleDet(String dateFrom, String dateTo) {
		return freservationDAO.getRentedVehicleDet(dateFrom, dateTo);
	}

	@Override
	public List<Freservationdiaryrpt> getResDiaryData(String dateFrom, String dateTo, String hireTypeId, String cohireStsId) {
		return freservationDAO.getResDiaryData(dateFrom, dateTo, hireTypeId, cohireStsId);
	}

	@Override
	public List<Freservationdiaryrpt> getVehicleHireStatus(String dateFrom, String dateTo, String hireTypeId, String cohireStsId, String rateType) {
		return freservationDAO.getVehicleHireStatus(dateFrom, dateTo, hireTypeId, cohireStsId, rateType);
	}
	@Override
	public List<Freservationdiaryrpt> getVehicleHireStatusCO(String dateFrom, String dateTo, String hireTypeId, String cohireStsId, String rateType) {
		return freservationDAO.getVehicleHireStatusCO(dateFrom, dateTo, hireTypeId, cohireStsId, rateType);
	}
	@Override
	public List<Freservationdiaryrpt> getVehicleHireStatusCI(String dateFrom, String dateTo, String hireTypeId, String cohireStsId, String rateType) {
		return freservationDAO.getVehicleHireStatusCI(dateFrom, dateTo, hireTypeId, cohireStsId, rateType);
	}
	@Override
	public List<Freservationdiaryrpt> getVehicleHireStatusDaily(String dateFrom, String dateTo, String hireTypeId, String cohireStsId, String rateType) {
		return freservationDAO.getVehicleHireStatusDaily(dateFrom, dateTo, hireTypeId, cohireStsId, rateType);
	}
	@Override
	public List<Freservation> listByHedAgrno(String agrno) {
		return freservationDAO.listByHedAgrno(agrno);
	}

	@Override
	public List<Freservation> listPartByHedAgrno(String agrno) {
		return freservationDAO.listPartByHedAgrno(agrno);
	}

	@Override
	public String changeVehicle(String resno, List<Fresvehicle> lstFresvehicle,
			Fresvehiclerate fresvehiclerate) {
		return freservationDAO.changeVehicle(resno, lstFresvehicle, fresvehiclerate);
	}
	@Override
	public List<FreservationView> advanceSearch(String hireStatus,String hireType,String debcode,String regno,String dateFrom ,String dateTo) {
	try{	
		return freservationDAO.advanceSearch( hireStatus, hireType, debcode, regno, dateFrom , dateTo);
	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException(e.getMessage());
	}
	}

	@Override
	public String changeCheckOutVehicle(Freservation freservation,
			Freshed freshed, List<Fresvehicle> lstfresvehicle,
			List<Fresaccs> lstfresaccs, List<Fresdriver> lstfresdriver,
			List<Fresothsrv> lstfresothsrv, Fresvehiclerate fresvehiclerate,
			List<Fresaccrate> lstfresaccrate, Fresdriverrate fresdriverrate,
			List<Fresothersrvrate> lstfresothersrvrate,
			List<Fresaddcharge> lstfresaddcharge,
			List<Fresclientdriver> lstfresclientdriver,
			List<Fvehicledamage> lstCheckInFvehicledamage,
			List<Fresvehinv> lstCheckInFresvehinv,
			Freservation freservationCopy, Freshed freshedCopy,
			List<Fresvehicle> lstfresvehicleCopy,
			List<Fresaccs> lstfresaccsCopy, List<Fresdriver> lstfresdriverCopy,
			List<Fresothsrv> lstfresothsrvCopy,
			Fresvehiclerate fresvehiclerateCopy,
			List<Fresaccrate> lstfresaccrateCopy,
			Fresdriverrate fresdriverrateCopy,
			List<Fresothersrvrate> lstfresothersrvrateCopy,
			List<Fresaddcharge> lstfresaddchargeCopy,
			List<Fresclientdriver> lstfresclientdriverCopy,
			List<Fvehicledamage> lstCheckInFvehicledamageCopy,
			List<Fresvehinv> lstCheckInFresvehinvCopy) {
		return freservationDAO.changeCheckOutVehicle(freservation, freshed, lstfresvehicle, lstfresaccs, lstfresdriver, lstfresothsrv, fresvehiclerate, lstfresaccrate, fresdriverrate, lstfresothersrvrate, lstfresaddcharge,lstfresclientdriver, lstCheckInFvehicledamage, lstCheckInFresvehinv, freservationCopy, freshedCopy, lstfresvehicleCopy, lstfresaccsCopy, lstfresdriverCopy, lstfresothsrvCopy, fresvehiclerateCopy, lstfresaccrateCopy, fresdriverrateCopy, lstfresothersrvrateCopy, lstfresaddchargeCopy,lstfresclientdriverCopy, lstCheckInFvehicledamageCopy, lstCheckInFresvehinvCopy);
	}

	@Override
	public List<Freservation> listDetailsByHedAgrno(String agrno) {
		return freservationDAO.listDetailsByHedAgrno(agrno);
	}

	@Override
	public List<Freservationdiaryrpt> getAgrHistory(String dateFrom,
			String dateTo) {
		return freservationDAO.getAgrHistory(dateFrom, dateTo);
	}

	@Override
	public List<Freservationdiaryrpt> getVehicleHistory(String dateFrom,
			String dateTo, String regno) {
		return freservationDAO.getVehicleHistory(dateFrom, dateTo, regno);
	}

	@Override
	public String getOtherServiceList(String resno) {
		return freservationDAO.getOtherServiceList(resno);
	}

	@Override
	public List<FreservationView> loadClientHistory(String debcode) {
		return freservationDAO.loadClientHistory(debcode);
	}

	@Override
	public String getHistoryByID(String debcode) {
		return freservationDAO.getHistoryByID(debcode);
	}

	@Override
	public List<FreservationView> loadCHD(String agrno) {
		return freservationDAO.loadCHD(agrno);
	}

	@Override
	public List<FreservationView> loadCHD2() {
		return freservationDAO.loadCHD2();
	}

	@Override
	public List<FreservationView> loadCAH2(String debcode) {
		return freservationDAO.loadCAH(debcode);
	}

	@Override
	public List<FreservationView> loadCHDAlert() {
		return freservationDAO.loadCHDAlert();
	}

	@Override
	public List<VehicleAvailabilityView> loadVehicleAvailability(String dfrom,String dto) {
		
	    List<VehicleAvailabilityView> list= freservationDAO.loadVehicleAvailability(dfrom, dto);
	    
/*		Map<String, Object[]> data = new HashMap<String, Object[]>();
		
		for(int i=0;i<list.size();i++)
		{
			VehicleAvailabilityView vehicleAvailabilityView=list.get(i);
			
			List<String> row=new ArrayList<String>();
			row.add(vehicleAvailabilityView.getRegno());
			row.add(vehicleAvailabilityView.getVehimakeid());
			row.add(vehicleAvailabilityView.getVehimodelid());
			row.add(vehicleAvailabilityView.getMainseats());
			row.add(vehicleAvailabilityView.getYear());
			row.add(vehicleAvailabilityView.getFueltypeid());
			row.add(vehicleAvailabilityView.getVehitransid());
			row.add(vehicleAvailabilityView.getColourid());
			row.addAll(vehicleAvailabilityView.getAvailabilityCalander());
			data.put(Integer.toString(i+2), row.toArray());

			
		}
		
		
		//-------------------------------------------------------------------------------------			
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Vehicle Availability");
		sheet.autoSizeColumn(0); //adjust width of the first column
		setCellStyles(workbook);
		try {
			insertHeaderInfo(sheet, 3, dfrom, dto);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		   
		Set<String> keyset = data.keySet();
		int rownum = 10;
		for (String key : keyset) {
		    Row row = sheet.createRow(rownum++);
		    Object [] objArr = data.get(key);
		    int cellnum = 0;
		    for (Object obj : objArr) {
		        Cell cell = row.createCell(cellnum++);
		        if(obj instanceof Date) 
		            cell.setCellValue((Date)obj);
		        else if(obj instanceof Boolean)
		            cell.setCellValue((Boolean)obj);
		        else if(obj instanceof String){
		            cell.setCellValue((String)obj);
		            if(cellnum>8){
		            if(!obj.equals("A"))
		            cell.setCellStyle(styleUnavailable);
		            }
		        }
		        else if(obj instanceof Double)
		            cell.setCellValue((Double)obj);
		    }
		}
		 
		try {
			
		    FileOutputStream out = 
		            //new FileOutputStream(new File("/VA_From"+dfrom+"_To"+dto+"_"+Math.random()+".xls"));
		    //new FileOutputStream(new File("C:\\VA_From"+dfrom+"_To"+dto+"_"+Math.random()+".xls"));
		    new FileOutputStream(new File(resource_location+"\\VehicleAvailability_From"+dfrom+"_To"+dto+"_"+Math.random()+".xls"));
		    workbook.write(out);
		    out.close();
		    System.out.println("Excel written successfully..");
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		//-------------------------------------------------------------------------------------
		
		
*/
		
		
		return list;
	}

	@Override
	public String createCopy(String resno) {
		System.out.println("#resno# :"+resno);
		return freservationDAO.createCopy(resno);
	}

	@Override
	public List<FreservationView> loadCAH(String debcode, String fromDate,
			String toDate) {
		return freservationDAO.loadCAH(debcode, fromDate, toDate);
	}

	@Override
	public List<Freservationdiaryrpt> getResDiaryDataHistory(String dateFrom,
			String dateTo, String hireTypeId) {
		return freservationDAO.getResDiaryDataHistory(dateFrom, dateTo, hireTypeId);
	}

	@Override
	public List<Freservationdiaryrpt> debtorReport(String dateFrom,
			String dateTo,String reportType) {
		return freservationDAO.debtorReport(dateFrom, dateTo,reportType);
	}
	
	
	
	
	
/*	
	 private CellStyle cs = null;
	 private CellStyle csBold = null;
	 private CellStyle csTop = null;
	 private CellStyle csRight = null;
	 private CellStyle csBottom = null;
	 private CellStyle csLeft = null;
	 private CellStyle csTopLeft = null;
	 private CellStyle csTopRight = null;
	 private CellStyle csBottomLeft = null;
	 private CellStyle csBottomRight = null;
	 private CellStyle styleDate= null;
	 private CellStyle styleUnavailable = null;
	 
	 private void setCellStyles(Workbook wb) {
		 
		  //font size 10
		  Font f = wb.createFont();
		  f.setFontHeightInPoints((short) 10);
		 
		  //Simple style 
		  cs = wb.createCellStyle();
		  cs.setFont(f);
		 
		  //Bold Fond
		  Font bold = wb.createFont();
		  bold.setBoldweight(Font.BOLDWEIGHT_BOLD);
		  bold.setFontHeightInPoints((short) 10);
		 
		  //Bold style 
		  csBold = wb.createCellStyle();
		  csBold.setBorderBottom(CellStyle.BORDER_THIN);
		  csBold.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		  csBold.setFont(bold);
		 
		  //Setup style for Top Border Line
		  csTop = wb.createCellStyle();
		  csTop.setBorderTop(CellStyle.BORDER_THIN);
		  csTop.setTopBorderColor(IndexedColors.BLACK.getIndex());
		  csTop.setFont(f);
		 
		  //Setup style for Right Border Line
		  csRight = wb.createCellStyle();
		  csRight.setBorderRight(CellStyle.BORDER_THIN);
		  csRight.setRightBorderColor(IndexedColors.BLACK.getIndex());
		  csRight.setFont(f);
		 
		  //Setup style for Bottom Border Line
		  csBottom = wb.createCellStyle();
		  csBottom.setBorderBottom(CellStyle.BORDER_THIN);
		  csBottom.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		  csBottom.setFont(f);
		 
		  //Setup style for Left Border Line
		  csLeft = wb.createCellStyle();
		  csLeft.setBorderLeft(CellStyle.BORDER_THIN);
		  csLeft.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		  csLeft.setFont(f);
		 
		  //Setup style for Top/Left corner cell Border Lines
		  csTopLeft = wb.createCellStyle();
		  csTopLeft.setBorderTop(CellStyle.BORDER_THIN);
		  csTopLeft.setTopBorderColor(IndexedColors.BLACK.getIndex());
		  csTopLeft.setBorderLeft(CellStyle.BORDER_THIN);
		  csTopLeft.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		  csTopLeft.setFont(f);
		 
		  //Setup style for Top/Right corner cell Border Lines
		  csTopRight = wb.createCellStyle();
		  csTopRight.setBorderTop(CellStyle.BORDER_THIN);
		  csTopRight.setTopBorderColor(IndexedColors.BLACK.getIndex());
		  csTopRight.setBorderRight(CellStyle.BORDER_THIN);
		  csTopRight.setRightBorderColor(IndexedColors.BLACK.getIndex());
		  csTopRight.setFont(f);
		 
		  //Setup style for Bottom/Left corner cell Border Lines
		  csBottomLeft = wb.createCellStyle();
		  csBottomLeft.setBorderBottom(CellStyle.BORDER_THIN);
		  csBottomLeft.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		  csBottomLeft.setBorderLeft(CellStyle.BORDER_THIN);
		  csBottomLeft.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		  csBottomLeft.setFont(f);
		 
		  //Setup style for Bottom/Right corner cell Border Lines
		  csBottomRight = wb.createCellStyle();
		  csBottomRight.setBorderBottom(CellStyle.BORDER_THIN);
		  csBottomRight.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		  csBottomRight.setBorderRight(CellStyle.BORDER_THIN);
		  csBottomRight.setRightBorderColor(IndexedColors.BLACK.getIndex());
		  csBottomRight.setFont(f);
		  
		    
		    // Orange "foreground", foreground being the fill foreground not the font color.
		  styleDate = wb.createCellStyle();
		  styleDate.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		  styleDate.setFillPattern(CellStyle.SOLID_FOREGROUND);
		    
		    // Orange "foreground", foreground being the fill foreground not the font color.
		    styleUnavailable = wb.createCellStyle();
		    styleUnavailable.setFillForegroundColor(IndexedColors.RED.getIndex());
		    styleUnavailable.setFillPattern(CellStyle.SOLID_FOREGROUND);
		    
		    
		 }
	 
	 private int insertHeaderInfo(Sheet sheet, int index,String dfrom,String dto) throws ParseException{
	 
		   //Get current Date and Time
	   Date date = new Date(System.currentTimeMillis());
	   DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
   
	   Date dateFrom=format.parse(dfrom);
	   Date dateTo=format.parse(dto); 
   
	   long diff = dateTo.getTime() - dateFrom.getTime();
	   long diffDays = (diff / (24 * 60 * 60 * 1000))+1;
		    
	  int rowIndex = index;
	  Row row = null;
	  Cell c = null;
	 
	  rowIndex++;
	  row = sheet.createRow(rowIndex);
	  c = row.createCell(0);
	  c.setCellValue("FROM DATE:");
	  c.setCellStyle(csTopLeft);
	  c = row.createCell(1);
	  c.setCellStyle(csTop);
	  c = row.createCell(2);
	  c.setCellValue(dfrom);
	  c.setCellStyle(csTopRight);
	 
	  rowIndex++;
	  row = sheet.createRow(rowIndex);
	  c = row.createCell(0);
	  c.setCellValue("TO DATE:");
	  c.setCellStyle(csLeft);
	  c = row.createCell(2);
	  c.setCellValue(dto);
	  c.setCellStyle(csRight);
	  
	  rowIndex = rowIndex + 3;
	  row = sheet.createRow(rowIndex);
	  c = row.createCell(0);
	  c.setCellValue("REG NO.");
	  c.setCellStyle(csBold);
	  c = row.createCell(1);
	  c.setCellValue("MAKE");
	  c.setCellStyle(csBold);
	  c = row.createCell(2);
	  c.setCellValue("MODEL");
	  c.setCellStyle(csBold);
	  c = row.createCell(3);
	  c.setCellValue("SEATING CAPACITY");
	  c.setCellStyle(csBold);
	  c = row.createCell(4);
	  c.setCellValue("Y.O.M");
	  c.setCellStyle(csBold);
	  
	  c = row.createCell(5);
	  c.setCellValue("FUEL");
	  c.setCellStyle(csBold);
	  
	  c = row.createCell(6);
	  c.setCellValue("TRANSMISSION");
	  c.setCellStyle(csBold);
	  
	  c = row.createCell(7);
	  c.setCellValue("COLOUR");
	  c.setCellStyle(csBold);
	 
	  
		for(int i=1;i<=diffDays;i++)
		{
			  c = row.createCell(7+i);
			  c.setCellValue(Integer.toString(i));
			  c.setCellStyle(csBold);
		}
		
	  return rowIndex;
	 
	 }
	 */
	 
	 
}
