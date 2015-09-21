package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fclienttype;

public interface FclienttypeSRV {
	int count();
	Boolean create(Fclienttype fclienttype);
	Fclienttype findByID(String clienttype);
	List<Fclienttype> List(int startIndex, int numItems);
	List<Fclienttype> ListAll();
	Boolean udpate(Fclienttype fclienttype);
	Boolean removeByID(String clienttype);
}
