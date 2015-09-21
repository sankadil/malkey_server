package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fpass;

public interface FpassSRV {
	int count();
	List<Fpass> listAll();
	List<Fpass> list(int startIndex, int numItems);
	Boolean create(Fpass fpass);
	Boolean update(Fpass fpass);
	Boolean removeById(String userid);
	Fpass findById(String userid);
	List<Fpass> listUsers();
}
