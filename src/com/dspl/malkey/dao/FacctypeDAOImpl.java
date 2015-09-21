package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Facctype;

public class FacctypeDAOImpl implements FacctypeDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Facctype> List(int startIndex, int numItems) {
		return em.createNamedQuery("FacctypeListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Facctype> ListAll() {
		return em.createNamedQuery("FacctypeListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Facctype").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Facctype facctype) {
		em.persist(facctype);
	}

	@Transactional
	@Override
	public Facctype findByID(String acctypeid) {
		return em.find(Facctype.class, acctypeid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String acctypeid) {
		em.remove(em.find(Facctype.class, acctypeid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Facctype facctype) {
		em.merge(facctype);
	}

}
