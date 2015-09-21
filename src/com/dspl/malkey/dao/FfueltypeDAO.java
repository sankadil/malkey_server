package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Ffueltype;

public interface FfueltypeDAO {
	int count();
	void create(Ffueltype ffueltype);
	Ffueltype findByID(String fueltypeid);
	List<Ffueltype> List(int startIndex, int numItems);
	List<Ffueltype> ListAll();
	void udpate(Ffueltype ffueltype);
	void removeByID(String fueltypeid);
}
