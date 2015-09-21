package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fresconfirm;
import com.dspl.malkey.domain.Freservation;

public interface FresconfirmDAO {
	int count();
	void create(Fresconfirm fresconfirm);
	Fresconfirm findByID(int recordid);
	List<Fresconfirm> list(int startIndex, int numItems);
	List<Fresconfirm> listAll();
	List<Fresconfirm> listUnconfirmedResevations();
	List<Fresconfirm> listByResNo(String sResNo);
	void removeByID(String recordid);
	int awaitingConfirmation(String sResNo);
	void updateResAsConfirmed(String sResNo);
	void updateResWithAlterationDtl(String sResNo, String sComment);
	void createByFreservation(Freservation fresservation,String debCode);
}
