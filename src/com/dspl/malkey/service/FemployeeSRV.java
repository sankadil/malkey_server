package com.dspl.malkey.service;

import java.util.Date;
import java.util.List;

import com.dspl.malkey.domain.Femployee;

public interface FemployeeSRV {
	int count();
	//Boolean create(Femployee femployee);
	Femployee findByID(String empid);
	List<Femployee> List(int startIndex, int numItems);
	List<Femployee> ListAll();
	//Boolean udpate(Femployee femployee);
	//Boolean removeByID(String empid);
	List<Femployee> DriverListAll();
	
	String create(Femployee femployee);
	Boolean update(Femployee femployee);
	Boolean removeByID(String empid);
	List<Femployee> ListEmployees();
	List<Femployee> DriverListAvailable(Date fromDate, Date toDate);
	
	List<Femployee> getEmpList(); //Employees Without Login Credentials
	List<Femployee> getEmployees(); //Employees (Empid,EmpName) 
	public List<Femployee> getDriversByResNo(String resno);
}
