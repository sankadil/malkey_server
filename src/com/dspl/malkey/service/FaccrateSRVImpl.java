package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FaccrateDAO;
import com.dspl.malkey.domain.Faccrate;
import com.dspl.malkey.domain.FaccratePK;

public class FaccrateSRVImpl implements FaccrateSRV {

	@Resource(name="faccrateDAO")
	FaccrateDAO faccrateDAO;

	@Override
	public List<Faccrate> List(int startIndex, int numItems) {
		return faccrateDAO.List(startIndex, numItems);
	}

	@Override
	public List<Faccrate> ListAll() {
		return faccrateDAO.ListAll();
	}

	@Override
	public int count() {
		return faccrateDAO.count();
	}

	@Override
	public Boolean create(Faccrate faccrate) {
		try {
			faccrateDAO.create(faccrate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Faccrate findByID(FaccratePK faccratePK) {
		return faccrateDAO.findByID(faccratePK);
	}

	@Override
	public Boolean removeByID(FaccratePK faccratePK) {
		try {
			faccrateDAO.removeByID(faccratePK);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Faccrate faccrate) {
		try {
			faccrateDAO.udpate(faccrate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Faccrate> ListByClienttype(String clienttype) {
		return faccrateDAO.ListByClienttype(clienttype);
	}

	@Override
	public List<Faccrate> findByAccId(String accid) {
		return faccrateDAO.findByAccId(accid);
	}

}
