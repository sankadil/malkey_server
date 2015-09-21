package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FresothsrvDAO;
import com.dspl.malkey.domain.Fresothsrv;
import com.dspl.malkey.domain.FresothsrvPK;

public class FresothsrvSRVImpl implements FresothsrvSRV {

	@Resource(name="fresothsrvDAO")
	FresothsrvDAO fresothsrvDAO;

	@Override
	public List<Fresothsrv> List(int startIndex, int numItems) {
		return fresothsrvDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fresothsrv> ListAll() {
		return fresothsrvDAO.ListAll();
	}

	@Override
	public int count() {
		return fresothsrvDAO.count();
	}

	@Override
	public Boolean create(Fresothsrv fresothsrv) {
		try {
			fresothsrvDAO.create(fresothsrv);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fresothsrv findByID(FresothsrvPK fresothsrvPK) {
		return fresothsrvDAO.findByID(fresothsrvPK);
	}

	@Override
	public Boolean removeByID(FresothsrvPK fresothsrvPK) {
		try {
			fresothsrvDAO.removeByID(fresothsrvPK);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fresothsrv fresothsrv) {
		try {
			fresothsrvDAO.udpate(fresothsrv);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Fresothsrv> listByResNo(String resno) {
		return fresothsrvDAO.listByResNo(resno);
	}

}
