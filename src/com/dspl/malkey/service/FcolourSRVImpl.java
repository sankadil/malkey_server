package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FcolourDAO;
import com.dspl.malkey.domain.Fcolour;

public class FcolourSRVImpl implements FcolourSRV {

	@Resource(name="fcolourDAO")
	FcolourDAO fcolourDAO;

	@Override
	public List<Fcolour> List(int startIndex, int numItems) {
		return fcolourDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fcolour> ListAll() {
		return fcolourDAO.ListAll();
	}

	@Override
	public int count() {
		return fcolourDAO.count();
	}

	@Override
	public Boolean create(Fcolour fcolour) {
		try {
			fcolourDAO.create(fcolour);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fcolour findByID(String colourid) {
		return fcolourDAO.findByID(colourid);
	}

	@Override
	public Boolean removeByID(String colourid) {
		try {
			fcolourDAO.removeByID(colourid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fcolour fcolour) {
		try {
			fcolourDAO.udpate(fcolour);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
