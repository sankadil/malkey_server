package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.dspl.malkey.domain.Finvhed;

public class FinvhedDAOImpl implements FinvhedDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Finvhed> listAll() {
		return em.createNamedQuery("FinvhedListAll", Finvhed.class).getResultList();
	}

}
