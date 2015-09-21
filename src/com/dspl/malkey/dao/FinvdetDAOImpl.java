package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.dspl.malkey.domain.Finvdet;

public class FinvdetDAOImpl implements FinvdetDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Finvdet> listAll() {
		return em.createNamedQuery("FinvdetListAll", Finvdet.class).getResultList();
	}

}
