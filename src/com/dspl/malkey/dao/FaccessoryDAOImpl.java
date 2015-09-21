package com.dspl.malkey.dao;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Faccessory;
import com.dspl.malkey.domain.Faccrate;
import com.dspl.malkey.service.FileUploadSRV;
import com.dspl.malkey.service.UserInfoSRV;

import flex.messaging.FlexContext;

public class FaccessoryDAOImpl implements FaccessoryDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Resource(name="fileUploadSRV")
	FileUploadSRV fileUploadSRV;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Transactional(readOnly=true)
	@Override
	public List<Faccessory> List(int startIndex, int numItems) {
		return em.createNamedQuery("FaccessoryListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional(readOnly=true)
	@Override
	public List<Faccessory> ListAll() {
		return em.createNamedQuery("FaccessoryListAll").getResultList();
	}

	@Transactional(readOnly=true)
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Faccessory").getSingleResult());
	}
	
	@Transactional(readOnly=true)
	@Override
	public Faccessory findByID(String accid) {
		try{
			return em.find(Faccessory.class, accid);
		}catch(Exception e){
			System.out.println("Faccessory findById: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

//	@Transactional(readOnly=false)
//	@Override
//	public void create(Faccessory faccessory) {
//		em.persist(faccessory);
//	}

//	@Transactional(readOnly=false)
//	@Override
//	public void removeByID(String accid) {
//		em.remove(em.find(Faccessory.class, accid));
//	}

//	@Transactional(readOnly=false)
//	@Override
//	public void update(Faccessory faccessory) {
//		em.merge(faccessory);
//	}

	@Transactional
	@Override
	public Boolean create(Faccessory faccessory, List<Faccrate> faccrates) {
		try {
			String curUser=userInfoSRV.getUser();
			String curMachine=userInfoSRV.getMachineName();
			Calendar curDate = Calendar.getInstance();
			curDate=fmaintenanceDAO.resetTime(curDate);
			
			Boolean lbFileUploaded=false;
			String targetFolder = "/image/accessory/";
			String serverName = "/resource";//FlexContext.getHttpRequest().getContextPath();

			String lsImageURL = serverName + targetFolder;
			
			if(faccessory.getAccimagedata()!=null){
				if(!faccessory.getImage().toString().trim().equals("")){
					lbFileUploaded = fileUploadSRV.uploadFileToServer(faccessory.getImage(),faccessory.getAccimagedata(), targetFolder);
					if(lbFileUploaded==false)
						return false;
					else
						faccessory.setImage((lsImageURL + faccessory.getImage()));
				}else
					return false;
			}
			
			//Persists FAccessory
			faccessory.setAdddate(curDate);
			faccessory.setAdduser(curUser);
			faccessory.setAddmach(curMachine);
			em.persist(faccessory);
			em.flush();
			
			for(int a=0;a<faccrates.size();a++){
				Faccrate faccrate=faccrates.get(a);
				faccrate.setAdddate(curDate);
				faccrate.setAdduser(curUser);
				faccrate.setAddmach(curMachine);
				//Persists FAccessory
				em.persist(faccrate);
				em.flush();
			}
			return true;
		} catch (Exception e) {
			System.out.println("Faccessory create: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	@Override
	public List<Faccessory> listAccessories() {
		try {
			String query ="SELECT accid,description,make,model FROM faccessory ORDER BY accid";
			return em.createNativeQuery(query, "accList").getResultList();
		} catch (Exception e) {
			System.out.println("Faccessory listAccessories: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public Boolean removeByID(String accid) {
		try {
			String query="DELETE FROM faccrate WHERE accid='"+ accid +"'";
			em.createNativeQuery(query).executeUpdate();
			em.flush();
			em.remove(em.find(Faccessory.class, accid));
			em.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Faccessory removeById: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	@Override
	public Boolean update(Faccessory faccessory, List<Faccrate> faccrates) {
		try {
			String curUser=userInfoSRV.getUser();
			String curMachine=userInfoSRV.getMachineName();
			Calendar curDate = Calendar.getInstance();
			curDate=fmaintenanceDAO.resetTime(curDate);
			
			Boolean lbFileUploaded=false;
			String targetFolder = "/image/accessory/";
			String serverName = "/resource";//FlexContext.getHttpRequest().getContextPath();

			String lsImageURL = serverName + targetFolder;
			
			if(faccessory.getAccimagedata()!=null){
				if(!faccessory.getImage().toString().trim().equals("")){
					lbFileUploaded = fileUploadSRV.uploadFileToServer(faccessory.getImage(),faccessory.getAccimagedata(), targetFolder);
					if(lbFileUploaded==false)
						return false;
					else
						faccessory.setImage((lsImageURL + faccessory.getImage()));
				}else
					return false;
			}
			
			//Merge FAccessory
			faccessory.setAdddate(curDate);
			faccessory.setAdduser(curUser);
			faccessory.setAddmach(curMachine);
			em.merge(faccessory);
			em.flush();
			
			//Delete Existing Records 
			String query="DELETE FROM faccrate WHERE accid='"+ faccessory.getAccid() +"'";
			em.createNativeQuery(query).executeUpdate();
//			em.flush();
			
			for(int a=0;a<faccrates.size();a++){
				Faccrate faccrate=faccrates.get(a);
				faccrate.setAdddate(curDate);
				faccrate.setAdduser(curUser);
				faccrate.setAddmach(curMachine);
				//Persists FAccessory
				em.persist(faccrate);
				em.flush();
			}
			return true;
		} catch (Exception e) {
			System.out.println("Faccessory create: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}
