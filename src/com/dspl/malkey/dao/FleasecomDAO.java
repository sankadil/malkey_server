package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fleasecom;

public interface FleasecomDAO {
	int count();
	void create(Fleasecom fleasecom);
	Fleasecom findByID(String leasecomid);
	List<Fleasecom> List(int startIndex, int numItems);
	List<Fleasecom> ListAll();
	void udpate(Fleasecom fleasecom);
	void removeByID(String leasecomid);
}
