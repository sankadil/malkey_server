package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fvehicledamage;

public class FvehicledamageDAOImpl implements FvehicledamageDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fvehicledamage> List(int startIndex, int numItems) {
		return em.createNamedQuery("FvehicledamageListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fvehicledamage> ListAll() {
		return em.createNamedQuery("FvehicledamageListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fvehicledamage").getSingleResult());
	}

	@Transactional
	@Override
	public void create(Fvehicledamage fvehicledamage) {
		em.persist(fvehicledamage);
	}

	@Transactional
	@Override
	public Fvehicledamage findByID(String vehidamageid) {
		return em.find(Fvehicledamage.class, vehidamageid);
	}

	@Transactional
	@Override
	public void removeByID(String vehidamageid) {
		em.remove(em.find(Fvehicledamage.class, vehidamageid));
	}

	@Transactional
	@Override
	public void udpate(Fvehicledamage fvehicledamage) {
		em.merge(fvehicledamage);
	}

	@Transactional
	@Override
	public List<Fvehicledamage> listByRegNo(String regNo) {
		return em.createNamedQuery("FvehicledamageListByRegNo").setParameter("regno", regNo).getResultList();
	}

}
