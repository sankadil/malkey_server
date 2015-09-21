package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fclientdriver;

public class FclientdriverDAOImpl implements FclientdriverDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fclientdriver> List(int startIndex, int numItems) {
		return em.createNamedQuery("FclientdriverListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public java.util.List<Fclientdriver> ListAll() {
		try {
				return em.createNamedQuery("FclientdriverListAll").getResultList();
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
	public java.util.List<Fclientdriver> listByClientid(String debcode) {
		try {
			return em.createNamedQuery("fclientdriverlistbyclientid").setParameter("debcode", debcode).getResultList();
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
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fclientdriver").getSingleResult());
	}

	
	@Transactional(readOnly=false)
	@Override
	public void create(Fclientdriver fclientdriver) {
		em.persist(fclientdriver);
	}

	@Transactional
	@Override
	public Fclientdriver findByID(int recordid) {
		try {
			return em.find(Fclientdriver.class, recordid);
		} catch (Exception e) {
			return null;
		}
		finally{
			em.flush();
		}
		
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(int recordid) {
		em.remove(em.find(Fclientdriver.class, recordid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fclientdriver fclientdriver) {
		try {
			em.merge(fclientdriver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			em.flush();
		}
		
	}

	@Transactional(readOnly=false)
	@Override
	public List<Fclientdriver> findByDebcode(String debcode) {
		try {
			return em.createNamedQuery("FclientdriverFindByDebcode").setParameter("debcode", debcode).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			em.flush();
		}
	}

}
