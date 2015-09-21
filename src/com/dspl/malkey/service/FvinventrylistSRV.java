package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fvinventrylist;

public interface FvinventrylistSRV {
	int count();
	Boolean create(Fvinventrylist fvinventrylist);
	Fvinventrylist findByID(String invid);
	List<Fvinventrylist> List(int startIndex, int numItems);
	List<Fvinventrylist> ListAll();
	Boolean udpate(Fvinventrylist fvinventrylist);
	Boolean removeByID(String invid);
}
