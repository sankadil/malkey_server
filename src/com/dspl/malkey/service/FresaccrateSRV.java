package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fresaccrate;
import com.dspl.malkey.domain.FresaccratePK;

public interface FresaccrateSRV {
	int count();
	void create(Fresaccrate fresaccrate);
	Fresaccrate findByID(FresaccratePK id);
	List<Fresaccrate> list(int startIndex, int numItems);
	List<Fresaccrate> listAll();
	void update(Fresaccrate fresaccrate);
	void removeByID(FresaccratePK id);
	List<Fresaccrate> listAllByResno(String resno);
}
