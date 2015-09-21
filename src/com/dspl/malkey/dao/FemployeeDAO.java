package com.dspl.malkey.dao;

import java.util.Date;
import java.util.List;

import com.dspl.malkey.domain.Femployee;

public interface FemployeeDAO {
	int count();
	//void create(Femployee femployee);
	Femployee findByID(String empid);
	List<Femployee> List(int startIndex, int numItems);
	List<Femployee> ListAll();
	//void udpate(Femployee femployee);
	//void removeByID(String empid);
	List<Femployee> DriverListAll();

	List<Femployee> DriverListAvailable(Date fromDate, Date toDate);

	String create(Femployee femployee);
	Boolean update(Femployee femployee);
	Boolean removeByID(String empid);
	List<Femployee> ListEmployees();
	
	List<Femployee> getEmpList(); //Employees Without Login Credentials
	List<Femployee> getEmployees(); //Employees (Empid,EmpName) 
	public List<Femployee> getDriversByResNo(String resno);
}
