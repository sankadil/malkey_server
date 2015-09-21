package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Freschkoutdamage;
import com.dspl.malkey.domain.FreschkoutdamagePK;

public class FreschkoutdamageDAOImpl implements FreschkoutdamageDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Freschkoutdamage> List(int startIndex, int numItems) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public List<Freschkoutdamage> ListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Freschkoutdamage freschkoutdamage) {
		em.persist(freschkoutdamage);
	}

	@Transactional
	@Override
	public Freschkoutdamage findByID(FreschkoutdamagePK freschkoutdamagePK) {
		return em.find(Freschkoutdamage.class, freschkoutdamagePK);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(FreschkoutdamagePK freschkoutdamagePK) {
		em.remove(em.find(Freschkoutdamage.class, freschkoutdamagePK));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Freschkoutdamage freschkoutdamage) {
		em.merge(freschkoutdamage);
	}

}
