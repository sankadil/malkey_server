package com.dspl.malkey.service;

import javax.annotation.Resource;

import com.dspl.malkey.dao.SubCustomerDAO;
import com.dspl.malkey.domain.SubCustomer;

public class SubCustomerSRVImpl implements SubCustomerSRV {

	@Resource(name="subCustomerDAO")
	SubCustomerDAO subCustomerDAO;
	
	@Override
	public java.util.List<SubCustomer> List(int startIndex, int numItems) {
		// TODO Auto-generated method stub
		return subCustomerDAO.List(startIndex, numItems);
	}

	@Override
	public java.util.List<SubCustomer> ListAll() {
		// TODO Auto-generated method stub
		return subCustomerDAO.ListAll();
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return subCustomerDAO.count();
	}

	@Override
	public Boolean create(SubCustomer subCustomer) {
		// TODO Auto-generated method stub
		return subCustomerDAO.create(subCustomer);
	}

	@Override
	public SubCustomer findByID(String id) {
		// TODO Auto-generated method stub
		return subCustomerDAO.findByID(id);
	}

	@Override
	public void removeByID(String id) {
		// TODO Auto-generated method stub
		subCustomerDAO.removeByID(id);
	}

	@Override
	public Boolean update(SubCustomer subCustomer) {
		// TODO Auto-generated method stub
		return subCustomerDAO.update(subCustomer);
	}

}
