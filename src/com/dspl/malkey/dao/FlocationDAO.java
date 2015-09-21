package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Flocation;

public interface FlocationDAO {
	int count();
	void create(Flocation flocation);
	Flocation findByID(String locationid);
	List<Flocation> List(int startIndex, int numItems);
	List<Flocation> ListAll();
	List<Flocation> ListCheckIn(String stChkIn);
	void udpate(Flocation flocation);
	void removeByID(String locationid);
}
