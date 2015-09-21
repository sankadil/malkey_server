package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fvehicleinventry;

public class FvehicleinventryDAOImpl implements FvehicleinventryDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fvehicleinventry> List(int startIndex, int numItems) {
		return em.createNamedQuery("FvehicleinventryListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fvehicleinventry> ListAll() {
		return em.createNamedQuery("FvehicleinventryListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fvehicleinventry").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fvehicleinventry fvehicleinventry) {
		em.persist(fvehicleinventry);
	}

	@Transactional
	@Override
	public Fvehicleinventry findByID(String regno) {
		return em.find(Fvehicleinventry.class, regno);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String regno) {
		em.remove(em.find(Fvehicleinventry.class, regno));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fvehicleinventry fvehicleinventry) {
		em.merge(fvehicleinventry);
	}

	@Transactional
	@Override
	public List<Fvehicleinventry> listByRegNo(String regNo) {
		return em.createNamedQuery("FvehicleinventryByRegNo").setParameter("regno", regNo).getResultList();
	}

}
