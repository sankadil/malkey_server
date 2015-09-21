package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Ftaxdet;
import com.dspl.malkey.domain.Ftaxhed;

import flex.messaging.io.ArrayCollection;

public interface FtaxhedDAO {
	int count();
	List<Ftaxhed> listAll();
	List<Ftaxhed> list(int startIndex, int numItems);
	void create(Ftaxhed ftaxhed);
	void update(Ftaxhed ftaxhed);
	void removeById(String taxcomcode);
	void deleterec(String taxcomcode);
	//void edit(Ftaxhed ftaxhed,Ftaxdet ftaxdet); 
	Ftaxhed findById(String taxcomcode);
	void insert(Ftaxhed ftaxhed, ArrayCollection ftaxdet);
	void edit(Ftaxhed ftaxhed, List<Ftaxdet> ftaxdet);
}
