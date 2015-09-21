package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fhiretype;

public interface FhiretypeSRV {
	int count();
	Boolean create(Fhiretype fhiretype);
	Fhiretype findByID(String hiretypeid);
	List<Fhiretype> List(int startIndex, int numItems);
	List<Fhiretype> ListAll();
	Boolean udpate(Fhiretype fhiretype);
	Boolean removeByID(String hiretypeid);
}
