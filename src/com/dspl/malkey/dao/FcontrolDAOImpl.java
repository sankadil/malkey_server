package com.dspl.malkey.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dspl.malkey.domain.Fcontrol;

public class FcontrolDAOImpl implements FcontrolDAO {
	@PersistenceContext
	EntityManager em;
	
	@Override
	public Fcontrol ListAll() {
		return (Fcontrol)em.createNamedQuery("FcontrolListAll").getSingleResult();
	}

	@Override
	public void update(Fcontrol fcontrol) {
		em.merge(fcontrol);
	}

}
