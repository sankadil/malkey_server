package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fcompanytax;

public interface FcompanytaxDAO {
	List<Fcompanytax> ListAll();
	Boolean update(List<Fcompanytax> fcompanytaxes);
	String getTaxComCode(String companyid,String hiretypeid);
}
