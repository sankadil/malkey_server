package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fresvehinv;
import com.dspl.malkey.domain.FresvehinvPK;

public interface FresvehinvDAO {
	int count();
	void create(Fresvehinv fresvehinv);
	Fresvehinv findByID(FresvehinvPK fresvehinvPK);
	List<Fresvehinv> List(int startIndex, int numItems);
	List<Fresvehinv> ListAll();
	void udpate(Fresvehinv fresvehinv);
	void removeByID(FresvehinvPK fresvehinvPK);
	List<Fresvehinv> listByResno(String resno);
}
