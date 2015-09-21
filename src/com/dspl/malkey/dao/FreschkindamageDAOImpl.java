package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Freschkindamage;
import com.dspl.malkey.domain.FreschkindamagePK;

public class FreschkindamageDAOImpl implements FreschkindamageDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Freschkindamage> List(int startIndex, int numItems) {
		return em.createNamedQuery("FreschkindamageListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Freschkindamage> ListAll() {
		return em.createNamedQuery("FreschkindamageListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Freschkindamage").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Freschkindamage freschkindamage) {
		em.persist(freschkindamage);
	}

	@Transactional
	@Override
	public Freschkindamage findByID(FreschkindamagePK freschkindamagePK) {
		return em.find(Freschkindamage.class, freschkindamagePK);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(FreschkindamagePK freschkindamagePK) {
		em.remove(em.find(Freschkindamage.class, freschkindamagePK));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Freschkindamage freschkindamage) {
		em.merge(freschkindamage);
	}

}
