package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fvehicleinventry;

public interface FvehicleinventryDAO {
	int count();
	void create(Fvehicleinventry fvehicleinventry);
	Fvehicleinventry findByID(String regno);
	List<Fvehicleinventry> List(int startIndex, int numItems);
	List<Fvehicleinventry> ListAll();
	void udpate(Fvehicleinventry fvehicleinventry);
	void removeByID(String regno);
	List<Fvehicleinventry> listByRegNo(String regNo);
}
