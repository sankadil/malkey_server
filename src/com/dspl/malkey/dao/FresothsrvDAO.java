package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fresothsrv;
import com.dspl.malkey.domain.FresothsrvPK;

public interface FresothsrvDAO {
	int count();
	void create(Fresothsrv fresothsrv);
	Fresothsrv findByID(FresothsrvPK fresothsrvPK);
	List<Fresothsrv> List(int startIndex, int numItems);
	List<Fresothsrv> ListAll();
	void udpate(Fresothsrv fresothsrv);
	void removeByID(FresothsrvPK fresothsrvPK);
	List<Fresothsrv> listByResNo(String resno);
}
