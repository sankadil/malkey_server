package com.dspl.malkey.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.AuditTrail;

import flex.messaging.io.ArrayCollection;

public class AuditTrailDAOImpl implements AuditTrailDAO {
	@PersistenceContext
	EntityManager em;

	@Transactional
	@Override
	public List<AuditTrail> listTrailForRelationTableField(String sTableName, String sFieldName, String sUUID) {
		// Detail audit of the field name
		List<AuditTrail> lstAuditTrail = new ArrayCollection();
		List<Object[]> lstHistoryData  = em.createNativeQuery("SELECT f.type,f.newvalue,f.adduser,f.updatedate FROM " + sTableName.trim() + " f WHERE f.type='I' AND f.fieldname=?1 AND f.uuid=?2 ORDER BY f.updatedate DESC")
			.setParameter(1, sFieldName)
			.setParameter(2, sUUID)
			.getResultList();
		
		String lsType;
		String lsNewValue;
		String lsAddUser;
		Date ldUpdateDate;
		int lnCount = 0;
		
		for (Object[] oRec : lstHistoryData){
			lnCount += 1;
			
			// Make the 1st most Audit entry as an Insert and the rest as Update(s)
			if (lnCount==lstHistoryData.size())
				lsType = "I" ;
			else
				lsType = "U";
			
			lsNewValue = (String)oRec[1];
			lsAddUser = (String)oRec[2];
			ldUpdateDate = (Date)oRec[3];
			
			AuditTrail newRec = new AuditTrail();
			newRec.setType(lsType);
			newRec.setAdduser(lsAddUser);
			newRec.setNewvalue(lsNewValue);
			newRec.setUpdatedate(ldUpdateDate);
			lstAuditTrail.add(newRec);
		}
		
		return lstAuditTrail;
	}

	@Transactional
	@Override
	public List<AuditTrail> listTrailForMasterTableField(String sTableName,	String sFieldName, String sUUID) {
		// Detail audit of the field name
		List<AuditTrail> lstAuditTrail = new ArrayCollection();
		List<Object[]> lstHistoryData  = em.createNativeQuery("SELECT f.type,f.newvalue,f.adduser,f.updatedate FROM " + sTableName.trim() + " f WHERE f.fieldname=?1 AND f.uuid=?2 ORDER BY f.updatedate DESC")
			.setParameter(1, sFieldName)
			.setParameter(2, sUUID)
			.getResultList();
		
		String lsType;
		String lsNewValue;
		String lsAddUser;
		Date ldUpdateDate;
		int lnCount = 0;
		
		for (Object[] oRec : lstHistoryData){
			lnCount += 1;
						
			lsType = (String)oRec[0];
			lsNewValue = (String)oRec[1];
			lsAddUser = (String)oRec[2];
			ldUpdateDate = (Date)oRec[3];
			
			AuditTrail newRec = new AuditTrail();
			newRec.setType(lsType);
			newRec.setAdduser(lsAddUser);
			newRec.setNewvalue(lsNewValue);
			newRec.setUpdatedate(ldUpdateDate);
			lstAuditTrail.add(newRec);
		}
		
		return lstAuditTrail;
	}
}
