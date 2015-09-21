package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fquote;

public class FquoteDAOImpl implements FquoteDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fquote> List(int startIndex, int numItems) {
		return em.createNamedQuery("FquoteListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fquote> ListAll() {
		return em.createNamedQuery("FquoteListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fquote").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fquote fquote) {
		em.persist(fquote);
	}

	@Transactional
	@Override
	public Fquote findByID(String quoteno) {
		return em.find(Fquote.class, quoteno);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String quoteno) {
		em.remove(em.find(Fquote.class, quoteno));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fquote fquote) {
		em.merge(fquote);
	}

}
