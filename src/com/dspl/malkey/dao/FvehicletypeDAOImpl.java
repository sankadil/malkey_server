package com.dspl.malkey.dao;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fvehiclemodel;
import com.dspl.malkey.domain.Fvehicletype;
import com.dspl.malkey.service.FileUploadSRV;
import com.dspl.malkey.service.UserInfoSRV;

import flex.messaging.FlexContext;

public class FvehicletypeDAOImpl implements FvehicletypeDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Resource(name="fileUploadSRV")
	FileUploadSRV fileUploadSRV;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Transactional
	@Override
	public List<Fvehicletype> List(int startIndex, int numItems) {
		return em.createNamedQuery("FvehicletypeListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fvehicletype> ListAll() {
		return em.createNamedQuery("FvehicletypeListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fvehicletype").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public Boolean create(Fvehicletype fvehicletype) {
		try {
			Boolean lbFileUploaded=false;
			String targetFolder = "/image/vehiclediagram/";
			String serverName = "/resource";//FlexContext.getHttpRequest().getContextPath();

			String lsImageURL = serverName + targetFolder;
			
			if(fvehicletype.getVehicletypeimagedata()!=null){
				fvehicletype.setImage(fvehicletype.getVehitypeid()+".jpg");
				if(!fvehicletype.getImage().toString().trim().equals("")){
					lbFileUploaded = fileUploadSRV.uploadFileToServer(fvehicletype.getImage(),fvehicletype.getVehicletypeimagedata(), targetFolder);
					if(lbFileUploaded==false)
						return false;
					else
						fvehicletype.setImage((lsImageURL + fvehicletype.getImage()));
				}else
					return false;
			}
			
			fvehicletype=setUserEnvDet(fvehicletype);
			em.persist(fvehicletype);
			return true;
		} catch (Exception e) {
			System.out.println("Fvehicletype create: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	@Override
	public Fvehicletype findByID(String vehitypeid) {
		return em.find(Fvehicletype.class, vehitypeid);
	}

	@Transactional(readOnly=false)
	@Override
	public Boolean removeByID(String vehitypeid) {
		Fvehicletype fvehicletype = em.find(Fvehicletype.class, vehitypeid);
		if(fvehicletype==null)
			System.out.println("null object found");
		else{
			em.remove(fvehicletype);
			return true;
		}
		return null;
	}

	@Transactional(readOnly=false)
	@Override
	public Boolean update(Fvehicletype fvehicletype) {
		try{
			Boolean lbFileUploaded=false;
			String targetFolder = "/image/vehiclediagram/";
			String serverName = "/resource";//FlexContext.getHttpRequest().getContextPath();

			String lsImageURL = serverName + targetFolder;
			
			if(fvehicletype.getVehicletypeimagedata()!=null){
				fvehicletype.setImage(fvehicletype.getVehitypeid()+".jpg");
				if(!fvehicletype.getImage().toString().trim().equals("")){
					lbFileUploaded = fileUploadSRV.uploadFileToServer(fvehicletype.getImage(),fvehicletype.getVehicletypeimagedata(), targetFolder);
					if(lbFileUploaded==false)
						return false;
					else
						fvehicletype.setImage((lsImageURL + fvehicletype.getImage()));
				}else
					return false;
			}
			
			fvehicletype=setUserEnvDet(fvehicletype);
			em.merge(fvehicletype);
			return true;
		}catch(Exception e){
			System.out.println("Fvehicletype update: " + e.getMessage());
		}
		return false;
	}
	
	private Fvehicletype setUserEnvDet(Fvehicletype fvehicletype){
		String curUser=userInfoSRV.getUser();
		String curMachine=userInfoSRV.getMachineName();
		Calendar curDate = Calendar.getInstance();
		curDate=fmaintenanceDAO.resetTime(curDate);
		fvehicletype.setAdduser(curUser);
		fvehicletype.setAddmach(curMachine);
		fvehicletype.setAdddate(curDate);
		return fvehicletype;
	}

}
