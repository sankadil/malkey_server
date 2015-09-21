package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fvehiclemake;

public class FvehiclemakeDAOImpl implements FvehiclemakeDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fvehiclemake> List(int startIndex, int numItems) {
		return em.createNamedQuery("FvehiclemakeListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fvehiclemake> ListAll() {
		return em.createNamedQuery("FvehiclemakeListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fvehiclemake").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fvehiclemake fvehiclemake) {
		em.persist(fvehiclemake);
	}

	@Transactional
	@Override
	public Fvehiclemake findByID(String vehimakeid) {
		return em.find(Fvehiclemake.class, vehimakeid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String vehimakeid) {
		em.remove(em.find(Fvehiclemake.class, vehimakeid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fvehiclemake fvehiclemake) {
		em.merge(fvehiclemake);
	}

}
