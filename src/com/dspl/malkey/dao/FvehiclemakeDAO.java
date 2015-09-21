package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fvehiclemake;

public interface FvehiclemakeDAO {
	int count();
	void create(Fvehiclemake fvehiclemake);
	Fvehiclemake findByID(String vehimakeid);
	List<Fvehiclemake> List(int startIndex, int numItems);
	List<Fvehiclemake> ListAll();
	void udpate(Fvehiclemake fvehiclemake);
	void removeByID(String vehimakeid);
}
