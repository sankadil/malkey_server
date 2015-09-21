package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Finsfleet;

public interface FinsfleetDAO {
	int count();
	void create(Finsfleet finsfleet);
	Finsfleet findByID(String fleetid);
	List<Finsfleet> List(int startIndex, int numItems);
	List<Finsfleet> ListAll();
	void udpate(Finsfleet finsfleet);
	void removeByID(String fleetid);
}
