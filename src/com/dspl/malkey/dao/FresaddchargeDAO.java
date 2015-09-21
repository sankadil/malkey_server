package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fresaddcharge;
import com.dspl.malkey.domain.FresaddchargePK;

public interface FresaddchargeDAO {
	int count();
	void create(Fresaddcharge fresaddcharge);
	Fresaddcharge findByID(FresaddchargePK fresaddchargePK);
	List<Fresaddcharge> List(int startIndex, int numItems);
	List<Fresaddcharge> ListAll();
	void udpate(Fresaddcharge fresaddcharge);
	void removeByID(FresaddchargePK fresaddchargePK);
	List<Fresaddcharge> listByresno(String resno);
}
