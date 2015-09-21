package com.dspl.malkey.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;

import com.dspl.malkey.dao.FvehiclemodelDAO;
import com.dspl.malkey.domain.Fvehiclemodel;
import com.dspl.malkey.domain.FvehiclemodelPK;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class FvehiclemodelSRVImpl implements FvehiclemodelSRV {

	@Resource(name="fvehiclemodelDAO")
	FvehiclemodelDAO fvehiclemodelDAO;

	@Override
	public List<Fvehiclemodel> List(int startIndex, int numItems) {
		return fvehiclemodelDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fvehiclemodel> ListAll() {
		return fvehiclemodelDAO.ListAll();
	}

	@Override
	public int count() {
		return fvehiclemodelDAO.count();
	}

	@Override
	public Boolean create(Fvehiclemodel fvehiclemodel) {
		return fvehiclemodelDAO.create(fvehiclemodel);
	}

	@Override
	public Fvehiclemodel findByID(FvehiclemodelPK fvehiclemodelPK) {
		return fvehiclemodelDAO.findByID(fvehiclemodelPK);
	}

	@Override
	public Boolean removeByID(FvehiclemodelPK fvehiclemodelPK){
		try{
			return fvehiclemodelDAO.removeByID(fvehiclemodelPK);
		}catch(Exception ex){
			System.out.println("Fvehiclemodel removeById: " + ex.getMessage());
		}
		return null;
	}

	@Override
	public Boolean udpate(Fvehiclemodel fvehiclemodel) {
		return fvehiclemodelDAO.update(fvehiclemodel);
	}

}
