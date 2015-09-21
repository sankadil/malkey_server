package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.dao.FtaxhedDAO;
import com.dspl.malkey.domain.Ftaxdet;
import com.dspl.malkey.domain.Ftaxhed;

import flex.messaging.io.ArrayCollection;

public class FtaxhedSRVImpl implements FtaxhedSRV {

	@Resource(name="ftaxhedDAO")
	FtaxhedDAO ftaxhedDAO;
	
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return ftaxhedDAO.count();
	}

	@Override
	public Boolean create(Ftaxhed ftaxhed) {
		// TODO Auto-generated method stub
		try {
			ftaxhedDAO.create(ftaxhed);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		//return ftaxhedDAO.create(ftaxhed);
	}

	@Override
	public Ftaxhed findById(String taxcomcode) {
		// TODO Auto-generated method stub
		return ftaxhedDAO.findById(taxcomcode);
	}

	@Override
	public List<Ftaxhed> list(int startIndex, int numItems) {
		// TODO Auto-generated method stub
		return ftaxhedDAO.list(startIndex, numItems);
	}

	@Override
	public List<Ftaxhed> listAll() {
		// TODO Auto-generated method stub
		return ftaxhedDAO.listAll();
	}

	@Override
	public Boolean removeById(String taxcomcode) {
		// TODO Auto-generated method stub
		try {
			ftaxhedDAO.removeById(taxcomcode);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		//return ftaxhedDAO.removeById(taxcomcode);
	}

	@Override
	public Boolean update(Ftaxhed ftaxhed) {
		try {
			ftaxhedDAO.update(ftaxhed);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean deleterec(String taxcomcode) {
		
		try {
			ftaxhedDAO.deleterec(taxcomcode);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public Boolean edit(Ftaxhed ftaxhed, List<Ftaxdet> ftaxdet) {
		try {
			ftaxhedDAO.edit(ftaxhed, ftaxdet);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	@Override
	public Boolean insert(Ftaxhed ftaxhed, ArrayCollection ftaxdet) {
		try {
			ftaxhedDAO.insert(ftaxhed, ftaxdet);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
