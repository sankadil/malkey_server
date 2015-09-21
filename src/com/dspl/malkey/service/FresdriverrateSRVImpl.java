package com.dspl.malkey.service;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FresdriverrateDAO;
import com.dspl.malkey.domain.Fresdriverrate;
import com.dspl.malkey.domain.FresdriverratePK;

public class FresdriverrateSRVImpl implements FresdriverrateSRV {

	@Resource(name="fresdriverrateDAO")
	FresdriverrateDAO fresdriverrateDAO;
	
	@Override
	public java.util.List<Fresdriverrate> list(int startIndex, int numItems) {
		return fresdriverrateDAO.list(startIndex, numItems);
	}

	@Override
	public java.util.List<Fresdriverrate> listAll() {
		return fresdriverrateDAO.listAll();
	}

	@Override
	public int count() {
		return fresdriverrateDAO.count();
	}

	@Override
	public void create(Fresdriverrate fresdriverrate) {
		fresdriverrateDAO.create(fresdriverrate);
	}

	@Override
	public Fresdriverrate findByID(FresdriverratePK id) {
		return fresdriverrateDAO.findByID(id);
	}

	@Override
	public void removeByID(FresdriverratePK id) {
		fresdriverrateDAO.removeByID(id);
	}

	@Override
	public void udpate(Fresdriverrate fresdriverrate) {
		fresdriverrateDAO.update(fresdriverrate);
	}

	@Override
	public Fresdriverrate findByResno(String resno) {
		return fresdriverrateDAO.findByResno(resno);
	}

}
