package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fresdriverrate;
import com.dspl.malkey.domain.FresdriverratePK;

public interface FresdriverrateSRV {
	int count();
	void create(Fresdriverrate fresdriverrate);
	Fresdriverrate findByID(FresdriverratePK id);
	List<Fresdriverrate> list(int startIndex, int numItems);
	List<Fresdriverrate> listAll();
	void udpate(Fresdriverrate fresdriverrate);
	void removeByID(FresdriverratePK id);
	Fresdriverrate findByResno(String resno);
}
