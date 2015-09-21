package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fenginesize;

public interface FenginesizeSRV {
	int count();
	Boolean create(Fenginesize fenginesize);
	Fenginesize findByID(String engsizeid);
	List<Fenginesize> List(int startIndex, int numItems);
	List<Fenginesize> ListAll();
	Boolean udpate(Fenginesize fenginesize);
	Boolean removeByID(String engsizeid);
}
