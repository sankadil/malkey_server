package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fvinventrylist;

public interface FvinventrylistDAO {
	int count();
	void create(Fvinventrylist fvinventrylist);
	Fvinventrylist findByID(String invid);
	List<Fvinventrylist> List(int startIndex, int numItems);
	List<Fvinventrylist> ListAll();
	void udpate(Fvinventrylist fvinventrylist);
	void removeByID(String invid);
}
