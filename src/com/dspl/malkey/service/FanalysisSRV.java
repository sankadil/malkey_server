package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fanalysis;

public interface FanalysisSRV {
	int count();
	Boolean create(Fanalysis fanalysis);
	Fanalysis findByID(String ancode);
	List<Fanalysis> list(int startIndex, int numItems);
	List<Fanalysis> listAll();
	List<Fanalysis> listByAnCodeOfVehicle(String ancode);
	Boolean udpate(Fanalysis fanalysis);
	Boolean removeByID(String ancode);
}
