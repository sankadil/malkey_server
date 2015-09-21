package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FpaytypeDAO;
import com.dspl.malkey.domain.Fpaytype;

public class FpaytypeSRVImpl implements FpaytypeSRV {

	@Resource(name="fpaytypeDAO")
	FpaytypeDAO fpaytypeDAO;

	@Override
	public List<Fpaytype> List(int startIndex, int numItems) {
		return fpaytypeDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fpaytype> ListAll() {
		return fpaytypeDAO.ListAll();
	}

	@Override
	public int count() {
		return fpaytypeDAO.count();
	}

	@Override
	public Boolean create(Fpaytype fpaytype) {
		try {
			fpaytypeDAO.create(fpaytype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fpaytype findByID(String paytypeid) {
		return fpaytypeDAO.findByID(paytypeid);
	}

	@Override
	public Boolean removeByID(String paytypeid) {
		try {
			fpaytypeDAO.removeByID(paytypeid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fpaytype fpaytype) {
		try {
			fpaytypeDAO.udpate(fpaytype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
