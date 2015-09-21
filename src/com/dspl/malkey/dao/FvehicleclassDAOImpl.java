package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fvehicleclass;

public class FvehicleclassDAOImpl implements FvehicleclassDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fvehicleclass> List(int startIndex, int numItems) {
		return em.createNamedQuery("FvehicleclassListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fvehicleclass> ListAll() {
		return em.createNamedQuery("FvehicleclassListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fvehicleclass").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fvehicleclass fvehicleclass) {
		em.persist(fvehicleclass);
	}

	@Transactional
	@Override
	public Fvehicleclass findByID(String vehiclassid) {
		return em.find(Fvehicleclass.class, vehiclassid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String vehiclassid) {
		em.remove(em.find(Fvehicleclass.class, vehiclassid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fvehicleclass fvehicleclass) {
		em.merge(fvehicleclass);
	}

}
