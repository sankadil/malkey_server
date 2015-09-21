package com.dspl.malkey.service;

import javax.annotation.Resource;
import com.dspl.malkey.dao.FcompanyDAO;
import com.dspl.malkey.domain.Fcompany;
import com.dspl.malkey.domain.Fcompanytax;

public class FcompanySRVImpl implements FcompanySRV {

	@Resource(name="fcompanyDAO")
	FcompanyDAO fcompanyDAO;
	
	@Override
	public java.util.List<Fcompany> List(int startIndex, int numItems) {
		return fcompanyDAO.List(startIndex, numItems);
	}

	@Override
	public java.util.List<Fcompany> ListAll() {
		return fcompanyDAO.ListAll();
	}

	@Override
	public int count() {
		return fcompanyDAO.count();
	}

	@Override
	public Boolean create(Fcompany fcompany, java.util.List<Fcompanytax> fcompanytaxes) {
		return fcompanyDAO.create(fcompany, fcompanytaxes);
	}

	@Override
	public Fcompany findByID(String companyid) {
		return fcompanyDAO.findByID(companyid);
	}

	@Override
	public java.util.List<Fcompany> listCompanies() {
		return fcompanyDAO.listCompanies();
	}

	@Override
	public Boolean removeByID(String companyid) {
		return fcompanyDAO.removeByID(companyid);
	}

	@Override
	public Boolean update(Fcompany fcompany, java.util.List<Fcompanytax> fcompanytaxes) {
		return fcompanyDAO.update(fcompany, fcompanytaxes);
	}

}
