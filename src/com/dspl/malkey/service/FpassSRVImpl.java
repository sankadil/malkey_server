package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FpassDAO;
import com.dspl.malkey.domain.Fpass;

public class FpassSRVImpl implements FpassSRV {

	@Resource(name="fpassDAO")
	FpassDAO fpassDAO;
	
	@Override
	public int count() {
		return fpassDAO.count();
	}

	@Override
	public Boolean create(Fpass fpass) {
		try {
			fpassDAO.create(fpass);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fpass findById(String userid) {
		return fpassDAO.findById(userid);
	}

	@Override
	public List<Fpass> list(int startIndex, int numItems) {
		return fpassDAO.list(startIndex, numItems);
	}

	@Override
	public List<Fpass> listAll() {
		return fpassDAO.listAll();
	}

	@Override
	public Boolean removeById(String userid) {
		try {
			fpassDAO.removeById(userid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean update(Fpass fpass) {			
		try {
			fpassDAO.update(fpass);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Fpass> listUsers() {
		return fpassDAO.listUsers();
	}

}
