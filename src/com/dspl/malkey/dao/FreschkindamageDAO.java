package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Freschkindamage;
import com.dspl.malkey.domain.FreschkindamagePK;

public interface FreschkindamageDAO {
	int count();
	void create(Freschkindamage freschkindamage);
	Freschkindamage findByID(FreschkindamagePK freschkindamagePK);
	List<Freschkindamage> List(int startIndex, int numItems);
	List<Freschkindamage> ListAll();
	void udpate(Freschkindamage freschkindamage);
	void removeByID(FreschkindamagePK freschkindamagePK);
}
