package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Flocation;

public interface FlocationSRV {
	int count();
	Boolean create(Flocation flocation);
	Flocation findByID(String locationid);
	List<Flocation> List(int startIndex, int numItems);
	List<Flocation> ListAll();
	List<Flocation> ListCheckIn(String stChkIn);
	Boolean udpate(Flocation flocation);
	Boolean removeByID(String locationid);
}
