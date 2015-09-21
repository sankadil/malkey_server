package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fresvehicle;
import com.dspl.malkey.domain.FresvehiclePK;

public interface FresvehicleDAO {
	int count();
	void create(Fresvehicle fresvehicle);
	Fresvehicle findByID(FresvehiclePK fresvehiclePK);
	List<Fresvehicle> List(int startIndex, int numItems);
	List<Fresvehicle> ListAll();
	void udpate(Fresvehicle fresvehicle);
	void removeByID(FresvehiclePK fresvehiclePK);
	List<Fresvehicle> listByResNo(String resno);
}
