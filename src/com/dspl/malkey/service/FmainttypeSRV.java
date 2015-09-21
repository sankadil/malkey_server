package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fmainttype;

public interface FmainttypeSRV {
	int count();
	Boolean create(Fmainttype fmainttype);
	Fmainttype findByID(String typeid);
	List<Fmainttype> List(int startIndex, int numItems);
	List<Fmainttype> ListAll();
	Boolean udpate(Fmainttype fmainttype);
	Boolean removeByID(String typeid);
}
