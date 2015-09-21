package com.dspl.malkey.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Femployee;
import com.dspl.malkey.service.FileUploadSRV;
import com.dspl.malkey.service.UserInfoSRV;

import flex.messaging.FlexContext;

public class FemployeeDAOImpl implements FemployeeDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Resource(name="fcompanysettingDAO")
	FcompanysettingDAO fcompanysettingDAO;
	
	@Resource(name="fileUploadSRV")
	FileUploadSRV fileUploadSRV;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Transactional
	@Override
	public List<Femployee> List(int startIndex, int numItems) {
		return em.createNamedQuery("FemployeeListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Femployee> ListAll() {
		return em.createNamedQuery("FemployeeListAll").getResultList();
	}
	
	@Transactional
	@Override
	public List<Femployee> DriverListAll() {
		//Assumption :- employee type DRIVER is DRIVER
		return em.createNamedQuery("FemployeeDriverListAll").setParameter("emptype","DRIVER").getResultList();
	}
	
	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Femployee").getSingleResult());
	}

//	@Transactional(readOnly=false)
//	@Override
//	public void create(Femployee femployee) {
//		em.persist(femployee);
//	}

	@Transactional
	@Override
	public Femployee findByID(String empid) {
		return em.find(Femployee.class, empid);
	}

//	@Transactional(readOnly=false)
//	@Override
//	public void removeByID(String empid) {
//		em.remove(em.find(Femployee.class, empid));
//	}

