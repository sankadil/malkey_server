package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Ftax;

public class FtaxDAOImpl implements FtaxDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional(readOnly=true)	
	@Override
	public int count() {
		int i=(Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Ftax").getSingleResult());
		return i;
	}

	@Transactional @Override
	public void create(Ftax ftax) {
		em.persist(ftax);
	}

	@Transactional @Override
	public Ftax findById(String taxcode) {
		return em.find(Ftax.class, taxcode);
	}

	@Transactional @Override
	public List<Ftax> list(int startIndex, int numItems) {
		return em.createNamedQuery("FtaxListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional @Override
	public List<Ftax> listAll() {
		return em.createNamedQuery("FtaxListAll").getResultList();
	}

	@Transactional @Override
	public void removeById(String taxcode) {
		em.remove(em.find(Ftax.class, taxcode));
	}

	@Transactional @Override
	public void update(Ftax ftax) {
		em.merge(ftax);
	}

}
