package com.dspl.malkey.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fresothersrvrate;
import com.dspl.malkey.domain.Fresvehiclerate;

public class FresvehiclerateDAOImpl implements FresvehiclerateDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional(readOnly=true)
	@Override
	public java.util.List<Fresvehiclerate> list(int startIndex, int numItems) {
		return em.createNamedQuery("FresvehiclerateListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}
	@Transactional(readOnly=true)
	@Override
	public java.util.List<Fresvehiclerate> listAll() {
		return em.createNamedQuery("FresvehiclerateListAll").getResultList();
	}
	/**
	 * @param resno
	 * @return
	 */
	@Transactional(readOnly=true)
	@Override
	public java.util.List<Fresvehiclerate> listByResno(String resno) {
		return em.createNamedQuery("FresvehiclerateListByResno").setParameter("resno", resno).getResultList();
	}
	@Transactional(readOnly=true)
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fresothersrvrate").getSingleResult());
	}
	@Transactional(readOnly=false)
	@Override
	public void create(Fresvehiclerate fresvehiclerate) {
		em.persist(fresvehiclerate);
	}
	@Transactional(readOnly=true)
	@Override
	public Fresvehiclerate findByID(String resno) {
		return em.find(Fresvehiclerate.class, resno);
	}
	@Transactional(readOnly=false)
	@Override
	public void removeByID(String resno) {
		em.remove(em.find(Fresvehiclerate.class, resno));

	}
	@Transactional(readOnly=false)
	@Override
	public void update(Fresvehiclerate fresvehiclerate) {
		em.merge(fresvehiclerate);

	}

}
