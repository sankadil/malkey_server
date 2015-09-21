package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Freschkindamage;
import com.dspl.malkey.domain.FreschkindamagePK;

public interface FreschkindamageSRV {
	int count();
	Boolean create(Freschkindamage freschkindamage);
	Freschkindamage findByID(FreschkindamagePK freschkindamagePK);
	List<Freschkindamage> List(int startIndex, int numItems);
	List<Freschkindamage> ListAll();
	Boolean udpate(Freschkindamage freschkindamage);
	Boolean removeByID(FreschkindamagePK freschkindamagePK);
}
