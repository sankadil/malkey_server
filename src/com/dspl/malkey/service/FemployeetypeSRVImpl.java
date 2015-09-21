package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FemployeetypeDAO;
import com.dspl.malkey.domain.Femployeetype;

public class FemployeetypeSRVImpl implements FemployeetypeSRV {

	@Resource(name="femployeetypeDAO")
	FemployeetypeDAO femployeetypeDAO;

	@Override
	public List<Femployeetype> List(int startIndex, int numItems) {
		return femployeetypeDAO.List(startIndex, numItems);
	}

	@Override
	public List<Femployeetype> ListAll() {
		return femployeetypeDAO.ListAll();
	}

	@Override
	public int count() {
		return femployeetypeDAO.count();
	}

	@Override
	public Boolean create(Femployeetype femployeetype) {
		try {
			femployeetypeDAO.create(femployeetype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Femployeetype findByID(String emptype) {
		return femployeetypeDAO.findByID(emptype);
	}

	@Override
	public Boolean removeByID(String emptype) {
		try {
			femployeetypeDAO.removeByID(emptype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Femployeetype femployeetype) {
		try {
			femployeetypeDAO.udpate(femployeetype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
