package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fcolour;

public interface FcolourSRV {
	int count();
	Boolean create(Fcolour fcolour);
	Fcolour findByID(String colourid);
	List<Fcolour> List(int startIndex, int numItems);
	List<Fcolour> ListAll();
	Boolean udpate(Fcolour fcolour);
	Boolean removeByID(String colourid);
}
