package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Facctype;

public interface FacctypeSRV {
	int count();
	Boolean create(Facctype facctype);
	Facctype findByID(String acctypeid);
	List<Facctype> List(int startIndex, int numItems);
	List<Facctype> ListAll();
	Boolean udpate(Facctype facctype);
	Boolean removeByID(String acctypeid);
}
