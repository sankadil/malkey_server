package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.AuditTrailDAO;
import com.dspl.malkey.domain.AuditTrail;

public class AuditTrailSRVImpl implements AuditTrailSRV {
	@Resource(name="auditTrailDAO")
	AuditTrailDAO auditTrailDAO;
	
	@Override
	public List<AuditTrail> listTrailForRelationTableField(String sTableName, String sFieldName, String sUUID) {
		return auditTrailDAO.listTrailForRelationTableField(sTableName, sFieldName, sUUID);
	}

	@Override
	public List<AuditTrail> listTrailForMasterTableField(String sTableName, String sFieldName, String sUUID) {
		return auditTrailDAO.listTrailForMasterTableField(sTableName, sFieldName, sUUID);
	}

}
