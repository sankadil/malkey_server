package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fmainttype;

public class FmainttypeDAOImpl implements FmainttypeDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fmainttype> List(int startIndex, int numItems) {
		return em.createNamedQuery("FmainttypeListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fmainttype> ListAll() {
		return em.createNamedQuery("FmainttypeListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fmainttype").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fmainttype fmainttype) {
		em.persist(fmainttype);
	}

	@Transactional
	@Override
	public Fmainttype findByID(String typeid) {
		return em.find(Fmainttype.class, typeid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String typeid) {
		em.remove(em.find(Fmainttype.class, typeid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fmainttype fmainttype) {
		em.merge(fmainttype);
	}

}
