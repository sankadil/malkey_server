package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fvehiclepic;

public class FvehiclepicDAOImpl implements FvehiclepicDAO {
	
	@PersistenceContext
	EntityManager em;

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fvehiclepic").getSingleResult());
	}

	@Transactional
	@Override
	public void create(Fvehiclepic fvehiclepic) {
		em.persist(fvehiclepic);
	}

	@Transactional
	@Override
	public Fvehiclepic findByID(String regNo) {
		return em.find(Fvehiclepic.class, regNo);
	}

	@Transactional
	@Override
	public List<Fvehiclepic> list(int startIndex, int numItems) {
		return em.createNamedQuery("FvehiclepicListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fvehiclepic> listAll() {
		return em.createNamedQuery("FvehiclepicListAll").getResultList();
	}

	@Transactional
	@Override
	public List<Fvehiclepic> listByRegNo(String regNo) {
		return em.createNamedQuery("FvehiclepicListByRegNo").setParameter("regno", regNo).getResultList();
	}

	@Transactional
	@Override
	public void removeByID(String regNo) {
		em.remove(em.find(Fvehiclepic.class, regNo));
	}

	@Transactional
	@Override
	public void udpate(Fvehiclepic fvehiclepic) {
		em.merge(fvehiclepic);
	}

}
