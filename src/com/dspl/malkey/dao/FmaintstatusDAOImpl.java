package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fmaintstatus;

public class FmaintstatusDAOImpl implements FmaintstatusDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fmaintstatus> List(int startIndex, int numItems) {
		return em.createNamedQuery("FmaintstatusListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fmaintstatus> ListAll() {
		return em.createNamedQuery("FmaintstatusListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fmaintstatus").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fmaintstatus fmaintstatus) {
		em.persist(fmaintstatus);
	}

	@Transactional
	@Override
	public Fmaintstatus findByID(String statusid) {
		return em.find(Fmaintstatus.class, statusid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String statusid) {
		em.remove(em.find(Fmaintstatus.class, statusid));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fmaintstatus fmaintstatus) {
		em.merge(fmaintstatus);
	}

}
