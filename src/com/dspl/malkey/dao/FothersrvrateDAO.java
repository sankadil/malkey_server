package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Faccrate;
import com.dspl.malkey.domain.Fothersrvrate;
import com.dspl.malkey.domain.FothersrvratePK;

public interface FothersrvrateDAO {
	int count();
	void create(Fothersrvrate fothersrvrate);
	Fothersrvrate findByID(FothersrvratePK fothersrvratePK);
	List<Fothersrvrate> List(int startIndex, int numItems);
	List<Fothersrvrate> ListAll();
	void udpate(Fothersrvrate fothersrvrate);
	void removeByID(FothersrvratePK fothersrvratePK);
	List<Fothersrvrate> findBySrvId(String srvid);
}
