package com.dspl.malkey.service;

import java.util.ArrayList;
import java.util.List;

import com.dspl.malkey.domain.CommMF;
import com.dspl.malkey.domain.Fmasterlist;
import flex.messaging.io.ArrayCollection;

public interface FmasterlistSRV {
	List<Fmasterlist> ListAll();
	List<CommMF> ListMF(String tableName,String keyColumn);
//	Boolean save(String tableName,String KeyField,String Id,String Description);
//	Boolean update(String tableName,String KeyField,String Id,String Description);
	Boolean save(String tableName,String KeyField,String Id,String Description, String addFieldName, String addFieldValue);
	Boolean update(String tableName,String KeyField,String Id,String Description, String addFieldName, String addFieldValue);
	Boolean remove(String tableName,String KeyField,String Id);
}
