package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FhirestatusDAO;
import com.dspl.malkey.domain.Fhirestatus;

public class FhirestatusSRVImpl implements FhirestatusSRV {

	@Resource(name="fhirestatusDAO")
	FhirestatusDAO fhirestatusDAO;

	@Override
	public List<Fhirestatus> List(int startIndex, int numItems) {
		return fhirestatusDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fhirestatus> ListAll() {
		return fhirestatusDAO.ListAll();
	}

	@Override
	public int count() {
		return fhirestatusDAO.count();
	}

	@Override
	public Boolean create(Fhirestatus fhirestatus) {
		try {
			fhirestatusDAO.create(fhirestatus);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fhirestatus findByID(String hirestsid) {
		return fhirestatusDAO.findByID(hirestsid);
	}

	@Override
	public Boolean removeByID(String hirestsid) {
		try {
			fhirestatusDAO.removeByID(hirestsid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fhirestatus fhirestatus) {
		try {
			fhirestatusDAO.udpate(fhirestatus);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
