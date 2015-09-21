package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fvehiclestatus;

public class FvehiclestatusDAOImpl implements FvehiclestatusDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fvehiclestatus> List(int startIndex, int numItems) {
		return em.createNamedQuery("FvehiclestatusListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fvehiclestatus> ListAll() {
		return em.createNamedQuery("FvehiclestatusListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fvehiclestatus").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fvehiclestatus fvehiclestatus) {
		em.persist(fvehiclestatus);
	}

	@Transactional
	@Override
	public Fvehiclestatus findByID(String vehistsid) {
		return em.find(Fvehiclestatus.class, vehistsid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String vehistsid) {
		em.remove(em.find(Fvehiclestatus.class, vehistsid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fvehiclestatus fvehiclestatus) {
		em.merge(fvehiclestatus);
	}

}
