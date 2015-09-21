package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Ffueltype;

public interface FfueltypeSRV {
	int count();
	Boolean create(Ffueltype ffueltype);
	Ffueltype findByID(String fueltypeid);
	List<Ffueltype> List(int startIndex, int numItems);
	List<Ffueltype> ListAll();
	Boolean udpate(Ffueltype ffueltype);
	Boolean removeByID(String fueltypeid);
}
