package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fguarantor;

public interface FguarantorSRV {
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
