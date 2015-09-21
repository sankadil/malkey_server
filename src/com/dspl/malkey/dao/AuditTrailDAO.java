package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.AuditTrail;;

public interface AuditTrailDAO {
	List<AuditTrail> listTrailForRelationTableField(String sTableName, 
			String sFieldName, String sUUID);
	
	List<AuditTrail> listTrailForMasterTableField(String sTableName, 
			String sFieldName, String sUUID);
}
