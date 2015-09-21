package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fvehicletype;

public interface FvehicletypeDAO {
	int count();
	Boolean create(Fvehicletype fvehicletype);
	Fvehicletype findByID(String vehitypeid);
	List<Fvehicletype> List(int startIndex, int numItems);
	List<Fvehicletype> ListAll();
	Boolean update(Fvehicletype fvehicletype);
	Boolean removeByID(String vehitypeid);
}
