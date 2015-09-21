package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Finsfleet;

public interface FinsfleetSRV {
	int count();
	Boolean create(Finsfleet finsfleet);
	Finsfleet findByID(String fleetid);
	List<Finsfleet> List(int startIndex, int numItems);
	List<Finsfleet> ListAll();
	Boolean udpate(Finsfleet finsfleet);
	Boolean removeByID(String fleetid);
}
