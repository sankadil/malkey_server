package com.dspl.malkey.dao;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Farfdtf;
import com.dspl.malkey.domain.FarfdtfPK;
import com.dspl.malkey.domain.Fdtftax;
import com.dspl.malkey.domain.FdtftaxPK;
import com.dspl.malkey.domain.Finvdet;
import com.dspl.malkey.domain.FinvdetPK;
import com.dspl.malkey.domain.Finvhed;
import com.dspl.malkey.domain.Fresaccs;
import com.dspl.malkey.domain.FresaccsPK;
import com.dspl.malkey.domain.Fresaddcharge;
import com.dspl.malkey.domain.FresaddchargePK;
import com.dspl.malkey.domain.Fresdriver;
import com.dspl.malkey.domain.Freservation;
import com.dspl.malkey.domain.Freshed;
import com.dspl.malkey.domain.Fresothsrv;
import com.dspl.malkey.domain.FresothsrvPK;
import com.dspl.malkey.domain.Ftaxdet;
import com.dspl.malkey.domain.FtaxdetPK;
import com.dspl.malkey.report.FarfdtfTMP;
import com.dspl.malkey.report.FinvdetRPT;
import com.dspl.malkey.service.UserInfoSRV;
import com.ibm.icu.text.DateFormat;

import flex.messaging.io.ArrayCollection;
import flex.messaging.io.amf.ASObject;

public class FarfdtfDAOImpl implements FarfdtfDAO {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fresdriverDAO")
	FresdriverDAO fresdriverDAO;
	
	@Resource(name="fcompanysettingDAO")
	FcompanysettingDAO fcompanysettingDAO;
	
