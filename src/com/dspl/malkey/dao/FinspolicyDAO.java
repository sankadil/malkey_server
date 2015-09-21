package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Finspolicy;

public interface FinspolicyDAO {
	int count();
	void create(Finspolicy finspolicy);
	Finspolicy findByID(String policyid);
	List<Finspolicy> List(int startIndex, int numItems);
	List<Finspolicy> ListAll();
	void udpate(Finspolicy finspolicy);
	void removeByID(String policyid);
}
