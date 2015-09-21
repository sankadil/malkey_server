package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fresdriver;
import com.dspl.malkey.domain.FresdriverPK;

public class FresdriverDAOImpl implements FresdriverDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fresdriver> List(int startIndex, int numItems) {
		return em.createNamedQuery("FresdriverListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fresdriver> ListAll() {
		return em.createNamedQuery("FresdriverListAll").getResultList();
	}
	
	@Transactional
	@Override
	public List<Fresdriver> listByResno(String resno) {
		return em.createNamedQuery("FresdriverListByResno").setParameter("resno", resno).getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fresdriver").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fresdriver fresdriver) {
		em.persist(fresdriver);
	}

	@Transactional
	@Override
	public Fresdriver findByID(FresdriverPK fresdriverPK) {
		return em.find(Fresdriver.class, fresdriverPK);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(FresdriverPK fresdriverPK) {
		em.remove(em.find(Fresdriver.class, fresdriverPK));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fresdriver fresdriver) {
		em.merge(fresdriver);
	}

}
