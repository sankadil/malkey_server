package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FguarantorDAO;
import com.dspl.malkey.domain.Fguarantor;

public class FguarantorSRVImpl implements FguarantorSRV {

	@Resource(name="fguarantorDAO")
	FguarantorDAO fguarantorDAO;

	@Override
	public List<Fguarantor> List(int startIndex, int numItems) {
		return fguarantorDAO.List(startIndex, numItems);
	}

	@Override
	public List<Fguarantor> ListAll() {
		return fguarantorDAO.ListAll();
	}

	@Override
	public int count() {
		return fguarantorDAO.count();
	}

	@Override
	public String create(Fguarantor fguarantor) {
		try {
			return fguarantorDAO.create(fguarantor);
		} catch (Exception e) {
			System.out.println("FguarantorSRVImpl: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Fguarantor findByID(String gid) {
		return fguarantorDAO.findByID(gid);
	}

	@Override
	public Boolean removeByID(String gid) {
		try {
			fguarantorDAO.removeByID(gid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean update(Fguarantor fguarantor) {
		try {
			fguarantorDAO.update(fguarantor);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Fguarantor> listApprovedGuarantors() {
		return fguarantorDAO.listApprovedGuarantors();
	}

	@Override
	public Boolean approveGuarantor(String gid, String approved) {
		return fguarantorDAO.approveGuarantor(gid, approved);
	}

	@Override
	public Fguarantor findByDebcode(String debcode) {
		return fguarantorDAO.findByDebcode(debcode);
	}

}
