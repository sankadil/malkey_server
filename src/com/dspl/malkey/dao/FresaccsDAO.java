package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fresaccs;
import com.dspl.malkey.domain.FresaccsPK;

public interface FresaccsDAO {
	int count();
	void create(Fresaccs fresaccs);
	Fresaccs findByID(FresaccsPK fresaccsPK);
	List<Fresaccs> List(int startIndex, int numItems);
	List<Fresaccs> ListAll();
	void udpate(Fresaccs fresaccs);
	void removeByID(FresaccsPK fresaccsPK);
	List<Fresaccs> listAllByresNo(String resno);
}
