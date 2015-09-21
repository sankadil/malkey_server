package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Femployeetype;

public interface FemployeetypeSRV {
	int count();
	Boolean create(Femployeetype femployeetype);
	Femployeetype findByID(String emptype);
	List<Femployeetype> List(int startIndex, int numItems);
	List<Femployeetype> ListAll();
	Boolean udpate(Femployeetype femployeetype);
	Boolean removeByID(String emptype);
}
