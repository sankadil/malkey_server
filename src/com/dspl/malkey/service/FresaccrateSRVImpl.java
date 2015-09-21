package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FresaccrateDAO;
import com.dspl.malkey.domain.Fresaccrate;
import com.dspl.malkey.domain.FresaccratePK;

public class FresaccrateSRVImpl implements FresaccrateSRV {

	@Resource(name="fresaccrateDAO")
	FresaccrateDAO fresaccrateDAO;
	
	@Override
	public java.util.List<Fresaccrate> list(int startIndex, int numItems) {
		return fresaccrateDAO.list(startIndex, numItems);
	}

	@Override
	public java.util.List<Fresaccrate> listAll() {
		return fresaccrateDAO.listAll();
	}

	@Override
	public int count() {
		return fresaccrateDAO.count();
	}

	@Override
	public void create(Fresaccrate fresaccrate) {
		fresaccrateDAO.create(fresaccrate);
	}

	@Override
	public Fresaccrate findByID(FresaccratePK id) {
		return fresaccrateDAO.findByID(id);
	}

	@Override
	public void removeByID(FresaccratePK id) {
		fresaccrateDAO.removeByID(id);
	}

	@Override
	public void update(Fresaccrate fresaccrate) {
		fresaccrateDAO.update(fresaccrate);
	}

	@Override
	public List<Fresaccrate> listAllByResno(String resno) {
		return fresaccrateDAO.listAllByResno(resno);
	}
	

}
