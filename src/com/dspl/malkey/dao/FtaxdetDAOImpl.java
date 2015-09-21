package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Ftaxdet;
import com.dspl.malkey.domain.FtaxdetPK;

public class FtaxdetDAOImpl implements FtaxdetDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional(readOnly=true)	
	@Override
	public int count() {
		int i=(Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Ftaxdet").getSingleResult());
		return i;
	}

	@Transactional @Override
	public void create(Ftaxdet ftaxdet) {
		// TODO Auto-generated method stub

	}

	@Transactional @Override
	public Ftaxdet findById(FtaxdetPK ftaxdetpk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional @Override
	public List<Ftaxdet> list(int startIndex, int numItems) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("FtaxdetListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional @Override
	public List<Ftaxdet> listAll() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("FtaxdetListAll").getResultList();
	}

	@Transactional @Override
	public void removeById(FtaxdetPK ftaxdetpk) {
		// TODO Auto-generated method stub

	}

	@Transactional @Override
	public void update(Ftaxdet ftaxdet) {
		em.merge(ftaxdet);
	}

	@Transactional
	@Override
	public Ftaxdet findByTaxcomcode(String taxcomcode) {
		try {
			//String query="SELECT "
		} catch (Exception e) {
			System.out.println("FtaxdetDaoImpl findByTaxcomcode: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
