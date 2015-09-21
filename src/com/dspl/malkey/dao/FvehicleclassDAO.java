package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fvehicleclass;

public interface FvehicleclassDAO {
	int count();
	void create(Fvehicleclass fvehicleclass);
	Fvehicleclass findByID(String vehiclassid);
	List<Fvehicleclass> List(int startIndex, int numItems);
	List<Fvehicleclass> ListAll();
	void udpate(Fvehicleclass fvehicleclass);
	void removeByID(String vehiclassid);
}
