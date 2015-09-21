package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.AuditTrail;

public interface AuditTrailSRV {
	List<AuditTrail> listTrailForRelationTableField(String sTableName, 
			String sFieldName, String sUUID);

	List<AuditTrail> listTrailForMasterTableField(String sTableName,
			String sFieldName, String sUUID);
}
