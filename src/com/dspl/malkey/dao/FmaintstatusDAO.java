package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fmaintstatus;

public interface FmaintstatusDAO {
	int count();
	void create(Fmaintstatus fmaintstatus);
	Fmaintstatus findByID(String statusid);
	List<Fmaintstatus> List(int startIndex, int numItems);
	List<Fmaintstatus> ListAll();
	void udpate(Fmaintstatus fmaintstatus);
	void removeByID(String statusid);
}
