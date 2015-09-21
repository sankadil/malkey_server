package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fclientdriver;

public interface FclientdriverSRV {
	int count();
	Boolean create(Fclientdriver fclientdriver);
	Fclientdriver findByID(int recordid);
	List<Fclientdriver> List(int startIndex, int numItems);
	List<Fclientdriver> ListAll();
	Boolean udpate(Fclientdriver fclientdriver);
	Boolean removeByID(int recordid);

	List<Fclientdriver> findByDebcode(String debcode);
	List<Fclientdriver> listByClientid(String clientID);
}
