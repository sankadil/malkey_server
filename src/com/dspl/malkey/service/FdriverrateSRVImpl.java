package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FdriverrateDAO;
import com.dspl.malkey.domain.Fdriverrate;
import com.dspl.malkey.domain.FdriverratePK;

public class FdriverrateSRVImpl implements FdriverrateSRV {

	@Resource(name="fdriverrateDAO")
	FdriverrateDAO fdriverrateDAO;

	@Override
	public List<Fdriverrate> List(int startIndex, int numItems) {
		return fdriverrateDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fdriverrate> ListAll() {
		return fdriverrateDAO.ListAll();
	}

	@Override
	public int count() {
		return fdriverrateDAO.count();
	}

	@Override
	public Boolean create(Fdriverrate fdriverrate) {
		try {
			fdriverrateDAO.create(fdriverrate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fdriverrate findByID(FdriverratePK fdriverratePK) {
		return fdriverrateDAO.findByID(fdriverratePK);
	}

	@Override
	public Boolean removeByID(FdriverratePK fdriverratePK) {
		try {
			fdriverrateDAO.removeByID(fdriverratePK);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fdriverrate fdriverrate) {
		try {
			fdriverrateDAO.udpate(fdriverrate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean update(List<Fdriverrate> fdriverrates) {
		return fdriverrateDAO.update(fdriverrates);
	}

}
