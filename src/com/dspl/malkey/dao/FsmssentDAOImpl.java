package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fsmssent;

public class FsmssentDAOImpl implements FsmssentDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fsmssent> List(int startIndex, int numItems) {
		return em.createNamedQuery("FsmssentListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fsmssent> ListAll() {
		return em.createNamedQuery("FsmssentListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fsmssent").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fsmssent fsmssent) {
		em.persist(fsmssent);
	}

	@Transactional
	@Override
	public Fsmssent findByID(int recordid) {
		return em.find(Fsmssent.class, recordid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(int recordid) {
		em.remove(em.find(Fsmssent.class, recordid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fsmssent fsmssent) {
		em.merge(fsmssent);
	}

}
