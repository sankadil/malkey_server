package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fsupplier2;

public interface Fsupplier2SRV {
	int count();
	Boolean create(Fsupplier2 fsupplier2);
	Fsupplier2 findByID(String supcode);
	List<Fsupplier2> list(int startIndex, int numItems);
	List<Fsupplier2> listAll();
	Boolean udpate(Fsupplier2 fsupplier2);
	Boolean removeByID(String supcode);
	List<Fsupplier2> listBySupType(String supType);
}
