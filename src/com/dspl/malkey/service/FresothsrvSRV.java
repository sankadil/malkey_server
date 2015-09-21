package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fresothsrv;
import com.dspl.malkey.domain.FresothsrvPK;

public interface FresothsrvSRV {
	int count();
	Boolean create(Fresothsrv fresothsrv);
	Fresothsrv findByID(FresothsrvPK fresothsrvPK);
	List<Fresothsrv> List(int startIndex, int numItems);
	List<Fresothsrv> ListAll();
	Boolean udpate(Fresothsrv fresothsrv);
	Boolean removeByID(FresothsrvPK fresothsrvPK);
	List<Fresothsrv> listByResNo(String resno);
}
