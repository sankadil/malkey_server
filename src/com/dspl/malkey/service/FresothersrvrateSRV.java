package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fresothersrvrate;
import com.dspl.malkey.domain.FresothersrvratePK;

public interface FresothersrvrateSRV {
	int count();
	void create(Fresothersrvrate fresothersrvrate);
	Fresothersrvrate findByID(FresothersrvratePK id);
	List<Fresothersrvrate> list(int startIndex, int numItems);
	List<Fresothersrvrate> listAll();
	void udpate(Fresothersrvrate fresothersrvrate);
	void removeByID(FresothersrvratePK id);
	List<Fresothersrvrate> listByResno(String resno);
}
