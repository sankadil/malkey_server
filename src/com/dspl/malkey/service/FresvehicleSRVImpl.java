package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FresvehicleDAO;
import com.dspl.malkey.domain.Fresvehicle;
import com.dspl.malkey.domain.FresvehiclePK;

public class FresvehicleSRVImpl implements FresvehicleSRV {

	@Resource(name="fresvehicleDAO")
	FresvehicleDAO fresvehicleDAO;

	@Override
	public List<Fresvehicle> List(int startIndex, int numItems) {
		return fresvehicleDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fresvehicle> ListAll() {
		return fresvehicleDAO.ListAll();
	}

	@Override
	public int count() {
		return fresvehicleDAO.count();
	}

	@Override
	public Boolean create(Fresvehicle fresvehicle) {
		try {
			fresvehicleDAO.create(fresvehicle);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fresvehicle findByID(FresvehiclePK fresvehiclePK) {
		return fresvehicleDAO.findByID(fresvehiclePK);
	}

	@Override
	public Boolean removeByID(FresvehiclePK fresvehiclePK) {
		try {
			fresvehicleDAO.removeByID(fresvehiclePK);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fresvehicle fresvehicle) {
		try {
			fresvehicleDAO.udpate(fresvehicle);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Fresvehicle> listByResNo(String resno) {
		return fresvehicleDAO.listByResNo(resno);
	}

}
