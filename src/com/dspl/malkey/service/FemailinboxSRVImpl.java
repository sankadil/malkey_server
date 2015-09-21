package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FemailinboxDAO;
import com.dspl.malkey.domain.Femailinbox;

public class FemailinboxSRVImpl implements FemailinboxSRV {

	@Resource(name="femailinboxDAO")
	FemailinboxDAO femailinboxDAO;

	@Override
	public List<Femailinbox> List(int startIndex, int numItems) {
		return femailinboxDAO.List(startIndex, numItems);
	}

	@Override
	public List<Femailinbox> ListAll() {
		return femailinboxDAO.ListAll();
	}

	@Override
	public int count() {
		return femailinboxDAO.count();
	}

	@Override
	public Boolean create(Femailinbox femailinbox) {
		try {
			femailinboxDAO.create(femailinbox);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Femailinbox findByID(String emailid) {
		return femailinboxDAO.findByID(emailid);
	}

	@Override
	public Boolean removeByID(String emailid) {
		try {
			femailinboxDAO.removeByID(emailid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Femailinbox femailinbox) {
		try {
			femailinboxDAO.udpate(femailinbox);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
