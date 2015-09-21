package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fresaccrate;
import com.dspl.malkey.domain.FresaccratePK;

public interface FresaccrateDAO {
	int count();
	void create(Fresaccrate fresaccrate);
	Fresaccrate findByID(FresaccratePK id);
	List<Fresaccrate> list(int startIndex, int numItems);
	List<Fresaccrate> listAll();
	List<Fresaccrate> listAllByResno(String resno);
	void update(Fresaccrate fresaccrate);
	void removeByID(FresaccratePK id);
}
