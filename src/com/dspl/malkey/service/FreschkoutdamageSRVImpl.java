package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FreschkoutdamageDAO;
import com.dspl.malkey.domain.Freschkoutdamage;
import com.dspl.malkey.domain.FreschkoutdamagePK;

public class FreschkoutdamageSRVImpl implements FreschkoutdamageSRV {

	@Resource(name="freschkoutdamageDAO")
	FreschkoutdamageDAO freschkoutdamageDAO;

	@Override
	public List<Freschkoutdamage> List(int startIndex, int numItems) {
		return freschkoutdamageDAO.List(startIndex, numItems);
	}

	@Override
	public List<Freschkoutdamage> ListAll() {
		return freschkoutdamageDAO.ListAll();
	}

	@Override
	public int count() {
		return freschkoutdamageDAO.count();
	}

	@Override
	public Boolean create(Freschkoutdamage freschkoutdamage) {
		try {
			freschkoutdamageDAO.create(freschkoutdamage);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Freschkoutdamage findByID(FreschkoutdamagePK freschkoutdamagePK) {
		return freschkoutdamageDAO.findByID(freschkoutdamagePK);
	}

	@Override
	public Boolean removeByID(FreschkoutdamagePK freschkoutdamagePK) {
		try {
			freschkoutdamageDAO.removeByID(freschkoutdamagePK);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Freschkoutdamage freschkoutdamage) {
		try {
			freschkoutdamageDAO.udpate(freschkoutdamage);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
