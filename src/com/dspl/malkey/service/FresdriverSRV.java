package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fresdriver;
import com.dspl.malkey.domain.FresdriverPK;

public interface FresdriverSRV {
	int count();
	Boolean create(Fresdriver fresdriver);
	Fresdriver findByID(FresdriverPK fresdriverPK);
	List<Fresdriver> List(int startIndex, int numItems);
	List<Fresdriver> ListAll();
	Boolean udpate(Fresdriver fresdriver);
	Boolean removeByID(FresdriverPK fresdriverPK);
	List<Fresdriver> listByResno(String resno);
}
