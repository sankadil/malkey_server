package com.dspl.malkey.dao;
 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import com.dspl.malkey.domain.Faccessrule;
import flex.messaging.io.ArrayCollection;
import flex.messaging.io.amf.ASObject;

public class FaccessruleDAOImpl implements FaccessruleDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional(readOnly=true)
	@Override
	public List<Faccessrule> findByUserTypeId(String UserTypeId) {
		return em.createNamedQuery("farFindByUserTypeId").setParameter("uti", UserTypeId).getResultList();
	}

	@Transactional(readOnly=false)
	@Override
	public Boolean insert(String UserTypeId,List<Faccessrule> faccessrule) 
	{
		try 
		{
			int i = em.createNativeQuery("DELETE FROM faccessrules WHERE usertypeid='" + UserTypeId + "'").executeUpdate();
			em.flush();
			for(int a=0;a<faccessrule.size();a++)
			{
				Faccessrule far = faccessrule.get(a);
				em.persist(far);
				em.flush();
			}
			return true;
		}
		catch (Exception e) 
		{
			System.out.println("Faccessrule Insert: " + e.getMessage());
			return false;
		}
	}

	@Transactional
	@Override
	public ArrayCollection getFunctionList(String UserTypeId, String TransCode) {
		try {
			String query="SELECT funccode FROM faccessrules WHERE usertypeid='"+UserTypeId+"' AND transcode='"+TransCode+"' ORDER BY funccode";
			Query q = em.createNativeQuery(query);
			List<Object[]> l = q.getResultList();
			ArrayCollection ac = new ArrayCollection();
			for(Object r : l)
			{
				ASObject aso = new ASObject();
				aso.put("funccode",r.toString());
				ac.add(aso);
			}
			return ac;
		} catch (Exception e) {
			System.out.println("FaccessruleDaoImpl getFunctionList: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
