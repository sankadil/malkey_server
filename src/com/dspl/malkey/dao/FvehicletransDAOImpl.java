package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fvehicletrans;

public class FvehicletransDAOImpl implements FvehicletransDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fvehicletrans> List(int startIndex, int numItems) {
		return em.createNamedQuery("FvehicletransListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fvehicletrans> ListAll() {
		return em.createNamedQuery("FvehicletransListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fvehicletrans").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fvehicletrans fvehicletrans) {
		em.persist(fvehicletrans);
	}

	@Transactional
	@Override
	public Fvehicletrans findByID(String vehitransid) {
		return em.find(Fvehicletrans.class, vehitransid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String vehitransid) {
		em.remove(em.find(Fvehicletrans.class, vehitransid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fvehicletrans fvehicletrans) {
		em.merge(fvehicletrans);
	}

}
