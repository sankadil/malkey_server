package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fresrates;
import com.dspl.malkey.domain.FresratesPK;

public class FresratesDAOImpl implements FresratesDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fresrates> List(int startIndex, int numItems) {
		return em.createNamedQuery("FresratesListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fresrates> ListAll() {
		return em.createNamedQuery("FresratesListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fresrates").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fresrates fresrates) {
		em.persist(fresrates);
	}

	@Transactional
	@Override
	public Fresrates findByID(FresratesPK fresratesPK) {
		return em.find(Fresrates.class, fresratesPK);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(FresratesPK fresratesPK) {
		em.remove(em.find(Fresrates.class, fresratesPK));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fresrates fresrates) {
		em.merge(fresrates);
	}

}
