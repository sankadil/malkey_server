package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fusertype;

public interface FusertypeSRV {
	int count();
	int countInAR(String UserTypeId);
	List<Fusertype> listAll();
	List<Fusertype> list(int startIndex, int numItems);
	Boolean create(Fusertype fusertype);
	Boolean update(Fusertype fusertype);
	Boolean removeById(String usertypeid);
	Fusertype findById(String usertypeid);
}
