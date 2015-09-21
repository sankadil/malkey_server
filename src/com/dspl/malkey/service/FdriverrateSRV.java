package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fdriverrate;
import com.dspl.malkey.domain.FdriverratePK;

public interface FdriverrateSRV {
	int count();
	Boolean create(Fdriverrate fdriverrate);
	Fdriverrate findByID(FdriverratePK fdriverratePK);
	List<Fdriverrate> List(int startIndex, int numItems);
	List<Fdriverrate> ListAll();
	Boolean udpate(Fdriverrate fdriverrate);
	Boolean removeByID(FdriverratePK fdriverratePK);
	
	Boolean update(List<Fdriverrate> fdriverrates);
}
