package com.dspl.malkey.dao;

import java.util.List;
import com.dspl.malkey.domain.CommMF;
import com.dspl.malkey.domain.Fmasterlist;

public interface FmasterlistDAO {
	List<Fmasterlist> ListAll();
	List<CommMF> ListMF(String tableName,String keyColumn);
	Boolean save(String tableName,String KeyField,String Id,String Description, String addFieldName, String addFieldValue);
	Boolean update(String tableName,String KeyField,String Id,String Description, String addFieldName, String addFieldValue);
	Boolean remove(String tableName,String KeyField,String Id);
	public int isAssociated(String tableName,String KeyField,String Id);
}
