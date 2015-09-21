package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FclientdriverDAO;
import com.dspl.malkey.domain.Fclientdriver;

public class FclientdriverSRVImpl implements FclientdriverSRV {

	@Resource(name="fclientdriverDAO")
	FclientdriverDAO fclientdriverDAO;

	@Override
	public List<Fclientdriver> List(int startIndex, int numItems) {
		return fclientdriverDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fclientdriver> ListAll() {
		return fclientdriverDAO.ListAll();
	}

	@Override
	public int count() {
		return fclientdriverDAO.count();
	}

	@Override
	public Boolean create(Fclientdriver fclientdriver) {
		try {
			fclientdriverDAO.create(fclientdriver);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fclientdriver findByID(int recordid) {
		return fclientdriverDAO.findByID(recordid);
	}

	@Override
	public Boolean removeByID(int recordid) {
		try {
			fclientdriverDAO.removeByID(recordid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fclientdriver fclientdriver) {
		try {
			fclientdriverDAO.udpate(fclientdriver);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Fclientdriver> findByDebcode(String debcode) {
		return fclientdriverDAO.findByDebcode(debcode);
	}

	@Override
	public List<Fclientdriver> listByClientid(String clientID) {
		return fclientdriverDAO.listByClientid(clientID);
	}
}
