package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Ftaxdet;
import com.dspl.malkey.domain.FtaxdetPK;

public interface FtaxdetDAO {
	int count();
	List<Ftaxdet> listAll();
	List<Ftaxdet> list(int startIndex, int numItems);
	void create(Ftaxdet ftaxdet);
	void update(Ftaxdet ftaxdet);
	void removeById(FtaxdetPK ftaxdetpk);
	Ftaxdet findById(FtaxdetPK ftaxdetpk);
	Ftaxdet findByTaxcomcode(String taxcomcode);
}
