package com.dspl.malkey.service;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FsupplierDAO;
import com.dspl.malkey.domain.Fsupplier;

public class FsupplierSRVImpl implements FsupplierSRV {

	@Resource(name="fsupplierDAO")
	FsupplierDAO fsupplierDAO;

	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Override
	public List<Fsupplier> list(int startIndex, int numItems) {
		return fsupplierDAO.list(startIndex, numItems);
	}

	@Override
	public List<Fsupplier> listAll() {
		return fsupplierDAO.listAll();
	}

	@Override
	public int count() {
		return fsupplierDAO.count();
	}

	@Override
	public Boolean create(Fsupplier fsupplier) {
		try {
			fsupplier.setAdddate(Calendar.getInstance());
			fsupplier.setAddmach(userInfoSRV.getMachineName());
			fsupplier.setAdduser(userInfoSRV.getUser());
			fsupplierDAO.create(fsupplier);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fsupplier findByID(String supcode) {
		return fsupplierDAO.findByID(supcode);
	}

	@Override
	public Boolean removeByID(String supcode) {
		try {
			fsupplierDAO.removeByID(supcode);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean udpate(Fsupplier fsupplier) {
		try {
			fsupplier.setAdddate(Calendar.getInstance());
			fsupplier.setAddmach(userInfoSRV.getMachineName());
			fsupplier.setAdduser(userInfoSRV.getUser());
			fsupplierDAO.udpate(fsupplier);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Fsupplier> listBySupType(String supType) {
		return fsupplierDAO.listBySupType(supType);
	}

}
