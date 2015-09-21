package com.dspl.malkey.service;

import java.util.List;
import javax.annotation.Resource;
import com.dspl.malkey.dao.FcompanytaxDAO;
import com.dspl.malkey.domain.Fcompanytax;

public class FcompanytaxSRVImpl implements FcompanytaxSRV{

	@Resource(name="fcompanytaxDAO")
	FcompanytaxDAO fcompanytaxDAO;
	
	@Override
	public List<Fcompanytax> ListAll() {
		try {
			return fcompanytaxDAO.ListAll();
		} catch (Exception e) {
			System.out.println("FcompanytaxSRVImpl ListAll: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean update(List<Fcompanytax> fcompanytaxes) {
		try {
			return fcompanytaxDAO.update(fcompanytaxes);
		} catch (Exception e) {
			System.out.println("FcompanytaxSRVImpl update: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getTaxComCode(String companyid, String hiretypeid) {
		try{
			return fcompanytaxDAO.getTaxComCode(companyid, hiretypeid);
		}catch(Exception e){
			System.out.println("fcompanytaxSRVImpl getTaxComCode: " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Tax Details Not Found For The Selected Company & Hire Type");
		}	
	}

}
