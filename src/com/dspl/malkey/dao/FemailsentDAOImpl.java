package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Femailsent;

public class FemailsentDAOImpl implements FemailsentDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Femailsent> List(int startIndex, int numItems) {
		return em.createNamedQuery("FemailsentListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Femailsent> ListAll() {
		return em.createNamedQuery("FemailsentListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Femailsent").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Femailsent femailsent) {
		em.persist(femailsent);
	}

	@Transactional
	@Override
	public Femailsent findByID(String emailid) {
		return em.find(Femailsent.class, emailid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String emailid) {
		em.remove(em.find(Femailsent.class, emailid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Femailsent femailsent) {
		em.merge(femailsent);
	}

}
