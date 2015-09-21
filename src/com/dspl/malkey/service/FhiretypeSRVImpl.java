package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FhiretypeDAO;
import com.dspl.malkey.domain.Fhiretype;

public class FhiretypeSRVImpl implements FhiretypeSRV {

	@Resource(name="fhiretypeDAO")
	FhiretypeDAO fhiretypeDAO;

	@Override
	public List<Fhiretype> List(int startIndex, int numItems) {
		return fhiretypeDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fhiretype> ListAll() {
		return fhiretypeDAO.ListAll();
	}

	@Override
	public int count() {
		return fhiretypeDAO.count();
	}

	@Override
	public Boolean create(Fhiretype fhiretype) {
		try {
			fhiretypeDAO.create(fhiretype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fhiretype findByID(String hiretypeid) {
		return fhiretypeDAO.findByID(hiretypeid);
	}

	@Override
	public Boolean removeByID(String hiretypeid) {
		try {
			fhiretypeDAO.removeByID(hiretypeid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fhiretype fhiretype) {
		try {
			fhiretypeDAO.udpate(fhiretype);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
