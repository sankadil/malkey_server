package com.dspl.malkey.service;

import java.util.List;
import javax.annotation.Resource;
import com.dspl.malkey.dao.FpassDAO;
import com.dspl.malkey.dao.FusertypeDAO;
import com.dspl.malkey.domain.Fusertype;

public class FusertypeSRVImpl implements FusertypeSRV {

	@Resource(name="fusertypeDAO")
	FusertypeDAO fusertypeDAO;
	
	@Override
	public int count() {
		return fusertypeDAO.count();
	}

	@Override
	public Boolean create(Fusertype fusertype) {
		try {
			fusertypeDAO.create(fusertype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fusertype findById(String usertypeid) {
		return fusertypeDAO.findById(usertypeid);
	}

	@Override
	public List<Fusertype> list(int startIndex, int numItems) {
		return fusertypeDAO.list(startIndex, numItems);
	}

	@Override
	public List<Fusertype> listAll() {
		return fusertypeDAO.listAll();
	}

	@Override
	public Boolean removeById(String usertypeid) {
		try {
			fusertypeDAO.removeById(usertypeid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean update(Fusertype fusertype) {
		try {
			fusertypeDAO.update(fusertype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int countInAR(String UserTypeId) 
	{
		return fusertypeDAO.countInAR(UserTypeId);
	}

}
