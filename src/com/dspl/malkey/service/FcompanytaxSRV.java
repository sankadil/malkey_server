package com.dspl.malkey.service;

import java.util.List;
import com.dspl.malkey.domain.Fcompanytax;

public interface FcompanytaxSRV {
	List<Fcompanytax> ListAll();
	Boolean update(List<Fcompanytax> fcompanytaxes);
	String getTaxComCode(String companyid,String hiretypeid);
}
