package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FvehiclerateDAO;
import com.dspl.malkey.domain.Fvehiclerate;
import com.dspl.malkey.domain.FvehicleratePK;

public class FvehiclerateSRVImpl implements FvehiclerateSRV {

	@Resource(name="fvehiclerateDAO")
	FvehiclerateDAO fvehiclerateDAO;

	@Override
	public List<Fvehiclerate> List(int startIndex, int numItems) {
		return fvehiclerateDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fvehiclerate> ListAll() {
		return fvehiclerateDAO.ListAll();
	}

	@Override
	public int count() {
		return fvehiclerateDAO.count();
	}

	@Override
	public Boolean create(Fvehiclerate fvehiclerate) {
		try {
			fvehiclerateDAO.create(fvehiclerate);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Fvehiclerate findByID(FvehicleratePK fvehicleratePK) {
		return fvehiclerateDAO.findByID(fvehicleratePK);
	}

	@Override
	public Boolean removeByID(FvehicleratePK fvehicleratePK) {
		try {
			fvehiclerateDAO.removeByID(fvehicleratePK);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean udpate(Fvehiclerate fvehiclerate) {
		try {
			fvehiclerateDAO.udpate(fvehiclerate);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Fvehiclerate> listByVehiModelID(String vehiModelID) {
		return fvehiclerateDAO.listByVehiModelID(vehiModelID);
	}

	@Override
	public List<Fvehiclerate> findByIDList(
			List<FvehicleratePK> lstfvehicleratePK) {
		return fvehiclerateDAO.findByIDList(lstfvehicleratePK);
	}

	@Override
	public Boolean udpateList(String sVehiModelID,	List<Fvehiclerate> fvehiclerate) {
		try {
			fvehiclerateDAO.udpateList(sVehiModelID, fvehiclerate);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Fvehiclerate> getRateList(String vehimodelid) {
		return fvehiclerateDAO.getRateList(vehimodelid);
	}

}
