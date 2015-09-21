package com.dspl.malkey.dao;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Faddcharge;
import com.dspl.malkey.domain.Fratetype;
import com.dspl.malkey.service.UserInfoSRV;

public class FratetypeDAOImpl implements FratetypeDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Transactional
	@Override
	public List<Fratetype> list(int startIndex, int numItems) {
		return em.createNamedQuery("FratetypeListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fratetype> listAll() {
		return em.createNamedQuery("FratetypeListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fratetype").getSingleResult());
	}

//	@Transactional(readOnly=false)
//	@Override
//	public void create(Fratetype fratetype) {
//		em.persist(fratetype);
//	}

	@Transactional
	@Override
	public Fratetype findByID(String ratetype) {
		return em.find(Fratetype.class, ratetype);
	}


//	@Transactional(readOnly=false)
//	@Override
//	public void removeByID(String ratetype) {
//		em.remove(em.find(Fratetype.class, ratetype));
//	}
//
//	@Transactional(readOnly=false)
//	@Override
//	public void udpate(Fratetype fratetype) {
//		em.merge(fratetype);
//	}
	
	@Transactional
	@Override
	public Boolean create(Fratetype Fratetype) {
		try {
			String curUser=userInfoSRV.getUser();
			String curMachine=userInfoSRV.getMachineName();
			Calendar curDate = Calendar.getInstance();
			curDate=fmaintenanceDAO.resetTime(curDate);
			Fratetype.setAdduser(curUser);
			Fratetype.setAddmach(curMachine);
			Fratetype.setAdddate(curDate);
			em.persist(Fratetype);
			em.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Fratetype create: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public Boolean removeByID(String ratetype) {
		try{
			em.remove(em.find(Fratetype.class, ratetype));
			return true;
		}catch (Exception e) {
			System.out.println("Fratetype update: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	@Override
	public Boolean update(Fratetype fratetype) {
		try {
			String curUser=userInfoSRV.getUser();
			String curMachine=userInfoSRV.getMachineName();
			Calendar curDate = Calendar.getInstance();
			curDate=fmaintenanceDAO.resetTime(curDate);
			fratetype.setAdduser(curUser);
			fratetype.setAddmach(curMachine);
			fratetype.setAdddate(curDate);
			em.merge(fratetype);
			em.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Fratetype update: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

}
