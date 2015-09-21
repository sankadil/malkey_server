package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Faccrate;
import com.dspl.malkey.domain.FaccratePK;

public class FaccrateDAOImpl implements FaccrateDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Faccrate> List(int startIndex, int numItems) {
		return em.createNamedQuery("FaccrateListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Faccrate> ListAll() {
		return em.createNamedQuery("FaccrateListAll").getResultList();
	}
	
	@Transactional
	@Override
	public List<Faccrate> ListByClienttype(String clienttype) {
		return em.createNamedQuery("FaccrateListByClienttype").setParameter("clienttype",clienttype).getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Faccrate").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Faccrate faccrate) {
		em.persist(faccrate);
	}

	@Transactional
	@Override
	public Faccrate findByID(FaccratePK faccratePK) {
		return em.find(Faccrate.class, faccratePK);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(FaccratePK faccratePK) {
		em.remove(em.find(Faccrate.class, faccratePK));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Faccrate faccrate) {
		em.merge(faccrate);
	}
	
	@Transactional
	@Override
	public List<Faccrate> findByAccId(String accid) {
		try {
			return em.createNamedQuery("FaccrateFindByAccId").setParameter("accid", accid).getResultList();
		} catch (Exception e) {
			System.out.println("findByAccId: " + e.getMessage());
			e.printStackTrace();	
		}
		return null;
	}


}
