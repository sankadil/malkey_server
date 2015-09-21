package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FvehiclemakeDAO;
import com.dspl.malkey.domain.Fvehiclemake;

public class FvehiclemakeSRVImpl implements FvehiclemakeSRV {

	@Resource(name="fvehiclemakeDAO")
	FvehiclemakeDAO fvehiclemakeDAO;

	@Override
	public List<Fvehiclemake> List(int startIndex, int numItems) {
		return fvehiclemakeDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fvehiclemake> ListAll() {
		return fvehiclemakeDAO.ListAll();
	}

	@Override
	public int count() {
		return fvehiclemakeDAO.count();
	}

	@Override
	public Boolean create(Fvehiclemake fvehiclemake) {
		try {
			fvehiclemakeDAO.create(fvehiclemake);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fvehiclemake findByID(String vehimakeid) {
		return fvehiclemakeDAO.findByID(vehimakeid);
	}

	@Override
	public Boolean removeByID(String vehimakeid) {
		try {
			fvehiclemakeDAO.removeByID(vehimakeid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fvehiclemake fvehiclemake) {
		try {
			fvehiclemakeDAO.udpate(fvehiclemake);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
