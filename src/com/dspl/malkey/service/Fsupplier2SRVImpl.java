package com.dspl.malkey.service;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.Fsupplier2DAO;
import com.dspl.malkey.domain.Fsupplier2;
import com.dspl.malkey.domain.Fvehicle;

public class Fsupplier2SRVImpl implements Fsupplier2SRV {

	@Resource(name="fsupplier2DAO")
	Fsupplier2DAO fsupplier2DAO;

	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Override
	public List<Fsupplier2> list(int startIndex, int numItems) {
		return fsupplier2DAO.list(startIndex, numItems);
	}

	@Override
	public List<Fsupplier2> listAll() {
		return fsupplier2DAO.listAll();
	}

	@Override
	public int count() {
		return fsupplier2DAO.count();
	}

	@Override
	public Boolean create(Fsupplier2 fsupplier2) {
		try {
			fsupplier2.setAdddate(Calendar.getInstance());
			fsupplier2.setAddmach(userInfoSRV.getMachineName());
			fsupplier2.setAdduser(userInfoSRV.getUser());
			fsupplier2DAO.create(fsupplier2);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fsupplier2 findByID(String supcode) {
		return fsupplier2DAO.findByID(supcode);
	}

	@Override
	public Boolean removeByID(String supcode) {
		try {
			fsupplier2DAO.removeByID(supcode);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fsupplier2 fsupplier2) {
		try {
			fsupplier2.setModifieduser(userInfoSRV.getUser());
			fsupplier2.setModifiedmach(userInfoSRV.getMachineName());
			fsupplier2.setModifieddate(Calendar.getInstance());
			setAddedDetails(fsupplier2);
			fsupplier2DAO.udpate(fsupplier2);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void setAddedDetails(Fsupplier2 fsupplier2)
	{
		Fsupplier2 temp=findByID(fsupplier2.getSupcode());
		fsupplier2.setAdddate(temp.getAdddate());
		fsupplier2.setAdduser(temp.getAdduser());
		fsupplier2.setAddmach(temp.getAddmach());
	}
	
	@Override
	public List<Fsupplier2> listBySupType(String supType) {
		return fsupplier2DAO.listBySupType(supType);
	}

}
