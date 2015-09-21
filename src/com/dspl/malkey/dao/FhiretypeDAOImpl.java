package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fhiretype;

public class FhiretypeDAOImpl implements FhiretypeDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fhiretype> List(int startIndex, int numItems) {
		return em.createNamedQuery("FhiretypeListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fhiretype> ListAll() {
		return em.createNamedQuery("FhiretypeListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fhiretype").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fhiretype fhiretype) {
		em.persist(fhiretype);
	}

	@Transactional
	@Override
	public Fhiretype findByID(String hiretypeid) {
		return em.find(Fhiretype.class, hiretypeid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String hiretypeid) {
		em.remove(em.find(Fhiretype.class, hiretypeid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fhiretype fhiretype) {
		em.merge(fhiretype);
	}

}