//	@Transactional(readOnly=false)
//	@Override
//	public void udpate(Femployee femployee) {
//		em.merge(femployee);
//	}

	@Transactional
	@Override
	public String create(Femployee femployee) {
		try {
			String refNo="";
			String curUser=userInfoSRV.getUser();
			String curMachine=userInfoSRV.getMachineName();
			Calendar curDate = Calendar.getInstance();
			curDate=fmaintenanceDAO.resetTime(curDate);
			
			refNo = fcompanysettingDAO.getRefNo("EMP", curDate.get(Calendar.YEAR), ((curDate.get(Calendar.MONTH))+1));
			
			if(refNo==null || refNo.trim().length()==0)
				return "";
						
			femployee.setEmpid(refNo);
			refNo=refNo.replace("/","-");
			
			Boolean lbFileUploaded=false;
			String targetFolder = "/image/employee/";
			String serverName = "/resource";//FlexContext.getHttpRequest().getContextPath();

			String lsImageURL = serverName + targetFolder;
			
			femployee.setPhoto(refNo + "_photo.jpg");
			femployee.setNicimage(refNo + "_nicF.jpg");
			femployee.setNicbackimage(refNo + "_nicB.jpg");
			
//			if(femployee.getPhotodata()!=null){
//				if(!femployee.getPhoto().toString().trim().equals("")){
//					lbFileUploaded = fileUploadSRV.uploadFileToServer(femployee.getPhoto(),femployee.getPhotodata(), targetFolder);
//					if(lbFileUploaded==false)
//						return "";
//					else
//						femployee.setPhoto((lsImageURL + femployee.getPhoto()));
//				}else
//					return "";
//			}else
//				return "";
			
			
			if(!femployee.getPhoto().toString().trim().equals("")){
				if(femployee.getPhotodata()!=null){
					lbFileUploaded = fileUploadSRV.uploadFileToServer(femployee.getPhoto(),femployee.getPhotodata(), targetFolder);
					if(lbFileUploaded==false)
						return "";
					else
						femployee.setPhoto((lsImageURL + femployee.getPhoto()));
				}else
					return "";
			}else
				return "";
			
			
//			if(femployee.getNicimagedata()!=null){
//				if(!femployee.getNicimage().toString().trim().equals("")){
//					lbFileUploaded = fileUploadSRV.uploadFileToServer(femployee.getNicimage(),femployee.getNicimagedata(), targetFolder);
//					if(lbFileUploaded==false)
//						return "";
//					else
//						femployee.setNicimage(lsImageURL + femployee.getNicimage());
//				}else
//					return "";
//			}else
//				return "";
			
			
			if(!femployee.getNicimage().toString().trim().equals("")){
				if(femployee.getNicimagedata()!=null){
					lbFileUploaded = fileUploadSRV.uploadFileToServer(femployee.getNicimage(),femployee.getNicimagedata(), targetFolder);
					if(lbFileUploaded==false)
						return "";
					else
						femployee.setNicimage(lsImageURL + femployee.getNicimage());
				}else
					return "";
			}else
				return "";
			
			if(!femployee.getNicbackimage().toString().trim().equals("")){
				if(femployee.getNicbackimagedata()!=null){
					lbFileUploaded = fileUploadSRV.uploadFileToServer(femployee.getNicbackimage(),femployee.getNicbackimagedata(), targetFolder);
					if(lbFileUploaded==false)
						return "";
					else
						femployee.setNicbackimage(lsImageURL + femployee.getNicbackimage());
				}else
					return "";
			}else
				return "";
			
			
			if(femployee.getEmptype().toLowerCase().trim().equals("driver")){
				targetFolder = "/image/employee/driver/";
				lsImageURL = serverName + targetFolder;
				
				femployee.setDlfrontimage(refNo + "_front.jpg");
				femployee.setDlbackimage(refNo + "_back.jpg");
				
				if(!femployee.getDlfrontimage().toString().trim().equals("")){
					if(femployee.getDlfrontimagedata()!=null){
						lbFileUploaded = fileUploadSRV.uploadFileToServer(femployee.getDlfrontimage(),femployee.getDlfrontimagedata(), targetFolder);
						if(lbFileUploaded==false)
							return "";
						else
							femployee.setDlfrontimage((lsImageURL + femployee.getDlfrontimage()));
					}else
						return "";
				}else
					return "";
				
				if(!femployee.getDlbackimage().toString().trim().equals("")){
					if(femployee.getDlbackimagedata()!=null){
						lbFileUploaded = fileUploadSRV.uploadFileToServer(femployee.getDlbackimage(),femployee.getDlbackimagedata(), targetFolder);
						if(lbFileUploaded==false)
							return "";
						else
							femployee.setDlbackimage((lsImageURL + femployee.getDlbackimage()));
					}else
						return "";
				}else
					return "";
			
			}
			
			//Persists FEmployee
			femployee.setAdddate(curDate);
			femployee.setAdduser(curUser);
			femployee.setAddmach(curMachine);
			em.persist(femployee);
			em.flush();
			
			return refNo;
		} catch (Exception e) {
			System.out.println("Femployee create: " + e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	@Transactional
	@Override
	public Boolean removeByID(String empid) {
		try {
			//Remove Femployee
			em.remove(em.find(Femployee.class, empid));
			em.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Femployee removeByID: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	@Override
	public Boolean update(Femployee femployee) {
		try {
			String refNo=femployee.getEmpid();
			String curUser=userInfoSRV.getUser();
			String curMachine=userInfoSRV.getMachineName();
			Calendar curDate = Calendar.getInstance();
			curDate=fmaintenanceDAO.resetTime(curDate);
						
			femployee.setEmpid(refNo);
			refNo=refNo.replace("/","-");
			
			Boolean lbFileUploaded=false;
			String targetFolder = "/image/employee/";
			String serverName = "/resource";//FlexContext.getHttpRequest().getContextPath();

			String lsImageURL = serverName + targetFolder;
			
//			femployee.setPhoto(refNo + "_photo.jpg");
//			femployee.setNicimage(refNo + "_nic.jpg");
			
			if(femployee.getPhotodata()!=null){
				femployee.setPhoto(refNo + "_photo.jpg");
				lbFileUploaded = fileUploadSRV.uploadFileToServer(femployee.getPhoto(),femployee.getPhotodata(), targetFolder);
				if(lbFileUploaded==false)
					return false;
				else
					femployee.setPhoto((lsImageURL + femployee.getPhoto()));
			}
			
			if(femployee.getNicimagedata()!=null){
				femployee.setNicimage(refNo + "_nicF.jpg");
				lbFileUploaded = fileUploadSRV.uploadFileToServer(femployee.getNicimage(),femployee.getNicimagedata(), targetFolder);
				if(lbFileUploaded==false)
					return false;
				else
					femployee.setNicimage(lsImageURL + femployee.getNicimage());
			}
			
			if(femployee.getNicbackimagedata()!=null){
				femployee.setNicbackimage(refNo + "_nicB.jpg");
				lbFileUploaded = fileUploadSRV.uploadFileToServer(femployee.getNicbackimage(),femployee.getNicbackimagedata(), targetFolder);
				if(lbFileUploaded==false)
					return false;
				else
					femployee.setNicbackimage(lsImageURL + femployee.getNicbackimage());
			}
		
			if(femployee.getEmptype().toLowerCase().trim().equals("driver")){
				targetFolder = "/image/employee/driver/";
				lsImageURL = serverName + targetFolder;
				
				if(femployee.getDlfrontimagedata()!=null){
					femployee.setDlfrontimage(refNo + "_front.jpg");
					lbFileUploaded = fileUploadSRV.uploadFileToServer(femployee.getDlfrontimage(),femployee.getDlfrontimagedata(), targetFolder);
					if(lbFileUploaded==false)
						return false;
					else
						femployee.setDlfrontimage((lsImageURL + femployee.getDlfrontimage()));
				}
				
				if(femployee.getDlbackimagedata()!=null){
					femployee.setDlbackimage(refNo + "_back.jpg");
					lbFileUploaded = fileUploadSRV.uploadFileToServer(femployee.getDlbackimage(),femployee.getDlbackimagedata(), targetFolder);
					if(lbFileUploaded==false)
						return false;
					else
						femployee.setDlbackimage((lsImageURL + femployee.getDlbackimage()));
				}
			}
			
			//Merge FEmployee
			femployee.setAdddate(curDate);
			femployee.setAdduser(curUser);
			femployee.setAddmach(curMachine);
			em.merge(femployee);
			em.flush();
			
			return true;
		} catch (Exception e) {
			System.out.println("Femployee update: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	@Override
	public List<Femployee> ListEmployees() {
		return em.createNativeQuery("SELECT f.empid,f.name,t.description,f.nicno,f.ppno,f.dlno FROM femployee f,femployeetype t WHERE f.emptype=t.emptype ORDER BY f.empid,t.description", "empList").getResultList();
	}
	
	
	@Transactional(readOnly=true)
	@Override
	public List<Femployee> DriverListAvailable(Date fromDate,Date toDate) {
		try {
			Query query = em.createNamedQuery("FemployeeDriverListByDate");
			query.setParameter(1, fromDate, TemporalType.TIMESTAMP);
			query.setParameter(2, toDate, TemporalType.TIMESTAMP);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
		finally{em.flush();}

	}

	@Transactional
	@Override
	public List<Femployee> getEmpList() {
		try {
			String query="SELECT empid,name,nicno FROM femployee WHERE empid NOT IN(SELECT empid FROM fpass) AND empstat='A' ORDER BY empid";
			return em.createNativeQuery(query, Femployee.class).getResultList();
		} catch (Exception e) {
			System.out.println("FemployeeDaoImpl: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public List<Femployee> getEmployees() {
		try {
			String query="SELECT empid,name FROM femployee ORDER BY empid";
			return em.createNativeQuery(query, Femployee.class).getResultList();
		} catch (Exception e) {
			System.out.println("FemployeeDaoImpl getEmployees: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	@Override
	public List<Femployee> getDriversByResNo(String resno) {
		try {
			String query="select empid,name,nicno,nicimage,photo,dlno,dldissue,dldexp,dlfrontimage,dlbackimage,mobilephone1 from femployee where empid in (select empid from fresdriver where resno=?)";
			return em.createNativeQuery(query, Femployee.class).setParameter(1, resno).getResultList();
		} catch (Exception e) {
			System.out.println("FemployeeDaoImpl getEmployees: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}	
}
