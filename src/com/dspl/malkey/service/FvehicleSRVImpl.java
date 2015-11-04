package com.dspl.malkey.service;

import java.net.InetAddress;
import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FvehicleDAO;
import com.dspl.malkey.domain.Fexpirationlistingrpt;
import com.dspl.malkey.domain.Fvehicle;
import com.dspl.malkey.domain.Fvehicledamage;
import com.dspl.malkey.domain.Fvehicleinventry;
import com.dspl.malkey.domain.Fvehiclepic;
import com.dspl.malkey.domain.Fvehiclerate;

import flex.messaging.FlexContext;

public class FvehicleSRVImpl implements FvehicleSRV {
	
	@Resource(name="fvehicleDAO")
	FvehicleDAO fvehicleDAO;

	@Override
	public List<Fvehicle> list(int startIndex, int numItems) {
		return fvehicleDAO.list(startIndex, numItems);
	}

	@Override
	public List<Fvehicle> listAll() {
		return fvehicleDAO.listAll();
	}

	@Override
	public int count() {
		return fvehicleDAO.count();
	}

	@Override
	public Boolean create(Fvehicle fvehicle, List<Fvehicledamage> fvehicledamage, List<Fvehicleinventry> fvehicleinventry, List<Fvehiclerate> fvehiclerate, List<Fvehiclepic> fvehiclepic) {
		try {
			fvehicle.setAddmach(InetAddress.getByName(FlexContext.getHttpRequest().getRemoteHost()).getHostName());
			fvehicleDAO.create(fvehicle, fvehicledamage, fvehicleinventry, fvehiclerate, fvehiclepic);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fvehicle findByID(String regNo) {
		return fvehicleDAO.findByID(regNo);
	}

	@Override
	public Boolean removeByID(String regNo) {
		int lnRetVal = fvehicleDAO.isVehicleRemovable(regNo);
		
		if (lnRetVal>0)
			// Details of this vehicle has made use of in other relational tables
			return false;
		
		try {
			fvehicleDAO.removeByID(regNo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean update(Fvehicle fvehicle, List<Fvehicledamage> fvehicledamage, List<Fvehicleinventry> fvehicleinventry, List<Fvehiclerate> fvehiclerate, List<Fvehiclepic> fvehiclepic) {
		try {
			fvehicle.setModifiedmach(InetAddress.getByName(FlexContext.getHttpRequest().getRemoteHost()).getHostName());
			fvehicleDAO.update(fvehicle, fvehicledamage, fvehicleinventry, fvehiclerate, fvehiclepic);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Fexpirationlistingrpt> getExpirationList(String fromDate, String toDate, String expType) {
		return fvehicleDAO.getExpirationList(fromDate, toDate, expType);
	}

	@Override
	public List<Fvehicle> getVehicleSummary() {
		return fvehicleDAO.getVehicleSummary();
	}

	@Override
	public Boolean isVehicleRemovable(String regNo) {
		int lnRetVal = fvehicleDAO.isVehicleRemovable(regNo);
		if (lnRetVal==0)
			return true;
		else
			return false;
				
	}

	@Override
	public List<Fvehicle> getVehicleList(String locationId) {
		// TODO Auto-generated method stub
		return fvehicleDAO.getVehicleList(locationId);
	}

	@Override
	public List<Fvehicle> getVehicleSummary2(String vstatus, String ownertype) {
		return fvehicleDAO.getVehicleSummary2(vstatus, ownertype);
	}

	@Override
	public int isDupplicate(String regno) {
		return fvehicleDAO.isDupplicate(regno);
	}


	
}
