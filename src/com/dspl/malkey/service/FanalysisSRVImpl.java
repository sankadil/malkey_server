package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FanalysisDAO;
import com.dspl.malkey.domain.Fanalysis;

public class FanalysisSRVImpl implements FanalysisSRV {

	@Resource(name="fanalysisDAO")
	FanalysisDAO fanalysisDAO;	
	
	@Override
	public int count() {
		return fanalysisDAO.count();
	}

	@Override
	public Boolean create(Fanalysis fanalysis) {
		try {
			fanalysisDAO.create(fanalysis);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fanalysis findByID(String ancode) {
		return fanalysisDAO.findByID(ancode);
	}

	@Override
	public List<Fanalysis> list(int startIndex, int numItems) {
		return fanalysisDAO.list(startIndex, numItems);
	}

	@Override
	public List<Fanalysis> listAll() {
		return fanalysisDAO.listAll();
	}

	@Override
	public List<Fanalysis> listByAnCodeOfVehicle(String ancode) {
		return fanalysisDAO.listByAnCodeOfVehicle(ancode);
	}

	@Override
	public Boolean removeByID(String ancode) {
		try {
			fanalysisDAO.removeByID(ancode);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fanalysis fanalysis) {
		try {
			fanalysisDAO.udpate(fanalysis);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
