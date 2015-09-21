package com.dspl.malkey.service;

import javax.annotation.Resource;
import com.dspl.malkey.dao.FdebtorstatusDAO;
import com.dspl.malkey.domain.Fdebtorstatus;

public class FdebtorstatusSRVImpl implements FdebtorstatusSRV {

	@Resource(name="fdebtorstatusDAO")
	FdebtorstatusDAO fdebtorstatusDAO;
	
	@Override
	public java.util.List<Fdebtorstatus> List(int startIndex, int numItems) {
		return fdebtorstatusDAO.List(startIndex, numItems);
	}

	@Override
	public java.util.List<Fdebtorstatus> ListAll() {
		return fdebtorstatusDAO.ListAll();
	}

	@Override
	public int count() {
		return fdebtorstatusDAO.count();
	}

	@Override
	public Boolean create(Fdebtorstatus fdebtorstatus) {
		try {
			fdebtorstatusDAO.create(fdebtorstatus);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fdebtorstatus findByID(String id) {
		return fdebtorstatusDAO.findByID(id);
	}

	@Override
	public Boolean removeByID(String id) {
		try {
			fdebtorstatusDAO.removeByID(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean update(Fdebtorstatus fdebtorstatus) {
		try {
			fdebtorstatusDAO.update(fdebtorstatus);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
