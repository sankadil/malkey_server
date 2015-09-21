package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FresaccsDAO;
import com.dspl.malkey.domain.Fresaccs;
import com.dspl.malkey.domain.FresaccsPK;

public class FresaccsSRVImpl implements FresaccsSRV {

	@Resource(name="fresaccsDAO")
	FresaccsDAO fresaccsDAO;

	@Override
	public List<Fresaccs> List(int startIndex, int numItems) {
		return fresaccsDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fresaccs> ListAll() {
		return fresaccsDAO.ListAll();
	}

	@Override
	public int count() {
		return fresaccsDAO.count();
	}

	@Override
	public Boolean create(Fresaccs fresaccs) {
		try {
			fresaccsDAO.create(fresaccs);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fresaccs findByID(FresaccsPK fresaccsPK) {
		return fresaccsDAO.findByID(fresaccsPK);
	}

	@Override
	public Boolean removeByID(FresaccsPK fresaccsPK) {
		try {
			fresaccsDAO.removeByID(fresaccsPK);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fresaccs fresaccs) {
		try {
			fresaccsDAO.udpate(fresaccs);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Fresaccs> listAllByresNo(String resno) {
		return fresaccsDAO.listAllByresNo(resno);
	}

}
