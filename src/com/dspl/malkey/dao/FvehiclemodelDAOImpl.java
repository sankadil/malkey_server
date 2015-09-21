package com.dspl.malkey.dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fvehiclemake;
import com.dspl.malkey.domain.Fvehiclemodel;
import com.dspl.malkey.domain.FvehiclemodelPK;
import com.dspl.malkey.service.UserInfoSRV;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class FvehiclemodelDAOImpl implements FvehiclemodelDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Resource(name="fmasterlistDAO")
	FmasterlistDAO fmasterlistDAO;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Transactional
	@Override
	public List<Fvehiclemodel> List(int startIndex, int numItems) {
		return em.createNamedQuery("FvehiclemodelListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fvehiclemodel> ListAll() {
		return em.createNamedQuery("FvehiclemodelListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fvehiclemodel").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public Boolean create(Fvehiclemodel fvehiclemodel) {
		try {
			fvehiclemodel=setUserEnvDet(fvehiclemodel);
			em.persist(fvehiclemodel);
			return true;
		} catch (Exception e) {
			System.out.println("Fvehiclemode create: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	@Override
	public Fvehiclemodel findByID(FvehiclemodelPK fvehiclemodelPK) {
		return em.find(Fvehiclemodel.class, fvehiclemodelPK);
	}

//	@Transactional(readOnly=false)
//	@Override
//	public Boolean removeByID(FvehiclemodelPK fvehiclemodelPK) {
//		int Cnt=fmasterlistDAO.isAssociated("fvehicle", "vehimodelid", fvehiclemodelPK.getVehimodelid());
//		if(Cnt>0){
//			throw new RuntimeException("9999");
//		}else if(Cnt<0)
//			return false;
//		else if(Cnt==0){
//			try{
//				em.remove(em.find(Fvehiclemodel.class, fvehiclemodelPK));
//				return true;
//			}catch(Exception e){
//				System.out.println("Fvehiclemode removeById: " + e.getMessage());
//				e.printStackTrace();
//			}
//		}
//		return false;
//	}
	
	
	@Transactional(readOnly=false)
	@Override
	public Boolean removeByID(FvehiclemodelPK fvehiclemodelPK){
		Fvehiclemodel fvehiclemodel = em.find(Fvehiclemodel.class, fvehiclemodelPK);
		if(fvehiclemodel==null)
			System.out.println("null object found");
		else{
			em.remove(fvehiclemodel);
			return true;
		}
		return null;
	}

	@Transactional(readOnly=false)
	@Override
	public Boolean update(Fvehiclemodel fvehiclemodel) {
		try{
			fvehiclemodel=setUserEnvDet(fvehiclemodel);
			String query="UPDATE fvehiclemodel SET vehimakeid='" + fvehiclemodel.getId().getVehimakeid() + "'";
			query += " ,description='" + fvehiclemodel.getDescription() + "'";
			query += " ,addmach='" + fvehiclemodel.getAddmach() + "'";
			query += " ,adduser='" + fvehiclemodel.getAdduser() + "'";
			query += " ,adddate='" + fvehiclemodel.getAdddate().get(Calendar.YEAR) + "." + (fvehiclemodel.getAdddate().get(Calendar.MONTH)+1) + "." + fvehiclemodel.getAdddate().get(Calendar.DATE) + "'";
			query += " WHERE vehimodelid='" + fvehiclemodel.getId().getVehimodelid() + "'";
			
			int val=em.createNativeQuery(query).executeUpdate();
			//em.merge(fvehiclemodel);
			System.out.println("Fvehiclemode update");
			if(val>0)				
				return true;
		}catch(Exception e){
			System.out.println("Fvehiclemode update: " + e.getMessage());
		}
		return false;
	}
	
	private Fvehiclemodel setUserEnvDet(Fvehiclemodel fvehiclemodel){
		String curUser=userInfoSRV.getUser();
		String curMachine=userInfoSRV.getMachineName();
		Calendar curDate = Calendar.getInstance();
		curDate=fmaintenanceDAO.resetTime(curDate);
		fvehiclemodel.setAdduser(curUser);
		fvehiclemodel.setAddmach(curMachine);
		fvehiclemodel.setAdddate(curDate);
		return fvehiclemodel;
	}
}
