package com.dspl.malkey.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fpass;
import com.dspl.malkey.domain.Freshed;

public class FreshedDAOImpl implements FreshedDAO {
	@PersistenceContext
	EntityManager em;

	@Transactional
	@Override
	public int count() {
		int i=(Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Freshed").getSingleResult());
		return i;
	}

	@Transactional
	@Override
	public Boolean create(Freshed freshed) {
		try{
			em.persist(freshed);
			return true;
		}catch(Exception e){
			System.out.println("Freshed create: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public Freshed findByID(String agrno) {
		//return em.find(Freshed.class, agrno);
		
		try {
			em.flush();
			String query="SELECT f.agrno,c.companyid,c.description,d.debcode,d.debname, "+
			" resno = STUFF((SELECT ', ' + r.resno FROM FRESERVATION r  WHERE r.agrno = f.agrno FOR XML PATH(''), TYPE).value('.[1]', 'nvarchar(max)'), 1, 2, '')"+
			"FROM Freshed f,Fcompany c,Fdebtor d	WHERE (d.debcode=f.debcode AND c.companyid=f.companyid AND f.agrno='"+agrno+"')	 ORDER BY f.agrno DESC";
			List<Object[]> lst=em.createNativeQuery(query).getResultList();
			List<Freshed> lstFreshed=new ArrayList<Freshed>();
			for(Object[] r : lst)
			{
				Freshed freshed=new Freshed();
				freshed.setAgrno((String)r[0]);
				freshed.setCompanyid((String)r[1]);
				freshed.setCompany((String)r[2]);
				freshed.setDebcode((String)r[3]);
				freshed.setDebname((String)r[4]);
				freshed.setUuid((String)r[5]);
				lstFreshed.add(freshed);
			}
			lst=null;
			if(lstFreshed.size()>0)
			return lstFreshed.get(0); //em.createNamedQuery("FreshedListAllDescription").getResultList();
			else return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally
		{
			em.flush();
		}
		
	}

	@Transactional
	@Override
	public List<Freshed> list(int startIndex, int numItems) {
		return em.createNamedQuery("FredhedListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Freshed> listAll() {
		return em.createNamedQuery("FreshedListAll").getResultList();
	}
	
	@Transactional
	//@Override
	public List<Freshed> listAll1() {
		return em.createNamedQuery("FreshedListAll").getResultList();
	}
	
	@Transactional
	@Override
	public List<Freshed> listAllDescription() {
		try {
			em.flush();
			String query="SELECT f.agrno,c.companyid,c.description,d.debcode,d.debname, "+
			" resno = STUFF((SELECT ', ' + r.resno FROM FRESERVATION r  WHERE r.agrno = f.agrno FOR XML PATH(''), TYPE).value('.[1]', 'nvarchar(max)'), 1, 2, '')"+
			"FROM Freshed f,Fcompany c,Fdebtor d	WHERE (d.debcode=f.debcode AND c.companyid=f.companyid)	 ORDER BY f.agrno DESC";
			List<Object[]> lst=em.createNativeQuery(query).getResultList();
			List<Freshed> lstFreshed=new ArrayList<Freshed>();
			for(Object[] r : lst)
			{
				Freshed freshed=new Freshed();
				freshed.setAgrno((String)r[0]);
				freshed.setCompanyid((String)r[1]);
				freshed.setCompany((String)r[2]);
				freshed.setDebcode((String)r[3]);
				freshed.setDebname((String)r[4]);
				freshed.setUuid((String)r[5]);
				lstFreshed.add(freshed);
			}
			lst=null;
			return lstFreshed; //em.createNamedQuery("FreshedListAllDescription").getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally
		{
			em.flush();
		}
		
		
		/*

SELECT *,
 resno = STUFF((SELECT ', ' + resno FROM FRESERVATION   WHERE agrno = x.agrno FOR XML PATH(''), TYPE).value('.[1]', 'nvarchar(max)'), 1, 2, '')
FROM freshed AS x


SELECT agrno,
 resno = STUFF((SELECT ', ' + resno FROM FRESERVATION   WHERE agrno = x.agrno FOR XML PATH(''), TYPE).value('.[1]', 'nvarchar(max)'), 1, 2, '')
FROM freshed AS x
GROUP BY agrno;

result formate
-------------------------------------------------------------------------------
agrno	debcode	companyid	adduser	addmach	adddate	recordid	uuid	resno

*/
	}
	@Transactional
	@Override
	public List<Freshed> feelLucky(String searchText) {
		try {
			em.flush();
			
			String query="SELECT distinct f.agrno,c.companyid,c.description,d.debcode,d.debname, "+
			" resno = STUFF((SELECT ', ' + r.resno FROM FRESERVATION r  WHERE " +
			"r.agrno = f.agrno FOR XML PATH(''), TYPE).value('.[1]', 'nvarchar(max)'), 1, 2, '')"+
			"FROM Freshed f,Fcompany c,Fdebtor d,Freservation r	WHERE " +
			"(d.debcode=f.debcode AND c.companyid=f.companyid AND r.agrno=f.agrno AND " +
			"(r.resno like '%"+searchText+"%' or r.agrno like '%"+searchText+"%' or r.debcode like '%"+searchText+"%' or d.debname like '%"+searchText+"%') )	" +
					" ORDER BY f.agrno DESC";
			
			System.out.println("Query feelLucky :"+query);
			//String query="select * from freshed where agrno in(select agrno from freservation where resno like '%"+searchText+"%' or agrno like '%"+searchText+"%' ) order by agrno";
			List<Object[]> lst=em.createNativeQuery(query).getResultList();
			List<Freshed> lstFreshed=new ArrayList<Freshed>();
			for(Object[] r : lst)
			{
				Freshed freshed=new Freshed();
				freshed.setAgrno((String)r[0]);
				freshed.setCompanyid((String)r[1]);
				freshed.setCompany((String)r[2]);
				freshed.setDebcode((String)r[3]);
				freshed.setDebname((String)r[4]);
				freshed.setUuid((String)r[5]);
				lstFreshed.add(freshed);
			}
			lst=null;
			return lstFreshed; //em.createNamedQuery("FreshedListAllDescription").getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally
		{
			em.flush();
		}
	}
	
	@Transactional
	@Override
	public List<Freshed> listAllDescriptionPage(int startIndex, int numItems) {
		try {
			em.flush();
			String query="SELECT f.agrno,c.companyid,c.description,d.debcode,d.debname, "+
			" resno = STUFF((SELECT ', ' + r.resno FROM FRESERVATION r  WHERE r.agrno = f.agrno FOR XML PATH(''), TYPE).value('.[1]', 'nvarchar(max)'), 1, 2, '')"+
			"FROM Freshed f,Fcompany c,Fdebtor d	WHERE (d.debcode=f.debcode AND c.companyid=f.companyid)	 ORDER BY f.agrno DESC";
			List<Object[]> lst=em.createNativeQuery(query).setFirstResult(startIndex).setMaxResults(numItems).getResultList();
			List<Freshed> lstFreshed=new ArrayList<Freshed>();
			for(Object[] r : lst)
			{
				Freshed freshed=new Freshed();
				freshed.setAgrno((String)r[0]);
				freshed.setCompanyid((String)r[1]);
				freshed.setCompany((String)r[2]);
				freshed.setDebcode((String)r[3]);
				freshed.setDebname((String)r[4]);
				freshed.setUuid((String)r[5]);
				lstFreshed.add(freshed);
			}
			lst=null;
			return lstFreshed; //em.createNamedQuery("FreshedListAllDescription").getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally
		{
			em.flush();
		}
		
	}

	@Transactional
	@Override
	public Boolean removeByID(String agrno) {
		try{
			em.remove(em.find(Fpass.class, agrno));
			return true;
		}catch(Exception e){
			System.out.println("Freshed removeById: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public Boolean update(Freshed freshed) {
		try{
			em.merge(freshed);
			return true;
		}catch(Exception e){
			System.out.println("Freshed update: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
