package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fresvehicle;
import com.dspl.malkey.domain.FresvehiclePK;

public class FresvehicleDAOImpl implements FresvehicleDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fresvehicle> List(int startIndex, int numItems) {
		em.flush();
		return em.createNamedQuery("FresvehicleListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fresvehicle> ListAll() {
		em.flush();
		return em.createNamedQuery("FresvehicleListAll").getResultList();
	}
	
	@Transactional
	@Override
	public List<Fresvehicle> listByResNo(String resno) {
		em.flush();
		return em.createNamedQuery("FresvehicleListByResNo").setParameter("resno", resno).getResultList();
	}

	@Transactional
	@Override
	public int count() {
		em.flush();
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fresvehicle").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fresvehicle fresvehicle) {
		em.flush();
		em.persist(fresvehicle);
	}

	@Transactional
	@Override
	public Fresvehicle findByID(FresvehiclePK fresvehiclePK) {
		em.flush();
		return em.find(Fresvehicle.class, fresvehiclePK);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(FresvehiclePK fresvehiclePK) {
		em.flush();
		em.remove(em.find(Fresvehicle.class, fresvehiclePK));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fresvehicle fresvehicle) {
		em.flush();
		em.merge(fresvehicle);
	}

}
