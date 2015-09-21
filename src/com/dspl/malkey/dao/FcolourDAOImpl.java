package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fcolour;

public class FcolourDAOImpl implements FcolourDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fcolour> List(int startIndex, int numItems) {
		return em.createNamedQuery("FcolourListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fcolour> ListAll() {
		return em.createNamedQuery("FcolourListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fcolour").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fcolour fcolour) {
		em.persist(fcolour);
	}

	@Transactional
	@Override
	public Fcolour findByID(String colourid) {
		return em.find(Fcolour.class, colourid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String colourid) {
		em.remove(em.find(Fcolour.class, colourid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fcolour fcolour) {
		em.merge(fcolour);
	}

}
