package com.dspl.malkey.dao;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Flocation;
import com.dspl.malkey.service.UserInfoSRV;

public class FlocationDAOImpl implements FlocationDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Transactional
	@Override
	public List<Flocation> List(int startIndex, int numItems) {
		return em.createNamedQuery("FlocationListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Flocation> ListAll() {
		return em.createNamedQuery("FlocationListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Flocation").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Flocation flocation) {
		Calendar curDate = Calendar.getInstance();
		curDate=fmaintenanceDAO.resetTime(curDate);
		flocation.setAdduser(userInfoSRV.getUser());
		flocation.setAddmach(userInfoSRV.getMachineName());
		flocation.setAdddate(curDate);
		em.persist(flocation);
	}

	@Transactional
	@Override
	public Flocation findByID(String locationid) {
		return em.find(Flocation.class, locationid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String locationid) {
		em.remove(em.find(Flocation.class, locationid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Flocation flocation) {
		Calendar curDate = Calendar.getInstance();
		curDate=fmaintenanceDAO.resetTime(curDate);
		flocation.setAdduser(userInfoSRV.getUser());
		flocation.setAddmach(userInfoSRV.getMachineName());
		flocation.setAdddate(curDate);
		em.merge(flocation);
	}

	@Transactional
	@Override
	public List<Flocation> ListCheckIn(String stChkIn) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("FlocationCheckInList").setParameter("chkin",stChkIn).getResultList();
	}

}
