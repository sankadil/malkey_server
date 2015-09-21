package com.dspl.malkey.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fresdriverrate;
import com.dspl.malkey.domain.FresdriverratePK;
import com.dspl.malkey.domain.Fresvehiclerate;

public class FresdriverrateDAOImpl implements FresdriverrateDAO {
	@PersistenceContext
	EntityManager em;
	
	@Transactional(readOnly=true)
	@Override
	public java.util.List<Fresdriverrate> list(int startIndex, int numItems) {
		return em.createNamedQuery("FresdriverrateListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}
	@Transactional(readOnly=true)
	@Override
	public java.util.List<Fresdriverrate> listAll() {
		return em.createNamedQuery("FresdriverrateListAll").getResultList();
	}
	@Transactional(readOnly=true)
	@Override
	public Fresdriverrate findByResno(String resno) {
		return (Fresdriverrate) em.createNamedQuery("FresdriverrateByResno").setParameter("resno", resno).getSingleResult();
	}
	@Transactional(readOnly=true)
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fresdriverrate").getSingleResult());
	}
	@Transactional(readOnly=false)
	@Override
	public void create(Fresdriverrate fresdriverrate) {
		em.persist(fresdriverrate);

	}
	@Transactional(readOnly=true)
	@Override
	public Fresdriverrate findByID(FresdriverratePK id) {
		return em.find(Fresdriverrate.class, id);
	}
	@Transactional(readOnly=false)
	@Override
	public void removeByID(FresdriverratePK id) {
		em.remove(em.find(Fresdriverrate.class, id));

	}
	@Transactional(readOnly=false)
	@Override
	public void update(Fresdriverrate fresdriverrate) {
		em.merge(fresdriverrate);

	}

}
