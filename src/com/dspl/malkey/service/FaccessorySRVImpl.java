package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FaccessoryDAO;
import com.dspl.malkey.domain.Faccessory;
import com.dspl.malkey.domain.Faccrate;

public class FaccessorySRVImpl implements FaccessorySRV {

	@Resource(name="faccessoryDAO")
	FaccessoryDAO faccessoryDAO;

	@Override
	public List<Faccessory> List(int startIndex, int numItems) {
		return faccessoryDAO.List(startIndex, numItems);
	}

	@Override
	public List<Faccessory> ListAll() {
		return faccessoryDAO.ListAll();
	}

	@Override
	public int count() {
		return faccessoryDAO.count();
	}

//	@Override
//	public Boolean create(Faccessory faccessory) {
//		try {
//			faccessoryDAO.create(faccessory);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}

	@Override
	public Faccessory findByID(String accid) {
		return faccessoryDAO.findByID(accid);
	}

//	@Override
//	public Boolean removeByID(String accid) {
//		try {
//			faccessoryDAO.removeByID(accid);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}

//	@Override
//	public Boolean udpate(Faccessory faccessory) {
//		try {
//			faccessoryDAO.update(faccessory);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
	
	@Override
	public Boolean create(Faccessory faccessory, List<Faccrate> faccrates) {
		return faccessoryDAO.create(faccessory, faccrates);
	}

	@Override
	public List<Faccessory> listAccessories() {
		return faccessoryDAO.listAccessories();
	}

	@Override
	public Boolean removeByID(String accid) {
		return faccessoryDAO.removeByID(accid);
	}

	@Override
	public Boolean update(Faccessory faccessory, List<Faccrate> faccrates) {
		return faccessoryDAO.update(faccessory, faccrates);
	}

}
