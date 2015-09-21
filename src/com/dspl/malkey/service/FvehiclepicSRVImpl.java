package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FvehiclepicDAO;
import com.dspl.malkey.domain.Fvehiclepic;

public class FvehiclepicSRVImpl implements FvehiclepicSRV {
	
	@Resource(name="fvehiclepicDAO")
	FvehiclepicDAO fvehiclepicDAO;
	
	@Override
	public int count() {
		return fvehiclepicDAO.count();
	}

	@Override
	public Boolean create(Fvehiclepic fvehiclepic) {
		try {
			fvehiclepicDAO.create(fvehiclepic);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fvehiclepic findByID(String regNo) {
		return fvehiclepicDAO.findByID(regNo);
	}

	@Override
	public List<Fvehiclepic> list(int startIndex, int numItems) {
		return fvehiclepicDAO.list(startIndex, numItems);
	}

	@Override
	public List<Fvehiclepic> listAll() {
		return fvehiclepicDAO.listAll();
	}

	@Override
	public List<Fvehiclepic> listByRegNo(String regNo) {
		return fvehiclepicDAO.listByRegNo(regNo);
	}

	@Override
	public Boolean removeByID(String regNo) {
		try {
			fvehiclepicDAO.removeByID(regNo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fvehiclepic fvehiclepic) {
		try {
			fvehiclepicDAO.udpate(fvehiclepic);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
