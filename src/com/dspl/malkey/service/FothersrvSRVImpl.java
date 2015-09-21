package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FothersrvDAO;
import com.dspl.malkey.domain.Fothersrv;
import com.dspl.malkey.domain.Fothersrvrate;

public class FothersrvSRVImpl implements FothersrvSRV {

	@Resource(name="fothersrvDAO")
	FothersrvDAO fothersrvDAO;

	@Override
	public List<Fothersrv> List(int startIndex, int numItems) {
		return fothersrvDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fothersrv> ListAll() {
		return fothersrvDAO.ListAll();
	}

	@Override
	public int count() {
		return fothersrvDAO.count();
	}

//	@Override
//	public Boolean create(Fothersrv fothersrv) {
//		try {
//			fothersrvDAO.create(fothersrv);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}

//	@Override
//	public Fothersrv findByID(String srvid) {
//		return fothersrvDAO.findByID(srvid);
//	}

	@Override
	public Boolean removeByID(String srvid) {
		try {
			fothersrvDAO.removeByID(srvid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

//	@Override
//	public Boolean udpate(Fothersrv fothersrv) {
//		try {
//			fothersrvDAO.udpate(fothersrv);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
	
	@Override
	public Boolean create(Fothersrv fothersrv, List<Fothersrvrate> fothersrvrates) {
		return fothersrvDAO.create(fothersrv, fothersrvrates);
	}

	@Override
	public Fothersrv findByID(String srvid) {
		return fothersrvDAO.findByID(srvid);
	}

	@Override
	public List<Fothersrv> listServices() {
		return fothersrvDAO.listServices();
	}

	@Override
	public Boolean update(Fothersrv fothersrv, List<Fothersrvrate> fothersrvrates) {
		return fothersrvDAO.update(fothersrv, fothersrvrates);
	}
}
