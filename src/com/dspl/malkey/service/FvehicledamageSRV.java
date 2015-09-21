package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fvehicledamage;

public interface FvehicledamageSRV {
	int count();
	Boolean create(Fvehicledamage fvehicledamage);
	Fvehicledamage findByID(String vehidamageid);
	List<Fvehicledamage> List(int startIndex, int numItems);
	List<Fvehicledamage> ListAll();
	Boolean udpate(Fvehicledamage fvehicledamage);
	Boolean removeByID(String vehidamageid);
	List<Fvehicledamage> listByRegNo(String regNo);
}
