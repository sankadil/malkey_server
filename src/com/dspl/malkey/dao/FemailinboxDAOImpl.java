package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Femailinbox;

public class FemailinboxDAOImpl implements FemailinboxDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Femailinbox> List(int startIndex, int numItems) {
		return em.createNamedQuery("FemailinboxListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Femailinbox> ListAll() {
		return em.createNamedQuery("FemailinboxListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Femailinbox").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Femailinbox femailinbox) {
		em.persist(femailinbox);
	}

	@Transactional
	@Override
	public Femailinbox findByID(String emailid) {
		return em.find(Femailinbox.class, emailid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String emailid) {
		em.remove(em.find(Femailinbox.class, emailid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Femailinbox femailinbox) {
		em.merge(femailinbox);
	}

}
