package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fresaddcharge;
import com.dspl.malkey.domain.FresaddchargePK;

public class FresaddchargeDAOImpl implements FresaddchargeDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fresaddcharge> List(int startIndex, int numItems) {
		return em.createNamedQuery("FresaddchargeListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fresaddcharge> ListAll() {
		return em.createNamedQuery("FresaddchargeListAll").getResultList();
	}
	@Transactional
	@Override
	public List<Fresaddcharge> listByresno(String resno) {
		return em.createNamedQuery("FresaddchargeListByresno").setParameter("resno", resno).getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fresaddcharge").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fresaddcharge fresaddcharge) {
		em.persist(fresaddcharge);
	}

	@Transactional
	@Override
	public Fresaddcharge findByID(FresaddchargePK fresaddchargePK) {
		return em.find(Fresaddcharge.class, fresaddchargePK);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(FresaddchargePK fresaddchargePK) {
		em.remove(em.find(Fresaddcharge.class, fresaddchargePK));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fresaddcharge fresaddcharge) {
		em.merge(fresaddcharge);
	}

}
