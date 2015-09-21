package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fvehicletrans;

public interface FvehicletransDAO {
	int count();
	void create(Fvehicletrans fvehicletrans);
	Fvehicletrans findByID(String vehitransid);
	List<Fvehicletrans> List(int startIndex, int numItems);
	List<Fvehicletrans> ListAll();
	void udpate(Fvehicletrans fvehicletrans);
	void removeByID(String vehitransid);
}
