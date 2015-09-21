package com.dspl.malkey.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Femployee;
import com.dspl.malkey.domain.Fmaintenance;
import com.dspl.malkey.domain.Freservationdiaryrpt;
import com.dspl.malkey.domain.Fsmssent;
import com.dspl.malkey.service.UserInfoSRV;

import flex.messaging.io.ArrayCollection;
import flex.messaging.io.amf.ASObject;

import java.util.Calendar;

public class FmaintenanceDAOImpl implements FmaintenanceDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fcompanysettingDAO")
	FcompanysettingDAO fcompanysettingDAO;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Transactional
	@Override
	public List<Fmaintenance> List(int startIndex, int numItems) {
		return em.createNamedQuery("FmaintenanceListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fmaintenance> ListAll() {
		return em.createNamedQuery("FmaintenanceListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fmaintenance").getSingleResult());
	}
	
	public Calendar resetTime(Calendar tmpCal)
	{
		tmpCal.set(Calendar.HOUR_OF_DAY, 0);
		tmpCal.set(Calendar.MINUTE, 0);
		tmpCal.set(Calendar.SECOND, 0);
		tmpCal.set(Calendar.MILLISECOND, 0);
		return tmpCal;
	}
	
	public Calendar copyCalendar(Calendar tmp) 
	{
		Calendar newCalendar = Calendar.getInstance();
		newCalendar.set(Calendar.HOUR_OF_DAY, tmp.get(Calendar.HOUR_OF_DAY));
		newCalendar.set(Calendar.YEAR, tmp.get(Calendar.YEAR));
		newCalendar.set(Calendar.MONTH, tmp.get(Calendar.MONTH));
		newCalendar.set(Calendar.DAY_OF_MONTH, tmp.get(Calendar.DAY_OF_MONTH));
		newCalendar.set(Calendar.MINUTE, tmp.get(Calendar.MINUTE));
		newCalendar.set(Calendar.SECOND,0);
		newCalendar.set(Calendar.MILLISECOND,0); 
		return newCalendar;
	}

	@Transactional(readOnly=false)
	@Override
	public String create(Fmaintenance fmaintenance) 
	{
		try
		{
			Calendar curDate = Calendar.getInstance();
			curDate = resetTime(curDate);
			String RefNo = fcompanysettingDAO.getRefNo("MTN", curDate.get(Calendar.YEAR), ((curDate.get(Calendar.MONTH))+1));
			fmaintenance.setRefno(RefNo);
			if(fmaintenance.getPeriod()>0 && fmaintenance.getSysgen()==false)
			{
				Calendar tmpCal = copyCalendar(fmaintenance.getDstart());
				tmpCal = resetTime(tmpCal);
//				System.out.println("start date: " + tmpCal.toString() + "   current date: " + curDate.toString());
				if(tmpCal.compareTo(curDate)<0)
				{
					tmpCal.add(Calendar.DAY_OF_MONTH, fmaintenance.getPeriod());
				}
//				System.out.println("due date: " + tmpCal.toString());
				tmpCal = resetTime(tmpCal);
				fmaintenance.setDdue(tmpCal);
			}
			fmaintenance.setTxndate(curDate);
			fmaintenance.setAdddate(curDate);
			fmaintenance.setAdduser(userInfoSRV.getUser());
			fmaintenance.setAddmach(userInfoSRV.getMachineName());
			em.persist(fmaintenance);
			em.flush();
			String frequency = fmaintenance.getFrequency().toString().trim();
			if(frequency.compareTo("One-off")==0)
			{
//				String query = "SELECT mobilephone1 FROM Femployee WHERE empid='" + fmaintenance.getAssignedto() + "'";
//				System.out.println("query: " + query);
//				List<Object[]> olist = em.createNativeQuery(query).getResultList();
//
//				Fsmssent fsmssent = new Fsmssent();
//				for(Object o : olist)
//				{
//					fsmssent.setTomobile(o.toString().trim());
//				}			
//				msg= "New Task for Vehicle " + fmaintenance.getRegno() + newline;
//				msg+= fmaintenance.getComment().trim() + newline;
//				msg+= " Priority: " + fmaintenance.getPriority().trim() + newline;
//				msg+= "Due Date: " + fmaintenance.getDdue().get(Calendar.DATE) + "." + (fmaintenance.getDdue().get(Calendar.MONTH) + 1) + "." + fmaintenance.getDdue().get(Calendar.YEAR) + newline;
//				msg += " Ref. No: " + fmaintenance.getRefno().trim();
//				
//				//fsmssent.setMessage("New Task Has Been Assigned. Reference No: " + RefNo);
//				fsmssent.setMessage(msg);
//				fsmssent.setDsynched(curDate);
//				fsmssent.setSchtosend(1);
//				fsmssent.setSentstatus("");
//				fsmssent.setRemindertype("pending");
//				fsmssent.setAdduser(userInfoSRV.getUser());
//				fsmssent.setAddmach(userInfoSRV.getMachineName());
//				fsmssent.setSchdate(fmaintenance.getNotifyon());
//				em.persist(fsmssent);
				setSMS(fmaintenance, curDate, false);
			}
			return RefNo;
		}
		catch(Exception e)
		{
			System.out.println("create Fmaintenance Error: " + e.getMessage());
			return e.getMessage();
		}
	}
	
	@Transactional
	private void delSMS(String refno){
		try {
			String query="DELETE FROM fsmssent WHERE [message] LIKE '%"+refno+"%'";
			em.createNativeQuery(query).executeUpdate();
			em.flush();
		} catch (Exception e) {
			System.out.println("delSMS: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Transactional
	private void setSMS(Fmaintenance fmaintenance, Calendar curDate, Boolean update){
		try{
			String query;
			if(update==true){
				delSMS(fmaintenance.getRefno());
			}
			query = "SELECT mobilephone1 FROM Femployee WHERE empid='" + fmaintenance.getAssignedto() + "'";
			System.out.println("query: " + query);
			List<Object[]> olist = em.createNativeQuery(query).getResultList();
	
			Fsmssent fsmssent = new Fsmssent();
			for(Object o : olist)
			{
				fsmssent.setTomobile(o.toString().trim());
			}
			String msg;
			String newline = System.getProperty("line.separator");
			msg= "New Task for Vehicle " + fmaintenance.getRegno() + newline;
			msg+= fmaintenance.getComment().trim() + newline;
			msg+= " Priority: " + fmaintenance.getPriority().trim() + newline;
			msg+= "Due Date: " + fmaintenance.getDdue().get(Calendar.DATE) + "." + (fmaintenance.getDdue().get(Calendar.MONTH) + 1) + "." + fmaintenance.getDdue().get(Calendar.YEAR) + newline;
			msg += " Ref. No: " + fmaintenance.getRefno().trim();
			
			//fsmssent.setMessage("New Task Has Been Assigned. Reference No: " + RefNo);
			fsmssent.setMessage(msg);
			fsmssent.setDsynched(curDate);
			fsmssent.setSchtosend(1);
			fsmssent.setSentstatus("");
			fsmssent.setRemindertype("pending");
			fsmssent.setAdduser(userInfoSRV.getUser());
			fsmssent.setAddmach(userInfoSRV.getMachineName());
			fsmssent.setSchdate(fmaintenance.getNotifyon());
			em.persist(fsmssent);
			em.flush();
		}catch(Exception ex){
			System.out.println("setSMS: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Transactional
	@Override
	public Fmaintenance findByID(String refno) {
		return em.find(Fmaintenance.class, refno);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String refno) {
		em.remove(em.find(Fmaintenance.class, refno));
	}

	@Transactional(readOnly=false)
	@Override
	public Boolean update(Fmaintenance fmaintenance) {
		try
		{
			if(fmaintenance.getPeriod()>0)
			{
				Calendar tmpCal = copyCalendar(fmaintenance.getDstart());
				if(tmpCal.compareTo(fmaintenance.getTxndate())<0)
				{
					tmpCal.add(Calendar.DAY_OF_MONTH, fmaintenance.getPeriod());
				}
				tmpCal = resetTime(tmpCal);
				fmaintenance.setDdue(tmpCal);
			}
			Calendar curDate = Calendar.getInstance();
			curDate = resetTime(curDate);
			fmaintenance.setAdddate(curDate);
			fmaintenance.setAdduser(userInfoSRV.getUser());
			fmaintenance.setAddmach(userInfoSRV.getMachineName());
			em.merge(fmaintenance);
			em.flush();
			
			if(fmaintenance.getFrequency().compareTo("One-off")==0 && fmaintenance.getStatusid().equals("DISCARD")==false)
				setSMS(fmaintenance, curDate, true);
			else if(fmaintenance.getFrequency().compareTo("Recurring")==0)
				delSMS(fmaintenance.getRefno());
			
			return true;
		}
		catch(Exception e)
		{
			System.out.println("create Fmaintenance Error: " + e.getMessage());
		}
		return null;
	}

	@Transactional(readOnly=false)
	@Override
	public ArrayList getExtMaintTypes(String regno,String refno) 
	{
		try 
		{
			Query query;
			if(refno=="")
				query = em.createNativeQuery("SELECT typeid FROM Fmaintenance WHERE frequency='Recurring' AND statusid='PENDING' AND regno='" + regno + "'");
			else
				query = em.createNativeQuery("SELECT typeid FROM Fmaintenance WHERE frequency='Recurring' AND statusid='PENDING' AND regno='" + regno + "' AND refno<>'" + refno + "'");
			List<Object[]> olist = query.getResultList();
			ArrayList alist = new ArrayList();
			for(Object o : olist)
			{
				ASObject aso = new ASObject();
				aso.put("typeid",o.toString().trim());
				alist.add(aso);
			}
			return alist;
		} 
		catch (Exception e) 
		{
			System.out.println("getExtMaintTypes [Error]: " + e.getMessage());
		}
		return null;
	}

	@Transactional(readOnly=false)
	@Override
	public List<Fmaintenance> listMaintenance(ArrayList paraList) 
	{
		try 
		{
			String query = "SELECT m.refno,m.regno,m.frequency,t.description [typeid],m.ddue,m.assignedto [assignedtoid],e.name [assignedto],m.priority,m.comment,s.description [statusid],m.actiontaken FROM fmaintenance AS m ";
			query += "LEFT OUTER JOIN fmainttype AS t ";
			query += "ON m.typeid=t.typeid ";
			query += "LEFT OUTER JOIN femployee AS e ";
			query += "ON m.assignedto=e.empid ";
			query += "LEFT OUTER JOIN fmaintstatus AS s ";
			query += "ON m.statusid=s.statusid ";
//			--WHERE 
//			--m.frequency='Recurring' AND
//			--m.statusid='PENDING' AND
//			--m.ddue='2011-05-15'
			if(paraList.size()>0)
			{
				query += " WHERE ";
				for(int a=0;a<paraList.size();a++)
		        {
		        	ASObject aso = (ASObject)paraList.get(a);
		        	Object[] ar = aso.keySet().toArray();
		        	if(aso.get(ar[0].toString()).toString()!="")
		        	{
		        		query += " m." + ar[0].toString() + "='" + aso.get(ar[0].toString()).toString() + "'";
		        	}
		        	if(a<(paraList.size()-1))
		        	{
		        		query += " AND ";
		        	}
		        }
			}
			
			query += " ORDER BY m.refno,m.regno,m.frequency ";
			
			Query qr = em.createNativeQuery(query,"mList");
		    return qr.getResultList();
		} 
		catch (Exception e) 
		{
			System.out.println("listMaintenance [Error]: " + e.getMessage());
		}
		return null;
	}

	@Transactional(readOnly=false)
	@Override
	public Boolean updateStatus(Fmaintenance fmaintenance){
		try{
			fmaintenance.setAdduser(userInfoSRV.getUser());
			fmaintenance.setAddmach(userInfoSRV.getMachineName());
			em.merge(fmaintenance);
			em.flush();
			
			if(fmaintenance.getStatusid().trim().equals("COMPLETED")==true && fmaintenance.getFrequency().trim().equals("Recurring")){
				fmaintenance.setDstart(fmaintenance.getCompdate());
				Calendar tmpCal=copyCalendar(fmaintenance.getDstart());
				tmpCal.add(Calendar.DAY_OF_MONTH, fmaintenance.getPeriod());
				fmaintenance.setDdue(tmpCal);
				fmaintenance.setCompdate(null);
				fmaintenance.setActionedby("");
				fmaintenance.setActiontaken("");
				fmaintenance.setStatusid("PENDING");
				fmaintenance.setSysgen(true);
				create(fmaintenance);
			}
			
			if(fmaintenance.getStatusid().trim().equals("DISCARD")==true)
				delSMS(fmaintenance.getRefno());
			return true;
		}catch (Exception e){
			System.out.println("updateStatus [Error]: " + e.getMessage());
		}
		return false;
	}

	@Transactional
	@Override
	public List<Fmaintenance> getMaintenanceReminder(String dateFrom,String dateTo, ArrayList paraList) {
		try {
			String query="SELECT * FROM";
			query += " (SELECT";
			query += " m.refno,m.regno,";
			query += " (SELECT description FROM fvehiclemake WHERE vehimakeid=(SELECT vehimakeid FROM fvehicle WHERE regno=m.regno)) AS make,";
			query += " (SELECT description FROM fvehicletrans WHERE vehitransid=(SELECT vehitransid FROM fvehicle WHERE regno=m.regno)) AS transmission,";
			query += " (SELECT description FROM ffueltype WHERE fueltypeid=(SELECT fueltypeid FROM fvehicle WHERE regno=m.regno)) AS fueltype,";
			query += " m.frequency,";
			query += " (SELECT description FROM fmainttype WHERE typeid=m.typeid) AS typeid,";
			query += " m.mileage,m.period,";
			query += " CASE RTRIM(m.frequency)";
			query += " WHEN 'Recurring' THEN ";
			query += " (SELECT TOP 1 compdate FROM fmaintenance WHERE regno=m.regno AND typeid=m.typeid AND refno<m.refno AND statusid='COMPLETED' ORDER BY refno DESC)";
			query += " END AS lastdonedate,";
			query += " m.dstart AS startdate,";
			query += " m.ddue AS duedate,m.compdate AS completeddate,";
			query += " (SELECT name FROM femployee WHERE empid=m.actionedby) AS actionedby,";
			query += " m.comment,";
			query += " (SELECT description FROM fmaintstatus WHERE statusid=m.statusid) AS statusid";
			query += " FROM fmaintenance AS m";
			if(paraList.size()>0){
				query += " WHERE ";
				for(int a=0;a<paraList.size();a++){
					ASObject aso = (ASObject)paraList.get(a);
		        	Object[] ar = aso.keySet().toArray();
		        	if(aso.get(ar[0].toString()).toString()!="")
		        		query += " m." + ar[0].toString() + "='" + aso.get(ar[0].toString()).toString() + "'";
		        	if(a<(paraList.size()-1))
		        		query += " AND ";
				}
			}
			query += " ) AS m";
			query += " WHERE duedate>='"+dateFrom+"' AND duedate<='"+dateTo+"'";
			query += " ORDER BY m.regno,m.frequency,m.typeid,m.duedate,m.statusid,m.completeddate";
			
			System.out.println("query: " + query);
			
			Query Q = em.createNativeQuery(query);
			List<Object[]> l = Q.getResultList();
			List<Fmaintenance> list=new ArrayCollection();
			
			System.out.println("l list length: " + l.size());
			
			String refno;
			String regno;
			String make;
			String transmission;
			String fueltype;
			String frequency;
			String typeid;
			int mileage;
			int period;
			Timestamp lastdonedate;
			Timestamp startdate;
			Timestamp duedate;
			Timestamp completeddate;
			String actionedby;
			String comment;
			String statusid;
			
			for(Object[] r : l){
				refno=(String)r[0];
				regno=(String)r[1];
				make=(String)r[2];
				transmission=(String)r[3];
				fueltype=(String)r[4];
				frequency=(String)r[5];
				typeid=(String)r[6];
				mileage=Integer.parseInt(r[7].toString());
				period=Integer.parseInt(r[8].toString());
				lastdonedate=(Timestamp)r[9];
				startdate=(Timestamp)r[10];
				duedate=(Timestamp)r[11];
				completeddate=(Timestamp)r[12];
				actionedby=(String)r[13];
				comment=(String)r[14];
				statusid=(String)r[15];
			
				Fmaintenance obj=new Fmaintenance();
				obj.setRefno(refno);
				obj.setRegno(regno);
				obj.setMake(make);
				obj.setTransmission(transmission);
				obj.setFueltype(fueltype);
				obj.setFrequency(frequency);
				obj.setTypeid(typeid);
				obj.setMileage(mileage);
				obj.setPeriod(period);
				obj.setLastdonedate(lastdonedate);
				obj.setStartdate(startdate);
				obj.setDuedate(duedate);
				obj.setCompleteddate(completeddate);
				obj.setActionedby(actionedby);
				obj.setComment(comment);
				obj.setStatusid(statusid);
				list.add(obj);
			}
			System.out.println("list added");
			return list;
		} catch (Exception e) {
			System.out.println("FmaintenanceDaoImpl getMaintenanceReminder: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
