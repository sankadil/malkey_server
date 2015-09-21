package com.dspl.malkey.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.dspl.malkey.domain.Fresothersrvrate;
import com.dspl.malkey.domain.FresothersrvratePK;

public class FresothersrvrateDAOImpl implements FresothersrvrateDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional(readOnly=true)
	@Override
	public java.util.List<Fresothersrvrate> list(int startIndex, int numItems) {
		return em.createNamedQuery("FresothersrvrateListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}
	@Transactional(readOnly=true)
	@Override
	public java.util.List<Fresothersrvrate> listAll() {
		return em.createNamedQuery("FresothersrvrateListAll").getResultList();
	}
	@Transactional(readOnly=true)
	@Override
	public java.util.List<Fresothersrvrate> listByResno(String resno) {
		return em.createNamedQuery("FresothersrvrateListByResno").setParameter("resno", resno).getResultList();
	}
	@Transactional(readOnly=true)
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fresothersrvrate").getSingleResult());
	}
	@Transactional(readOnly=false)
	@Override
	public void create(Fresothersrvrate fresothersrvrate) {
		em.persist(fresothersrvrate);

	}
	@Transactional(readOnly=true)
	@Override
	public Fresothersrvrate findByID(FresothersrvratePK id) {
		return em.find(Fresothersrvrate.class, id);
	}
	@Transactional(readOnly=false)
	@Override
	public void removeByID(FresothersrvratePK id) {
		em.remove(em.find(Fresothersrvrate.class, id));

	}
	@Transactional(readOnly=false)
	@Override
	public void update(Fresothersrvrate fresothersrvrate) {
		em.merge(fresothersrvrate);
	}

}
