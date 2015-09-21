package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fsmssent;

public interface FsmssentSRV {
	int count();
	Boolean create(Fsmssent fsmssent);
	Fsmssent findByID(int recordid);
	List<Fsmssent> List(int startIndex, int numItems);
	List<Fsmssent> ListAll();
	Boolean udpate(Fsmssent fsmssent);
	Boolean removeByID(int recordid);
}
