package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fhirestatus;

public interface FhirestatusSRV {
	int count();
	Boolean create(Fhirestatus fhirestatus);
	Fhirestatus findByID(String hirestsid);
	List<Fhirestatus> List(int startIndex, int numItems);
	List<Fhirestatus> ListAll();
	Boolean udpate(Fhirestatus fhirestatus);
	Boolean removeByID(String hirestsid);
}
