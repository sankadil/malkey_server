package com.dspl.malkey.dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fanalysis;
import com.dspl.malkey.domain.Fdebtor;
import com.dspl.malkey.domain.Fexpirationlistingrpt;
import com.dspl.malkey.domain.Fmaintenance;
import com.dspl.malkey.domain.Fmainttype;
import com.dspl.malkey.domain.Fvehicle;
import com.dspl.malkey.domain.Fvehicledamage;
import com.dspl.malkey.domain.Fvehicleinventry;
import com.dspl.malkey.domain.FvehicleinventryPK;
import com.dspl.malkey.domain.Fvehiclepic;
import com.dspl.malkey.domain.Fvehiclerate;
import com.dspl.malkey.service.FileUploadSRV;
import com.dspl.malkey.service.FvehiclerateSRV;
import com.dspl.malkey.service.UserInfoSRV;

import flex.messaging.FlexContext;
import flex.messaging.io.ArrayCollection;

public class FvehicleDAOImpl implements FvehicleDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fileUploadSRV")
	FileUploadSRV fileUploadSRV;

	@Resource(name="fanalysisDAO")
	FanalysisDAO fanalysisDAO;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;

	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Resource(name="fvehiclerateSRV")
	FvehiclerateSRV fvehiclerateSRV;

	@Transactional
	@Override
	public List<Fvehicle> list(int startIndex, int numItems) {
		return em.createNamedQuery("FvehicleListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fvehicle> listAll() {
		return em.createNamedQuery("FvehicleListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fvehicle").getSingleResult());
	}
	@Transactional
	@Override
	public int isDupplicate(String regno) {
		regno = regno.replace("-", "");
		regno = regno.trim();
		regno = regno.toUpperCase();
		String sql="SELECT COUNT(*) FROM FVEHICLE WHERE UPPER(REPLACE( REPLACE(REGNO,' ',''),'-',''))=UPPER('"+regno+"')";
		System.out.println("sql :"+sql);
		/*return*/ Integer i=(Integer)(em.createNativeQuery(sql).getSingleResult());
		System.out.println("I ="+i);
		return i;
	}

	@Transactional
	@Override
	public void create(Fvehicle fvehicle, List<Fvehicledamage> fvehicledamage, List<Fvehicleinventry> fvehicleinventry, List<Fvehiclerate> fvehiclerate, List<Fvehiclepic> fvehiclepic) {
		String lsRegNo = fvehicle.getRegno();
		String lsVehiModelID = fvehicle.getVehimodelid();
		String lsAddUser = userInfoSRV.getUser();
		String lsAddMachine = userInfoSRV.getMachineName();
		Calendar ldToday = Calendar.getInstance();
		
		if(isDupplicate(lsRegNo)>0)
		{
			throw new RuntimeException("Vehicle Reg. Number Already Available.");
		}
		// fVehicleDamage
		updateFvehicledamage(lsRegNo, fvehicledamage, lsAddUser);
//		Iterator<Fvehicledamage> newVehiDamage = fvehicledamage.iterator();
//		while (newVehiDamage.hasNext()){
//			Fvehicledamage newRecord = (Fvehicledamage)newVehiDamage.next();
//			newRecord.setRegno(lsRegNo);		// Always update with the original RegNo to ensure the relational data points the correct parent record
//			
//			if (newRecord.getAdduser()==null || newRecord.getAdduser().isEmpty())
//				newRecord.setAdduser(lsAddUser);
//			
//			if (newRecord.getUuid()==null || newRecord.getUuid().isEmpty())
//				newRecord.setUuid(UUID.randomUUID().toString());
//			
//			em.persist(newRecord);
//		}
		
		// fVehicleInventry
		updateFvehicleinventry(lsRegNo, fvehicleinventry, lsAddUser);
//		Iterator<Fvehicleinventry> newVehiInv = fvehicleinventry.iterator();
//		while (newVehiInv.hasNext()){
//			Fvehicleinventry newRecord = (Fvehicleinventry)newVehiInv.next();
//			FvehicleinventryPK newRecordPK = newRecord.getId(); 			// Always update with the original RegNo to ensure the relational data points the correct parent record
//			newRecordPK.setRegno(lsRegNo);
//			newRecord.setId(newRecordPK);
//			
//			if (newRecord.getAdduser()==null || newRecord.getAdduser().isEmpty())
//				newRecord.setAdduser(lsAddUser);
//			
//			if (newRecord.getUuid()==null || newRecord.getUuid().isEmpty())
//				newRecord.setUuid(UUID.randomUUID().toString());
//			
//			em.persist(newRecord);
//		}

		// fVehicleRate - Remove existing records and insert the latest
		fvehiclerateSRV.udpateList(lsVehiModelID, fvehiclerate);
		
		// fVehiclePic
		updateFvehiclePic(lsRegNo, fvehiclepic);
		
		// fAnalysis
		updateFanalysisWithVehiInfo(lsRegNo, fvehicle.getDescription(), lsAddUser);
		
		// fMaintenance
		updateFmaintenanceWithDefaultVehicleTasks(fvehicle);
		
		// fVehicle
		fvehicle.setAdduser(lsAddUser);
		fvehicle.setAddmach(lsAddMachine);
		fvehicle.setAdddate(ldToday);
		fvehicle.setUuid(UUID.randomUUID().toString());
		em.persist(fvehicle);
	}

	@Transactional
	@Override
	public Fvehicle findByID(String sRegNo) {
		return em.find(Fvehicle.class, sRegNo);
	}

	@Transactional
	@Override
	public void removeByID(String sRegNo) {
		// Remove records from relational table
		em.createNativeQuery("DELETE FROM Fvehicledamage WHERE regno='"+sRegNo+"'").executeUpdate();		// fVehicleDamage
		em.createNativeQuery("DELETE FROM Fvehicleinventry WHERE regno='"+sRegNo+"'").executeUpdate();	// fVehicleInventry
		em.createNativeQuery("DELETE FROM Fvehiclepic WHERE regno='"+sRegNo+"'").executeUpdate();		// fVehiclePic
		em.createNativeQuery("DELETE FROM Fmaintenance WHERE regno='"+sRegNo+"'").executeUpdate();		// fMaintenace
		
		// Fvehicle - Parent Table
		em.remove(em.find(Fvehicle.class, sRegNo));
	}

	private void setAddedDetails(Fvehicle fvehicle)
	{
		Fvehicle temp=findByID(fvehicle.getRegno());
		fvehicle.setAdddate(temp.getAdddate());
		fvehicle.setAdduser(temp.getAdduser());
		fvehicle.setAddmach(temp.getAddmach());
	}
		
	@Transactional
	@Override
	public void update(Fvehicle fvehicle, List<Fvehicledamage> fvehicledamage, List<Fvehicleinventry> fvehicleinventry, List<Fvehiclerate> fvehiclerate, List<Fvehiclepic> fvehiclepic) {
		String lsRegNo = fvehicle.getRegno();
		String lsVehiModelID = fvehicle.getVehimodelid();
		String lsModifiedUser = userInfoSRV.getUser();
		String lsModifiedMachine = userInfoSRV.getMachineName();
		Calendar ldToday = Calendar.getInstance();
		
		// fVehicleDamage - Remove existing records and insert the latest
		updateFvehicledamage(lsRegNo, fvehicledamage, lsModifiedUser);
//		em.createNativeQuery("DELETE FROM Fvehicledamage WHERE regno='"+lsRegNo+"'").executeUpdate();
//		Iterator<Fvehicledamage> newVehiDamage = fvehicledamage.iterator();
//		while (newVehiDamage.hasNext()){
//			Fvehicledamage newRecord = (Fvehicledamage)newVehiDamage.next();
//			newRecord.setRegno(lsRegNo);		// Always update with the original RegNo to ensure the relational data points the correct parent record
//			
//			if (newRecord.getAdduser()==null || newRecord.getAdduser().isEmpty())
//				newRecord.setAdduser(lsAddUser);
//			
//			if (newRecord.getUuid()==null || newRecord.getUuid().isEmpty())
//				newRecord.setUuid(UUID.randomUUID().toString());
//			
//			em.persist(newRecord);
//		}
		
		// fVehicleInventry - Remove existing records and insert the latest
		updateFvehicleinventry(lsRegNo, fvehicleinventry, lsModifiedUser);
//		em.createNativeQuery("DELETE FROM Fvehicleinventry WHERE regno='"+lsRegNo+"'").executeUpdate();
//		Iterator<Fvehicleinventry> newVehiInv = fvehicleinventry.iterator();
//		while (newVehiInv.hasNext()){
//			Fvehicleinventry newRecord = (Fvehicleinventry)newVehiInv.next();
//			FvehicleinventryPK newRecordPK = newRecord.getId(); 			// Always update with the original RegNo to ensure the relational data points the correct parent record
//			newRecordPK.setRegno(lsRegNo);
//			newRecord.setId(newRecordPK);
//			
//			if (newRecord.getAdduser()==null || newRecord.getAdduser().isEmpty())
//				newRecord.setAdduser(lsAddUser);
//			
//			if (newRecord.getUuid()==null || newRecord.getUuid().isEmpty())
//				newRecord.setUuid(UUID.randomUUID().toString());
//			
//			em.persist(newRecord);
//		}
		
		// fVehicleRate - Remove existing records and insert the latest
		fvehiclerateSRV.udpateList(lsVehiModelID, fvehiclerate);
		
		// fVehiclePic - Remove existing images
		em.createNativeQuery("DELETE FROM Fvehiclepic WHERE regno='"+lsRegNo+"'").executeUpdate();
		
		// fVehiclePic - Update/ upload with the latest image info
		updateFvehiclePic(lsRegNo, fvehiclepic);
		
		// fAnalysis
		updateFanalysisWithVehiInfo(lsRegNo, fvehicle.getDescription(), lsModifiedUser);
		
		// fMaintenance
		updateFmaintenanceWithDefaultVehicleTasks(fvehicle);
		
		// fVehicle
		fvehicle.setModifieduser(lsModifiedUser);
		fvehicle.setModifiedmach(lsModifiedMachine);
		fvehicle.setModifieddate(ldToday);
		setAddedDetails(fvehicle);
		if (fvehicle.getUuid()==null || fvehicle.getUuid().isEmpty())
			fvehicle.setUuid(UUID.randomUUID().toString());
		
		em.merge(fvehicle);
	}

	
	@Transactional
	private void updateFvehicledamage(String sRegNo, List<Fvehicledamage> fvehicledamage, String sAddUser) {
		em.createNativeQuery("DELETE FROM Fvehicledamage WHERE regno='"+sRegNo+"'").executeUpdate();
		if(fvehicledamage==null)
			return;
		Iterator<Fvehicledamage> newVehiDamage =  fvehicledamage.iterator();
		while (newVehiDamage!=null && newVehiDamage.hasNext()){
			Fvehicledamage newRecord = (Fvehicledamage)newVehiDamage.next();
			newRecord.setRegno(sRegNo);		// Always update with the original RegNo to ensure the relational data points the correct parent record
			
			if (newRecord.getAdduser()==null || newRecord.getAdduser().isEmpty())
				newRecord.setAdduser(sAddUser);
			
			if (newRecord.getUuid()==null || newRecord.getUuid().isEmpty())
				newRecord.setUuid(UUID.randomUUID().toString());
			
			em.persist(newRecord);
		}
	}
	
	@Transactional
	private void updateFvehicleinventry(String sRegNo, List<Fvehicleinventry> fvehicleinventry, String sAddUser) {
		// fVehicleInventry - Remove existing records and insert the latest
		em.createNativeQuery("DELETE FROM Fvehicleinventry WHERE regno='"+sRegNo+"'").executeUpdate();
		Iterator<Fvehicleinventry> newVehiInv = fvehicleinventry.iterator();
		while (newVehiInv.hasNext()){
			Fvehicleinventry newRecord = (Fvehicleinventry)newVehiInv.next();
			FvehicleinventryPK newRecordPK = newRecord.getId(); 			// Always update with the original RegNo to ensure the relational data points the correct parent record
			newRecordPK.setRegno(sRegNo);
			newRecord.setId(newRecordPK);
			
			if (newRecord.getAdduser()==null || newRecord.getAdduser().isEmpty())
				newRecord.setAdduser(sAddUser);
			
			if (newRecord.getUuid()==null || newRecord.getUuid().isEmpty())
				newRecord.setUuid(UUID.randomUUID().toString());
			
			em.persist(newRecord);
		}
	}
	
	@Transactional
	private void updateFanalysisWithVehiInfo(String sRegNo, String description, String addUser) {
		// Verify if Fanalysis already contains record for this RegNo 		
		List<Fanalysis> fanalysis = fanalysisDAO.listByAnCodeOfVehicle(sRegNo);
		
		// Check if records available
		if (fanalysis.size()==0){
			// Insert a new record
			Fanalysis newAnalysis = new Fanalysis();
			newAnalysis.setAncode(sRegNo);
			newAnalysis.setAdec(description);
			newAnalysis.setAdduser(addUser);
			newAnalysis.setTyp("V");
			
			em.persist(newAnalysis);
		} else {
			// Update existing records with the latest
			Iterator<Fanalysis> analysisItr = fanalysis.iterator();
			while (analysisItr.hasNext()){
				Fanalysis analysisRec = (Fanalysis)analysisItr.next();
				analysisRec.setAncode(sRegNo);
				analysisRec.setAdec(description);
				analysisRec.setAdduser(addUser);
				
				em.merge(analysisRec);
			}
		}
	}

	@Transactional
	private void updateFvehiclePic(String sRegNo, List<Fvehiclepic> fvehiclepic) {
		String targetFolder = "/image/vehicle/";
		String serverName = "/resource";//FlexContext.getHttpRequest().getContextPath();		// Only the server name (eg. /malkey_server)
		
		//Update/ upload with the latest image info
		Iterator<Fvehiclepic> newVehiPic = fvehiclepic.iterator();
		while (newVehiPic.hasNext()){
			Fvehiclepic vehiPicDtl = (Fvehiclepic)newVehiPic.next();
			
			// Check if the image is already uploaded or a new one
			if (vehiPicDtl.getImageurl().trim().length()==0){
				String lsImageName = vehiPicDtl.getImagename();		// Image name with the file extension of the original file format
				String lsImageURL = serverName + targetFolder + lsImageName;
				
				// Upload the New File to server
				Boolean lbFileUploaded = fileUploadSRV.uploadFileToServer(lsImageName, vehiPicDtl.getImagedata(), targetFolder);
				
				if (lbFileUploaded==true)
					vehiPicDtl.setImageurl(lsImageURL);
				else
					continue;		// Uploading to the server failed, skip updating the table with this image info
			}
			
			// Update the table with the image info
			Fvehiclepic newRecord = new Fvehiclepic();
			newRecord.setRegno(sRegNo);		// Always update with the original RegNo to ensure the relational data points the correct parent record
			newRecord.setDefaultimage(vehiPicDtl.getDefaultimage());
			newRecord.setImageurl(vehiPicDtl.getImageurl());
			em.persist(newRecord);
		}
	}
	
	@Transactional
	private void updateFmaintenanceWithDefaultVehicleTasks(Fvehicle fvehicle) {
		if (fvehicle.getVehistsid().equals("SOLD"))
			return;		// If this vehicle had been SOLD do not update fMaintenance
		
		String lsStatusId = "PENDING";
		String lsFrequency = "Recurring";
		String lsRaisedByType = "Internal";
		String lsRaisedBy = "";
		String lsAssignedTo = "";
		String lsPriority = "High";
		String lsComment = "Default maintenance task";
		String lsRegNo = fvehicle.getRegno();
		
		// Retrieve the default tasks
		List<Fmainttype> fmainttype = em.createNamedQuery("FmainttypeListDefaultTypes").getResultList();
		
		Iterator<Fmainttype> vehicleMaintType = fmainttype.iterator();
		while (vehicleMaintType.hasNext()){
			Fmainttype maintType = (Fmainttype)vehicleMaintType.next();
			String lsMaintType = maintType.getTypeid().trim();
			
			Calendar ldTaskStart = Calendar.getInstance();
			
			if (lsMaintType.equals("INSURANCE")){
				ldTaskStart = fvehicle.getInsdexpiry();	// Ins. Policy expiry date
				// Get hold of the Ins. Policy start date
				ldTaskStart.add(Calendar.YEAR, -1);		
				ldTaskStart.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			if (lsMaintType.equals("REVLICENSE")){
				ldTaskStart = fvehicle.getDrevlicense();
			}

			if (lsMaintType.equals("EMISSION")){
				ldTaskStart = fvehicle.getDemission();
			}
			
			if (lsMaintType.equals("FITNESS")){
				if (fvehicle.getDfitness()==null)
					continue;	// This vehicle do not require a fitness certificate
				else
					ldTaskStart = fvehicle.getDfitness(); 
			}
			
			if (lsMaintType.equals("LEASE")){
				if (fvehicle.getLeasedlastpay()==null)
					continue;	// Lease is not applicable for this vehicle
				else
					ldTaskStart = fvehicle.getLeasedlastpay(); 
			}
				
			
			// Check if fMaintenance is already updated with a maintenance task for this RegNo
			Query query = em.createNamedQuery("FmaintenanceListByStatusRegnoType");
			query.setParameter("statusid", lsStatusId);
			query.setParameter("regno", lsRegNo);
			query.setParameter("typeid", lsMaintType);
			List<Fmaintenance> fmaintenance = query.getResultList(); 
			
			if (fmaintenance.size()==0){
				// Insert a new record
				Fmaintenance newMaintenance = new Fmaintenance();
				newMaintenance.setTxndate(Calendar.getInstance());
				newMaintenance.setRegno(lsRegNo);
				newMaintenance.setTypeid(lsMaintType);
				newMaintenance.setStatusid(lsStatusId);
				newMaintenance.setFrequency(lsFrequency);
				newMaintenance.setMileage(maintType.getMileage());
				newMaintenance.setPeriod(maintType.getPeriod());
				newMaintenance.setDstart(resetTime(ldTaskStart));
				newMaintenance.setRaisedbytype(lsRaisedByType);
				newMaintenance.setRaisedby(lsRaisedBy);
				newMaintenance.setAssignedto(lsAssignedTo);
				newMaintenance.setPriority(lsPriority);
				newMaintenance.setComment(lsComment);
				newMaintenance.setActionedby("");
				newMaintenance.setActiontaken("");
				newMaintenance.setAdduser(fvehicle.getAdduser());
				newMaintenance.setAddmach(fvehicle.getAddmach());
				newMaintenance.setAdddate(resetTime(Calendar.getInstance()));
				
				fmaintenanceDAO.create(newMaintenance);
			} else {
				// Update existing records with the latest
				Iterator<Fmaintenance> vehicleMaintenance = fmaintenance.iterator();
				while (vehicleMaintenance.hasNext()){
					Fmaintenance maintenanceRec = (Fmaintenance)vehicleMaintenance.next();
					maintenanceRec.setDstart(resetTime(ldTaskStart));
					maintenanceRec.setAdduser(fvehicle.getAdduser());
					maintenanceRec.setAddmach(fvehicle.getAddmach());
					maintenanceRec.setAdddate(resetTime(Calendar.getInstance()));
					
					fmaintenanceDAO.update(maintenanceRec);
				}
			}
		}
	}
	
	private Calendar resetTime(Calendar tmpCal)
	{
		tmpCal.set(Calendar.HOUR_OF_DAY, 0);
		tmpCal.set(Calendar.MINUTE, 0);
		tmpCal.set(Calendar.SECOND, 0);
		tmpCal.set(Calendar.MILLISECOND, 0);
		return tmpCal;
	}

	@Transactional
	@Override
	public List<Fexpirationlistingrpt> getExpirationList(String fromDate, String toDate, String expType) {
		try {
		
			String query ="SELECT * FROM"; 
			query += " (SELECT"; 
			query += " v.regno,";
			query += " (SELECT description FROM fvehiclemake WHERE vehimakeid=v.vehimakeid) AS make,";
			query += " (SELECT description FROM fvehiclemodel WHERE vehimodelid=v.vehimodelid) AS model,";
			
			//expType   L=License, E=Emission
			String fieldName="";
			if(expType.trim().equals("L")==true)
				fieldName="v.drevlicense";
			else if(expType.trim().equals("E")==true)
				fieldName="v.demission";
			else
				return null;
			
			query += " "+fieldName+" AS [periodfrom],";

			query += " DATEADD(year,1,"+fieldName+") AS [periodto],";
			query += " v.ownertype,";
			query += " (SELECT description FROM fvehiclestatus WHERE vehistsid=v.vehistsid) AS rentstatus";
			query += " FROM fvehicle AS v where v.vehistsid NOT IN ('SOLD','RTOWNER')) AS v";
//			query += " FROM fvehicle AS v) AS v";
			
			if(fromDate.trim().length()>0 && toDate.trim().length()>0)
				query += " WHERE v.periodto>='"+fromDate+"' AND v.periodto<='"+toDate+"'";//" AND v.vehistsid NOT IN ('SOLD','RTOWNER') ";
			
			query += " ORDER BY v.periodto,v.regno";
			
			//Get Current Date
			Timestamp curDate = new Timestamp(new Date().getTime());
			
			Query Q = em.createNativeQuery(query);
			List<Object[]> l1 = Q.getResultList();
			List<Fexpirationlistingrpt> list=new ArrayCollection();
			
			String regno;
			String make;
			String model;
			Timestamp periodfrom;
			Timestamp periodto;
			String ownership;
			String rentstatus;
			
			for(Object[] r : l1)
			{
				regno=(String)r[0];
				make=(String)r[1];
				model=(String)r[2];
				periodfrom=(Timestamp)r[3];
				periodto=(Timestamp)r[4];
				ownership=(String)r[5];
				rentstatus=(String)r[6];
				
				int daysleft=0;
				if(curDate.compareTo(periodto)<0){ //Check whether current date is less than periodto
					daysleft=(int)((periodto.getTime()-curDate.getTime())/(24 * 60 * 60 * 1000));
					daysleft+=2;
				}
				
				Fexpirationlistingrpt obj=new Fexpirationlistingrpt();
				obj.setRegno(regno);
				obj.setMake(make);
				obj.setModel(model);
				obj.setPeriodfrom(periodfrom);
				obj.setPeriodto(periodto);
				obj.setOwnership(ownership);
				obj.setRentstatus(rentstatus);
				obj.setDaysleft(daysleft);
				
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			System.out.println("Fvehicle getExpirationList: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
/*
	public List<Fvehicle> getVehicleListCustomized(String vstatus,String ownertype) {
		try {
			//'SOLD'
			//select * from fvehicle where vehistsid='AVAILABLE' 'SOLD' 'ATREPAIR'  
			//select * from fvehicle where ownertype='Company' 'Outside' 
			String subQuery="";
			if(vstatus!=null)
			{
				subQuery+=" V.vehistsid='"+vstatus+"' AND";//AVAILABLE,SOLD,ATREPAIR,...etc
			}
			if(vstatus!=null)
			{
				subQuery+=" V.ownertype='"+ownertype+"' AND";//Company,Outside,...etc
			}
	*/		
	@Transactional
	@Override
	public List<Fvehicle> getVehicleSummary2(String vstatus,String ownertype) {
		try {
			
			//'SOLD'
			//select * from fvehicle where vehistsid='AVAILABLE' 'SOLD' 'ATREPAIR'  
			//select * from fvehicle where ownertype='Company' 'Outside' 
			String subQuery="";
/*			if((vstatus!=null && !vstatus.equals("all")) || (ownertype!=null && !ownertype.equals("all")))
			{
				subQuery+=" AND ";//AVAILABLE,SOLD,ATREPAIR,...etc
			}*/
			
			if(vstatus!=null && !vstatus.equals("all"))
			{
				subQuery+=" AND v.vehistsid='"+vstatus+"' ";
			}
			
			if(ownertype!=null && !ownertype.equals("all"))
			{
/*				if(vstatus!=null && !vstatus.equals("all"))
				{
					subQuery+=" AND ";
				}*/
				subQuery+=" AND v.ownertype='"+ownertype+"'";
			}
			
			
			String query="SELECT v.regno,";
			query += " (SELECT description FROM fvehiclemake WHERE vehimakeid=v.vehimakeid) AS [make],";
			query += " v.description,v.chassisno,v.engineno,";
			query += " (CONVERT(VARCHAR(10),v.mainseats)+'-'+CONVERT(VARCHAR(10),v.jumpseats)) AS seatingcapacity,";
			query += " v.dpurchase,";
			query += " (SELECT description FROM fvehiclestatus WHERE vehistsid=v.vehistsid) AS status ,";
			
			query += " (SELECT description from fvehiclemodel as m WHERE m.vehimakeid=v.vehimakeid AND m.vehimodelid=v.vehimodelid) as [model], ";
			query += " v.year,v.curmileage,v.engsizeid ";
			
			query += " FROM fvehicle AS v WHERE v.dummyvehi<>1  "+subQuery+"  ORDER BY v.regno";
			
			Query Q = em.createNativeQuery(query);
			List<Object[]> l1 = Q.getResultList();
			List<Fvehicle> list = new ArrayCollection();
			
			String regno;
			String make;
			String description;
			String chassisno;
			String engineno;
			String seatingcapacity;
			Timestamp tsdpurchase;
			String status;
			
			String model;
			Integer year=new Integer(0);
			Integer curmileage=new Integer(0);
			String engsizeid;
			
			for(Object[] r : l1){
				regno=(String)r[0];
				make=(String)r[1];
				description=(String)r[2];
				chassisno=(String)r[3];
				engineno=(String)r[4];
				seatingcapacity=(String)r[5];
				tsdpurchase=(Timestamp)r[6];
				status=(String)r[7];

				model=(String)r[8];
				if(r[9]!=null)
				year=(Integer)r[9];
				if(r[10]!=null)
				curmileage=(Integer)r[10];
				engsizeid=(String)r[11];
				
				Fvehicle obj=new Fvehicle();
				obj.setRegno(regno);
				obj.setMake(make);
				obj.setDescription(description);
				obj.setChassisno(chassisno);
				obj.setEngineno(engineno);
				obj.setSeatingcapacity(seatingcapacity);
				obj.setTsdpurchase(tsdpurchase);
				obj.setStatus(status);
				
				obj.setVehimodelid(model);
				obj.setYear(year);
				obj.setCurmileage(curmileage);
				obj.setEngsizeid(engsizeid);
				
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			System.out.println("FvehicleDaoImpl: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	//commented on 2015.08.31 
	 * Dummy vehicles should be eliminated from the report
	Make sure that it is included in the report, the vehicles which are in the status of available for rental in the master files
	Other vehicle status should be eliminated
	 */
	
	//@Transactional
	//@Override
	public List<Fvehicle> getVehicleList_(String locationId) {//commented on 2015.08.31
		try {
			String query="SELECT v.regno,";
			query += " (SELECT description FROM fvehiclemake WHERE vehimakeid=v.vehimakeid) AS [make],";
			query += " v.description,v.chassisno,v.engineno,";
			query += " (CONVERT(VARCHAR(10),v.mainseats)+'-'+CONVERT(VARCHAR(10),v.jumpseats)) AS seatingcapacity,";
			query += " v.dpurchase,";
			query += " (SELECT description FROM fvehiclestatus WHERE vehistsid=v.vehistsid) AS status,";
			query += " (SELECT description FROM flocation WHERE locationid=v.locationid) AS locationid";
			query += " FROM fvehicle AS v WHERE V.vehistsid<>'SOLD' AND v.locationid='"+locationId+"' ";
			query += " AND v.regno NOT IN (SELECT REGNO FROM fresvehicle JOIN freservation ON fresvehicle.RESNO=freservation.RESNO WHERE (freservation.cohirestsid<>'CHECKOUT' AND FRESVEHICLE.PRIORITY=1 AND  freservation.DOUT<=GETDATE() AND freservation.DIN>=GETDATE()) or FRESVEHICLE.PRIORITY<>1) ";
			query +=" ORDER BY v.regno";
			
			Query Q = em.createNativeQuery(query);
			List<Object[]> l1 = Q.getResultList();
			List<Fvehicle> list = new ArrayCollection();
			
			String regno;
			String make;
			String description;
			String chassisno;
			String engineno;
			String seatingcapacity;
			Timestamp tsdpurchase;
			String status;
			
			for(Object[] r : l1){
				regno=(String)r[0];
				make=(String)r[1];
				description=(String)r[2];
				chassisno=(String)r[3];
				engineno=(String)r[4];
				seatingcapacity=(String)r[5];
				tsdpurchase=(Timestamp)r[6];
				status=(String)r[7];
				
				Fvehicle obj=new Fvehicle();
				obj.setRegno(regno);
				obj.setMake(make);
				obj.setDescription(description);
				obj.setChassisno(chassisno);
				obj.setEngineno(engineno);
				obj.setSeatingcapacity(seatingcapacity);
				obj.setTsdpurchase(tsdpurchase);
				obj.setStatus(status);
				obj.setLocationid((String)r[8]);
				
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			System.out.println("FvehicleDaoImpl: getVehicleList: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	@Override
	public List<Fvehicle> getVehicleList(String locationId/*,String vstatus,String dummyvehi*/) {//'VINTAGE','CLASSIC'
		try {
			String query="SELECT v.regno,";
			query += " (SELECT description FROM fvehiclemake WHERE vehimakeid=v.vehimakeid) AS [make],";
			query += " v.description,v.chassisno,v.engineno,";
			query += " (CONVERT(VARCHAR(10),v.mainseats)+'-'+CONVERT(VARCHAR(10),v.jumpseats)) AS seatingcapacity,";
			query += " v.dpurchase,";
			query += " (SELECT description FROM fvehiclestatus WHERE vehistsid=v.vehistsid) AS status,";
			query += " (SELECT description FROM flocation WHERE locationid=v.locationid) AS locationid,";
			
			query += " (SELECT m.description from fvehiclemodel as m WHERE m.vehimakeid=v.vehimakeid AND m.vehimodelid=v.vehimodelid) AS [model],";
			query += " v.year AS [year],v.curmileage AS [curmileage],v.engsizeid AS [engsizeid]";
			
			query += " FROM fvehicle AS v WHERE V.vehistsid<>'SOLD' AND v.locationid='"+locationId+"' AND v.vehistsid='AVAILABLE' AND DUMMYVEHI<>1 AND v.vehiclassid not in ('VINTAGE','CLASSIC')";
			query += " AND v.regno NOT IN (SELECT REGNO FROM fresvehicle JOIN freservation ON fresvehicle.RESNO=freservation.RESNO WHERE (freservation.cohirestsid<>'CHECKOUT' AND FRESVEHICLE.PRIORITY=1 AND  freservation.DOUT<=GETDATE() AND freservation.DIN>=GETDATE()) or FRESVEHICLE.PRIORITY<>1) ";
			query +=" ORDER BY v.regno";
			
			Query Q = em.createNativeQuery(query);
			List<Object[]> l1 = Q.getResultList();
			List<Fvehicle> list = new ArrayCollection();
			System.out.println("query :\n"+query);
			String regno;
			String make;
			String description;
			String chassisno;
			String engineno;
			String seatingcapacity;
			Timestamp tsdpurchase;
			String status;
			String model;
			Integer year=new Integer(0);
			Integer curmileage=new Integer(0);
			String engsizeid;			
			for(Object[] r : l1){
				regno=(String)r[0];
				make=(String)r[1];
				description=(String)r[2];
				chassisno=(String)r[3];
				engineno=(String)r[4];
				seatingcapacity=(String)r[5];
				tsdpurchase=(Timestamp)r[6];
				status=(String)r[7];
				model=(String)r[9];
					if(r[10]!=null)
					year=(Integer)r[10];
					if(r[11]!=null)
					curmileage=(Integer)r[11];
					engsizeid=(String)r[12];
					
				Fvehicle obj=new Fvehicle();
				obj.setRegno(regno);
				obj.setMake(make);
				obj.setDescription(description);
				obj.setChassisno(chassisno);
				obj.setEngineno(engineno);
				obj.setSeatingcapacity(seatingcapacity);
				obj.setTsdpurchase(tsdpurchase);
				obj.setStatus(status);
				obj.setLocationid((String)r[8]);
				
				obj.setVehimodelid(model);
				obj.setYear(year);
				obj.setCurmileage(curmileage);
				obj.setEngsizeid(engsizeid);
				
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			System.out.println("FvehicleDaoImpl: getVehicleList: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	//@Transactional
	//@Override
/*	public List<Fvehicle> getVehicleListCustomized(String vstatus,String ownertype) {
		try {
			//'SOLD'
			//select * from fvehicle where vehistsid='AVAILABLE' 'SOLD' 'ATREPAIR'  
			//select * from fvehicle where ownertype='Company' 'Outside' 
			String subQuery="";
			if(vstatus!=null)
			{
				subQuery+=" V.vehistsid='"+vstatus+"' AND";//AVAILABLE,SOLD,ATREPAIR,...etc
			}
			if(vstatus!=null)
			{
				subQuery+=" V.ownertype='"+ownertype+"' AND";//Company,Outside,...etc
			}
			String query="SELECT v.regno,";
			query += " (SELECT description FROM fvehiclemake WHERE vehimakeid=v.vehimakeid) AS [make],";
			query += " v.description,v.chassisno,v.engineno,";
			query += " (CONVERT(VARCHAR(10),v.mainseats)+'-'+CONVERT(VARCHAR(10),v.jumpseats)) AS seatingcapacity,";
			query += " v.dpurchase,";
			query += " (SELECT description FROM fvehiclestatus WHERE vehistsid=v.vehistsid) AS status,";
			query += " (SELECT description FROM flocation WHERE locationid=v.locationid) AS locationid";
			query += " FROM fvehicle AS v WHERE "+subQuery+"  DUMMYVEHI<>1";
			query += " AND v.regno NOT IN (SELECT REGNO FROM fresvehicle JOIN freservation ON fresvehicle.RESNO=freservation.RESNO WHERE (freservation.cohirestsid<>'CHECKOUT' AND FRESVEHICLE.PRIORITY=1 AND  freservation.DOUT<=GETDATE() AND freservation.DIN>=GETDATE()) or FRESVEHICLE.PRIORITY<>1) ";
			query +=" ORDER BY v.regno";
			
			Query Q = em.createNativeQuery(query);
			List<Object[]> l1 = Q.getResultList();
			List<Fvehicle> list = new ArrayCollection();
			System.out.println("query :\n"+query);
			String regno;
			String make;
			String description;
			String chassisno;
			String engineno;
			String seatingcapacity;
			Timestamp tsdpurchase;
			String status;
			
			for(Object[] r : l1){
				regno=(String)r[0];
				make=(String)r[1];
				description=(String)r[2];
				chassisno=(String)r[3];
				engineno=(String)r[4];
				seatingcapacity=(String)r[5];
				tsdpurchase=(Timestamp)r[6];
				status=(String)r[7];
				
				Fvehicle obj=new Fvehicle();
				obj.setRegno(regno);
				obj.setMake(make);
				obj.setDescription(description);
				obj.setChassisno(chassisno);
				obj.setEngineno(engineno);
				obj.setSeatingcapacity(seatingcapacity);
				obj.setTsdpurchase(tsdpurchase);
				obj.setStatus(status);
				obj.setLocationid((String)r[8]);
				
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			System.out.println("FvehicleDaoImpl: getVehicleList: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	*/
	
	@Transactional
	@Override
	public int isVehicleRemovable(String sRegNo) {
		// Validate if the vehicle record can be deleted
		Query sql = em.createNativeQuery("SELECT regno FROM Fmaintenance WHERE regno='"+sRegNo+"'");		// fMaintenace
		List<Fmaintenance> fMaintenance = sql.getResultList();
		return fMaintenance.size();
	}

	@Override
	public List<Fvehicle> getVehicleListCustomized(String vstatus,
			String ownertype) {
		// TODO Auto-generated method stub
		return null;
	}


	@Transactional
	@Override
	public List<Fvehicle> getVehicleSummary() {
		try {
			

			
			
			String query="SELECT v.regno,";
			query += " (SELECT description FROM fvehiclemake WHERE vehimakeid=v.vehimakeid) AS [make],";
			query += " v.description,v.chassisno,v.engineno,";
			query += " (CONVERT(VARCHAR(10),v.mainseats)+'-'+CONVERT(VARCHAR(10),v.jumpseats)) AS seatingcapacity,";
			query += " v.dpurchase,";
			query += " (SELECT description FROM fvehiclestatus WHERE vehistsid=v.vehistsid) AS status,";
			query += " (SELECT m.description from fvehiclemodel as m WHERE m.vehimakeid=v.vehimakeid AND m.vehimodelid=v.vehimodelid) AS [model],";
			query += " v.year AS [year],v.curmileage AS [curmileage],v.engsizeid AS [engsizeid]";
			query += " FROM fvehicle AS v WHERE v.dummyvehi<>1 ORDER BY v.regno";
			
			//WHERE v.dummyvehi<>1 added on 2015.09.14
			Query Q = em.createNativeQuery(query);
			List<Object[]> l1 = Q.getResultList();
			List<Fvehicle> list = new ArrayCollection();
			String regno;
			String make;
			String description;
			String chassisno;
			String engineno;
			String seatingcapacity;
			Timestamp tsdpurchase;
			String status;
			String model;
			Integer year=new Integer(0);
			Integer curmileage=new Integer(0);
			String engsizeid;
			
			
			for(Object[] r : l1){
				regno=(String)r[0];
				make=(String)r[1];
				description=(String)r[2];
				chassisno=(String)r[3];
				engineno=(String)r[4];
				seatingcapacity=(String)r[5];
				tsdpurchase=(Timestamp)r[6];
				status=(String)r[7];
				model=(String)r[8];
				
				if(r[9]!=null)
				year=(Integer)r[9];
				if(r[10]!=null)
				curmileage=(Integer)r[10];
				engsizeid=(String)r[11];
				
				Fvehicle obj=new Fvehicle();
				obj.setRegno(regno);
				obj.setMake(make);
				obj.setDescription(description);
				obj.setChassisno(chassisno);
				obj.setEngineno(engineno);
				obj.setSeatingcapacity(seatingcapacity);
				obj.setTsdpurchase(tsdpurchase);
				obj.setStatus(status);
				
				obj.setVehimodelid(model);
				obj.setYear(year);
				obj.setCurmileage(curmileage);
				obj.setEngsizeid(engsizeid);
				
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			System.out.println("FvehicleDaoImpl: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
}
