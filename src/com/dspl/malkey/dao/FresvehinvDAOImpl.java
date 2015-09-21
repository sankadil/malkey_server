package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fresvehinv;
import com.dspl.malkey.domain.FresvehinvPK;

public class FresvehinvDAOImpl implements FresvehinvDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fresvehinv> List(int startIndex, int numItems) {
		return em.createNamedQuery("FresvehinvListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fresvehinv> ListAll() {
		return em.createNamedQuery("FresvehinvListAll").getResultList();
	}
	@Transactional
	@Override
	public List<Fresvehinv> listByResno(String resno) {
		return em.createNamedQuery("FresvehinvListByResno").setParameter("resno", resno).getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fresvehinv").getSingleResult());
	}

	@Transactional
	@Override
	public void create(Fresvehinv fresvehinv) {
		em.persist(fresvehinv);
	}

	@Transactional(readOnly=false)
	@Override
	public Fresvehinv findByID(FresvehinvPK fresvehinvPK) {
		return em.find(Fresvehinv.class, fresvehinvPK);
	}

	@Transactional
	@Override
	public void removeByID(FresvehinvPK fresvehinvPK) {
		em.remove(em.find(Fresvehinv.class, fresvehinvPK));
	}

	@Transactional
	@Override
	public void udpate(Fresvehinv fresvehinv) {
		em.merge(fresvehinv);
	}

}
