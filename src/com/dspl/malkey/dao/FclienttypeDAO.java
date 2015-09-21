package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fclienttype;

public interface FclienttypeDAO {
	int count();
	void create(Fclienttype fclienttype);
	Fclienttype findByID(String clienttype);
	List<Fclienttype> List(int startIndex, int numItems);
	List<Fclienttype> ListAll();
	void udpate(Fclienttype fclienttype);
	void removeByID(String clienttype);
}
