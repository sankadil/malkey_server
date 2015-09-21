package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.SubCustomer;

public interface SubCustomerDAO {
	int count();
	Boolean create(SubCustomer subCustomer);
	SubCustomer findByID(String id);
	List<SubCustomer> List(int startIndex, int numItems);
	List<SubCustomer> ListAll();
	Boolean update(SubCustomer subCustomer);
	void removeByID(String id);
}
