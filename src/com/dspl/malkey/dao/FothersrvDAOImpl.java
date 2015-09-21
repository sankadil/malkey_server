package com.dspl.malkey.dao;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Faccessory;
import com.dspl.malkey.domain.Faccrate;
import com.dspl.malkey.domain.Fothersrv;
import com.dspl.malkey.domain.Fothersrvrate;
import com.dspl.malkey.service.UserInfoSRV;

import flex.messaging.FlexContext;

public class FothersrvDAOImpl implements FothersrvDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Transactional
	@Override
	public List<Fothersrv> List(int startIndex, int numItems) {
		return em.createNamedQuery("FothersrvListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fothersrv> ListAll() {
		return em.createNamedQuery("FothersrvListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fothersrv").getSingleResult());
	}

//	@Transactional(readOnly=false)
//	@Override
//	public void create(Fothersrv fothersrv) {
//		em.persist(fothersrv);
//	}

	@Transactional
	@Override
	public Fothersrv findByID(String srvid) {
		return em.find(Fothersrv.class, srvid);
	}


//	@Transactional(readOnly=false)
//	@Override
//	public void removeByID(String srvid) {
//		em.remove(em.find(Fothersrv.class, srvid));
//	}

//	@Transactional(readOnly=false)
//	@Override
//	public void udpate(Fothersrv fothersrv) {
//		em.merge(fothersrv);
//	}

	@Transactional
	@Override
	public Boolean create(Fothersrv fothersrv, List<Fothersrvrate> fothersrvrates) {
		try {
			String curUser=userInfoSRV.getUser();
			String curMachine=userInfoSRV.getMachineName();
			Calendar curDate = Calendar.getInstance();
			curDate=fmaintenanceDAO.resetTime(curDate);
		
			//Persist FOtherService
			fothersrv.setAdddate(curDate);
			fothersrv.setAdduser(curUser);
			fothersrv.setAddmach(curMachine);
			em.persist(fothersrv);
			em.flush();
			
			for(int a=0;a<fothersrvrates.size();a++){
				Fothersrvrate fothersrvrate=fothersrvrates.get(a);
				fothersrvrate.setAdddate(curDate);
				fothersrvrate.setAdduser(curUser);
				fothersrvrate.setAddmach(curMachine);
				//Persists FOtherServiceRate
				em.persist(fothersrvrate);
				em.flush();
			}
			return true;
		} catch (Exception e) {
			System.out.println("Fothersrv create: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	@Override
	public List<Fothersrv> listServices() {
		try {
			String query ="SELECT srvid,description FROM fothersrv ORDER BY srvid";
			return em.createNativeQuery(query, "srvList").getResultList();
		} catch (Exception e) {
			System.out.println("Fothersrv listServices: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public Boolean removeByID(String srvid) {
		try {
			String query="DELETE FROM fothersrvrate WHERE srvid='"+ srvid +"'";
			em.createNativeQuery(query).executeUpdate();
			em.flush();
			em.remove(em.find(Fothersrv.class, srvid));
			em.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Fothersrv removeById: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	@Override
	public Boolean update(Fothersrv fothersrv, List<Fothersrvrate> fothersrvrates) {
		try {
			String curUser=userInfoSRV.getUser();
			String curMachine=userInfoSRV.getMachineName();
			Calendar curDate = Calendar.getInstance();
			curDate=fmaintenanceDAO.resetTime(curDate);
		
			//Merge FOtherService
			fothersrv.setAdddate(curDate);
			fothersrv.setAdduser(curUser);
			fothersrv.setAddmach(curMachine);
			em.merge(fothersrv);
			em.flush();
			
			//Delete Existing Records 
			String query="DELETE FROM fothersrvrate WHERE srvid='"+ fothersrv.getSrvid() +"'";
			em.createNativeQuery(query).executeUpdate();
			
			for(int a=0;a<fothersrvrates.size();a++){
				Fothersrvrate fothersrvrate=fothersrvrates.get(a);
				fothersrvrate.setAdddate(curDate);
				fothersrvrate.setAdduser(curUser);
				fothersrvrate.setAddmach(curMachine);
				//Persists FOtherServiceRate
				em.persist(fothersrvrate);
				em.flush();
			}
			return true;
		} catch (Exception e) {
			System.out.println("Fothersrv create: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}
