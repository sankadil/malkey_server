package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Faccessory;
import com.dspl.malkey.domain.Faccrate;

public interface FaccessorySRV {
	int count();
//	Boolean create(Faccessory faccessory);
	Faccessory findByID(String accid);
	List<Faccessory> List(int startIndex, int numItems);
	List<Faccessory> ListAll();
//	Boolean removeByID(String accid);
//	Boolean udpate(Faccessory faccessory);
	
	List<Faccessory> listAccessories();
	Boolean create(Faccessory faccessory,List<Faccrate> faccrates);
	Boolean update(Faccessory faccessory,List<Faccrate> faccrates);
	Boolean removeByID(String accid);
}
