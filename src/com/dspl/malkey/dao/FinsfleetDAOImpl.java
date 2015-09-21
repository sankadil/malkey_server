package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Finsfleet;

public class FinsfleetDAOImpl implements FinsfleetDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Finsfleet> List(int startIndex, int numItems) {
		return em.createNamedQuery("FinsfleetListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Finsfleet> ListAll() {
		return em.createNamedQuery("FinsfleetListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Finsfleet").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Finsfleet finsfleet) {
		em.persist(finsfleet);
	}

	@Transactional
	@Override
	public Finsfleet findByID(String fleetid) {
		return em.find(Finsfleet.class, fleetid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String fleetid) {
		em.remove(em.find(Finsfleet.class, fleetid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Finsfleet finsfleet) {
		em.merge(finsfleet);
	}

}
