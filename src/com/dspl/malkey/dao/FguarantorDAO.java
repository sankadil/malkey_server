package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fguarantor;

public interface FguarantorDAO {
	int count();
	String create(Fguarantor fguarantor);
	Fguarantor findByID(String gid);
	List<Fguarantor> List(int startIndex, int numItems);
	List<Fguarantor> ListAll();
	Boolean update(Fguarantor fguarantor);
	Boolean removeByID(String gid);
	List<Fguarantor> listApprovedGuarantors();
	Boolean approveGuarantor(String gid,String approved);
	Fguarantor findByDebcode(String debcode);
}
