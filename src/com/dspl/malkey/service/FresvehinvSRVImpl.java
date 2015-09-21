package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FresvehinvDAO;
import com.dspl.malkey.domain.Fresvehinv;
import com.dspl.malkey.domain.FresvehinvPK;

public class FresvehinvSRVImpl implements FresvehinvSRV {

	@Resource(name="fresvehinvDAO")
	FresvehinvDAO fresvehinvDAO;

	@Override
	public List<Fresvehinv> List(int startIndex, int numItems) {
		return fresvehinvDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fresvehinv> ListAll() {
		return fresvehinvDAO.ListAll();
	}

	@Override
	public int count() {
		return fresvehinvDAO.count();
	}

	@Override
	public Boolean create(Fresvehinv fresvehinv) {
		try {
			fresvehinvDAO.create(fresvehinv);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fresvehinv findByID(FresvehinvPK fresvehinvPK) {
		return fresvehinvDAO.findByID(fresvehinvPK);
	}

	@Override
	public Boolean removeByID(FresvehinvPK fresvehinvPK) {
		try {
			fresvehinvDAO.removeByID(fresvehinvPK);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fresvehinv fresvehinv) {
		try {
			fresvehinvDAO.udpate(fresvehinv);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Fresvehinv> listByResno(String resno) {
		// TODO Auto-generated method stub
		return fresvehinvDAO.listByResno(resno);
	}

}
