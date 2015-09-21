package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Freshed;

public interface FreshedDAO {
	int count();
	Boolean create(Freshed freshed);
	Freshed findByID(String agrno);
	List<Freshed> list(int startIndex, int numItems);
	List<Freshed> listAll();
	List<Freshed> listAllDescription();
	Boolean update(Freshed freshed);
	Boolean removeByID(String agrno);
	List<Freshed> listAllDescriptionPage(int startIndex, int numItems);
	List<Freshed> feelLucky(String searchText);
}
