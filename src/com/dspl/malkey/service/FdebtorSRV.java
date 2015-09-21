package com.dspl.malkey.service;

import java.util.ArrayList;
import java.util.List;
import com.dspl.malkey.domain.Fdebtor;

public interface FdebtorSRV {
	int count();
	Fdebtor findByID(String debcode);
	List<Fdebtor> List(int startIndex, int numItems);
	List<Fdebtor> ListAll();
	String create(Fdebtor fdebtor,ArrayList driList);
	Boolean update(Fdebtor fdebtor,ArrayList driList);
	Boolean removeByID(String debcode);
	List<Fdebtor> getDebList();
	List<Fdebtor> getGDebList();
	List<Fdebtor> getDebtorListRPT(); //Report
	List<Fdebtor> getDebtorList(String clientType,boolean isLongterm); //Report
	List<Fdebtor> listAllOptimized();
	List<Fdebtor> listAllOptimizedPage(int startIndex, int numItems);
	List<Fdebtor> listAllNameNIC();
	List<Fdebtor> listLikeName(String name);
}
