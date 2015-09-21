package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fvehiclepic;

public interface FvehiclepicSRV {
	int count();
	Boolean create(Fvehiclepic fvehiclepic);
	Fvehiclepic findByID(String regNo);
	List<Fvehiclepic> list(int startIndex, int numItems);
	List<Fvehiclepic> listAll();
	Boolean udpate(Fvehiclepic fvehiclepic);
	Boolean removeByID(String regNo);
	List<Fvehiclepic> listByRegNo(String regNo);
}
