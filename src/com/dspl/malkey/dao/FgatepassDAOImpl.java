package com.dspl.malkey.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import com.dspl.malkey.domain.Femployee;
import com.dspl.malkey.domain.Fgatepass;
import com.dspl.malkey.domain.Fvehicle;
import com.dspl.malkey.service.UserInfoSRV;

import flex.messaging.io.ArrayCollection;

public class FgatepassDAOImpl implements FgatepassDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fcompanysettingDAO")
	FcompanysettingDAO fcompanysettingDAO;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Transactional
	@Override
	public List<Fgatepass> List(int startIndex, int numItems) {
		return em.createNamedQuery("FgatepassListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fgatepass> ListAll() {
		return em.createNamedQuery("FgatepassListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fgatepass").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public String create(Fgatepass fgatepass) {
		try {
			Calendar curDate = Calendar.getInstance();
			curDate = fmaintenanceDAO.resetTime(curDate);
			String RefNo = fcompanysettingDAO.getRefNo("GTP", curDate.get(Calendar.YEAR), ((curDate.get(Calendar.MONTH))+1));
			if(RefNo.toString().length()<=0)
				return "";
			else{
				fgatepass.setPassno(RefNo);
				fgatepass.setTxndate(curDate);
				fgatepass.setAdddate(curDate);
				fgatepass.setAdduser(userInfoSRV.getUser());
				fgatepass.setAddmach(userInfoSRV.getMachineName());
				em.persist(fgatepass);
				em.flush();
				
				//Update Fvehicle
				Fvehicle fvehicle=em.find(Fvehicle.class, fgatepass.getRegno());
				if(fvehicle==null){
					throw new RuntimeException("Failed Updating Fvehicle");
				}else{
					fvehicle.setCurmileage(fgatepass.getOutmileage());
					fvehicle.setFuellevel(fgatepass.getOutfuellevel());
					if(fgatepass.getLocationtype().equals("E")==true){
						fvehicle.setLocationid(fgatepass.getOuttoloc());
					}
					em.merge(fvehicle);
				}
				return RefNo;
			}
		} catch (Exception e) {
			System.out.println("Fgatepass create: " + e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	@Transactional
	@Override
	public Fgatepass findByID(String passno) {
		return em.find(Fgatepass.class, passno);
	}
	@Transactional(readOnly=false)
	@Override
	public Boolean removeByID(String passno) {
		try{
			em.remove(em.find(Fgatepass.class, passno));
			return true;
		}catch(Exception e){
			System.out.println("Fgatepass removeById: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Transactional(readOnly=false)
	@Override
	public Boolean update(Fgatepass fgatepass) {
		try {
			Calendar curDate = Calendar.getInstance();
			curDate = fmaintenanceDAO.resetTime(curDate);
			//fgatepass.setTxndate(curDate);
			fgatepass.setAdddate(curDate);
			fgatepass.setAdduser(userInfoSRV.getUser());
			fgatepass.setAddmach(userInfoSRV.getMachineName());
			em.merge(fgatepass);
			em.flush();
			//Update Fvehicle
			Fvehicle fvehicle=em.find(Fvehicle.class, fgatepass.getRegno());
			if(fvehicle==null){
				throw new RuntimeException("Failed Updating Fvehicle");
			}else{
				fvehicle.setCurmileage(fgatepass.getInmileage());
				fvehicle.setFuellevel(fgatepass.getInfuellevel());
				fvehicle.setLocationid(fgatepass.getIntoloc());
				em.merge(fvehicle);
			}
			return true;
		} catch (Exception e) {
			System.out.println("Fgatepass update: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public List<Fvehicle> getVehicleList() {
		try {
			//String query="SELECT v.regno,v.description,m.description [vehimakeid],d.description [vehimodelid],v.curmileage,v.fuellevel FROM fvehicle AS v,fvehiclemake AS m,fvehiclemodel AS d WHERE v.vehimakeid=m.vehimakeid AND v.vehimodelid=d.vehimodelid";
			//String query="SELECT v.regno,m.description [vehimakeid],d.description [vehimodelid],v.curmileage,v.fuellevel FROM fvehicle AS v,fvehiclemake AS m,fvehiclemodel AS d WHERE v.vehimakeid=m.vehimakeid AND v.vehimodelid=d.vehimodelid";
			String query="SELECT v.regno,m.description [vehimakeid],d.description [vehimodelid],v.curmileage,v.fuellevel,v.locationid FROM fvehicle AS v,fvehiclemake AS m,fvehiclemodel AS d WHERE v.vehimakeid=m.vehimakeid AND v.vehimodelid=d.vehimodelid AND m.vehimakeid=d.vehimakeid";
			query += " AND v.regno NOT IN(SELECT regno FROM fgatepass WHERE status='O')";
			//query += " AND v.vehimakeid<>'N/A' AND v.vehimodelid<>'N/A' AND v.locationid<>''";
			Query Q = em.createNativeQuery(query);
			List<Object[]> l1 = Q.getResultList();
			List<Fvehicle> fvehicles=new ArrayCollection();
			for(Object[] r1 : l1)
			{
				String regno=(String)r1[0];
				//String description=(String)r1[1];
				String vehimakeid=(String)r1[1];
				String vehimodelid=(String)r1[2];
				int curmileage=Integer.parseInt(r1[3].toString());
				int fuellevel=Integer.parseInt(r1[4].toString());
				String locationid=(String)r1[5];
				
				Fvehicle fvehicle=new Fvehicle();
				fvehicle.setRegno(regno);
				//fvehicle.setDescription(description);
				fvehicle.setVehimakeid(vehimakeid);
				fvehicle.setVehimodelid(vehimodelid);
				fvehicle.setCurmileage(curmileage);
				fvehicle.setFuellevel(fuellevel);
				fvehicle.setLocationid(locationid);
				fvehicles.add(fvehicle);
			}
			l1=null;
			return fvehicles;
		} catch (Exception e) {
			System.out.println("Fgatepass getVehicleList: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public List<Femployee> getEmployeeList() {
		try {
			String query="SELECT e.empid,e.name,t.description [emptype] FROM femployee AS e,femployeetype AS t WHERE e.emptype=t.emptype AND e.empstat='A' ORDER BY emptype,empid";
			Query Q = em.createNativeQuery(query);
			List<Object[]> l1 = Q.getResultList();
			List<Femployee> femployees=new ArrayCollection();
			for(Object[] r1 : l1)
			{
				String empid=(String)r1[0];
				String name=(String)r1[1];
				String emptype=(String)r1[2];
				
				Femployee Femployee=new Femployee();
				Femployee.setEmpid(empid);
				Femployee.setName(name);
				Femployee.setEmptype(emptype);
				femployees.add(Femployee);
			}
			l1=null;
			return femployees;
		} catch (Exception e) {
			System.out.println("Fgatepass getEmployeeList: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public List<Femployee> getDriverList() {
		try {
			//String query="SELECT empid,name,dlno FROM femployee WHERE emptype='DRIVER' AND empstat='A' ORDER BY empid";
			//femployeetype table can keep which emptype can be driver 
			String query="SELECT empid,name,dlno FROM femployee WHERE emptype in ('DRIVER','MAINTENANC','MD') AND empstat='A' ORDER BY empid";
			Query Q = em.createNativeQuery(query);
			List<Object[]> l1 = Q.getResultList();
			List<Femployee> femployees=new ArrayCollection();
			for(Object[] r1 : l1)
			{
				String empid=(String)r1[0];
				String name=(String)r1[1];
				String dlno=(String)r1[2];
				
				Femployee Femployee=new Femployee();
				Femployee.setEmpid(empid);
				Femployee.setName(name);
				Femployee.setDlno(dlno);
				femployees.add(Femployee);
			}
			l1=null;
			return femployees;
		} catch (Exception e) {
			System.out.println("Fgatepass getEmployeeList: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public List<Fgatepass> getRefList() {
		try {
			String query="SELECT passno,regno,outdate,outtime FROM fgatepass WHERE status='O' ORDER BY passno,outdate";
			Query Q = em.createNativeQuery(query);
			List<Object[]> l1 = Q.getResultList();
			List<Fgatepass> fgatepasses=new ArrayCollection();
			for(Object[] r1 : l1)
			{
				String passno=(String)r1[0];
				String regno=(String)r1[1];
				
				Timestamp tmpoutdate=(Timestamp)r1[2];
				Date dt=new Date(tmpoutdate.getTime());
				Calendar outdate=Calendar.getInstance();
				outdate.setTime(dt);
				
				String outtime=(String)r1[3];
				
				Fgatepass fgatepass=new Fgatepass();
				fgatepass.setPassno(passno);
				fgatepass.setRegno(regno);
				fgatepass.setOutdate(outdate);
				fgatepass.setOuttime(outtime);
				fgatepasses.add(fgatepass);
			}
			return fgatepasses;
		} catch (Exception e) {
			System.out.println("Fgatepass getRefList: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public Fgatepass findByPassNo(String passno) {
		try {
//			String query="SELECT (SELECT DISTINCT description FROM fvehiclemake WHERE vehimakeid=(SELECT vehimakeid FROM fvehicle WHERE regno=p.regno)) [vehimakeid],";
//			query += " (SELECT DISTINCT description FROM fvehiclemodel WHERE vehimodelid=(SELECT vehimodelid FROM fvehicle WHERE regno=p.regno)) [vehimodelid],";
//			query += " (SELECT name FROM femployee WHERE empid=p.empid) [name],";
//			query += " p.passno,p.txndate,p.regno,p.empid,p.outdate,p.outmileage,p.outfuellevel,p.indate,p.inmileage,p.infuellevel,p.authorisedby,p.remarks,p.adduser,p.addmach,p.adddate,p.recordid,p.status,p.outtime,p.intime FROM fgatepass AS p";
//			query += " WHERE p.passno='"+ passno +"'";

			
			Fgatepass fgatepass=findByID(passno);
			if(fgatepass==null){
				System.out.println("fgatepass is null");
				return null;
			}
			
			String query="SELECT";
			query += " (SELECT DISTINCT description FROM fvehiclemake WHERE vehimakeid=(SELECT vehimakeid FROM fvehicle WHERE regno=p.regno)) [vehimakeid],"; 
			query += " (SELECT DISTINCT description FROM fvehiclemodel WHERE vehimodelid=(SELECT vehimodelid FROM fvehicle WHERE regno=p.regno)) [vehimodelid],";
			query += " (SELECT locationid FROM fvehicle WHERE regno=p.regno) [locationid],";
			query += " (SELECT [type] FROM flocation WHERE locationid=p.outtoloc) [locationtype],";
			query += " (SELECT name FROM femployee WHERE empid=p.empid) [name],";
			query += " (SELECT name FROM femployee WHERE empid=p.authorisedby) [authorisedbyname],";
			query += " (SELECT description FROM flocation WHERE locationid=p.outfromloc) [outfromlocdes],";
			query += " (SELECT description FROM flocation WHERE locationid=p.outtoloc) [outtolocdes],";
			query += " p.outdate,p.adduser";
			query += " FROM fgatepass AS p WHERE p.passno='"+passno+"'";
			
			System.out.println("query: " + query);
			
			Query Q = em.createNativeQuery(query);
			List<Object[]> l1 = Q.getResultList();
			
			if(l1.size()<=0){
				System.out.println("l1 length is zero");
				return null;
			}
			
			Object[] r1=l1.get(0);
			String vehimakeid=(String)r1[0];
			String vehimodelid=(String)r1[1];
			String locationid=(String)r1[2];
			String locationtype=(String)r1[3];
			String name=(String)r1[4];
			String authorisedbyname=(String)r1[5];
			String outfromlocdes=(String)r1[6];
			String outtolocdes=(String)r1[7];
			Timestamp tsoutdate=(Timestamp)r1[8];
			
			fgatepass.setVehimakeid(vehimakeid);
			fgatepass.setVehimodelid(vehimodelid);
			fgatepass.setLocationid(locationid);
			fgatepass.setLocationtype(locationtype);
			fgatepass.setName(name);
			fgatepass.setAuthorisedbyname(authorisedbyname);
			fgatepass.setOutfromlocdes(outfromlocdes);
			fgatepass.setOuttolocdes(outtolocdes);
			fgatepass.setTsoutdate(tsoutdate);
			fgatepass.setBcode("*" + fgatepass.getPassno() + "*");
			fgatepass.setAdduser((String)r1[9]);
			return fgatepass;
		} catch (Exception e) {
			System.out.println("Fgatepass findByPassNo: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public List<Fgatepass> getGatePassList(String dateFrom, String dateTo, String status) {
		try {
			Query query;
			
//			System.out.println("dateFrom: " + dateFrom + "    dateTo: " + dateTo + "   status: " + status);
//			DateFormat formatter=new SimpleDateFormat("yyyy.MM.dd");
//			
//			java.util.Date fromdate=(java.util.Date)formatter.parse(dateFrom);
//			Calendar df=Calendar.getInstance();
//			df.setTime(fromdate);
//			df=fmaintenanceDAO.resetTime(df);
//			
//			java.util.Date todate=(java.util.Date)formatter.parse(dateTo);
//			Calendar dt=Calendar.getInstance();
//			dt.setTime(todate);
//			dt=fmaintenanceDAO.resetTime(dt);
			
			String qr="SELECT p.regno,p.outdate,p.outtime,p.outmileage,p.outfuellevel,p.indate,p.intime,p.inmileage,p.infuellevel,p.remarks,p.passno,";
				qr += " (SELECT name FROM femployee WHERE empid=p.empid) [name],";
				qr += " (SELECT name FROM femployee WHERE empid=p.authorisedby) [authorisedbyname]";
				qr += " FROM fgatepass AS p WHERE ";
				qr += " outdate>='"+dateFrom+"'";
				qr += " AND outdate<='"+dateTo+"'"; 
				if(status!=null && !status.trim().equals("A")==true){
					qr += " AND status='"+status+"'"; 	
				}
				qr += " ORDER BY outdate,regno";
			
			System.out.println("Query:\n"+qr);	
			Query Q = em.createNativeQuery(qr);
			List<Object[]> l1 = Q.getResultList();
			List<Fgatepass> fgatepasses=new ArrayCollection();
			
			String regno;
			Timestamp tsoutdate;
			String outtime;
			int outmileage;
			int outfuellevel;
			Timestamp tsindate;
			String intime;
			int inmileage;
			int infuellevel;
			String remarks;
			String passno;
			String name;
			String authorisedbyname;
			
			for(Object[] r1 : l1)
			{
				regno=(String)r1[0];
				
				tsoutdate=(Timestamp)r1[1];
				outtime=(String)r1[2];
				outmileage=Integer.parseInt(r1[3].toString());
				outfuellevel=Integer.parseInt(r1[4].toString());
				
				tsindate=(Timestamp)r1[5];
				intime=(String)r1[6];
				inmileage=Integer.parseInt(r1[7].toString());
				infuellevel=Integer.parseInt(r1[8].toString());
				
				remarks=(String)r1[9];
				passno=(String)r1[10];
				name=(String)r1[11];
				authorisedbyname=(String)r1[12];
					
				Fgatepass fgatepass=new Fgatepass();
				fgatepass.setRegno(regno);
				fgatepass.setTsoutdate(tsoutdate);
				fgatepass.setOuttime(outtime);
				fgatepass.setOutmileage(outmileage);
				fgatepass.setOutfuellevel(outfuellevel);
				fgatepass.setTsindate(tsindate);
				fgatepass.setIntime(intime);
				fgatepass.setInmileage(inmileage);
				fgatepass.setInfuellevel(infuellevel);
				fgatepass.setRemarks(remarks);
				fgatepass.setPassno(passno);
				fgatepass.setName(name);
				fgatepass.setAuthorisedbyname(authorisedbyname);
				
				fgatepasses.add(fgatepass);
			}
			return fgatepasses;

//			if(status.trim().equals("A")==true){
//				System.out.println("status length is zero");
//				query=em.createNamedQuery("FgatepassVehicleMovement1");
//				query.setParameter("df", df);
//				query.setParameter("dt", dt);
//				System.out.println("is zero test");
//			}else{
//				System.out.println("status length is not ZERO");
//				query=em.createNamedQuery("FgatepassVehicleMovement2");
//				query.setParameter("df", df);
//				query.setParameter("dt", dt);
//				query.setParameter("st", status);
//				System.out.println("is not zero test");
//			}
//			if(query==null)
//				System.out.println("query is null");
//			return query.getResultList();
		} catch (Exception e) {
			System.out.println("FgatepassDaoImpl getGatePassList: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
}
