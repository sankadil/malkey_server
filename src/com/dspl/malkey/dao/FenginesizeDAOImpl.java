package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fenginesize;

public class FenginesizeDAOImpl implements FenginesizeDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fenginesize> List(int startIndex, int numItems) {
		return em.createNamedQuery("FenginesizeListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fenginesize> ListAll() {
		return em.createNamedQuery("FenginesizeListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fenginesize").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fenginesize fenginesize) {
		em.persist(fenginesize);
	}

	@Transactional
	@Override
	public Fenginesize findByID(String engsizeid) {
		return em.find(Fenginesize.class, engsizeid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String engsizeid) {
		em.remove(em.find(Fenginesize.class, engsizeid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fenginesize fenginesize) {
		em.merge(fenginesize);
	}

}
