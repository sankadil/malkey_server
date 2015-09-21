package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fresaddcharge;
import com.dspl.malkey.domain.FresaddchargePK;

public interface FresaddchargeSRV {
	int count();
	Boolean create(Fresaddcharge fresaddcharge);
	Fresaddcharge findByID(FresaddchargePK fresaddchargePK);
	List<Fresaddcharge> List(int startIndex, int numItems);
	List<Fresaddcharge> ListAll();
	Boolean udpate(Fresaddcharge fresaddcharge);
	Boolean removeByID(FresaddchargePK fresaddchargePK);
	List<Fresaddcharge> listByresno(String resno);
}
