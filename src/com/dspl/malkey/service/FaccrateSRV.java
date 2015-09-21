package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Faccrate;
import com.dspl.malkey.domain.FaccratePK;

public interface FaccrateSRV {
	int count();
	Boolean create(Faccrate faccrate);
	Faccrate findByID(FaccratePK faccratePK);
	List<Faccrate> List(int startIndex, int numItems);
	List<Faccrate> ListAll();
	Boolean udpate(Faccrate faccrate);
	Boolean removeByID(FaccratePK faccratePK);
	List<Faccrate> ListByClienttype(String clienttype);
	List<Faccrate> findByAccId(String accid);
}
