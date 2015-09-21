package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fanalysis;

public class FanalysisDAOImpl implements FanalysisDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fanalysis").getSingleResult());
	}

	@Transactional
	@Override
	public void create(Fanalysis fanalysis) {
		em.persist(fanalysis);
	}

	@Transactional
	@Override
	public Fanalysis findByID(String ancode) {
		return em.find(Fanalysis.class, ancode);
	}

	@Transactional
	@Override
	public List<Fanalysis> list(int startIndex, int numItems) {
		return em.createNamedQuery("FanalysisListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fanalysis> listByAnCodeOfVehicle(String ancode) {
		return em.createNamedQuery("FanalysisByAnCodeOfVehicle").setParameter("ancode", ancode).getResultList();
	}

	@Transactional
	@Override
	public void removeByID(String ancode) {
		em.remove(em.find(Fanalysis.class, ancode));
	}

	@Transactional
	@Override
	public void udpate(Fanalysis fanalysis) {
		em.merge(fanalysis);
	}

	@Override
	public List<Fanalysis> listAll() {
		return em.createNamedQuery("FanalysisListAll").getResultList();
	}

}
