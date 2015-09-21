package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FvehicleclassDAO;
import com.dspl.malkey.domain.Fvehicleclass;

public class FvehicleclassSRVImpl implements FvehicleclassSRV {

	@Resource(name="fvehicleclassDAO")
	FvehicleclassDAO fvehicleclassDAO;

	@Override
	public List<Fvehicleclass> List(int startIndex, int numItems) {
		return fvehicleclassDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fvehicleclass> ListAll() {
		return fvehicleclassDAO.ListAll();
	}

	@Override
	public int count() {
		return fvehicleclassDAO.count();
	}

	@Override
	public Boolean create(Fvehicleclass fvehicleclass) {
		try {
			fvehicleclassDAO.create(fvehicleclass);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fvehicleclass findByID(String vehiclassid) {
		return fvehicleclassDAO.findByID(vehiclassid);
	}

	@Override
	public Boolean removeByID(String vehiclassid) {
		try {
			fvehicleclassDAO.removeByID(vehiclassid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fvehicleclass fvehicleclass) {
		try {
			fvehicleclassDAO.udpate(fvehicleclass);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
