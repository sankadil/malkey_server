package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Facctype;

public interface FacctypeDAO {
	int count();
	void create(Facctype facctype);
	Facctype findByID(String acctypeid);
	List<Facctype> List(int startIndex, int numItems);
	List<Facctype> ListAll();
	void udpate(Facctype facctype);
	void removeByID(String acctypeid);
}
