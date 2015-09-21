package com.dspl.malkey.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

import com.businessobjects.report.htmlrender.q;
import com.dspl.malkey.domain.CommMF;
import com.dspl.malkey.domain.Fmasterlist;
import com.dspl.malkey.service.UserInfoSRV;

public class FmasterlistDAOImpl implements FmasterlistDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Transactional
	@Override
	public List<Fmasterlist> ListAll() {
		return em.createNamedQuery("FmasterlistListAll").getResultList();
	}

	@Transactional
	@Override
	public List<CommMF> ListMF(String tableName, String keyColumn) {
		try{
			String Q="SELECT " + keyColumn + ",description,adduser";
			if(tableName.equals("fcolour")==true)
				Q += ",colcode";
			Q += " FROM " + tableName;
			if(tableName.equals("fmainttype")==true)
				Q += " WHERE isdefault=0 ";
			Q += " ORDER BY " + keyColumn;
			System.out.println("Q: " + Q);
			Query query = em.createNativeQuery(Q);
			List<Object[]> l1 = query.getResultList();
			List<CommMF> ac = new ArrayList<CommMF>();
			for(Object[] r1 : l1)
			{
//				System.out.println(r1.length);
//				System.out.println(r1.toString());
				CommMF mf = new CommMF();
				mf.setId((String)r1[0]);
				mf.setDescription((String)r1[1]);
				mf.setAdduser((String)r1[2]);
				if(tableName.equals("fcolour")==true)
					mf.setColcode(Integer.parseInt(r1[3].toString()));
				ac.add(mf);
			}
			return ac;
		}catch(Exception ex){
			System.out.println("ListMF: " + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public Boolean remove(String tableName, String KeyField, String Id) {
		try{
			String Q="DELETE FROM " + tableName + " WHERE " + KeyField + "='" + Id + "'";
			Query query = em.createNativeQuery(Q);
			if(query.executeUpdate()==1)
				return true;
		}catch(Exception ex){
			System.out.println("delete: " + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public Boolean save(String tableName, String KeyField, String Id, String Description, String addFieldName, String addFieldValue) {
		try{
			String AddUser=userInfoSRV.getUser();
			String AddMach=userInfoSRV.getMachineName();
			Calendar Cal = Calendar.getInstance();
			String AddDate= Cal.get(Calendar.YEAR) + "-" + (Cal.get(Calendar.MONTH)+1) + "-" + Cal.get(Calendar.DATE);
			
			String Q="INSERT INTO " + tableName + " ("+ KeyField + ",description,adduser,addmach,adddate";
			if(addFieldName.trim().length()>0)
				Q += "," + addFieldName;
			
			Q += ") VALUES('" + Id + "','" + Description + "','" + AddUser + "','" + AddMach + "','" + AddDate + "'";
			if(addFieldName.trim().length()>0)
				Q += "," + addFieldValue;
			Q += ")";
			Query query = em.createNativeQuery(Q);
			if(query.executeUpdate()==1)
				return true;
		}catch(Exception ex){
			System.out.println("save: " + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public Boolean update(String tableName, String KeyField, String Id, String Description, String addFieldName, String addFieldValue) {
		try{
			String Q="UPDATE " + tableName + " SET description='" + Description + "',adduser='" + userInfoSRV.getUser() + "',addmach='" + userInfoSRV.getMachineName() + "'";
			if(addFieldName.trim().length()>0)
				Q += "," + addFieldName + "=" + addFieldValue;
			Q += " WHERE " + KeyField + "='" + Id + "'";
			Query query = em.createNativeQuery(Q);
			if(query.executeUpdate()==1)
				return true;
		}catch(Exception ex){
			System.out.println("update: " + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public int isAssociated(String tableName, String KeyField, String Id) {
		try {
			String query="SELECT COUNT(*) FROM "+ tableName +" WHERE "+ KeyField +"='"+ Id +"'";
			return (Integer)(em.createNativeQuery(query).getSingleResult());
		} catch (Exception e) {
			System.out.println("isAssociated: " + e.getMessage());
		}
		return -1;
	}

		
}