	@Resource(name="fmaintenanceDAO")
	FmaintenanceDAO fmaintenanceDAO;
	
	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Transactional
	@Override
	public List<Freshed> getAgreementList() {
		try {
			String query="SELECT h.agrno,h.debcode,d.debname,h.companyid,c.description [company] FROM freshed AS h,fdebtor AS d,fcompany AS c"; 
			query += " WHERE";
			query += " h.debcode=d.debcode AND";
			query += " h.companyid=c.companyid AND"; 
			//query += " h.agrno IN (SELECT agrno FROM freservation WHERE agrno=h.agrno AND cohirestsid='COMPLETED' AND resno NOT IN (SELECT DISTINCT resno FROM farfdtf))";
			query += " h.agrno IN (SELECT agrno FROM freservation WHERE agrno=h.agrno AND cohirestsid='COMPLETED')";
			query += " ORDER BY h.agrno,h.debcode";
			
			System.out.println("\r getAgreementList: " + query + "\r");
			
			List<Object[]> l = em.createNativeQuery(query).getResultList();
			
			List<Freshed> ac = new ArrayList<Freshed>();
			Freshed mf;
			
			String agrno;
			String debcode;
			String debname;
			String companyid;
			String company;
			
			for(Object[] r : l){
				agrno=(String)r[0];
				debcode=(String)r[1];
				debname=(String)r[2];
				companyid=(String)r[3];
				company=(String)r[4];

				mf=new Freshed();
				mf.setAgrno(agrno);
				mf.setDebcode(debcode);
				mf.setDebname(debname);
				mf.setCompanyid(companyid);
				mf.setCompany(company);
				ac.add(mf);
			}
			return ac;

		} catch (Exception e) {
			System.out.println("FarfdtfDAOImpl getAgreementList: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public List<Freservation> getSubAgreementList(String agrno) {
		try {
			em.flush();
			agrno=agrno.trim();
			if(agrno.length()<=0)
				return null;
			
			String query="SELECT r.resno,v.regno,r.dout,r.din,t.description [hiretype]";
			query += " FROM"; 
			query += " freservation AS r,";
			query += " fhiretype AS t,";
			query += " fresvehicle AS v";
			query += " WHERE r.hiretypeid=t.hiretypeid AND";
			query += " r.resno=v.resno AND v.priority=1 AND";
			//query += " r.resno IN (SELECT resno FROM freservation WHERE agrno='"+agrno+"' AND cohirestsid='COMPLETED' AND resno NOT IN (SELECT DISTINCT resno FROM farfdtf))";
			query += " r.resno IN (SELECT resno FROM freservation WHERE agrno='"+agrno+"' AND cohirestsid='COMPLETED')";
			query += " ORDER BY r.resno";
			
			System.out.println("\r getSubAgreementList: " + query + "\r");
			
			List<Object[]> l = em.createNativeQuery(query).getResultList();
			
			List<Freservation> ac = new ArrayList<Freservation>();
			Freservation mf;
			
			String resno;
			String regno;
			Timestamp dout;
			Timestamp din;
			String hiretype;
			
			for(Object[] r : l){
				resno=(String)r[0];
				regno=(String)r[1];
				dout=(Timestamp)r[2];
				din=(Timestamp)r[3];
				hiretype=(String)r[4];
				
				mf=new Freservation();
				mf.setSelected(false);
				mf.setResno(resno);
				mf.setQuoteno(regno);
				
				Calendar calOut=Calendar.getInstance();
				java.util.Date dtOut=new java.util.Date(dout.getTime());
				calOut.setTime(dtOut);
				mf.setDout(calOut);
				
				System.out.println("resno: " + resno +  "     dtOut: " + dtOut.toString()  + "    calOut: " + calOut.toString());
				
				Calendar calIn=Calendar.getInstance();
				java.util.Date dtIn=new java.util.Date(din.getTime());
				calIn.setTime(dtIn);
				mf.setDin(calIn);
				
				mf.setHiretypeid(hiretype);
				ac.add(mf);
			}
			return ac;
			
		} catch (Exception e) {
			System.out.println("FarfdtfDAOImpl getSubAgreementList: " + e.getMessage());
			e.printStackTrace();
		}
		finally{em.flush();}
		return null;
	}
	
	@Transactional
	@Override
	public List<Freservation> getSubAgreementList2(String agrno) {
		try {
			em.flush();
			agrno=agrno.trim();
			if(agrno.length()<=0)
				return null;
			
			String query="SELECT r.resno,v.regno,r.dout,r.din,t.description [hiretype]";
			query += " FROM"; 
			query += " freservation AS r,";
			query += " fhiretype AS t,";
			query += " fresvehicle AS v";
			query += " WHERE r.hiretypeid=t.hiretypeid AND";
			query += " r.resno=v.resno AND v.priority=1 AND";
			//query += " r.resno IN (SELECT resno FROM freservation WHERE agrno='"+agrno+"' AND cohirestsid='COMPLETED' AND resno NOT IN (SELECT DISTINCT resno FROM farfdtf))";
			query += " r.resno IN (SELECT resno FROM freservation WHERE agrno='"+agrno+"' AND cohirestsid='CHECKOUT' AND ratetype='LONGTERM')";
			query += " ORDER BY r.resno";
			
			System.out.println("\r getSubAgreementList: " + query + "\r");
			
			List<Object[]> l = em.createNativeQuery(query).getResultList();
			
			List<Freservation> ac = new ArrayList<Freservation>();
			Freservation mf;
			
			String resno;
			String regno;
			Timestamp dout;
			Timestamp din;
			String hiretype;
			
			for(Object[] r : l){
				resno=(String)r[0];
				regno=(String)r[1];
				dout=(Timestamp)r[2];
				din=(Timestamp)r[3];
				hiretype=(String)r[4];
				
				mf=new Freservation();
				mf.setSelected(false);
				mf.setResno(resno);
				mf.setQuoteno(regno);
				
				Calendar calOut=Calendar.getInstance();
				java.util.Date dtOut=new java.util.Date(dout.getTime());
				calOut.setTime(dtOut);
				mf.setDout(calOut);
				
				System.out.println("resno: " + resno +  "     dtOut: " + dtOut.toString()  + "    calOut: " + calOut.toString());
				
				Calendar calIn=Calendar.getInstance();
				java.util.Date dtIn=new java.util.Date(din.getTime());
				calIn.setTime(dtIn);
				mf.setDin(calIn);
				
				mf.setHiretypeid(hiretype);
				ac.add(mf);
			}
			return ac;
			
		} catch (Exception e) {
			System.out.println("FarfdtfDAOImpl getSubAgreementList: " + e.getMessage());
			e.printStackTrace();
		}
		finally{em.flush();}
		return null;
	}

	int seqCnt;
	int hedSeqCnt;
	
	double roundTwoDecimals(double d){
    	//DecimalFormat twoDForm = new DecimalFormat("#.##");
    	//return Double.valueOf(twoDForm.format(d));
		return new Double(new BigDecimal(String.valueOf(d)).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private java.math.BigDecimal roundTwoDecimals(java.math.BigDecimal d){
		try{
	    	//DecimalFormat twoDForm = new DecimalFormat("#.##");
	    	//return java.math.BigDecimal.valueOf(Double.valueOf(twoDForm.format(d)));
	    	return new BigDecimal(String.valueOf(d)).setScale(2, BigDecimal.ROUND_HALF_UP);
	    	
		}catch(Exception e){
			System.out.println("roundTwoDecimals: " + e.getMessage());
		}
		return null;
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private java.math.BigDecimal roundNoDecimals(java.math.BigDecimal d){
		try{
			//DecimalFormat twoDForm = new DecimalFormat("#.##");
			//return java.math.BigDecimal.valueOf(Double.valueOf(twoDForm.format(d)));
			return new BigDecimal(String.valueOf(d)).setScale(0, BigDecimal.ROUND_HALF_UP);
			
		}catch(Exception e){
			System.out.println("roundTwoDecimals: " + e.getMessage());
		}
		return null;
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private java.math.BigDecimal roundFiveDecimals(java.math.BigDecimal d){
		try{
	    	//DecimalFormat twoDForm = new DecimalFormat("#.##");
	    	//return java.math.BigDecimal.valueOf(Double.valueOf(twoDForm.format(d)));
	    	return new BigDecimal(String.valueOf(d)).setScale(5, BigDecimal.ROUND_HALF_UP);
	    	
		}catch(Exception e){
			System.out.println("roundTwoDecimals: " + e.getMessage());
		}
		return null;
	}
	
//	@Transactional(propagation=Propagation.MANDATORY)
//	private ASObject getSecRptDateDet(Calendar dt,ArrayList<ASObject> rptDateList){
//		String dtStr=dt.get(Calendar.YEAR)+"."+(dt.get(Calendar.MONTH)+1)+"."+dt.get(Calendar.DATE);
//		Calendar tmpDtCal=Calendar.getInstance();
//		String tmpDtStr="";
//		for(int a=0;a<rptDateList.size();a++){
//			ASObject aso=rptDateList.get(a);
//			tmpDtCal=(Calendar)aso.get("date");
//			tmpDtStr=tmpDtCal.get(Calendar.YEAR)+"."+(tmpDtCal.get(Calendar.MONTH)+1)+"."+tmpDtCal.get(Calendar.DATE);
//			if(dtStr.equals(tmpDtStr)==true){
//				//System.out.println("dtStr: " + dtStr + "    tmpDtStr: " + tmpDtStr);
//				return aso;
//			}
//		}
//		return null;
//	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private ASObject getSecRptDateDet(String dtStr,ArrayList<ASObject> rptDateList){
		Calendar tmpDtCal=Calendar.getInstance();
		String tmpDtStr="";
		for(int a=0;a<rptDateList.size();a++){
			ASObject aso=rptDateList.get(a);
			tmpDtCal=(Calendar)aso.get("date");
			tmpDtStr=tmpDtCal.get(Calendar.YEAR)+"."+(tmpDtCal.get(Calendar.MONTH)+1)+"."+tmpDtCal.get(Calendar.DATE);
			if(dtStr.equals(tmpDtStr)==true){
				//System.out.println("dtStr: " + dtStr + "    tmpDtStr: " + tmpDtStr);
				return aso;
			}
		}
		return null;
	}
	
	private String formatRateDuration(String ratetype){
		ratetype=ratetype.trim();
		if(ratetype.equals("DAILY")==true)
			ratetype="Per Day";
		else if(ratetype.equals("WEEKLY")==true)
			ratetype="Per Week";
		if(ratetype.equals("MONTHLY")==true)
			ratetype="Per Month";
		if(ratetype.equals("LONGTERM")==true)
			ratetype="Per Year";
		return ratetype;
	}
	
/*	@Transactional
	//@Override
	public ArrayCollection genInvoice3(String resno) {
		
		
		Freshed freshed=(Freshed)(em.createQuery("select f from Freshed f where f.agrno in (select r.agrno from Freservation r where r.resno='RS/1104/00001')").getSingleResult());
		System.out.println("freshed resno="+resno);
		System.out.println("freshed="+freshed.getAgrno());
		ASObject aso=new ASObject();
		aso.put("resno", resno);
		ArrayCollection subAgrList=new ArrayCollection();
		subAgrList.add(aso);
		ArrayCollection ac=genInvoice2(freshed, subAgrList);
		return ac;
	}*/
	
	/**
	 * 
	 * Following code add  "+ VAT 11.00%" portion for description in following description part
	 * description=Mercedes Benz BENZ C180 [Rs.19500.00/- Per Day - 10.0000%  Discount + VAT 11.00%]
	 * 
	 * @param taxdets
	 * @param des
	 * @return
	 */
	private String setTaxDetails(List<Ftaxdet> taxdets,String des){
		for(int b=0;b<taxdets.size();b++){//Tax Details
			des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
		}
		return des;
	}
	
	/***
	 * 
	 * function returntrue if vattype is S or T
	 * select vattyp from fdebtor
	 * @param vattyp
	 * @return
	 */
	private boolean isTaxInvoice(String vattyp)
	{
		if( vattyp.trim().equalsIgnoreCase("T") || vattyp.trim().equalsIgnoreCase("S"))
		{
			return true;
		}
		return false;
	}
	
	
		@Transactional
		@Override
		public ArrayCollection genInvoice(Freshed freshed, ArrayCollection subAgrList) {
		try {
			em.flush();
			String strSubAgrList="";
			ASObject aso;
			for(int a=0;a<subAgrList.size();a++) {
				aso=(ASObject)subAgrList.get(a);
				strSubAgrList+="'"+aso.get("resno").toString()+"'";
				if(a<(subAgrList.size()-1))
					strSubAgrList+=",";
			}

			String query="SELECT r.*,";
			//query += " (SELECT taxcomcode FROM fcompanytax WHERE companyid='"+freshed.getCompanyid()+"' AND hiretypeid=r.hiretypeid) AS taxcomcode,";
			query += " (RTRIM((SELECT description FROM fvehiclemake WHERE vehimakeid=(SELECT vehimakeid FROM fvehicle WHERE regno=r.regno))) + ' ' +";
			query += " RTRIM((SELECT description FROM fvehiclemodel WHERE vehimodelid=(SELECT vehimodelid FROM fvehicle WHERE regno=r.regno) AND vehimakeid=(SELECT vehimakeid FROM fvehicle WHERE regno=r.regno)))) AS vehicle";
			query += " FROM"; 
			query += " (SELECT"; 
			query += " r.resno,";
			query += " r.taxcomcode,";
			query += " rv.regno,";
			query += " vr.ratetype,vr.rate,vr.discount,vr.xsmilerate,ISNULL(r.discount_xmile,0) AS discount_xmile,vr.allotedkms,ISNULL(vr.xhourrate,0) AS xhourrate,ISNULL(vr.allotedhours,0) AS allotedhours,ISNULL(vr.discountxhourrate,0) AS discountxhourrate,"; //Vehicle Details 
			//query += " dr.ratetype AS drratetype,dr.discount AS drdiscount,dr.nightoutrate AS drnightoutrate,dr.otrate AS drotrate,"; //Driver Details 
			//query += " dr.ratetype AS drratetype,dr.nightoutrate AS drnightoutrate,dr.discount_nightout AS drdiscount_nightout,dr.otrate AS drotrate,dr.discount_detention AS drdiscount_detention,"; //Driver Details
			query += " ISNULL(xhours,0) AS xhours,ISNULL(xhoursamt,0) AS xhoursamt,ISNULL(discount_xhoursamt,0) AS discount_xhoursamt,";
			query += " ISNULL(dr.ratetype,'') AS drratetype,";
			query += " ISNULL(dr.nightoutrate,0) AS drnightoutrate,";
			query += " ISNULL(dr.discount_nightout,0) AS drdiscount_nightout,";
			query += " ISNULL(dr.otrate,0) AS drotrate,";
			query += " ISNULL(dr.discount_detention,0) AS drdiscount_detention,"; 
			query += " r.hiretypeid,";
			query += " (SELECT description FROM fhiretype WHERE hiretypeid=r.hiretypeid) AS hiretype,";
			query += " r.dout,r.din,r.noofday,r.chargdays,r.deposit,r.advance,r.comileage,r.cimileage,r.cixsmileage,r.cixsmileagers,r.cidetenhrs,r.cidetenhrsrs,r.cinightout,r.cinightoutrs,r.ciother,ISNULL(r.othernaration,'') AS othernaration,r.cifueldiff,r.cidamagers,vr.standardrate [vrsrate], fdeb.vattyp [vattyp]"; 
			query += " FROM freservation AS r LEFT OUTER JOIN";
			query += " fresvehicle AS rv ON r.resno=rv.resno AND rv.priority=1";
			query += " LEFT OUTER JOIN fresvehiclerate AS vr ON r.resno=vr.resno";
			query += " LEFT OUTER JOIN fresdriverrate AS dr ON r.resno=dr.resno";
			query += " LEFT OUTER JOIN fdebtor AS fdeb ON r.debcode=fdeb.debcode";
			query += " WHERE r.resno IN("+strSubAgrList+")) AS r";
			query += " ORDER BY r.resno";
			
			System.out.println("query: " + query);
			
			List<FarfdtfTMP> list=em.createNativeQuery(query, "farfdtfTMPSQL").getResultList();

			List<Farfdtf> farfdtfs=new ArrayList<Farfdtf>();
			Farfdtf farfdtf;
			FarfdtfPK pk;
			List<Ftaxdet> taxdets; 
			
			ArrayCollection invDet=new ArrayCollection();
			ASObject asoObj;
			String des;
			seqCnt=1;
			hedSeqCnt=1;
			
			for(int a=0;a<list.size();a++){
				
				if (a==1)
				{
					System.out.println(a);
				}
				
				FarfdtfTMP tmp=list.get(a);
				taxdets=loadTaxDet(tmp.getTaxcomcode());
				
				//Get Additional Charges
				List<Fresaddcharge> addChargeList=getAdditionalCharges(tmp.getResno());
				String addChargeDes="";
				Fresaddcharge fresaddcharge;
				for(int s=0;s<addChargeList.size();s++){
					fresaddcharge=addChargeList.get(s);
					addChargeDes+=" + "+fresaddcharge.getAddcharge()+" "+fresaddcharge.getPercentage()+"%";
				}
				
				//VEHICLE RATE
				asoObj=new ASObject(); 
				asoObj=setHedVal(tmp, asoObj); //Set HedVal 
				
				//Per Day Discount Amount
				java.math.BigDecimal pdisamt=calcDisAmt(tmp.getRate(), tmp.getDiscount());
				//Per Day Before Tax Amount (Rate - Discount)
				//java.math.BigDecimal pbtamt=roundTwoDecimals(tmp.getRate().subtract(pdisamt));
				java.math.BigDecimal pbtamt=tmp.getRate().subtract(pdisamt);
				//Calculate Additional Charges
				//pbtamt=roundTwoDecimals(calcAddCharges(addChargeList, pbtamt));
				pbtamt=calcAddCharges(addChargeList, pbtamt);
//				BigDecimal totalAddCharge=new BigDecimal(0);
//				for(int s=0;s<addChargeList.size();s++){
//					fresaddcharge=addChargeList.get(s);
//					totalAddCharge=roundTwoDecimals(totalAddCharge.add(roundTwoDecimals(pbtamt.multiply(fresaddcharge.getPercentage()).divide(new BigDecimal(100)))));
//				}
//				pbtamt=roundTwoDecimals(pbtamt.add(totalAddCharge));
				//Total Before Tax Amount (pbtamt X km or pbtamt X days)
				java.math.BigDecimal tbamt;
				java.math.BigDecimal totalRental=pbtamt.multiply(new java.math.BigDecimal(tmp.getChargdays()));
				System.out.println("rate: " + tmp.getRate() + "   disper: " + tmp.getDiscount() + "   pdisamt: " + pdisamt + "    pbtamt: " + pbtamt + "   totalRental: " + totalRental);
								
				des=tmp.getVehicle()+"\r";
				//des+="[Rs."+ tmp.getRate() +"/- Per Day";
				des+="[Rs."+ tmp.getVrsrate() +"/- " + formatRateDuration(tmp.getRatetype());
				if(tmp.getDiscount().compareTo(new java.math.BigDecimal(0))==0){ //Discount Not Applicable 
					
				}else if(tmp.getDiscount().compareTo(new java.math.BigDecimal(0))>0){ //Discount Applicable 
					des+=" - "+tmp.getDiscount()+"% Discount";
				}
				des+=addChargeDes; //Additional Charges

//SELECT td.taxcode,t.taxname,t.taxper,td.taxrate,td.taxseq FROM 
//ftaxdet AS td,ftax AS t WHERE t.taxcode=td.taxcode AND td.taxcomcode='VATNBT' ORDER BY td.taxseq	
	
//				NBT       	NBT  3.00	2.2400000000	1
//				VAT       	VAT  12.00	12.0000000000	2
				
				//following code add  "+ VAT 11.00%" portion for description in following description part
				//description=Mercedes Benz BENZ C180 [Rs.19500.00/- Per Day - 10.0000%  Discount + VAT 11.00%]
				//Following code written by sanka 2015.09.14
				if(!isTaxInvoice(tmp.getVattyp())){
					des= setTaxDetails(taxdets,des);
				}
				//Following code commented by sanka 2015.09.14
/*				for(int b=0;b<taxdets.size();b++){//Tax Details
					des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
				}*/
				des+="]";
				System.out.println("#des : "+des);
				asoObj.put("description", des);
				asoObj.put("rate", pbtamt.toString());
				System.out.println("\rpbtamt asoObj : " + pbtamt + "\r");
				asoObj.put("km", 0);
				asoObj.put("days", tmp.getChargdays());
				asoObj.put("type","VR");
				invDet.add(asoObj);
				
				if(tmp.getHiretypeid().equals("SD")==true){
					farfdtf=new Farfdtf();
					farfdtf=setFarfdtfHedVal(freshed, tmp, farfdtf);
					farfdtf.setEmpid("");
					farfdtf.setType("VR");
					
					java.math.BigDecimal days;
					days=roundTwoDecimals(new java.math.BigDecimal(tmp.getChargdays()));
					
					System.out.println("Dayssssssss "+days.toString());
					
					farfdtf.setPgamt(tmp.getRate());
					farfdtf.setDisper(tmp.getDiscount());
					farfdtf.setPdisamt(pdisamt);
					farfdtf.setPbtamt(pbtamt);
					
					//tbamt=roundTwoDecimals(pbtamt.multiply(days, MathContext.UNLIMITED));
					tbamt=pbtamt.multiply(days, MathContext.UNLIMITED);
					//tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
					farfdtf.setDays(days);
					farfdtf.setTbamt(tbamt);
					
					farfdtfs.add(farfdtf);
				}else{//WD or WEDDING
					List<Fresdriver> driverList=getDriverList(tmp.getResno(), false,"");
					driverList=splitVehicleRates(driverList,tmp.getNoofday()); //Split Vehicle Rates
					
					java.math.BigDecimal tot=new java.math.BigDecimal(0);
					for(int b=0;b<driverList.size();b++){
						farfdtf=new Farfdtf();
						farfdtf=setFarfdtfHedVal(freshed, tmp, farfdtf);
						farfdtf.setEmpid(driverList.get(b).getId().getEmpid());
						farfdtf.setType("VR");
												
						java.math.BigDecimal days;
						if(driverList.get(b).getDays()==null)
							days=roundTwoDecimals(new java.math.BigDecimal(driverList.get(b).getPriority()));
						else 
							days=roundTwoDecimals(driverList.get(b).getDays());
						
						farfdtf.setPgamt(tmp.getRate());
						farfdtf.setDisper(tmp.getDiscount());
						farfdtf.setPdisamt(pdisamt);
						farfdtf.setPbtamt(pbtamt);
						
						//tbamt=roundTwoDecimals(totalRental.multiply(days, MathContext.UNLIMITED));
						//tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
						tbamt=totalRental.multiply(days, MathContext.UNLIMITED);
						tbamt=tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP);
						tot=tot.add(tbamt);
						
						if(b==(driverList.size()-1)){
							if(tbamt.equals(tot)==false){
//								java.math.BigDecimal totDiff=roundTwoDecimals(totalRental.subtract(tot));
//								tbamt=roundTwoDecimals(tbamt.add(totDiff));
//								tot=roundTwoDecimals(tot.add(totDiff));
								java.math.BigDecimal totDiff=totalRental.subtract(tot);
								tbamt=tbamt.add(totDiff);
								tot=tot.add(totDiff);
							}
						}
						
						//System.out.println("days: " + roundTwoDecimals(days) + "    total per day: " + tbamt);
						farfdtf.setDays(days);
						farfdtf.setTbamt(tbamt);
						
						farfdtfs.add(farfdtf);
						
						System.out.println("total rental : " + totalRental + "      tot: " + tot);
					}
					
					System.out.println("total vehicle rate value: " + tot);
				}
								
				//EXCESS MILEAGE
				if(tmp.getCixsmileage()>0){
					
					//Per Day Discount Amount
					pdisamt=calcDisAmt(tmp.getXsmilerate(), tmp.getDiscount_xmile());
					//Per Day Before Tax Amount (Rate - Discount)
					pbtamt=roundTwoDecimals(tmp.getXsmilerate().subtract(pdisamt));
					//Calculate Additional Charges
					pbtamt=roundTwoDecimals(calcAddCharges(addChargeList, pbtamt));
//					totalAddCharge=new BigDecimal(0);
//					for(int s=0;s<addChargeList.size();s++){
//						fresaddcharge=addChargeList.get(s);
//						totalAddCharge=roundTwoDecimals(totalAddCharge.add(roundTwoDecimals(pbtamt.multiply(fresaddcharge.getPercentage()).divide(new BigDecimal(100)))));
//					}
//					pbtamt=roundTwoDecimals(pbtamt.add(totalAddCharge));
					java.math.BigDecimal totalXMileRate=pbtamt.multiply(new java.math.BigDecimal(tmp.getCixsmileage()));
					tbamt=new java.math.BigDecimal(0);
					
					asoObj=new ASObject();
					asoObj=setHedVal(tmp, asoObj);
					des="Excess Mileage\r";
					des+="[Rs."+ tmp.getXsmilerate() +"/- Per KM";
					if(tmp.getDiscount_xmile().compareTo(new java.math.BigDecimal(0))==0){ //Discount Not Applicable 
						
					}else if(tmp.getDiscount_xmile().compareTo(new java.math.BigDecimal(0))>0){ //Discount Applicable 
						des+=" - "+tmp.getDiscount_xmile()+"% Discount";
					}
					des+=addChargeDes; //Additional Charges
					
					//following code add  "+ VAT 11.00%" portion for description in following description part
					//description=Mercedes Benz BENZ C180 [Rs.19500.00/- Per Day - 10.0000%  Discount + VAT 11.00%]
					//Following code written by sanka 2015.09.14
					if(!isTaxInvoice(tmp.getVattyp())){
						des= setTaxDetails(taxdets,des);
					}
					//Following code commented by sanka 2015.09.14
/*					for(int b=0;b<taxdets.size();b++){//Tax Details
						des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
					}*/
					des+="]\r";
					des+="Mile. Done = "+(tmp.getCimileage()-tmp.getComileage())+" KM\r";
					des+="Limit. Mile. = "+(tmp.getAllotedkms()*tmp.getChargdays())+" KM\r";
					des+="Exce. Mile. = "+tmp.getCixsmileage()+" KM\r";
					asoObj.put("description", des);
					asoObj.put("rate", pbtamt);
					asoObj.put("km", tmp.getCixsmileage());
					asoObj.put("days", 0);
					asoObj.put("type","EM");
					invDet.add(asoObj);
					java.math.BigDecimal kms;
					
					if(tmp.getHiretypeid().equals("SD")==true){
						farfdtf=new Farfdtf();
						farfdtf=setFarfdtfHedVal(freshed, tmp, farfdtf);
						farfdtf.setEmpid("");
						farfdtf.setType("EM");
						
						kms=roundTwoDecimals(new java.math.BigDecimal(tmp.getCixsmileage()));
						
						farfdtf.setPgamt(tmp.getXsmilerate());
						farfdtf.setDisper(tmp.getDiscount_xmile());
						farfdtf.setPdisamt(pdisamt);
						farfdtf.setPbtamt(pbtamt);
						
						tbamt=roundTwoDecimals(pbtamt.multiply(kms, MathContext.UNLIMITED));
						//tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
						farfdtf.setKm(tmp.getCixsmileage());
						farfdtf.setDays(new java.math.BigDecimal(0));
						farfdtf.setTbamt(tbamt);
						
						System.out.println("kms: " + kms + "     pbtamt: " + pbtamt + "     tbamt: " + tbamt + "    xsmilerate: " + tmp.getXsmilerate());
						
						
						farfdtfs.add(farfdtf);
					}else{//WD or WEDDING
						List<Fresdriver> driverList=getDriverList(tmp.getResno(), false,"");
						driverList=splitVehicleRates(driverList,tmp.getNoofday()); //Split Vehicle Rates
						
						java.math.BigDecimal tot=new java.math.BigDecimal(0);
						for(int b=0;b<driverList.size();b++){
							farfdtf=new Farfdtf();
							farfdtf=setFarfdtfHedVal(freshed, tmp, farfdtf);
							farfdtf.setEmpid(driverList.get(b).getId().getEmpid());
							farfdtf.setType("EM");
													
							if(driverList.get(b).getDays()==null)
								kms=roundTwoDecimals(new java.math.BigDecimal(driverList.get(b).getPriority()));
							else 
								kms=roundTwoDecimals(driverList.get(b).getDays());
							
							farfdtf.setPgamt(tmp.getXsmilerate());
							farfdtf.setDisper(tmp.getDiscount_xmile());
							farfdtf.setPdisamt(pdisamt);
							farfdtf.setPbtamt(pbtamt);
							
							tbamt=roundTwoDecimals(totalXMileRate.multiply(kms, MathContext.UNLIMITED));
							tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
							tot=tot.add(tbamt);
							
							if(b==(driverList.size()-1)){
								if(tbamt.equals(tot)==false){
									java.math.BigDecimal totDiff=roundTwoDecimals(totalXMileRate.subtract(tot));
									tbamt=roundTwoDecimals(tbamt.add(totDiff));
									tot=roundTwoDecimals(tot.add(totDiff));
								}
							}
							
							//System.out.println("kilo meters : " + roundTwoDecimals(kms) + "    total per day: " + tbamt);
							farfdtf.setKm(tmp.getCixsmileage());
							farfdtf.setDays(new java.math.BigDecimal(0));
							farfdtf.setTbamt(tbamt);
							
							farfdtfs.add(farfdtf);
						}
						System.out.println("total excess mileage value: " + tot);
					}
				}
				
				//EXCESS HOURS
				if(tmp.getXhours()>0 && tmp.getHiretypeid().equals("WEDDING")==true){
					
					//Per Day Discount Amount
					pdisamt=calcDisAmt(tmp.getXhourrate(), tmp.getDiscountxhourrate());
					//Per Day Before Tax Amount (Rate - Discount)
					pbtamt=roundTwoDecimals(tmp.getXhourrate().subtract(pdisamt));
					//Calculate Additional Charges
					pbtamt=roundTwoDecimals(calcAddCharges(addChargeList, pbtamt));
					java.math.BigDecimal totalXHourRate=pbtamt.multiply(new java.math.BigDecimal(tmp.getXhours()));
					tbamt=new java.math.BigDecimal(0);
					
					asoObj=new ASObject();
					asoObj=setHedVal(tmp, asoObj);
					des="Excess Hours\r";
					des+="[Rs."+ tmp.getXhourrate() +"/- Per Hour";
					if(tmp.getDiscountxhourrate().compareTo(new java.math.BigDecimal(0))==0){ //Discount Not Applicable 
						
					}else if(tmp.getDiscountxhourrate().compareTo(new java.math.BigDecimal(0))>0){ //Discount Applicable 
						des+=" - "+tmp.getDiscountxhourrate()+"% Discount";
					}
					des+=addChargeDes; //Additional Charges
					
					//following code add  "+ VAT 11.00%" portion for description in following description part
					//description=Mercedes Benz BENZ C180 [Rs.19500.00/- Per Day - 10.0000%  Discount + VAT 11.00%]
					//Following code written by sanka 2015.09.14
					if(!isTaxInvoice(tmp.getVattyp())){
						des= setTaxDetails(taxdets,des);
					}
					//Following code commented by sanka 2015.09.14
/*					for(int b=0;b<taxdets.size();b++){//Tax Details
						des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
					}*/
					des+="]\r";
					des+="Alloted. Hours = "+tmp.getAllotedhours()+"\r";
					des+="Exce. Hours = "+tmp.getXhours()+"\r";
					asoObj.put("description", des);
					asoObj.put("rate", pbtamt);
					asoObj.put("km", 0);
					asoObj.put("days", tmp.getXhours());
					asoObj.put("type","EH");
					invDet.add(asoObj);
					java.math.BigDecimal kms;
					
					List<Fresdriver> driverList=getDriverList(tmp.getResno(), false,"");
					driverList=splitVehicleRates(driverList,tmp.getNoofday()); //Split Vehicle Rates
					
					java.math.BigDecimal tot=new java.math.BigDecimal(0);
					for(int b=0;b<driverList.size();b++){
						farfdtf=new Farfdtf();
						farfdtf=setFarfdtfHedVal(freshed, tmp, farfdtf);
						farfdtf.setEmpid(driverList.get(b).getId().getEmpid());
						farfdtf.setType("EH");
												
						if(driverList.get(b).getDays()==null)
							kms=roundTwoDecimals(new java.math.BigDecimal(driverList.get(b).getPriority()));
						else 
							kms=roundTwoDecimals(driverList.get(b).getDays());
						
						farfdtf.setPgamt(tmp.getXhourrate());
						farfdtf.setDisper(tmp.getDiscountxhourrate());
						farfdtf.setPdisamt(pdisamt);
						farfdtf.setPbtamt(pbtamt);
						
						tbamt=roundTwoDecimals(totalXHourRate.multiply(kms, MathContext.UNLIMITED));
						tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
						tot=tot.add(tbamt);
						
						if(b==(driverList.size()-1)){
							if(tbamt.equals(tot)==false){
								java.math.BigDecimal totDiff=roundTwoDecimals(totalXHourRate.subtract(tot));
								tbamt=roundTwoDecimals(tbamt.add(totDiff));
								tot=roundTwoDecimals(tot.add(totDiff));
							}
						}
						
						//System.out.println("kilo meters : " + roundTwoDecimals(kms) + "    total per day: " + tbamt);
						farfdtf.setKm(0);
						farfdtf.setDays(new java.math.BigDecimal(tmp.getXhours()));
						farfdtf.setTbamt(tbamt);
						
						farfdtfs.add(farfdtf);
					}
					System.out.println("total excess mileage value: " + tot);
				}
				
				//DETENTION 
				if(tmp.getCidetenhrs()>0){				
					
					//Per Day Discount Amount
					pdisamt=calcDisAmt(tmp.getDrotrate(), tmp.getDrdiscount_detention());
					//Per Day Before Tax Amount (Rate - Discount)
					pbtamt=roundTwoDecimals(tmp.getDrotrate().subtract(pdisamt));
					java.math.BigDecimal totalDetRate=pbtamt.multiply(new java.math.BigDecimal(tmp.getCidetenhrs()));
					tbamt=new java.math.BigDecimal(0);
					
					asoObj=new ASObject();
					asoObj=setHedVal(tmp, asoObj);
					des="Detention After 8 Hours\r";
					des+="[Rs."+ tmp.getDrotrate() +"/- Per Hour";
					if(tmp.getDrdiscount_detention().compareTo(new java.math.BigDecimal(0))==0){ //Discount Not Applicable 
						
					}else if(tmp.getDrdiscount_detention().compareTo(new java.math.BigDecimal(0))>0){ //Discount Applicable 
						des+=" - "+tmp.getDrdiscount_detention()+"% Discount";
					}
					//following code add  "+ VAT 11.00%" portion for description in following description part
					//description=Mercedes Benz BENZ C180 [Rs.19500.00/- Per Day - 10.0000%  Discount + VAT 11.00%]
					//Following code written by sanka 2015.09.14
					if(!isTaxInvoice(tmp.getVattyp())){
						des= setTaxDetails(taxdets,des);
					}
					//Following code commented by sanka 2015.09.14
/*					for(int b=0;b<taxdets.size();b++){//Tax Details
						des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
					}*/
					des+="]\r";
					asoObj.put("description", des);
					asoObj.put("rate", pbtamt);
					asoObj.put("km", 0);
					asoObj.put("days", tmp.getCidetenhrs());			
					asoObj.put("type","DT");
					invDet.add(asoObj);
					
					java.math.BigDecimal hrs;
					if(tmp.getHiretypeid().equals("SD")==true){
						farfdtf=new Farfdtf();
						farfdtf=setFarfdtfHedVal(freshed, tmp, farfdtf);
						farfdtf.setEmpid("");
						farfdtf.setType("DT");
						
						hrs=roundTwoDecimals(new java.math.BigDecimal(tmp.getCidetenhrs()));
						
						farfdtf.setPgamt(tmp.getDrotrate());
						farfdtf.setDisper(tmp.getDrdiscount_detention());
						farfdtf.setPdisamt(pdisamt);
						farfdtf.setPbtamt(pbtamt);
						
						tbamt=roundTwoDecimals(pbtamt.multiply(hrs, MathContext.UNLIMITED));
						//tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
						farfdtf.setKm(0);
						farfdtf.setDays(new java.math.BigDecimal(tmp.getCidetenhrs()));
						farfdtf.setTbamt(tbamt);
						
						System.out.println("hrs: " + hrs + "     pbtamt: " + pbtamt + "     tbamt: " + tbamt + "    xsmilerate: " + tmp.getXsmilerate());
						
						farfdtfs.add(farfdtf);
					}else{//WD or WEDDING
						List<Fresdriver> driverList=getDriverList(tmp.getResno(), false,"");
						driverList=splitVehicleRates(driverList,tmp.getNoofday()); //Split Vehicle Rates
						
						java.math.BigDecimal tot=new java.math.BigDecimal(0);
						for(int b=0;b<driverList.size();b++){
							farfdtf=new Farfdtf();
							farfdtf=setFarfdtfHedVal(freshed, tmp, farfdtf);
							farfdtf.setEmpid(driverList.get(b).getId().getEmpid());
							farfdtf.setType("DT");
													
							if(driverList.get(b).getDays()==null)
								hrs=roundTwoDecimals(new java.math.BigDecimal(driverList.get(b).getPriority()));
							else 
								hrs=roundTwoDecimals(driverList.get(b).getDays());
							
							farfdtf.setPgamt(tmp.getDrotrate());
							farfdtf.setDisper(tmp.getDrdiscount_detention());
							farfdtf.setPdisamt(pdisamt);
							farfdtf.setPbtamt(pbtamt);
							
							tbamt=roundTwoDecimals(totalDetRate.multiply(hrs, MathContext.UNLIMITED));
							tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
							tot=tot.add(tbamt);
							
							if(b==(driverList.size()-1)){
								if(tbamt.equals(tot)==false){
									java.math.BigDecimal totDiff=roundTwoDecimals(totalDetRate.subtract(tot));
									tbamt=roundTwoDecimals(tbamt.add(totDiff));
									tot=roundTwoDecimals(tot.add(totDiff));
								}
							}
							
							//System.out.println("detention hours : " + roundTwoDecimals(hrs) + "    total per day: " + tbamt);
							farfdtf.setKm(0);
							farfdtf.setDays(new java.math.BigDecimal(tmp.getCidetenhrs()));
							farfdtf.setTbamt(tbamt);
							
							farfdtfs.add(farfdtf);
						}
						System.out.println("total detention hours value: " + tot);
					}
				}
				
				//NIGHT OUT
				if(tmp.getCinightout()>0){
					
					//Per Day Discount Amount
					pdisamt=calcDisAmt(tmp.getDrnightoutrate(), tmp.getDrdiscount_nightout());
					//Per Day Before Tax Amount (Rate - Discount)
					pbtamt=roundTwoDecimals(tmp.getDrnightoutrate().subtract(pdisamt));
					java.math.BigDecimal totalNOutRate=pbtamt.multiply(new java.math.BigDecimal(tmp.getCinightout()));
					tbamt=new java.math.BigDecimal(0);
					
					asoObj=new ASObject();
					asoObj=setHedVal(tmp, asoObj);
					des="Night Out\r";
					des+="[Rs."+ tmp.getDrnightoutrate() +"/- Per Day";
					if(tmp.getDrdiscount_nightout().compareTo(new java.math.BigDecimal(0))==0){ //Discount Not Applicable 
						
					}else if(tmp.getDrdiscount_nightout().compareTo(new java.math.BigDecimal(0))>0){ //Discount Applicable 
						des+=" - "+tmp.getDrdiscount_nightout()+"% Discount";
					}
					//following code add  "+ VAT 11.00%" portion for description in following description part
					//description=Mercedes Benz BENZ C180 [Rs.19500.00/- Per Day - 10.0000%  Discount + VAT 11.00%]
					//Following code written by sanka 2015.09.14
					if(!isTaxInvoice(tmp.getVattyp())){
						des= setTaxDetails(taxdets,des);
					}
					//Following code commented by sanka 2015.09.14
/*					for(int b=0;b<taxdets.size();b++){//Tax Details
						des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
					}*/
					des+="]\r";
					asoObj.put("description", des);
					asoObj.put("rate", pbtamt);
					asoObj.put("km", 0);
					asoObj.put("days", tmp.getCinightout());
					asoObj.put("type","NO");
					invDet.add(asoObj);
					
					java.math.BigDecimal hrs;
					if(tmp.getHiretypeid().equals("SD")==true){
						farfdtf=new Farfdtf();
						farfdtf=setFarfdtfHedVal(freshed, tmp, farfdtf);
						farfdtf.setEmpid("");
						farfdtf.setType("NO");
						
						hrs=roundTwoDecimals(new java.math.BigDecimal(tmp.getCinightout()));
						
						farfdtf.setPgamt(tmp.getDrnightoutrate());
						farfdtf.setDisper(tmp.getDrdiscount_nightout());
						farfdtf.setPdisamt(pdisamt);
						farfdtf.setPbtamt(pbtamt);
						
						tbamt=roundTwoDecimals(pbtamt.multiply(hrs, MathContext.UNLIMITED));
						//tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
						farfdtf.setKm(0);
						farfdtf.setDays(new java.math.BigDecimal(tmp.getCinightout()));
						farfdtf.setTbamt(tbamt);
						
						System.out.println("hrs: " + hrs + "     pbtamt: " + pbtamt + "     tbamt: " + tbamt + "    xsmilerate: " + tmp.getXsmilerate());
						
						farfdtfs.add(farfdtf);
					}else{//WD or WEDDING
						List<Fresdriver> driverList=getDriverList(tmp.getResno(), false,"");
						driverList=splitVehicleRates(driverList,tmp.getNoofday()); //Split Vehicle Rates
						
						java.math.BigDecimal tot=new java.math.BigDecimal(0);
						for(int b=0;b<driverList.size();b++){
							farfdtf=new Farfdtf();
							farfdtf=setFarfdtfHedVal(freshed, tmp, farfdtf);
							farfdtf.setEmpid(driverList.get(b).getId().getEmpid());
							farfdtf.setType("NO");
													
							if(driverList.get(b).getDays()==null)
								hrs=roundTwoDecimals(new java.math.BigDecimal(driverList.get(b).getPriority()));
							else 
								hrs=roundTwoDecimals(driverList.get(b).getDays());
							
							farfdtf.setPgamt(tmp.getDrnightoutrate());
							farfdtf.setDisper(tmp.getDrdiscount_nightout());
							farfdtf.setPdisamt(pdisamt);
							farfdtf.setPbtamt(pbtamt);
							
							tbamt=roundTwoDecimals(totalNOutRate.multiply(hrs, MathContext.UNLIMITED));
							tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
							tot=tot.add(tbamt);
							
							if(b==(driverList.size()-1)){
								if(tbamt.equals(tot)==false){
									java.math.BigDecimal totDiff=roundTwoDecimals(totalNOutRate.subtract(tot));
									tbamt=roundTwoDecimals(tbamt.add(totDiff));
									tot=roundTwoDecimals(tot.add(totDiff));
								}
							}
							
							//System.out.println("night out : " + roundTwoDecimals(hrs) + "    total per day: " + tbamt);
							farfdtf.setKm(0);
							farfdtf.setDays(new java.math.BigDecimal(tmp.getCinightout()));
							farfdtf.setTbamt(tbamt);
							
							farfdtfs.add(farfdtf);
						}
						System.out.println("total night out value: " + tot);
					}
				}
				
				//OTHER
				if(tmp.getCiother().compareTo(new java.math.BigDecimal(0))>0){
					asoObj=new ASObject();
					asoObj=setHedVal(tmp, asoObj);
					des="Other Charges\r" + tmp.getOthernaration();
					asoObj.put("description", des);
					asoObj.put("rate", tmp.getCiother());
					asoObj.put("km", 0);
					asoObj.put("days", 0);
					asoObj.put("amount", tmp.getCiother());
					asoObj.put("type","OT");
					invDet.add(asoObj);

					farfdtf=new Farfdtf();
					farfdtf=setFarfdtfHedVal(freshed, tmp, farfdtf);
					farfdtf.setEmpid("");
					farfdtf.setType("OT");
					
					farfdtf.setPgamt(tmp.getCiother());
					farfdtf.setDisper(new java.math.BigDecimal(0));
					farfdtf.setPdisamt(new java.math.BigDecimal(0));
					farfdtf.setPbtamt(tmp.getCiother());

					farfdtf.setKm(0);
					farfdtf.setDays(new java.math.BigDecimal(0));
					farfdtf.setTbamt(tmp.getCiother());
					farfdtf.setDtfamt(tmp.getCiother());
					farfdtf.setDtfbal(tmp.getCiother());
					farfdtf.setDtfgst(new java.math.BigDecimal(0));
					
					farfdtfs.add(farfdtf);
				}
				
				//FUEL DIFFERENCES
				if(tmp.getCifueldiff().compareTo(new java.math.BigDecimal(0))>0 || tmp.getCifueldiff().compareTo(new java.math.BigDecimal(0))<0){
					asoObj=new ASObject();
					asoObj=setHedVal(tmp, asoObj);
					des="Fuel Difference";
					asoObj.put("description", des);
					asoObj.put("rate", tmp.getCifueldiff());
					asoObj.put("km", 0);
					asoObj.put("days", 0);
					asoObj.put("amount", tmp.getCifueldiff());
					asoObj.put("type","FD");
					invDet.add(asoObj);
					
					farfdtf=new Farfdtf();
					farfdtf=setFarfdtfHedVal(freshed, tmp, farfdtf);
					farfdtf.setEmpid("");
					farfdtf.setType("FD");
					
					farfdtf.setPgamt(tmp.getCifueldiff());
					farfdtf.setDisper(new java.math.BigDecimal(0));
					farfdtf.setPdisamt(new java.math.BigDecimal(0));
					farfdtf.setPbtamt(tmp.getCifueldiff());

					farfdtf.setKm(0);
					farfdtf.setDays(new java.math.BigDecimal(0));
					farfdtf.setTbamt(tmp.getCifueldiff());
					farfdtf.setDtfamt(tmp.getCifueldiff());
					farfdtf.setDtfbal(tmp.getCifueldiff());
					farfdtf.setDtfgst(new java.math.BigDecimal(0));
					
					farfdtfs.add(farfdtf);
				}
				
				//DAMAGES
				if(tmp.getCidamagers().compareTo(new java.math.BigDecimal(0))>0 || tmp.getCidamagers().compareTo(new java.math.BigDecimal(0))<0){//edited by sanka || tmp.getCidamagers().compareTo(new java.math.BigDecimal(0))<0
					asoObj=new ASObject();
					asoObj=setHedVal(tmp, asoObj);
					des="Damages";
					asoObj.put("description", des);
					asoObj.put("rate", tmp.getCidamagers());
					asoObj.put("km", 0);
					asoObj.put("days", 0);
					asoObj.put("amount", tmp.getCidamagers());
					asoObj.put("type","DM");
					invDet.add(asoObj);
					
					farfdtf=new Farfdtf();
					farfdtf=setFarfdtfHedVal(freshed, tmp, farfdtf);
					farfdtf.setEmpid("");
					farfdtf.setType("DM");
					
					farfdtf.setPgamt(tmp.getCidamagers());
					farfdtf.setDisper(new java.math.BigDecimal(0));
					farfdtf.setPdisamt(new java.math.BigDecimal(0));
					farfdtf.setPbtamt(tmp.getCidamagers());

					farfdtf.setKm(0);
					farfdtf.setDays(new java.math.BigDecimal(0));
					farfdtf.setTbamt(tmp.getCidamagers());
					farfdtf.setDtfamt(tmp.getCidamagers());
					farfdtf.setDtfbal(tmp.getCidamagers());
					farfdtf.setDtfgst(new java.math.BigDecimal(0));
					
					farfdtfs.add(farfdtf);
				}
				
				//ACCESSORY
				List<Fresaccs> fresaccsList=getAccessoryList(tmp.getResno());
				if(fresaccsList.size()>0){
					for(int t=0;t<fresaccsList.size();t++){
						Fresaccs fresaccs=fresaccsList.get(t);
						
						//Per Day Discount Amount
						pdisamt=calcDisAmt(fresaccs.getRate(), fresaccs.getDiscount());
						//Per Day Before Tax Amount (Rate - Discount)
						//pbtamt=roundTwoDecimals(fresaccs.getRate().subtract(pdisamt));
						pbtamt=fresaccs.getRate().subtract(pdisamt);
						//java.math.BigDecimal totalAccRate=roundTwoDecimals(pbtamt.multiply(new java.math.BigDecimal(fresaccs.getQty())));
						tbamt=new java.math.BigDecimal(0);
						
						asoObj=new ASObject();
						asoObj=setHedVal(tmp, asoObj);
						des=fresaccs.getDescription()+"\r";
						//des+="[Rs."+ fresaccs.getRate() +"/- Per Item";
						des+="[Rs."+ fresaccs.getStandardrate() +"/- " + formatRateDuration(fresaccs.getRatetype());
						if(fresaccs.getDiscount().compareTo(new java.math.BigDecimal(0))==0){ //Discount Not Applicable 
							
						}else if(fresaccs.getDiscount().compareTo(new java.math.BigDecimal(0))>0){ //Discount Applicable 
							des+=" - "+fresaccs.getDiscount()+"% Discount";
						}
						//following code add  "+ VAT 11.00%" portion for description in following description part
						//description=Mercedes Benz BENZ C180 [Rs.19500.00/- Per Day - 10.0000%  Discount + VAT 11.00%]
						//Following code written by sanka 2015.09.14
						if(!isTaxInvoice(tmp.getVattyp())){
							des= setTaxDetails(taxdets,des);
						}
						//Following code commented by sanka 2015.09.14
/*						for(int b=0;b<taxdets.size();b++){//Tax Details
							des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
						}*/
						des+="]\r";
						asoObj.put("description", des);
						asoObj.put("rate", pbtamt);
						asoObj.put("km", 0);
						asoObj.put("days", fresaccs.getNoofdays());
						asoObj.put("qty", fresaccs.getQty());
						asoObj.put("type","AC");
						asoObj.put("subseq", (t+1));
						invDet.add(asoObj);
						
						farfdtf=new Farfdtf();
						farfdtf=setFarfdtfHedVal(freshed, tmp, farfdtf);
						farfdtf.setEmpid("");
						farfdtf.setType("AC");
						
						farfdtf.setPgamt(fresaccs.getRate());
						farfdtf.setDisper(fresaccs.getDiscount());
						farfdtf.setPdisamt(pdisamt);
						farfdtf.setPbtamt(pbtamt);
						
						//tbamt=roundTwoDecimals(pbtamt.multiply(new BigDecimal(fresaccs.getNoofdays()), MathContext.UNLIMITED));
						//tbamt=roundTwoDecimals(tbamt.multiply(new BigDecimal(fresaccs.getQty()), MathContext.UNLIMITED));
						tbamt=roundTwoDecimals(pbtamt.multiply(new BigDecimal(fresaccs.getNoofdays()), MathContext.UNLIMITED));
						tbamt=roundTwoDecimals(tbamt.multiply(new BigDecimal(fresaccs.getQty()), MathContext.UNLIMITED));
						
						//tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(fresaccs.getNoofdays()), RoundingMode.HALF_UP));
						java.math.BigDecimal tot=new java.math.BigDecimal(0);
						tot=tot.add(tbamt);
						
						
						System.out.println("\r");
						System.out.println("rate: " + fresaccs.getRate() + "     discount per: " + fresaccs.getDiscount() + "     pdisamt: " + pdisamt + "    pbtmt: " + pbtamt + "    tbamt: " + tbamt);
						System.out.println("\r");
						
						farfdtf.setSubseq((t+1));
						farfdtf.setKm(0);
						farfdtf.setDays(new java.math.BigDecimal(fresaccs.getNoofdays()));
						farfdtf.setQty(fresaccs.getQty());
						farfdtf.setTbamt(tbamt);
						farfdtfs.add(farfdtf);
					}
				}
				
				//OTHER SERVICES
				List<Fresothsrv> fresothsrvList=getOtherServiceList(tmp.getResno());				
				if(fresothsrvList.size()>0){
					Fresothsrv fresothsrv;
					for(int m=0;m<fresothsrvList.size();m++){
						fresothsrv=fresothsrvList.get(m);
						
						//Per Day Discount Amount
						pdisamt=calcDisAmt(fresothsrv.getRate(), fresothsrv.getDiscount());
						//Per Day Before Tax Amount (Rate - Discount)
						//pbtamt=roundTwoDecimals(fresothsrv.getRate().subtract(pdisamt));
						pbtamt=fresothsrv.getRate().subtract(pdisamt);
						java.math.BigDecimal totalOthSrvRate=pbtamt.multiply(new java.math.BigDecimal(fresothsrv.getNoofdays()));
						tbamt=new java.math.BigDecimal(0);
						
						asoObj=new ASObject();
						asoObj=setHedVal(tmp, asoObj);
						des=fresothsrv.getDescription()+"\r";
						des+="[Rs."+ fresothsrv.getStandardrate() +"/- " + formatRateDuration(fresothsrv.getRatetype());
						if(fresothsrv.getDiscount().compareTo(new java.math.BigDecimal(0))==0){ //Discount Not Applicable 
							
						}else if(fresothsrv.getDiscount().compareTo(new java.math.BigDecimal(0))>0){ //Discount Applicable 
							des+=" - "+fresothsrv.getDiscount()+"% Discount";
						}
						//following code add  "+ VAT 11.00%" portion for description in following description part
						//description=Mercedes Benz BENZ C180 [Rs.19500.00/- Per Day - 10.0000%  Discount + VAT 11.00%]
						//Following code written by sanka 2015.09.14
						if(!isTaxInvoice(tmp.getVattyp())){
							des= setTaxDetails(taxdets,des);
						}
						//Following code commented by sanka 2015.09.14
/*						for(int b=0;b<taxdets.size();b++){//Tax Details
							des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
						}*/
						des+="]";
						asoObj.put("description", des);
						asoObj.put("rate", pbtamt);
						asoObj.put("km", 0);
						asoObj.put("days", fresothsrv.getNoofdays());
						asoObj.put("type","OS");
						asoObj.put("subseq", (m+1));
						invDet.add(asoObj);
						java.math.BigDecimal days;
						
						List<Fresdriver> driverList=getDriverList(fresothsrv.getId().getResno(), true, fresothsrv.getId().getSrvid());
						driverList=splitVehicleRates(driverList,fresothsrv.getNoofdays()); //Split Vehicle Rates
						
						java.math.BigDecimal tot=new java.math.BigDecimal(0);
						for(int b=0;b<driverList.size();b++){
							farfdtf=new Farfdtf();
							farfdtf=setFarfdtfHedVal(freshed, tmp, farfdtf);
							farfdtf.setEmpid(driverList.get(b).getId().getEmpid());
							farfdtf.setType("OS");
													
							if(driverList.get(b).getDays()==null)
								days=roundTwoDecimals(new java.math.BigDecimal(driverList.get(b).getPriority()));
							else 
								days=roundTwoDecimals(driverList.get(b).getDays());
							
							farfdtf.setPgamt(fresothsrv.getRate());
							farfdtf.setDisper(fresothsrv.getDiscount());
							farfdtf.setPdisamt(pdisamt);
							farfdtf.setPbtamt(pbtamt);
							
//							tbamt=roundTwoDecimals(totalOthSrvRate.multiply(days, MathContext.UNLIMITED));
//							tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(fresothsrv.getNoofdays()), RoundingMode.HALF_UP));
							tbamt=totalOthSrvRate.multiply(days, MathContext.UNLIMITED);
							tbamt=tbamt.divide(new java.math.BigDecimal(fresothsrv.getNoofdays()), RoundingMode.HALF_UP);
							tot=tot.add(tbamt);
							
							if(b==(driverList.size()-1)){
								if(tbamt.equals(tot)==false){
									java.math.BigDecimal totDiff=roundTwoDecimals(totalOthSrvRate.subtract(tot));
									tbamt=roundTwoDecimals(tbamt.add(totDiff));
									tot=roundTwoDecimals(tot.add(totDiff));
								}
							}
							
							//System.out.println("kilo meters : " + roundTwoDecimals(kms) + "    total per day: " + tbamt);
							farfdtf.setSubseq((m+1));
							farfdtf.setKm(0);
							farfdtf.setDays(new java.math.BigDecimal(fresothsrv.getNoofdays()));
							farfdtf.setTbamt(tbamt);
							
							farfdtfs.add(farfdtf);
						}
						System.out.println("total other service ["+fresothsrv.getDescription()+"] value: " + tot);
					}
				}
			}
			
			ArrayCollection tmpAC=calcTax(farfdtfs);
			if(tmpAC==null)
				return null;
			
			farfdtfs=(List<Farfdtf>)tmpAC.get(0);
			List<Fdtftax> fdtftaxs=(List<Fdtftax>)tmpAC.get(1);
			
			//Calculate Invoice Detail Tax 
			invDet=calcInvDetTax(invDet);
			
			
			System.out.println("*******************\r\r");
			//System.out.println("invDet legth: " + invDet.size() + "       farfdtfs length: " + farfdtfs.size());
			System.out.println("\r");
			//Tally Differences
			aso=null;
			for(int x=0;x<invDet.size();x++){
				aso=(ASObject)invDet.get(x);
				String resno=aso.get("resno").toString();
				String type=aso.get("type").toString();
				BigDecimal amount=roundTwoDecimals(new BigDecimal(aso.get("amount").toString()));
				BigDecimal taxval=roundTwoDecimals(new BigDecimal(aso.get("taxval").toString()));
				int subSeq=Integer.parseInt(aso.get("subseq").toString());
				BigDecimal runTotal=new BigDecimal(0);
				BigDecimal runTax=new BigDecimal(0);
				int index=-1;
				Farfdtf fdf=new Farfdtf();
				for(int y=0;y<farfdtfs.size();y++){
					fdf=farfdtfs.get(y);
					System.out.println("fdf.getSubseq() :"+fdf.getSubseq());
					System.out.println("subSeq :"+subSeq);
					if(fdf.getResno().equals(resno)==true && fdf.getType().equals(type)==true && fdf.getSubseq()==subSeq){
						runTotal=roundTwoDecimals(runTotal.add(fdf.getDtfamt()));
						runTax=roundTwoDecimals(runTax.add(fdf.getDtfgst()));
						index=y;
						System.out.println("index :"+index);
					}
				}
				if(amount.equals(runTotal)==false && index!=-1){
					BigDecimal diff=new BigDecimal(0);
					diff=roundTwoDecimals(amount.subtract(runTotal));
					if(diff.equals(new BigDecimal(0))==false){
						BigDecimal dtfamt=new BigDecimal(0);
						if (farfdtfs != null && farfdtfs.size() - 1 >= index) {
							dtfamt=roundTwoDecimals(farfdtfs.get(index).getDtfamt());
						} else {
							System.out.println("error handled and ignored");
						}
						
						//System.out.println("type: " + type + "     amount: " + amount + "   runtotal: " + runTotal + "   diff: " + diff + "    dtfamt: " + dtfamt + " index: " + index);
						dtfamt=roundTwoDecimals(dtfamt.add(diff));
						fdf.setDtfamt(dtfamt);
						fdf.setDtfbal(dtfamt);
						farfdtfs.get(index).setDtfamt(dtfamt);
						farfdtfs.get(index).setDtfbal(dtfamt);

					}
				}
				if(taxval.equals(runTax)==false && index!=-1){
					BigDecimal diff=new BigDecimal(0);
					diff=roundTwoDecimals(taxval.subtract(runTax));
					if(diff.equals(new BigDecimal(0))==false){
						
						BigDecimal dtfgst=new BigDecimal(0);
						if (farfdtfs != null && farfdtfs.size() - 1 >= index) {
							dtfgst=roundTwoDecimals(farfdtfs.get(index).getDtfgst());
						} else {
							System.out.println("error handled and ignored");
						}
						
						
						//BigDecimal dtfgst=roundTwoDecimals(farfdtfs.get(index).getDtfgst());
						//System.out.println("type: " + type + "     taxval: " + taxval + "   runtax: " + runTax + "   diff: " + diff + "    dtfgst: " + dtfgst + " index: " + index + "diff: " + diff + "\r");
						dtfgst=roundTwoDecimals(dtfgst.add(diff));
						fdf.setDtfgst(dtfgst);
						farfdtfs.get(index).setDtfgst(dtfgst);

						//Update Tax Details With Differences 
						index=0;
						BigDecimal runTaxAmt=new BigDecimal(0);
						for(int z=0;z<fdtftaxs.size();z++){
							Fdtftax fdtftax=fdtftaxs.get(z);
							if(fdtftax.getResno().equals(resno)==true && fdtftax.getType().equals(type)==true && fdtftax.getSubseq()==subSeq){
								runTaxAmt=roundTwoDecimals(runTaxAmt.add(fdtftax.getTaxamt()));
								index=z;
							}
						}
						if(taxval.equals(runTaxAmt)==false && index!=-1){
							diff=new BigDecimal(0);
							diff=roundTwoDecimals(taxval.subtract(runTaxAmt));
							BigDecimal taxamt=roundTwoDecimals(fdtftaxs.get(index).getTaxamt());
							//System.out.println("resno: " +  resno + "    subseq: " + subSeq + "    type: " + type + "     taxval: " + taxval + "   runTaxAmt: " + runTaxAmt + "   diff: " + diff + "   index: " + index + "\r");
							taxamt=roundTwoDecimals(taxamt.add(diff));
							fdtftaxs.get(index).setTaxamt(taxamt);
						}
					}
				}				
			}
			System.out.println("\r\r*******************");
			
//			//Persist farfdtf
//			for(int d=0;d<farfdtfs.size();d++){
//				Farfdtf mf=farfdtfs.get(d);
//				//System.out.println("persists farfdtf mf: " + d);
//				em.persist(mf);
//				em.flush();
//			}
//			
//			//Persist fdtftax
//			for(int d=0;d<fdtftaxs.size();d++){
//				Fdtftax tax=fdtftaxs.get(d);
//				//System.out.println("persists fdtftax tax: " + d);
//				em.persist(tax);
//				em.flush();
//			}
			
//			System.out.println("list length: " + list.size());
			ArrayCollection ac=new ArrayCollection();
			ac.add(invDet);
			ac.add(farfdtfs);
			ac.add(fdtftaxs);
			
			return ac;
		} catch (Exception e) {
			System.out.println("FarfdtfDAOImpl genInvoice: " + e.getMessage());
			System.out.println("sanka#############################################################################");
			e.printStackTrace();
			System.out.println("sanka#############################################################################");
			throw new RuntimeException(e);
		}
		finally{em.flush();}
		//return null;
	}
	
		
		@Transactional
		@Override
		public ArrayCollection genInvoice2(Freshed freshed, ArrayCollection subAgrList,String fromDateStr,String toDateStr) {
			try {
				
				
				//String dateStart = "01/14/2012 09:29:58";
				//String dateStop = "01/15/2012 10:31:48";
				
				//HH converts hour in 24 hours format (0-23), day calculation
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");//DD/MM/YYYY
				java.util.Date fromDate=format.parse(fromDateStr);
				java.util.Date toDate=format.parse(toDateStr);
				//in milliseconds
				long diffTime = toDate.getTime() - fromDate.getTime();
				long diffDays = diffTime / (24 * 60 * 60 * 1000);
				diffDays=diffDays+1;
				//int noOfDays=diffDays
				System.out.print("diffDays :"+diffDays + " days, ");
				
				
				em.flush();
				String strSubAgrList="";
				ASObject aso;
				for(int a=0;a<subAgrList.size();a++) {
					aso=(ASObject)subAgrList.get(a);
					strSubAgrList+="'"+aso.get("resno").toString()+"'";
					if(a<(subAgrList.size()-1))
						strSubAgrList+=",";
				}
				
				String query="SELECT r.*,";
				//query += " (SELECT taxcomcode FROM fcompanytax WHERE companyid='"+freshed.getCompanyid()+"' AND hiretypeid=r.hiretypeid) AS taxcomcode,";
				query += " (RTRIM((SELECT description FROM fvehiclemake WHERE vehimakeid=(SELECT vehimakeid FROM fvehicle WHERE regno=r.regno))) + ' ' +";
				query += " RTRIM((SELECT description FROM fvehiclemodel WHERE vehimodelid=(SELECT vehimodelid FROM fvehicle WHERE regno=r.regno) AND vehimakeid=(SELECT vehimakeid FROM fvehicle WHERE regno=r.regno)))) AS vehicle";
				query += " FROM"; 
				query += " (SELECT"; 
				query += " r.resno,";
				query += " r.taxcomcode,";
				query += " rv.regno,";
				query += " vr.ratetype,vr.rate,vr.discount,vr.xsmilerate,ISNULL(r.discount_xmile,0) AS discount_xmile,vr.allotedkms,ISNULL(vr.xhourrate,0) AS xhourrate,ISNULL(vr.allotedhours,0) AS allotedhours,ISNULL(vr.discountxhourrate,0) AS discountxhourrate,"; //Vehicle Details 
				//query += " dr.ratetype AS drratetype,dr.discount AS drdiscount,dr.nightoutrate AS drnightoutrate,dr.otrate AS drotrate,"; //Driver Details 
				//query += " dr.ratetype AS drratetype,dr.nightoutrate AS drnightoutrate,dr.discount_nightout AS drdiscount_nightout,dr.otrate AS drotrate,dr.discount_detention AS drdiscount_detention,"; //Driver Details
				query += " ISNULL(xhours,0) AS xhours,ISNULL(xhoursamt,0) AS xhoursamt,ISNULL(discount_xhoursamt,0) AS discount_xhoursamt,";
				query += " ISNULL(dr.ratetype,'') AS drratetype,";
				query += " ISNULL(dr.nightoutrate,0) AS drnightoutrate,";
				query += " ISNULL(dr.discount_nightout,0) AS drdiscount_nightout,";
				query += " ISNULL(dr.otrate,0) AS drotrate,";
				query += " ISNULL(dr.discount_detention,0) AS drdiscount_detention,"; 
				query += " r.hiretypeid,";
				query += " (SELECT description FROM fhiretype WHERE hiretypeid=r.hiretypeid) AS hiretype,";
				query += " r.dout,r.din,r.noofday,r.chargdays,r.deposit,r.advance,r.comileage,r.cimileage,r.cixsmileage,r.cixsmileagers,r.cidetenhrs,r.cidetenhrsrs,r.cinightout,r.cinightoutrs,r.ciother,ISNULL(r.othernaration,'') AS othernaration,r.cifueldiff,r.cidamagers,vr.standardrate [vrsrate]"; 
				query += " FROM freservation AS r LEFT OUTER JOIN";
				query += " fresvehicle AS rv ON r.resno=rv.resno AND rv.priority=1";
				query += " LEFT OUTER JOIN fresvehiclerate AS vr ON r.resno=vr.resno";
				query += " LEFT OUTER JOIN fresdriverrate AS dr ON r.resno=dr.resno";
				query += " WHERE r.resno IN("+strSubAgrList+")) AS r";
				query += " ORDER BY r.resno";
				
				System.out.println("query: " + query);
				
				List<FarfdtfTMP> list=em.createNativeQuery(query, "farfdtfTMPSQL").getResultList();
				
				List<Farfdtf> farfdtfs=new ArrayList<Farfdtf>();
				Farfdtf farfdtf;
				FarfdtfPK pk;
				List<Ftaxdet> taxdets; 
				
				ArrayCollection invDet=new ArrayCollection();
				ASObject asoObj;
				String des;
				seqCnt=1;
				hedSeqCnt=1;
				
				for(int a=0;a<list.size();a++){
					
					if (a==1)
					{
						System.out.println(a);
					}
					
					
					FarfdtfTMP tmp=list.get(a);
					taxdets=loadTaxDet(tmp.getTaxcomcode());
					
					//Get Additional Charges
					List<Fresaddcharge> addChargeList=getAdditionalCharges(tmp.getResno());
					String addChargeDes="";
					Fresaddcharge fresaddcharge;
					for(int s=0;s<addChargeList.size();s++){
						fresaddcharge=addChargeList.get(s);
						addChargeDes+=" + "+fresaddcharge.getAddcharge()+" "+fresaddcharge.getPercentage()+"%";
					}
					
					//VEHICLE RATE
					asoObj=new ASObject(); 
					asoObj=setHedVal(tmp, asoObj); //Set HedVal 
					
					//Per Day Discount Amount
					java.math.BigDecimal pdisamt=calcDisAmt(tmp.getRate(), tmp.getDiscount());
					//Per Day Before Tax Amount (Rate - Discount)
					//java.math.BigDecimal pbtamt=roundTwoDecimals(tmp.getRate().subtract(pdisamt));
					java.math.BigDecimal pbtamt=tmp.getRate().subtract(pdisamt);
					//Calculate Additional Charges
					//pbtamt=roundTwoDecimals(calcAddCharges(addChargeList, pbtamt));
					pbtamt=calcAddCharges(addChargeList, pbtamt);
//				BigDecimal totalAddCharge=new BigDecimal(0);
//				for(int s=0;s<addChargeList.size();s++){
//					fresaddcharge=addChargeList.get(s);
//					totalAddCharge=roundTwoDecimals(totalAddCharge.add(roundTwoDecimals(pbtamt.multiply(fresaddcharge.getPercentage()).divide(new BigDecimal(100)))));
//				}
//				pbtamt=roundTwoDecimals(pbtamt.add(totalAddCharge));
					//Total Before Tax Amount (pbtamt X km or pbtamt X days)
					
					java.math.BigDecimal tbamt;
					java.math.BigDecimal totalRental=pbtamt.multiply(new java.math.BigDecimal(diffDays));
					System.out.println("rate: " + tmp.getRate() + "   disper: " + tmp.getDiscount() + "   pdisamt: " + pdisamt + "    pbtamt: " + pbtamt + "   totalRental: " + totalRental);
					
					des=tmp.getVehicle()+"\r";
					//des+="[Rs."+ tmp.getRate() +"/- Per Day";
					des+="[Rs."+ tmp.getVrsrate() +"/- " + formatRateDuration(tmp.getRatetype());
					if(tmp.getDiscount().compareTo(new java.math.BigDecimal(0))==0){ //Discount Not Applicable 
						
					}else if(tmp.getDiscount().compareTo(new java.math.BigDecimal(0))>0){ //Discount Applicable 
						des+=" - "+tmp.getDiscount()+"%  Discount";
					}
					des+=addChargeDes; //Additional Charges
					
//SELECT td.taxcode,t.taxname,t.taxper,td.taxrate,td.taxseq FROM 
//ftaxdet AS td,ftax AS t WHERE t.taxcode=td.taxcode AND td.taxcomcode='VATNBT' ORDER BY td.taxseq	
					
//				NBT       	NBT  3.00	2.2400000000	1
//				VAT       	VAT  12.00	12.0000000000	2
					//following code add  "+ VAT 11.00%" portion for description in following description part
					//description=Mercedes Benz BENZ C180 [Rs.19500.00/- Per Day - 10.0000%  Discount + VAT 11.00%]
					//Following code written by sanka 2015.09.14
					if(!isTaxInvoice(tmp.getVattyp())){
						des= setTaxDetails(taxdets,des);
					}
					//Following code commented by sanka 2015.09.14
/*					for(int b=0;b<taxdets.size();b++){//Tax Details
						des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
					}*/
					des+="]";
					System.out.println("#des : "+des);
					asoObj.put("description", des);
					asoObj.put("rate", pbtamt.toString());
					System.out.println("\rpbtamt asoObj : " + pbtamt + "\r");
					asoObj.put("km", 0);
					asoObj.put("days", diffDays);
					asoObj.put("type","VR");
					asoObj.put("dfrom", fromDate);
					asoObj.put("dto", toDate);
					invDet.add(asoObj);
					
					if(tmp.getHiretypeid().equals("SD")==true){
						farfdtf=new Farfdtf();
						farfdtf=setFarfdtfHedVal2(freshed, tmp, farfdtf);
						farfdtf.setEmpid("");
						farfdtf.setType("VR");
						
						java.math.BigDecimal days;
						days=roundTwoDecimals(new java.math.BigDecimal(diffDays));
						
						System.out.println("Dayssssssss "+days.toString());
						
						farfdtf.setPgamt(tmp.getRate());
						farfdtf.setDisper(tmp.getDiscount());
						farfdtf.setPdisamt(pdisamt);
						farfdtf.setPbtamt(pbtamt);
						
						//tbamt=roundTwoDecimals(pbtamt.multiply(days, MathContext.UNLIMITED));
						tbamt=pbtamt.multiply(days, MathContext.UNLIMITED);
						//tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
						farfdtf.setDays(days);
						farfdtf.setTbamt(tbamt);
						
						farfdtfs.add(farfdtf);
					}else{//WD or WEDDING
						List<Fresdriver> driverList=getDriverList(tmp.getResno(), false,"");
						driverList=splitVehicleRates(driverList,tmp.getNoofday()); //Split Vehicle Rates
						//TODO sanka performer invoice 
						java.math.BigDecimal tot=new java.math.BigDecimal(0);
						for(int b=0;b<driverList.size();b++){
							//TODO sanka performer invoice 
							farfdtf=new Farfdtf();
							farfdtf=setFarfdtfHedVal2(freshed, tmp, farfdtf);
							farfdtf.setEmpid(driverList.get(b).getId().getEmpid());
							farfdtf.setType("VR");
							
							java.math.BigDecimal days;
							if(driverList.get(b).getDays()==null)
								days=roundTwoDecimals(new java.math.BigDecimal(driverList.get(b).getPriority()));
							else 
								days=roundTwoDecimals(driverList.get(b).getDays());
							
							farfdtf.setPgamt(tmp.getRate());
							farfdtf.setDisper(tmp.getDiscount());
							farfdtf.setPdisamt(pdisamt);
							farfdtf.setPbtamt(pbtamt);
							
							//tbamt=roundTwoDecimals(totalRental.multiply(days, MathContext.UNLIMITED));
							//tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
							tbamt=totalRental.multiply(days, MathContext.UNLIMITED);
							tbamt=tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP);
							tot=tot.add(tbamt);
							
							if(b==(driverList.size()-1)){
								if(tbamt.equals(tot)==false){
//								java.math.BigDecimal totDiff=roundTwoDecimals(totalRental.subtract(tot));
//								tbamt=roundTwoDecimals(tbamt.add(totDiff));
//								tot=roundTwoDecimals(tot.add(totDiff));
									java.math.BigDecimal totDiff=totalRental.subtract(tot);
									tbamt=tbamt.add(totDiff);
									tot=tot.add(totDiff);
								}
							}
							
							//System.out.println("days: " + roundTwoDecimals(days) + "    total per day: " + tbamt);
							farfdtf.setDays(new BigDecimal(diffDays));
							farfdtf.setTbamt(tbamt);
							
							farfdtfs.add(farfdtf);
							
							System.out.println("total rental : " + totalRental + "      tot: " + tot);
						}
						
						System.out.println("total vehicle rate value: " + tot);
					}
					
					//EXCESS MILEAGE
					if(tmp.getCixsmileage()>0){
						
						//Per Day Discount Amount
						pdisamt=calcDisAmt(tmp.getXsmilerate(), tmp.getDiscount_xmile());
						//Per Day Before Tax Amount (Rate - Discount)
						pbtamt=roundTwoDecimals(tmp.getXsmilerate().subtract(pdisamt));
						//Calculate Additional Charges
						pbtamt=roundTwoDecimals(calcAddCharges(addChargeList, pbtamt));
//					totalAddCharge=new BigDecimal(0);
//					for(int s=0;s<addChargeList.size();s++){
//						fresaddcharge=addChargeList.get(s);
//						totalAddCharge=roundTwoDecimals(totalAddCharge.add(roundTwoDecimals(pbtamt.multiply(fresaddcharge.getPercentage()).divide(new BigDecimal(100)))));
//					}
//					pbtamt=roundTwoDecimals(pbtamt.add(totalAddCharge));
						java.math.BigDecimal totalXMileRate=pbtamt.multiply(new java.math.BigDecimal(tmp.getCixsmileage()));
						tbamt=new java.math.BigDecimal(0);
						
						asoObj=new ASObject();
						asoObj=setHedVal(tmp, asoObj);
						des="Excess Mileage\r";
						des+="[Rs."+ tmp.getXsmilerate() +"/- Per KM";
						if(tmp.getDiscount_xmile().compareTo(new java.math.BigDecimal(0))==0){ //Discount Not Applicable 
							
						}else if(tmp.getDiscount_xmile().compareTo(new java.math.BigDecimal(0))>0){ //Discount Applicable 
							des+=" - "+tmp.getDiscount_xmile()+"% Discount";
						}
						des+=addChargeDes; //Additional Charges
						//following code add  "+ VAT 11.00%" portion for description in following description part
						//description=Mercedes Benz BENZ C180 [Rs.19500.00/- Per Day - 10.0000%  Discount + VAT 11.00%]
						//Following code written by sanka 2015.09.14
						if(!isTaxInvoice(tmp.getVattyp())){
							des= setTaxDetails(taxdets,des);
						}
						//Following code commented by sanka 2015.09.14
/*						for(int b=0;b<taxdets.size();b++){//Tax Details
							des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
						}*/
						des+="]\r";
						des+="Mile. Done = "+(tmp.getCimileage()-tmp.getComileage())+" KM\r";
						des+="Limit. Mile. = "+(tmp.getAllotedkms()*tmp.getChargdays())+" KM\r";
						des+="Exce. Mile. = "+tmp.getCixsmileage()+" KM\r";
						asoObj.put("description", des);
						asoObj.put("rate", pbtamt);
						asoObj.put("km", tmp.getCixsmileage());
						asoObj.put("days", 0);
						asoObj.put("type","EM");
						invDet.add(asoObj);
						java.math.BigDecimal kms;
						
						if(tmp.getHiretypeid().equals("SD")==true){
							farfdtf=new Farfdtf();
							farfdtf=setFarfdtfHedVal2(freshed, tmp, farfdtf);
							farfdtf.setEmpid("");
							farfdtf.setType("EM");
							
							kms=roundTwoDecimals(new java.math.BigDecimal(tmp.getCixsmileage()));
							
							farfdtf.setPgamt(tmp.getXsmilerate());
							farfdtf.setDisper(tmp.getDiscount_xmile());
							farfdtf.setPdisamt(pdisamt);
							farfdtf.setPbtamt(pbtamt);
							
							tbamt=roundTwoDecimals(pbtamt.multiply(kms, MathContext.UNLIMITED));
							//tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
							farfdtf.setKm(tmp.getCixsmileage());
							farfdtf.setDays(new java.math.BigDecimal(0));
							farfdtf.setTbamt(tbamt);
							
							System.out.println("kms: " + kms + "     pbtamt: " + pbtamt + "     tbamt: " + tbamt + "    xsmilerate: " + tmp.getXsmilerate());
							
							
							farfdtfs.add(farfdtf);
						}else{//WD or WEDDING
							List<Fresdriver> driverList=getDriverList(tmp.getResno(), false,"");
							driverList=splitVehicleRates(driverList,tmp.getNoofday()); //Split Vehicle Rates
							
							java.math.BigDecimal tot=new java.math.BigDecimal(0);
							for(int b=0;b<driverList.size();b++){
								farfdtf=new Farfdtf();
								farfdtf=setFarfdtfHedVal2(freshed, tmp, farfdtf);
								farfdtf.setEmpid(driverList.get(b).getId().getEmpid());
								farfdtf.setType("EM");
								
								if(driverList.get(b).getDays()==null)
									kms=roundTwoDecimals(new java.math.BigDecimal(driverList.get(b).getPriority()));
								else 
									kms=roundTwoDecimals(driverList.get(b).getDays());
								
								farfdtf.setPgamt(tmp.getXsmilerate());
								farfdtf.setDisper(tmp.getDiscount_xmile());
								farfdtf.setPdisamt(pdisamt);
								farfdtf.setPbtamt(pbtamt);
								
								tbamt=roundTwoDecimals(totalXMileRate.multiply(kms, MathContext.UNLIMITED));
								tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
								tot=tot.add(tbamt);
								
								if(b==(driverList.size()-1)){
									if(tbamt.equals(tot)==false){
										java.math.BigDecimal totDiff=roundTwoDecimals(totalXMileRate.subtract(tot));
										tbamt=roundTwoDecimals(tbamt.add(totDiff));
										tot=roundTwoDecimals(tot.add(totDiff));
									}
								}
								
								//System.out.println("kilo meters : " + roundTwoDecimals(kms) + "    total per day: " + tbamt);
								farfdtf.setKm(tmp.getCixsmileage());
								farfdtf.setDays(new java.math.BigDecimal(0));
								farfdtf.setTbamt(tbamt);
								
								farfdtfs.add(farfdtf);
							}
							System.out.println("total excess mileage value: " + tot);
						}
					}
					
					//EXCESS HOURS
					if(tmp.getXhours()>0 && tmp.getHiretypeid().equals("WEDDING")==true){
						
						//Per Day Discount Amount
						pdisamt=calcDisAmt(tmp.getXhourrate(), tmp.getDiscountxhourrate());
						//Per Day Before Tax Amount (Rate - Discount)
						pbtamt=roundTwoDecimals(tmp.getXhourrate().subtract(pdisamt));
						//Calculate Additional Charges
						pbtamt=roundTwoDecimals(calcAddCharges(addChargeList, pbtamt));
						java.math.BigDecimal totalXHourRate=pbtamt.multiply(new java.math.BigDecimal(tmp.getXhours()));
						tbamt=new java.math.BigDecimal(0);
						
						asoObj=new ASObject();
						asoObj=setHedVal(tmp, asoObj);
						des="Excess Hours\r";
						des+="[Rs."+ tmp.getXhourrate() +"/- Per Hour";
						if(tmp.getDiscountxhourrate().compareTo(new java.math.BigDecimal(0))==0){ //Discount Not Applicable 
							
						}else if(tmp.getDiscountxhourrate().compareTo(new java.math.BigDecimal(0))>0){ //Discount Applicable 
							des+=" - "+tmp.getDiscountxhourrate()+"% Discount";
						}
						des+=addChargeDes; //Additional Charges
						//following code add  "+ VAT 11.00%" portion for description in following description part
						//description=Mercedes Benz BENZ C180 [Rs.19500.00/- Per Day - 10.0000%  Discount + VAT 11.00%]
						//Following code written by sanka 2015.09.14
						if(!isTaxInvoice(tmp.getVattyp())){
							des= setTaxDetails(taxdets,des);
						}
						//Following code commented by sanka 2015.09.14
/*						for(int b=0;b<taxdets.size();b++){//Tax Details
							des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
						}*/
						des+="]\r";
						des+="Alloted. Hours = "+tmp.getAllotedhours()+"\r";
						des+="Exce. Hours = "+tmp.getXhours()+"\r";
						asoObj.put("description", des);
						asoObj.put("rate", pbtamt);
						asoObj.put("km", 0);
						asoObj.put("days", tmp.getXhours());
						asoObj.put("type","EH");
						invDet.add(asoObj);
						java.math.BigDecimal kms;
						
						List<Fresdriver> driverList=getDriverList(tmp.getResno(), false,"");
						driverList=splitVehicleRates(driverList,tmp.getNoofday()); //Split Vehicle Rates
						
						java.math.BigDecimal tot=new java.math.BigDecimal(0);
						for(int b=0;b<driverList.size();b++){
							farfdtf=new Farfdtf();
							farfdtf=setFarfdtfHedVal2(freshed, tmp, farfdtf);
							farfdtf.setEmpid(driverList.get(b).getId().getEmpid());
							farfdtf.setType("EH");
							
							if(driverList.get(b).getDays()==null)
								kms=roundTwoDecimals(new java.math.BigDecimal(driverList.get(b).getPriority()));
							else 
								kms=roundTwoDecimals(driverList.get(b).getDays());
							
							farfdtf.setPgamt(tmp.getXhourrate());
							farfdtf.setDisper(tmp.getDiscountxhourrate());
							farfdtf.setPdisamt(pdisamt);
							farfdtf.setPbtamt(pbtamt);
							
							tbamt=roundTwoDecimals(totalXHourRate.multiply(kms, MathContext.UNLIMITED));
							tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
							tot=tot.add(tbamt);
							
							if(b==(driverList.size()-1)){
								if(tbamt.equals(tot)==false){
									java.math.BigDecimal totDiff=roundTwoDecimals(totalXHourRate.subtract(tot));
									tbamt=roundTwoDecimals(tbamt.add(totDiff));
									tot=roundTwoDecimals(tot.add(totDiff));
								}
							}
							
							//System.out.println("kilo meters : " + roundTwoDecimals(kms) + "    total per day: " + tbamt);
							farfdtf.setKm(0);
							farfdtf.setDays(new java.math.BigDecimal(tmp.getXhours()));
							farfdtf.setTbamt(tbamt);
							
							farfdtfs.add(farfdtf);
						}
						System.out.println("total excess mileage value: " + tot);
					}
					
					//DETENTION 
					if(tmp.getCidetenhrs()>0){				
						
						//Per Day Discount Amount
						pdisamt=calcDisAmt(tmp.getDrotrate(), tmp.getDrdiscount_detention());
						//Per Day Before Tax Amount (Rate - Discount)
						pbtamt=roundTwoDecimals(tmp.getDrotrate().subtract(pdisamt));
						java.math.BigDecimal totalDetRate=pbtamt.multiply(new java.math.BigDecimal(tmp.getCidetenhrs()));
						tbamt=new java.math.BigDecimal(0);
						
						asoObj=new ASObject();
						asoObj=setHedVal(tmp, asoObj);
						des="Detention After 8 Hours\r";
						des+="[Rs."+ tmp.getDrotrate() +"/- Per Hour";
						if(tmp.getDrdiscount_detention().compareTo(new java.math.BigDecimal(0))==0){ //Discount Not Applicable 
							
						}else if(tmp.getDrdiscount_detention().compareTo(new java.math.BigDecimal(0))>0){ //Discount Applicable 
							des+=" - "+tmp.getDrdiscount_detention()+"% Discount";
						}
						//following code add  "+ VAT 11.00%" portion for description in following description part
						//description=Mercedes Benz BENZ C180 [Rs.19500.00/- Per Day - 10.0000%  Discount + VAT 11.00%]
						//Following code written by sanka 2015.09.14
						if(!isTaxInvoice(tmp.getVattyp())){
							des= setTaxDetails(taxdets,des);
						}
						//Following code commented by sanka 2015.09.14
/*						for(int b=0;b<taxdets.size();b++){//Tax Details
							des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
						}*/
						des+="]\r";
						asoObj.put("description", des);
						asoObj.put("rate", pbtamt);
						asoObj.put("km", 0);
						asoObj.put("days", tmp.getCidetenhrs());			
						asoObj.put("type","DT");
						invDet.add(asoObj);
						
						java.math.BigDecimal hrs;
						if(tmp.getHiretypeid().equals("SD")==true){
							farfdtf=new Farfdtf();
							farfdtf=setFarfdtfHedVal2(freshed, tmp, farfdtf);
							farfdtf.setEmpid("");
							farfdtf.setType("DT");
							
							hrs=roundTwoDecimals(new java.math.BigDecimal(tmp.getCidetenhrs()));
							
							farfdtf.setPgamt(tmp.getDrotrate());
							farfdtf.setDisper(tmp.getDrdiscount_detention());
							farfdtf.setPdisamt(pdisamt);
							farfdtf.setPbtamt(pbtamt);
							
							tbamt=roundTwoDecimals(pbtamt.multiply(hrs, MathContext.UNLIMITED));
							//tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
							farfdtf.setKm(0);
							farfdtf.setDays(new java.math.BigDecimal(tmp.getCidetenhrs()));
							farfdtf.setTbamt(tbamt);
							
							System.out.println("hrs: " + hrs + "     pbtamt: " + pbtamt + "     tbamt: " + tbamt + "    xsmilerate: " + tmp.getXsmilerate());
							
							farfdtfs.add(farfdtf);
						}else{//WD or WEDDING
							List<Fresdriver> driverList=getDriverList(tmp.getResno(), false,"");
							driverList=splitVehicleRates(driverList,tmp.getNoofday()); //Split Vehicle Rates
							
							java.math.BigDecimal tot=new java.math.BigDecimal(0);
							for(int b=0;b<driverList.size();b++){
								farfdtf=new Farfdtf();
								farfdtf=setFarfdtfHedVal2(freshed, tmp, farfdtf);
								farfdtf.setEmpid(driverList.get(b).getId().getEmpid());
								farfdtf.setType("DT");
								
								if(driverList.get(b).getDays()==null)
									hrs=roundTwoDecimals(new java.math.BigDecimal(driverList.get(b).getPriority()));
								else 
									hrs=roundTwoDecimals(driverList.get(b).getDays());
								
								farfdtf.setPgamt(tmp.getDrotrate());
								farfdtf.setDisper(tmp.getDrdiscount_detention());
								farfdtf.setPdisamt(pdisamt);
								farfdtf.setPbtamt(pbtamt);
								
								tbamt=roundTwoDecimals(totalDetRate.multiply(hrs, MathContext.UNLIMITED));
								tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
								tot=tot.add(tbamt);
								
								if(b==(driverList.size()-1)){
									if(tbamt.equals(tot)==false){
										java.math.BigDecimal totDiff=roundTwoDecimals(totalDetRate.subtract(tot));
										tbamt=roundTwoDecimals(tbamt.add(totDiff));
										tot=roundTwoDecimals(tot.add(totDiff));
									}
								}
								
								//System.out.println("detention hours : " + roundTwoDecimals(hrs) + "    total per day: " + tbamt);
								farfdtf.setKm(0);
								farfdtf.setDays(new java.math.BigDecimal(tmp.getCidetenhrs()));
								farfdtf.setTbamt(tbamt);
								
								farfdtfs.add(farfdtf);
							}
							System.out.println("total detention hours value: " + tot);
						}
					}
					
					//NIGHT OUT
					if(tmp.getCinightout()>0){
						
						//Per Day Discount Amount
						pdisamt=calcDisAmt(tmp.getDrnightoutrate(), tmp.getDrdiscount_nightout());
						//Per Day Before Tax Amount (Rate - Discount)
						pbtamt=roundTwoDecimals(tmp.getDrnightoutrate().subtract(pdisamt));
						java.math.BigDecimal totalNOutRate=pbtamt.multiply(new java.math.BigDecimal(tmp.getCinightout()));
						tbamt=new java.math.BigDecimal(0);
						
						asoObj=new ASObject();
						asoObj=setHedVal(tmp, asoObj);
						des="Night Out\r";
						des+="[Rs."+ tmp.getDrnightoutrate() +"/- Per Day";
						if(tmp.getDrdiscount_nightout().compareTo(new java.math.BigDecimal(0))==0){ //Discount Not Applicable 
							
						}else if(tmp.getDrdiscount_nightout().compareTo(new java.math.BigDecimal(0))>0){ //Discount Applicable 
							des+=" - "+tmp.getDrdiscount_nightout()+"% Discount";
						}
						//following code add  "+ VAT 11.00%" portion for description in following description part
						//description=Mercedes Benz BENZ C180 [Rs.19500.00/- Per Day - 10.0000%  Discount + VAT 11.00%]
						//Following code written by sanka 2015.09.14
						if(!isTaxInvoice(tmp.getVattyp())){
							des= setTaxDetails(taxdets,des);
						}
						//Following code commented by sanka 2015.09.14
/*						for(int b=0;b<taxdets.size();b++){//Tax Details
							des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
						}*/
						des+="]\r";
						asoObj.put("description", des);
						asoObj.put("rate", pbtamt);
						asoObj.put("km", 0);
						asoObj.put("days", tmp.getCinightout());
						asoObj.put("type","NO");
						invDet.add(asoObj);
						
						java.math.BigDecimal hrs;
						if(tmp.getHiretypeid().equals("SD")==true){
							farfdtf=new Farfdtf();
							farfdtf=setFarfdtfHedVal(freshed, tmp, farfdtf);
							farfdtf.setEmpid("");
							farfdtf.setType("NO");
							
							hrs=roundTwoDecimals(new java.math.BigDecimal(tmp.getCinightout()));
							
							farfdtf.setPgamt(tmp.getDrnightoutrate());
							farfdtf.setDisper(tmp.getDrdiscount_nightout());
							farfdtf.setPdisamt(pdisamt);
							farfdtf.setPbtamt(pbtamt);
							
							tbamt=roundTwoDecimals(pbtamt.multiply(hrs, MathContext.UNLIMITED));
							//tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
							farfdtf.setKm(0);
							farfdtf.setDays(new java.math.BigDecimal(tmp.getCinightout()));
							farfdtf.setTbamt(tbamt);
							
							System.out.println("hrs: " + hrs + "     pbtamt: " + pbtamt + "     tbamt: " + tbamt + "    xsmilerate: " + tmp.getXsmilerate());
							
							farfdtfs.add(farfdtf);
						}else{//WD or WEDDING
							List<Fresdriver> driverList=getDriverList(tmp.getResno(), false,"");
							driverList=splitVehicleRates(driverList,tmp.getNoofday()); //Split Vehicle Rates
							
							java.math.BigDecimal tot=new java.math.BigDecimal(0);
							for(int b=0;b<driverList.size();b++){
								farfdtf=new Farfdtf();
								farfdtf=setFarfdtfHedVal2(freshed, tmp, farfdtf);
								farfdtf.setEmpid(driverList.get(b).getId().getEmpid());
								farfdtf.setType("NO");
								
								if(driverList.get(b).getDays()==null)
									hrs=roundTwoDecimals(new java.math.BigDecimal(driverList.get(b).getPriority()));
								else 
									hrs=roundTwoDecimals(driverList.get(b).getDays());
								
								farfdtf.setPgamt(tmp.getDrnightoutrate());
								farfdtf.setDisper(tmp.getDrdiscount_nightout());
								farfdtf.setPdisamt(pdisamt);
								farfdtf.setPbtamt(pbtamt);
								
								tbamt=roundTwoDecimals(totalNOutRate.multiply(hrs, MathContext.UNLIMITED));
								tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
								tot=tot.add(tbamt);
								
								if(b==(driverList.size()-1)){
									if(tbamt.equals(tot)==false){
										java.math.BigDecimal totDiff=roundTwoDecimals(totalNOutRate.subtract(tot));
										tbamt=roundTwoDecimals(tbamt.add(totDiff));
										tot=roundTwoDecimals(tot.add(totDiff));
									}
								}
								
								//System.out.println("night out : " + roundTwoDecimals(hrs) + "    total per day: " + tbamt);
								farfdtf.setKm(0);
								farfdtf.setDays(new java.math.BigDecimal(tmp.getCinightout()));
								farfdtf.setTbamt(tbamt);
								
								farfdtfs.add(farfdtf);
							}
							System.out.println("total night out value: " + tot);
						}
					}
					
					//OTHER
					if(tmp.getCiother()==null)
						tmp.setCiother(new BigDecimal(0));
					
					if(tmp.getCiother().compareTo(new java.math.BigDecimal(0))>0){
						asoObj=new ASObject();
						asoObj=setHedVal(tmp, asoObj);
						des="Other Charges\r" + tmp.getOthernaration();
						asoObj.put("description", des);
						asoObj.put("rate", tmp.getCiother());
						asoObj.put("km", 0);
						asoObj.put("days", 0);
						asoObj.put("amount", tmp.getCiother());
						asoObj.put("type","OT");
						invDet.add(asoObj);
						
						farfdtf=new Farfdtf();
						farfdtf=setFarfdtfHedVal2(freshed, tmp, farfdtf);
						farfdtf.setEmpid("");
						farfdtf.setType("OT");
						
						farfdtf.setPgamt(tmp.getCiother());
						farfdtf.setDisper(new java.math.BigDecimal(0));
						farfdtf.setPdisamt(new java.math.BigDecimal(0));
						farfdtf.setPbtamt(tmp.getCiother());
						
						farfdtf.setKm(0);
						farfdtf.setDays(new java.math.BigDecimal(0));
						farfdtf.setTbamt(tmp.getCiother());
						farfdtf.setDtfamt(tmp.getCiother());
						farfdtf.setDtfbal(tmp.getCiother());
						farfdtf.setDtfgst(new java.math.BigDecimal(0));
						
						farfdtfs.add(farfdtf);
					}
					
					//FUEL DIFFERENCES
					if(tmp.getCifueldiff()==null)
						tmp.setCifueldiff(new java.math.BigDecimal(0));
					if(tmp.getCifueldiff().compareTo(new java.math.BigDecimal(0))>0 || tmp.getCifueldiff().compareTo(new java.math.BigDecimal(0))<0){
						asoObj=new ASObject();
						asoObj=setHedVal(tmp, asoObj);
						des="Fuel Difference";
						asoObj.put("description", des);
						asoObj.put("rate", tmp.getCifueldiff());
						asoObj.put("km", 0);
						asoObj.put("days", 0);
						asoObj.put("amount", tmp.getCifueldiff());
						asoObj.put("type","FD");
						invDet.add(asoObj);
						
						farfdtf=new Farfdtf();
						farfdtf=setFarfdtfHedVal2(freshed, tmp, farfdtf);
						farfdtf.setEmpid("");
						farfdtf.setType("FD");
						
						farfdtf.setPgamt(tmp.getCifueldiff());
						farfdtf.setDisper(new java.math.BigDecimal(0));
						farfdtf.setPdisamt(new java.math.BigDecimal(0));
						farfdtf.setPbtamt(tmp.getCifueldiff());
						
						farfdtf.setKm(0);
						farfdtf.setDays(new java.math.BigDecimal(0));
						farfdtf.setTbamt(tmp.getCifueldiff());
						farfdtf.setDtfamt(tmp.getCifueldiff());
						farfdtf.setDtfbal(tmp.getCifueldiff());
						farfdtf.setDtfgst(new java.math.BigDecimal(0));
						
						farfdtfs.add(farfdtf);
					}
					
					//DAMAGES
					if(tmp.getCidamagers()==null)
						tmp.setCidamagers(new java.math.BigDecimal(0));
						
					if(tmp.getCidamagers().compareTo(new java.math.BigDecimal(0))>0 || tmp.getCidamagers().compareTo(new java.math.BigDecimal(0))<0){//edited by sanka || tmp.getCidamagers().compareTo(new java.math.BigDecimal(0))<0
						asoObj=new ASObject();
						asoObj=setHedVal(tmp, asoObj);
						des="Damages";
						asoObj.put("description", des);
						asoObj.put("rate", tmp.getCidamagers());
						asoObj.put("km", 0);
						asoObj.put("days", 0);
						asoObj.put("amount", tmp.getCidamagers());
						asoObj.put("type","DM");
						invDet.add(asoObj);
						
						farfdtf=new Farfdtf();
						farfdtf=setFarfdtfHedVal2(freshed, tmp, farfdtf);
						farfdtf.setEmpid("");
						farfdtf.setType("DM");
						
						farfdtf.setPgamt(tmp.getCidamagers());
						farfdtf.setDisper(new java.math.BigDecimal(0));
						farfdtf.setPdisamt(new java.math.BigDecimal(0));
						farfdtf.setPbtamt(tmp.getCidamagers());
						
						farfdtf.setKm(0);
						farfdtf.setDays(new java.math.BigDecimal(0));
						farfdtf.setTbamt(tmp.getCidamagers());
						farfdtf.setDtfamt(tmp.getCidamagers());
						farfdtf.setDtfbal(tmp.getCidamagers());
						farfdtf.setDtfgst(new java.math.BigDecimal(0));
						
						farfdtfs.add(farfdtf);
					}
					
					//ACCESSORY
					List<Fresaccs> fresaccsList=getAccessoryList(tmp.getResno());
					if(fresaccsList.size()>0){
						for(int t=0;t<fresaccsList.size();t++){
							Fresaccs fresaccs=fresaccsList.get(t);
							
							Date calDfrom =fresaccs.getId().getDfrom().getTime();
							Date calDto=fresaccs.getId().getDto().getTime();
							//TODO CHECK DATE RANGE
							//fromDate,toDate
							long x=calDfrom.getTime() - fromDate.getTime();//x>0 resacc start with invoice date range
							long y=toDate.getTime() - calDfrom.getTime();//y>0 resacc end with invoice date range
							//long y=toDate.getTime() - calDto.getTime();//y>0 resacc end with invoice date range
							if(x>=0 && y>=0)//fresacc within invoice date range
							{
								//valid
							}
							else
							{
								continue;
							}
							
							//Per Day Discount Amount
							pdisamt=calcDisAmt(fresaccs.getRate(), fresaccs.getDiscount());
							//Per Day Before Tax Amount (Rate - Discount)
							//pbtamt=roundTwoDecimals(fresaccs.getRate().subtract(pdisamt));
							pbtamt=fresaccs.getRate().subtract(pdisamt);
							//java.math.BigDecimal totalAccRate=roundTwoDecimals(pbtamt.multiply(new java.math.BigDecimal(fresaccs.getQty())));
							tbamt=new java.math.BigDecimal(0);
							
							asoObj=new ASObject();
							asoObj=setHedVal(tmp, asoObj);
							des=fresaccs.getDescription()+"\r";
							//des+="[Rs."+ fresaccs.getRate() +"/- Per Item";
							des+="[Rs."+ fresaccs.getStandardrate() +"/- " + formatRateDuration(fresaccs.getRatetype());
							if(fresaccs.getDiscount().compareTo(new java.math.BigDecimal(0))==0){ //Discount Not Applicable 
								
							}else if(fresaccs.getDiscount().compareTo(new java.math.BigDecimal(0))>0){ //Discount Applicable 
								des+=" - "+fresaccs.getDiscount()+"% Discount";
							}
							//following code add  "+ VAT 11.00%" portion for description in following description part
							//description=Mercedes Benz BENZ C180 [Rs.19500.00/- Per Day - 10.0000%  Discount + VAT 11.00%]
							//Following code written by sanka 2015.09.14
							if(!isTaxInvoice(tmp.getVattyp())){
								des= setTaxDetails(taxdets,des);
							}
							//Following code commented by sanka 2015.09.14
/*							for(int b=0;b<taxdets.size();b++){//Tax Details
								des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
							}*/
							des+="]\r";
							asoObj.put("description", des);
							asoObj.put("rate", pbtamt);
							asoObj.put("km", 0);
							asoObj.put("days", fresaccs.getNoofdays());
							asoObj.put("qty", fresaccs.getQty());
							asoObj.put("type","AC");
							asoObj.put("subseq", (t+1));
							invDet.add(asoObj);
							
							farfdtf=new Farfdtf();
							farfdtf=setFarfdtfHedVal2(freshed, tmp, farfdtf);
							farfdtf.setEmpid("");
							farfdtf.setType("AC");
							
							farfdtf.setPgamt(fresaccs.getRate());
							farfdtf.setDisper(fresaccs.getDiscount());
							farfdtf.setPdisamt(pdisamt);
							farfdtf.setPbtamt(pbtamt);
							
							//tbamt=roundTwoDecimals(pbtamt.multiply(new BigDecimal(fresaccs.getNoofdays()), MathContext.UNLIMITED));
							//tbamt=roundTwoDecimals(tbamt.multiply(new BigDecimal(fresaccs.getQty()), MathContext.UNLIMITED));
							tbamt=roundTwoDecimals(pbtamt.multiply(new BigDecimal(fresaccs.getNoofdays()), MathContext.UNLIMITED));
							tbamt=roundTwoDecimals(tbamt.multiply(new BigDecimal(fresaccs.getQty()), MathContext.UNLIMITED));
							
							//tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(fresaccs.getNoofdays()), RoundingMode.HALF_UP));
							java.math.BigDecimal tot=new java.math.BigDecimal(0);
							tot=tot.add(tbamt);
							
							
							System.out.println("\r");
							System.out.println("rate: " + fresaccs.getRate() + "     discount per: " + fresaccs.getDiscount() + "     pdisamt: " + pdisamt + "    pbtmt: " + pbtamt + "    tbamt: " + tbamt);
							System.out.println("\r");
							
							farfdtf.setSubseq((t+1));
							farfdtf.setKm(0);
							farfdtf.setDays(new java.math.BigDecimal(fresaccs.getNoofdays()));
							farfdtf.setQty(fresaccs.getQty());
							farfdtf.setTbamt(tbamt);
							farfdtfs.add(farfdtf);
						}
					}
					
					//OTHER SERVICES
					List<Fresothsrv> fresothsrvList=getOtherServiceList(tmp.getResno());				
					if(fresothsrvList.size()>0){
						Fresothsrv fresothsrv;
						for(int m=0;m<fresothsrvList.size();m++){
							fresothsrv=fresothsrvList.get(m);
							
							Date calDfrom =fresothsrv.getId().getDfrom().getTime();
							Date calDto=fresothsrv.getId().getDto().getTime();
							//TODO CHECK DATE RANGE
							//fromDate,toDate
							long x=calDfrom.getTime() - fromDate.getTime();//x>0 resacc start with invoice date range
							long y=toDate.getTime() - calDfrom.getTime();//y>0 resacc end with invoice date range
							//long y=toDate.getTime() - calDto.getTime();//y>0 resacc end with invoice date range
							if(x>=0 && y>=0)//fresacc within invoice date range
							{
								//valid
							}
							else
							{
								continue;
							}
							
							//Per Day Discount Amount
							pdisamt=calcDisAmt(fresothsrv.getRate(), fresothsrv.getDiscount());
							//Per Day Before Tax Amount (Rate - Discount)
							//pbtamt=roundTwoDecimals(fresothsrv.getRate().subtract(pdisamt));
							pbtamt=fresothsrv.getRate().subtract(pdisamt);
							java.math.BigDecimal totalOthSrvRate=pbtamt.multiply(new java.math.BigDecimal(fresothsrv.getNoofdays()));
							tbamt=new java.math.BigDecimal(0);
							
							asoObj=new ASObject();
							asoObj=setHedVal(tmp, asoObj);
							des=fresothsrv.getDescription()+"\r";
							des+="[Rs."+ fresothsrv.getStandardrate() +"/- " + formatRateDuration(fresothsrv.getRatetype());
							if(fresothsrv.getDiscount().compareTo(new java.math.BigDecimal(0))==0){ //Discount Not Applicable 
								
							}else if(fresothsrv.getDiscount().compareTo(new java.math.BigDecimal(0))>0){ //Discount Applicable 
								des+=" - "+fresothsrv.getDiscount()+"% Discount";
							}
							//following code add  "+ VAT 11.00%" portion for description in following description part
							//description=Mercedes Benz BENZ C180 [Rs.19500.00/- Per Day - 10.0000%  Discount + VAT 11.00%]
							//Following code written by sanka 2015.09.14
							if(!isTaxInvoice(tmp.getVattyp())){
								des= setTaxDetails(taxdets,des);
							}
							//Following code commented by sanka 2015.09.14
/*							for(int b=0;b<taxdets.size();b++){//Tax Details
								des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
							}*/
							des+="]";
							asoObj.put("description", des);
							asoObj.put("rate", pbtamt);
							asoObj.put("km", 0);
							asoObj.put("days", fresothsrv.getNoofdays());
							asoObj.put("type","OS");
							asoObj.put("subseq", (m+1));
							invDet.add(asoObj);
							java.math.BigDecimal days;
							
							List<Fresdriver> driverList=getDriverList(fresothsrv.getId().getResno(), true, fresothsrv.getId().getSrvid());
							driverList=splitVehicleRates(driverList,fresothsrv.getNoofdays()); //Split Vehicle Rates
							
							java.math.BigDecimal tot=new java.math.BigDecimal(0);
							for(int b=0;b<driverList.size();b++){
								farfdtf=new Farfdtf();
								farfdtf=setFarfdtfHedVal2(freshed, tmp, farfdtf);
								farfdtf.setEmpid(driverList.get(b).getId().getEmpid());
								farfdtf.setType("OS");
								
								if(driverList.get(b).getDays()==null)
									days=roundTwoDecimals(new java.math.BigDecimal(driverList.get(b).getPriority()));
								else 
									days=roundTwoDecimals(driverList.get(b).getDays());
								
								farfdtf.setPgamt(fresothsrv.getRate());
								farfdtf.setDisper(fresothsrv.getDiscount());
								farfdtf.setPdisamt(pdisamt);
								farfdtf.setPbtamt(pbtamt);
								
//							tbamt=roundTwoDecimals(totalOthSrvRate.multiply(days, MathContext.UNLIMITED));
//							tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(fresothsrv.getNoofdays()), RoundingMode.HALF_UP));
								tbamt=totalOthSrvRate.multiply(days, MathContext.UNLIMITED);
								tbamt=tbamt.divide(new java.math.BigDecimal(fresothsrv.getNoofdays()), RoundingMode.HALF_UP);
								tot=tot.add(tbamt);
								
								if(b==(driverList.size()-1)){
									if(tbamt.equals(tot)==false){
										java.math.BigDecimal totDiff=roundTwoDecimals(totalOthSrvRate.subtract(tot));
										tbamt=roundTwoDecimals(tbamt.add(totDiff));
										tot=roundTwoDecimals(tot.add(totDiff));
									}
								}
								
								//System.out.println("kilo meters : " + roundTwoDecimals(kms) + "    total per day: " + tbamt);
								farfdtf.setSubseq((m+1));
								farfdtf.setKm(0);
								farfdtf.setDays(new java.math.BigDecimal(fresothsrv.getNoofdays()));
								farfdtf.setTbamt(tbamt);
								
								farfdtfs.add(farfdtf);
							}
							System.out.println("total other service ["+fresothsrv.getDescription()+"] value: " + tot);
						}
					}
				}
				
				ArrayCollection tmpAC=calcTax(farfdtfs);
				if(tmpAC==null)
					return null;
				
				farfdtfs=(List<Farfdtf>)tmpAC.get(0);
				List<Fdtftax> fdtftaxs=(List<Fdtftax>)tmpAC.get(1);
				
				//Calculate Invoice Detail Tax 
				invDet=calcInvDetTax(invDet);
				
				
				System.out.println("*******************\r\r");
				//System.out.println("invDet legth: " + invDet.size() + "       farfdtfs length: " + farfdtfs.size());
				System.out.println("\r");
				//Tally Differences
				aso=null;
				for(int x=0;x<invDet.size();x++){
					aso=(ASObject)invDet.get(x);
					String resno=aso.get("resno").toString();
					String type=aso.get("type").toString();
					BigDecimal amount=roundTwoDecimals(new BigDecimal(aso.get("amount").toString()));
					BigDecimal taxval=roundTwoDecimals(new BigDecimal(aso.get("taxval").toString()));
					int subSeq=Integer.parseInt(aso.get("subseq").toString());
					BigDecimal runTotal=new BigDecimal(0);
					BigDecimal runTax=new BigDecimal(0);
					int index=-1;
					Farfdtf fdf=new Farfdtf();
					for(int y=0;y<farfdtfs.size();y++){
						fdf=farfdtfs.get(y);
						System.out.println("fdf.getSubseq() :"+fdf.getSubseq());
						System.out.println("subSeq :"+subSeq);
						if(fdf.getResno().equals(resno)==true && fdf.getType().equals(type)==true && fdf.getSubseq()==subSeq){
							runTotal=roundTwoDecimals(runTotal.add(fdf.getDtfamt()));
							runTax=roundTwoDecimals(runTax.add(fdf.getDtfgst()));
							index=y;
							System.out.println("index :"+index);
						}
					}
					if(amount.equals(runTotal)==false && index!=-1){
						BigDecimal diff=new BigDecimal(0);
						diff=roundTwoDecimals(amount.subtract(runTotal));
						if(diff.equals(new BigDecimal(0))==false){
							BigDecimal dtfamt=new BigDecimal(0);
							if (farfdtfs != null && farfdtfs.size() - 1 >= index) {
								dtfamt=roundTwoDecimals(farfdtfs.get(index).getDtfamt());
							} else {
								System.out.println("error handled and ignored");
							}
							
							//System.out.println("type: " + type + "     amount: " + amount + "   runtotal: " + runTotal + "   diff: " + diff + "    dtfamt: " + dtfamt + " index: " + index);
							dtfamt=roundTwoDecimals(dtfamt.add(diff));
							fdf.setDtfamt(dtfamt);
							fdf.setDtfbal(dtfamt);
							farfdtfs.get(index).setDtfamt(dtfamt);
							farfdtfs.get(index).setDtfbal(dtfamt);
							
						}
					}
					if(taxval.equals(runTax)==false && index!=-1){
						BigDecimal diff=new BigDecimal(0);
						diff=roundTwoDecimals(taxval.subtract(runTax));
						if(diff.equals(new BigDecimal(0))==false){
							
							BigDecimal dtfgst=new BigDecimal(0);
							if (farfdtfs != null && farfdtfs.size() - 1 >= index) {
								dtfgst=roundTwoDecimals(farfdtfs.get(index).getDtfgst());
							} else {
								System.out.println("error handled and ignored");
							}
							
							
							//BigDecimal dtfgst=roundTwoDecimals(farfdtfs.get(index).getDtfgst());
							//System.out.println("type: " + type + "     taxval: " + taxval + "   runtax: " + runTax + "   diff: " + diff + "    dtfgst: " + dtfgst + " index: " + index + "diff: " + diff + "\r");
							dtfgst=roundTwoDecimals(dtfgst.add(diff));
							fdf.setDtfgst(dtfgst);
							farfdtfs.get(index).setDtfgst(dtfgst);
							
							//Update Tax Details With Differences 
							index=0;
							BigDecimal runTaxAmt=new BigDecimal(0);
							for(int z=0;z<fdtftaxs.size();z++){
								Fdtftax fdtftax=fdtftaxs.get(z);
								if(fdtftax.getResno().equals(resno)==true && fdtftax.getType().equals(type)==true && fdtftax.getSubseq()==subSeq){
									runTaxAmt=roundTwoDecimals(runTaxAmt.add(fdtftax.getTaxamt()));
									index=z;
								}
							}
							if(taxval.equals(runTaxAmt)==false && index!=-1){
								diff=new BigDecimal(0);
								diff=roundTwoDecimals(taxval.subtract(runTaxAmt));
								BigDecimal taxamt=roundTwoDecimals(fdtftaxs.get(index).getTaxamt());
								//System.out.println("resno: " +  resno + "    subseq: " + subSeq + "    type: " + type + "     taxval: " + taxval + "   runTaxAmt: " + runTaxAmt + "   diff: " + diff + "   index: " + index + "\r");
								taxamt=roundTwoDecimals(taxamt.add(diff));
								fdtftaxs.get(index).setTaxamt(taxamt);
							}
						}
					}				
				}
				System.out.println("\r\r*******************");
				

				ArrayCollection ac=new ArrayCollection();
				ac.add(invDet);
				ac.add(farfdtfs);
				ac.add(fdtftaxs);
				
				return ac;
			} catch (Exception e) {
				System.out.println("FarfdtfDAOImpl genInvoice2: " + e.getMessage());
				System.out.println("sanka#############################################################################");
				e.printStackTrace();
				System.out.println("sanka#############################################################################");
				throw new RuntimeException(e);
			}
			finally{em.flush();}
			//return null;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		@Transactional
		@Override
		public String saveInvoice2(Finvhed finvhed,ArrayCollection acInvDet,List<Farfdtf> farfdtfList,List<Fdtftax> fdtftaxList) {
			try {
				//Finvhed finvhed=(Finvhed)invCol.get(0); //Invoice Hed 
				//ArrayCollection acInvDet=(ArrayCollection)invCol.get(1); //Invoice Detail
				//List<Farfdtf> farfdtfsList=(List<Farfdtf>)invCol.get(2); //Farfdtf
				//List<Fdtftax> fdtftaxsList=(List<Fdtftax>)invCol.get(3); //Fdtftax
				em.flush();
				ArrayList distResList=new ArrayList();
				for(int g=0;g<acInvDet.size();g++){
					ASObject detAso=(ASObject)acInvDet.get(g);
					String rno=detAso.get("resno").toString(); //reservation no
					Boolean sFound=false;
					for(int h=0;h<distResList.size();h++){
						detAso=(ASObject)distResList.get(h);
						if(detAso.get("resno").equals(rno)==true)
							sFound=true;
					}
					if(sFound==false){
						detAso=new ASObject();
						detAso.put("resno", rno);
						distResList.add(detAso);
					}
				}
				
				String paraList="";
				for(int xx=0;xx<distResList.size();xx++){
					ASObject detAso=(ASObject)distResList.get(xx);
					paraList="'"+detAso.get("resno").toString()+"'";
					if(xx<(distResList.size()-1))
						paraList+=",";
				}
				
				String chkDupLstQuery="SELECT COUNT(*) FROM farfdtf WHERE resno IN ("+paraList+") AND dtftype='p'";
				System.out.println("chkDupLstQuery: " + chkDupLstQuery);
				
				int rptCnt=Integer.parseInt(em.createNativeQuery(chkDupLstQuery).getSingleResult().toString());
				if(rptCnt>0){
					return "Invoice Has Been Already Raised For Selected Reservation(s)";
				}		
				
				Calendar curDate = Calendar.getInstance();
				String txnTime=curDate.get(Calendar.HOUR) + ":" + curDate.get(Calendar.MINUTE);
				if(curDate.get(Calendar.AM_PM)==0)
					txnTime+=" AM";
				else 
					txnTime+=" PM";
				curDate=fmaintenanceDAO.resetTime(curDate);
				String refNo=fcompanysettingDAO.getRefNo("PIN", curDate.get(Calendar.YEAR), ((curDate.get(Calendar.MONTH))+1));
				
				//Persist Invoice Hed
				finvhed.setTxndate(curDate);
				finvhed.setInvno(refNo);
				finvhed.setAdduser(userInfoSRV.getUser());
				finvhed.setAddmach(userInfoSRV.getMachineName());
				finvhed.setAdddate(curDate);
				em.persist(finvhed);
				em.flush();
				System.out.println("persist invoice hed");
				
				//Persists Invoice Detail
				Finvdet finvdet;
				FinvdetPK pk;
				for(int a=0;a<acInvDet.size();a++){
					ASObject aso=(ASObject)acInvDet.get(a);
					pk=new FinvdetPK();
					finvdet=new Finvdet();
					
					pk.setInvno(refNo);
					pk.setSeq(Integer.parseInt(aso.get("seq").toString()));
					finvdet.setId(pk);
					
					finvdet.setResno(aso.get("resno").toString());
					finvdet.setHiretypeid(aso.get("hiretypeid").toString());
					
					java.util.Date dtFrom=(java.util.Date)aso.get("dfrom");
					Calendar calDFrom=Calendar.getInstance();
					calDFrom.setTime(dtFrom);
					finvdet.setDfrom(calDFrom);
					
					java.util.Date dtTo=(java.util.Date)aso.get("dto");
					Calendar calDTo=Calendar.getInstance();
					calDTo.setTime(dtTo);
					finvdet.setDto(calDTo);
					System.out.println("\n\n\n\n\n=============================\ninvoice_description: "+aso.get("description").toString());
					finvdet.setDescription(aso.get("description").toString());
					finvdet.setRegno(aso.get("regno").toString());
					finvdet.setRate(new BigDecimal(aso.get("rate").toString()));
					finvdet.setKm(new BigDecimal(aso.get("km").toString()));
					finvdet.setDays(new BigDecimal(aso.get("days").toString()));
					finvdet.setQty(Integer.parseInt(aso.get("qty").toString()));
					finvdet.setAmount(new BigDecimal(aso.get("amount").toString()));
					finvdet.setTaxcomcode(aso.get("taxcomcode").toString());
					finvdet.setType(aso.get("type").toString());
					finvdet.setSubseq(Integer.parseInt(aso.get("subseq").toString()));
					finvdet.setAdduser(userInfoSRV.getUser());
					finvdet.setAddmach(userInfoSRV.getMachineName());
					finvdet.setAdddate(curDate);
					
					em.persist(finvdet);
					em.flush();
				}
				System.out.println("persist invoice det");
				
				
				//Persist fdtftax
				for(int d=0;d<fdtftaxList.size();d++){
					Fdtftax tax=fdtftaxList.get(d);
					tax.getId().setDtfref(refNo);
					em.persist(tax);
					em.flush();				
				}
				
				//Update Cohirestsid To Invoice [Invoice Raised]
/*				for(int a=0;a<distResList.size();a++){
					ASObject detAso=(ASObject)distResList.get(a);
					Freservation freservation=em.find(Freservation.class, detAso.get("resno").toString());
					freservation.setCohirestsid("INVOICE");
					em.merge(freservation);
					em.flush();
				}*/
				
				return refNo;
			} catch (Exception e) {
				System.out.println("FarfdtfDAOImpl saveInvoice: " + e.getMessage());
				e.printStackTrace();
			}
			finally{em.flush();}
			return "";
		}
		
		
		
		
		
	private ArrayCollection calcTax(List<Farfdtf> farfdtfs){
		try{
			em.flush();
			List<Ftaxdet> taxdets;
			List<Fdtftax> fdtftaxs=new ArrayList<Fdtftax>();
			Fdtftax fdtftax;
			FdtftaxPK fdtftaxPK;

			String taxcomcode;
			String taxcode;
			String taxname;
			java.math.BigDecimal taxper;
			java.math.BigDecimal taxrate;
			java.math.BigDecimal taxseq;
			
			java.math.BigDecimal lnTaxVal;
			java.math.BigDecimal indTaxVal;
			java.math.BigDecimal total;
			
			for(int a=0;a<farfdtfs.size();a++){
				Farfdtf fdf=farfdtfs.get(a);
				
				if(fdf.getType().equals("DM")==false && fdf.getType().equals("OT")==false && fdf.getType().equals("FD")==false){
					taxdets=loadTaxDet(fdf.getTaxcomcode());					
					lnTaxVal=new java.math.BigDecimal(0);
					
					for(int b=0;b<taxdets.size();b++){
						Ftaxdet det=taxdets.get(b);
						FtaxdetPK pk=det.getId();
						
						taxcomcode=pk.getTaxcomcode();
						taxcode=pk.getTaxcode();
						taxname=det.getTaxname();
						taxper=det.getTaxper();
						taxrate=det.getTaxrate();
						taxseq=det.getTaxseq();
	
						indTaxVal=new java.math.BigDecimal(0);	
						indTaxVal=taxrate;
						indTaxVal=roundTwoDecimals(indTaxVal.multiply(fdf.getTbamt()));
						indTaxVal=roundTwoDecimals(indTaxVal.divide(new java.math.BigDecimal(100)));
						
						lnTaxVal=roundTwoDecimals(lnTaxVal.add(indTaxVal)); //Add Individual Tax Value
						
						fdtftax=new Fdtftax();
						fdtftaxPK=new FdtftaxPK();
						
						fdtftaxPK.setDtfref(fdf.getId().getDtfref());
						fdtftaxPK.setSeq(fdf.getId().getSeq());
						fdtftaxPK.setTaxcode(taxcode);
						fdtftax.setId(fdtftaxPK);
						
						fdtftax.setTaxcomcode(taxcomcode);
						//fdtftax.setTaxcode(taxcode);
						fdtftax.setTaxper(taxper);
						fdtftax.setTaxrate(taxrate);
						fdtftax.setTaxseq(Integer.parseInt(taxseq.toString()));
						fdtftax.setTaxamt(indTaxVal);
						
						fdtftax.setResno(fdf.getResno());
						fdtftax.setType(fdf.getType());
						fdtftax.setSubseq(fdf.getSubseq());
						
						fdtftaxs.add(fdtftax);
					}
					fdf.setDtfgst(lnTaxVal);
					total=new java.math.BigDecimal(0);
					total=roundTwoDecimals(fdf.getDtfgst().add(fdf.getTbamt()));
					fdf.setDtfamt(total);
					fdf.setDtfbal(total);
					
					farfdtfs.remove(a);
					farfdtfs.add(a, fdf);
				}
			}
			
			ArrayCollection ac=new ArrayCollection();
			ac.add(farfdtfs); //Add farfdtfs List to Array Collection
			ac.add(fdtftaxs); //Add fdtftaxs List to Array Collection
			return ac;
		}catch(Exception e){
			System.out.println("calTax: " + e.getMessage());
		}
		finally{em.flush();}
		return null;
	}
	
	private ArrayCollection calcInvDetTax(ArrayCollection invDet){
		try{
			em.flush();
			List<Ftaxdet> taxdets;
			List<Fdtftax> fdtftaxs=new ArrayList<Fdtftax>();
			Fdtftax fdtftax;
			FdtftaxPK fdtftaxPK;

			String taxcomcode;
			String taxcode;
			String taxname;
			java.math.BigDecimal taxper;
			java.math.BigDecimal taxrate;
			java.math.BigDecimal taxseq;

			java.math.BigDecimal asoLnTaxVal;
			java.math.BigDecimal asoIndTaxVal;
			java.math.BigDecimal asoTotal;
			java.math.BigDecimal asoRate;
			java.math.BigDecimal multiVal;
			
			
			for(int a=0;a<invDet.size();a++){
				ASObject aso=(ASObject)invDet.get(a);
				if(aso.get("type").equals("DM")==false && aso.get("type").equals("OT")==false && aso.get("type").equals("FD")==false){
					taxdets=loadTaxDet(aso.get("taxcomcode").toString());
					asoLnTaxVal=new java.math.BigDecimal(0);
					
					asoRate=new java.math.BigDecimal(aso.get("rate").toString());
					
					for(int b=0;b<taxdets.size();b++){
						Ftaxdet det=taxdets.get(b);
						FtaxdetPK pk=det.getId();
						
						taxcomcode=pk.getTaxcomcode();
						taxcode=pk.getTaxcode();
						taxname=det.getTaxname();
						taxper=det.getTaxper();
						taxrate=det.getTaxrate();
						taxseq=det.getTaxseq();
	
						asoIndTaxVal=new java.math.BigDecimal(0);	
						asoIndTaxVal=taxrate;
//						asoIndTaxVal=roundTwoDecimals(asoIndTaxVal.multiply(asoRate));
//						asoIndTaxVal=roundTwoDecimals(asoIndTaxVal.divide(new java.math.BigDecimal(100)));
						
//						asoLnTaxVal=roundTwoDecimals(asoLnTaxVal.add(asoIndTaxVal)); //Add Individual Tax Value
						
						asoIndTaxVal=asoIndTaxVal.multiply(asoRate);
						asoIndTaxVal=asoIndTaxVal.divide(new java.math.BigDecimal(100));
						
						asoLnTaxVal=asoLnTaxVal.add(asoIndTaxVal); //Add Individual Tax Value
					}
					asoLnTaxVal=roundTwoDecimals(asoLnTaxVal);
					asoTotal=new java.math.BigDecimal(0);
					asoTotal=roundFiveDecimals(asoRate.add(asoLnTaxVal));
					aso.put("rate", asoTotal);
					System.out.println("\r\rasoTotal: " + asoTotal);
					
					multiVal=new java.math.BigDecimal(0);
					System.out.println("km: " + aso.get("km").toString() + "     km length: " + aso.get("km").toString().length());
					
					multiVal=new java.math.BigDecimal(aso.get("km").toString());
					if(Integer.parseInt(aso.get("km").toString())==0)
						multiVal=new java.math.BigDecimal(aso.get("days").toString());
					
					if(multiVal.compareTo(new java.math.BigDecimal(0))!=0){
//						asoTotal=roundTwoDecimals(asoTotal.multiply(multiVal));
//						asoLnTaxVal=roundTwoDecimals(asoLnTaxVal.multiply(multiVal));
						asoTotal=roundFiveDecimals(asoTotal.multiply(multiVal));
						asoLnTaxVal=roundFiveDecimals(asoLnTaxVal.multiply(multiVal));
					}
					
					if(aso.get("type").equals("AC")==true){
						asoTotal=roundTwoDecimals(asoTotal.multiply(new BigDecimal(aso.get("qty").toString())));
						asoLnTaxVal=roundTwoDecimals(asoLnTaxVal.multiply(new BigDecimal(aso.get("qty").toString())));
					}
					aso.put("taxval", asoLnTaxVal);
					
					aso.put("amount", asoTotal);
					
					invDet.remove(a);
					invDet.add(a,aso);
				}
			}
			
			return invDet;
		}catch(Exception e){
			System.out.println("calcInvDetTax: " + e.getMessage());
		}
		finally{em.flush();}
		return null;
	}
	
	private String getRepeatingDateDet(String dout,String din,ArrayList<ASObject> rptDateList){
		try{
			em.flush();
			String val="";
			int isDout=0; //0 - Repeating   1 - Not Repeating
			int isDin=0;  //0 - Repeating   1 - Not Repeating
			String dOutDate="";
			String dInDate="";
			Calendar tmpDtCal=Calendar.getInstance();
			String tmpDtStr="";
			for(int a=0;a<rptDateList.size();a++){
				ASObject aso=rptDateList.get(a);
				tmpDtCal=(Calendar)aso.get("date");
				tmpDtStr=tmpDtCal.get(Calendar.YEAR)+"."+(tmpDtCal.get(Calendar.MONTH)+1)+"."+tmpDtCal.get(Calendar.DATE);
				if(isDout==0){
					if(dout.equals(tmpDtStr)==true){
						isDout=1;
						dOutDate=tmpDtStr;
					}
				}
				if(isDin==0){
					if(din.equals(tmpDtStr)==true){
						isDin=1;
						dInDate=tmpDtStr;
					}
				}
			}
			if(isDout==1 && isDin==1 && dOutDate.equals(dInDate)==true){
				val="both"; //same date for both dout and din
			}else{
				if(isDout==1){
					val="dout";
					return val;
				}
				if(isDin==1){
					val="din";
					return val;
				}
				if(isDout==0 && isDin==0)
					val="none";
			}
			return val;
		}catch(Exception e){
			System.out.println("getRepeatingDateDet: " + e.getMessage());
		}
		finally{em.flush();}
		return null;
	}
	
	private List<Fresdriver> splitVehicleRates(List<Fresdriver> driverList,int noofday){
		try{
			em.flush();
			ArrayList<ASObject> lstRptDates=getRepeatingDateList(driverList); //Repeating Date List			
			ArrayList<ASObject> lstRptNewDates=new ArrayList<ASObject>();
			Fresdriver resdriver;
			String dout;
			String din;
			double tmpDays=0.00;
			double rDateDiff;
			Double diffVal;
			Double doiDiff;
		    Double rCnt;
		    ASObject iniDateASO;  //Initial Repeating Date
		    ASObject secDateASO;  //Secondary Repeating Date
		    Double secDateVal;
		    Double iniDateVal;
		    
		    Double totalDays=0.00;
			Double days=0.00;
			for(int a=0;a<driverList.size();a++){			
				diffVal=1.00;
				iniDateASO=new ASObject();
				secDateASO=new ASObject();
				resdriver=driverList.get(a);
				doiDiff=new Double(resdriver.getPriority());
				//dout
				dout=resdriver.getId().getDout().get(Calendar.YEAR)+".";
				dout+=(resdriver.getId().getDout().get(Calendar.MONTH)+1)+".";
				dout+=resdriver.getId().getDout().get(Calendar.DATE);
				//din
				din=resdriver.getId().getDin().get(Calendar.YEAR)+".";
				din+=(resdriver.getId().getDin().get(Calendar.MONTH)+1)+".";
				din+=resdriver.getId().getDin().get(Calendar.DATE);
				
				String val=getRepeatingDateDet(dout, din, lstRptDates);
				if(val.equals("none")==true){
					resdriver.setDays(new java.math.BigDecimal(doiDiff));
				}else{ //dout or din or both
					if(val.equals("dout")==true || val.equals("both")==true){
						iniDateASO=getSecRptDateDet(dout, lstRptDates);
					}else if(val.equals("din")==true || val.equals("both")==true){
						iniDateASO=getSecRptDateDet(din, lstRptDates);
					}
					
					rCnt=new Double(iniDateASO.get("count").toString());
					
					tmpDays=0.00;
					secDateVal=0.00;
					iniDateVal=0.00;
					if(val.equals("both")==true){
						tmpDays=roundTwoDecimals(((doiDiff-diffVal)+(new Double("1")/rCnt)));
					}else{
						if(val.equals("dout")==true)
							secDateASO=getSecRptDateDet(din, lstRptDates);
						else if(val.equals("din")==true)
							secDateASO=getSecRptDateDet(dout, lstRptDates);
						
						iniDateVal=new Double("1")/rCnt;
						if(secDateASO!=null){
							secDateVal=(new Double("1")/new Double(secDateASO.get("count").toString()));
							diffVal+=1;
						}
						tmpDays=roundTwoDecimals((doiDiff-diffVal)+iniDateVal);
						tmpDays=roundTwoDecimals(tmpDays+secDateVal);
					}
					resdriver.setDays(new java.math.BigDecimal(tmpDays));
				}
				days=new Double(resdriver.getDays().toString());
				totalDays+=days;
				if(a==(driverList.size()-1)){
					if(totalDays<noofday){
						Double diff=roundTwoDecimals(noofday-totalDays);
						days=roundTwoDecimals(days+diff);
						resdriver.setDays(new java.math.BigDecimal(days));
					}
				}
				driverList.remove(a);
				driverList.add(a, resdriver);
				
//				System.out.println("***************************");
//				System.out.println("difference: " + resdriver.getPriority() + "      dout: " + dout + "     din: " + din);
//				System.out.println("***************************");
			}		
			return driverList;
		}catch(Exception e){
			System.out.println("splitVehicleRates: " + e.getMessage());
			e.printStackTrace();
		}
		finally{em.flush();}
		return null;
	}
	
	
	private List<Fresdriver> splitVehicleRates2(List<Fresdriver> driverList,int noofday){
		try{
			em.flush();
			ArrayList<ASObject> lstRptDates=getRepeatingDateList(driverList); //Repeating Date List			
			ArrayList<ASObject> lstRptNewDates=new ArrayList<ASObject>();
			Fresdriver resdriver;
			String dout;
			String din;
			double tmpDays=0.00;
			double rDateDiff;
			Double diffVal;
			Double doiDiff;
			Double rCnt;
			ASObject iniDateASO;  //Initial Repeating Date
			ASObject secDateASO;  //Secondary Repeating Date
			Double secDateVal;
			Double iniDateVal;
			
			Double totalDays=0.00;
			Double days=0.00;
			for(int a=0;a<driverList.size();a++){			
				diffVal=1.00;
				iniDateASO=new ASObject();
				secDateASO=new ASObject();
				resdriver=driverList.get(a);
				doiDiff=new Double(resdriver.getPriority());
				//dout
				dout=resdriver.getId().getDout().get(Calendar.YEAR)+".";
				dout+=(resdriver.getId().getDout().get(Calendar.MONTH)+1)+".";
				dout+=resdriver.getId().getDout().get(Calendar.DATE);
				//din
				din=resdriver.getId().getDin().get(Calendar.YEAR)+".";
				din+=(resdriver.getId().getDin().get(Calendar.MONTH)+1)+".";
				din+=resdriver.getId().getDin().get(Calendar.DATE);
				
				String val=getRepeatingDateDet(dout, din, lstRptDates);
				if(val.equals("none")==true){
					resdriver.setDays(new java.math.BigDecimal(doiDiff));
				}else{ //dout or din or both
					if(val.equals("dout")==true || val.equals("both")==true){
						iniDateASO=getSecRptDateDet(dout, lstRptDates);
					}else if(val.equals("din")==true || val.equals("both")==true){
						iniDateASO=getSecRptDateDet(din, lstRptDates);
					}
					
					rCnt=new Double(iniDateASO.get("count").toString());
					
					tmpDays=0.00;
					secDateVal=0.00;
					iniDateVal=0.00;
					if(val.equals("both")==true){
						tmpDays=roundTwoDecimals(((doiDiff-diffVal)+(new Double("1")/rCnt)));
					}else{
						if(val.equals("dout")==true)
							secDateASO=getSecRptDateDet(din, lstRptDates);
						else if(val.equals("din")==true)
							secDateASO=getSecRptDateDet(dout, lstRptDates);
						
						iniDateVal=new Double("1")/rCnt;
						if(secDateASO!=null){
							secDateVal=(new Double("1")/new Double(secDateASO.get("count").toString()));
							diffVal+=1;
						}
						tmpDays=roundTwoDecimals((doiDiff-diffVal)+iniDateVal);
						tmpDays=roundTwoDecimals(tmpDays+secDateVal);
					}
					resdriver.setDays(new java.math.BigDecimal(tmpDays));
				}
				days=new Double(resdriver.getDays().toString());
				totalDays+=days;
				if(a==(driverList.size()-1)){
					if(totalDays<noofday){
						Double diff=roundTwoDecimals(noofday-totalDays);
						days=roundTwoDecimals(days+diff);
						resdriver.setDays(new java.math.BigDecimal(days));
					}
				}
				driverList.remove(a);
				driverList.add(a, resdriver);
				
//				System.out.println("***************************");
//				System.out.println("difference: " + resdriver.getPriority() + "      dout: " + dout + "     din: " + din);
//				System.out.println("***************************");
			}		
			return driverList;
		}catch(Exception e){
			System.out.println("splitVehicleRates: " + e.getMessage());
			e.printStackTrace();
		}
		finally{em.flush();}
		return null;
	}
	
	
	
	//***************************************
//	private List<Fresdriver> splitVehicleRates(List<Fresdriver> driverList){
//		try{
//			ArrayList<ASObject> lstRptDates=getRepeatingDateList(driverList); //Repeating Date List
//			Calendar rDate;
//			double rDateDiff;
//			int tmpRCnt;
//		    Double rCnt;
//		    Double rRunCnt;
//		    Double tmpVal;
//			for(int x=0;x<lstRptDates.size();x++){
//				rDate=(Calendar)lstRptDates.get(x).get("date");
//				rCnt=Double.parseDouble(lstRptDates.get(x).get("count").toString());
//				rDateDiff=Double.parseDouble(lstRptDates.get(x).get("dateDiff").toString()); //Date Difference 
//				//System.out.println("count: " + rCnt);
//				rRunCnt=0.00;
//				tmpRCnt=0;
//				tmpVal=0.00;
//				ArrayList<ASObject> oLappingDateList=new ArrayList<ASObject>();
//				for(int y=0;y<driverList.size();y++){
//					if(driverList.get(y).getId().getDout().equals(rDate)==true || driverList.get(y).getId().getDin().equals(rDate)==true){
//						String validDates="";
//						if(driverList.get(y).getId().getDout().equals(rDate)==true && driverList.get(y).getId().getDin().equals(rDate)==true){
//							validDates="both";
//						}else if(driverList.get(y).getId().getDout().equals(rDate)==true && driverList.get(y).getId().getDin().equals(rDate)==false){
//							validDates="dout";
//						}else if(driverList.get(y).getId().getDout().equals(rDate)==false && driverList.get(y).getId().getDin().equals(rDate)==true){
//							validDates="din";
//						}
//						
//						//Date Out
//						String ddout=driverList.get(y).getId().getDout().get(Calendar.YEAR)+".";
//						ddout+=(driverList.get(y).getId().getDout().get(Calendar.MONTH)+1)+".";
//						ddout+=driverList.get(y).getId().getDout().get(Calendar.DATE);
//
//						//Date In
//						String ddin=driverList.get(y).getId().getDin().get(Calendar.YEAR)+".";
//						ddin+=(driverList.get(y).getId().getDin().get(Calendar.MONTH)+1)+".";
//						ddin+=driverList.get(y).getId().getDin().get(Calendar.DATE);
//						
//						Double secRptDateVal=0.00; //Repeating No Of Days Value
//						Double subRptNoOfDays=roundTwoDecimals(driverList.get(y).getPriority()-1);
//						
//						ASObject rptDate=new ASObject();
//						if(validDates.equals("dout")==true){
//							rptDate=getSecRptDateDet(driverList.get(y).getId().getDin(), lstRptDates);
//						}else if(validDates.equals("din")==true){
//							rptDate=getSecRptDateDet(driverList.get(y).getId().getDout(), lstRptDates);
//						}else{
//							rptDate=null;
//						}
//						Double oTmpVal=0.00;
//						
//						if(rptDate!=null){
//							oTmpVal=roundTwoDecimals(roundTwoDecimals((subRptNoOfDays/new Double(rCnt))));
//							oTmpVal=roundTwoDecimals(subRptNoOfDays+tmpVal);
//							subRptNoOfDays-=1;
//							secRptDateVal=roundTwoDecimals((new Double("1")/new Double(Integer.parseInt(rptDate.get("count").toString()))));
//							//rDateDiff-=secRptDateVal;
//							//tmpVal-=secRptDateVal;
//							//System.out.println("y: " + y + "    secRptDateVal ***: " + secRptDateVal);
//							//System.out.println("dout: " + ddout + "     din: " + ddin + "     secRptDateVal: " + secRptDateVal + "    tmpVal: " + tmpVal);
//							ASObject tmpOLDate=new ASObject();
//							tmpOLDate.put("dout", ddout);
//							tmpOLDate.put("din", ddin);
//							tmpOLDate.put("secRptDateVal", secRptDateVal);
//							tmpOLDate.put("checked", "0");
//							oLappingDateList.add(tmpOLDate);
//						}
//						
//						java.math.BigDecimal days;
//						//tmpVal=roundTwoDecimals(roundTwoDecimals((new Double("1")/new Double(rCnt)))+secRptDateVal);
//						//tmpVal=roundTwoDecimals(roundTwoDecimals((new Double("1")/new Double(rCnt))));
//						tmpVal=roundTwoDecimals(roundTwoDecimals((new Double("1")/new Double(rCnt))));
//						
//						tmpVal+=secRptDateVal;
//						tmpVal=roundTwoDecimals(subRptNoOfDays+tmpVal);
//						
//						//if(rptDate==null){
//							rRunCnt+=tmpVal; 
//							tmpRCnt+=1;
//							if(tmpRCnt==rCnt){
//								if(rRunCnt<rDateDiff){
//									//System.out.println("rRunCnt: " + rRunCnt + "     rDateDiff: " + rDateDiff);
//									//System.out.println("before tmpVal: " + tmpVal);
//									//System.out.println("rDateDiff: " + rDateDiff);
//									
//									double diff=roundTwoDecimals(rDateDiff-rRunCnt);
////									for(int l=0;l<oLappingDateList.size();l++){
////										ASObject tmpOLDate=oLappingDateList.get(l);
////										if(tmpOLDate.get("dout").equals(ddout)==true || tmpOLDate.get("din").equals(ddout)==true)
////											//System.out.println("checked: " + tmpOLDate.get("checked").toString());
////											if(tmpOLDate.get("checked").equals("0")==true){
////												rDateDiff=roundTwoDecimals(rDateDiff-Double.parseDouble(tmpOLDate.get("secRptDateVal").toString()));
////												diff=roundTwoDecimals(rDateDiff-rRunCnt);
////												tmpOLDate.put("checked", "1");
////												oLappingDateList.remove(l);
////												oLappingDateList.add(l, tmpOLDate);
////											}
////									}
//									
//									System.out.println("before tmpVal: " + tmpVal);
//									tmpVal+=diff;
//									System.out.println("dout: " + ddout + "     din: " + ddin + "tmpRCnt: " + tmpRCnt + "    rCnt: " + rCnt + "      rRunCnt: " + rRunCnt + "      rDateDiff: " + rDateDiff + "    diff: " + diff + "    tmpVal: " + tmpVal);
//								}
//							}
//						//}
//						days=roundTwoDecimals(new java.math.BigDecimal(tmpVal));
//						
//						//System.out.println("dout: " + ddout + "    din: " + ddin + "    days: " + days + "   rDateDiff: " + rDateDiff);
//						driverList.get(y).setDays(days);
//					}else
//						System.out.println("**********");
//				}
//			}
//			return driverList;
//		}catch(Exception e){
//			System.out.println("splitVehicleRates: " + e.getMessage());
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	//************************************
	
//	@Transactional
//	@Override
//	public ArrayCollection genInvoice(Freshed freshed, ArrayCollection subAgrList) {
//		try {
//			String strSubAgrList="";
//			ASObject aso;
//			for(int a=0;a<subAgrList.size();a++) {
//				aso=(ASObject)subAgrList.get(a);
//				strSubAgrList+="'"+aso.get("resno").toString()+"'";
//				if(a<(subAgrList.size()-1))
//					strSubAgrList+=",";
//			}
//			
//			String query="SELECT r.*,";
//			query += " (SELECT taxcomcode FROM fcompanytax WHERE companyid='"+freshed.getCompanyid()+"' AND hiretypeid=r.hiretypeid) AS taxcomcode,";
//			query += " (RTRIM((SELECT description FROM fvehiclemake WHERE vehimakeid=(SELECT vehimakeid FROM fvehicle WHERE regno=r.regno))) + ' ' +";
//			query += " RTRIM((SELECT description FROM fvehiclemodel WHERE vehimodelid=(SELECT vehimodelid FROM fvehicle WHERE regno=r.regno)))) AS vehicle";
//			query += " FROM"; 
//			query += " (SELECT"; 
//			query += " r.resno,";
//			query += " rv.regno,";
//			query += " vr.ratetype,vr.rate,vr.discount,vr.xsmilerate,vr.allotedkms,"; //Vehicle Details 
//			query += " dr.ratetype AS drratetype,dr.discount AS drdiscount,dr.nightoutrate AS drnightoutrate,dr.otrate AS drotrate,"; //Driver Details 
//			query += " r.hiretypeid,r.dout,r.din,r.noofday,r.chargdays,r.deposit,r.advance,r.comileage,r.cimileage,r.cixsmileage,r.cixsmileagers,r.cidetenhrs,r.cidetenhrsrs,r.cinightout,r.cinightoutrs,r.ciother,r.cifueldiff,r.cidamagers"; 
//			query += " FROM freservation AS r LEFT OUTER JOIN";
//			query += " fresvehicle AS rv ON r.resno=rv.resno AND rv.priority=1";
//			query += " LEFT OUTER JOIN fresvehiclerate AS vr ON r.resno=vr.resno";
//			query += " LEFT OUTER JOIN fresdriverrate AS dr ON r.resno=dr.resno";
//			query += " WHERE r.resno IN("+strSubAgrList+")) AS r";
//			query += " ORDER BY r.resno";
//			
//			System.out.println("query: " + query);
//			
//			List<FarfdtfTMP> list=em.createNativeQuery(query, "farfdtfTMPSQL").getResultList();
//
//			List<Farfdtf> farfdtfs=new ArrayList<Farfdtf>();
//			Farfdtf farfdtf;
//			FarfdtfPK pk;
//			List<Ftaxdet> taxdets;
//			
//			ArrayCollection invDet=new ArrayCollection();
//			ASObject asoObj;
//			String des;
//			seqCnt=1;
//			
//			for(int a=0;a<list.size();a++){
//				FarfdtfTMP tmp=list.get(a);
//				taxdets=loadTaxDet(tmp.getTaxcomcode());
//				
//				//VEHICLE RATE
//				asoObj=new ASObject(); 
//				asoObj=setHedVal(tmp, asoObj); //Set HedVal 
//				
//				//Per Day Discount Amount
//				java.math.BigDecimal pdisamt=calcDisAmt(tmp.getRate(), tmp.getDiscount());
//				//Per Day Before Tax Amount (Rate - Discount)
//				java.math.BigDecimal pbtamt=tmp.getRate().subtract(pdisamt);
//				java.math.BigDecimal totalRental=pbtamt.multiply(new java.math.BigDecimal(tmp.getChargdays()));
//				System.out.println("rate: " + tmp.getRate() + "   disper: " + tmp.getDiscount() + "   pdisamt: " + pdisamt + "    pbtamt: " + pbtamt + "   totalRental: " + totalRental);
//								
//				des=tmp.getVehicle()+"\r";
//				des+="[Rs."+ tmp.getRate() +"/- Per Day";
//				if(tmp.getDiscount().compareTo(new java.math.BigDecimal(0))==0){ //Discount Not Applicable 
//					
//				}else if(tmp.getDiscount().compareTo(new java.math.BigDecimal(0))>0){ //Discount Applicable 
//					des+=" - "+tmp.getDiscount()+"%";
//				}
//				
//				for(int b=0;b<taxdets.size();b++){//Tax Details
//					des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
//				}
//				des+="]";
//				asoObj.put("description", des);
//				asoObj.put("km", 0);
//				asoObj.put("days", tmp.getChargdays());
//				
//				if(tmp.getHiretypeid().equals("SD")==true){
//					farfdtf=new Farfdtf();
//					farfdtf=setFarfdtfHedVal(freshed, tmp, farfdtf);
//					farfdtf.setType("VR");
//					farfdtfs.add(farfdtf);
//				}else{//WD or WEDDING
//					List<Fresdriver> driverList=getDriverList(tmp.getResno(), false);
//					ArrayList<ASObject> lstRptDates=getRepeatingDateList(driverList); //Repeating Date List
//
////					System.out.println("***************************************\r");
////					for(int t=0;t<lstRptDates.size();t++){
////						Calendar dt=(Calendar)lstRptDates.get(t).get("date");
////						int cnt=Integer.parseInt(lstRptDates.get(t).get("count").toString());
////						System.out.println("date: " + dt.get(Calendar.YEAR) + "." + (dt.get(Calendar.MONTH)+1) + "." + dt.get(Calendar.DATE) + "     count: " + cnt);
////					}
////					System.out.println("\r***************************************\r");
//					
//					Calendar rDate;
//					double rDateDiff;
//					int tmpRCnt;
//				    Double rCnt;
//				    Double rRunCnt;
//				    Double tmpVal;
//					for(int x=0;x<lstRptDates.size();x++){
//						rDate=(Calendar)lstRptDates.get(x).get("date");
//						rCnt=Double.parseDouble(lstRptDates.get(x).get("count").toString());
//						rDateDiff=Double.parseDouble(lstRptDates.get(x).get("dateDiff").toString()); //Date Difference 
//						//System.out.println("count: " + rCnt);
//						rRunCnt=0.00;
//						tmpRCnt=0;
//						for(int y=0;y<driverList.size();y++){
////							System.out.println("dout: " + driverList.get(y).getId().getDout().get(Calendar.YEAR) + "." + driverList.get(y).getId().getDout().get(Calendar.MONTH)+1 + "." + + driverList.get(y).getId().getDout().get(Calendar.DATE));
////							System.out.println("din: " + driverList.get(y).getId().getDin().get(Calendar.YEAR) + "." + driverList.get(y).getId().getDin().get(Calendar.MONTH)+1 + "." + + driverList.get(y).getId().getDin().get(Calendar.DATE));
//							if(driverList.get(y).getId().getDout().equals(rDate)==true || driverList.get(y).getId().getDin().equals(rDate)==true){
//								String validDates="";
//								if(driverList.get(y).getId().getDout().equals(rDate)==true && driverList.get(y).getId().getDin().equals(rDate)==true){
//									validDates="both";
//								}else if(driverList.get(y).getId().getDout().equals(rDate)==true && driverList.get(y).getId().getDin().equals(rDate)==false){
//									validDates="dout";
//								}else if(driverList.get(y).getId().getDout().equals(rDate)==false && driverList.get(y).getId().getDin().equals(rDate)==true){
//									validDates="din";
//								}
//								//System.out.println("validDates: " + validDates);
//								
//								Double secRptDateVal=0.00; //Repeating No Of Days Value
//								Double subRptNoOfDays=roundTwoDecimals(driverList.get(y).getPriority()-1);
//								
//								ASObject rptDate=new ASObject();
//								if(validDates.equals("dout")==true){
//									rptDate=getSecRptDateDet(driverList.get(y).getId().getDin(), lstRptDates);
//								}else if(validDates.equals("din")==true){
//									rptDate=getSecRptDateDet(driverList.get(y).getId().getDout(), lstRptDates);
//								}else{
//									rptDate=null;
//								}
//								if(rptDate!=null){
//									subRptNoOfDays-=1;
//									secRptDateVal=roundTwoDecimals((new Double("1")/new Double(Integer.parseInt(rptDate.get("count").toString()))));
//									
//									//Calendar dot=driverList.get(y).getId().getDout();
//									//Calendar diin=driverList.get(y).getId().getDin();
//									//String dotstr=dot.get(Calendar.YEAR)+"."+(dot.get(Calendar.MONTH)+1)+"."+dot.get(Calendar.DATE);
//									//String diinstr=diin.get(Calendar.YEAR)+"."+(diin.get(Calendar.MONTH)+1)+"."+diin.get(Calendar.DATE);
//									
//									
//									//System.out.println("y: " + y + "     dout: " + dotstr + "     din: " + diinstr + "        1/"+rptDate.get("count").toString());
//									//System.out.println("subRptNoOfDays: " + subRptNoOfDays + "    secRptDateVal: " + secRptDateVal);
//								}
//								
//								java.math.BigDecimal days;
//								//tmpVal=new Double("1")/new Double(rCnt);
//								tmpVal=roundTwoDecimals(roundTwoDecimals((new Double("1")/new Double(rCnt)))+secRptDateVal);
//								
//								//tmpVal=roundTwoDecimals((driverList.get(y).getPriority()-1)+tmpVal);
//								//System.out.println("subRptNoOfDays: " + subRptNoOfDays + "    secRptDateVal: " + secRptDateVal);
//								tmpVal=roundTwoDecimals(subRptNoOfDays+tmpVal);
//								
//								if(rptDate==null){
//									rRunCnt+=tmpVal; 
//									tmpRCnt+=1;
//									if(tmpRCnt==rCnt){
//										if(rRunCnt<rDateDiff){
//											
//											//Calendar dot=driverList.get(y).getId().getDout();
//											//Calendar diin=driverList.get(y).getId().getDin();
//											//String dotstr=dot.get(Calendar.YEAR)+"."+(dot.get(Calendar.MONTH)+1)+"."+dot.get(Calendar.DATE);
//											//String diinstr=diin.get(Calendar.YEAR)+"."+(diin.get(Calendar.MONTH)+1)+"."+diin.get(Calendar.DATE);
//											//System.out.println("tmpVal Before: " + tmpVal);
//											
//											double diff=roundTwoDecimals(rDateDiff-rRunCnt);
//											//System.out.println("y: " + y + "     dout: " + dotstr + "     din: " + diinstr + "       rRunCnt: " + rRunCnt + "      rDateDiff: " + rDateDiff + "      diff: " + diff);
//											tmpVal+=diff;
//										}
//									}
//								}
//								days=new java.math.BigDecimal(tmpVal);
//								System.out.println("days: " + days + "       rDateDiff: " + rDateDiff + "    rRunCnt: " + rRunCnt + "    tmpRCnt: " + tmpRCnt + "   tmpVal: " + tmpVal);
//								driverList.get(y).setDays(days);
//							}
//						}
//					}
//					
//					
//					java.math.BigDecimal tot=new java.math.BigDecimal(0);
//					for(int b=0;b<driverList.size();b++){
//						farfdtf=new Farfdtf();
//						farfdtf=setFarfdtfHedVal(freshed, tmp, farfdtf);
//						farfdtf.setEmpid(driverList.get(b).getId().getEmpid());
//						farfdtf.setType("VR");
//												
//						java.math.BigDecimal days;
//						if(driverList.get(b).getDays()==null)
//							days=new java.math.BigDecimal(driverList.get(b).getPriority());
//						else 
//							days=driverList.get(b).getDays();
//						
//						farfdtf.setPgamt(tmp.getRate());
//						farfdtf.setDisper(tmp.getDiscount());
//						farfdtf.setPdisamt(pdisamt);
//						farfdtf.setPbtamt(pbtamt);
//						
//						java.math.BigDecimal tbamt;
//						tbamt=roundTwoDecimals(totalRental.multiply(days, MathContext.UNLIMITED));
//						tbamt=roundTwoDecimals(tbamt.divide(new java.math.BigDecimal(tmp.getNoofday()), RoundingMode.HALF_UP));
//						tot=tot.add(tbamt);
//						System.out.println("total per day: " + tbamt);
//						farfdtf.setTbamt(tbamt);
//						
//						farfdtfs.add(farfdtf);
//					}
//					
//					System.out.println("total value: " + tot);
//				}
//				
//				invDet.add(asoObj);
//				
//				
////				//EXCESS MILEAGE
////				if(tmp.getCixsmileage()>0){
////					asoObj=new ASObject();
////					asoObj=setHedVal(tmp, asoObj);
////					des="Excess Mileage\r";
////					des+="[Rs."+ tmp.getXsmilerate() +"/- Per KM";
////					if(tmp.getDiscount().compareTo(new java.math.BigDecimal(0))==0){ //Discount Not Applicable 
////						
////					}else if(tmp.getDiscount().compareTo(new java.math.BigDecimal(0))>0){ //Discount Applicable 
////						des+=" - "+tmp.getDiscount()+"%";
////					}
////					
////					for(int b=0;b<taxdets.size();b++){//Tax Details
////						des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
////					}
////					des+="]\r";
////					des+="Mile. Done = "+(tmp.getCimileage()-tmp.getComileage())+" KM\r";
////					des+="Limit. Mile. = "+(tmp.getAllotedkms()*tmp.getChargdays())+" KM\r";
////					des+="Exce. Mile. = "+tmp.getCixsmileage()+" KM\r";
////					asoObj.put("description", des);
////					asoObj.put("km", tmp.getCixsmileage());
////					asoObj.put("days", 0);
////					invDet.add(asoObj);
////				}
////				
////				//DETENTION 
////				if(tmp.getCidetenhrs()>0){
////					asoObj=new ASObject();
////					asoObj=setHedVal(tmp, asoObj);
////					des="Detention After 8 Hours\r";
////					des+="[Rs."+ tmp.getDrotrate() +"/- Per Hour";
////				
////					for(int b=0;b<taxdets.size();b++){//Tax Details
////						des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
////					}
////					des+="]\r";
////					asoObj.put("description", des);
////					asoObj.put("km", 0);
////					asoObj.put("days", tmp.getCidetenhrs()+" Hrs");
////					invDet.add(asoObj);
////				}
////				
////				//NIGHT OUT
////				if(tmp.getCinightout()>0){
////					asoObj=new ASObject();
////					asoObj=setHedVal(tmp, asoObj);
////					des="Night Out\r";
////					des+="[Rs."+ tmp.getDrnightoutrate() +"/- Per Day";
////				
////					for(int b=0;b<taxdets.size();b++){//Tax Details
////						des+=" + "+taxdets.get(b).getTaxname()+" "+taxdets.get(b).getTaxper()+"%";
////					}
////					des+="]\r";
////					asoObj.put("description", des);
////					asoObj.put("km", 0);
////					asoObj.put("days", tmp.getCinightout());
////					invDet.add(asoObj);
////				}
////				
////				//OTHER
////				if(tmp.getCiother().compareTo(new java.math.BigDecimal(0))>0){
////					asoObj=new ASObject();
////					asoObj=setHedVal(tmp, asoObj);
////					des="Other Charges";
////					asoObj.put("description", des);
////					asoObj.put("rate", tmp.getCiother());
////					asoObj.put("km", 0);
////					asoObj.put("days", 0);
////					asoObj.put("amount", tmp.getCiother());
////					invDet.add(asoObj);
////				}
////				
////				//FUEL DIFFERENCES
////				if(tmp.getCifueldiff().compareTo(new java.math.BigDecimal(0))>0 || tmp.getCifueldiff().compareTo(new java.math.BigDecimal(0))<0){
////					asoObj=new ASObject();
////					asoObj=setHedVal(tmp, asoObj);
////					des="Fuel Difference";
////					asoObj.put("description", des);
////					asoObj.put("rate", tmp.getCifueldiff());
////					asoObj.put("km", 0);
////					asoObj.put("days", 0);
////					asoObj.put("amount", tmp.getCifueldiff());
////					invDet.add(asoObj);
////				}
////				
////				//DAMAGES
////				if(tmp.getCidamagers().compareTo(new java.math.BigDecimal(0))>0){
////					asoObj=new ASObject();
////					asoObj=setHedVal(tmp, asoObj);
////					des="Damages";
////					asoObj.put("description", des);
////					asoObj.put("rate", tmp.getCidamagers());
////					asoObj.put("km", 0);
////					asoObj.put("days", 0);
////					asoObj.put("amount", tmp.getCidamagers());
////					invDet.add(asoObj);
////				}
//				
//				//OTHER SERVICES
//				//ACCESSORY
//			}
//			
//			System.out.println("list length: " + list.size());
//			ArrayCollection ac=new ArrayCollection();
//			ac.add(invDet);
//			ac.add(farfdtfs);
//			return ac;
//		} catch (Exception e) {
//			System.out.println("FarfdtfDAOImpl genInvoice: " + e.getMessage());
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private ArrayList<ASObject> getRepeatingDateList(List<Fresdriver> fresdrivers){
		long MILLSECS_PER_DAY=1000*60*60*24;
		ArrayList<ASObject> list=new ArrayList<ASObject>();
		ASObject aso;
		int occCnt;
		int index=0;
		for (int a = 0; a < fresdrivers.size(); a++) {
			Calendar dout=fresdrivers.get(a).getId().getDout(); //Get Date Out
			Calendar din=fresdrivers.get(a).getId().getDin(); //Get Date In
			Calendar lastDate=Calendar.getInstance();
			long dateDiff=0;
			if(isChecked(list,din)==false){ //Check If Date Is Already Checked 
				occCnt=0;
				for(int b=0;b<fresdrivers.size();b++){
					if(a!=b){
						if(fresdrivers.get(b).getId().getDout().equals(din)==true){ //Compare Date In With Date Out
							occCnt+=1;
							lastDate=(Calendar)fresdrivers.get(b).getId().getDin().clone();
						}
					}
				}
				if(occCnt>0){
					occCnt+=1;
					aso=new ASObject();
					aso.put("index", index);
					aso.put("date", din);
					aso.put("count", occCnt);
					
					//System.out.println(dout.get(Calendar.YEAR) + "." + (dout.get(Calendar.MONTH)+1) + "." + dout.get(Calendar.DATE));
					//System.out.println(lastDate.get(Calendar.YEAR) + "." + (lastDate.get(Calendar.MONTH)+1) + "." + lastDate.get(Calendar.DATE));
					dateDiff=((lastDate.getTimeInMillis() - dout.getTimeInMillis())/MILLSECS_PER_DAY)+1;
					
					aso.put("dateDiff", dateDiff);
					//System.out.println("dateDiff: " + dateDiff);
					list.add(aso);
					index+=1;
				}
			}
		}
		return list;
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private Boolean isChecked(ArrayList<ASObject> list,Calendar chkDate){
		for (int a = 0; a < list.size(); a++) {
			Calendar tmpDate=(Calendar)list.get(a).get("date");
			String vDate=tmpDate.get(Calendar.YEAR) + "." + (tmpDate.get(Calendar.MONTH)+1) + "." + tmpDate.get(Calendar.DATE);
			String cDate=chkDate.get(Calendar.YEAR) + "." + (chkDate.get(Calendar.MONTH)+1) + "." + chkDate.get(Calendar.DATE);
			if(vDate.equals(cDate)==true){
				return true;
			}
		}
		return false;
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private java.math.BigDecimal calcDisAmt(java.math.BigDecimal rate,java.math.BigDecimal disper){
		return roundTwoDecimals(((rate.multiply(disper).divide(new java.math.BigDecimal(100)))));
		//return ((rate.multiply(disper).divide(new java.math.BigDecimal(100))));
	}
	
//	@Transactional(propagation=Propagation.MANDATORY)
//	private List<Fresdriver> getDriverList(String resno,Boolean isSrv){
//		String query="SELECT";
//		query += " d.resno,d.empid,";
//		query += " (SELECT (DATEDIFF(d,d.dout,d.din))+1) AS priority,";
//		query += " d.dout,d.din,d.timeout,d.timein,d.srvid,d.issrv";
//		query += " FROM fresdriver AS d WHERE resno='"+resno+"' AND priority=1 AND issrv=";
//		if(isSrv==true)
//			query += "1";
//		else
//			query += "0";
//		query += " ORDER BY dout,timeout,din,timein";
//		System.out.println("getDriverList: " + query);
//		List<Fresdriver> list=em.createNativeQuery(query, Fresdriver.class).getResultList();
//		return list;
//	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private BigDecimal calcAddCharges(List<Fresaddcharge> addChargeList,BigDecimal pbtamt){
		BigDecimal totalAddCharge=new BigDecimal(0);
		try {
			em.flush();
			Fresaddcharge fresaddcharge;
			for(int s=0;s<addChargeList.size();s++){
				fresaddcharge=addChargeList.get(s);
				//totalAddCharge=roundTwoDecimals(totalAddCharge.add(roundTwoDecimals(pbtamt.multiply(fresaddcharge.getPercentage()).divide(new BigDecimal(100)))));
				totalAddCharge=totalAddCharge.add(roundTwoDecimals(pbtamt.multiply(fresaddcharge.getPercentage()).divide(new BigDecimal(100))));
			}
			//pbtamt=roundTwoDecimals(pbtamt.add(totalAddCharge));
			pbtamt=pbtamt.add(totalAddCharge);
			return pbtamt;
		} catch (Exception e) {
			System.out.println("calcAddCharges: " + e.getMessage());
		}
		finally{em.flush();}
		return null;
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private List<Fresaddcharge> getAdditionalCharges(String resno){
		try {
			em.flush();
			String query="SELECT r.addchargeid,";
			query += " (SELECT description FROM faddcharge WHERE addchargeid=r.addchargeid) AS [addcharge],";
			query += " (SELECT ctype FROM faddcharge WHERE addchargeid=r.addchargeid) AS [ctype],";
			query += " r.amount,r.percentage";
			query += " FROM fresaddcharge AS r"; 
			query += " WHERE r.resno='"+resno+"'"; 
			query += " ORDER BY r.addchargeid";
			
			Query q = em.createNativeQuery(query);
			List<Object[]> l1 = q.getResultList();
			List<Fresaddcharge> ac = new ArrayList<Fresaddcharge>();

			String addchargeid;
			String addcharge;
			String ctype;
			BigDecimal amount;
			BigDecimal percentage;
			
			for(Object[] r1 : l1){
				Fresaddcharge mf=new Fresaddcharge();
				FresaddchargePK pk=new FresaddchargePK();
				
				addchargeid=(String)r1[0];
				addcharge=(String)r1[1];
				ctype=(String)r1[2];
				amount=new java.math.BigDecimal(r1[3].toString());
				percentage=new java.math.BigDecimal(r1[4].toString());
				
				pk.setResno(resno);
				pk.setAddchargeid(addchargeid);
				mf.setId(pk);
				
				mf.setAddcharge(addcharge);
				mf.setCtype(ctype);
				mf.setAmount(amount);
				mf.setPercentage(percentage);
				
				ac.add(mf);
			}
			return ac;
		} catch (Exception e) {
			System.out.println("getAdditionalCharges: " + e.getMessage());
			e.printStackTrace();
		}
		finally{em.flush();}
		return null;
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private List<Fresaddcharge> getAdditionalCharges2(String resno,BigDecimal dateFragment){
		try {
			em.flush();
			String query="SELECT r.addchargeid,";
			query += " (SELECT description FROM faddcharge WHERE addchargeid=r.addchargeid) AS [addcharge],";
			query += " (SELECT ctype FROM faddcharge WHERE addchargeid=r.addchargeid) AS [ctype],";
			query += " r.amount,r.percentage";
			query += " FROM fresaddcharge AS r"; 
			query += " WHERE r.resno='"+resno+"'"; 
			query += " ORDER BY r.addchargeid";
			
			Query q = em.createNativeQuery(query);
			List<Object[]> l1 = q.getResultList();
			List<Fresaddcharge> ac = new ArrayList<Fresaddcharge>();
			
			String addchargeid;
			String addcharge;
			String ctype;
			BigDecimal amount;
			BigDecimal percentage;
			
			for(Object[] r1 : l1){
				Fresaddcharge mf=new Fresaddcharge();
				FresaddchargePK pk=new FresaddchargePK();
				
				addchargeid=(String)r1[0];
				addcharge=(String)r1[1];
				ctype=(String)r1[2];
				amount=new java.math.BigDecimal(r1[3].toString());
				percentage=new java.math.BigDecimal(r1[4].toString());
				
				pk.setResno(resno);
				pk.setAddchargeid(addchargeid);
				mf.setId(pk);
				
				mf.setAddcharge(addcharge);
				mf.setCtype(ctype);
				mf.setAmount(amount.divide(dateFragment));
				mf.setPercentage(percentage);
				
				ac.add(mf);
			}
			return ac;
		} catch (Exception e) {
			System.out.println("getAdditionalCharges: " + e.getMessage());
			e.printStackTrace();
		}
		finally{em.flush();}
		return null;
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private List<Fresaccs> getAccessoryList(String resno){
		try{
//			String query="SELECT ra.accid,";
//			query += " (SELECT description FROM faccessory WHERE accid=ra.accid) AS description,";
//			query += " ra.dfrom,ra.dto,ra.noofdays,ra.rate,ra.qty,ra.remarks FROM fresaccs AS ra WHERE resno='"+resno+"'";
			em.flush();
			String query="SELECT ra.accid,";
			query += " (SELECT description FROM faccessory WHERE accid=ra.accid) AS description,";
			query += " ra.dfrom,ra.dto,ra.noofdays,";
			query += " (SELECT ratetype FROM fresaccrate WHERE resno=ra.resno AND accid=ra.accid AND dfrom=ra.dfrom AND dto=ra.dto) AS ratetype,";
			query += " (SELECT standardrate FROM fresaccrate WHERE resno=ra.resno AND accid=ra.accid AND dfrom=ra.dfrom AND dto=ra.dto) AS standardrate,";
			query += " (SELECT rate FROM fresaccrate WHERE resno=ra.resno AND accid=ra.accid AND dfrom=ra.dfrom AND dto=ra.dto) AS rate,";
			query += " (SELECT discount FROM fresaccrate WHERE resno=ra.resno AND accid=ra.accid AND dfrom=ra.dfrom AND dto=ra.dto) AS discount,";
			query += " ra.qty FROM fresaccs AS ra";
			query += " WHERE resno='"+resno+"'";
			
			System.out.println("\r\r");
			System.out.println("getAccessoryList Query: " + query);
			System.out.println("\r\r");
			
			Query q = em.createNativeQuery(query);
			List<Object[]> l1 = q.getResultList();
			List<Fresaccs> ac = new ArrayList<Fresaccs>();

			String accid;
			String description;
			Timestamp dfrom;
			Timestamp dto;
			int noofdays;
			String ratetype;
			BigDecimal standardrate;
			java.math.BigDecimal rate;
			BigDecimal discount;
			int qty;
			
			for(Object[] r1 : l1){
				Fresaccs mf=new Fresaccs();
				FresaccsPK pk=new FresaccsPK();
				
				accid=(String)r1[0];
				description=(String)r1[1];
				dfrom=(Timestamp)r1[2];
				dto=(Timestamp)r1[3];
				noofdays=Integer.parseInt(r1[4].toString());
				ratetype=(String)r1[5];
				standardrate=new java.math.BigDecimal(r1[6].toString());
				rate=new java.math.BigDecimal(r1[7].toString());
				discount=new java.math.BigDecimal(r1[8].toString());
				qty=Integer.parseInt(r1[9].toString());
				
				pk.setResno(resno);
				pk.setAccid(accid);
				
				Calendar dateFrom=Calendar.getInstance();
				java.util.Date dtFrom=new java.util.Date(dfrom.getTime());
				dateFrom.setTime(dtFrom);
				pk.setDfrom(dateFrom);
				
				Calendar dateTo=Calendar.getInstance();
				java.util.Date dtTo=new java.util.Date(dto.getTime());
				dateTo.setTime(dtTo);
				pk.setDto(dateTo);
				
				mf.setId(pk);
				
				mf.setDescription(description);
				mf.setNoofdays(noofdays);
				mf.setQty(qty);
				mf.setRatetype(ratetype);
				mf.setStandardrate(standardrate);
				mf.setRate(rate);
				mf.setDiscount(discount);
				
				ac.add(mf);
			}
			return ac;
		}catch(Exception e){
			System.out.println("getAccessoryList: " + e.getMessage());
		}
		finally{em.flush();}
		return null;
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private List<Fresothsrv> getOtherServiceList(String resno){
		try{			
			em.flush();
			String query="SELECT s.srvid,";
			query += " (SELECT description FROM fothersrv WHERE srvid=s.srvid) AS description,";
			query += " s.dfrom,s.dto,s.noofdays,";
			query += " (SELECT ratetype FROM fresothersrvrate WHERE resno=s.resno AND srvid=s.srvid AND dfrom=s.dfrom AND dto=s.dto AND timeout=s.timeout and timein=s.timein) AS ratetype,";
			query += " (SELECT standardrate FROM fresothersrvrate WHERE resno=s.resno AND srvid=s.srvid AND dfrom=s.dfrom AND dto=s.dto AND timeout=s.timeout and timein=s.timein) AS standardrate,";
			query += " (SELECT rate FROM fresothersrvrate WHERE resno=s.resno AND srvid=s.srvid AND dfrom=s.dfrom AND dto=s.dto AND timeout=s.timeout and timein=s.timein) AS rate,";
			query += " (SELECT discount FROM fresothersrvrate WHERE resno=s.resno AND srvid=s.srvid AND dfrom=s.dfrom AND dto=s.dto AND timeout=s.timeout and timein=s.timein) AS discount";
			query += " FROM fresothsrv AS s";
			query += " WHERE resno='"+resno+"'";
			query += " ORDER BY s.srvid,s.dfrom,s.dto";
			
			System.out.println("\r\r");
			System.out.println("getOtherServiceList Query: " + query);
			System.out.println("\r\r");
			
			Query q = em.createNativeQuery(query);
			List<Object[]> l1 = q.getResultList();
			List<Fresothsrv> ac = new ArrayList<Fresothsrv>();

			String srvid;
			String description;
			Timestamp dfrom;
			Timestamp dto;
			int noofdays;
			String ratetype;
			BigDecimal standardrate;
			java.math.BigDecimal rate;
			BigDecimal discount;
			
			Fresothsrv mf;
			FresothsrvPK pk;
			
			for(Object[] r1 : l1){
				mf=new Fresothsrv();
				pk=new FresothsrvPK();
				
				srvid=(String)r1[0];
				description=(String)r1[1];
				dfrom=(Timestamp)r1[2];
				dto=(Timestamp)r1[3];
				noofdays=Integer.parseInt(r1[4].toString());
				ratetype=(String)r1[5];
				standardrate=new java.math.BigDecimal(r1[6].toString());
				rate=new java.math.BigDecimal(r1[7].toString());
				discount=new java.math.BigDecimal(r1[8].toString());
				
				pk.setResno(resno);
				pk.setSrvid(srvid);
				
				
				Calendar dateFrom=Calendar.getInstance();
				java.util.Date dtFrom=new java.util.Date(dfrom.getTime());
				dateFrom.setTime(dtFrom);
				pk.setDfrom(dateFrom);
				
				Calendar dateTo=Calendar.getInstance();
				java.util.Date dtTo=new java.util.Date(dto.getTime());
				dateTo.setTime(dtTo);
				pk.setDto(dateTo);
				
				mf.setId(pk);
				
				mf.setDescription(description);
				mf.setNoofdays(noofdays);
				mf.setRatetype(ratetype);
				mf.setStandardrate(standardrate);
				mf.setRate(rate);
				mf.setDiscount(discount);
				
				ac.add(mf);
			}
			l1=null;
			return ac;
		}catch(Exception e){
			System.out.println("getOtherServiceList: " + e.getMessage());
		}
		finally{em.flush();}
		return null;
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private List<Fresdriver> getDriverList(String resno,Boolean isSrv,String srvid){
		String query="SELECT dr.resno,dr.empid,";
		query += " (SELECT (DATEDIFF(d,dr.dout,dr.din))+1) AS priority,"; 
		query += " dr.dout,dr.din,dr.timeout,dr.timein,dr.srvid,dr.issrv";
		query += " FROM";
		query += " (SELECT d.resno,d.empid,";
		query += " (SELECT DATEADD(dd, DATEDIFF(d, 0, d.dout), 0)) as dout,";
		query += " (SELECT DATEADD(dd, DATEDIFF(d, 0, d.din), 0)) as din,";
		query += " d.timeout,d.timein,d.srvid,d.issrv";
		query += " FROM fresdriver AS d WHERE resno='"+resno+"' AND priority=1 AND issrv=";
		if(isSrv==true)
			query += "1 AND srvid='" + srvid + "'";
		else
			query += "0";
		query += ") AS dr ORDER BY dr.dout,dr.timeout,dr.din,dr.timein";
		System.out.println("getDriverList: " + query);
		List<Fresdriver> list=em.createNativeQuery(query, Fresdriver.class).getResultList();
		return list;
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private ASObject setHedVal(FarfdtfTMP tmp, ASObject asoObj){
		asoObj.put("resno", tmp.getResno());
		asoObj.put("dfrom", tmp.getDout());
		asoObj.put("dto", tmp.getDin());
		asoObj.put("regno", tmp.getRegno());
		asoObj.put("rate", "0");
		asoObj.put("taxval", "0");
		asoObj.put("amount", "0");
		asoObj.put("taxcomcode", tmp.getTaxcomcode());
		asoObj.put("type", "");
		asoObj.put("qty", "0");
		asoObj.put("seq", hedSeqCnt);
		asoObj.put("subseq", "0");
		asoObj.put("hiretypeid", tmp.getHiretypeid());
		asoObj.put("hiretype", tmp.getHiretype());		
		hedSeqCnt+=1;
		return asoObj;
	}
	
	FarfdtfPK pk;
	
	@Transactional(propagation=Propagation.MANDATORY)
	private Farfdtf setFarfdtfHedVal(Freshed freshed, FarfdtfTMP tmp, Farfdtf farfdtf){
		//Set PK
		String tmpdtfref="TMP00000000";
		pk=new FarfdtfPK();
		pk.setDtfref(tmpdtfref);
		pk.setDtfbil(tmpdtfref);
		pk.setSeq(seqCnt);
		seqCnt+=1;
		farfdtf.setId(pk);
		
		//Set Rest 
		farfdtf.setSubseq(0);
		farfdtf.setDebcode(freshed.getDebcode());
		farfdtf.setDtftype("2");
		farfdtf.setDtffamt(new java.math.BigDecimal(0));
		farfdtf.setDtfbal1(new java.math.BigDecimal(0));
		farfdtf.setDtffbal(new java.math.BigDecimal(0));
		farfdtf.setDtffbal1(new java.math.BigDecimal(0));
		farfdtf.setDtfremarks("");
		farfdtf.setDtffgst(new java.math.BigDecimal(0));
		farfdtf.setDtfchqno("");
		farfdtf.setGpost("");
		farfdtf.setOpbal("N");
		farfdtf.setAccoddb("");
		farfdtf.setAccodcr("");
		farfdtf.setDtfchqdat(null);
		farfdtf.setMinvno("");
		farfdtf.setDtfbranch("");
		farfdtf.setDtfnbt(new java.math.BigDecimal(0));
		farfdtf.setDtffnbt(new java.math.BigDecimal(0));
		farfdtf.setNbtper(new java.math.BigDecimal(0));
		farfdtf.setVatper(new java.math.BigDecimal(0));
		
		farfdtf.setCurcode("LKR");
		farfdtf.setCurrate(new java.math.BigDecimal(1));
		farfdtf.setGainloss(new java.math.BigDecimal(0));
		farfdtf.setCoscod("");
		farfdtf.setAgnno(freshed.getAgrno()); //IMPORTANT
		farfdtf.setResno(tmp.getResno());
		farfdtf.setRegno(tmp.getRegno());
		farfdtf.setTaxcomcode(tmp.getTaxcomcode());
		farfdtf.setQty(0);
		farfdtf.setHiretypeid(tmp.getHiretypeid());		
		farfdtf.setCompanyid(freshed.getCompanyid());
		
		return farfdtf;
	}
	@Transactional(propagation=Propagation.MANDATORY)
	private Farfdtf setFarfdtfHedVal2(Freshed freshed, FarfdtfTMP tmp, Farfdtf farfdtf){
		//Set PK
		String tmpdtfref="TMP00000000";
		pk=new FarfdtfPK();
		pk.setDtfref(tmpdtfref);
		pk.setDtfbil(tmpdtfref);
		pk.setSeq(seqCnt);
		seqCnt+=1;
		farfdtf.setId(pk);
		
		//Set Rest 
		farfdtf.setSubseq(0);
		farfdtf.setDebcode(freshed.getDebcode());
		farfdtf.setDtftype("p");
		farfdtf.setDtffamt(new java.math.BigDecimal(0));
		farfdtf.setDtfbal1(new java.math.BigDecimal(0));
		farfdtf.setDtffbal(new java.math.BigDecimal(0));
		farfdtf.setDtffbal1(new java.math.BigDecimal(0));
		farfdtf.setDtfremarks("");
		farfdtf.setDtffgst(new java.math.BigDecimal(0));
		farfdtf.setDtfchqno("");
		farfdtf.setGpost("");
		farfdtf.setOpbal("N");
		farfdtf.setAccoddb("");
		farfdtf.setAccodcr("");
		farfdtf.setDtfchqdat(null);
		farfdtf.setMinvno("");
		farfdtf.setDtfbranch("");
		farfdtf.setDtfnbt(new java.math.BigDecimal(0));
		farfdtf.setDtffnbt(new java.math.BigDecimal(0));
		farfdtf.setNbtper(new java.math.BigDecimal(0));
		farfdtf.setVatper(new java.math.BigDecimal(0));
		
		farfdtf.setCurcode("LKR");
		farfdtf.setCurrate(new java.math.BigDecimal(1));
		farfdtf.setGainloss(new java.math.BigDecimal(0));
		farfdtf.setCoscod("");
		farfdtf.setAgnno(freshed.getAgrno()); //IMPORTANT
		farfdtf.setResno(tmp.getResno());
		farfdtf.setRegno(tmp.getRegno());
		farfdtf.setTaxcomcode(tmp.getTaxcomcode());
		farfdtf.setQty(0);
		farfdtf.setHiretypeid(tmp.getHiretypeid());		
		farfdtf.setCompanyid(freshed.getCompanyid());
		
		return farfdtf;
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private List<Ftaxdet> loadTaxDet(String taxcomcode){
		try {
			em.flush();
			String query="SELECT td.taxcode,t.taxname,t.taxper,td.taxrate,td.taxseq FROM ftaxdet AS td,ftax AS t WHERE t.taxcode=td.taxcode AND td.taxcomcode='"+taxcomcode+"' ORDER BY td.taxseq";
			
			List<Object[]> l = em.createNativeQuery(query).getResultList();
			
			List<Ftaxdet> ac = new ArrayList<Ftaxdet>();
			Ftaxdet mf;
			FtaxdetPK pk;
			
			String taxcode;
			String taxname;
			java.math.BigDecimal taxper;
			java.math.BigDecimal taxrate;
			java.math.BigDecimal taxseq;
			
			for(Object[] r : l){
				taxcode=(String)r[0];
				taxname=(String)r[1];
				taxper=(java.math.BigDecimal)r[2];
				taxrate=(java.math.BigDecimal)r[3];
				taxseq=(java.math.BigDecimal)r[4];
				
				mf=new Ftaxdet();
				pk=new FtaxdetPK();
				
				pk.setTaxcomcode(taxcomcode);
				pk.setTaxcode(taxcode);
				mf.setId(pk);
				mf.setTaxname(taxname);
				mf.setTaxper(taxper);
				mf.setTaxrate(taxrate);
				mf.setTaxseq(taxseq);
				
				ac.add(mf);
			}
			return ac;
		} catch (Exception e) {
			System.out.println("loadTaxDet: " + e.getMessage());
			e.printStackTrace();
		}
		finally{em.flush();}
		return null;
	}

	@Transactional
	//@Override
	public String saveInvoiceP(Finvhed finvhed,ArrayCollection acInvDet,List<Farfdtf> farfdtfList,List<Fdtftax> fdtftaxList) {
		try {
			//Finvhed finvhed=(Finvhed)invCol.get(0); //Invoice Hed 
			//ArrayCollection acInvDet=(ArrayCollection)invCol.get(1); //Invoice Detail
			//List<Farfdtf> farfdtfsList=(List<Farfdtf>)invCol.get(2); //Farfdtf
			//List<Fdtftax> fdtftaxsList=(List<Fdtftax>)invCol.get(3); //Fdtftax
			em.flush();
			ArrayList distResList=new ArrayList();
			for(int g=0;g<acInvDet.size();g++){
				ASObject detAso=(ASObject)acInvDet.get(g);
				String rno=detAso.get("resno").toString(); //reservation no
				Boolean sFound=false;
				for(int h=0;h<distResList.size();h++){
					detAso=(ASObject)distResList.get(h);
					if(detAso.get("resno").equals(rno)==true)
						sFound=true;
				}
				if(sFound==false){
					detAso=new ASObject();
					detAso.put("resno", rno);
					distResList.add(detAso);
				}
			}
			
			String paraList="";
			for(int xx=0;xx<distResList.size();xx++){
				ASObject detAso=(ASObject)distResList.get(xx);
				paraList="'"+detAso.get("resno").toString()+"'";
				if(xx<(distResList.size()-1))
					paraList+=",";
			}
			
			String chkDupLstQuery="SELECT COUNT(*) FROM farfdtf1 WHERE resno IN ("+paraList+") AND dtftype=2";
			System.out.println("chkDupLstQuery: " + chkDupLstQuery);
			
			int rptCnt=Integer.parseInt(em.createNativeQuery(chkDupLstQuery).getSingleResult().toString());
			if(rptCnt>0){
				return "Invoice Has Been Already Raised For Selected Reservation(s)";
			}		
			
			Calendar curDate = Calendar.getInstance();
			String txnTime=curDate.get(Calendar.HOUR) + ":" + curDate.get(Calendar.MINUTE);
			if(curDate.get(Calendar.AM_PM)==0)
				txnTime+=" AM";
			else 
				txnTime+=" PM";
			curDate=fmaintenanceDAO.resetTime(curDate);
			String companycode=getCompanyCode(finvhed.getAgrno());
			String refNo=fcompanysettingDAO.getRefNo("IN"+companycode, curDate.get(Calendar.YEAR), ((curDate.get(Calendar.MONTH))+1));
			
			//Persist Invoice Hed
			finvhed.setTxndate(curDate);
			finvhed.setInvno(refNo);
			finvhed.setAdduser(userInfoSRV.getUser());
			finvhed.setAddmach(userInfoSRV.getMachineName());
			finvhed.setAdddate(curDate);
			em.persist(finvhed);
			em.flush();
			System.out.println("persist invoice hed");
			
			//Persists Invoice Detail
			Finvdet finvdet;
			FinvdetPK pk;
			for(int a=0;a<acInvDet.size();a++){
				ASObject aso=(ASObject)acInvDet.get(a);
				pk=new FinvdetPK();
				finvdet=new Finvdet();
				
				pk.setInvno(refNo);
				pk.setSeq(Integer.parseInt(aso.get("seq").toString()));
				finvdet.setId(pk);
				
				finvdet.setResno(aso.get("resno").toString());
				finvdet.setHiretypeid(aso.get("hiretypeid").toString());
				
				java.util.Date dtFrom=(java.util.Date)aso.get("dfrom");
				Calendar calDFrom=Calendar.getInstance();
				calDFrom.setTime(dtFrom);
				finvdet.setDfrom(calDFrom);
				
				java.util.Date dtTo=(java.util.Date)aso.get("dto");
				Calendar calDTo=Calendar.getInstance();
				calDTo.setTime(dtTo);
				finvdet.setDto(calDTo);
				System.out.println("\n\n\n\n\n=============================\ninvoice_description: "+aso.get("description").toString());
				finvdet.setDescription(aso.get("description").toString());
				finvdet.setRegno(aso.get("regno").toString());
				finvdet.setRate(new BigDecimal(aso.get("rate").toString()));
				finvdet.setKm(new BigDecimal(aso.get("km").toString()));
				finvdet.setDays(new BigDecimal(aso.get("days").toString()));
				finvdet.setQty(Integer.parseInt(aso.get("qty").toString()));
				finvdet.setAmount(new BigDecimal(aso.get("amount").toString()));
				finvdet.setTaxcomcode(aso.get("taxcomcode").toString());
				finvdet.setType(aso.get("type").toString());
				finvdet.setSubseq(Integer.parseInt(aso.get("subseq").toString()));
				finvdet.setAdduser(userInfoSRV.getUser());
				finvdet.setAddmach(userInfoSRV.getMachineName());
				finvdet.setAdddate(curDate);
				
				em.persist(finvdet);
				em.flush();
			}
			System.out.println("persist invoice det");
			

			//Persist fdtftax
			for(int d=0;d<fdtftaxList.size();d++){
				Fdtftax tax=fdtftaxList.get(d);
				tax.getId().setDtfref(refNo);
				em.persist(tax);
				em.flush();				
			}
			
			//Update Cohirestsid To Invoice [Invoice Raised]
			for(int a=0;a<distResList.size();a++){
				ASObject detAso=(ASObject)distResList.get(a);
				Freservation freservation=em.find(Freservation.class, detAso.get("resno").toString());
				freservation.setCohirestsid("INVOICE");
				em.merge(freservation);
				em.flush();
			}
			
			return refNo;
		} catch (Exception e) {
			System.out.println("FarfdtfDAOImpl saveInvoice: " + e.getMessage());
			e.printStackTrace();
		}
		finally{em.flush();}
		return "";
	}
	
	@Transactional
	public String getCompanyCode(String agrno){
		// select companyid from freshed where agrno in (select agrno from
		// freservation where resno='RS/1412/00005')
		String query = "select companyid from freshed where agrno in ('"+agrno+"')";
		Query q = em.createNativeQuery(query);
		System.out.println("getCompanyCode #QueryXXXX# : " + query);
		String code = (String)q.getSingleResult();
		System.out.println("code.substring(1) : " + code.substring(1));
/*		for (String[] r : l) {
			System.out.println("getCompanyCode : " + r);
			String code=r.toString();
			System.out.println("code.substring(1) : " + code.substring(1));
			return code.substring(1);
		}*/
		return code.substring(1);
	}
	@Transactional
	@Override
	public synchronized String saveInvoice(Finvhed finvhed,ArrayCollection acInvDet,List<Farfdtf> farfdtfList,List<Fdtftax> fdtftaxList) {
		try {
			//Finvhed finvhed=(Finvhed)invCol.get(0); //Invoice Hed 
			//ArrayCollection acInvDet=(ArrayCollection)invCol.get(1); //Invoice Detail
			//List<Farfdtf> farfdtfsList=(List<Farfdtf>)invCol.get(2); //Farfdtf
			//List<Fdtftax> fdtftaxsList=(List<Fdtftax>)invCol.get(3); //Fdtftax
			em.flush();
			ArrayList distResList=new ArrayList();
			for(int g=0;g<acInvDet.size();g++){
				ASObject detAso=(ASObject)acInvDet.get(g);
				String rno=detAso.get("resno").toString(); //reservation no
				Boolean sFound=false;
				for(int h=0;h<distResList.size();h++){
					detAso=(ASObject)distResList.get(h);
					if(detAso.get("resno").equals(rno)==true)
						sFound=true;
				}
				if(sFound==false){
					detAso=new ASObject();
					detAso.put("resno", rno);
					distResList.add(detAso);
				}
			}
			
			String paraList="";
			for(int xx=0;xx<distResList.size();xx++){
				ASObject detAso=(ASObject)distResList.get(xx);
				paraList="'"+detAso.get("resno").toString()+"'";
				if(xx<(distResList.size()-1))
					paraList+=",";
			}
			
			String chkDupLstQuery="SELECT COUNT(*) FROM farfdtf WHERE resno IN ("+paraList+") AND dtftype=2";
			System.out.println("chkDupLstQuery: " + chkDupLstQuery);
			
			int rptCnt=Integer.parseInt(em.createNativeQuery(chkDupLstQuery).getSingleResult().toString());
			if(rptCnt>0){
				return "Invoice Has Been Already Raised For Selected Reservation(s)";
			}		
			
			Calendar curDate = Calendar.getInstance();
			String txnTime=curDate.get(Calendar.HOUR) + ":" + curDate.get(Calendar.MINUTE);
			if(curDate.get(Calendar.AM_PM)==0)
				txnTime+=" AM";
			else 
				txnTime+=" PM";
			curDate=fmaintenanceDAO.resetTime(curDate);
			String companycode=getCompanyCode(finvhed.getAgrno());
			String refNo=fcompanysettingDAO.getRefNo("IN"+companycode, curDate.get(Calendar.YEAR), ((curDate.get(Calendar.MONTH))+1));
//			String refNo=fcompanysettingDAO.getRefNo("INV", curDate.get(Calendar.YEAR), ((curDate.get(Calendar.MONTH))+1)); commented on 2015.05.14 for invoice number change
			
			System.out.println("refno "+refNo);
			//Persist Invoice Hed
			finvhed.setTxndate(curDate);
			finvhed.setInvno(refNo);
			finvhed.setAdduser(userInfoSRV.getUser());
			finvhed.setAddmach(userInfoSRV.getMachineName());
			finvhed.setAdddate(curDate);
			em.persist(finvhed);
			em.flush();
			System.out.println("persist invoice hed");
			
			//Persists Invoice Detail
			Finvdet finvdet;
			FinvdetPK pk;
			for(int a=0;a<acInvDet.size();a++){
				ASObject aso=(ASObject)acInvDet.get(a);
				pk=new FinvdetPK();
				finvdet=new Finvdet();
				
				pk.setInvno(refNo);
				pk.setSeq(Integer.parseInt(aso.get("seq").toString()));
				finvdet.setId(pk);
				
				finvdet.setResno(aso.get("resno").toString());
				finvdet.setHiretypeid(aso.get("hiretypeid").toString());
				
				java.util.Date dtFrom=(java.util.Date)aso.get("dfrom");
				Calendar calDFrom=Calendar.getInstance();
				calDFrom.setTime(dtFrom);
				finvdet.setDfrom(calDFrom);
				
				java.util.Date dtTo=(java.util.Date)aso.get("dto");
				Calendar calDTo=Calendar.getInstance();
				calDTo.setTime(dtTo);
				finvdet.setDto(calDTo);
				System.out.println("\n\n\n\n\n=============================\ninvoice_description: "+aso.get("description").toString());
				finvdet.setDescription(aso.get("description").toString());
				finvdet.setRegno(aso.get("regno").toString());
				finvdet.setRate(new BigDecimal(aso.get("rate").toString()));
				finvdet.setKm(new BigDecimal(aso.get("km").toString()));
				finvdet.setDays(new BigDecimal(aso.get("days").toString()));
				finvdet.setQty(Integer.parseInt(aso.get("qty").toString()));
				finvdet.setAmount(new BigDecimal(aso.get("amount").toString()));
				finvdet.setTaxcomcode(aso.get("taxcomcode").toString());
				finvdet.setType(aso.get("type").toString());
				finvdet.setSubseq(Integer.parseInt(aso.get("subseq").toString()));
				finvdet.setAdduser(userInfoSRV.getUser());
				finvdet.setAddmach(userInfoSRV.getMachineName());
				finvdet.setAdddate(curDate);
				
				em.persist(finvdet);
				em.flush();
			}
			System.out.println("persist invoice det");
			
			/*
			
			Email By Chamila
			Sanka,

			Sum following fields.
			dtfamt
			dtffamt

			dtfgst
			dtffgst 

			dtfnbt
			dtffnbt


			Further:
			 * dtfamt is the total customer should pay in LKR
			 * dtffamt is the total customer should pay in foreign currency (if the invoice is in foreign currency)
			 * dtfgst is total VAT from LKR (dtfamt is VAT included amount)
			 * dtffgst is foreign currency VAT amount (if the invoice is in foreign currency & if any VAT)
			 * dtfnbt & dtffnbt are for NBT & behave same as dtfgst & dtffgst fields
			 * dtfbal should be equal to dtfamt

			 * dtffbal should be equal to dtffamt
			 */
			
			//original code commented for Chamila's/Menaka request 2014.03.15
			//start: added by sanka for chamila request
			//Persist farfdtf
			/*			if(farfdtfList.size()>0)
			{
			Farfdtf mfSummary=new Farfdtf();
			mfSummary=farfdtfList.get(0);
			mfSummary.getId().setDtfref(refNo);
			mfSummary.getId().setDtfbil(refNo);
			mfSummary.setTxndate(curDate);
			mfSummary.setDtfdate(curDate);
			mfSummary.setTxntime(txnTime);
			mfSummary.setTxnuser(userInfoSRV.getUser());
			for(int a=0;a<farfdtfList.size();a++){
				Farfdtf mf=farfdtfList.get(a);
				System.out.println("farfdtf companyid: " + mf.getCompanyid());
				
				mfSummary.setDtfamt(mf.getDtfamt().add(mfSummary.getDtfamt()) );
				mfSummary.setDtffamt(mf.getDtffamt().add(mfSummary.getDtffamt()) );
				mfSummary.setDtfgst(mf.getDtfgst().add(mfSummary.getDtfgst()) );
				mfSummary.setDtffgst(mf.getDtffgst().add(mfSummary.getDtffgst()) );
				mfSummary.setDtfnbt(mf.getDtfnbt().add(mfSummary.getDtfnbt()) );
				mfSummary.setDtffnbt(mf.getDtffnbt().add(mfSummary.getDtffnbt()) );
				if(! mfSummary.getResno().contains(mf.getResno()))
				{
					mfSummary.setResno(mfSummary.getResno()+","+mf.getResno());
				}
				if(! mfSummary.getRegno().contains(mf.getRegno()))
				{
					mfSummary.setRegno(mfSummary.getRegno()+","+mf.getRegno());
				}
				if(! mfSummary.getType().contains(mf.getType()))
				{
					mfSummary.setType(mfSummary.getType()+","+mf.getType());
				}
			}
			em.persist(mfSummary);
			em.flush();
			}*/
			//end:added by sanka for chamila request
			
			
			//original code commented for Chamila's/Menaka request 2014.03.15
			//Persist farfdtf
			/* 			for(int a=0;a<farfdtfList.size();a++){
				Farfdtf mf=farfdtfList.get(a);
				System.out.println("farfdtf companyid: " + mf.getCompanyid());
				mf.getId().setDtfref(refNo);
				mf.getId().setDtfbil(refNo);
				mf.setTxndate(curDate);
				mf.setDtfdate(curDate);
				mf.setTxntime(txnTime);
				mf.setTxnuser(userInfoSRV.getUser());
				em.persist(mf);
				em.flush();
			} 
			 */
			//Persist fdtftax
			for(int d=0;d<fdtftaxList.size();d++){
				Fdtftax tax=fdtftaxList.get(d);
				tax.getId().setDtfref(refNo);
				em.persist(tax);
				em.flush();				
			}
			
			//Update Cohirestsid To Invoice [Invoice Raised]
			for(int a=0;a<distResList.size();a++){
				ASObject detAso=(ASObject)distResList.get(a);
				Freservation freservation=em.find(Freservation.class, detAso.get("resno").toString());
				freservation.setCohirestsid("INVOICE");
				freservation.setInvoiced(userInfoSRV.getUser());
				freservation.setInvoicedate(Calendar.getInstance());
				freservation.setInvoicedApplication("FO");
				em.merge(freservation);
				em.flush();
			}
			
			return refNo;
		} catch (Exception e) {
			System.out.println("FarfdtfDAOImpl saveInvoice: " + e.getMessage());
			e.printStackTrace();
		}
		finally{em.flush();}
		return "";
	}
	
	@Transactional(readOnly=false)
	@Override
	public int count() {
		em.flush();
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Finvhed").getSingleResult());
	}
	
	@Transactional
	@Override
	public List<Finvhed> getInvoiceList(int startIndex, int numItems) {
		try {
			em.flush();
			String query="SELECT h.invno,h.txndate,";
//			String query="SELECT h.invno,h.txndate,";
			query += " (SELECT debname FROM fdebtor WHERE debcode=h.debcode) AS debname,";
			query += " (SELECT agrno +','+ ((SELECT STUFF((SELECT distinct ', ' + RTRIM(resno) FROM finvdet as d WHERE d.invno=h.invno FOR XML PATH('')),1, 1, ''))) FROM freshed WHERE agrno=h.agrno) AS agrno,";
			query += " h.adduser";
			query += " FROM finvhed AS h ORDER BY h.recordid DESC";
			
			Query q = em.createNativeQuery(query);
			List<Object[]> l = q.setFirstResult(startIndex).setMaxResults(numItems).getResultList();
			List<Finvhed> list=new ArrayList<Finvhed>();
			for(Object[] r:l){					
				Finvhed finvhed=new Finvhed();
				finvhed.setInvno((String)r[0]);
				
				Timestamp txndate=(Timestamp)r[1];
				finvhed.setTxndatets(txndate);
				
				Calendar calTxndate=Calendar.getInstance();
				java.util.Date dtTxnDate=new java.util.Date(txndate.getTime());
				calTxndate.setTime(dtTxnDate);
				finvhed.setTxndate(calTxndate);

				finvhed.setDebname((String)r[2]);
				finvhed.setAgrno((String)r[3]);
				finvhed.setAdduser((String)r[4]);

				list.add(finvhed);
			}
			l=null;
			return list;
		} catch (Exception e) {
			System.out.println("getInvoiceList: " + e.getMessage());
		}
		finally
		{em.flush();}
		return null;
	}
	@Transactional
	@Override
	public List<Finvhed> getInvoiceList() {
		try {
			em.flush();
			String query="SELECT h.invno,h.txndate,";
			query += " (SELECT debname FROM fdebtor WHERE debcode=h.debcode) AS debname,";
			query += " h.adduser";
			query += " FROM finvhed AS h ORDER BY h.recordid DESC";
			
			Query q = em.createNativeQuery(query);
			List<Object[]> l = q.getResultList();
			List<Finvhed> list=new ArrayList<Finvhed>();
			for(Object[] r:l){					
				Finvhed finvhed=new Finvhed();
				finvhed.setInvno((String)r[0]);
				
				Timestamp txndate=(Timestamp)r[1];
				finvhed.setTxndatets(txndate);
				
				Calendar calTxndate=Calendar.getInstance();
				java.util.Date dtTxnDate=new java.util.Date(txndate.getTime());
				calTxndate.setTime(dtTxnDate);
				finvhed.setTxndate(calTxndate);
				
				finvhed.setDebname((String)r[2]);
				finvhed.setAdduser((String)r[3]);
				
				list.add(finvhed);
			}
			l=null;
			return list;
		} catch (Exception e) {
			System.out.println("getInvoiceList: " + e.getMessage());
		}
		finally{em.flush();}
		return null;
	}
	
	@Transactional
	@Override
	public List<Finvhed> getInvoiceListFeelLucky(String searchText) {
		try {
			em.flush();
			String query="SELECT h.invno,h.txndate,";
			query += " (SELECT debname FROM fdebtor WHERE debcode=h.debcode) AS debname,";
			query += " (SELECT agrno +','+ ((SELECT STUFF((SELECT distinct ', ' + RTRIM(resno) FROM finvdet as d WHERE d.invno=h.invno FOR XML PATH('')),1, 1, ''))) FROM freshed WHERE agrno=h.agrno) AS agrno,";
			query += " h.adduser";
			query += " FROM finvhed AS h " +
					" join fdebtor fd on h.debcode=fd.debcode " +
					" join finvdet on h.invno=finvdet.invno " +
					"WHERE h.invno LIKE '%"+searchText+"%' OR h.agrno LIKE '%"+searchText+"%' " +
					"OR finvdet.resno LIKE '%"+searchText+"%'" +
					"OR fd.debcode LIKE '%"+searchText+"%'" +
					"OR fd.debname LIKE '%"+searchText+"%'" +
					"ORDER BY h.invno DESC";
			
			Query q = em.createNativeQuery(query);
			List<Object[]> l = q.getResultList();
			List<Finvhed> list=new ArrayList<Finvhed>();
			for(Object[] r:l){					
				Finvhed finvhed=new Finvhed();
				finvhed.setInvno((String)r[0]);
				
				Timestamp txndate=(Timestamp)r[1];
				finvhed.setTxndatets(txndate);
				
				Calendar calTxndate=Calendar.getInstance();
				java.util.Date dtTxnDate=new java.util.Date(txndate.getTime());
				calTxndate.setTime(dtTxnDate);
				finvhed.setTxndate(calTxndate);
				
				finvhed.setDebname((String)r[2]);
				finvhed.setAgrno((String)r[3]);
				finvhed.setAdduser((String)r[4]);
				
				list.add(finvhed);
			}
			l=null;
			return list;
		} catch (Exception e) {
			System.out.println("getInvoiceList: " + e.getMessage());
		}
		finally{em.flush();}
		return null;
	}

	@Transactional
	@Override
	public Finvhed getInvHedByInvNo(String invno) {
		try {
			em.flush();
			String query="SELECT h.invno,h.txndate,h.agrno,h.debcode,";
			query += " (SELECT billname FROM fdebtor WHERE debcode=h.debcode) AS debname,";
			query += " (SELECT debadd FROM fdebtor WHERE debcode=h.debcode) AS debadd,";
			query += " (SELECT tel FROM fdebtor WHERE debcode=h.debcode) AS tel,";
			query += " h.adduser,h.addmach,h.adddate,";
			query += " (SELECT companyid FROM freshed WHERE agrno=h.agrno) AS companyid,";
			query += " (SELECT description FROM fcompany WHERE companyid=(SELECT companyid FROM freshed WHERE agrno=h.agrno)) AS company,";
			query += " (SELECT vatno FROM fdebtor WHERE debcode=h.debcode),";
			query += " (SELECT vattyp FROM fdebtor WHERE debcode=h.debcode),";
			query += " (SELECT svatno FROM fdebtor WHERE debcode=h.debcode),";
			query += " (select top 1 freservation.remarks_customer from finvhed join freservation on h.agrno=freservation.agrno),";
			query += " (select telephone  from Subcustomer where name in (select top 1 freservation.remarks_customer from finvhed join freservation on h.agrno=freservation.agrno))";
			query += " FROM finvhed AS h";
			query += " WHERE invno='"+invno+"'";
	
			//select top 1 freservation.remarks_customer from finvhed join freservation on finvhed.agrno=freservation.agrno
			
			String newline = System.getProperty("line.separator");
			
			Query q = em.createNativeQuery(query);
			List<Object[]> l = q.getResultList();
			Object[] r=l.get(0);
						
			Finvhed finvhed=new Finvhed();
			finvhed.setInvno((String)r[0]);
			
			Timestamp txndate=(Timestamp)r[1];
			finvhed.setTxndatets(txndate);
			
			Calendar calTxndate=Calendar.getInstance();
			java.util.Date dtTxnDate=new java.util.Date(txndate.getTime());
			calTxndate.setTime(dtTxnDate);
			finvhed.setTxndate(calTxndate);
			
			finvhed.setAgrno((String)r[2]);
			finvhed.setDebcode((String)r[3]);
			finvhed.setDebname((String)r[4]);
			finvhed.setDebadd((String)r[5]);
			finvhed.setTel((String)r[6]);
			finvhed.setAdduser((String)r[7]);
			finvhed.setAddmach((String)r[8]);
			

			finvhed.setDebinfo((String)r[4] + newline + (String)r[5] + newline + (String)r[6]);
			finvhed.setCusvatno((String)r[12]);
			finvhed.setVattyp((String)r[13]);
			finvhed.setVattyp((String)r[13]);
			finvhed.setSvatno((String)r[14]);
/*			if( (String)r[12]!=null && ((String)r[12]).trim()!="")
			{
				finvhed.setDebinfo(finvhed.getDebinfo());
			}*/
			
			finvhed.setCompanyid((String)r[10]);
			finvhed.setCompany((String)r[11]);
			
			Timestamp adddate=(Timestamp)r[9];
			Calendar calAddDate=Calendar.getInstance();
			java.util.Date dtAddDate=new java.util.Date(adddate.getTime());
			calAddDate.setTime(dtAddDate);
			finvhed.setAdddate(calAddDate);

			if((String)r[15] !=null)
			{
				finvhed.setByWhomOrdered((String)r[15]);
				if((String)r[16] !=null){
					finvhed.setByWhomOrdered( finvhed.getByWhomOrdered()/*+ newline*/+" Tel :"+(String)r[16]);
				}
			}
			
			if(!finvhed.getCompany().contains("Mal-Key Rent-a-Car Pvt Ltd"))
			{
				finvhed.setAgentText("Agent For Mal-Key Rent-a-Car (Pvt.) Ltd");
			}
			if(!finvhed.getCompany().contains("Mal-Key Rent-a-Car Pvt Ltd"))
			{
				finvhed.setChequeText("Please Draw Cheque in Favour of :\nMahesh E. Mallawaratchie Enterprises.\n\nVat No: 443600051 7000");
			}
			else
			{
				finvhed.setChequeText("Please Draw Cheque in Favour of :\nMal-Key Rent A Car (Pvt) Ltd.");
			}
			
			
			return finvhed;
		} catch (Exception e) {
			System.out.println("getInvHedByInvNo: " + e.getMessage());
		}
		finally{em.flush();}
		return null;
	}
	@Transactional
	@Override
	public List<FinvdetRPT> getInvDetByInvNo(String invno,boolean flag)
	{
		List<FinvdetRPT> l= getInvDetByInvNo(invno);
		if(flag)
		{
			for (int i = 0; i < l.size(); i++) {
				FinvdetRPT finvdetRPT = l.get(i);
				finvdetRPT.setHiretypeid("PRO FORMA  INVOICE");
			}
		}
		return l;
	}
	@Transactional
	@Override
	public List<FinvdetRPT> getInvDetByInvNo(String invno) {
		try {
			em.flush();
			String query="SELECT d.resno,d.hiretypeid,";
			query += " (SELECT description FROM fhiretype WHERE hiretypeid=d.hiretypeid) AS hiretype,";
			query += " d.dfrom,d.dto,d.description,d.regno,d.rate,d.km,d.days,d.qty,d.amount,";
			query += " (select vattyp from fdebtor where debcode in (select debcode from finvhed WHERE invno=d.invno) )as vattyp,";//added on 2014.02.05
			query += " (SELECT STUFF((SELECT ', ' + RTRIM(em.name) FROM femployee as em WHERE em.empid in (select rd.empid from fresdriver as rd where rd.resno=d.resno and rd.priority=1 and rd.issrv=0) FOR XML PATH('')),1, 1, '') ) as driver";//added on 2014.02.22
			query += " FROM finvdet AS d";
			query += " WHERE invno='"+invno+"'";
			query += " ORDER BY d.seq,d.subseq";

//select finvdet.*, (select sum(fdtftax.taxamt)  from finvdet  join fdtftax on fdtftax.dtfref=finvdet.invno and finvdet.type=fdtftax.type
//		where finvdet.invno='IN/1407/00001' group by fdtftax.type) as total_tax from finvdet where invno='IN/1407/00001'
						
						
			System.out.println("getInvDetByInvNo #Query# : "+query);
			Query q = em.createNativeQuery(query);
			List<Object[]> l = q.getResultList();
			List<FinvdetRPT> list=new ArrayList<FinvdetRPT>();
			FinvdetRPT rpt;
			String description;
			String newline = System.getProperty("line.separator");
			int seq=0;
			for(Object[] r:l){
				rpt=new FinvdetRPT();
				rpt.setResno((String)r[0]);
				String hiretypeid=(String)r[1];
				hiretypeid=hiretypeid.trim();
				if(hiretypeid.length()>2){
					hiretypeid=hiretypeid.substring(0,1);
					r[2]="With Driver";
				}
				rpt.setHiretypeid(hiretypeid);
				System.out.println("(String)r[2] :"+(String)r[2]);
				rpt.setHiretype((String)r[2] + getTaxTypeDecorded((String)r[12])+" Invoice");////added on 2014.02.05
				System.out.println("rpt.setHiretype :"+rpt.getHiretype());
				System.out.println("rpt.setHiretypeID :"+rpt.getHiretypeid());
				rpt.setDfrom((Timestamp)r[3]);
				rpt.setDto((Timestamp)r[4]);
				
				description=(String)r[5];
				
				if(seq==0 && !hiretypeid.equalsIgnoreCase("SD") /*(hiretypeid.equalsIgnoreCase("WD") || hiretypeid.equalsIgnoreCase("WEDDING"))*/)
				{
					description += newline+"Driver:"+(String)r[13];
				}
					
				description=description.replace("\\r",newline);

				rpt.setDescription(description);
				
				rpt.setRegno((String)r[6]);
				rpt.setRate((BigDecimal)r[7]);
				rpt.setKm((BigDecimal)r[8]);
//				System.out.println("r[9] :"+r[9]);
//				System.out.println("r[9] :"+new BigDecimal(r[9].toString()));
				rpt.setDays(new BigDecimal(r[9].toString()));
				rpt.setQty(new BigDecimal(r[10].toString()));
				rpt.setAmount((BigDecimal)r[11]);
				
				list.add(rpt);
				seq++;
			}
			l=null;
			em.flush();
			return list;
		} catch (Exception e) {
			System.out.println("genInvDetByInvNo: " + e.getMessage());
		}
		finally{em.flush();}
		return null;
	}
	
	
	@Transactional
	@Override
	public List<FinvdetRPT> getInvDetByInvNo2(String invno) {
		try {
			em.flush();
			String query="SELECT d.resno,d.hiretypeid,";
			query += " (SELECT description FROM fhiretype WHERE hiretypeid=d.hiretypeid) AS hiretype,";
			query += " d.dfrom,d.dto,d.description,d.regno,d.rate,d.km,d.days,d.qty,d.amount,";
			query += " (select vattyp from fdebtor where debcode in (select debcode from finvhed WHERE invno=d.invno) )as vattyp,";//added on 2014.02.05
			query += " (SELECT STUFF((SELECT ', ' + RTRIM(em.name) FROM femployee as em WHERE em.empid in (select rd.empid from fresdriver as rd where rd.resno=d.resno and rd.priority=1 and rd.issrv=0) FOR XML PATH('')),1, 1, '') ) as driver";//added on 2014.02.22
			query += " ,d.type,d.seq,d.subseq ";
			query += " FROM finvdet AS d";
			query += " WHERE invno='"+invno+"'";
			query += " ORDER BY d.seq,d.subseq";
			
//select finvdet.*, (select sum(fdtftax.taxamt)  from finvdet  join fdtftax on fdtftax.dtfref=finvdet.invno and finvdet.type=fdtftax.type
//		where finvdet.invno='IN/1407/00001' group by fdtftax.type) as total_tax from finvdet where invno='IN/1407/00001'
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");//DD/MM/YYYY
			//java.util.Date fromDate=format.parse(fromDateStr);
			
			System.out.println("getInvDetByInvNo2 #Query2# : "+query);
			Query q = em.createNativeQuery(query);
			System.out.println("getInvDetByInvNo2 #Query3# : "+query);
			List<Object[]> l = q.getResultList();
			List<FinvdetRPT> list=new ArrayList<FinvdetRPT>();
			List<FinvdetRPT> list2=new ArrayList<FinvdetRPT>();
			FinvdetRPT rpt;
			String description;
			String newline = System.getProperty("line.separator");
			int seq=0;
			boolean isTax=false;
			boolean isSVAT=false;
			int itemIndex=0;
			String taxType=null;
			for(Object[] r:l){
				System.out.println("for(Object[] r:l itemIndex= "+itemIndex++);
				rpt=new FinvdetRPT();
				rpt.setResno((String)r[0]);
				String hiretypeid=(String)r[1];
				String type=((String)r[14]).trim();
				hiretypeid=hiretypeid.trim();
				rpt.setTaxOrderText("*VAT is charged after considering the NBT");
				System.out.println("#hiretypeid "+hiretypeid);
				if(hiretypeid.length()>2){
					hiretypeid=hiretypeid.substring(0,1);
					r[2]="With Driver";
				}
				rpt.setHiretypeid(hiretypeid);
//				if(hiretypeid.equalsIgnoreCase("WD") || hiretypeid.equalsIgnoreCase("WEDDING"))
					if(!hiretypeid.equalsIgnoreCase("SD"))
				{
					System.out.println("#hiretypeid 1 "+hiretypeid);
					rpt.setTaxOrderText("");
				}
				System.out.println("#rpt.setTaxOrderText "+rpt.getTaxOrderText());
//				System.out.println("(String)r[2] :"+(String)r[2]);
				if(taxType==null){
					taxType=(String)r[12];
					System.out.println("taxType :"+taxType);
				}
				
				rpt.setHiretype((String)r[2] + getTaxTypeDecorded((String)r[12])+" Invoice");////added on 2014.02.05
				System.out.println("getTaxTypeDecorded "+rpt.getHiretype());
//				System.out.println("rpt.setHiretype :"+rpt.getHiretype());
//				System.out.println("rpt.setHiretypeID :"+rpt.getHiretypeid());
				rpt.setDfrom((Timestamp)r[3]);
				rpt.setDto((Timestamp)r[4]);
				System.out.println("XXX");
				description=(String)r[5];
				System.out.println("yyy");
				if(seq==0 && !hiretypeid.equalsIgnoreCase("SD") /*(hiretypeid.equalsIgnoreCase("WD") || hiretypeid.equalsIgnoreCase("WEDDING"))*/)
				{
					description += newline+"Driver:"+(String)r[13];
					System.out.println("description : "+description);
				}
				
				description=description.replace("\\r",newline);
				System.out.println("zzz");
				rpt.setDescription(description);
				
				rpt.setRegno((String)r[6]);
				rpt.setRate((BigDecimal)r[7]);
				rpt.setKm((BigDecimal)r[8]);
//				System.out.println("r[9] :"+r[9]);
//				System.out.println("r[9] :"+new BigDecimal(r[9].toString()));
				rpt.setDays(new BigDecimal(r[9].toString()));
				rpt.setQty(new BigDecimal(r[10].toString()));
				rpt.setAmount((BigDecimal)r[11]);
				
				

				System.out.println("D TAX TYPE: "+(String)r[12]);
				if(((String)r[12]).trim().equalsIgnoreCase("T") || ((String)r[12]).trim().equalsIgnoreCase("S")){
					try{
						isTax=true;
						
						String query2="select sum(fdtftax.taxamt) from fdtftax where resno='"+rpt.getResno()+"' and dtfref='"+invno+"' and fdtftax.type='"+(String)r[14]+"'  and fdtftax.seq='"+(Integer)r[15]+"'  and fdtftax.subseq='"+(Integer)r[16]+"' group by type,seq,subseq"; 
						Query q2 = em.createNativeQuery(query2);
						Object fdtftax_taxamt = BigDecimal.ZERO;
						try {
							fdtftax_taxamt = q2.getSingleResult();
						} catch (javax.persistence.NoResultException e) {
							fdtftax_taxamt=BigDecimal.ZERO;
						}
						BigDecimal fdtftax_taxamtB = (BigDecimal)fdtftax_taxamt;
						rpt.setAmount(rpt.getAmount().subtract(fdtftax_taxamtB));
						
						System.out.println("abc_description : "+description);
						if(((String)r[12]).trim().equalsIgnoreCase("S")){
							rpt.setDescription(prepareForDisplay(description));//need to remove
						}
						
						if(type!=null){
						if(type.equals("VR") || type.equals("OS") || type.equals("NO") || type.equals("DT")|| type.equals("EH") )
						{
							if(rpt.getDays().intValue()!=0){
							BigDecimal nontaxRateAmount=rpt.getAmount().divide(rpt.getDays() ,2, RoundingMode.HALF_UP);
							rpt.setRate(nontaxRateAmount);
							}
						}
						else if(type.equals("EM")  )
						{
							if(rpt.getKm().intValue()!=0){
							BigDecimal nontaxRateAmount=rpt.getAmount().divide(rpt.getKm(),2, RoundingMode.HALF_UP);
							rpt.setRate(nontaxRateAmount);
							}
						}
						else if(type.equals("AC")  )
						{
							if(rpt.getKm().intValue()!=0 && rpt.getQty().intValue()!=0){
							BigDecimal nontaxRateAmount=rpt.getAmount().divide(rpt.getKm().multiply(rpt.getQty()),2, RoundingMode.HALF_UP);
							rpt.setRate(nontaxRateAmount);
							}
						}
						}
						
		
/*					String query3="select taxcode,sum(fdtftax.taxamt),taxper from fdtftax where  dtfref='"+invno+"'  group by taxcode,taxper";		
						//String query3="select taxcode,taxamt,taxper from fdtftax where resno='"+rpt.getResno()+"' and dtfref='"+invno+"' and type='"+(String)r[14]+"' order by taxseq"; 
						Query q3 = em.createNativeQuery(query3);
						List<Object[]> listTaxDetails = q3.getResultList();
						for(Object[] record:listTaxDetails){
							
							FinvdetRPT cloned = (FinvdetRPT) rpt.clone();
							cloned.setRate(new BigDecimal(0));
							cloned.setDays(new BigDecimal(0));
							cloned.setAmount((BigDecimal)record[1]);
							cloned.setDescription(((String)record[0]).trim()+" "+(BigDecimal)record[2]+"% TAX AMOUNT"+newline+description);
							cloned.setTaxFlag(1);
							list2.add(cloned);
						}*/
						
						System.out.println("TAX TYPEZ: "+(String)r[12]);
						if(((String)r[12]).trim().equalsIgnoreCase("S")){
							isSVAT=true;
							if(type!=null && type.equals("VR")){
							rpt.setHiretype("SUSPENDED TAX INVOICE");
							description=rpt.getDescription().replace(newline,"");
							description=description.substring(0, description.length()-1);
							description=description+" - "+rpt.getRegno()+newline+"(VEHICLE RENT ON "+((String)r[2]).toUpperCase()+" BASIS)"+newline+
							" Period -From "+format.format(rpt.getDfrom())+" to "+format.format(rpt.getDto());
							rpt.setDescription(description);
//							System.out.println("#123"+description);
//							System.out.println("#(String)r[2]"+(String)r[2]);
//							System.out.println("#format.format(rpt.getDfrom())"+format.format(rpt.getDfrom()));
							}
						}
						
						}catch(Exception ee)
						{
							ee.printStackTrace();
						}
				}
				

				list.add(rpt);
				seq++;
			}
			
			
//-----------------------------------------------------------------------------------------------------------------------------------------			
			System.out.println("TAX TYPEX isTax: "+isTax);
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			if(isTax){
				String query3="select taxcode,sum(fdtftax.taxamt),taxper from fdtftax where  dtfref='"+invno+"'  group by taxcode,taxper";		
				//String query3="select taxcode,taxamt,taxper from fdtftax where resno='"+rpt.getResno()+"' and dtfref='"+invno+"' and type='"+(String)r[14]+"' order by taxseq"; 
				Query q3 = em.createNativeQuery(query3);
				List<Object[]> listTaxDetails = q3.getResultList();
				BigDecimal totalAmount=new BigDecimal(0);
				for(Object[] record:listTaxDetails){
					//System.out.println("(BigDecimal)record[1] "+(BigDecimal)record[1]);
					BigDecimal subtotal=(BigDecimal)record[1];
					totalAmount=totalAmount.add(subtotal);
//					System.out.println("total :"+totalAmount);
					FinvdetRPT cloned = new FinvdetRPT();//(FinvdetRPT) rpt.clone();
					cloned.setRate(new BigDecimal(0));
					cloned.setDays(new BigDecimal(0));
					cloned.setAmount((BigDecimal)record[1]);
					cloned.setDescription(((String)record[0]).trim()+" "+(BigDecimal)record[2]+" %");
					cloned.setTaxFlag(1);
					list2.add(cloned);
					System.out.println("total :::"+totalAmount);
				}
				if(isSVAT)
				{
					list2.clear();
					FinvdetRPT svatObject = new FinvdetRPT();//(FinvdetRPT) rpt.clone();
					svatObject.setRate(new BigDecimal(0));
					svatObject.setDays(new BigDecimal(0));
					//svatObject.setAmount(totalAmount);//following code commented for SVAT change request by Eranga CR2015-09-02
					/*					
					2015 Sep 2
					Dear Mr. Sanka,
					Scanned copies of 2 Svat Invoices which we had raised on 2011 are attached herewith for your perusal.
					In order to SIMPLIFY the VAT payments and returns of the VAT & SVAT, above guidelines were published.
					(Copy of the overleaf of the Vat return is also attached for your scrutiny)
					Concern of the DIR is Rounding the Vat portion to the nearest rupee.
					Any how you can adopt option 01 or 02
					
*/
					
					svatObject.setAmount(roundNoDecimals(totalAmount));
					svatObject.setDescription("Suspended VAT @ 11%");
					list2.add(svatObject);
				}
			}
			else
			{
				System.out.println("new change 2015/06/07 :"+taxType);
				if(taxType.equalsIgnoreCase("X")){
				list2.clear();
				FinvdetRPT svatObject = new FinvdetRPT();
				svatObject.setRate(new BigDecimal(0));
				svatObject.setDays(new BigDecimal(0));
				svatObject.setAmount(new BigDecimal(0));
				svatObject.setDescription("[EXEMPTED FROM TAXES]");
				list2.add(svatObject);
				}
				
			}
			//-----------------------------------------------------------------------------------------------------------------------------------------			
			
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			
			l=null;
			em.flush();
			list.addAll(list2);
			System.out.println("concat list"+list.size());
			return list;
		} catch (Exception e) {
			System.out.println("genInvDetByInvNo: " + e.getMessage());
		}
		finally{em.flush();}
		return null;
	}
	

	private String prepareForDisplay(String description)
	{
		System.out.println("description "+description);
		int startIndex=description.indexOf("[");
		int endIndex=description.indexOf("]");
		int length= description.length()-1;
		String description1="";
		String description2="";
		if(startIndex>-1){
			description1=description.substring(0, startIndex);
			description1=description1.replace(System.getProperty("line.separator"), "");
		}
		if(endIndex>-1 && (length!=endIndex)){
		description2=description.substring(endIndex+1, length);
		description2=description2.replace(System.getProperty("line.separator"), "");
		}
		
		if((startIndex>-1) || (endIndex>-1 && (length!=endIndex)))
		{
			description=description1+description2;
		}
		System.out.println("description1+description2 "+description );
		return description ;
	}
	
	@Transactional
	//@Override
	public List<FinvdetRPT> getInvDetByInvNo_backup(String invno) {
		try {
			em.flush();
			String query="SELECT d.resno,d.hiretypeid,";
			query += " (SELECT description FROM fhiretype WHERE hiretypeid=d.hiretypeid) AS hiretype,";
			query += " d.dfrom,d.dto,d.description,d.regno,d.rate,d.km,d.days,d.qty,d.amount,";
			query += " (select vattyp from fdebtor where debcode in (select debcode from finvhed WHERE invno=d.invno) )as vattyp,";//added on 2014.02.05
			query += " (SELECT STUFF((SELECT ', ' + RTRIM(em.name) FROM femployee as em WHERE em.empid in (select rd.empid from fresdriver as rd where rd.resno=d.resno and rd.priority=1) FOR XML PATH('')),1, 1, '') ) as driver";//added on 2014.02.22
			query += " FROM finvdet AS d";
			query += " WHERE invno='"+invno+"'";
			query += " ORDER BY d.seq,d.subseq";
			
			Query q = em.createNativeQuery(query);
			List<Object[]> l = q.getResultList();
			List<FinvdetRPT> list=new ArrayList<FinvdetRPT>();
			FinvdetRPT rpt;
			String description;
			String newline = System.getProperty("line.separator");
			int seq=0;
			for(Object[] r:l){
				rpt=new FinvdetRPT();
				rpt.setResno((String)r[0]);
				String hiretypeid=(String)r[1];
				hiretypeid=hiretypeid.trim();
				if(hiretypeid.length()>2){
					hiretypeid=hiretypeid.substring(0,1);
					r[2]="With Driver";
				}
				rpt.setHiretypeid(hiretypeid);
				System.out.println("(String)r[2] :"+(String)r[2]);
				rpt.setHiretype((String)r[2] + getTaxTypeDecorded((String)r[12])+" Invoice");////added on 2014.02.05
				System.out.println("rpt.setHiretype :"+rpt.getHiretype());
				System.out.println("rpt.setHiretypeID :"+rpt.getHiretypeid());
				rpt.setDfrom((Timestamp)r[3]);
				rpt.setDto((Timestamp)r[4]);
				
				description=(String)r[5];
				
				if(seq==0 && !hiretypeid.equalsIgnoreCase("SD") /*(hiretypeid.equalsIgnoreCase("WD") || hiretypeid.equalsIgnoreCase("WEDDING"))*/)
				{
					description += newline+"Driver:"+(String)r[13];
				}
				
				description=description.replace("\\r",newline);
				
				rpt.setDescription(description);
				
				rpt.setRegno((String)r[6]);
				rpt.setRate((BigDecimal)r[7]);
				rpt.setKm((BigDecimal)r[8]);
//				System.out.println("r[9] :"+r[9]);
//				System.out.println("r[9] :"+new BigDecimal(r[9].toString()));
				rpt.setDays(new BigDecimal(r[9].toString()));
				rpt.setQty(new BigDecimal(r[10].toString()));
				rpt.setAmount((BigDecimal)r[11]);
				
				list.add(rpt);
				seq++;
			}
			l=null;
			em.flush();
			return list;
		} catch (Exception e) {
			System.out.println("genInvDetByInvNo: " + e.getMessage());
		}
		finally{em.flush();}
		return null;
	}

	private String getTaxTypeDecorded(String dbCode)//added on 2014.02.05
	{
		System.out.println("dbCode "+dbCode);
		if(dbCode!=null)
		{
		if(dbCode.equalsIgnoreCase("T"))
		{
			return " TAX";
		}
		else if(dbCode.equalsIgnoreCase("S"))
		{
			return " SVAT";
		}
		
		}
			return "";
	}
	@Transactional
	@Override
	public List<Finvdet> getInvDetById(String invno) {
		try{
			em.flush();
			//return em.createNamedQuery("FinvdetFindById", Finvdet.class).setParameter("ino", invno).getResultList();
			//String query="SELECT * FROM finvdet WHERE invno='" + invno + "' ORDER BY seq,subseq";
			String query="SELECT d.resno,d.dfrom,d.dto,d.description,t.description AS [hiretype],d.regno,d.rate,d.km,d.days,d.qty,d.amount FROM finvdet AS d,fhiretype AS t WHERE invno='"+invno+"' AND d.hiretypeid=t.hiretypeid ORDER BY seq,subseq";
			//List<Finvdet> list=em.createNativeQuery(query, Finvdet.class).getResultList();
			List<Finvdet> list=new ArrayList<Finvdet>();

			Query q = em.createNativeQuery(query);
			List<Object[]> l = q.getResultList();
			Finvdet finvdet;
			String resno;
			Calendar dfrom;
			Calendar dto;
			String description;
			String hiretype;
			String regno;
			BigDecimal rate;
			BigDecimal km;
			BigDecimal days;
			int qty;
			BigDecimal amount;
			for(Object[] r:l){
				resno=(String)r[0];
				
				java.util.Date dtFrom=new java.util.Date(((Timestamp)r[1]).getTime());
				dfrom=Calendar.getInstance();
				dfrom.setTime(dtFrom);
				
				java.util.Date dtTo=new java.util.Date(((Timestamp)r[2]).getTime());
				dto=Calendar.getInstance();
				dto.setTime(dtTo);
				
				description=(String)r[3];
				hiretype=(String)r[4];
				regno=(String)r[5];
				rate=(BigDecimal)r[6];
				km=(BigDecimal)r[7];
				days=(BigDecimal)r[8];
				qty=Integer.parseInt(r[9].toString());
				amount=(BigDecimal)r[10];
				
				finvdet=new Finvdet();
				finvdet.setResno(resno);
				finvdet.setDfrom(dfrom);
				finvdet.setDto(dto);
				finvdet.setDescription(description);
				finvdet.setHiretype(hiretype);
				finvdet.setRegno(regno);
				finvdet.setRate(rate);
				finvdet.setKm(km);
				finvdet.setDays(days);
				finvdet.setQty(qty);
				finvdet.setAmount(amount);

				list.add(finvdet);
			}
			
			return list;
		}catch(Exception e){
			System.out.println("getInvDetById: " + e.getMessage());
			e.printStackTrace();
		}
		finally{em.flush();}
		return null;
	}
	
	@Transactional
	@Override
	public List<Finvhed> getInvoiceDateRange(String df, String dt) {
		try {
			em.flush();
			String newline = System.getProperty("line.separator");
			String query="SELECT h.invno,h.txndate,";
			query += " (SELECT debname FROM fdebtor WHERE debcode=h.debcode) AS debname,";
			query += " (SELECT agrno +','+ ((SELECT STUFF((SELECT distinct ', ' + RTRIM(resno) FROM finvdet as d WHERE d.invno=h.invno FOR XML PATH('')),1, 1, ''))) FROM freshed WHERE agrno=h.agrno) AS agrno,";
			query += " h.adduser,h.debcode";
			query += " , (SELECT SUM(AMOUNT) FROM FINVDET WHERE INVNO=h.invno) as  AMOUNT";
			query += " FROM finvhed AS h WHERE h.adddate>='"+df+"' AND h.adddate<='"+dt+"' ORDER BY h.recordid DESC";
			
			System.out.println("\n\n\n"+query);
			Query q = em.createNativeQuery(query);
			List<Object[]> l = q.getResultList();
			List<Finvhed> list=new ArrayList<Finvhed>();
			for(Object[] r:l){					
				Finvhed finvhed=new Finvhed();
				finvhed.setInvno((String)r[0]);
				
				Timestamp txndate=(Timestamp)r[1];
				finvhed.setTxndatets(txndate);
				
				Calendar calTxndate=Calendar.getInstance();
				java.util.Date dtTxnDate=new java.util.Date(txndate.getTime());
				calTxndate.setTime(dtTxnDate);
				finvhed.setTxndate(calTxndate);
				
				finvhed.setDebname((String)r[5]+" "+(String)r[2]);
				finvhed.setAgrno((String)r[3]);
				finvhed.setAdduser((String)r[4]);
				
				
				NumberFormat cf = NumberFormat.getNumberInstance();
				double taxD = ((BigDecimal)r[6]).doubleValue();
				System.out.println(cf.format(taxD));
				finvhed.setAddmach(cf.format(taxD));
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				String date = dateFormat.format(txndate.getTime());
				finvhed.setTel(date);
				list.add(finvhed);
			}
			l=null;
			return list;
		} catch (Exception e) {
			System.out.println("getInvoiceList: " + e.getMessage());
		}
		finally{em.flush();}
		return null;
	}
	
/*	@Transactional
	//@Override
	public List<Finvhed> getInvoiceDateRange(String df, String dt,String hiretype) {
		try {
			em.flush();
			String query="SELECT h.invno,h.txndate,";
			query += " (SELECT debname FROM fdebtor WHERE debcode=h.debcode) AS debname,";
			query += " (SELECT agrno +','+ ((SELECT STUFF((SELECT distinct ', ' + RTRIM(resno) FROM finvdet as d WHERE d.invno=h.invno FOR XML PATH('')),1, 1, ''))) FROM freshed WHERE agrno=h.agrno) AS agrno,";
			query += " h.adduser,finvdet.resno,freservation.hiretypeid,finvdet.AMOUNT,FRESVEHICLE.REGNO ";
			query += " FROM finvhed AS h   ";
			query += " JOIN FINVDET ON finvdet.invno=h.invno JOIN FRESERVATION on freservation.resno=finvdet.resno JOIN FRESVEHICLE ON FRESVEHICLE.RESNO=freservation.resno ";
			query += " WHERE h.adddate>='"+df+"' AND h.adddate<='"+dt+"' AND  freservation.hiretypeid='"+hiretype+"'  AND FRESVEHICLE.PRIORITY=1 ";
			query += " ORDER BY h.recordid DESC";
			
			System.out.println("\n\n\n"+query);
			Query q = em.createNativeQuery(query);
			List<Object[]> l = q.getResultList();
			List<Finvhed> list=new ArrayList<Finvhed>();
			for(Object[] r:l){					
				Finvhed finvhed=new Finvhed();
				finvhed.setInvno((String)r[0]);
				
				Timestamp txndate=(Timestamp)r[1];
				finvhed.setTxndatets(txndate);
				
				Calendar calTxndate=Calendar.getInstance();
				java.util.Date dtTxnDate=new java.util.Date(txndate.getTime());
				calTxndate.setTime(dtTxnDate);
				finvhed.setTxndate(calTxndate);
				
				finvhed.setDebname((String)r[2]);
				finvhed.setAgrno((String)r[3]);
				finvhed.setAdduser((String)r[4]);
				
				list.add(finvhed);
			}
			l=null;
			return list;
		} catch (Exception e) {
			System.out.println("getInvoiceList: " + e.getMessage());
		}
		finally{em.flush();}
		return null;
	}*/
}
