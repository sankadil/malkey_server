package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FvehicletransDAO;
import com.dspl.malkey.domain.Fvehicletrans;

public class FvehicletransSRVImpl implements FvehicletransSRV {

	@Resource(name="fvehicletransDAO")
	FvehicletransDAO fvehicletransDAO;

	@Override
	public List<Fvehicletrans> List(int startIndex, int numItems) {
		return fvehicletransDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fvehicletrans> ListAll() {
		return fvehicletransDAO.ListAll();
	}

	@Override
	public int count() {
		return fvehicletransDAO.count();
	}

	@Override
	public Boolean create(Fvehicletrans fvehicletrans) {
		try {
			fvehicletransDAO.create(fvehicletrans);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fvehicletrans findByID(String vehicletrans) {
		return fvehicletransDAO.findByID(vehicletrans);
	}

	@Override
	public Boolean removeByID(String vehicletrans) {
		try {
			fvehicletransDAO.removeByID(vehicletrans);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fvehicletrans fvehicletrans) {
		try {
			fvehicletransDAO.udpate(fvehicletrans);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
