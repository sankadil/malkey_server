package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Femployeetype;

public interface FemployeetypeDAO {
	int count();
	void create(Femployeetype femployeetype);
	Femployeetype findByID(String emptype);
	List<Femployeetype> List(int startIndex, int numItems);
	List<Femployeetype> ListAll();
	void udpate(Femployeetype femployeetype);
	void removeByID(String emptype);
}
