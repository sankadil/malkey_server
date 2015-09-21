package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FvehicledamageDAO;
import com.dspl.malkey.domain.Fvehicledamage;

public class FvehicledamageSRVImpl implements FvehicledamageSRV {

	@Resource(name="fvehicledamageDAO")
	FvehicledamageDAO fvehicledamageDAO;

	@Override
	public List<Fvehicledamage> List(int startIndex, int numItems) {
		return fvehicledamageDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fvehicledamage> ListAll() {
		return fvehicledamageDAO.ListAll();
	}

	@Override
	public int count() {
		return fvehicledamageDAO.count();
	}

	@Override
	public Boolean create(Fvehicledamage fvehicledamage) {
		try {
			fvehicledamageDAO.create(fvehicledamage);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fvehicledamage findByID(String vehidamageid) {
		return fvehicledamageDAO.findByID(vehidamageid);
	}

	@Override
	public Boolean removeByID(String vehidamageid) {
		try {
			fvehicledamageDAO.removeByID(vehidamageid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fvehicledamage fvehicledamage) {
		try {
			fvehicledamageDAO.udpate(fvehicledamage);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Fvehicledamage> listByRegNo(String regNo) {
		return fvehicledamageDAO.listByRegNo(regNo);
	}

}
