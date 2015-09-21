package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FlocationDAO;
import com.dspl.malkey.domain.Flocation;

public class FlocationSRVImpl implements FlocationSRV {

	@Resource(name="flocationDAO")
	FlocationDAO flocationDAO;

	@Override
	public List<Flocation> List(int startIndex, int numItems) {
		return flocationDAO.List(startIndex, numItems);
	}

	@Override
	public List<Flocation> ListAll() {
		return flocationDAO.ListAll();
	}

	@Override
	public int count() {
		return flocationDAO.count();
	}

	@Override
	public Boolean create(Flocation flocation) {
		try {
			flocationDAO.create(flocation);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Flocation findByID(String locationid) {
		return flocationDAO.findByID(locationid);
	}

	@Override
	public Boolean removeByID(String locationid) {
		try {
			flocationDAO.removeByID(locationid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Flocation flocation) {
		try {
			flocationDAO.udpate(flocation);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Flocation> ListCheckIn(String stChkIn) {
		// TODO Auto-generated method stub
		return flocationDAO.ListCheckIn(stChkIn);
	}

}
