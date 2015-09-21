package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FfueltypeDAO;
import com.dspl.malkey.domain.Ffueltype;

public class FfueltypeSRVImpl implements FfueltypeSRV {

	@Resource(name="ffueltypeDAO")
	FfueltypeDAO ffueltypeDAO;

	@Override
	public List<Ffueltype> List(int startIndex, int numItems) {
		return ffueltypeDAO.List(startIndex, numItems);
	}

	@Override
	public List<Ffueltype> ListAll() {
		return ffueltypeDAO.ListAll();
	}

	@Override
	public int count() {
		return ffueltypeDAO.count();
	}

	@Override
	public Boolean create(Ffueltype ffueltype) {
		try {
			ffueltypeDAO.create(ffueltype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Ffueltype findByID(String fueltypeid) {
		return ffueltypeDAO.findByID(fueltypeid);
	}

	@Override
	public Boolean removeByID(String fueltypeid) {
		try {
			ffueltypeDAO.removeByID(fueltypeid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Ffueltype ffueltype) {
		try {
			ffueltypeDAO.udpate(ffueltype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
