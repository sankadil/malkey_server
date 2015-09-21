package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fclienttype;

public class FclienttypeDAOImpl implements FclienttypeDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fclienttype> List(int startIndex, int numItems) {
		return em.createNamedQuery("FclienttypeListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fclienttype> ListAll() {
		return em.createNamedQuery("FclienttypeListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fclienttype").getSingleResult());
	}


	@Transactional(readOnly=false)
	@Override
	public void create(Fclienttype fclienttype) {
		em.persist(fclienttype);
	}

	@Transactional
	@Override
	public Fclienttype findByID(String clienttype) {
		return em.find(Fclienttype.class, clienttype);
	}


	@Transactional(readOnly=false)
	@Override
	public void removeByID(String clienttype) {
		em.remove(em.find(Fclienttype.class, clienttype));
	}


	@Transactional(readOnly=false)
	@Override
	public void udpate(Fclienttype fclienttype) {
		em.merge(fclienttype);
	}

}
