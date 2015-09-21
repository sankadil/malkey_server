package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fvehicledamage;

public interface FvehicledamageDAO {
	int count();
	void create(Fvehicledamage fvehicledamage);
	Fvehicledamage findByID(String vehidamageid);
	List<Fvehicledamage> List(int startIndex, int numItems);
	List<Fvehicledamage> ListAll();
	void udpate(Fvehicledamage fvehicledamage);
	void removeByID(String vehidamageid);
	List<Fvehicledamage> listByRegNo(String regNo);
}
