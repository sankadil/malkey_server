package com.dspl.malkey.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.dspl.malkey.domain.Fresaccrate;
import com.dspl.malkey.domain.FresaccratePK;
import com.dspl.malkey.domain.Fresvehiclerate;
import java.util.*;

public class FresaccrateDAOImpl implements FresaccrateDAO {
	@PersistenceContext
	EntityManager em;
	
	@Transactional(readOnly=true)
	@Override
	public List<Fresaccrate> list(int startIndex, int numItems) {
		return em.createNamedQuery("FresaccrateListAll123").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}
	@Transactional(readOnly=true)
	@Override
	public List<Fresaccrate> listAll() {
		return em.createNamedQuery("FresaccrateListAll123").getResultList();
	}
	@Transactional(readOnly=true)
	@Override
	public List<Fresaccrate> listAllByResno(String resno) {
		return em.createNamedQuery("FresaccrateListByResno").setParameter("resno", resno).getResultList();
	}
	@Transactional(readOnly=true)
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fresaccrate").getSingleResult());
	}
	@Transactional(readOnly=false)
	@Override
	public void create(Fresaccrate fresaccrate) {
		em.persist(fresaccrate);

	}
	@Transactional(readOnly=true)
	@Override
	public Fresaccrate findByID(FresaccratePK id) {
		return em.find(Fresaccrate.class, id);
	}
	@Transactional(readOnly=false)
	@Override
	public void removeByID(FresaccratePK id) {
		em.remove(em.find(Fresvehiclerate.class, id));
	}
	@Transactional(readOnly=false)
	@Override
	public void update(Fresaccrate fresaccrate) {
		em.merge(fresaccrate);

	}

}
