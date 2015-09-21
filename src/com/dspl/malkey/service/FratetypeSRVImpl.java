package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FratetypeDAO;
import com.dspl.malkey.domain.Fratetype;
import com.dspl.malkey.domain.Fratetype;

public class FratetypeSRVImpl implements FratetypeSRV {

	@Resource(name="fratetypeDAO")
	FratetypeDAO fratetypeDAO;

	@Override
	public List<Fratetype> list(int startIndex, int numItems) {
		return fratetypeDAO.list(startIndex, numItems);
	}

	@Override
	public List<Fratetype> listAll() {
		return fratetypeDAO.listAll();
	}

	@Override
	public int count() {
		return fratetypeDAO.count();
	}

	@Override
	public Boolean create(Fratetype fratetype) {
		return fratetypeDAO.create(fratetype);
	}

	@Override
	public Fratetype findByID(String ratetype) {
		return fratetypeDAO.findByID(ratetype);
	}

	@Override
	public Boolean removeByID(String ratetype) {
		try{
		return fratetypeDAO.removeByID(ratetype);
		}catch(Exception ex){
			System.out.println("FreatetypeSrv removeById: " + ex.getMessage());
		}
		return null;
	}

	@Override
	public Boolean update(Fratetype fratetype) {
		return fratetypeDAO.update(fratetype);
	}

}
