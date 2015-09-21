package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FresdriverDAO;
import com.dspl.malkey.domain.Fresdriver;
import com.dspl.malkey.domain.FresdriverPK;

public class FresdriverSRVImpl implements FresdriverSRV {

	@Resource(name="fresdriverDAO")
	FresdriverDAO fresdriverDAO;

	@Override
	public List<Fresdriver> List(int startIndex, int numItems) {
		return fresdriverDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fresdriver> ListAll() {
		return fresdriverDAO.ListAll();
	}

	@Override
	public int count() {
		return fresdriverDAO.count();
	}

	@Override
	public Boolean create(Fresdriver fresdriver) {
		try {
			fresdriverDAO.create(fresdriver);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fresdriver findByID(FresdriverPK fresdriverPK) {
		return fresdriverDAO.findByID(fresdriverPK);
	}

	@Override
	public Boolean removeByID(FresdriverPK fresdriverPK) {
		try {
			fresdriverDAO.removeByID(fresdriverPK);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fresdriver fresdriver) {
		try {
			fresdriverDAO.udpate(fresdriver);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Fresdriver> listByResno(String resno) {
		return fresdriverDAO.listByResno(resno);
	}

}
