package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fresvehicledamage;
import com.dspl.malkey.domain.Fvehicledamage;

public class FresvehicledamageDAOImpl implements FresvehicledamageDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fresvehicledamage> List(int startIndex, int numItems) {
		return em.createNamedQuery("FresvehicledamageListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fresvehicledamage> ListAll() {
		return em.createNamedQuery("FresvehicledamageListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fresvehicledamage").getSingleResult());
	}

	@Transactional
	@Override
	public void create(Fresvehicledamage fvehicledamage) {
		em.persist(fvehicledamage);
	}

	@Transactional
	@Override
	public Fresvehicledamage findByID(String vehidamageid) {
		return em.find(Fresvehicledamage.class, vehidamageid);
	}

	@Transactional
	@Override
	public void removeByID(String vehidamageid) {
		em.remove(em.find(Fvehicledamage.class, vehidamageid));
	}

	@Transactional
	@Override
	public void update(Fresvehicledamage fvehicledamage) {
		em.merge(fvehicledamage);
	}

	@Transactional
	@Override
	public List<Fresvehicledamage> listByRegNo(String regNo,String resNo) {
		return em.createNamedQuery("FresvehicledamageListByRegNo").setParameter("regno", regNo).setParameter("resno", resNo).getResultList();
	}

}
