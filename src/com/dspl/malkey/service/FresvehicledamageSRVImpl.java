package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FresvehicledamageDAO;
import com.dspl.malkey.domain.Fresvehicledamage;

public class FresvehicledamageSRVImpl implements FresvehicledamageSRV {

	@Resource(name="fresvehicledamageDAO")
	FresvehicledamageDAO fresvehicledamageDAO;

	@Override
	public List<Fresvehicledamage> List(int startIndex, int numItems) {
		return fresvehicledamageDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fresvehicledamage> ListAll() {
		return fresvehicledamageDAO.ListAll();
	}

	@Override
	public int count() {
		return fresvehicledamageDAO.count();
	}

	@Override
	public Boolean create(Fresvehicledamage fvehicledamage) {
		try {
			fresvehicledamageDAO.create(fvehicledamage);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fresvehicledamage findByID(String vehidamageid) {
		return fresvehicledamageDAO.findByID(vehidamageid);
	}

	@Override
	public Boolean removeByID(String vehidamageid) {
		try {
			fresvehicledamageDAO.removeByID(vehidamageid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean update(Fresvehicledamage fvehicledamage) {
		try {
			fresvehicledamageDAO.update(fvehicledamage);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Fresvehicledamage> listByRegNo(String regNo,String resNo) {
		return fresvehicledamageDAO.listByRegNo(regNo, resNo);
	}

}
