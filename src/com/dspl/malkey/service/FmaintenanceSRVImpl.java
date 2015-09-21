package com.dspl.malkey.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FmaintenanceDAO;
import com.dspl.malkey.domain.Fmaintenance;

public class FmaintenanceSRVImpl implements FmaintenanceSRV {

	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;

	@Override
	public List<Fmaintenance> List(int startIndex, int numItems) {
		return fmaintenanceDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fmaintenance> ListAll() {
		return fmaintenanceDAO.ListAll();
	}

	@Override
	public int count() {
		return fmaintenanceDAO.count();
	}

	@Override
	public String create(Fmaintenance fmaintenance) 
	{
			return fmaintenanceDAO.create(fmaintenance);
	}

	@Override
	public Fmaintenance findByID(String refno) {
		return fmaintenanceDAO.findByID(refno);
	}

	@Override
	public Boolean removeByID(String refno) {
		try {
			fmaintenanceDAO.removeByID(refno);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean update(Fmaintenance fmaintenance) 
	{
		return fmaintenanceDAO.update(fmaintenance);
	}

	@Override
	public ArrayList getExtMaintTypes(String regno,String refno) 
	{
		return fmaintenanceDAO.getExtMaintTypes(regno,refno);
	}

	@Override
	public List<Fmaintenance> listMaintenance(ArrayList paraList) 
	{
		return fmaintenanceDAO.listMaintenance(paraList);
	}

	@Override
	public Boolean updateStatus(Fmaintenance fmaintenance)
	{
		return fmaintenanceDAO.updateStatus(fmaintenance);
	}

	@Override
	public List<Fmaintenance> getMaintenanceReminder(String dateFrom, String dateTo, ArrayList paraList) {
		return fmaintenanceDAO.getMaintenanceReminder(dateFrom, dateTo, paraList);
	}

}
