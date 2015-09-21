package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FinvdetDAO;
import com.dspl.malkey.domain.Finvdet;

public class FinvdetSRVImpl implements FinvdetSRV {

	@Resource(name="finvdetDAO")
	FinvdetDAO finvdetDAO;
	
	@Override
	public List<Finvdet> listAll() {
		return finvdetDAO.listAll();
	}

}
