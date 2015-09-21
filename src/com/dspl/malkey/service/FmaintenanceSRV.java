package com.dspl.malkey.service;

import java.util.ArrayList;
import java.util.List;

import com.dspl.malkey.domain.Fmaintenance;

public interface FmaintenanceSRV {
	int count();
	String create(Fmaintenance fmaintenance);
	Fmaintenance findByID(String refno);
	List<Fmaintenance> List(int startIndex, int numItems);
	List<Fmaintenance> ListAll();
	Boolean update(Fmaintenance fmaintenance);
	Boolean removeByID(String refno);
	ArrayList getExtMaintTypes(String regno,String refno); //Get Existing Maintenance Types For By Vehicle
	List<Fmaintenance> listMaintenance(ArrayList paraList);
	Boolean updateStatus(Fmaintenance fmaintenance);
	
	List<Fmaintenance> getMaintenanceReminder(String dateFrom,String dateTo,ArrayList paraList);
}
