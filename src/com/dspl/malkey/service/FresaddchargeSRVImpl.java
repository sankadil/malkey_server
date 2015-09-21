package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FresaddchargeDAO;
import com.dspl.malkey.domain.Fresaddcharge;
import com.dspl.malkey.domain.FresaddchargePK;

public class FresaddchargeSRVImpl implements FresaddchargeSRV {

	@Resource(name="fresaddchargeDAO")
	FresaddchargeDAO fresaddchargeDAO;

	@Override
	public List<Fresaddcharge> List(int startIndex, int numItems) {
		return fresaddchargeDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fresaddcharge> ListAll() {
		return fresaddchargeDAO.ListAll();
	}

	@Override
	public int count() {
		return fresaddchargeDAO.count();
	}

	@Override
	public Boolean create(Fresaddcharge fresaddcharge) {
		try {
			fresaddchargeDAO.create(fresaddcharge);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fresaddcharge findByID(FresaddchargePK fresaddchargePK) {
		return fresaddchargeDAO.findByID(fresaddchargePK);
	}

	@Override
	public Boolean removeByID(FresaddchargePK fresaddchargePK) {
		try {
			fresaddchargeDAO.removeByID(fresaddchargePK);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fresaddcharge fresaddcharge) {
		try {
			fresaddchargeDAO.udpate(fresaddcharge);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Fresaddcharge> listByresno(String resno) {
		return fresaddchargeDAO.listByresno(resno);
	}

}
