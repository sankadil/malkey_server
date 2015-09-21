package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fvehicleinventry;

public interface FvehicleinventrySRV {
	int count();
	Boolean create(Fvehicleinventry fvehicleinventry);
	Fvehicleinventry findByID(String regno);
	List<Fvehicleinventry> List(int startIndex, int numItems);
	List<Fvehicleinventry> ListAll();
	Boolean udpate(Fvehicleinventry fvehicleinventry);
	Boolean removeByID(String regno);
	List<Fvehicleinventry> listByRegNo(String regNo);
}
