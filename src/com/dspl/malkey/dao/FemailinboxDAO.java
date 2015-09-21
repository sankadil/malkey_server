package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Femailinbox;

public interface FemailinboxDAO {
	int count();
	void create(Femailinbox femailinbox);
	Femailinbox findByID(String emailid);
	List<Femailinbox> List(int startIndex, int numItems);
	List<Femailinbox> ListAll();
	void udpate(Femailinbox femailinbox);
	void removeByID(String emailid);
}
