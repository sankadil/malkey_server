package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fvehiclemake;

public interface FvehiclemakeSRV {
	int count();
	Boolean create(Fvehiclemake fvehiclemake);
	Fvehiclemake findByID(String vehimakeid);
	List<Fvehiclemake> List(int startIndex, int numItems);
	List<Fvehiclemake> ListAll();
	Boolean udpate(Fvehiclemake fvehiclemake);
	Boolean removeByID(String vehimakeid);
}
