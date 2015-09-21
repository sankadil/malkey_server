package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Femailinbox;

public interface FemailinboxSRV {
	int count();
	Boolean create(Femailinbox femailinbox);
	Femailinbox findByID(String emailid);
	List<Femailinbox> List(int startIndex, int numItems);
	List<Femailinbox> ListAll();
	Boolean udpate(Femailinbox femailinbox);
	Boolean removeByID(String emailid);
}
