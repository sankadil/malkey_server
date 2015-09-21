package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fothersrvrate;
import com.dspl.malkey.domain.FothersrvratePK;

public interface FothersrvrateSRV {
	int count();
	Boolean create(Fothersrvrate fothersrvrate);
	Fothersrvrate findByID(FothersrvratePK fothersrvratePK);
	List<Fothersrvrate> List(int startIndex, int numItems);
	List<Fothersrvrate> ListAll();
	Boolean udpate(Fothersrvrate fothersrvrate);
	Boolean removeByID(FothersrvratePK fothersrvratePK);
	List<Fothersrvrate> findBySrvId(String srvid);
}
