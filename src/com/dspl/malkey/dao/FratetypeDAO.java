package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fratetype;

public interface FratetypeDAO {
	int count();
//	void create(Fratetype fratetype);
	Fratetype findByID(String ratetype);
	List<Fratetype> list(int startIndex, int numItems);
	List<Fratetype> listAll();
//	void udpate(Fratetype fratetype);
//	void removeByID(String ratetype);
	
	Boolean create(Fratetype Fratetype);
	Boolean update(Fratetype fratetype);
	Boolean removeByID(String ratetype);
}
