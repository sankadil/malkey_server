package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fleasecom;

public interface FleasecomSRV {
	int count();
	Boolean create(Fleasecom fleasecom);
	Fleasecom findByID(String leasecomid);
	List<Fleasecom> List(int startIndex, int numItems);
	List<Fleasecom> ListAll();
	Boolean udpate(Fleasecom fleasecom);
	Boolean removeByID(String leasecomid);
}
