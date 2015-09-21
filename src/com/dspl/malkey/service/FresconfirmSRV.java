package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fresconfirm;

public interface FresconfirmSRV {
	int count();
	Boolean create(Fresconfirm fresconfirm);
	Fresconfirm findByID(int recordid);
	List<Fresconfirm> list(int startIndex, int numItems);
	List<Fresconfirm> listAll();
	List<Fresconfirm> listUnconfirmedResevations();
	List<Fresconfirm> listByResNo(String sResNo);
	Boolean removeByID(String recordid);
	int awaitingConfirmation(String sResNo);
	Boolean updateResAsConfirmed(String sResNo);
	Boolean updateResWithAlterationDtl(String sResNo, String sComment);
}
