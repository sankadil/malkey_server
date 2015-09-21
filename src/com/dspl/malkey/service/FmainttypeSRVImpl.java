package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FmainttypeDAO;
import com.dspl.malkey.domain.Fmainttype;

public class FmainttypeSRVImpl implements FmainttypeSRV {

	@Resource(name="fmainttypeDAO")
	FmainttypeDAO fmainttypeDAO;

	@Override
	public List<Fmainttype> List(int startIndex, int numItems) {
		return fmainttypeDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fmainttype> ListAll() {
		return fmainttypeDAO.ListAll();
	}

	@Override
	public int count() {
		return fmainttypeDAO.count();
	}

	@Override
	public Boolean create(Fmainttype fmainttype) {
		try {
			fmainttypeDAO.create(fmainttype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fmainttype findByID(String typeid) {
		return fmainttypeDAO.findByID(typeid);
	}

	@Override
	public Boolean removeByID(String typeid) {
		try {
			fmainttypeDAO.removeByID(typeid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fmainttype fmainttype) {
		try {
			fmainttypeDAO.udpate(fmainttype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
