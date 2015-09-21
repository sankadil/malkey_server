package com.dspl.malkey.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FemployeeDAO;
import com.dspl.malkey.domain.Femployee;

public class FemployeeSRVImpl implements FemployeeSRV {

	@Resource(name="femployeeDAO")
	FemployeeDAO femployeeDAO;

	@Override
	public List<Femployee> List(int startIndex, int numItems) {
		return femployeeDAO.List(startIndex, numItems);
	}

	@Override
	public List<Femployee> ListAll() {
		return femployeeDAO.ListAll();
	}

	@Override
	public int count() {
		return femployeeDAO.count();
	}

//	@Override
//	public Boolean create(Femployee femployee) {
//		try {
//			femployeeDAO.create(femployee);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}

	@Override
	public Femployee findByID(String empid) {
		return femployeeDAO.findByID(empid);
	}

//	@Override
//	public Boolean removeByID(String empid) {
//		try {
//			femployeeDAO.removeByID(empid);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}

//	@Override
//	public Boolean udpate(Femployee femployee) {
//		try {
//			femployeeDAO.udpate(femployee);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}

	@Override
	public List<Femployee> DriverListAll() {
		return femployeeDAO.DriverListAll();
	}

	@Override
	public Boolean update(Femployee femployee) {
		return femployeeDAO.update(femployee);
	}

	@Override
	public String create(Femployee femployee) {
		return femployeeDAO.create(femployee);
	}

	@Override
	public Boolean removeByID(String empid) {
		return femployeeDAO.removeByID(empid);
	}

	@Override
	public List<Femployee> ListEmployees() {
		return femployeeDAO.ListEmployees();
	}

	@Override
	public List<Femployee> DriverListAvailable(Date fromDate, Date toDate) {
		return femployeeDAO.DriverListAvailable(fromDate, toDate);
	}

	@Override
	public List<Femployee> getEmpList() {
		return femployeeDAO.getEmpList();
	}

	@Override
	public List<Femployee> getEmployees() {
		return femployeeDAO.getEmployees();
	}

	@Override
	public List<Femployee> getDriversByResNo(String resno) {
		return femployeeDAO.getDriversByResNo(resno);
	}


}
