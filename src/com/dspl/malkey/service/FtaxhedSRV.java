package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Ftaxdet;
import com.dspl.malkey.domain.Ftaxhed;

import flex.messaging.io.ArrayCollection;

public interface FtaxhedSRV {
	int count();
	List<Ftaxhed> listAll();
	List<Ftaxhed> list(int startIndex, int numItems);
	Boolean create(Ftaxhed ftaxhed);
	Boolean update(Ftaxhed ftaxhed);
	Boolean removeById(String taxcomcode);
	Boolean deleterec(String taxcomcode);
	Ftaxhed findById(String taxcomcode);
	Boolean edit(Ftaxhed ftaxhed, List<Ftaxdet> ftaxdet);
	Boolean insert(Ftaxhed ftaxhed, ArrayCollection ftaxdet);
	
}
