package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FinsfleetDAO;
import com.dspl.malkey.domain.Finsfleet;

public class FinsfleetSRVImpl implements FinsfleetSRV {

	@Resource(name="finsfleetDAO")
	FinsfleetDAO finsfleetDAO;

	@Override
	public List<Finsfleet> List(int startIndex, int numItems) {
		return finsfleetDAO.List(startIndex, numItems);
	}

	@Override
	public List<Finsfleet> ListAll() {
		return finsfleetDAO.ListAll();
	}

	@Override
	public int count() {
		return finsfleetDAO.count();
	}

	@Override
	public Boolean create(Finsfleet finsfleet) {
		try {
			finsfleetDAO.create(finsfleet);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Finsfleet findByID(String fleetid) {
		return finsfleetDAO.findByID(fleetid);
	}

	@Override
	public Boolean removeByID(String fleetid) {
		try {
			finsfleetDAO.removeByID(fleetid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Finsfleet finsfleet) {
		try {
			finsfleetDAO.udpate(finsfleet);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
