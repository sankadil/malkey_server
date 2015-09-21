package com.dspl.malkey.dao;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fguarantor;
import com.dspl.malkey.service.FileUploadSRV;
import com.dspl.malkey.service.UserInfoSRV;

import flex.messaging.FlexContext;

public class FguarantorDAOImpl implements FguarantorDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Resource(name="fcompanysettingDAO")
	FcompanysettingDAO fcompanysettingDAO;
	
	@Resource(name="fileUploadSRV")
	FileUploadSRV fileUploadSRV;
	
	@Transactional
	@Override
	public List<Fguarantor> List(int startIndex, int numItems) {
		return em.createNamedQuery("FguarantorListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fguarantor> ListAll() {
		try {
			return em.createNamedQuery("FguarantorListAll").getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		finally{
			em.flush();
		}
		
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fguarantor").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public String create(Fguarantor fguarantor) {
		try {
			fguarantor=setUserEnvDet(fguarantor);
			String refNo=fcompanysettingDAO.getRefNo("GUR", fguarantor.getAdddate().get(Calendar.YEAR), ((fguarantor.getAdddate().get(Calendar.MONTH))+1));
			fguarantor.setGid(refNo);
			
			Boolean lbFileUploaded=false;
			String targetFolder = "/image/guarantor/";
			String serverName = "/resource";//FlexContext.getHttpRequest().getContextPath();

			String lsImageURL = serverName + targetFolder;
			
			if(fguarantor.getNicimagedata()!=null){
				if(!fguarantor.getNicimage().toString().equals("")){
					fguarantor.setNicimage(refNo+"_NF.jpeg");
					lbFileUploaded = fileUploadSRV.uploadFileToServer(fguarantor.getNicimage(),fguarantor.getNicimagedata(), targetFolder);
					if(lbFileUploaded==false)
						fguarantor.setNicimage("");
					else
						fguarantor.setNicimage(lsImageURL + fguarantor.getNicimage());
				}
			}else
				fguarantor.setNicimage("");
			
			if(fguarantor.getNicbackimagedata()!=null){
				if(!fguarantor.getNicbackimage().toString().equals("")){
					fguarantor.setNicbackimage(refNo+"_NB.jpeg");
					lbFileUploaded = fileUploadSRV.uploadFileToServer(fguarantor.getNicbackimage(),fguarantor.getNicbackimagedata(), targetFolder);
					if(lbFileUploaded==false)
						fguarantor.setNicbackimage("");
					else
						fguarantor.setNicbackimage(lsImageURL + fguarantor.getNicbackimage());
				}
			}else
				fguarantor.setNicbackimage("");
			
			if(fguarantor.getPassimagedata()!=null){
				if(!fguarantor.getPpimage().toString().equals("")){
					fguarantor.setPpimage(refNo+"_P.jpeg");
					lbFileUploaded = fileUploadSRV.uploadFileToServer(fguarantor.getPpimage(),fguarantor.getPassimagedata(), targetFolder);
					if(lbFileUploaded==false)
						fguarantor.setPpimage("");
					else
						fguarantor.setPpimage(lsImageURL + fguarantor.getPpimage());
				}
			}else
				fguarantor.setPpimage("");
			
			em.persist(fguarantor);
			em.flush();
			
			return refNo;
		} catch (Exception e) {
			System.out.println("Fguarantor create: " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			em.flush();
		}
		return null;
	}

	@Transactional
	@Override
	public Fguarantor findByID(String gid) {
		return em.find(Fguarantor.class, gid);
	}
	
	@Transactional
	@Override
	public Fguarantor findByDebcode(String debcode) {
		try{
		return (Fguarantor)em.createNamedQuery("FguarantorByDebcode").setParameter("debcode", debcode).getSingleResult();
		}
		catch(Exception e){
			System.out.println("unable to find guaranator");
			//throw new RuntimeException();}
			return null;}
		finally{
			em.flush();
		}
	}

	@Transactional(readOnly=false)
	@Override
	public Boolean removeByID(String gid) {
		Fguarantor fguarantor=em.find(Fguarantor.class, gid);
		if(fguarantor==null)
			System.out.println("null object found");
		else{
			em.remove(fguarantor);
			return true;
		}
		return null;
	}

	@Transactional(readOnly=false)
	@Override
	public Boolean update(Fguarantor fguarantor) {
		try{
			fguarantor=setUserEnvDet(fguarantor);
			String refNo=fguarantor.getGid();
			
			Boolean lbFileUploaded=false;
			String targetFolder = "/image/guarantor/";
			String serverName = "/resource";//FlexContext.getHttpRequest().getContextPath();

			String lsImageURL = serverName + targetFolder;
			
			if(fguarantor.getNicimagedata()!=null){
				if(fguarantor.getNicimage().toString()!=""){
					fguarantor.setNicimage(refNo+"_NF.jpeg");
					lbFileUploaded = fileUploadSRV.uploadFileToServer(fguarantor.getNicimage(),fguarantor.getNicimagedata(), targetFolder);
					if(lbFileUploaded==false)
						fguarantor.setNicimage("");
					else
						fguarantor.setNicimage(lsImageURL + fguarantor.getNicimage());
				}
			}
			
			if(fguarantor.getNicbackimagedata()!=null){
				if(!fguarantor.getNicbackimage().toString().equals("")){
					fguarantor.setNicbackimage(refNo+"_NB.jpeg");
					lbFileUploaded = fileUploadSRV.uploadFileToServer(fguarantor.getNicbackimage(),fguarantor.getNicbackimagedata(), targetFolder);
					if(lbFileUploaded==false)
						fguarantor.setNicbackimage("");
					else
						fguarantor.setNicbackimage(lsImageURL + fguarantor.getNicbackimage());
				}
			}
			
			if(fguarantor.getPassimagedata()!=null){
				if(fguarantor.getPpimage().toString()!=""){
					fguarantor.setPpimage(refNo+"_P.jpeg");
					lbFileUploaded = fileUploadSRV.uploadFileToServer(fguarantor.getPpimage(),fguarantor.getPassimagedata(), targetFolder);
					if(lbFileUploaded==false)
						fguarantor.setPpimage("");
					else
						fguarantor.setPpimage(lsImageURL + fguarantor.getPpimage());
				}
			}
			
			em.merge(fguarantor);
			return true;
		}catch(Exception e){
			System.out.println("Fvehiclemode update: " + e.getMessage());
		}
		finally{
			em.flush();
		}
		return false;
	}
	
	private Fguarantor setUserEnvDet(Fguarantor fguarantor){
		String curUser=userInfoSRV.getUser();
		String curMachine=userInfoSRV.getMachineName();
		Calendar curDate = Calendar.getInstance();
		curDate=fmaintenanceDAO.resetTime(curDate);
		fguarantor.setAdduser(curUser);
		fguarantor.setAddmach(curMachine);
		fguarantor.setAdddate(curDate);
		return fguarantor;
	}

	@Transactional(readOnly=false)
	@Override
	public List<Fguarantor> listApprovedGuarantors() {
		try {
			return em.createNamedQuery("FguarantorApprovedList").getResultList();
		} catch (Exception e) {
			System.out.println("listApprovedGuarantors: " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			em.flush();
		}
		return null;
	}

	@Transactional(readOnly=false)
	@Override
	public Boolean approveGuarantor(String gid, String approved) {
		try {
			String query="UPDATE fguarantor SET approved='"+approved+"' WHERE gid='"+gid+"'";
			em.createNativeQuery(query).executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("approveGuarantor: " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			em.flush();
		}
		return false;
	}

}
