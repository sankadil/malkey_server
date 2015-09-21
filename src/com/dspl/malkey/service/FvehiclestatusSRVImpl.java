package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FvehiclestatusDAO;
import com.dspl.malkey.domain.Fvehiclestatus;

public class FvehiclestatusSRVImpl implements FvehiclestatusSRV {

	@Resource(name="fvehiclestatusDAO")
	FvehiclestatusDAO fvehiclestatusDAO;

	@Override
	public List<Fvehiclestatus> List(int startIndex, int numItems) {
		return fvehiclestatusDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fvehiclestatus> ListAll() {
		return fvehiclestatusDAO.ListAll();
	}

	@Override
	public int count() {
		return fvehiclestatusDAO.count();
	}

	@Override
	public Boolean create(Fvehiclestatus fvehiclestatus) {
		try {
			fvehiclestatusDAO.create(fvehiclestatus);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fvehiclestatus findByID(String vehistsid) {
		return fvehiclestatusDAO.findByID(vehistsid);
	}

	@Override
	public Boolean removeByID(String vehistsid) {
		try {
			fvehiclestatusDAO.removeByID(vehistsid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fvehiclestatus fvehiclestatus) {
		try {
			fvehiclestatusDAO.udpate(fvehiclestatus);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
