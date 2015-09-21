package com.dspl.malkey.dao;

import java.sql.SQLException;
import java.util.List;

import com.dspl.malkey.domain.Fvehiclemodel;
import com.dspl.malkey.domain.FvehiclemodelPK;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public interface FvehiclemodelDAO {
	int count();
	Boolean create(Fvehiclemodel fvehiclemodel);
	Fvehiclemodel findByID(FvehiclemodelPK fvehiclemodelPK);
	List<Fvehiclemodel> List(int startIndex, int numItems);
	List<Fvehiclemodel> ListAll();
	Boolean update(Fvehiclemodel fvehiclemodel);
	Boolean removeByID(FvehiclemodelPK fvehiclemodelPK);
}
