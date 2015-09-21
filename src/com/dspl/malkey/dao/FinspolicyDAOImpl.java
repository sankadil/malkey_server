package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Finspolicy;

public class FinspolicyDAOImpl implements FinspolicyDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Finspolicy> List(int startIndex, int numItems) {
		return em.createNamedQuery("FinspolicyListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();	
	}

	@Transactional
	@Override
	public List<Finspolicy> ListAll() {
		return em.createNamedQuery("FinspolicyListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Finspolicy").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Finspolicy finspolicy) {
		em.persist(finspolicy);
	}

	@Transactional
	@Override
	public Finspolicy findByID(String policyid) {
		return em.find(Finspolicy.class, policyid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String policyid) {
		em.remove(em.find(Finspolicy.class, policyid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Finspolicy finspolicy) {
		em.merge(finspolicy);
	}

}
