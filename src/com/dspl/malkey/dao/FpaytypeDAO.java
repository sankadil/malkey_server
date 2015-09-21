package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fpaytype;

public interface FpaytypeDAO {
	int count();
	void create(Fpaytype fpaytype);
	Fpaytype findByID(String paytypeid);
	List<Fpaytype> List(int startIndex, int numItems);
	List<Fpaytype> ListAll();
	void udpate(Fpaytype fpaytype);
	void removeByID(String paytypeid);
}
