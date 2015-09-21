package com.dspl.malkey.service;

import java.sql.SQLException;
import java.util.List;

import com.dspl.malkey.domain.Fvehiclemodel;
import com.dspl.malkey.domain.FvehiclemodelPK;

public interface FvehiclemodelSRV {
	int count();
	Boolean create(Fvehiclemodel fvehiclemodel);
	Fvehiclemodel findByID(FvehiclemodelPK fvehiclemodelPK);
	List<Fvehiclemodel> List(int startIndex, int numItems);
	List<Fvehiclemodel> ListAll();
	Boolean udpate(Fvehiclemodel fvehiclemodel);
	Boolean removeByID(FvehiclemodelPK fvehiclemodelPK);
}
