package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fresvehicle;
import com.dspl.malkey.domain.FresvehiclePK;

public interface FresvehicleSRV {
	int count();
	Boolean create(Fresvehicle fresvehicle);
	Fresvehicle findByID(FresvehiclePK fresvehiclePK);
	List<Fresvehicle> List(int startIndex, int numItems);
	List<Fresvehicle> ListAll();
	Boolean udpate(Fresvehicle fresvehicle);
	Boolean removeByID(FresvehiclePK fresvehiclePK);
	List<Fresvehicle> listByResNo(String resno);
}
