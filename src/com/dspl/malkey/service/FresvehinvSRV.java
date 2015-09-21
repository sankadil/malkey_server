package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fresvehinv;
import com.dspl.malkey.domain.FresvehinvPK;

public interface FresvehinvSRV {
	int count();
	Boolean create(Fresvehinv fresvehinv);
	Fresvehinv findByID(FresvehinvPK fresvehinvPK);
	List<Fresvehinv> List(int startIndex, int numItems);
	List<Fresvehinv> ListAll();
	Boolean udpate(Fresvehinv fresvehinv);
	Boolean removeByID(FresvehinvPK fresvehinvPK);
	List<Fresvehinv> listByResno(String resno);
}
