package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Ffueltype;

public class FfueltypeDAOImpl implements FfueltypeDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Ffueltype> List(int startIndex, int numItems) {
		return em.createNamedQuery("FfueltypeListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Ffueltype> ListAll() {
		return em.createNamedQuery("FfueltypeListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Ffueltype").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Ffueltype ffueltype) {
		em.persist(ffueltype);
	}

	@Transactional
	@Override
	public Ffueltype findByID(String fueltypeid) {
		return em.find(Ffueltype.class, fueltypeid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String fueltypeid) {
		em.remove(em.find(Ffueltype.class, fueltypeid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Ffueltype ffueltype) {
		em.merge(ffueltype);
	}

}
