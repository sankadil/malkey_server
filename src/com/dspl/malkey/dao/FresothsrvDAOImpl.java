package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fresothsrv;
import com.dspl.malkey.domain.FresothsrvPK;

public class FresothsrvDAOImpl implements FresothsrvDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fresothsrv> List(int startIndex, int numItems) {
		return em.createNamedQuery("FresothsrvListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fresothsrv> ListAll() {
		return em.createNamedQuery("FresothsrvListAll").getResultList();
	}
	
	@Transactional
	@Override
	public List<Fresothsrv> listByResNo(String resno) {
		return em.createNamedQuery("FresothsrvListAllByResNo").setParameter("resno", resno).getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fresothsrv").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fresothsrv fresothsrv) {
		em.persist(fresothsrv);
	}

	@Transactional
	@Override
	public Fresothsrv findByID(FresothsrvPK fresothsrvPK) {
		return em.find(Fresothsrv.class, fresothsrvPK);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(FresothsrvPK fresothsrvPK) {
		em.remove(em.find(Fresothsrv.class, fresothsrvPK));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fresothsrv fresothsrv) {
		em.merge(fresothsrv);
	}

}
