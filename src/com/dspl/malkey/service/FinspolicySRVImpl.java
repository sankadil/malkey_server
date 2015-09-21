package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FinspolicyDAO;
import com.dspl.malkey.domain.Finspolicy;

public class FinspolicySRVImpl implements FinspolicySRV {

	@Resource(name="finspolicyDAO")
	FinspolicyDAO finspolicyDAO;

	@Override
	public List<Finspolicy> List(int startIndex, int numItems) {
		return finspolicyDAO.List(startIndex, numItems);
	}

	@Override
	public List<Finspolicy> ListAll() {
		return finspolicyDAO.ListAll();
	}

	@Override
	public int count() {
		return finspolicyDAO.count();
	}

	@Override
	public Boolean create(Finspolicy finspolicy) {
		try {
			finspolicyDAO.create(finspolicy);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Finspolicy findByID(String policyid) {
		return finspolicyDAO.findByID(policyid);
	}

	@Override
	public Boolean removeByID(String policyid) {
		try {
			finspolicyDAO.removeByID(policyid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Finspolicy finspolicy) {
		try {
			finspolicyDAO.udpate(finspolicy);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
