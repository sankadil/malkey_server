package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FresothersrvrateDAO;
import com.dspl.malkey.domain.Fresothersrvrate;
import com.dspl.malkey.domain.FresothersrvratePK;

public class FresothersrvrateSRVImpl implements FresothersrvrateSRV {

	@Resource(name="fresothersrvrateDAO")
	FresothersrvrateDAO fresothersrvrateDAO;
	
	@Override
	public java.util.List<Fresothersrvrate> list(int startIndex, int numItems) {
		return fresothersrvrateDAO.list(startIndex, numItems);
	}

	@Override
	public java.util.List<Fresothersrvrate> listAll() {
		return fresothersrvrateDAO.listAll();
	}

	@Override
	public int count() {
		return fresothersrvrateDAO.count();
	}

	@Override
	public void create(Fresothersrvrate fresothersrvrate) {
		fresothersrvrateDAO.create(fresothersrvrate);
	}

	@Override
	public Fresothersrvrate findByID(FresothersrvratePK id) {
		return fresothersrvrateDAO.findByID(id);
	}

	@Override
	public void removeByID(FresothersrvratePK id) {
		fresothersrvrateDAO.removeByID(id);
	}

	@Override
	public void udpate(Fresothersrvrate fresothersrvrate) {
		fresothersrvrateDAO.update(fresothersrvrate);
	}

	@Override
	public List<Fresothersrvrate> listByResno(String resno) {
		return fresothersrvrateDAO.listByResno(resno);
	}
	

}
