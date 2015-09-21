package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fvehicleclass;

public interface FvehicleclassSRV {
	int count();
	Boolean create(Fvehicleclass fvehicleclass);
	Fvehicleclass findByID(String vehiclassid);
	List<Fvehicleclass> List(int startIndex, int numItems);
	List<Fvehicleclass> ListAll();
	Boolean udpate(Fvehicleclass fvehicleclass);
	Boolean removeByID(String vehiclassid);
}
