package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fleasecom;

public class FleasecomDAOImpl implements FleasecomDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override	
	public List<Fleasecom> List(int startIndex, int numItems) {
		return em.createNamedQuery("FleasecomListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fleasecom> ListAll() {
		return em.createNamedQuery("FleasecomListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fleasecom").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fleasecom fleasecom) {
		em.persist(fleasecom);
	}

	@Transactional
	@Override
	public Fleasecom findByID(String leasecomid) {
		return em.find(Fleasecom.class, leasecomid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String leasecomid) {
		em.remove(em.find(Fleasecom.class, leasecomid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fleasecom fleasecom) {
		em.merge(fleasecom);
	}

}
