package com.dspl.malkey.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fclientdriver;
import com.dspl.malkey.domain.Fdebtor;
import com.dspl.malkey.service.FileUploadSRV;
import com.dspl.malkey.service.UserInfoSRV;

import flex.messaging.FlexContext;

public class FdebtorDAOImpl implements FdebtorDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fileUploadSRV")
	FileUploadSRV fileUploadSRV;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Resource(name="fcompanysettingDAO")
	FcompanysettingDAO fcompanysettingDAO;
	
	@Transactional(readOnly=true)
	@Override
	public List<Fdebtor> List(int startIndex, int numItems) {
		try {
		long startTime = System.currentTimeMillis();
		em.flush();
		List<Fdebtor> list= em.createNamedQuery("FdebtorListAllDesc").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("FdebtorDAO.List :"+totalTime);
		return list;
		} catch (Exception e) {
			return null;
		}	finally	{em.flush();}	
	}

	@Transactional(readOnly=true)
	@Override
	public List<Fdebtor> ListAll() {
/*		long startTime = System.currentTimeMillis();
		List<Fdebtor> list= em.createNamedQuery("FdebtorListAll").getResultList();
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("FdebtorDAO.ListAll :"+totalTime);
		return list;*/
		return listAllOptimized();
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Fdebtor> listAllOptimizedPage(int startIndex, int numItems) {
		try {
		long startTime = System.currentTimeMillis();
		em.flush();
		List<Object[]> lstRowDebtors= em.createNamedQuery("FdebtorListAll.Optimized.Page").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
		List<Fdebtor> lstDebtor=new ArrayList<Fdebtor>();
		for(Object[] row : lstRowDebtors)
		{
			//query order :f.debcode,f.debname,f.nicno,f.clienttype,f.company,f.billmob1
			Fdebtor fdebtor=new Fdebtor();
			fdebtor.setDebcode((String)row[0]);
			fdebtor.setDebname((String)row[1]);
			fdebtor.setNicno((String)row[2]);
			fdebtor.setClienttype((String)row[3]);
			fdebtor.setCompany((String)row[4]);
			fdebtor.setBillmob1((String)row[5]);
			fdebtor.setLongterm((String)(row[6]));
			fdebtor.setPassportno((String)(row[7]));
			fdebtor.setDebstat((String)(row[8]));
			lstDebtor.add(fdebtor);
		}
		lstRowDebtors=null;
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("FdebtorDAO.listAllOptimized :"+totalTime);
		
		return lstDebtor;
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.flush();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Fdebtor> listLikeName(String name) {
		try {
			long startTime = System.currentTimeMillis();
			em.flush();
//f.debstat='A' AND
			String query="select f.debcode,f.debname,f.nicno,f.clienttype,f.company,f.billmob1,f.longterm,f.passportno,f.debstat from Fdebtor f where  (";
			String query2=" OR (f.debcode like '%"+name+"%')";
			
			String[] names=name.split("\\s+");
			int counter=0;
			for( String temp: names){
				counter++;
				query+=" f.debname like '%"+temp+"%'";
				if(counter!=names.length)
				{
					query+=" AND ";
				}
			}
			query=query+")"+query2;
			
			//f.debcode,f.debname,f.nicno,f.clienttype,f.company,f.billmob1,f.longterm,f.passportno
			System.out.println("query : "+query);
			List<Object[]> lstRowDebtors= em.createNativeQuery(query).getResultList();
			System.out.println("zize: "+lstRowDebtors.size());
			List<Fdebtor> lstDebtor=new ArrayList<Fdebtor>();
			for(Object[] row : lstRowDebtors)
			{
				//query order :f.debcode,f.debname,f.nicno,f.clienttype,f.company,f.billmob1
				Fdebtor fdebtor=new Fdebtor();
				fdebtor.setDebcode((String)row[0]);
				fdebtor.setDebname((String)row[1]);
				fdebtor.setNicno((String)row[2]);
				fdebtor.setClienttype((String)row[3]);
				fdebtor.setCompany((String)row[4]);
				fdebtor.setBillmob1((String)row[5]);
				fdebtor.setLongterm((String)(row[6]));
				fdebtor.setPassportno((String)(row[7]));
				fdebtor.setDebstat((String)(row[8]));
				lstDebtor.add(fdebtor);
			}
			lstRowDebtors=null;
			
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("listLikeName :"+totalTime);
			
			return lstDebtor;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.flush();
		}
		
	}
	@Transactional(readOnly=true)
	@Override
	public List<Fdebtor> listAllOptimized() {
		try {
			long startTime = System.currentTimeMillis();
			em.flush();
			List<Object[]> lstRowDebtors= em.createNamedQuery("FdebtorListAll.Optimized").getResultList();
			List<Fdebtor> lstDebtor=new ArrayList<Fdebtor>();
			for(Object[] row : lstRowDebtors)
			{
				//query order :f.debcode,f.debname,f.nicno,f.clienttype,f.company,f.billmob1
				Fdebtor fdebtor=new Fdebtor();
				fdebtor.setDebcode((String)row[0]);
				fdebtor.setDebname((String)row[1]);
				fdebtor.setNicno((String)row[2]);
				fdebtor.setClienttype((String)row[3]);
				fdebtor.setCompany((String)row[4]);
				fdebtor.setBillmob1((String)row[5]);
				fdebtor.setLongterm((String)(row[6]));
				fdebtor.setDebstat((String)(row[7]));
				lstDebtor.add(fdebtor);
			}
			lstRowDebtors=null;
			
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("FdebtorDAO.listAllOptimized :"+totalTime);
			
			return lstDebtor;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.flush();
		}
		
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Fdebtor> listAllNameNIC() {
		try {
			long startTime = System.currentTimeMillis();
			em.flush();
			List<Object[]> lstRowDebtors= em.createNamedQuery("FdebtorListAll.NameNIC").getResultList();
			List<Fdebtor> lstDebtor=new ArrayList<Fdebtor>();
			for(Object[] row : lstRowDebtors)
			{
				//query order :f.debcode,f.debname,f.nicno,f.clienttype,f.company,f.billmob1
				Fdebtor fdebtor=new Fdebtor();
				fdebtor.setDebcode((String)row[0]);
				fdebtor.setDebname((String)row[1]);
				fdebtor.setNicno((String)row[2]);
				fdebtor.setDebstat((String)(row[3]));
				lstDebtor.add(fdebtor);
			}
			lstRowDebtors=null;
			
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("FdebtorDAO.listAllNameNIC :"+totalTime);
			
			return lstDebtor;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.flush();
		}
		
	}
	


	@Transactional(readOnly=true)
	@Override
	public int count() {
		long startTime = System.currentTimeMillis();
		em.flush();
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fdebtor").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public String create(Fdebtor fdebtor,ArrayList driList) {
		long startTime = System.currentTimeMillis();
		em.flush();
		try {
			
			String refNo=""; 
			String curUser=userInfoSRV.getUser();
			String curMachine=userInfoSRV.getMachineName();
			Calendar curDate = Calendar.getInstance();
			curDate=fmaintenanceDAO.resetTime(curDate);
			
			refNo=fcompanysettingDAO.getRefNo("CUS", curDate.get(Calendar.YEAR), ((curDate.get(Calendar.MONTH))+1));
			fdebtor.setDebcode(refNo);
			
			Boolean lbFileUploaded=false;
			String targetFolder = "/image/customer/";
			String serverName = "/resource";//FlexContext.getHttpRequest().getContextPath();

			String lsImageURL = serverName + targetFolder;
			
			if(fdebtor.getNicimagedata()!=null){
				if(!fdebtor.getNicimage().toString().equals("")){
					fdebtor.setNicimage(refNo+"_NF.jpeg");
					lbFileUploaded = fileUploadSRV.uploadFileToServer(fdebtor.getNicimage(),fdebtor.getNicimagedata(), targetFolder);
					if(lbFileUploaded==false)
						fdebtor.setNicimage("");
					else
						fdebtor.setNicimage(lsImageURL + fdebtor.getNicimage());
				}
			}else
				fdebtor.setNicimage("");
			
			if(fdebtor.getNicbackimagedata()!=null){
				if(!fdebtor.getNicbackimage().toString().equals("")){
					fdebtor.setNicbackimage(refNo+"_NB.jpeg");
					lbFileUploaded = fileUploadSRV.uploadFileToServer(fdebtor.getNicbackimage(),fdebtor.getNicbackimagedata(), targetFolder);
					if(lbFileUploaded==false)
						fdebtor.setNicbackimage("");
					else
						fdebtor.setNicbackimage(lsImageURL + fdebtor.getNicbackimage());
				}
			}else
				fdebtor.setNicbackimage("");
			
			if(fdebtor.getPassimagedata()!=null){
				if(!fdebtor.getPpimage().toString().equals("")){
					fdebtor.setPpimage(refNo+"_P.jpeg");
					lbFileUploaded = fileUploadSRV.uploadFileToServer(fdebtor.getPpimage(),fdebtor.getPassimagedata(), targetFolder);
					if(lbFileUploaded==false)
						fdebtor.setPpimage("");
					else
						fdebtor.setPpimage(lsImageURL + fdebtor.getPpimage());
				}
			}else
				fdebtor.setPpimage("");
			
			//if(fdebtor.getEmailClient())
			//System.out.println("emailClient: "+fdebtor.getEmailClient());
			
			//Persists FDebtor
			fdebtor.setAdddate(curDate);
			fdebtor.setAdduser(curUser);
			fdebtor.setAddmach(curMachine);
			em.persist(fdebtor);
			em.flush();
			
			targetFolder = "/image/customerdriver/";
			lsImageURL = serverName + targetFolder;
			for(int a=0;a<driList.size();a++){
				Fclientdriver fclientdriver=(Fclientdriver)driList.get(a);
				fclientdriver.setDebcode(fdebtor.getDebcode());
				fclientdriver.setAdddate(curDate);
				fclientdriver.setAdduser(curUser);
				fclientdriver.setAddmach(curMachine);
				
				if(fclientdriver.getLicfrontdata()!=null){
					//String frontImg=fdebtor.getDebcode()+ "_" + (a+1) +"_Front.jpeg";
					String frontImg=fdebtor.getDebcode()+ "_" + fclientdriver.getDlno().replace("/","-").replace("\\","-") +"_Front.jpeg";
					lbFileUploaded = fileUploadSRV.uploadFileToServer(frontImg,fclientdriver.getLicfrontdata(),targetFolder);
					if(lbFileUploaded==false)
						fclientdriver.setDlfontimage("");
					else
						fclientdriver.setDlfontimage(lsImageURL + frontImg);
				}
				
				if(fclientdriver.getLicbackdata()!=null){
					//String backImg=fdebtor.getDebcode()+ "_" + (a+1) +"_Back.jpeg";
					String backImg=fdebtor.getDebcode()+ "_" + fclientdriver.getDlno().replace("/","-").replace("\\","-") +"_Back.jpeg";
					lbFileUploaded = fileUploadSRV.uploadFileToServer(backImg,fclientdriver.getLicbackdata(),targetFolder);
					if(lbFileUploaded==false)
						fclientdriver.setDlbackimage("");
					else
						fclientdriver.setDlbackimage(lsImageURL + backImg);
				}
				
				//Persists FClientDriver
				em.persist(fclientdriver);
				em.flush();
			}
			
			return refNo;
		} catch (Exception e) {
			System.out.println("Fdebtor Create [Error]: " + e.getLocalizedMessage());
			e.printStackTrace();
		}finally	{em.flush();}
		return null;
	}

	@Transactional(readOnly=true)
	@Override
	public Fdebtor findByID(String debcode) {
		try {
			em.flush();
			long startTime = System.currentTimeMillis();
			System.out.println("start findByID @"+startTime);
			Fdebtor fdebtor = em.find(Fdebtor.class, debcode);
			//Fdebtor fdebtor = (Fdebtor)em.createNativeQuery("SELECT * FROM FDEBTOR WHERE DEBCODE=?", Fdebtor.class);
			long endTime = System.currentTimeMillis();
			System.out.println("end findByID @"+endTime);
			long totalTime = endTime - startTime;
			System.out.println("FdebtorDAO.findByID :" + totalTime);
			return fdebtor;

		} catch (Exception e) {
			return null;
		} finally {
			em.flush();
		}
		
	}
	
	@Transactional(readOnly=false)
	@Override
	public Boolean removeByID(String debcode) {
		long startTime = System.currentTimeMillis();
		em.flush();
		try {
			String query;
			query="DELETE FROM fdebtor WHERE debcode='" + debcode + "'";
			em.createNativeQuery(query).executeUpdate();
			em.flush();
			query="DELETE FROM fclientdriver WHERE debcode='" + debcode + "'";
			em.createNativeQuery(query).executeUpdate();
			em.flush();
			
			
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("FdebtorDAO.removeByID :"+totalTime);
			
			
			return true;
		} catch (Exception e) {
			System.out.println("Fdebtor RemoveByID [Error]: " + e.getLocalizedMessage());
			e.printStackTrace();
			return false;
		}finally	{em.flush();}
	}

//	@Transactional(readOnly=false)
//	@Override
//	public Boolean removeByID(String debcode) {
//		em.remove(em.find(Fdebtor.class, debcode));
//		return true;
//	}

//	@Transactional(readOnly=false)
//	@Override
//	public Boolean update(Fdebtor fdebtor,ArrayList driList) {
//		em.merge(fdebtor);
//		return true;
//	}
	
	private void setAddedDetails(Fdebtor fdebtor)
	{
		Fdebtor temp=findByID(fdebtor.getDebcode());
		fdebtor.setAdddate(temp.getAdddate());
		fdebtor.setAdduser(temp.getAdduser());
		fdebtor.setAddmach(temp.getAddmach());
	}
	
	@Transactional(readOnly=false)
	@Override
	public Boolean update(Fdebtor fdebtor,ArrayList driList) {
		long startTime = System.currentTimeMillis();
		em.flush();
		try {
			String refNo=fdebtor.getDebcode();
			String delQuery="DELETE FROM fclientdriver WHERE debcode='" + fdebtor.getDebcode() + "'";
			em.createNativeQuery(delQuery).executeUpdate();
			
			String curUser=userInfoSRV.getUser();
			String curMachine=userInfoSRV.getMachineName();
			Calendar curDate = Calendar.getInstance();
			curDate=fmaintenanceDAO.resetTime(curDate);
			
			Boolean lbFileUploaded=false;
			String targetFolder = "/image/customer/";
			String serverName = "/resource";//FlexContext.getHttpRequest().getContextPath();

			String lsImageURL = serverName + targetFolder;
			
			if(fdebtor.getNicimagedata()!=null){
				if(fdebtor.getNicimage().toString()!=""){
					fdebtor.setNicimage(refNo+"_NF.jpeg");
					lbFileUploaded = fileUploadSRV.uploadFileToServer(fdebtor.getNicimage(),fdebtor.getNicimagedata(), targetFolder);
					if(lbFileUploaded==false)
						fdebtor.setNicimage("");
					else
						fdebtor.setNicimage(lsImageURL + fdebtor.getNicimage());
				}
			}
			
			if(fdebtor.getNicbackimagedata()!=null){
				if(fdebtor.getNicbackimage().toString()!=""){
					fdebtor.setNicbackimage(refNo+"_NB.jpeg");
					lbFileUploaded = fileUploadSRV.uploadFileToServer(fdebtor.getNicbackimage(),fdebtor.getNicbackimagedata(), targetFolder);
					if(lbFileUploaded==false)
						fdebtor.setNicbackimage("");
					else
						fdebtor.setNicbackimage(lsImageURL + fdebtor.getNicbackimage());
				}
			}
			
			if(fdebtor.getPassimagedata()!=null){
				if(fdebtor.getPpimage().toString()!=""){
					fdebtor.setPpimage(refNo+"_P.jpeg");
					lbFileUploaded = fileUploadSRV.uploadFileToServer(fdebtor.getPpimage(),fdebtor.getPassimagedata(), targetFolder);
					if(lbFileUploaded==false)
						fdebtor.setPpimage("");
					else
						fdebtor.setPpimage(lsImageURL + fdebtor.getPpimage());
				}
			}
			
			//Persists FDebtor
			fdebtor.setModifieddate(curDate);
			fdebtor.setModifieduser(curUser);
			fdebtor.setModifiedmach(curMachine);
			setAddedDetails(fdebtor);
			em.merge(fdebtor);
			em.flush();
			
			targetFolder = "/image/customerdriver/";
			lsImageURL = serverName + targetFolder;
			for(int a=0;a<driList.size();a++){
				Fclientdriver fclientdriver=(Fclientdriver)driList.get(a);
				fclientdriver.setDebcode(fdebtor.getDebcode());
				fclientdriver.setAdddate(curDate);
				fclientdriver.setAdduser(curUser);
				fclientdriver.setAddmach(curMachine);
				
				if(fclientdriver.getLicfrontdata()!=null){
					//String frontImg=fdebtor.getDebcode()+ "_" + (a+1) +"_Front.jpeg";
					String frontImg=fdebtor.getDebcode()+ "_" + fclientdriver.getDlno().replace("/","-").replace("\\","-") +"_Front.jpeg";
					lbFileUploaded = fileUploadSRV.uploadFileToServer(frontImg,fclientdriver.getLicfrontdata(),targetFolder);
					if(lbFileUploaded==false)
						fclientdriver.setDlfontimage("");
					else
						fclientdriver.setDlfontimage(lsImageURL + frontImg);
				}
				
				if(fclientdriver.getLicbackdata()!=null){
					//String backImg=fdebtor.getDebcode()+ "_" + (a+1) +"_Back.jpeg";
					String backImg=fdebtor.getDebcode()+ "_" + fclientdriver.getDlno().replace("/","-").replace("\\","-") +"_Back.jpeg";
					lbFileUploaded = fileUploadSRV.uploadFileToServer(backImg,fclientdriver.getLicbackdata(),targetFolder);
					if(lbFileUploaded==false)
						fclientdriver.setDlbackimage("");
					else
						fclientdriver.setDlbackimage(lsImageURL + backImg);
				}
				
				//Persists FClientDriver
				em.persist(fclientdriver);
				em.flush();
			}
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("FdebtorDAO.update :"+totalTime);
			return true;
		} catch (Exception e) {
			System.out.println("Fdebtor Update [Error]: " + e.getLocalizedMessage());
			e.printStackTrace();
			return false;
		}
		finally
		{em.flush();}
	}

	@Transactional(readOnly=true)
	@Override
	public List<Fdebtor> getDebList() {
		long startTime = System.currentTimeMillis();
		em.flush();
		try {
			//following code commented because performance issue
/*			String query="SELECT debcode,debname,nicno,passportno,company FROM fdebtor";
			List<Fdebtor> list= em.createNativeQuery(query, "debList").getResultList();
			return list;*/

			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("FdebtorDAO.getDebList :"+totalTime);
			return listAllOptimized();
		} catch (Exception e) {
			return null;
		}finally	{em.flush();}
	}

	@Transactional(readOnly=true)
	@Override
	public List<Fdebtor> getGDebList() {
		long startTime = System.currentTimeMillis();
		em.flush();
		try {
			String query="SELECT debcode,debname,nicno,passportno,company FROM fdebtor WHERE debcode NOT IN (SELECT debcode FROM fguarantor WHERE debcode<>'') AND debstat='A'";
			em.flush();
			List<Fdebtor> list=  em.createNativeQuery(query, "debList").getResultList();
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("FdebtorDAO.getGDebList :"+totalTime);
			return list;
		} catch (Exception e) {
			return null;
		}finally	{em.flush();}
	}

	@Transactional(readOnly=true)
	@Override
	public List<Fdebtor> getDebtorList() {
		long startTime = System.currentTimeMillis();
		em.flush();
		try {
			String query="SELECT d.debcode,d.debname,d.nicno,d.passportno,d.debadd,d.tel,d.company,d.vatno,";
			query += " (SELECT description FROM fclienttype WHERE clienttype=d.clienttype) AS clienttype,";
			query += " (SELECT description FROM fdebtorstatus WHERE id=d.debstat) AS debstat";
			query += " FROM fdebtor AS d ORDER BY d.clienttype,d.debstat";
			

			em.flush();
			List<Fdebtor> list=  em.createNativeQuery(query, "debtorListRpt").getResultList();
			
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("FdebtorDAO.getDebtorList"+totalTime);
			return list;
		} catch (Exception e) {
			System.out.println("FdebotrDaoImpl getDebtorList: " + e.getMessage());
			e.printStackTrace();
		}
		finally	{em.flush();}
		return null;
	}
	@Transactional(readOnly=true)
	@Override
	public List<Fdebtor> getDebtorList(String clientType,boolean isLongterm) {
		long startTime = System.currentTimeMillis();
		em.flush();
		try {
			String query="SELECT d.debcode,d.debname,d.nicno,d.passportno,d.debadd,d.tel,d.company,d.vatno,";
			query += " (SELECT description FROM fclienttype WHERE clienttype=d.clienttype) AS clienttype,";
			query += " (SELECT description FROM fdebtorstatus WHERE id=d.debstat) AS debstat";
			query += " FROM fdebtor AS d ";
			if(!isLongterm && clientType!=null && clientType.trim().length()!=0){
				System.out.println("isLongterm "+isLongterm);
				System.out.println("clientType "+clientType);
			query += "WHERE d.clienttype=? ";}
			else if(isLongterm)
			{
				System.out.println("isLongterm "+isLongterm);
				query += "WHERE d.longterm=? ";
			}

			query += " ORDER BY d.clienttype,d.debstat";
			
			
			em.flush();
			//List<Fdebtor> list=  em.createNativeQuery(query, "debtorListRpt").setParameter(1,clientType).getResultList();
			Query q=  em.createNativeQuery(query, "debtorListRpt");
			if(!isLongterm && clientType!=null && clientType.trim().length()!=0){
				q.setParameter(1,clientType);
			}
			else if(isLongterm)
			{
				q.setParameter(1,"LONGTERM");
			}
			
			List<Fdebtor> list=q.getResultList();
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("FdebtorDAO.getDebtorList"+totalTime);
			return list;
		} catch (Exception e) {
			System.out.println("FdebotrDaoImpl getDebtorList: " + e.getMessage());
			e.printStackTrace();
		}
		finally	{em.flush();}
		return null;
	}
	
	
	


}
