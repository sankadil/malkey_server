package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Ftax;

public interface FtaxSRV {
	int count();
	List<Ftax> listAll();
	List<Ftax> list(int startIndex, int numItems);
	Boolean create(Ftax ftax);
	Boolean update(Ftax ftax);
	Boolean removeById(String taxcode);
	Ftax findById(String taxcode);
}
