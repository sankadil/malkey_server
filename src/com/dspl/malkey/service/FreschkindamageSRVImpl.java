package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FreschkindamageDAO;
import com.dspl.malkey.domain.Freschkindamage;
import com.dspl.malkey.domain.FreschkindamagePK;

public class FreschkindamageSRVImpl implements FreschkindamageSRV {

	@Resource(name="freschkindamageDAO")
	FreschkindamageDAO freschkindamageDAO;

	@Override
	public List<Freschkindamage> List(int startIndex, int numItems) {
		return freschkindamageDAO.List(startIndex, numItems);
	}

	@Override
	public List<Freschkindamage> ListAll() {
		return freschkindamageDAO.ListAll();
	}

	@Override
	public int count() {
		return freschkindamageDAO.count();
	}

	@Override
	public Boolean create(Freschkindamage freschkindamage) {
		try {
			freschkindamageDAO.create(freschkindamage);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Freschkindamage findByID(FreschkindamagePK freschkindamagePK) {
		return freschkindamageDAO.findByID(freschkindamagePK);
	}

	@Override
	public Boolean removeByID(FreschkindamagePK freschkindamagePK) {
		try {
			freschkindamageDAO.removeByID(freschkindamagePK);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Freschkindamage freschkindamage) {
		try {
			freschkindamageDAO.udpate(freschkindamage);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
