package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fmaintstatus;

public interface FmaintstatusSRV {
	int count();
	Boolean create(Fmaintstatus fmaintstatus);
	Fmaintstatus findByID(String statusid);
	List<Fmaintstatus> List(int startIndex, int numItems);
	List<Fmaintstatus> ListAll();
	Boolean udpate(Fmaintstatus fmaintstatus);
	Boolean removeByID(String statusid);
}
