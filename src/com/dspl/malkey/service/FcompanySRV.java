package com.dspl.malkey.service;

import java.util.List;
import com.dspl.malkey.domain.Fcompany;
import com.dspl.malkey.domain.Fcompanytax;

public interface FcompanySRV {
	int count();
	Fcompany findByID(String companyid);
	List<Fcompany> List(int startIndex, int numItems);
	List<Fcompany> ListAll();
	Boolean create(Fcompany fcompany, List<Fcompanytax> fcompanytaxes);
	Boolean removeByID(String companyid);
	List<Fcompany> listCompanies();
	Boolean update(Fcompany fcompany, List<Fcompanytax> fcompanytaxes);
}
