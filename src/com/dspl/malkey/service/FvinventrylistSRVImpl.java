package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FvinventrylistDAO;
import com.dspl.malkey.domain.Fvinventrylist;

public class FvinventrylistSRVImpl implements FvinventrylistSRV {

	@Resource(name="fvinventrylistDAO")
	FvinventrylistDAO fvinventrylistDAO;

	@Override
	public List<Fvinventrylist> List(int startIndex, int numItems) {
		return fvinventrylistDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fvinventrylist> ListAll() {
		return fvinventrylistDAO.ListAll();
	}

	@Override
	public int count() {
		return fvinventrylistDAO.count();
	}

	@Override
	public Boolean create(Fvinventrylist fvinventrylist) {
		try {
			fvinventrylistDAO.create(fvinventrylist);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fvinventrylist findByID(String invid) {
		return fvinventrylistDAO.findByID(invid);
	}

	@Override
	public Boolean removeByID(String invid) {
		try {
			fvinventrylistDAO.removeByID(invid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fvinventrylist fvinventrylist) {
		try {
			fvinventrylistDAO.udpate(fvinventrylist);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
