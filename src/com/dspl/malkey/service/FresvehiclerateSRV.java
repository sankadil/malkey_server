package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fresvehicle;
import com.dspl.malkey.domain.Fresvehiclerate;

public interface FresvehiclerateSRV {
	int count();
	void create(Fresvehiclerate fresvehiclerate);
	Fresvehiclerate findByID(String resno);
	List<Fresvehiclerate> list(int startIndex, int numItems);
	List<Fresvehiclerate> listAll();
	void update(Fresvehiclerate faccrate);
	void removeByID(String resno);
	List<Fresvehiclerate> listByResno(String resno);
}
