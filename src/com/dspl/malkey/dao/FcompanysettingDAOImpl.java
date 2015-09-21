package com.dspl.malkey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fcompanysetting;
import com.dspl.malkey.domain.Nonyearmonth;
import com.dspl.malkey.domain.Yearmonth;
import com.dspl.malkey.domain.YearmonthPK;
import com.dspl.malkey.util.IdGenarater;

public class FcompanysettingDAOImpl implements FcompanysettingDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional(readOnly=true)	
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Fcompanysetting").getSingleResult());
	}
	
	@Transactional
	@Override
	public void create(Fcompanysetting fcompanysetting) {
		em.persist(fcompanysetting);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Fcompanysetting findById(String csettingscode) {
		return em.find(Fcompanysetting.class, csettingscode);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Fcompanysetting> list(int startIndex, int numItems) {
		return em.createNamedQuery("FcompanysettingListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Fcompanysetting> listAll() {
		return em.createNamedQuery("FcompanysettingListAll").getResultList();
	}
	
	@Transactional
	@Override
	public void removeById(String csettingscode) {
		em.remove(em.find(Fcompanysetting.class, csettingscode));
	}
	
	@Transactional(readOnly=false)
	@Override
	public void update(Fcompanysetting fcompanysetting) {
		em.merge(fcompanysetting);
	}

	
	/* 
	 * @Caller code	
		Calendar addDate = customer.getAddDate();
		String seqNo = fCompanySettingDao.getRefNo("DBN", addDate.get(Calendar.YEAR), addDate.get(Calendar.MONTH));
		*/	
		@Transactional(propagation = Propagation.REQUIRED,rollbackFor=RuntimeException.class)
		@Override
		public String getRefNo(String cSettingsCode, int year, int month) {
			System.out
					.println(" FCompanySettingDaoImpl.getRefNo ");
			Fcompanysetting fCompanySettings = em.find(Fcompanysetting.class,
					cSettingsCode);
			
/*			Added by Sanka 2010.12.03
			This code segment return if fCompanySettings is null then return empty*/
			if(null==fCompanySettings)
			{
				throw new RuntimeException("Invalied cSettingsCode value have provided");
				//return "";
			}
			
			// System.out.println("fCompanySettings.getNType() ===="+fCompanySettings.getNType());
			int seqNo = 0;
			String refNumber = "";
			switch (fCompanySettings.getNtype().intValue()) {
			case 4:
				// Following is the code segment for YearMonth
				// System.out.println("\n\n\n ntype is 1");
				YearmonthPK ympk = new YearmonthPK();
				ympk.setCsettingscode(cSettingsCode);
				ympk.setNmonth(month);
				ympk.setNyear(year);
				// System.out.println("This after set value ympk\n\n");
				// System.out.println("Before find");
				Yearmonth ymFind = em.find(Yearmonth.class, ympk);
				if (ymFind != null) {
					// System.out.println("After find\n\n\n\n");
					seqNo = ymFind.getNNumVal();
					ymFind.setNNumVal(ymFind.getNNumVal() + 1);
					em.merge(ymFind);
				} else {
					ymFind = new Yearmonth();
					ymFind.setId(ympk);
					ymFind.setNNumVal(2);
					em.persist(ymFind);
					seqNo = 1;
					System.out
							.println("ther is not a sequence for the paticular prefix in yearmonth then inserted it");
				}
				refNumber = IdGenarater.getFormatedRefNo(seqNo, year, month,
						fCompanySettings.getCcharval());
				break;

			case 2:
				// Following is the code segment for NonYearMonth
				System.out.println("\n\n\n ntype is 2");
				Nonyearmonth nymFind = em.find(Nonyearmonth.class, cSettingsCode);
				if (null != nymFind) {
					seqNo = nymFind.getNnumval();
					nymFind.setNnumval(nymFind.getNnumval() + 1);
					em.merge(nymFind);
				} else {
					nymFind = new Nonyearmonth();
					nymFind.setNnumval(2);
					nymFind.setCsettingscode(cSettingsCode);
					em.persist(nymFind);
					seqNo = 1;
					System.out
							.println("ther is not a sequence for the paticular prefix in nonyearmonth then inserted it");
				}
				refNumber = IdGenarater.getFormatedRefNo(seqNo, fCompanySettings
						.getCcharval());
				break;
				
			case 6:
				// Following is the code segment for NonYearMonth
				System.out.println("\n\n\n ntype is 6");
				Nonyearmonth nymsFind = em.find(Nonyearmonth.class, cSettingsCode);
				if (null != nymsFind) {
					seqNo = nymsFind.getNnumval();
					nymsFind.setNnumval(nymsFind.getNnumval() + 1);
					em.merge(nymsFind);
				} else {
					nymsFind = new Nonyearmonth();
					nymsFind.setNnumval(2);
					nymsFind.setCsettingscode(cSettingsCode);
					em.persist(nymsFind);
					seqNo = 1;
					System.out
							.println("ther is not a sequence for the paticular prefix in nonyearmonth then inserted it");
				}
				refNumber = IdGenarater.getFormatedRefNo(seqNo, fCompanySettings
						.getCcharval(),9);
				break;

				
			default:
				System.out.println("\n\n\n\n ntype is default");
				break;
			}
			em.flush();
			System.out.println("\n\n\nReferenceNo: " + refNumber);
			return refNumber;
		}
		
}
