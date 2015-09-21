package com.dspl.malkey.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.CommMF;
import com.dspl.malkey.domain.Fcompanytax;
import com.dspl.malkey.domain.FcompanytaxPK;
import com.dspl.malkey.domain.Fpass;
import com.dspl.malkey.service.UserInfoSRV;

public class FcompanytaxDAOImpl implements FcompanytaxDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Transactional
	@Override
	public List<Fcompanytax> ListAll() {
		try {
			String query="SELECT";
			query += " ct.companyid,c.description AS [company],";
			query += " ct.hiretypeid,h.description AS [hiretype],";
			query += " ct.taxcomcode,t.taxcomname AS [taxcombination],";
			query += " ct.adduser";
			query += " FROM ";
			query += " fcompanytax AS ct,";
			query += " fhiretype AS h,";
			query += " fcompany AS c,";
			query += " ftaxhed AS t";
			query += " WHERE";
			query += " ct.companyid=c.companyid AND";
			query += " ct.hiretypeid=h.hiretypeid AND";
			query += " ct.taxcomcode=t.taxcomcode";
			query += " ORDER BY companyid";
			
			List<Object[]> l = em.createNativeQuery(query).getResultList();
			
			List<Fcompanytax> ac = new ArrayList<Fcompanytax>();
			Fcompanytax mf;
			FcompanytaxPK pk;
			
			String companyid;
			String company;
			String hiretypeid;
			String hiretype;
			String taxcomcode;
			String taxcombination;
			String adduser;
			
			for(Object[] r : l){
				companyid=(String)r[0];
				company=(String)r[1];
				hiretypeid=(String)r[2];
				hiretype=(String)r[3];
				taxcomcode=(String)r[4];
				taxcombination=(String)r[5];
				adduser=(String)r[6];
				
				mf=new Fcompanytax();
				pk=new FcompanytaxPK();				
				
				pk.setCompanyid(companyid);
				pk.setHiretypeid(hiretypeid);
				mf.setId(pk);
				mf.setCompany(company);
				mf.setHiretype(hiretype);
				mf.setTaxcomcode(taxcomcode);
				mf.setTaxcombination(taxcombination);
				mf.setAdduser(adduser);
				
				ac.add(mf);
			}
			return ac;
		} catch (Exception e) {
			System.out.println("FcompanytaxDAO ListAll: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public Boolean update(List<Fcompanytax> fcompanytaxes) {
		try {
			String query="DELETE FROM fcompanytax";
			em.createNativeQuery(query).executeUpdate();
			em.flush();
			
			for(int a=0;a<fcompanytaxes.size();a++){
				Fcompanytax mf=fcompanytaxes.get(a);
				mf=(Fcompanytax)setEnvVar(mf);
				em.persist(mf);
				em.flush();
			}
			return true;
		} catch (Exception e) {
			System.out.println("FcompanytaxDAO update: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	private Object setEnvVar(Fcompanytax obj){
		Calendar curDate = Calendar.getInstance();
		curDate=fmaintenanceDAO.resetTime(curDate);
		obj.setAdduser(userInfoSRV.getUser());
		obj.setAddmach(userInfoSRV.getMachineName());
		obj.setAdddate(curDate);
		return obj;
	}

	@Transactional
	@Override
	public String getTaxComCode(String companyid, String hiretypeid) {
//		try {
			String Query="SELECT taxcomcode FROM fcompanytax WHERE companyid='"+companyid+"' AND hiretypeid='"+hiretypeid+"'";
			return (String)em.createNativeQuery(Query).getSingleResult();
//		} catch (Exception e) {
//			System.out.println("getTaxComCode: "+ e.getMessage());
//			e.printStackTrace();
//		}
//		return "";
	}

}
