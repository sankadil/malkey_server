package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fenginesize;

public interface FenginesizeDAO {
	int count();
	void create(Fenginesize fenginesize);
	Fenginesize findByID(String engsizeid);
	List<Fenginesize> List(int startIndex, int numItems);
	List<Fenginesize> ListAll();
	void udpate(Fenginesize fenginesize);
	void removeByID(String engsizeid);
}
