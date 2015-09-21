package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fvehiclestatus;

public interface FvehiclestatusSRV {
	int count();
	Boolean create(Fvehiclestatus fvehiclestatus);
	Fvehiclestatus findByID(String vehistsid);
	List<Fvehiclestatus> List(int startIndex, int numItems);
	List<Fvehiclestatus> ListAll();
	Boolean udpate(Fvehiclestatus fvehiclestatus);
	Boolean removeByID(String vehistsid);
}
