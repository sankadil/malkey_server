package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FothersrvrateDAO;
import com.dspl.malkey.domain.Fothersrvrate;
import com.dspl.malkey.domain.FothersrvratePK;

public class FothersrvrateSRVImpl implements FothersrvrateSRV {

	@Resource(name="fothersrvrateDAO")
	FothersrvrateDAO fothersrvrateDAO;

	@Override
	public List<Fothersrvrate> List(int startIndex, int numItems) {
		return fothersrvrateDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fothersrvrate> ListAll() {
		return fothersrvrateDAO.ListAll();
	}

	@Override
	public int count() {
		return fothersrvrateDAO.count();
	}

	@Override
	public Boolean create(Fothersrvrate fothersrvrate) {
		try {
			fothersrvrateDAO.create(fothersrvrate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fothersrvrate findByID(FothersrvratePK fothersrvratePK) {
		return fothersrvrateDAO.findByID(fothersrvratePK);
	}

	@Override
	public Boolean removeByID(FothersrvratePK fothersrvratePK) {
		try {
			fothersrvrateDAO.removeByID(fothersrvratePK);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fothersrvrate fothersrvrate) {
		try {
			fothersrvrateDAO.udpate(fothersrvrate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Fothersrvrate> findBySrvId(String srvid) {
		return fothersrvrateDAO.findBySrvId(srvid);
	}

}
