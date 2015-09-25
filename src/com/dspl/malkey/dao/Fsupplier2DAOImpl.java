package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fsupplier2;

public class Fsupplier2DAOImpl implements Fsupplier2DAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fsupplier2> list(int startIndex, int numItems) {
		return em.createNamedQuery("Fsupplier2ListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fsupplier2> listAll() {
		return em.createNamedQuery("Fsupplier2ListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fsupplier2").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fsupplier2 fsupplier2) {
		em.persist(fsupplier2);
	}

	@Transactional
	@Override
	public Fsupplier2 findByID(String supcode) {
		return em.find(Fsupplier2.class, supcode);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String supcode) {
		em.remove(em.find(Fsupplier2.class, supcode));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fsupplier2 fsupplier2) {
		em.merge(fsupplier2);
	}

	@Transactional
	@Override
	public List<Fsupplier2> listBySupType(String supType) {
		Query query = em.createQuery("SELECT f FROM Fsupplier2 f WHERE f.typ=?1");
		query.setParameter(1, supType);
		return query.getResultList();
	}
}
