package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fothersrvrate;
import com.dspl.malkey.domain.FothersrvratePK;

public class FothersrvrateDAOImpl implements FothersrvrateDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fothersrvrate> List(int startIndex, int numItems) {
		return em.createNamedQuery("FothersrvrateListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fothersrvrate> ListAll() {
		return em.createNamedQuery("FothersrvrateListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fothersrvrate").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fothersrvrate fothersrvrate) {
		em.persist(fothersrvrate);
	}

	@Transactional
	@Override
	public Fothersrvrate findByID(FothersrvratePK fothersrvratePK) {
		return em.find(Fothersrvrate.class, fothersrvratePK);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(FothersrvratePK fothersrvratePK) {
		em.remove(em.find(Fothersrvrate.class, fothersrvratePK));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fothersrvrate fothersrvrate) {
		em.merge(fothersrvrate);
	}

	@Transactional
	@Override
	public List<Fothersrvrate> findBySrvId(String srvid) {
		try {
			return em.createNamedQuery("FothersrvFindBySrvId").setParameter("srvid", srvid).getResultList();
		} catch (Exception e) {
			System.out.println("findBySrvId: " + e.getMessage());
			e.printStackTrace();	
		}
		return null;
	}

}
