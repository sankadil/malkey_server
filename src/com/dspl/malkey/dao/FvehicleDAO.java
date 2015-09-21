package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fexpirationlistingrpt;
import com.dspl.malkey.domain.Fvehicle;
import com.dspl.malkey.domain.Fvehicledamage;
import com.dspl.malkey.domain.Fvehicleinventry;
import com.dspl.malkey.domain.Fvehiclepic;
import com.dspl.malkey.domain.Fvehiclerate;

public interface FvehicleDAO {
	int count();
	void create(Fvehicle fvehicle, List<Fvehicledamage> fvehicledamage, List<Fvehicleinventry> fvehicleinventry, List<Fvehiclerate> fvehiclerate, List<Fvehiclepic> fvehiclepic);
	Fvehicle findByID(String regNo);
	List<Fvehicle> list(int startIndex, int numItems);
	List<Fvehicle> listAll();
	void update(Fvehicle fvehicle, List<Fvehicledamage> fvehicledamage, List<Fvehicleinventry> fvehicleinventry, List<Fvehiclerate> fvehiclerate, List<Fvehiclepic> fvehiclepic);
	void removeByID(String regNo);
	
	List<Fexpirationlistingrpt> getExpirationList(String fromDate,String toDate,String expType);
	List<Fvehicle> getVehicleSummary();
	int isVehicleRemovable(String regNo);
	List<Fvehicle> getVehicleList(String locationId);
	List<Fvehicle> getVehicleListCustomized(String vstatus, String ownertype);
	List<Fvehicle> getVehicleSummary2(String vstatus, String ownertype);
}

