package com.dspl.malkey.dao;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Faddcharge;
import com.dspl.malkey.service.UserInfoSRV;

public class FaddchargeDAOImpl implements FaddchargeDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Transactional
	@Override
	public List<Faddcharge> List(int startIndex, int numItems) {
		return em.createNamedQuery("FaddchargeListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Faddcharge> ListAll() {
		return em.createNamedQuery("FaddchargeListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Faddcharge").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public Boolean create(Faddcharge faddcharge) {
		try {
			String curUser=userInfoSRV.getUser();
			String curMachine=userInfoSRV.getMachineName();
			Calendar curDate = Calendar.getInstance();
			curDate=fmaintenanceDAO.resetTime(curDate);
			faddcharge.setAdduser(curUser);
			faddcharge.setAddmach(curMachine);
			faddcharge.setAdddate(curDate);
			em.persist(faddcharge);
			em.flush();
			return true;
		} catch (Exception e) {
			System.out.println("faddcharge create: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public Faddcharge findByID(String addchargeid) {
		return em.find(Faddcharge.class, addchargeid);
	}

	@Transactional(readOnly=false)
	@Override
	public Boolean removeByID(String addchargeid) {
		try{
			em.remove(em.find(Faddcharge.class, addchargeid));
			return true;
		}catch (Exception e) {
			System.out.println("Faddcharge update: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Transactional(readOnly=false)
	@Override
	public Boolean update(Faddcharge faddcharge) {
		try {
			String curUser=userInfoSRV.getUser();
			String curMachine=userInfoSRV.getMachineName();
			Calendar curDate = Calendar.getInstance();
			curDate=fmaintenanceDAO.resetTime(curDate);
			faddcharge.setAdduser(curUser);
			faddcharge.setAddmach(curMachine);
			faddcharge.setAdddate(curDate);
			em.merge(faddcharge);
			em.flush();
			return true;
		} catch (Exception e) {
			System.out.println("faddchrage update: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

}
