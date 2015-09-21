package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fhiretype;

public interface FhiretypeDAO {
	int count();
	void create(Fhiretype fhiretype);
	Fhiretype findByID(String hiretypeid);
	List<Fhiretype> List(int startIndex, int numItems);
	List<Fhiretype> ListAll();
	void udpate(Fhiretype fhiretype);
	void removeByID(String hiretypeid);
}
