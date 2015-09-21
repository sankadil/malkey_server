package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Femployeetype;

public class FemployeetypeDAOImpl implements FemployeetypeDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Femployeetype> List(int startIndex, int numItems) {
		return em.createNamedQuery("FemployeetypeListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Femployeetype> ListAll() {
		return em.createNamedQuery("FemployeetypeListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Femployeetype").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Femployeetype femployeetype) {
		// TODO Auto-generated method stub

	}

	@Transactional
	@Override
	public Femployeetype findByID(String emptype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String emptype) {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Femployeetype femployeetype) {
		// TODO Auto-generated method stub

	}

}
