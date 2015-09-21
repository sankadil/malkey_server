package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fresaccs;
import com.dspl.malkey.domain.FresaccsPK;

public class FresaccsDAOImpl implements FresaccsDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fresaccs> List(int startIndex, int numItems) {
		return em.createNamedQuery("FresaccsListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fresaccs> ListAll() {
		return em.createNamedQuery("FresaccsListAll").getResultList();
	}
	
	@Transactional
	@Override
	public List<Fresaccs> listAllByresNo(String resno) {
		return em.createNamedQuery("FresaccsListByresNo").setParameter("resno", resno).getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fresaccs").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fresaccs fresaccs) {
		em.persist(fresaccs);
	}

	@Transactional
	@Override
	public Fresaccs findByID(FresaccsPK fresaccsPK) {
		return em.find(Fresaccs.class, fresaccsPK);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(FresaccsPK fresaccsPK) {
		em.remove(em.find(Fresaccs.class, fresaccsPK));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fresaccs fresaccs) {
		em.merge(fresaccs);
	}

}
