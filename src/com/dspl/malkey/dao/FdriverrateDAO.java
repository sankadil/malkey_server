package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fdriverrate;
import com.dspl.malkey.domain.FdriverratePK;

public interface FdriverrateDAO {
	int count();
	void create(Fdriverrate fdriverrate);
	Fdriverrate findByID(FdriverratePK fdriverratePK);
	List<Fdriverrate> List(int startIndex, int numItems);
	List<Fdriverrate> ListAll();
	void udpate(Fdriverrate fdriverrate);
	void removeByID(FdriverratePK fdriverratePK);
	
	Boolean update(List<Fdriverrate> fdriverrates);
}
