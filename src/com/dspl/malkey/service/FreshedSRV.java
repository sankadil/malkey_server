package com.dspl.malkey.service;

import java.util.List;
import com.dspl.malkey.domain.*;

public interface FreshedSRV {
	int count();
	Boolean create(Freshed freshed);
	Freshed findByID(String agrno);
	List<Freshed> list(int startIndex, int numItems);
	List<Freshed> listAll();
	Boolean update(Freshed freshed);
	Boolean removeByID(String agrno);
	List<Freshed> listAllDescription();
	void runTask();
	List<Freshed> listAllDescriptionPage(int startIndex, int numItems);
	List<Freshed> feelLucky(String searchText);
}
