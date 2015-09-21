package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fresdriver;
import com.dspl.malkey.domain.FresdriverPK;

public interface FresdriverDAO {
	int count();
	void create(Fresdriver fresdriver);
	Fresdriver findByID(FresdriverPK fresdriverPK);
	List<Fresdriver> List(int startIndex, int numItems);
	List<Fresdriver> ListAll();
	void udpate(Fresdriver fresdriver);
	void removeByID(FresdriverPK fresdriverPK);
	List<Fresdriver> listByResno(String resno);
}
