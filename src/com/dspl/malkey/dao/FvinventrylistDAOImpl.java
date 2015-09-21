package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fvinventrylist;

public class FvinventrylistDAOImpl implements FvinventrylistDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fvinventrylist> List(int startIndex, int numItems) {
		return em.createNamedQuery("FvinventrylistListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fvinventrylist> ListAll() {
		return em.createNamedQuery("FvinventrylistListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fvinventrylist").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fvinventrylist fvinventrylist) {
		em.persist(fvinventrylist);
	}

	@Transactional
	@Override
	public Fvinventrylist findByID(String invid) {
		return em.find(Fvinventrylist.class, invid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String invid) {
		em.remove(em.find(Fvinventrylist.class, invid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fvinventrylist fvinventrylist) {
		em.merge(fvinventrylist);
	}

}
