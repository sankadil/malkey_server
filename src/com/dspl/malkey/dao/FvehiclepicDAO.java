package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fvehiclepic;

public interface FvehiclepicDAO {
	int count();
	void create(Fvehiclepic fvehiclepic);
	Fvehiclepic findByID(String regNo);
	List<Fvehiclepic> list(int startIndex, int numItems);
	List<Fvehiclepic> listAll();
	void udpate(Fvehiclepic fvehiclepic);
	void removeByID(String regNo);
	List<Fvehiclepic> listByRegNo(String regNo);
}
