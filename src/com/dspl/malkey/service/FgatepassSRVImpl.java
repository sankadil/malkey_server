package com.dspl.malkey.service;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FgatepassDAO;
import com.dspl.malkey.domain.Femployee;
import com.dspl.malkey.domain.Fgatepass;
import com.dspl.malkey.domain.Fvehicle;

public class FgatepassSRVImpl implements FgatepassSRV {

	@Resource(name="fgatepassDAO")
	FgatepassDAO fgatepassDAO;

	@Override
	public List<Fgatepass> List(int startIndex, int numItems) {
		return fgatepassDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fgatepass> ListAll() {
		return fgatepassDAO.ListAll();
	}

	@Override
	public int count() {
		return fgatepassDAO.count();
	}

	@Override
	public String create(Fgatepass fgatepass) {
		try {
			return fgatepassDAO.create(fgatepass);
		} catch (Exception e) {
			System.out.println("FgatepassSrv create: " + e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public Fgatepass findByID(String passno) {
		return fgatepassDAO.findByID(passno);
	}

	@Override
	public Boolean removeByID(String passno) {
		try {
			fgatepassDAO.removeByID(passno);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean update(Fgatepass fgatepass) {
		try {
			return fgatepassDAO.update(fgatepass);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Fvehicle> getVehicleList() {
		return fgatepassDAO.getVehicleList();
	}

	@Override
	public List<Femployee> getDriverList() {
		return fgatepassDAO.getDriverList();
	}

	@Override
	public List<Femployee> getEmployeeList() {
		return fgatepassDAO.getEmployeeList();
	}

	@Override
	public Fgatepass findByPassNo(String passno) {
		return fgatepassDAO.findByPassNo(passno);
	}

	@Override
	public List<Fgatepass> getRefList() {
		return fgatepassDAO.getRefList();
	}

	@Override
	public List<Fgatepass> getGatePassList(String dateFrom, String dateTo, String status) {
		try{
			return fgatepassDAO.getGatePassList(dateFrom, dateTo, status);
		}catch(Exception e){
			System.out.println("FgatepassSrvImpl getGetPassList: " + e.getMessage());
		}
		return null;
	}

}
