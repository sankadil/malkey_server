package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fsupplier2;

public interface Fsupplier2DAO {
	int count();
	void create(Fsupplier2 fsupplier2);
	Fsupplier2 findByID(String supcode);
	List<Fsupplier2> list(int startIndex, int numItems);
	List<Fsupplier2> listAll();
	void udpate(Fsupplier2 fsupplier2);
	void removeByID(String supcode);
	List<Fsupplier2> listBySupType(String supType);
}
