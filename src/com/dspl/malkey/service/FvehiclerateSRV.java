package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fvehiclerate;
import com.dspl.malkey.domain.FvehicleratePK;

public interface FvehiclerateSRV {
	int count();
	Boolean create(Fvehiclerate fvehiclerate);
	Fvehiclerate findByID(FvehicleratePK fvehicleratePK);
	List<Fvehiclerate> List(int startIndex, int numItems);
	List<Fvehiclerate> ListAll();
	Boolean udpate(Fvehiclerate fvehiclerate);
	Boolean removeByID(FvehicleratePK fvehicleratePK);
	List<Fvehiclerate> listByVehiModelID(String vehiModelID);
	List<Fvehiclerate> findByIDList(List<FvehicleratePK> lstfvehicleratePK);
	Boolean udpateList(String sVehiModelID, List<Fvehiclerate> fvehiclerate);
	List<Fvehiclerate> getRateList(String vehimodelid);
}
