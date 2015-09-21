package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FmaintstatusDAO;
import com.dspl.malkey.domain.Fmaintstatus;

public class FmaintstatusSRVImpl implements FmaintstatusSRV {

	@Resource(name="fmaintstatusDAO")
	FmaintstatusDAO fmaintstatusDAO;

	@Override
	public List<Fmaintstatus> List(int startIndex, int numItems) {
		return fmaintstatusDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fmaintstatus> ListAll() {
		return fmaintstatusDAO.ListAll();
	}

	@Override
	public int count() {
		return fmaintstatusDAO.count();
	}

	@Override
	public Boolean create(Fmaintstatus fmaintstatus) {
		try {
			fmaintstatusDAO.create(fmaintstatus);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fmaintstatus findByID(String statusid) {
		return fmaintstatusDAO.findByID(statusid);
	}

	@Override
	public Boolean removeByID(String statusid) {
		try {
			fmaintstatusDAO.removeByID(statusid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fmaintstatus fmaintstatus) {
		try {
			fmaintstatusDAO.udpate(fmaintstatus);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
