package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fresaccs;
import com.dspl.malkey.domain.FresaccsPK;

public interface FresaccsSRV {
	int count();
	Boolean create(Fresaccs fresaccs);
	Fresaccs findByID(FresaccsPK fresaccsPK);
	List<Fresaccs> List(int startIndex, int numItems);
	List<Fresaccs> ListAll();
	Boolean udpate(Fresaccs fresaccs);
	Boolean removeByID(FresaccsPK fresaccsPK);
	List<Fresaccs> listAllByresNo(String resno);
}
