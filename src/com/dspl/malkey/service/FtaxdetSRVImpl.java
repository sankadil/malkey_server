package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FtaxdetDAO;
import com.dspl.malkey.domain.Ftaxdet;
import com.dspl.malkey.domain.FtaxdetPK;

public class FtaxdetSRVImpl implements FtaxdetSRV {

	@Resource(name="ftaxdetDAO")
	FtaxdetDAO ftaxdetDAO;
	
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return ftaxdetDAO.count();
	}

	@Override
	public Boolean create(Ftaxdet ftaxdet) {
		// TODO Auto-generated method stub
		try {
			ftaxdetDAO.create(ftaxdet);
			System.out.println("insert record");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("insert rec faild");
			return false;
		}
//		return ftaxdetDAO.create(ftaxdet);
	}

	@Override
	public Ftaxdet findById(FtaxdetPK ftaxdetpk) {
		// TODO Auto-generated method stub
		return ftaxdetDAO.findById(ftaxdetpk);
	}

	@Override
	public List<Ftaxdet> list(int startIndex, int numItems) {
		// TODO Auto-generated method stub
		return ftaxdetDAO.list(startIndex, numItems);
	}

	@Override
	public List<Ftaxdet> listAll() {
		// TODO Auto-generated method stub
		return ftaxdetDAO.listAll();
	}

	@Override
	public Boolean removeById(FtaxdetPK ftaxdetpk) {
		// TODO Auto-generated method stub
		try {
			ftaxdetDAO.removeById(ftaxdetpk);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
//		return ftaxdetDAO.removeById(ftaxdetpk);
	}

	@Override
	public Boolean update(Ftaxdet ftaxdet) {
		try {
			ftaxdetDAO.update(ftaxdet);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
