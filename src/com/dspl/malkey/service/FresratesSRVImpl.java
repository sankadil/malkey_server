package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FresratesDAO;
import com.dspl.malkey.domain.Fresrates;
import com.dspl.malkey.domain.FresratesPK;

public class FresratesSRVImpl implements FresratesSRV {

	@Resource(name="fresratesDAO")
	FresratesDAO fresratesDAO;

	@Override
	public List<Fresrates> List(int startIndex, int numItems) {
		return fresratesDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fresrates> ListAll() {
		return fresratesDAO.ListAll();
	}

	@Override
	public int count() {
		return fresratesDAO.count();
	}

	@Override
	public Boolean create(Fresrates fresrates) {
		try {
			fresratesDAO.create(fresrates);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fresrates findByID(FresratesPK fresratesPK) {
		return fresratesDAO.findByID(fresratesPK);
	}

	@Override
	public Boolean removeByID(FresratesPK fresratesPK) {
		try {
			fresratesDAO.removeByID(fresratesPK);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fresrates fresrates) {
		try {
			fresratesDAO.udpate(fresrates);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
