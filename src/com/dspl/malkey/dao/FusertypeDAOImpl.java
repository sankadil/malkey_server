package com.dspl.malkey.dao;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fpass;
import com.dspl.malkey.domain.Fusertype;
import com.dspl.malkey.service.UserInfoSRV;

public class FusertypeDAOImpl implements FusertypeDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Transactional(readOnly=true)
	@Override
	public int count() {
		int i = (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fusertype").getSingleResult());
		System.out.println("count :"+i);
		return i;
	}
	
	private Fusertype setEnvVar(Fusertype fusertype){
		Calendar curDate = Calendar.getInstance();
		curDate=fmaintenanceDAO.resetTime(curDate);
		fusertype.setAdduser(userInfoSRV.getUser());
		fusertype.setAddmach(userInfoSRV.getMachineName());
		fusertype.setAdddate(curDate);
		return fusertype;
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fusertype fusertype) 
	{
		fusertype=setEnvVar(fusertype);
		em.persist(fusertype);
	}

	@Transactional(readOnly=true)
	@Override
	public Fusertype findById(String usertypeid) {
		return em.find(Fusertype.class, usertypeid);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Fusertype> list(int startIndex, int numItems) {
		return em.createNamedQuery("FusertypeListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional(readOnly=true)
	@Override
	public List<Fusertype> listAll() {
		return em.createNamedQuery("FusertypeListAll").getResultList();
	}

	@Transactional(readOnly=false)
	@Override
	public void removeById(String usertypeid)
	{
		int i = em.createNativeQuery("DELETE FROM faccessrules WHERE usertypeid='" + usertypeid + "'").executeUpdate();
		em.flush();
		em.remove(em.find(Fusertype.class, usertypeid));
	}

	@Transactional(readOnly=false)
	@Override
	public void update(Fusertype fusertype) 
	{
		Fusertype fut = em.find(Fusertype.class,fusertype.getUsertypeid());
		fut.setUsertypeid(fusertype.getUsertypeid());
		fut=setEnvVar(fut);
		em.merge(fut);
	}
	
	@Transactional(readOnly=true)
	@Override
	public int countInAR(String UserTypeId) 
	{
		try
		{
			int i = (Integer)(em.createNativeQuery("SELECT COUNT(*) FROM Fpass WHERE levelu='" + UserTypeId + "'").getSingleResult());
			return i;
		}
		catch(Exception ex)
		{
			return -1;
		}
	}
}
