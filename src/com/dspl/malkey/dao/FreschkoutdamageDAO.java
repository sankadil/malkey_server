package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Freschkoutdamage;
import com.dspl.malkey.domain.FreschkoutdamagePK;

public interface FreschkoutdamageDAO {
	int count();
	void create(Freschkoutdamage freschkoutdamage);
	Freschkoutdamage findByID(FreschkoutdamagePK freschkoutdamagePK);
	List<Freschkoutdamage> List(int startIndex, int numItems);
	List<Freschkoutdamage> ListAll();
	void udpate(Freschkoutdamage freschkoutdamage);
	void removeByID(FreschkoutdamagePK freschkoutdamagePK);
}
