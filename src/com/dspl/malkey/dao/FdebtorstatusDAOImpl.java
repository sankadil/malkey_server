package com.dspl.malkey.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.dspl.malkey.domain.Fdebtorstatus;

public class FdebtorstatusDAOImpl implements FdebtorstatusDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public java.util.List<Fdebtorstatus> List(int startIndex, int numItems) {
		return em.createNamedQuery("FdebtorstatusListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public java.util.List<Fdebtorstatus> ListAll() {
		return em.createNamedQuery("FdebtorstatusListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fdebtorstatus").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fdebtorstatus fdebtorstatus) {
		em.persist(fdebtorstatus);
	}

	@Transactional
	@Override
	public Fdebtorstatus findByID(String id) {
		return em.find(Fdebtorstatus.class, id);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String id) {
		em.remove(em.find(Fdebtorstatus.class, id));
	}

	@Transactional(readOnly=false)
	@Override
	public void update(Fdebtorstatus fdebtorstatus) {
		em.merge(fdebtorstatus);
	}

}
