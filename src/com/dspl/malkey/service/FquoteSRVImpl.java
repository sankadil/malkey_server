package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FquoteDAO;
import com.dspl.malkey.domain.Fquote;

public class FquoteSRVImpl implements FquoteSRV {

	@Resource(name="fquoteDAO")
	FquoteDAO fquoteDAO;

	@Override
	public List<Fquote> List(int startIndex, int numItems) {
		return fquoteDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fquote> ListAll() {
		return fquoteDAO.ListAll();
	}

	@Override
	public int count() {
		return fquoteDAO.count();
	}

	@Override
	public Boolean create(Fquote fquote) {
		try {
			fquoteDAO.create(fquote);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fquote findByID(String quoteno) {
		return fquoteDAO.findByID(quoteno);
	}

	@Override
	public Boolean removeByID(String quoteno) {
		try {
			fquoteDAO.removeByID(quoteno);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fquote fquote) {
		try {
			fquoteDAO.udpate(fquote);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
