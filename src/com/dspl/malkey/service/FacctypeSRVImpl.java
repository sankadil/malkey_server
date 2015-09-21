package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FacctypeDAO;
import com.dspl.malkey.domain.Facctype;

public class FacctypeSRVImpl implements FacctypeSRV {

	@Resource(name="facctypeDAO")
	FacctypeDAO facctypeDAO;

	@Override
	public List<Facctype> List(int startIndex, int numItems) {
		return facctypeDAO.List(startIndex, numItems);
	}

	@Override
	public List<Facctype> ListAll() {
		return facctypeDAO.ListAll();
	}

	@Override
	public int count() {
		return facctypeDAO.count();
	}

	@Override
	public Boolean create(Facctype facctype) {
		try {
			facctypeDAO.create(facctype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Facctype findByID(String acctypeid) {
		return facctypeDAO.findByID(acctypeid);
	}

	@Override
	public Boolean removeByID(String acctypeid) {
		try {
			facctypeDAO.removeByID(acctypeid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Facctype facctype) {
		try {
			facctypeDAO.udpate(facctype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
