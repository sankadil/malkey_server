package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fsmssent;

public interface FsmssentDAO {
	int count();
	void create(Fsmssent fsmssent);
	Fsmssent findByID(int recordid);
	List<Fsmssent> List(int startIndex, int numItems);
	List<Fsmssent> ListAll();
	void udpate(Fsmssent fsmssent);
	void removeByID(int recordid);
}
