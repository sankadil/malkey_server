package com.dspl.malkey.dao;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fdriverrate;
import com.dspl.malkey.domain.FdriverratePK;
import com.dspl.malkey.service.UserInfoSRV;

public class FdriverrateDAOImpl implements FdriverrateDAO {

	@PersistenceContext
	EntityManager em;

	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Transactional
	@Override
	public List<Fdriverrate> List(int startIndex, int numItems) {
		return em.createNamedQuery("FdriverrateListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fdriverrate> ListAll() {
		return em.createNamedQuery("FdriverrateListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fdriverrate").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fdriverrate fdriverrate) {
		em.persist(fdriverrate);
	}

	@Transactional
	@Override
	public Fdriverrate findByID(FdriverratePK fdriverratePK) {
		return em.find(Fdriverrate.class, fdriverratePK);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(FdriverratePK fdriverratePK) {
		em.remove(em.find(Fdriverrate.class, fdriverratePK));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fdriverrate fdriverrate) {
		em.merge(fdriverrate);
	}

	@Transactional
	@Override
	public Boolean update(List<Fdriverrate> fdriverrates) {
		try {
			//Delete Existing Records
			String query="DELETE FROM fdriverrate";
			em.createNativeQuery(query).executeUpdate();
			em.flush();
			
			//Persists FDriverRates
			String curUser=userInfoSRV.getUser();
			String curMachine=userInfoSRV.getMachineName();
			Calendar curDate = Calendar.getInstance();
			curDate=fmaintenanceDAO.resetTime(curDate);
			Fdriverrate fdriverrate;
			for(int a=0;a<fdriverrates.size();a++){
				fdriverrate=fdriverrates.get(a);
				fdriverrate.setAdduser(curUser);
				fdriverrate.setAddmach(curMachine);
				fdriverrate.setAdddate(curDate);
				em.persist(fdriverrate);
				em.flush();
			}
			return true;
		} catch (Exception e) {
			System.out.println("fdriverrate: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

}
