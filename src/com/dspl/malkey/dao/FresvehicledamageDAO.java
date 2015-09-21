package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fresvehicledamage;

public interface FresvehicledamageDAO {
	int count();
	void create(Fresvehicledamage fvehicledamage);
	Fresvehicledamage findByID(String vehidamageid);
	List<Fresvehicledamage> List(int startIndex, int numItems);
	List<Fresvehicledamage> ListAll();
	void update(Fresvehicledamage fvehicledamage);
	void removeByID(String vehidamageid);
	List<Fresvehicledamage> listByRegNo(String regNo,String resNo);
}
