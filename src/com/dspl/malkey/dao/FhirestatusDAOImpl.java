package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fhirestatus;

public class FhirestatusDAOImpl implements FhirestatusDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fhirestatus> List(int startIndex, int numItems) {
		return em.createNamedQuery("FhirestatusListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fhirestatus> ListAll() {
		
		List<Fhirestatus> l=em.createNamedQuery("FhirestatusListAll").getResultList();
		return l;
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fhirestatus").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fhirestatus fhirestatus) {
		em.persist(fhirestatus);
	}

	@Transactional
	@Override
	public Fhirestatus findByID(String hirestsid) {
		return em.find(Fhirestatus.class, hirestsid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String hirestsid) {
		em.remove(em.find(Fhirestatus.class, hirestsid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fhirestatus fhirestatus) {
		em.merge(fhirestatus);
	}

}
