package com.dspl.malkey.service;

import java.util.List;
import com.dspl.malkey.domain.Ftaxdet;
import com.dspl.malkey.domain.FtaxdetPK;

public interface FtaxdetSRV {
	int count();
	List<Ftaxdet> listAll();
	List<Ftaxdet> list(int startIndex, int numItems);
	Boolean create(Ftaxdet ftaxdet);
	Boolean update(Ftaxdet ftaxdet);
	Boolean removeById(FtaxdetPK ftaxdetpk);
	Ftaxdet findById(FtaxdetPK ftaxdetpk);
}
