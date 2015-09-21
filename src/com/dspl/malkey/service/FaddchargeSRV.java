package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Faddcharge;

public interface FaddchargeSRV {
	int count();
	Boolean create(Faddcharge faddcharge);
	Faddcharge findByID(String addchargeid);
	List<Faddcharge> List(int startIndex, int numItems);
	List<Faddcharge> ListAll();
	Boolean update(Faddcharge faddcharge);
	Boolean removeByID(String addchargeid);
}
