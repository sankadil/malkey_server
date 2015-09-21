package com.dspl.malkey.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fpass;
import com.dspl.malkey.service.UserInfoSRV;

import flex.messaging.FlexContext;

public class FpassDAOImpl implements FpassDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Transactional(readOnly=true)
	@Override
	public int count() {
		int i = (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fpass").getSingleResult());
		System.out.println("count :"+i);
		return i;
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Fpass fpass) {
		fpass=setEnvVar(fpass);
		em.persist(fpass);
	}
	
	private Fpass setEnvVar(Fpass fpass){
		Calendar curDate = Calendar.getInstance();
		curDate=fmaintenanceDAO.resetTime(curDate);
		fpass.setAdduser(userInfoSRV.getUser());
		fpass.setAddmach(userInfoSRV.getMachineName());
		fpass.setAdddate(curDate);
		return fpass;
	}

	@Transactional(readOnly=true)
	@Override
	public Fpass findById(String userid) {
		try{
			Fpass fpass=em.find(Fpass.class, userid);
			if(fpass!=null){
				String query="SELECT name FROM femployee WHERE empid='" + fpass.getEmpid() + "'";
				List<Object[]> l = em.createNativeQuery(query).getResultList();
				String empname=l.toString();
				empname=empname.substring(1, (empname.length()-1));
				fpass.setEmpname(empname);
				//Add user into session
				HttpSession session =FlexContext.getHttpRequest().getSession(true);
				session.setMaxInactiveInterval(-1);
				session.setAttribute("user",fpass);
				
			}
			return fpass;
		}catch(Exception e){
			System.out.println("FpassDaoImpl findById: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional(readOnly=true)
	@Override
	public List<Fpass> list(int startIndex, int numItems) {
		return em.createNamedQuery("FpassListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional(readOnly=true)
	@Override
	public List<Fpass> listAll() {
		return em.createNamedQuery("FpassListAll").getResultList();
	}

	@Transactional(readOnly=false)
	@Override
	
	public void removeById(String userid) {
		em.remove(em.find(Fpass.class, userid));

	}

	@Transactional(readOnly=false)
	@Override
	public void update(Fpass fpass) {
		fpass=setEnvVar(fpass);
		em.merge(fpass);
	}

	@Transactional
	@Override
	public List<Fpass> listUsers() {
		try {
			String query="SELECT"; 
			query += " (SELECT name FROM femployee WHERE empid=p.empid) AS [empname],";
			query += " p.username,";
			query += " (SELECT description FROM fusertype WHERE usertypeid=p.usertypeid) AS [usertype]";
			query += " FROM fpass AS p";
			//query += " WHERE ibuilt=0";
			
			List<Object[]> l = em.createNativeQuery(query).getResultList();
			List<Fpass> list = new ArrayList<Fpass>();
			for(Object[] r : l)
			{
				Fpass obj=new Fpass();
				obj.setEmpname((String)r[0]);
				obj.setUsername((String)r[1]);
				obj.setUsertype((String)r[2]);
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			System.out.println("FpassDaoImpl: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
