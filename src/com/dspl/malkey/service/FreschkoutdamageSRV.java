package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Freschkoutdamage;
import com.dspl.malkey.domain.FreschkoutdamagePK;

public interface FreschkoutdamageSRV {
	int count();
	Boolean create(Freschkoutdamage freschkoutdamage);
	Freschkoutdamage findByID(FreschkoutdamagePK freschkoutdamagePK);
	List<Freschkoutdamage> List(int startIndex, int numItems);
	List<Freschkoutdamage> ListAll();
	Boolean udpate(Freschkoutdamage freschkoutdamage);
	Boolean removeByID(FreschkoutdamagePK freschkoutdamagePK);
}
