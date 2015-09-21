package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fmainttype;

public interface FmainttypeDAO {
	int count();
	void create(Fmainttype fmainttype);
	Fmainttype findByID(String typeid);
	List<Fmainttype> List(int startIndex, int numItems);
	List<Fmainttype> ListAll();
	void udpate(Fmainttype fmainttype);
	void removeByID(String typeid);
}
