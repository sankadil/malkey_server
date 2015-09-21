package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FinvhedDAO;
import com.dspl.malkey.domain.Finvhed;

public class FinvhedSRVImpl implements FinvhedSRV {

	@Resource(name="finvhedDAO")
	FinvhedDAO finvhedDAO;
	
	@Override
	public List<Finvhed> listAll() {
		return finvhedDAO.listAll();
	}

}
