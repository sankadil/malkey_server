package com.dspl.malkey.service;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FcontrolDAO;
import com.dspl.malkey.domain.Fcontrol;

public class FcontrolSRVImpl implements FcontrolSRV {
	@Resource(name="fcontrolDAO")
	FcontrolDAO fcontrolDAO;
	
	@Override
	public Fcontrol ListAll() {
		return fcontrolDAO.ListAll();
	}

	@Override
	public Boolean update(Fcontrol fcontrol) {
		try {
			fcontrolDAO.update(fcontrol);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
