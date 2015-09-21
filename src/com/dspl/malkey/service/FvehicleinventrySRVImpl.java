package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FvehicleinventryDAO;
import com.dspl.malkey.domain.Fvehicleinventry;

public class FvehicleinventrySRVImpl implements FvehicleinventrySRV {

	@Resource(name="fvehicleinventryDAO")
	FvehicleinventryDAO fvehicleinventryDAO;

	@Override
	public List<Fvehicleinventry> List(int startIndex, int numItems) {
		return fvehicleinventryDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fvehicleinventry> ListAll() {
		return fvehicleinventryDAO.ListAll();
	}

	@Override
	public int count() {
		return fvehicleinventryDAO.count();
	}

	@Override
	public Boolean create(Fvehicleinventry fvehicleinventry) {
		try {
			fvehicleinventryDAO.create(fvehicleinventry);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fvehicleinventry findByID(String regno) {
		return fvehicleinventryDAO.findByID(regno);
	}

	@Override
	public Boolean removeByID(String regno) {
		try {
			fvehicleinventryDAO.removeByID(regno);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fvehicleinventry fvehicleinventry) {
		try {
			fvehicleinventryDAO.udpate(fvehicleinventry);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Fvehicleinventry> listByRegNo(String regNo) {
		return fvehicleinventryDAO.listByRegNo(regNo);
	}

}
