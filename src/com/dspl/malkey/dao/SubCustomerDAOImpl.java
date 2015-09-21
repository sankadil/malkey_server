package com.dspl.malkey.dao;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.dspl.malkey.domain.SubCustomer;

public class SubCustomerDAOImpl implements SubCustomerDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fcompanysettingDAO")
	FcompanysettingDAO fcompanysettingDAO;
	
	@Transactional
	@Override
	public java.util.List<SubCustomer> List(int startIndex, int numItems) {
		// TODO Auto-generated method stub
			return em.createNamedQuery("SubcustomerListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}
	@Transactional
	@Override
	public java.util.List<SubCustomer> ListAll() {
		return em.createNamedQuery("SubcustomerListAll").getResultList();
	}
	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM subcustomer").getSingleResult());
	}
	@Transactional
	@Override
	public Boolean create(SubCustomer subCustomer) {
		try {
			Calendar curDate = Calendar.getInstance();
			String refNo=fcompanysettingDAO.getRefNo("SC", curDate.get(Calendar.YEAR), ((curDate.get(Calendar.MONTH))+1));
			subCustomer.setId(refNo);
			em.persist(subCustomer);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	@Transactional
	@Override
	public SubCustomer findByID(String id) {
		return em.find(SubCustomer.class, id);
	}
	@Transactional
	@Override
	public void removeByID(String id) {
		em.remove(em.find(SubCustomer.class, id));
		
	}
	@Transactional
	@Override
	public Boolean update(SubCustomer subCustomer) {
		//em.remove(em.find(SubCustomer.class, subCustomer.getId()));
		//em.persist(subCustomer);
		try {
			em.merge(subCustomer);
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
	}

}
