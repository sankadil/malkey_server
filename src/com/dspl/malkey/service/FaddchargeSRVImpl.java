package com.dspl.malkey.service;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FaddchargeDAO;
import com.dspl.malkey.dao.FmaintenanceDAO;
import com.dspl.malkey.domain.Faddcharge;

public class FaddchargeSRVImpl implements FaddchargeSRV {

	@Resource(name="faddchargeDAO")
	FaddchargeDAO faddchargeDAO;
	
	@Override
	public List<Faddcharge> List(int startIndex, int numItems) {
		return faddchargeDAO.List(startIndex, numItems);
	}

	@Override
	public List<Faddcharge> ListAll() {
		return faddchargeDAO.ListAll();
	}

	@Override
	public int count() {
		return faddchargeDAO.count();
	}

	@Override
	public Boolean create(Faddcharge faddcharge) {
		return faddchargeDAO.create(faddcharge);
	}

	@Override
	public Faddcharge findByID(String addchargeid) {
		return faddchargeDAO.findByID(addchargeid);
	}

	@Override
	public Boolean removeByID(String addchargeid) {
		return faddchargeDAO.removeByID(addchargeid);
	}

	@Override
	public Boolean update(Faddcharge faddcharge) {
		return faddchargeDAO.update(faddcharge);
	}

}
