package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Femailsent;

public interface FemailsentDAO {
	int count();
	void create(Femailsent femailsent);
	Femailsent findByID(String emailid);
	List<Femailsent> List(int startIndex, int numItems);
	List<Femailsent> ListAll();
	void udpate(Femailsent femailsent);
	void removeByID(String emailid);
}
