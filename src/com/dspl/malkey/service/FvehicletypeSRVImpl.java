package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FvehicletypeDAO;
import com.dspl.malkey.domain.Fvehicletype;

public class FvehicletypeSRVImpl implements FvehicletypeSRV {

	@Resource(name="fvehicletypeDAO")
	FvehicletypeDAO fvehicletypeDAO;

	@Override
	public List<Fvehicletype> List(int startIndex, int numItems) {
		return fvehicletypeDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fvehicletype> ListAll() {
		return fvehicletypeDAO.ListAll();
	}

	@Override
	public int count() {
		return fvehicletypeDAO.count();
	}

	@Override
	public Boolean create(Fvehicletype fvehicletype) {
		try {
			return fvehicletypeDAO.create(fvehicletype);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fvehicletype findByID(String vehitypeid) {
		return fvehicletypeDAO.findByID(vehitypeid);
	}

	@Override
	public Boolean removeByID(String vehitypeid) {
		try {
			return fvehicletypeDAO.removeByID(vehitypeid);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean update(Fvehicletype fvehicletype) {
		try {
			return fvehicletypeDAO.update(fvehicletype);
		} catch (Exception e) {
			return false;
		}
	}

}
