package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fresvehicledamage;

public interface FresvehicledamageSRV {
	int count();
	Boolean create(Fresvehicledamage fvehicledamage);
	Fresvehicledamage findByID(String vehidamageid);
	List<Fresvehicledamage> List(int startIndex, int numItems);
	List<Fresvehicledamage> ListAll();
	Boolean update(Fresvehicledamage fvehicledamage);
	Boolean removeByID(String vehidamageid);
	List<Fresvehicledamage> listByRegNo(String regNo,String resNo);
}
