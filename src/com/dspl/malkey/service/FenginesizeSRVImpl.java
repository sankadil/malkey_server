package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FenginesizeDAO;
import com.dspl.malkey.domain.Fenginesize;

public class FenginesizeSRVImpl implements FenginesizeSRV {

	@Resource(name="fenginesizeDAO")
	FenginesizeDAO fenginesizeDAO;

	@Override
	public List<Fenginesize> List(int startIndex, int numItems) {
		return fenginesizeDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fenginesize> ListAll() {
		return fenginesizeDAO.ListAll();
	}

	@Override
	public int count() {
		return fenginesizeDAO.count();
	}

	@Override
	public Boolean create(Fenginesize fenginesize) {
		try {
			fenginesizeDAO.create(fenginesize);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fenginesize findByID(String engsizeid) {
		return fenginesizeDAO.findByID(engsizeid);
	}

	@Override
	public Boolean removeByID(String engsizeid) {
		try {
			fenginesizeDAO.removeByID(engsizeid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fenginesize fenginesize) {
		try {
			fenginesizeDAO.udpate(fenginesize);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
