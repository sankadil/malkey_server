package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fvehicletrans;

public interface FvehicletransSRV {
	int count();
	Boolean create(Fvehicletrans fvehicletrans);
	Fvehicletrans findByID(String vehitransid);
	List<Fvehicletrans> List(int startIndex, int numItems);
	List<Fvehicletrans> ListAll();
	Boolean udpate(Fvehicletrans fvehicletrans);
	Boolean removeByID(String vehitransid);
}
