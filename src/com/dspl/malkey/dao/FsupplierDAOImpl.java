package com.dspl.malkey.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fsupplier;

public class FsupplierDAOImpl implements FsupplierDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fsupplier> list(int startIndex, int numItems) {
		return em.createNamedQuery("FsupplierListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fsupplier> listAll() {
		return em.createNamedQuery("FsupplierListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fsupplier2").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fsupplier fsupplier) {
		em.persist(fsupplier);
	}

	@Transactional
	@Override
	public Fsupplier findByID(String supcode) {
		return em.find(Fsupplier.class, supcode);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String supcode) {
		em.remove(em.find(Fsupplier.class, supcode));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fsupplier fsupplier) {
		em.merge(fsupplier);
	}

	@Transactional
	@Override
	public List<Fsupplier> listBySupType(String supType) {
		Query query = em.createQuery("SELECT f FROM Fsupplier f WHERE f.typ=?1");
		query.setParameter(1, supType);
		return query.getResultList();
	}
}
