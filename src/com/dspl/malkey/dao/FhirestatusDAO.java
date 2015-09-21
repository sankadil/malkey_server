package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fhirestatus;

public interface FhirestatusDAO {
	int count();
	void create(Fhirestatus fhirestatus);
	Fhirestatus findByID(String hirestsid);
	List<Fhirestatus> List(int startIndex, int numItems);
	List<Fhirestatus> ListAll();
	void udpate(Fhirestatus fhirestatus);
	void removeByID(String hirestsid);
}
