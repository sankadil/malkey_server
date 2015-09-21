package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fanalysis;

public interface FanalysisDAO {
	int count();
	void create(Fanalysis fanalysis);
	Fanalysis findByID(String ancode);
	List<Fanalysis> list(int startIndex, int numItems);
	List<Fanalysis> listAll();
	List<Fanalysis> listByAnCodeOfVehicle(String ancode);
	void udpate(Fanalysis fanalysis);
	void removeByID(String ancode);
}
