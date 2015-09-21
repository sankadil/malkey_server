package com.dspl.malkey.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.dspl.malkey.domain.Fmaintenance;

public interface FmaintenanceDAO {
	int count();
	String create(Fmaintenance fmaintenance);
	Fmaintenance findByID(String refno);
	List<Fmaintenance> List(int startIndex, int numItems);
	List<Fmaintenance> ListAll();
	Boolean update(Fmaintenance fmaintenance);
	void removeByID(String refno);
	ArrayList getExtMaintTypes(String regno,String refno); //Get Existing Maintenance Types For By Vehicle
	List<Fmaintenance> listMaintenance(ArrayList paraList);
	Boolean updateStatus(Fmaintenance fmaintenance);
	
	public Calendar resetTime(Calendar tmpCal);
	public Calendar copyCalendar(Calendar tmp); 
	
	List<Fmaintenance> getMaintenanceReminder(String dateFrom,String dateTo,ArrayList paraList);
}
