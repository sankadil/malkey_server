package com.dspl.malkey.dao;

import java.util.Calendar;
import java.util.List;

import com.dspl.malkey.domain.Femployee;
import com.dspl.malkey.domain.Fgatepass;
import com.dspl.malkey.domain.Fvehicle;

public interface FgatepassDAO {
	int count();
	String create(Fgatepass fgatepass);
	Fgatepass findByID(String passno);
	List<Fgatepass> List(int startIndex, int numItems);
	List<Fgatepass> ListAll();
	Boolean update(Fgatepass fgatepass);
	Boolean removeByID(String passno);
	List<Fvehicle> getVehicleList();
	List<Femployee> getEmployeeList();
	List<Femployee> getDriverList();
	List<Fgatepass> getRefList();
	Fgatepass findByPassNo(String passno);
	List<Fgatepass> getGatePassList(String dateFrom,String dateTo,String status);
}
