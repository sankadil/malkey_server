package com.dspl.malkey.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fmaintenance;
import com.dspl.malkey.domain.Fvehicle;
import com.dspl.malkey.view.HireDetailsView;

import flex.messaging.io.ArrayCollection;
import flex.messaging.io.amf.ASObject;

public class VehiclesearchDAOImpl implements VehiclesearchDAO {

	@PersistenceContext
	EntityManager em;
	
//	@Transactional(readOnly=true)
//	@Override
//	public List<Fvehicle> searchVehicles(String dateFrom, String dateTo,ArrayList paraList) 
//	{
//		try
//		{
//			String query = "SELECT v.regno,c.description [vehiclassid],y.description [vehitypeid],k.description [vehimakeid],m.description [vehimodelid],t.description [vehitransid],f.description [fueltypeid],o.description [colourid],";
//			query += "(SELECT COUNT(p.regno) FROM fvehiclepic AS p WHERE p.regno=v.regno) AS [imagecnt] ";
//	        query += "FROM fvehicle AS v,fvehicleclass AS c,fvehicletype AS y,fvehiclemake AS k,fvehiclemodel AS m,fvehicletrans AS t,ffueltype AS f,fcolour AS o ";
//	        query += "WHERE v.vehiclassid=c.vehiclassid AND v.vehitypeid=y.vehitypeid AND v.vehimakeid=k.vehimakeid AND v.vehimodelid=m.vehimodelid AND v.vehitransid=t.vehitransid AND v.fueltypeid=f.fueltypeid AND v.colourid=o.colourid AND v.vehistsid='AVAILABLE' ";
//	        query += "AND v.regno NOT IN (SELECT DISTINCT regno FROM fresvehicle ";
//	        query += "WHERE priority=1 AND resno IN (SELECT resno FROM freservation ";
//	        query += "WHERE resno IN (SELECT resno FROM freservation ";
//	        query += "WHERE '"+ dateFrom +"' BETWEEN dout AND din OR '"+ dateTo +"' BETWEEN dout AND din AND dout BETWEEN '"+ dateFrom +"' AND '"+ dateTo +"' OR din BETWEEN '"+ dateFrom +"' AND '"+ dateTo +"') ";
//	        query += "AND cohirestsid IN ('BOOKED','CHECKIN','CHECKOUT','CONFIRMED','PREPARED')) ";
//	        query += ") ";
//	        
//	        for(int a=0;a<paraList.size();a++)
//	        {
//	        	ASObject aso = (ASObject)paraList.get(a);
//	        	Object[] ar = aso.keySet().toArray();
//	        	if(aso.get(ar[0].toString()).toString()!="")
//	        	{
//	        		query += " AND v." + ar[0].toString() + "='" + aso.get(ar[0].toString()).toString() + "'";
//	        	}
//	        }
//	        
//	        Query qr = em.createNativeQuery(query,"vList");
//	        return qr.getResultList();
//		}
//		catch(Exception ex)
//		{
//			System.out.println("searchVehicles [Error]: " + ex.getMessage() + " : " + ex.getLocalizedMessage());
//			return null;
//		}
//	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Fvehicle> searchVehicles(String dateFrom, String dateTo,ArrayList paraList,Boolean available) 
	{
		try
		{
			em.flush();
			String avbVal="";
			if(available==true)
			{
				avbVal="NOT";
			}
			
			System.out.println("dateFrom:"+dateFrom);
			System.out.println("dateTo:"+dateTo);
			//2014.1.25 format convert to 1-25-2014
			SimpleDateFormat dtformat = new SimpleDateFormat("yyyy.MM.dd");
			java.util.Date dtToDate = dtformat.parse(dateTo);
			//System.out.println("Date Fromat Date ="+dtToDate+"=");
			dtformat = new SimpleDateFormat("MM-dd-yyyy");
			String strdate  = dtformat.format(dtToDate);
			//System.out.println("Date Fromat Current ="+strdate+"=");
			dateTo=strdate;
			
			String query = "SELECT v.regno,c.description [vehiclassid],y.description [vehitypeid],k.description [vehimakeid],m.description [vehimodelid],v.vehimodelid [inscompany],t.description [vehitransid],f.description [fueltypeid],o.description [colourid],o.colcode,";
			query += "(SELECT COUNT(p.regno) FROM fvehiclepic AS p WHERE p.regno=v.regno) AS [imagecnt], ";
			query += "v.ownertype ";
	        query += "FROM fvehicle AS v,fvehicleclass AS c,fvehicletype AS y,fvehiclemake AS k,fvehiclemodel AS m,fvehicletrans AS t,ffueltype AS f,fcolour AS o ";
	        query += "WHERE v.vehiclassid=c.vehiclassid AND v.vehitypeid=y.vehitypeid AND v.vehimakeid=k.vehimakeid AND v.vehimodelid=m.vehimodelid AND v.vehitransid=t.vehitransid AND v.fueltypeid=f.fueltypeid AND v.colourid=o.colourid AND v.vehistsid='AVAILABLE' ";
	        query += "AND m.vehimakeid=k.vehimakeid ";
	        query += "AND v.regno " + avbVal + " IN (SELECT DISTINCT regno FROM fresvehicle ";
	        query += "WHERE priority=1 AND resno IN (SELECT resno FROM freservation ";
	        query += "WHERE resno IN (SELECT resno FROM freservation ";
	        query += "WHERE '"+ dateFrom +"' BETWEEN dout AND din OR '"+ dateTo +"' BETWEEN dout AND din AND dout BETWEEN '"+ dateFrom +"' AND '"+ dateTo +"' OR din BETWEEN '"+ dateFrom +"' AND '"+ dateTo +"') ";
	        query += "AND cohirestsid IN ('BOOKED','CHECKIN','CHECKOUT','CONFIRMED','PREPARED')) ";
	        query += ") ";
	        
	        for(int a=0;a<paraList.size();a++)
	        {
	        	ASObject aso = (ASObject)paraList.get(a);
	        	Object[] ar = aso.keySet().toArray();
	        	if(aso.get(ar[0].toString()).toString()!="")
	        	{
	        		query += " AND v." + ar[0].toString() + "='" + aso.get(ar[0].toString()).toString() + "'";
	        	}
	        }
	        
	        //System.out.println("vehicleSearchQuery: " + query);
	        
	        Query qr = em.createNativeQuery(query,"vList1");
	       return qr.getResultList();
		}
		catch(Exception ex)
		{
			System.out.println("searchVehicles [Error]: " + ex.getMessage() + " : " + ex.getLocalizedMessage());
			throw new RuntimeException("searchVehicles exception");
			//return null;
		}
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Fvehicle> getVehicleDet() 
	{
		try 
		{
			em.flush();
			String query = " SELECT * FROM (";
			query += " SELECT v.regno,t.description [vehitypeid],m.description [vehimakeid],d.description [vehimodelid],r.description [vehitransid],u.description [fueltypeid],p.imageurl AS [image],v.curmileage ";
			query += " FROM fvehicle AS v LEFT OUTER JOIN ";
			query += " fvehicletype AS t ON v.vehitypeid=t.vehitypeid"; 
			query += " LEFT OUTER JOIN fvehiclemake AS m ON v.vehimakeid=m.vehimakeid";
			query += " LEFT OUTER JOIN fvehiclemodel AS d ON v.vehimodelid=d.vehimodelid AND d.vehimakeid=m.vehimakeid";
			query += " LEFT OUTER JOIN fvehicletrans AS r ON v.vehitransid=r.vehitransid";
			query += " LEFT OUTER JOIN ffueltype AS u ON v.fueltypeid=u.fueltypeid";
			query += " LEFT OUTER JOIN fvehiclepic AS p ON v.regno=p.regno AND p.defaultimage=1";
			query += " ) AS v"; 
			//query += " WHERE v.vehitypeid<>'N/A' AND v.vehimakeid<>'N/A' AND v.vehimodelid<>'N/A' AND v.vehitransid<>'N/A' AND v.fueltypeid<>'N/A'";
			
			System.out.println("getVehicleDet Query: " + query);
	        
	        Query qr = em.createNativeQuery(query,"vList1");
/*	        List<Fvehicle> list= qr.getResultList();
			for (Iterator<Fvehicle> iterator = list.iterator(); iterator.hasNext();) {
				Fvehicle fvehicle = (Fvehicle) iterator.next();
				System.out.println("fvehicle : "+fvehicle.getRegno());
			}
		    return list;*/
	        return qr.getResultList();
		}
		catch (Exception e) 
		{
			System.out.println("getVehicleDet: " + e.getMessage());
			//return null;
			throw new RuntimeException("getVehicleDet exception");
		}
	}

	@Transactional
	@Override
	public ArrayCollection getResList(String dateFrom, String dateTo,ArrayList paraList){
		try{
			em.flush();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
//			Timestamp dfrom = new Timestamp(sdf.parse(dateFrom).getTime());
			java.util.Date dfrom = sdf.parse(dateFrom);
			int HR_PER_DAY = 1000 * 60 * 60;
			
			String query = "SELECT"; 
			query += " v.regno,r.agrno,r.resno,r.dout,";
			query += " r.din,r.hiretypeid,t.description AS [typedes],";
			query += " r.cohirestsid,s.description AS [hiredes]";
			query += " ,r.timeout,r.timein";
			query += " FROM freservation AS r";
			query += " ,fresvehicle AS v";
			query += " ,fhirestatus AS s";
			query += " ,fhiretype AS t"; 
			query += " ,fvehicle AS vh";
			query += " WHERE"; 
			query += " v.regno=vh.regno AND";
			query += " r.hiretypeid=t.hiretypeid AND";
			query += " r.cohirestsid=s.hirestsid AND";
			query += " v.priority=1 AND";
			query += " r.resno=v.resno AND";
			query += " r.resno IN(";
			query += " SELECT resno FROM freservation"; 
			query += " WHERE resno IN"; 
			query += " (SELECT resno FROM freservation"; 
			query += " WHERE '"+ dateFrom +"' BETWEEN dout AND din OR '"+ dateTo +"' BETWEEN dout AND din AND dout BETWEEN '"+ dateFrom +"' AND '"+ dateTo +"' OR din BETWEEN '"+ dateFrom +"' AND '"+ dateTo +"') "; 
			query += " AND cohirestsid IN ('BOOKED','CHECKIN','CHECKOUT','CONFIRMED','PREPARED'))";

	        for(int a=0;a<paraList.size();a++)
	        {
	        	ASObject aso = (ASObject)paraList.get(a);
	        	Object[] ar = aso.keySet().toArray();
	        	if(aso.get(ar[0].toString()).toString()!="")
	        	{
	        		query += " AND vh." + ar[0].toString() + "='" + aso.get(ar[0].toString()).toString() + "'";
	        	}
	        }
	        query += " ORDER BY v.regno,r.resno";
	        
	        Query Q = em.createNativeQuery(query);
			List<Object[]> l1 = Q.getResultList();
			ArrayCollection ac = new ArrayCollection();
			
			String regno;
			String agrno;
			String resno;
			Timestamp dout;
			Timestamp din;
			String hiretypeid;
			String typedes;
			String cohirestsid;
			String hiredes;
			String timeout;
			String timein;
			for(Object[] r1 : l1)
			{
				regno=(String)r1[0];
				agrno=(String)r1[1];
				resno=(String)r1[2];
				dout=(Timestamp)r1[3];
				din=(Timestamp)r1[4];
				hiretypeid=(String)r1[5];
				typedes=(String)r1[6];
				cohirestsid=(String)r1[7];
				hiredes=(String)r1[8];
				timeout=(String)r1[9];
				timein=(String)r1[10];
				
				
				Timestamp sdate; //Start Date
				int val = dout.compareTo(dfrom);
				if(val<0)
					sdate=new Timestamp(dfrom.getTime());
				else
					sdate=dout;
				
				long diff=Math.abs((din.getTime()-sdate.getTime())/HR_PER_DAY)+24;
				
				ASObject aso = new ASObject();
				aso.put("regno", regno);
				aso.put("agrno", agrno);
				aso.put("resno", resno);
				aso.put("dout", dout);
				aso.put("din", din);
				aso.put("hiretypeid", hiretypeid);
				aso.put("typedes", typedes);
				aso.put("cohirestsid", cohirestsid);
				aso.put("hiredes", hiredes);
				aso.put("sdate",sdate);
				aso.put("diff", diff);
				aso.put("timeout", timeout);
				aso.put("timein", timein);
				ac.add(aso);
			}
	        return ac;
		}catch(Exception ex){
			System.out.println("getResList: " + ex.getMessage());
			ex.printStackTrace();
			throw new RuntimeException("getResList exception");
		}
		//return null;
	}
	
	
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	@Override
	public int getAvailability(java.util.Calendar dateFrom, java.util.Calendar dateTo,String regno) 
	{
		
		/*
		SELECT COUNT(regno) FROM fresvehicle 
		WHERE priority=1 AND regno=?1 AND resno IN (SELECT resno FROM freservation 
		WHERE resno IN (SELECT resno FROM freservation 
		WHERE ?2 BETWEEN dout AND din OR ?3 BETWEEN dout AND 
		din AND dout BETWEEN ?2 AND ?3 OR din BETWEEN ?2 AND ?3) 
		AND cohirestsid IN ('BOOKED','CHECKIN','CHECKOUT','CONFIRMED','PREPARED'))
		*/
		em.flush();
		String query_string="SELECT COUNT(regno) FROM fresvehicle WHERE priority=1 AND regno=?1 AND resno IN (SELECT resno FROM freservation WHERE resno IN (SELECT resno FROM freservation WHERE ?2 BETWEEN dout AND din OR ?3 BETWEEN dout AND din AND dout BETWEEN ?2 AND ?3 OR din BETWEEN ?2 AND ?3) AND cohirestsid IN ('BOOKED','CHECKIN','CHECKOUT','CONFIRMED','PREPARED'))";
		Query query= em.createNativeQuery(query_string);
		query.setParameter(1, regno);
		query.setParameter(2, dateFrom);
		query.setParameter(3, dateTo);
		int result=(Integer)(query.getSingleResult());
		return result;
	}
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	@Override
	public int getAvailabilityByResNo(java.util.Calendar dateFrom, java.util.Calendar dateTo,String regno,String resno) 
	{
		em.flush();
		String query_string="SELECT COUNT(regno) FROM fresvehicle WHERE priority=1 AND regno=?1 AND resno<>?4 AND resno IN (SELECT resno FROM freservation WHERE resno IN (SELECT resno FROM freservation WHERE ?2 BETWEEN dout AND din OR ?3 BETWEEN dout AND din AND dout BETWEEN ?2 AND ?3 OR din BETWEEN ?2 AND ?3) AND cohirestsid IN ('BOOKED','CHECKIN','CHECKOUT','CONFIRMED','PREPARED'))";
		Query query= em.createNativeQuery(query_string);
		query.setParameter(1, regno);
		query.setParameter(2, dateFrom);
		query.setParameter(3, dateTo);
		query.setParameter(4, resno);
		int result=(Integer)(query.getSingleResult());
		return result;
	}
	
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	@Override
	public List<String> getUnAvailabileListByResNo(java.util.Calendar dateFrom, java.util.Calendar dateTo,String regno,String resno) 
	{
		em.flush();
		String query_string="SELECT resno FROM fresvehicle WHERE priority=1 AND regno=?1 AND resno<>?4 AND resno IN (SELECT resno FROM freservation WHERE resno IN (SELECT resno FROM freservation WHERE ?2 BETWEEN dout AND din OR ?3 BETWEEN dout AND din AND dout BETWEEN ?2 AND ?3 OR din BETWEEN ?2 AND ?3) AND cohirestsid IN ('BOOKED','CHECKIN','CHECKOUT','CONFIRMED','PREPARED'))";
		Query query= em.createNativeQuery(query_string);
		query.setParameter(1, regno);
		query.setParameter(2, dateFrom);
		query.setParameter(3, dateTo);
		query.setParameter(4, resno);
		List<String> lst=query.getResultList();
		return lst;
	}

	/***
	 * 
	 * 
	 * 
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @param hireDetailsView search paramaters hire,client and rate types
	 * @param available
	 * @return rates available & available vehicles
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Fvehicle> advancedSearchVehicles(String dateFrom, String dateTo,HireDetailsView hireDetailsView,Boolean available) 
	{
		try
		{
			em.flush();
			String avbVal="";
			if(available==true)
			{
				avbVal="NOT";
			}
			
			System.out.println("dateFrom:"+dateFrom);
			System.out.println("dateTo:"+dateTo);
			
			String query = "SELECT v.regno,c.description [vehiclassid],y.description [vehitypeid],k.description [vehimakeid],m.description [vehimodelid],t.description [vehitransid],f.description [fueltypeid],o.description [colourid],";
			query += "(SELECT COUNT(p.regno) FROM fvehiclepic AS p WHERE p.regno=v.regno) AS [imagecnt] ";
	        query += "FROM fvehicle AS v,fvehicleclass AS c,fvehicletype AS y,fvehiclemake AS k,fvehiclemodel AS m,fvehicletrans AS t,ffueltype AS f,fcolour AS o ";
	        query += "WHERE v.vehiclassid=c.vehiclassid AND v.vehitypeid=y.vehitypeid AND v.vehimakeid=k.vehimakeid AND v.vehimodelid=m.vehimodelid AND v.vehitransid=t.vehitransid AND v.fueltypeid=f.fueltypeid AND v.colourid=o.colourid AND v.vehistsid='AVAILABLE' ";
	        query += "AND m.vehimakeid=k.vehimakeid ";
	        query += "AND v.regno " + avbVal + " IN (SELECT DISTINCT regno FROM fresvehicle ";
	        query += "WHERE priority=1 AND resno IN (SELECT resno FROM freservation ";
	        query += "WHERE resno IN (SELECT resno FROM freservation ";
	        query += "WHERE '"+ dateFrom +"' BETWEEN dout AND din OR '"+ dateTo +"' BETWEEN dout AND din AND dout BETWEEN '"+ dateFrom +"' AND '"+ dateTo +"' OR din BETWEEN '"+ dateFrom +"' AND '"+ dateTo +"') ";
	        query += "AND cohirestsid IN ('BOOKED','CHECKIN','CHECKOUT','CONFIRMED','PREPARED')) ";
	        query += ") ";
	        if(hireDetailsView!=null)
	        {
		        String clientType = hireDetailsView.getClientType();
		        String hireType = hireDetailsView.getHireType();
		        String rateType = hireDetailsView.getRateType();
		        query +=" AND v.regno IN ( select regno from fvehicle where vehimodelid in (select vehimodelid from fvehiclerate where hiretypeid='"+hireType+"' and ratetype='"+rateType+"' and clienttype='"+clientType+"'))";
	        }
	        Query qr = em.createNativeQuery(query,"vList1");
	        return qr.getResultList();
		}
		catch(Exception ex)
		{
			System.out.println("advancedSearchVehicles [Error]: " + ex.getMessage() + " : " + ex.getLocalizedMessage());
			//return null;
			throw new RuntimeException("advancedSearchVehicles exception");
		}
	}
	
}
