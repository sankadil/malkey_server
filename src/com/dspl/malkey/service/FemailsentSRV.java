package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Femailsent;

public interface FemailsentSRV {
	int count();
	Boolean create(Femailsent femailsent);
	Femailsent findByID(String emailid);
	List<Femailsent> List(int startIndex, int numItems);
	List<Femailsent> ListAll();
	Boolean udpate(Femailsent femailsent);
	Boolean removeByID(String emailid);
}
