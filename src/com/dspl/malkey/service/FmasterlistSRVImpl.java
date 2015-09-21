package com.dspl.malkey.service;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.dspl.malkey.dao.FmasterlistDAO;
import com.dspl.malkey.domain.CommMF;
import com.dspl.malkey.domain.Fmasterlist;

import flex.messaging.io.ArrayCollection;

public class FmasterlistSRVImpl implements FmasterlistSRV {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fmasterlistDAO")
	FmasterlistDAO fmasterlistDAO;

	@Override
	public List<Fmasterlist> ListAll() {
		return fmasterlistDAO.ListAll();
	}

	@Override
	public List<CommMF> ListMF(String tableName, String keyColumn) {
		return fmasterlistDAO.ListMF(tableName, keyColumn);
	}

	@Override
	public Boolean remove(String tableName, String KeyField, String Id) {
		return fmasterlistDAO.remove(tableName, KeyField, Id);
	}

	@Override
	public Boolean save(String tableName, String KeyField, String Id,
			String Description, String addFieldName, String addFieldValue) {
		return fmasterlistDAO.save(tableName, KeyField, Id, Description, addFieldName, addFieldValue);
	}

	@Override
	public Boolean update(String tableName, String KeyField, String Id,
			String Description, String addFieldName, String addFieldValue) {
		return fmasterlistDAO.update(tableName, KeyField, Id, Description, addFieldName, addFieldValue);
	}

//	@Override
//	public Boolean save(String tableName, String KeyField, String Id, String Description) {
//		return fmasterlistDAO.save(tableName, KeyField, Id, Description);
//	}
//
//	@Override
//	public Boolean update(String tableName, String KeyField, String Id,	String Description) {
//		return fmasterlistDAO.update(tableName, KeyField, Id, Description);
//	}
}
