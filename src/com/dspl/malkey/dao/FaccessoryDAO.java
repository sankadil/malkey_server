package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Faccessory;
import com.dspl.malkey.domain.Faccrate;

public interface FaccessoryDAO {
	int count();
	//void create(Faccessory faccessory);
	Faccessory findByID(String accid);
	List<Faccessory> List(int startIndex, int numItems);
	List<Faccessory> ListAll();
	//void update(Faccessory faccessory);
	//void removeByID(String accid);
	
	Boolean create(Faccessory faccessory, List<Faccrate> faccrates);
	Boolean removeByID(String accid);
	List<Faccessory> listAccessories();
	Boolean update(Faccessory faccessory, List<Faccrate> faccrates);
}
