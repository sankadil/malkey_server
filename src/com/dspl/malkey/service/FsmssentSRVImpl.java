package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FsmssentDAO;
import com.dspl.malkey.domain.Fsmssent;

public class FsmssentSRVImpl implements FsmssentSRV {

	@Resource(name="fsmssentDAO")
	FsmssentDAO fsmssentDAO;

	@Override
	public List<Fsmssent> List(int startIndex, int numItems) {
		return fsmssentDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fsmssent> ListAll() {
		return fsmssentDAO.ListAll();
	}

	@Override
	public int count() {
		return fsmssentDAO.count();
	}

	@Override
	public Boolean create(Fsmssent fsmssent) {
		try {
			fsmssentDAO.create(fsmssent);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fsmssent findByID(int recordid) {
		return fsmssentDAO.findByID(recordid);
	}

	@Override
	public Boolean removeByID(int recordid) {
		try {
			fsmssentDAO.removeByID(recordid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fsmssent fsmssent) {
		try {
			fsmssentDAO.udpate(fsmssent);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
