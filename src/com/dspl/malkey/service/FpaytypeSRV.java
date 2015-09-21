package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fpaytype;

public interface FpaytypeSRV {
	int count();
	Boolean create(Fpaytype fpaytype);
	Fpaytype findByID(String paytypeid);
	List<Fpaytype> List(int startIndex, int numItems);
	List<Fpaytype> ListAll();
	Boolean udpate(Fpaytype fpaytype);
	Boolean removeByID(String paytypeid);
}
