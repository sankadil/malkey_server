package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fvehiclerate;
import com.dspl.malkey.domain.FvehicleratePK;

public interface FvehiclerateDAO {
	int count();
	void create(Fvehiclerate fvehiclerate);
	Fvehiclerate findByID(FvehicleratePK fvehicleratePK);
	List<Fvehiclerate> List(int startIndex, int numItems);
	List<Fvehiclerate> ListAll();
	void udpate(Fvehiclerate fvehiclerate);
	void removeByID(FvehicleratePK fvehicleratePK);
	List<Fvehiclerate> listByVehiModelID(String vehiModelID);
	List<Fvehiclerate> findByIDList(List<FvehicleratePK> lstfvehicleratePK);
	void udpateList(String sVehiModelID, List<Fvehiclerate> fvehiclerate);	
	List<Fvehiclerate> getRateList(String vehimodelid);
}
