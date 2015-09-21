package com.dspl.malkey.service;

import javax.annotation.Resource;
import java.util.List;

import com.dspl.malkey.dao.FresconfirmDAO;
import com.dspl.malkey.domain.Fresconfirm;

public class FresconfirmSRVImpl implements FresconfirmSRV {

	@Resource(name="fresconfirmDAO")
	FresconfirmDAO fresconfirmDAO;
	
	@Override
	public List<Fresconfirm> list(int startIndex, int numItems) {
		return fresconfirmDAO.list(startIndex, numItems);
	}

	@Override
	public List<Fresconfirm> listAll() {
		return fresconfirmDAO.listAll();
	}

	@Override
	public List<Fresconfirm> listUnconfirmedResevations() {
		return fresconfirmDAO.listUnconfirmedResevations();
	}

	@Override
	public int count() {
		return fresconfirmDAO.count();
	}

	@Override
	public Boolean create(Fresconfirm fresconfirm) {
		try {
			fresconfirmDAO.create(fresconfirm);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fresconfirm findByID(int recordid) {
		return fresconfirmDAO.findByID(recordid);
	}

	@Override
	public List<Fresconfirm> listByResNo(String sResNo) {
		return fresconfirmDAO.listByResNo(sResNo);
	}

	@Override
	public Boolean removeByID(String recordid) {
		try {
			fresconfirmDAO.removeByID(recordid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int awaitingConfirmation(String sResNo) {
		return fresconfirmDAO.awaitingConfirmation(sResNo);
	}

	@Override
	public Boolean updateResAsConfirmed(String sResNo) {
		try {
			fresconfirmDAO.updateResAsConfirmed(sResNo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean updateResWithAlterationDtl(String sResNo, String sComment) {
		try {
			fresconfirmDAO.updateResWithAlterationDtl(sResNo, sComment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
