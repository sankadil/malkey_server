package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fvehiclestatus;

public interface FvehiclestatusDAO {
	int count();
	void create(Fvehiclestatus fvehiclestatus);
	Fvehiclestatus findByID(String vehistsid);
	List<Fvehiclestatus> List(int startIndex, int numItems);
	List<Fvehiclestatus> ListAll();
	void udpate(Fvehiclestatus fvehiclestatus);
	void removeByID(String vehistsid);
}
