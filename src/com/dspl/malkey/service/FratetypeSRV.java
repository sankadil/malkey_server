package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fratetype;

public interface FratetypeSRV {
	int count();
	Boolean create(Fratetype fratetype);
	Fratetype findByID(String ratetype);
	List<Fratetype> list(int startIndex, int numItems);
	List<Fratetype> listAll();
	Boolean update(Fratetype fratetype);
	Boolean removeByID(String ratetype);
}
