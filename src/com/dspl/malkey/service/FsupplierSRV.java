package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fsupplier;

public interface FsupplierSRV {
	int count();
	Boolean create(Fsupplier fsupplier);
	Fsupplier findByID(String supcode);
	List<Fsupplier> list(int startIndex, int numItems);
	List<Fsupplier> listAll();
	Boolean udpate(Fsupplier fsupplier);
	Boolean removeByID(String supcode);
	List<Fsupplier> listBySupType(String supType);
}
