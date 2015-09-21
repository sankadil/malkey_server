package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Finspolicy;

public interface FinspolicySRV {
	int count();
	Boolean create(Finspolicy finspolicy);
	Finspolicy findByID(String policyid);
	List<Finspolicy> List(int startIndex, int numItems);
	List<Finspolicy> ListAll();
	Boolean udpate(Finspolicy finspolicy);
	Boolean removeByID(String policyid);
}
