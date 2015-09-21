package com.dspl.malkey.dao;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.dspl.malkey.domain.Ftaxdet;
import com.dspl.malkey.domain.FtaxdetPK;
import com.dspl.malkey.domain.Ftaxhed;

import flex.messaging.io.ArrayCollection;
import flex.messaging.io.amf.ASObject;

public class FtaxhedDAOImpl implements FtaxhedDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional(readOnly=true)	
	@Override
	public int count() {
		int i=(Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Ftaxhed").getSingleResult());
		return i;
	}

	@Transactional @Override
	public void create(Ftaxhed ftaxhed) {
		em.persist(ftaxhed);

	}

	@Transactional @Override
	public Ftaxhed findById(String taxcomcode) {
		return em.find(Ftaxhed.class, taxcomcode);
	}

	@Transactional @Override
	public List<Ftaxhed> list(int startIndex, int numItems) {
		return em.createNamedQuery("FtaxhedListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional @Override
	public List<Ftaxhed> listAll() {
		return em.createNamedQuery("FtaxhedListAll").getResultList();
	}

	@Transactional @Override
	public void removeById(String taxcomcode) {
		em.remove(em.find(Ftaxhed.class, taxcomcode));

	}
	
	

	@Transactional @Override
	public void update(Ftaxhed ftaxhed) {
		em.merge(ftaxhed);
	}

	@Transactional
	@Override
	public void deleterec(String taxcomcode) {

			System.out.println("--------------deleteFtaxhed--------------------");
			em.createQuery("DELETE FROM Ftaxdet e " +
				"WHERE e.id.taxcomcode = ?1")
			.setParameter(1, taxcomcode)
			.executeUpdate();
			em.remove(em.find(Ftaxhed.class, taxcomcode));
			em.flush();
			
		}
	@Transactional
	@Override
	public void edit(Ftaxhed ftaxhed, List<Ftaxdet> listftaxdet) {
		em.merge(ftaxhed);
		for (Ftaxdet ftaxdet : listftaxdet) {
			
		
		Ftaxdet temp1=em.find(Ftaxdet.class, ftaxdet.getId());
		if(temp1==null)
		{
			//System.out.println("--------------	update ftax1	--------------------");
			em.persist(ftaxdet);
			em.flush();
			System.out.println("--------------	update ftaxdet2	--------------------");
			
		
		}else
		{
		System.out.println("--------------	update ftaxdet3	--------------------");
		temp1.setTaxrate(ftaxdet.getTaxrate());
		temp1.setTaxseq(ftaxdet.getTaxseq());
		temp1.setId(ftaxdet.getId());

		em.merge(temp1);
		System.out.println("--------------	update ftaxdet5	--------------------");
		em.flush();
		//System.out.println("--------------	update ftaxdet4	--------------------");
		}
		
		}
	}

	@Transactional
	@Override
	public void insert(Ftaxhed ftaxhed, ArrayCollection listftaxdet) 
	{
		try
		{
			System.out.println("--------------	create ftaxhed	--------------------");
			em.persist(ftaxhed);
			for(int a=0;a<listftaxdet.size();a++)
			{
				ASObject aso = (ASObject)listftaxdet.get(a);
				FtaxdetPK ftaxdetPK = new FtaxdetPK();
				Ftaxdet ftaxdet = new Ftaxdet();
				
				ftaxdetPK.setTaxcomcode(aso.get("taxcomcode").toString());
				ftaxdetPK.setTaxcode(aso.get("taxcode").toString());
				
				ftaxdet.setId(ftaxdetPK);
				ftaxdet.setTaxrate(BigDecimal.valueOf(Double.valueOf(aso.get("taxrate").toString())));
				ftaxdet.setTaxseq(BigDecimal.valueOf(Double.valueOf(aso.get("taxseq").toString())));
				ftaxdet.setTaxmode(BigDecimal.valueOf(Double.valueOf(aso.get("taxmode").toString())));
				
				em.persist(ftaxdet);
			}
//			for (Ftaxdet ftaxdet : listftaxdet) {
//				System.out.println("--------------	create ftaxdet	--------------------");
//				em.persist(ftaxdet);	
//			}	
		}
		catch(Exception ex)
		{
			System.out.println("insert: " + ex.getMessage());
		}
	}
}


