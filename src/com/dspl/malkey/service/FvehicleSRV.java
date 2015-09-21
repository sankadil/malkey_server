package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fexpirationlistingrpt;
import com.dspl.malkey.domain.Fvehicle;
import com.dspl.malkey.domain.Fvehicledamage;
import com.dspl.malkey.domain.Fvehicleinventry;
import com.dspl.malkey.domain.Fvehiclepic;
import com.dspl.malkey.domain.Fvehiclerate;

public interface FvehicleSRV {
	int count();
	Boolean create(Fvehicle fvehicle, List<Fvehicledamage> fvehicledamage, List<Fvehicleinventry> fvehicleinventry, List<Fvehiclerate> fvehiclerate, List<Fvehiclepic> fvehiclepic);
	Fvehicle findByID(String regNo);
	List<Fvehicle> list(int startIndex, int numItems);
	List<Fvehicle> listAll();
	Boolean update(Fvehicle fvehicle, List<Fvehicledamage> fvehicledamage, List<Fvehicleinventry> fvehicleinventry, List<Fvehiclerate> fvehiclerate, List<Fvehiclepic> fvehiclepic);
	Boolean removeByID(String regNo);
	
	List<Fexpirationlistingrpt> getExpirationList(String fromDate,String toDate,String expType);
	List<Fvehicle> getVehicleSummary();
	Boolean isVehicleRemovable(String regNo);
	List<Fvehicle> getVehicleList(String locationId);
	List<Fvehicle> getVehicleSummary2(String vstatus,String ownertype);
}
