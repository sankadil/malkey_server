package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FtaxDAO;
import com.dspl.malkey.domain.Ftax;

public class FtaxSRVImpl implements FtaxSRV {
	@Resource(name="ftaxDAO")
	FtaxDAO ftaxDAO;
	
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return ftaxDAO.count();
	}

	@Override
	public Boolean create(Ftax ftax) {
		// TODO Auto-generated method stub
		try {
			ftaxDAO.create(ftax);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		//return ftaxDAO.create(ftax);
	}

	@Override
	public Ftax findById(String taxcode) {
		// TODO Auto-generated method stub
		return ftaxDAO.findById(taxcode);
	}

	@Override
	public List<Ftax> list(int startIndex, int numItems) {
		// TODO Auto-generated method stub
		return ftaxDAO.list(startIndex, numItems);
	}

	@Override
	public List<Ftax> listAll() {
		// TODO Auto-generated method stub
		return ftaxDAO.listAll();
	}

	@Override
	public Boolean removeById(String taxcode) {
		// TODO Auto-generated method stub
		try {
			ftaxDAO.removeById(taxcode);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
//		return ftaxDAO.removeById(taxcode);
	}

	@Override
	public Boolean update(Ftax ftax) {
		try {
			ftaxDAO.update(ftax);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
