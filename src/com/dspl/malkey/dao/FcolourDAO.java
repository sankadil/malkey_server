package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fcolour;

public interface FcolourDAO {
	int count();
	void create(Fcolour fcolour);
	Fcolour findByID(String colourid);
	List<Fcolour> List(int startIndex, int numItems);
	List<Fcolour> ListAll();
	void udpate(Fcolour fcolour);
	void removeByID(String colourid);
}
