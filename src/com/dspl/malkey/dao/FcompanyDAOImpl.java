package com.dspl.malkey.dao;

import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.dspl.malkey.domain.Fcompany;
import com.dspl.malkey.domain.Fcompanytax;
import com.dspl.malkey.service.FileUploadSRV;
import com.dspl.malkey.service.UserInfoSRV;

public class FcompanyDAOImpl implements FcompanyDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Resource(name="fileUploadSRV")
	FileUploadSRV fileUploadSRV;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Transactional(readOnly=true)
	@Override
	public List<Fcompany> List(int startIndex, int numItems) {
		return em.createNamedQuery("FcompanyListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional(readOnly=true)
	@Override
	public List<Fcompany> ListAll() {
		return em.createNamedQuery("FcompanyListAll").getResultList();
	}

	@Transactional(readOnly=true)
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Faccessory").getSingleResult());
	}
	
	@Transactional(readOnly=true)
	@Override
	public Fcompany findByID(String accid) {
		try{
			return em.find(Fcompany.class, accid);
		}catch(Exception e){
			System.out.println("Faccessory findById: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public Boolean create(Fcompany fcompany, List<Fcompanytax> fcompanytaxes) {
		try {
			String curUser=userInfoSRV.getUser();
			String curMachine=userInfoSRV.getMachineName();
			Calendar curDate = Calendar.getInstance();
			curDate=fmaintenanceDAO.resetTime(curDate);
					
			//Persists FCompany
			fcompany.setAdddate(curDate);
			fcompany.setAdduser(curUser);
			fcompany.setAddmach(curMachine);
			em.persist(fcompany);
			em.flush();
			
			for(int a=0;a<fcompanytaxes.size();a++){
				Fcompanytax fcompanytax=fcompanytaxes.get(a);
				fcompanytax.setAdddate(curDate);
				fcompanytax.setAdduser(curUser);
				fcompanytax.setAddmach(curMachine);
				//Persists FCompanyTax
				em.persist(fcompanytax);
				em.flush();
			}
			return true;
		} catch (Exception e) {
			System.out.println("Fcompany create: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	@Override
	public List<Fcompany> listCompanies() {
		try {
			String query ="SELECT companyid,description FROM fcompany ORDER BY companyid";
			return em.createNativeQuery(query, "compList").getResultList();
		} catch (Exception e) {
			System.out.println("Fcompany listCompanies: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public Boolean removeByID(String companyid) {
		try {
			String query="DELETE FROM fcompanytax WHERE companyid='"+ companyid +"'";
			em.createNativeQuery(query).executeUpdate();
			em.flush();
			em.remove(em.find(Fcompany.class, companyid));
			em.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Fcompany removeById: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	@Override
	public Boolean update(Fcompany fcompany, List<Fcompanytax> fcompanytaxes) {
		try {
			String curUser=userInfoSRV.getUser();
			String curMachine=userInfoSRV.getMachineName();
			Calendar curDate = Calendar.getInstance();
			curDate=fmaintenanceDAO.resetTime(curDate);
			
			//Merge FAccessory
			fcompany.setAdddate(curDate);
			fcompany.setAdduser(curUser);
			fcompany.setAddmach(curMachine);
			em.merge(fcompany);
			em.flush();
			
			//Delete Existing Records 
			String query="DELETE FROM fcompanytax WHERE companyid='"+ fcompany.getCompanyid() +"'";
			em.createNativeQuery(query).executeUpdate();
//			em.flush();
			
			for(int a=0;a<fcompanytaxes.size();a++){
				Fcompanytax fcompanytax=fcompanytaxes.get(a);
				fcompanytax.setAdddate(curDate);
				fcompanytax.setAdduser(curUser);
				fcompanytax.setAddmach(curMachine);
				//Persists Fcompanytax
				em.persist(fcompanytax);
				em.flush();
			}
			return true;
		} catch (Exception e) {
			System.out.println("Fcompany create: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}
