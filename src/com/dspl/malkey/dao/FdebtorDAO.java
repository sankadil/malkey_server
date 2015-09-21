package com.dspl.malkey.dao;

import java.util.ArrayList;
import java.util.List;
import com.dspl.malkey.domain.Fdebtor;

public interface FdebtorDAO {
	int count();
	Fdebtor findByID(String debcode);
	List<Fdebtor> List(int startIndex, int numItems);
	List<Fdebtor> ListAll();
	String create(Fdebtor fdebtor,ArrayList driList);
	Boolean update(Fdebtor fdebtor,ArrayList driList);
	Boolean removeByID(String debcode);
	List<Fdebtor> getDebList();
	List<Fdebtor> getGDebList();
	List<Fdebtor> getDebtorList(); //Report
	List<Fdebtor> getDebtorList(String clientType,boolean isLongterm); //Report
	List<Fdebtor> listAllOptimized();
	List<Fdebtor> listAllOptimizedPage(int startIndex, int numItems);
	List<Fdebtor> listAllNameNIC();
	List<Fdebtor> listLikeName(String name);
}
