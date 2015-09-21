package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FclienttypeDAO;
import com.dspl.malkey.domain.Fclienttype;

public class FclienttypeSRVImpl implements FclienttypeSRV {

	@Resource(name="fclienttypeDAO")
	FclienttypeDAO fclienttypeDAO;

	@Override
	public List<Fclienttype> List(int startIndex, int numItems) {
		return fclienttypeDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fclienttype> ListAll() {
		return fclienttypeDAO.ListAll();
	}

	@Override
	public int count() {
		return fclienttypeDAO.count();
	}

	@Override
	public Boolean create(Fclienttype fclienttype) {
		try {
			fclienttypeDAO.create(fclienttype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fclienttype findByID(String clienttype) {
		return fclienttypeDAO.findByID(clienttype);
	}

	@Override
	public Boolean removeByID(String clienttype) {
		try {
			fclienttypeDAO.removeByID(clienttype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fclienttype fclienttype) {
		try {
			fclienttypeDAO.udpate(fclienttype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
