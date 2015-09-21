package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fpaytype;

public class FpaytypeDAOImpl implements FpaytypeDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fpaytype> List(int startIndex, int numItems) {
		return em.createNamedQuery("FpaytypeListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fpaytype> ListAll() {
		return em.createNamedQuery("FpaytypeListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fpaytype").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fpaytype fpaytype) {
		em.persist(fpaytype);
	}

	@Transactional
	@Override
	public Fpaytype findByID(String paytypeid) {
		return em.find(Fpaytype.class, paytypeid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String paytypeid) {
		em.remove(em.find(Fpaytype.class, paytypeid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fpaytype fpaytype) {
		em.merge(fpaytype);
	}

}
