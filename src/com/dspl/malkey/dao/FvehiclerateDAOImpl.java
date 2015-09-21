package com.dspl.malkey.dao;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fvehiclerate;
import com.dspl.malkey.domain.FvehicleratePK;
import com.dspl.malkey.service.UserInfoSRV;

import flex.messaging.io.ArrayList;

public class FvehiclerateDAOImpl implements FvehiclerateDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;

	@Transactional
	@Override
	public List<Fvehiclerate> List(int startIndex, int numItems) {
		return em.createNamedQuery("FvehiclerateListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fvehiclerate> ListAll() {
		return em.createNamedQuery("FvehiclerateListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fvehiclerate").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fvehiclerate fvehiclerate) {
		em.persist(fvehiclerate);
	}

	@Transactional
	@Override
	public Fvehiclerate findByID(FvehicleratePK fvehicleratePK) {
		return em.find(Fvehiclerate.class, fvehicleratePK);
	}
	
	@Transactional
	@Override
	public List<Fvehiclerate>  findByIDList(List<FvehicleratePK>  lstfvehicleratePK) {
		List<Fvehiclerate> lstTemp=new ArrayList();
		for(FvehicleratePK fvehicleratePK : lstfvehicleratePK)
		{
			lstTemp.add( em.find(Fvehiclerate.class, fvehicleratePK));
		}
		return lstTemp;
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(FvehicleratePK fvehicleratePK) {
		em.remove(em.find(Fvehiclerate.class, fvehicleratePK));
	}

	@Transactional(readOnly=false)
	@Override
	public void udpate(Fvehiclerate fvehiclerate) {
		em.merge(fvehiclerate);
	}

	@Transactional(readOnly=false)
	@Override
	public List<Fvehiclerate> listByVehiModelID(String vehiModelID) {
		return em.createNamedQuery("FvehiclerateListByVehiModelID").setParameter("vehimodelid", vehiModelID).getResultList();
	}

	@Transactional(readOnly=false)
	@Override
	public void udpateList(String sVehiModelID, List<Fvehiclerate> fvehiclerate) {
		String lsAddUser = userInfoSRV.getUser();
		String lsAddMachine = userInfoSRV.getMachineName();
		
		// fVehicleRate - Remove existing records and insert the latest
		em.createNativeQuery("DELETE FROM Fvehiclerate WHERE vehimodelid='"+sVehiModelID+"'").executeUpdate();
		
		// Update the server table with the latest records
		Iterator<Fvehiclerate> newVehiRate = fvehiclerate.iterator();
		while (newVehiRate.hasNext()){
			Fvehiclerate newRecord = (Fvehiclerate)newVehiRate.next();
			FvehicleratePK newRecordPK = newRecord.getId(); 			// Always update with the original Vehicle Model ID to ensure the relational data points the correct parent record
			newRecordPK.setVehimodelid(sVehiModelID);
			newRecord.setId(newRecordPK);
			
			if (newRecord.getAdduser()==null || newRecord.getAdduser().isEmpty())
				newRecord.setAdduser(lsAddUser);
			
			
			if (newRecord.getAddmach()==null || newRecord.getAddmach().isEmpty())						// Only update this field if its empty (i.e. for new records)
				newRecord.setAddmach(lsAddMachine);
			
			if (newRecord.getUuid()==null || newRecord.getUuid().isEmpty())
				newRecord.setUuid(UUID.randomUUID().toString());
			
			em.persist(newRecord);
		}
	}

	@Transactional
	@Override
	public List<Fvehiclerate> getRateList(String vehimodelid) {
		try {
			String query="SELECT vr.vehimodelid,t.description AS clienttype,h.description AS hiretypeid,r.description AS ratetype,vr.standardrate,vr.xsmilerate,vr.allotedkms,vr.xhourrate,vr.allotedhours";
			query += " FROM fvehiclerate AS vr,fclienttype AS t,fhiretype AS h,fratetype AS r";
			query += " WHERE vr.vehimodelid='"+vehimodelid+"' AND";
			query += " vr.clienttype=t.clienttype AND";
			query += " vr.hiretypeid=h.hiretypeid AND";
			query += " vr.ratetype=r.ratetype";
			query += " ORDER BY clienttype,hiretypeid,ratetype,standardrate";
			
			return em.createNativeQuery(query, Fvehiclerate.class).getResultList();
		} catch (Exception e) {
			System.out.println("getRateList: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
