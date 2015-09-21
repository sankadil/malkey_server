package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FemailsentDAO;
import com.dspl.malkey.domain.Femailsent;

public class FemailsentSRVImpl implements FemailsentSRV {

	@Resource(name="femailsentDAO")
	FemailsentDAO femailsentDAO;

	@Override
	public List<Femailsent> List(int startIndex, int numItems) {
		return femailsentDAO.List(startIndex, numItems);
	}

	@Override
	public List<Femailsent> ListAll() {
		return femailsentDAO.ListAll();
	}

	@Override
	public int count() {
		return femailsentDAO.count();
	}

	@Override
	public Boolean create(Femailsent femailsent) {
		try {
			femailsentDAO.create(femailsent);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Femailsent findByID(String emailid) {
		return femailsentDAO.findByID(emailid);
	}

	@Override
	public Boolean removeByID(String emailid) {
		try {
			femailsentDAO.removeByID(emailid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Femailsent femailsent) {
		try {
			femailsentDAO.udpate(femailsent);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
