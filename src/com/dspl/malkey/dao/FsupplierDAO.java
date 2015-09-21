package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fsupplier;

public interface FsupplierDAO {
	int count();
	void create(Fsupplier fsupplier);
	Fsupplier findByID(String supcode);
	List<Fsupplier> list(int startIndex, int numItems);
	List<Fsupplier> listAll();
	void udpate(Fsupplier fsupplier);
	void removeByID(String supcode);
	List<Fsupplier> listBySupType(String supType);
}
