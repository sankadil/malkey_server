package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Faccrate;
import com.dspl.malkey.domain.FaccratePK;

public interface FaccrateDAO {
	int count();
	void create(Faccrate faccrate);
	Faccrate findByID(FaccratePK faccratePK);
	List<Faccrate> List(int startIndex, int numItems);
	List<Faccrate> ListAll();
	void udpate(Faccrate faccrate);
	void removeByID(FaccratePK faccratePK);
	List<Faccrate> ListByClienttype(String clienttype);
	List<Faccrate> findByAccId(String accid);
}
