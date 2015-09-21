package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fclientdriver;

public interface FclientdriverDAO {
	int count();
	void create(Fclientdriver fclientdriver);
	Fclientdriver findByID(int recordid);
	List<Fclientdriver> List(int startIndex, int numItems);
	List<Fclientdriver> ListAll();
	void udpate(Fclientdriver fclientdriver);
	void removeByID(int recordid);

	List<Fclientdriver> findByDebcode(String debcode);
	List<Fclientdriver> listByClientid(String clientID);
}
