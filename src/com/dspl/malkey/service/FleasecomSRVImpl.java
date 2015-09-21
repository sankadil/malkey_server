package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FleasecomDAO;
import com.dspl.malkey.domain.Fleasecom;

public class FleasecomSRVImpl implements FleasecomSRV {

	@Resource(name="fleasecomDAO")
	FleasecomDAO fleasecomDAO;

	@Override
	public List<Fleasecom> List(int startIndex, int numItems) {
		return fleasecomDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fleasecom> ListAll() {
		return fleasecomDAO.ListAll();
	}

	@Override
	public int count() {
		return fleasecomDAO.count();
	}

	@Override
	public Boolean create(Fleasecom fleasecom) {
		try {
			fleasecomDAO.create(fleasecom);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fleasecom findByID(String leasecomid) {
		return fleasecomDAO.findByID(leasecomid);
	}

	@Override
	public Boolean removeByID(String leasecomid) {
		try {
			fleasecomDAO.removeByID(leasecomid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fleasecom fleasecom) {
		try {
			fleasecomDAO.udpate(fleasecom);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
