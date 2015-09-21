package com.dspl.malkey.dao;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fresaccrate;
import com.dspl.malkey.domain.FresaccratePK;
import com.dspl.malkey.domain.Fresaccs;
import com.dspl.malkey.domain.FresaccsPK;
import com.dspl.malkey.domain.Fresaddcharge;
import com.dspl.malkey.domain.FresaddchargePK;
import com.dspl.malkey.domain.Fresclientdriver;
import com.dspl.malkey.domain.Fresdriver;
import com.dspl.malkey.domain.FresdriverPK;
import com.dspl.malkey.domain.Fresdriverrate;
import com.dspl.malkey.domain.FresdriverratePK;
import com.dspl.malkey.domain.Freservation;
import com.dspl.malkey.domain.Freservationdiaryrpt;
import com.dspl.malkey.domain.Freshed;
import com.dspl.malkey.domain.Fresothersrvrate;
import com.dspl.malkey.domain.FresothersrvratePK;
import com.dspl.malkey.domain.Fresothsrv;
import com.dspl.malkey.domain.FresothsrvPK;
import com.dspl.malkey.domain.Fresvehicle;
import com.dspl.malkey.domain.Fresvehicledamage;
import com.dspl.malkey.domain.Fresvehiclerate;
import com.dspl.malkey.domain.Fresvehinv;
import com.dspl.malkey.domain.Fvehicle;
import com.dspl.malkey.domain.Fvehicledamage;
import com.dspl.malkey.domain.Fvehicleinventry;
import com.dspl.malkey.domain.FvehicleinventryPK;
import com.dspl.malkey.report.FrentagreementRPT;
import com.dspl.malkey.report.FrentagrvehinvRPT;
import com.dspl.malkey.report.FrentvehinvRPT;
import com.dspl.malkey.report.Reservation;
import com.dspl.malkey.service.FileUploadSRV;
import com.dspl.malkey.service.UserInfoSRV;
import com.dspl.malkey.view.FreservationView;
import com.dspl.malkey.view.VehicleAvailabilityView;

import flex.messaging.FlexContext;
import flex.messaging.io.ArrayCollection;

public class FreservationDAOImpl implements FreservationDAO {

	private final Log log = LogFactory.getLog(this.getClass());
	
	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fcompanysettingDAO")
	FcompanysettingDAO fcompanysettingDAO;
	
	@Resource(name="fileUploadSRV")
	FileUploadSRV fileUploadSRV;
	
	@Resource(name="vehiclesearchDAO")
	VehiclesearchDAO vehiclesearchDAO;

	@Resource(name="userInfoSRV")
	UserInfoSRV userInfoSRV;
	
	@Resource(name="fresconfirmDAO")
	FresconfirmDAO fresconfirmDAO;
	

	@Transactional(readOnly=false)
	@Override
	public List<Freservation> List(int startIndex, int numItems) {
		try {
			em.flush();
			return em.createNamedQuery("FreservationListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
		} catch (Exception e) {
			return null;
		}
		finally	{em.flush();}
		
	}

	@Transactional(readOnly=false)
	@Override
	public List<Freservation> listByHedAgrno(String agrno) {
		try {
			long startTime = System.currentTimeMillis();
			em.flush();
			List<Freservation> lst = em.createNamedQuery(
					"FreservationSelectByAgrno").setParameter("agrno", agrno)
					.getResultList();
			long endTime = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("listByHedAgrno" + totalTime);
			log.info("listByHedAgrno :" + totalTime);
			return lst;
		} catch (Exception e) {
			return null;
		}finally	{em.flush();}

	}
	//NOT WORKING
	@Transactional(readOnly=false)
	@Override
	public List<Freservation> listPartByHedAgrno(String agrno) {
		try {
					em.flush();
		System.out.println("FreservationDAOImpl.listPartByHedAgrno.agrno:"+agrno);
	    List<Freservation> lst= em.createNamedQuery("FreservationPartSelectByAgrno",Freservation.class).setParameter("agrno",agrno).getResultList();
		System.out.println("Length:"+lst.size());
		em.flush();
		return lst;
		} catch (Exception e) {
			return null;
		}
		finally	{em.flush();}

	}
	
	//TESING
	@Transactional(readOnly=false)
	@Override
	public List<Freservation> listDetailsByHedAgrno(String agrno) {
		em.flush();
		List<Freservation> lst= em.createNamedQuery("FreservationSelectByAgrno").setParameter("agrno",agrno).getResultList();
		for(Freservation freservation :lst)
		{
			String resno=freservation.getResno();
			freservation.setDetails(listAllByResno(freservation.getResno()));
		}
		em.flush();
		return lst;
	}
	
	@Transactional(readOnly=false)
	@Override
	public List<Freservation> ListAll() {
		try {
			em.flush();
			return em.createNamedQuery("FreservationListAll").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return (new ArrayList<Freservation>());
		}
		finally
		{
			em.flush();
		}
		
	}
	
	@Transactional(readOnly=false)
	@Override
	public Reservation listAllByResno(String resno) {
		
		try {
			

		long startTime = System.currentTimeMillis();
		em.flush();
		Reservation reservation=new Reservation();
		reservation.setFresvehicle(em.createNamedQuery("FresvehicleListByResNo").setParameter("resno", resno).getResultList());//resvehicle
		System.out.println("res-vehicle size:"+reservation.getFresvehicle().size());
	//	reservation.setFreservation( em.find(Freservation.class, resno));//reservation
		reservation.setFresaccs( em.createNamedQuery("FresaccsListByresNo").setParameter("resno", resno).getResultList());
		reservation.setFresothsrv( em.createNamedQuery("FresothsrvListAllByResNo").setParameter("resno", resno).getResultList());
		reservation.setFresdriver( em.createNamedQuery("FresdriverListByResno").setParameter("resno", resno).getResultList());
		reservation.setFresaddcharge( em.createNamedQuery("FresaddchargeListByresno").setParameter("resno", resno).getResultList());
		reservation.setFresclientdriver( em.createNamedQuery("FresclientdriverListByResno").setParameter("resno", resno).getResultList());
		try{
		Fresdriverrate fresdriverrate= (Fresdriverrate) em.createNamedQuery("FresdriverrateByResno").setParameter("resno", resno).getSingleResult();
		reservation.setFresdriverrate(fresdriverrate);
		}
		catch(Exception e)
		{}
		em.flush();
		if(reservation.getFresvehicle().size()>0){
		String regno=reservation.getFresvehicle().get(0).getId().getRegno();
		List<Fresvehicledamage> lst=em.createNamedQuery("FresvehicledamageListByRegNo").setParameter("regno",regno ).setParameter("resno", resno).getResultList();
		reservation.setFresvehicledamage(lst);
		em.flush();
		reservation.setFresvehinv(em.createNamedQuery("FresvehinvListByResno").setParameter("resno", resno).getResultList());
		}
		
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("listAllByResno"+totalTime);
		log.info("listAllByResno :"+totalTime);
		
		
		 return reservation;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.flush();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<FreservationView> advanceSearch(String hireStatus,String hireType,String debcode,String regno,String dateFrom ,String dateTo) {
		em.flush();
		String sqlVehicle = "";
		String sqlVehicleWhere = " r.resno=v.resno";
		String sqlDebcodePart = "";
		String sqlHireTypePart = "";
		String sqlHireStatusPart = "";
		String sqlFromDatePart = "";
		String sqlWhere = " WHERE ";

		if(regno==null){regno="";}
		if(hireStatus==null){hireStatus="";}
		if(hireType==null){hireType="";}
		if(debcode==null){debcode="";}
		if(dateFrom==null){dateFrom="";}
		if(dateTo==null){dateTo="";}
		
		if (regno.length() > 0) {
			sqlVehicleWhere += " AND v.regno='" + regno + "'";//AND SHOULD SET PRIORITY==1
		}
		if (debcode.length() > 0 || hireType.length() > 0
				|| hireStatus.length() > 0 || dateFrom.length() > 0
				|| dateTo.length() > 0) {
			sqlVehicleWhere = " AND " + sqlVehicleWhere;
		}
		if (debcode.length() > 0) {
			sqlDebcodePart = " r.debcode='" + debcode + "'";
		}
		if (hireType.length() > 0) {
			if (debcode.length() > 0) {
				sqlHireTypePart = " AND ";
			}
			sqlHireTypePart += " r.hiretypeid='" + hireType + "'";
		}
		if (hireStatus.length() > 0) {
			if (debcode.length() > 0 || hireType.length() > 0) {
				sqlHireStatusPart = " AND ";
			}
			sqlHireStatusPart += " r.cohirestsid='" + hireStatus + "'";
		}
		if (dateFrom.length() > 0 && dateTo.length() > 0) {
			if (debcode.length() > 0 || hireType.length() > 0
					|| hireStatus.length() > 0) {
				sqlFromDatePart = " AND ";
			}
			sqlFromDatePart += " ( '" + dateFrom
					+ "' BETWEEN r.dout AND r.din OR '" + dateTo
					+ "' BETWEEN r.dout AND r.din AND r.dout BETWEEN '"
					+ dateFrom + "' AND '" + dateTo + "' OR r.din BETWEEN '"
					+ dateFrom + "' AND '" + dateTo + "' ) ";
		}
		String debcodeQuery="(select ( debcode+' '+ debname) as debcode from Fdebtor where debcode= r.debcode)";
		String sqlQuery = "SELECT r.agrno,r.resno,r.cohirestsid,"+debcodeQuery+",r.hiretypeid,r.dout,r.din,r.timein,r.timeout,r.taxcomcode,r.gdout,r.gotime,v.regno,r.nettotal FROM Freservation as r,Fresvehicle as v"
				+ sqlVehicle
				+ sqlWhere
				+ sqlDebcodePart
				+ sqlHireTypePart
				+ sqlHireStatusPart + sqlFromDatePart + sqlVehicleWhere;

		List<Object[]> rowObjectlist = new ArrayList();
		try {
			Query query = em.createNativeQuery(sqlQuery);
			rowObjectlist = query.getResultList();
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		List<FreservationView> lstFreservationView = new ArrayList<FreservationView>();
		for (Object[] row : rowObjectlist) {
			try {
				FreservationView view = new FreservationView();
				view.setAgrno((String) row[0]);
				view.setResno((String) row[1]);
				view.setCohirestsid((String) row[2]);
				view.setDebcode((String) row[3]);
				view.setHiretypeid((String) row[4]);
				Timestamp tdout = (Timestamp) row[5];
				Timestamp tdin = (Timestamp) row[6];
				Calendar dout = Calendar.getInstance();
				dout.setTimeInMillis(tdout.getTime());
				Calendar din = Calendar.getInstance();
				din.setTimeInMillis(tdin.getTime());
				view.setDout(dout);
				view.setDin(din);
				view.setTimein((String) row[7]);
				view.setTimeout((String) row[8]);
				view.setTaxcomcode((String) row[9]);
				Timestamp tgdout = (Timestamp) row[10];
				Calendar gdout = Calendar.getInstance();
				gdout.setTimeInMillis(tgdout.getTime());
				view.setGdout(gdout);
				view.setGotime((String) row[11]);
				view.setRegno((String) row[12]);
				view.setTotal((BigDecimal) row[13]);
				lstFreservationView.add(view);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lstFreservationView;
	}

	@Transactional(readOnly=true)
	@Override
	public List<FreservationView> loadClientHistory(String debcode) {
		try {
			long startTime = System.currentTimeMillis();
			em.flush();
			String query="select agrno,resno,cohirestsid,dout,din,hiretypeid,(select fv.regno from fresvehicle as fv where fv.resno=r.resno) as regno " +
					"from freservation where debcode='"+debcode+"' order by dout desc";
			List<Object[]> lstRow= em.createNativeQuery(query).getResultList();
			List<FreservationView> lstFreservationView = new ArrayList<FreservationView>();
			for(Object[] row : lstRow)
			{
				try {
					FreservationView view = new FreservationView();
					view.setAgrno((String) row[0]);
					view.setResno((String) row[1]);
					view.setCohirestsid((String) row[2]);
					Timestamp tdout = (Timestamp) row[3];
					Timestamp tdin = (Timestamp) row[4];
					Calendar dout = Calendar.getInstance();
					dout.setTimeInMillis(tdout.getTime());
					Calendar din = Calendar.getInstance();
					din.setTimeInMillis(tdin.getTime());
					view.setHiretypeid((String) row[5]);
					view.setDout(dout);
					view.setDin(din);
					view.setRegno((String) row[6]);
					lstFreservationView.add(view);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			lstRow=null;
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("FreservationDAO.loadClientHistory :"+totalTime);
			return lstFreservationView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.flush();
		}
		
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<VehicleAvailabilityView> loadVehicleAvailability(String dfrom,String dto) {
		try {
			long startTime = System.currentTimeMillis();
			List<FreservationView> lstFreservationView = new ArrayList<FreservationView>();
			List<VehicleAvailabilityView> availabilityCalanderList = new ArrayList<VehicleAvailabilityView>();
			em.flush();
			//'2014-01-01'
			//'2014-01-31'
			//dfrom="2014-01-01";
			//dto="2014-01-31";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dateFrom=format.parse(dfrom);
			Date dateTo=format.parse(dto);
			Calendar caldateFrom=Calendar.getInstance();
			caldateFrom.setTime(dateFrom);
			Calendar caldateTo=Calendar.getInstance();
			caldateTo.setTime(dateTo);
			
			long duration = ((dateTo.getTime() - dateFrom.getTime())/ (24 * 60 * 60 * 1000))+1;
			
			
			//String regno="0000000003";
			String queryVehicle="select regno,vehimakeid,vehimodelid,mainseats,year,fueltypeid,vehitransid,colourid from fvehicle ";
			List<Object[]> lstVehicle= em.createNativeQuery(queryVehicle).getResultList();
			for(Object[] vehicleArray : lstVehicle)
			{
				List<String> availabilityCalander = new ArrayList<String>();
				for (int i = 0; i < duration; i++) {
					availabilityCalander.add("A");//AVAILABLE
				}
				VehicleAvailabilityView vehicleAvailabilityView=new VehicleAvailabilityView();
				try{
				vehicleAvailabilityView.setColourid((String) vehicleArray[7]);
				vehicleAvailabilityView.setFueltypeid((String) vehicleArray[5]);
				if(vehicleArray[3]!=null)
				vehicleAvailabilityView.setMainseats(Integer.toString((Integer) vehicleArray[3]));
				vehicleAvailabilityView.setRegno((String) vehicleArray[0]);
				vehicleAvailabilityView.setVehimakeid((String) vehicleArray[1]);
				vehicleAvailabilityView.setVehimodelid((String) vehicleArray[2]);
				vehicleAvailabilityView.setVehitransid((String) vehicleArray[6]);
				if(vehicleArray[4]!=null)
				vehicleAvailabilityView.setYear(Integer.toString((Integer) vehicleArray[4]));
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				//create a calendar that include 31 days,default all days available,when loop each reservation set unavailable each days
				
/*				System.out.println("=======================================================================================");
				System.out.println("regno "+ vehicleAvailabilityView.getRegno());
				System.out.println("vehimakeid "+ vehicleAvailabilityView.getVehimakeid());
				System.out.println("vehimodelid "+ vehicleAvailabilityView.getVehimodelid());
				System.out.println("mainseats "+ vehicleAvailabilityView.getMainseats());
				System.out.println("year "+ vehicleAvailabilityView.getYear());
				System.out.println("fueltypeid "+ vehicleAvailabilityView.getFueltypeid());
				System.out.println("vehitransid "+ vehicleAvailabilityView.getVehitransid());
				System.out.println("colorid "+ vehicleAvailabilityView.getColourid());
				System.out.println("=======================================================================================");*/
				
				
				
				//===================================================================================================================================
				String query="select agrno,freservation.resno,regno,cohirestsid,freservation.debcode,fdebtor.debname,hiretypeid,dout,din from freservation join fresvehicle on (fresvehicle.resno=freservation.resno and fresvehicle.priority=1 and fresvehicle.regno='"+vehicleAvailabilityView.getRegno()+"')"+ 
				"join fdebtor on (freservation.debcode = fdebtor.debcode)"+
				"where "+
				"(freservation.dout between '"+dfrom+"' and  '"+dto+"') or"+
				"(freservation.din between '"+dfrom+"' and  '"+dto+"') or"+
				"('"+dto+"' between freservation.dout and freservation.din) or"+
				"('"+dfrom+"' between freservation.dout and freservation.din) "+
				"order by dout,din";
				
				System.out.println("=========================================================");
				System.out.println("#query:# "+query);
				System.out.println("caldateFrom :"+format.format(caldateFrom.getTime()));
				System.out.println("caldateTo :"+format.format(caldateTo.getTime()));
				System.out.println("=========================================================");
				List<Object[]> lstRow= em.createNativeQuery(query).getResultList();

				for(Object[] row : lstRow)
				{
					//agrno,freservation.resno,regno,cohirestsid,freservation.debcode,fdebtor.debname,hiretypeid,dout,din
					try {
						FreservationView view = new FreservationView();
						view.setAgrno((String) row[0]);
						view.setResno((String) row[1]);
						view.setRegno((String) row[2]);
						view.setCohirestsid((String) row[3]);
						view.setDebcode((String) row[4] + (String) row[5]);
						view.setHiretypeid((String) row[6]);
						Timestamp tdout = (Timestamp) row[7];
						Timestamp tdin = (Timestamp) row[8];
						Calendar dout = Calendar.getInstance();
						dout.setTimeInMillis(tdout.getTime());
						Calendar din = Calendar.getInstance();
						din.setTimeInMillis(tdin.getTime());
						view.setDout(dout);
						view.setDin(din);
						
						lstFreservationView.add(view);
						
						
						//in milliseconds
						//long diffDays = getDuration(din.getTime(), dout.getTime());
						
						
						//get dout
						//check is it within range
						//if yes availabilityCalander.add(false); 
						//increment date
						//loop
						
						System.out.println("#zize="+availabilityCalander.size());
						System.out.println("####################################################");
						System.out.println("#########AGR FROM :"+format.format(dout.getTime())+"#####");
						System.out.println("########AGR TO :"+format.format(din.getTime())+"#######");
						System.out.println("#####################################################");
						
						Calendar doutTemp = Calendar.getInstance();
						doutTemp.setTime(dout.getTime());
						
						long noOfdaysAgreement=getDuration(din.getTime(), dout.getTime());//din-dout
						for (int i = 0; i < noOfdaysAgreement; i++) {
							
							
/*							System.out.println("#noOfdaysAgreement="+noOfdaysAgreement);
							System.out.println("####################################################"+i+" of "+noOfdaysAgreement);
							System.out.println("#########LOOP AGR FROM :"+format.format(dout.getTime())+"#####");
							System.out.println("########LOOP AGR TO :"+format.format(din.getTime())+"#######");
							System.out.println("#########LOOP Report FROM :"+format.format(caldateFrom.getTime())+"#####");
							System.out.println("########LOOP Report TO :"+format.format(caldateTo.getTime())+"#######");
							System.out.println("#####################################################");*/
							
							
							if((doutTemp.after(caldateFrom) && doutTemp.before(caldateTo)) 
									|| (doutTemp.getTime().getTime()==caldateFrom.getTime().getTime() || doutTemp.getTime().getTime()==caldateTo.getTime().getTime() )){
/*								System.out.println("#zize="+availabilityCalander.size());
								System.out.println("#i="+i);
								System.out.println("doutTemp :"+format.format(doutTemp.getTime()));
								System.out.println("caldateFrom :"+format.format(caldateFrom.getTime()));
								System.out.println("caldateTo :"+format.format(caldateTo.getTime()));*/
								int correctIndex=getDateIndex(caldateFrom, caldateTo, doutTemp);
//								System.out.println("#correctIndex="+correctIndex);

								if(correctIndex==-1){
//									System.out.println("fucking loop continues to next.......");
									continue;
								}
								
								availabilityCalander.remove(correctIndex);
								//point to be handled
								//availabilityCalander.add("U");//UNAVAILABLE
								if(view.getCohirestsid().equalsIgnoreCase("BOOKED"))
								{
									availabilityCalander.add(correctIndex,"B");//UNAVAILABLE
								}
								else if(view.getCohirestsid().equalsIgnoreCase("CANCELLED"))
								{
									availabilityCalander.add(correctIndex,"A");//UNAVAILABLE
								}
								else if(view.getCohirestsid().equalsIgnoreCase("CHECKIN"))
								{
									availabilityCalander.add(correctIndex,"CI");//UNAVAILABLE
								}
								else if(view.getCohirestsid().equalsIgnoreCase("CHECKOUT"))
								{
									availabilityCalander.add(correctIndex,"CO");//UNAVAILABLE
								}
								else if(view.getCohirestsid().equalsIgnoreCase("COMPLETED"))
								{
									availabilityCalander.add(correctIndex,"CI");//UNAVAILABLE
								}
								else if(view.getCohirestsid().equalsIgnoreCase("CONFIRMED"))
								{
									availabilityCalander.add(correctIndex,"CNF");//UNAVAILABLE
								}
								else if(view.getCohirestsid().equalsIgnoreCase("INVOICE"))
								{
									availabilityCalander.add(correctIndex,"CI");//UNAVAILABLE
								}
								else if(view.getCohirestsid().equalsIgnoreCase("VC"))
								{
									availabilityCalander.add(correctIndex,"CI");//UNAVAILABLE
								}
								else if(view.getCohirestsid().equalsIgnoreCase("PREPARED"))
								{
									availabilityCalander.add(correctIndex,"B");//UNAVAILABLE
								}
								else if(view.getCohirestsid().equalsIgnoreCase("NOSHOW"))
								{
									availabilityCalander.add(correctIndex,"CI");//UNAVAILABLE
								}
							}
							doutTemp.add(Calendar.DAY_OF_MONTH, i);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				lstRow=null;
				//==================================================================================================================
				vehicleAvailabilityView.setAvailabilityCalander(availabilityCalander);
				availabilityCalanderList.add(vehicleAvailabilityView);
			}
			
/*			for(int i=0;i<lstFreservationView.size();i++)
			{
				FreservationView temp=lstFreservationView.get(i);
				System.out.println("=====================================================================================================");
				System.out.println("Agrno "+temp.getAgrno());
				System.out.println("Resno "+temp.getResno());
				System.out.println("Regno "+temp.getRegno());
				System.out.println("hirestatus "+temp.getCohirestsid());
				System.out.println("Debcode "+temp.getDebcode());
				System.out.println("Dout "+temp.getDout());
				System.out.println("Din "+temp.getDin());
				System.out.println("======================================================================================================");
			}*/
			
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("FreservationDAO.loadVehicleAvailability :"+totalTime);
			return availabilityCalanderList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.flush();
		}
		
	}
	
	private long getDuration(Date from,Date to)
	{
		
		long diff = to.getTime() - from.getTime();
		long diffDays = (diff / (24 * 60 * 60 * 1000));
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("######################################################");
		System.out.println("#########getDurationFROM:"+format.format(from)+"######");
		System.out.println("########getDurationTO:"+format.format(to)+"###########");
		System.out.println("######################################################"+diffDays);
		
		return diffDays+1;
	}
	
	/***
	 * 
	 * function return 
	 */
	private Integer getDateIndex(Calendar reportFrom,Calendar reportTo,Calendar date)
	{
		Calendar temp=Calendar.getInstance();
		temp.setTime(reportFrom.getTime());
		
		int index=-1;
		long numberOfdays=getDuration(reportFrom.getTime(),reportTo.getTime());
		for (int i = 0; i < numberOfdays; i++) {
			if(date.getTime().getTime()==temp.getTime().getTime())
			{
				System.out.println("date"+date.getTime().toString());
				System.out.println("temp"+temp.getTime().toString());
				return i;
			}
			temp.add(Calendar.DAY_OF_MONTH, 1);
		}
		return index;
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<FreservationView> loadCHD(String agrno) {
		try {
			//RH/1104/00018
			long startTime = System.currentTimeMillis();
			em.flush();
			
			SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd/MM/yyyy");
			//String dateAsString = simpleDateFormat.format(new Date());
			
			String query="select freservation.agrno,freservation.resno,freservation.cohirestsid,freservation.dout,freservation.din," +
					"freservation.hiretypeid,fresvehicle.regno,freservation.nettotal,(cixsmileage) as mileage,freservation.noofday, " +
					"FDEBTOR.DEBCODE,FDEBTOR.DEBNAME,(FDEBTOR.DEBADD+FDEBTOR.tel),FDEBTOR.NICNO ,othernaration "+
					" ,BOOKED,CONFIRMED,CANCELLED,CHECKOUT,CHECKIN,COMPLETED,INVOICE "+
					"from freservation join fresvehicle  on fresvehicle.resno=freservation.resno " +
					"join FDEBTOR on FDEBTOR.debcode=freservation.debcode " +
					"where   freservation.agrno='"+agrno+"'";
			//freservation.ratetype='LONGTERM' and
			Query q=em.createNativeQuery(query);
			//q.setParameter(1, agrno);
			List<Object[]> lstRow= q.getResultList();
			List<FreservationView> lstFreservationView = new ArrayList<FreservationView>();
			for(Object[] row : lstRow)
			{
				try {
					FreservationView view = new FreservationView();
					view.setAgrno((String) row[0]);
					view.setResno((String) row[1]);
					view.setCohirestsid((String) row[2]);
					Timestamp tdout = (Timestamp) row[3];
					Timestamp tdin = (Timestamp) row[4];
					Calendar dout = Calendar.getInstance();
					dout.setTimeInMillis(tdout.getTime());
					Calendar din = Calendar.getInstance();
					din.setTimeInMillis(tdin.getTime());
					view.setHiretypeid((String) row[5]);
					view.setDout(dout);
					view.setDin(din);
					view.setDateFormaterOut(simpleDateFormat.format(dout.getTime()));
					view.setDateFormaterIn(simpleDateFormat.format(din.getTime()));
					view.setRegno((String) row[6]);
					view.setNettotal(((BigDecimal)row[7]));
					view.setComileage(((Integer)row[8]));
					view.setNoofday(((Integer)row[9]));
					view.setDebcode(((String)row[10]));
					view.setRemarks(((String)row[11]));//deb name
					view.setAddmach(((String)row[12]));//DEBADD
					view.setCancelled(((String)row[13]));//NICNO
					view.setOthernaration(((String)row[14]));//othernaration

					String modifiedBy="";
					for(int i=15;i<21;i++){
						String temp=(String)row[i];
						if(temp!=null)
						modifiedBy=modifiedBy+"-"+((String)row[i]);
					}
					
					view.setBooked(modifiedBy);
					//view.setOthernaration(((String)row[14]) +modifiedBy);//othernaration
					
					lstFreservationView.add(view);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			lstRow=null;
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("FreservationDAO.loadCHD :"+totalTime);
			return lstFreservationView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.flush();
		}
		
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<FreservationView> loadCHD2() {
		try {
			//RH/1104/00018
			long startTime = System.currentTimeMillis();
			em.flush();
			
			SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd/MM/yyyy");
			
			String query="select  " +
			"freservation.agrno,freservation.resno,freservation.cohirestsid,freservation.dout,freservation.din," +
			"freservation.hiretypeid,fresvehicle.regno,freservation.nettotal,(cimileage-comileage) as mileage,freservation.noofday, " +
			"fdebtor.debcode,fdebtor.debname,fdebtor.debadd,fdebtor.nicno, "+
			" (SELECT STUFF((SELECT ', ' + RTRIM(name) FROM femployee WHERE femployee.empid IN (SELECT empid FROM fresdriver WHERE fresdriver.resno=freservation.resno AND priority=1) FOR XML PATH('')),1, 1, '')) AS driver"+
					" from freservation " +
					"join fresvehicle  on fresvehicle.resno=freservation.resno "+
					" join fdebtor on fdebtor.debcode=freservation.debcode " +
					" where  freservation.hiretypeid='WD' and (fdebtor.longterm='LONGTERM' OR freservation.ratetype='LONGTERM') AND freservation.cohirestsid IN ('CHECKOUT') order by cohirestsid";
		
			System.out.println("query :"+query);
			Query q=em.createNativeQuery(query);
			List<Object[]> lstRow= q.getResultList();
			List<FreservationView> lstFreservationView = new ArrayList<FreservationView>();
			for(Object[] row : lstRow)
			{
				try {
					FreservationView view = new FreservationView();
					view.setAgrno((String) row[0]);
					view.setResno((String) row[1]);
					view.setCohirestsid((String) row[2]);
					Timestamp tdout = (Timestamp) row[3];
					Timestamp tdin = (Timestamp) row[4];
					Calendar dout = Calendar.getInstance();
					dout.setTimeInMillis(tdout.getTime());
					Calendar din = Calendar.getInstance();
					din.setTimeInMillis(tdin.getTime());
					view.setHiretypeid((String) row[5]);
					view.setDout(dout);
					view.setDin(din);
					view.setDateFormaterOut(simpleDateFormat.format(dout.getTime()));
					view.setDateFormaterIn(simpleDateFormat.format(din.getTime()));
					view.setRegno((String) row[6]);
					view.setNettotal(((BigDecimal)row[7]));
					view.setComileage(((Integer)row[8]));
					view.setNoofday(((Integer)row[9]));
					view.setDebcode(((String)row[10]));
					view.setRemarks(((String)row[11]));//deb name
					view.setAddmach(((String)row[12]));//DEBADD
					view.setCancelled(((String)row[13]));//NICNO
					view.setBooked(((String)row[14]));//NICNO
					
					lstFreservationView.add(view);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			lstRow=null;
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("FreservationDAO.loadCHD2 :"+totalTime);
			return lstFreservationView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.flush();
		}
		
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<FreservationView> loadCHDAlert() {
		try {
			//RH/1104/00018
			long startTime = System.currentTimeMillis();
			em.flush();
			
			SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd/MM/yyyy");
			
			String query="select  " +
			"freservation.agrno,freservation.resno,freservation.cohirestsid,freservation.dout,freservation.din," +
			"freservation.hiretypeid,fresvehicle.regno,freservation.nettotal,(cimileage-comileage) as mileage,freservation.noofday, " +
			"fdebtor.debcode,fdebtor.debname,fdebtor.debadd,fdebtor.nicno "+
			" from freservation " +
			"join fresvehicle  on fresvehicle.resno=freservation.resno "+
			" join fdebtor on fdebtor.debcode=freservation.debcode " +
			" where  (DATEDIFF(day,freservation.dout,getdate()) % 30 )=0 and (fdebtor.longterm='LONGTERM' OR freservation.ratetype='LONGTERM')  AND freservation.cohirestsid IN ('CHECKOUT','CHECKIN','COMPLEATED')";
			
			Query q=em.createNativeQuery(query);
			List<Object[]> lstRow= q.getResultList();
			List<FreservationView> lstFreservationView = new ArrayList<FreservationView>();
			for(Object[] row : lstRow)
			{
				try {
					FreservationView view = new FreservationView();
					view.setAgrno((String) row[0]);
					view.setResno((String) row[1]);
					view.setCohirestsid((String) row[2]);
					Timestamp tdout = (Timestamp) row[3];
					Timestamp tdin = (Timestamp) row[4];
					Calendar dout = Calendar.getInstance();
					dout.setTimeInMillis(tdout.getTime());
					Calendar din = Calendar.getInstance();
					din.setTimeInMillis(tdin.getTime());
					view.setHiretypeid((String) row[5]);
					view.setDout(dout);
					view.setDin(din);
					view.setDateFormaterOut(simpleDateFormat.format(dout.getTime()));
					view.setDateFormaterIn(simpleDateFormat.format(din.getTime()));
					view.setRegno((String) row[6]);
					view.setNettotal(((BigDecimal)row[7]));
					view.setComileage(((Integer)row[8]));
					view.setNoofday(((Integer)row[9]));
					view.setDebcode(((String)row[10]));
					view.setRemarks(((String)row[11]));//deb name
					view.setAddmach(((String)row[12]));//DEBADD
					view.setCancelled(((String)row[13]));//NICNO
					
					lstFreservationView.add(view);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			lstRow=null;
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("FreservationDAO.loadCHDAlert :"+totalTime);
			return lstFreservationView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.flush();
		}
		
	}

	
	//customer Agreement History
	@Transactional(readOnly=true)
	@Override
	public List<FreservationView> loadCAH(String debcode) {
		try {
			//RH/1104/00018
			long startTime = System.currentTimeMillis();
			em.flush();
			
			SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd/MM/yyyy");
			
			String query="select  " +
			"freservation.agrno,freservation.resno,freservation.cohirestsid,freservation.dout,freservation.din," +
			"freservation.hiretypeid,fresvehicle.regno,freservation.nettotal,(cimileage-comileage) as mileage,freservation.noofday, " +
			"fdebtor.debcode,fdebtor.debname,fdebtor.debadd,fdebtor.nicno,finvhed.invno "+
			" from freservation " +
			"join fresvehicle  on fresvehicle.resno=freservation.resno "+
			" join fdebtor on fdebtor.debcode=freservation.debcode " +
			" join finvhed on finvhed.agrno=freservation.agrno " +
			" where  freservation.debcode='"+debcode+"'   order by freservation.agrno,freservation.resno";
			
			Query q=em.createNativeQuery(query);
			List<Object[]> lstRow= q.getResultList();
			List<FreservationView> lstFreservationView = new ArrayList<FreservationView>();
			for(Object[] row : lstRow)
			{
				try {
					FreservationView view = new FreservationView();
					view.setAgrno((String) row[0]);
					view.setResno((String) row[1]);
					view.setCohirestsid((String) row[2]);
					Timestamp tdout = (Timestamp) row[3];
					Timestamp tdin = (Timestamp) row[4];
					Calendar dout = Calendar.getInstance();
					dout.setTimeInMillis(tdout.getTime());
					Calendar din = Calendar.getInstance();
					din.setTimeInMillis(tdin.getTime());
					view.setHiretypeid((String) row[5]);
					view.setDout(dout);
					view.setDin(din);
					view.setDateFormaterOut(simpleDateFormat.format(dout.getTime()));
					view.setDateFormaterIn(simpleDateFormat.format(din.getTime()));
					view.setRegno((String) row[6]);
					view.setNettotal(((BigDecimal)row[7]));
					view.setComileage(((Integer)row[8]));
					view.setNoofday(((Integer)row[9]));
					view.setDebcode(((String)row[10]));
					view.setRemarks(((String)row[11]));//deb name
					view.setAddmach(((String)row[12]));//DEBADD
					view.setCancelled(((String)row[13]));//NICNO
					view.setBooked(((String)row[14]));//finvhed.invno
					
					lstFreservationView.add(view);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			lstRow=null;
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("FreservationDAO.loadCHD :"+totalTime);
			return lstFreservationView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.flush();
		}
		
	}
	
	
	
	//customer Agreement History
	@Transactional(readOnly=true)
	@Override
	public List<FreservationView> loadCAH(String debcode,String fromDate,String toDate) {
		try {
			//RH/1104/00018
			long startTime = System.currentTimeMillis();
			em.flush();
			
			SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd/MM/yyyy");
			
			String query="select  " +
			"freservation.agrno,freservation.resno,freservation.cohirestsid,freservation.dout,freservation.din," +
			"freservation.hiretypeid,fresvehicle.regno,freservation.nettotal,(cimileage-comileage) as mileage,freservation.noofday, " +
			"fdebtor.debcode,fdebtor.debname,fdebtor.debadd,fdebtor.nicno,finvhed.invno "+
			" from freservation " +
			"join fresvehicle  on fresvehicle.resno=freservation.resno "+
			" join fdebtor on fdebtor.debcode=freservation.debcode " +
			" join finvhed on (finvhed.agrno=freservation.agrno and finvhed.txndate >='"+fromDate+"' and '"+toDate+"'>= finvhed.txndate )" +
			" where  freservation.debcode='"+debcode+"'   order by freservation.agrno,freservation.resno";
			
			Query q=em.createNativeQuery(query);
			List<Object[]> lstRow= q.getResultList();
			System.out.println("lstRow :"+lstRow.size());
			List<FreservationView> lstFreservationView = new ArrayList<FreservationView>();
			for(Object[] row : lstRow)
			{
				try {
					FreservationView view = new FreservationView();
					view.setAgrno((String) row[0]);
					view.setResno((String) row[1]);
					view.setCohirestsid((String) row[2]);
					Timestamp tdout = (Timestamp) row[3];
					Timestamp tdin = (Timestamp) row[4];
					Calendar dout = Calendar.getInstance();
					dout.setTimeInMillis(tdout.getTime());
					Calendar din = Calendar.getInstance();
					din.setTimeInMillis(tdin.getTime());
					view.setHiretypeid((String) row[5]);
					view.setDout(dout);
					view.setDin(din);
					view.setDateFormaterOut(simpleDateFormat.format(dout.getTime()));
					view.setDateFormaterIn(simpleDateFormat.format(din.getTime()));
					view.setRegno((String) row[6]);
					view.setNettotal(((BigDecimal)row[7]));
					view.setComileage(((Integer)row[8]));
					view.setNoofday(((Integer)row[9]));
					view.setDebcode(((String)row[10]));
					view.setRemarks(((String)row[11]));//deb name
					view.setAddmach(((String)row[12]));//DEBADD
					view.setCancelled(((String)row[13]));//NICNO
					view.setBooked(((String)row[14]));//finvhed.invno
					
					lstFreservationView.add(view);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			lstRow=null;
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("FreservationDAO.loadCHD :"+totalTime+" size :"+lstFreservationView.size());
			return lstFreservationView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.flush();
		}
		
	}
	
	
	
/*
	
	like query 2014.02.26
	@Transactional(readOnly=true)
	//@Override
	public List<FreservationView> listAllByTextSearch(String searchText) {
		try {
			long startTime = System.currentTimeMillis();
			em.flush();
			String query="select r.agrno,r.resno,r.cohirestsid,(select ( debcode+' '+ debname) as debcode from Fdebtor where debcode= r.debcode),r.hiretypeid,r.dout,r.din,r.timein,r.timeout,r.taxcomcode,r.gdout,r.gotime,v.regno" +
					" from freservation where resno like '%"+searchText+"%' or agrno like '%"+searchText+"%'";
			List<Object[]> lstRow= em.createNativeQuery(query).setParameter(1, searchText).setParameter(2, searchText).getResultList();
			List<FreservationView> lstFreservationView = new ArrayList<FreservationView>();
			for(Object[] row : lstRow)
			{
				
				try {
					FreservationView view = new FreservationView();
					view.setAgrno((String) row[0]);
					view.setResno((String) row[1]);
					view.setCohirestsid((String) row[2]);
					view.setDebcode((String) row[3]);
					view.setHiretypeid((String) row[4]);
					Timestamp tdout = (Timestamp) row[5];
					Timestamp tdin = (Timestamp) row[6];
					Calendar dout = Calendar.getInstance();
					dout.setTimeInMillis(tdout.getTime());
					Calendar din = Calendar.getInstance();
					din.setTimeInMillis(tdin.getTime());
					view.setDout(dout);
					view.setDin(din);
					view.setTimein((String) row[7]);
					view.setTimeout((String) row[8]);
					view.setTaxcomcode((String) row[9]);
					Timestamp tgdout = (Timestamp) row[10];
					Calendar gdout = Calendar.getInstance();
					gdout.setTimeInMillis(tgdout.getTime());
					view.setGdout(gdout);
					view.setGotime((String) row[11]);
					view.setRegno((String) row[12]);
					lstFreservationView.add(view);
				} catch (Exception e) {
					e.printStackTrace();
				}
				lstFreservationView.add(fdebtor);
			}
			lstRowDebtors=null;
			
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("FdebtorDAO.listAllOptimized :"+totalTime);
			
			return lstFreservation;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.flush();
		}
		
	}

	*/
	
	//Freshed

//	@Transactional(readOnly=false)
//	@Override
//	public Freshed listAllByReshed(String resno) {
//		 em.createNamedQuery("FresaccsListByresNo").setParameter("resno", resno).getResultList();
//	}
	
	@Transactional(readOnly=false)
	@Override
	public int count() {
		em.flush();
		return (Integer)(em.createNativeQuery("SELECT COUNT(*) AS COUNT FROM Freservation").getSingleResult());
	}

/*	@Transactional(readOnly=false)
	@Override
	public void create(Freservation freservation) {
		em.persist(freservation);
	}*/

	@Transactional(readOnly=false)
	@Override
	public Freservation findByID(String resno) {
		em.flush();
		return em.find(Freservation.class, resno);
	}

	/***
	 * In here coded only for the deltetion.
	 * Todo :log files
	 * 
	 */
	@Transactional(readOnly=false)
	@Override
	public void removeByID(String resno) {
		System.out.println("resno=?"+resno);
		em.flush();
		em.createNamedQuery("FresvehicleDeleteByResno").setParameter("resno", resno).executeUpdate();
		em.createNamedQuery("FresaccsDeleteByResno").setParameter("resno", resno).executeUpdate();
		em.createNamedQuery("FresothsrvDeleteByResno").setParameter("resno", resno).executeUpdate();
		em.createNamedQuery("FresdriverDeleteByResno").setParameter("resno", resno).executeUpdate();
		em.createNamedQuery("FresaccrateDeleteByResno").setParameter("resno", resno).executeUpdate();
		em.createNamedQuery("FresothersrvrateDeleteByResno").setParameter("resno", resno).executeUpdate();
		em.createNamedQuery("FresvehiclerateDeleteByResno").setParameter("resno", resno).executeUpdate();
		em.createNamedQuery("FresdriverrateDeleteByResno").setParameter("resno", resno).executeUpdate();
		em.createNamedQuery("FreservationDeleteByResno").setParameter("resno", resno).executeUpdate();
		em.createNamedQuery("FresaddchargeDeleteByresno").setParameter("resno", resno).executeUpdate();
		em.createNamedQuery("FresvehicledamagedeleteByResNo").setParameter("resno",resno).executeUpdate();
		em.createNamedQuery("FresvehinvDeleteByResno").setParameter("resno",resno).executeUpdate();
		em.flush();
	}

/*	@Transactional(readOnly=false)
	@Override
	public void udpate(Freservation freservation) {
		em.merge(freservation);
	}*/
	private Freservation updateModifiedUser(Freservation reservation)
	{
		Freservation fres=em.find(Freservation.class, reservation.getResno());
		if(fres!=null){
		reservation.setBooked(fres.getBooked());
		reservation.setConfirmed(fres.getConfirmed());
		reservation.setCancelled(fres.getCancelled());
		reservation.setCheckout(fres.getCheckout());
		reservation.setCheckin(fres.getCheckin());
		reservation.setCompleted(fres.getCompleted());
		
		reservation.setBookdate(fres.getBookdate());
		reservation.setConfirmdate(fres.getConfirmdate());
		reservation.setCanceldate(fres.getCanceldate());
		reservation.setCodate(fres.getCodate());
		reservation.setCidate(fres.getCidate());
		reservation.setCompletedate(fres.getCompletedate());
		reservation.setAdddate(fres.getAdddate());
		
		}
		
		if(reservation.getCohirestsid().equalsIgnoreCase("BOOKED"))
		{
			if(reservation.getBookdate()==null)
				reservation.setAdddate(Calendar.getInstance());
			
			reservation.setBooked(reservation.getAdduser());
			reservation.setBookdate(Calendar.getInstance());

		}
		else if(reservation.getCohirestsid().equalsIgnoreCase("CONFIRMED"))
		{
			if(reservation.getConfirmdate()==null && reservation.getBookdate()==null)
				reservation.setAdddate(Calendar.getInstance());
			
			reservation.setConfirmed(reservation.getAdduser());
			reservation.setConfirmdate(Calendar.getInstance());
		}
		else if(reservation.getCohirestsid().equalsIgnoreCase("CANCELLED"))
		{
			reservation.setCancelled(reservation.getAdduser());
			reservation.setCanceldate(Calendar.getInstance());
		}
		else if(reservation.getCohirestsid().equalsIgnoreCase("CHECKOUT"))
		{
			reservation.setCheckout(reservation.getAdduser());
			reservation.setCodate(Calendar.getInstance());
		}
		else if(reservation.getCohirestsid().equalsIgnoreCase("CHECKIN"))
		{
			reservation.setCheckin(reservation.getAdduser());
			reservation.setCidate(Calendar.getInstance());
		}
		else if(reservation.getCohirestsid().equalsIgnoreCase("COMPLETED"))
		{
			reservation.setCompleted(reservation.getAdduser());
			reservation.setCompletedate(Calendar.getInstance());
		}
		else if(reservation.getCohirestsid().equalsIgnoreCase("INVOICE"))
		{
			reservation.setInvoiced(reservation.getAdduser());
		}
		else if(reservation.getCohirestsid().equalsIgnoreCase("VC"))
		{
			reservation.setConfirmed(reservation.getAdduser());
		}
		em.flush();
		return reservation;
		
	}
	
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public synchronized String createCopy(String resno)
	{

		String lsAddUser = userInfoSRV.getUser();
		String lsAddMachine = userInfoSRV.getMachineName();
		Calendar today=Calendar.getInstance();
		
		Freservation freservation=em.find(Freservation.class, resno);
		String refNo=fcompanysettingDAO.getRefNo("RES", freservation.getAdddate().get(Calendar.YEAR), freservation.getAdddate().get(Calendar.MONTH)+1);
		freservation.setAdddate(today);
		freservation.setCohirestsid("BOOKED");
		freservation.setResno(resno);
		em.persist(freservation);	
		System.out.println("create new resno :"+refNo);
		return refNo;
	}
			
			
	/***
	 * Following method fire when user add, booking or confirm state reservation.
	 * 
	 * */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public synchronized String create(
			Freservation freservation,
			Freshed freshed, 
			List<Fresvehicle> lstfresvehicle,
			List<Fresaccs> lstfresaccs, 
			List<Fresdriver> lstfresdriver, 
			List<Fresothsrv> lstfresothsrv,
			Fresvehiclerate fresvehiclerate, 
			List<Fresaccrate> lstfresaccrate,
			Fresdriverrate fresdriverrate, 
			List<Fresothersrvrate> lstfresothersrvrate,
			List<Fresaddcharge> lstfresaddcharge,
			List<Fresclientdriver> lstfresclientdriver,
			Boolean ignoreAvailability
			) {
		
		long startTime = System.currentTimeMillis();
		em.flush();
		String lsAddUser = userInfoSRV.getUser();
		String lsAddMachine = userInfoSRV.getMachineName();
		Calendar today=Calendar.getInstance();
		freservation.setAdddate(today);		
		String agrno="";
		if(freshed.getIsNew())
		{
				agrno=fcompanysettingDAO.getRefNo("RSH", freservation.getAdddate().get(Calendar.YEAR), freservation.getAdddate().get(Calendar.MONTH)+1);
				freshed.setAgrno(agrno);
				freshed.setAdddate(today);
				freshed.setAdduser(lsAddUser);
				freshed.setUuid(UUID.randomUUID().toString());
				em.persist(freshed);
		}
		else
		{
			agrno=freshed.getAgrno();
		}
		
		String refNo=fcompanysettingDAO.getRefNo("RES", freservation.getAdddate().get(Calendar.YEAR), freservation.getAdddate().get(Calendar.MONTH)+1);
		String serverName ="/resource";//FlexContext.getHttpRequest().getContextPath();
		String targetFolder = "/image/damageinfo/";
		String fileName=refNo.replaceAll("/", "-");
		if(freservation.getCohirestsid().equals("CHECKOUT"))
		{
			fileName=fileName+"-CO.jpg";
			freservation.setCodamage(serverName+targetFolder+fileName);
		}
		else if(freservation.getCohirestsid().equals("BOOKED") || freservation.getCohirestsid().equals("PREPARED") || freservation.getCohirestsid().equals("CONFIRMED"))
		{
			fileName=fileName+"-B.jpg";
			freservation.setCodamage(serverName+targetFolder+fileName);
			freservation.setCidamage(serverName+targetFolder+fileName);
			try {
				fileUploadSRV.uploadFileToServer(fileName,freservation.getCheckoutdata(), targetFolder);//error
			} catch (Exception e) {
				System.out.println("Error in fileUploadSRV.uploadFileToServer" + e.getMessage());
			}
		}
		freservation.setRatetype(fresvehiclerate.getRatetype());
		freservation.setAgrdout(freservation.getDout());
		freservation.setAgrdin(freservation.getDin());
		freservation.setResno(refNo);
		freservation.setAgrno(agrno);
		freservation.setAdddate(today);
		freservation.setAdduser(lsAddUser);
		freservation.setAddmach(lsAddMachine);
		freservation.setUuid(UUID.randomUUID().toString());
		
		//Following code handle the concurrent control
		//---------------------------------------------------------------------------------------------------------------------------------
		if(!ignoreAvailability)//beacause if it was a renew of long term hire then ignore it
		{
			if(lstfresvehicle.size()>0)
			{
				int avaialability=0;
				Fresvehicle fresvehicle = lstfresvehicle.get(0);
				avaialability=vehiclesearchDAO.getAvailability(freservation.getDout(),freservation.getDin(),fresvehicle.getId().getRegno());
				if(avaialability==1)
				{
					throw new RuntimeException("Sorry,\nSelected vehicle has been already allocated for a hire.\nPlease select another vehicle and re-submit.");
				}
			}
		}
		//---------------------------------------------------------------------------------------------------------------------------------
		System.out.println("save reservation");
		updateModifiedUser(freservation);
		em.persist(freservation);
		em.flush();
		
		


		
		
		for(Fresvehicle fresvehicle : lstfresvehicle)
		{
			fresvehicle.getId().setResno(refNo);
			fresvehicle.setAdduser(lsAddUser);
			fresvehicle.setUuid(UUID.randomUUID().toString());
			
			em.persist(fresvehicle);
			em.flush();
		}
		

		
		for(Fresaccs fresaccs : lstfresaccs)
		{
			fresaccs.getId().setResno(refNo);
			fresaccs.setAdduser(lsAddUser);
			fresaccs.setUuid(UUID.randomUUID().toString());
				
			em.persist(fresaccs);
			em.flush();
		}
		for(Fresothsrv fresothsrv :lstfresothsrv)
		{
			fresothsrv.getId().setResno(refNo);
			fresothsrv.setAdduser(lsAddUser);
			fresothsrv.setUuid(UUID.randomUUID().toString());
				
			em.persist(fresothsrv);
			em.flush();
		}

		for(Fresaccrate fresaccrate :lstfresaccrate)
		{
			fresaccrate.getId().setResno(refNo);			
			fresaccrate.setAdduser(lsAddUser);
			fresaccrate.setUuid(UUID.randomUUID().toString());
				
			em.persist(fresaccrate);
			em.flush();
		}
		for(Fresothersrvrate fresothersrvrate:lstfresothersrvrate)
		{
			fresothersrvrate.getId().setResno(refNo);
			fresothersrvrate.setAdduser(lsAddUser);
			fresothersrvrate.setUuid(UUID.randomUUID().toString());
				
			em.persist(fresothersrvrate);
			em.flush();
		}
		
		for(Fresaddcharge fresaddcharge:lstfresaddcharge)
		{
			fresaddcharge.getId().setResno(refNo);				
			fresaddcharge.setAdduser(lsAddUser);
			fresaddcharge.setUuid(UUID.randomUUID().toString());
				
			em.persist(fresaddcharge);		
			em.flush();
		}

		
		fresvehiclerate.setResno(refNo);
		fresvehiclerate.setAdduser(lsAddUser);
		fresvehiclerate.setUuid(UUID.randomUUID().toString());
		em.persist(fresvehiclerate);
		em.flush();
		
		//System.out.println("Hiretypeid ="+freservation.getHiretypeid());
	//	if(!freservation.getHiretypeid().equals("SD"))
	//	{
		
		for(Fresdriver fresdriver:lstfresdriver)
		{
			fresdriver.getId().setResno(refNo);
			fresdriver.setAdduser(lsAddUser);
			fresdriver.setUuid(UUID.randomUUID().toString());
			
			em.persist(fresdriver);
			em.flush();
		}			
		//if(fresdriverrate !=null){
		fresdriverrate.getId().setResno(refNo);
		fresdriverrate.setAdduser(lsAddUser);
		fresdriverrate.setUuid(UUID.randomUUID().toString());
		em.persist(fresdriverrate);
		em.flush();	
		//}
		
		//UPDATE=DELETE+INSERT APPLY FOR RES-CLIENT-DRIVER
		//START
		//em.createNamedQuery("FresclientdriverDeleteByResno").setParameter("resno", refNo).executeUpdate();
		//em.flush();
		for(Fresclientdriver fresclientdriver:lstfresclientdriver)
		{
			fresclientdriver.getId().setResno(refNo);
			em.persist(fresclientdriver);
		}
		em.flush();
		//END
		
		
		
			/*
			SELECT COUNT(regno) FROM fresvehicle 
			WHERE priority=1 AND regno=?1 AND resno IN (SELECT resno FROM freservation 
			WHERE resno IN (SELECT resno FROM freservation 
			WHERE ?2 BETWEEN dout AND din OR ?3 BETWEEN dout AND 
			din AND dout BETWEEN ?2 AND ?3 OR din BETWEEN ?2 AND ?3) 
			AND cohirestsid IN ('BOOKED','CHECKIN','CHECKOUT','CONFIRMED','PREPARED'))
			*/
			
			
	//	}	
		//System.out.println("end loop");
/*			
		if(freservation.getCohirestsid().equals("CHECKOUT"))
		{
				for(int i=0;i<lstfresvehicle.size();i++)
				{
					if(i==0)
					{
						Fresvehicle fresvehicle = lstfresvehicle.get(i);
						List<Fresvehinv> tempList= fresvehicle.getLstFresvehinv();
						//should update the fvehicleinventry also
						em.createNamedQuery("FvehicleinventryDeleteByRegNo").setParameter("regno",fresvehicle.getId().getRegno() ).executeUpdate();
						for(Fresvehinv tempFresvehinv : tempList)
						{
							tempFresvehinv.getId().setResno(refNo);
							tempFresvehinv.setCheckout(1);
							tempFresvehinv.setCheckin(0);
							tempFresvehinv.setAdduser(adduser);
							em.persist(tempFresvehinv);//UPDATE THE RESERVATION
							Fvehicleinventry fvehicleinventry=new Fvehicleinventry();
							FvehicleinventryPK fvehicleinventryPK=new FvehicleinventryPK();
							fvehicleinventryPK.setInvid(tempFresvehinv.getId().getInvid());
							fvehicleinventryPK.setRegno(tempFresvehinv.getId().getRegno());
							fvehicleinventry.setId(fvehicleinventryPK);
							fvehicleinventry.setAdduser(adduser);
							em.persist(fvehicleinventry);//UPDATE THE VEHICLE STATUS
						}
						
						//set vehicle status damage update
						//delete existing one in vehicle
						int isUpdate=em.createNamedQuery("FvehicledamagedeleteByRegNo").setParameter("regno",fresvehicle.getId().getRegno() ).executeUpdate();
						//System.out.println("execute the query FvehicledamagedeleteByRegNo\nisUpdate"+isUpdate);
						//If update : delete existing one in resvehicledamage
						//em.createNamedQuery("FresvehicledamagedeleteByRegNo").setParameter("regno",fresvehicle.getId().getRegno() ).executeUpdate();
						List<Fvehicledamage> tempListFvehicledamage= fresvehicle.getLstFvehicledamage();	
						for(Fvehicledamage tempFvehicledamage : tempListFvehicledamage)
						{
							//System.out.println("before save___________________________1");
							tempFvehicledamage.setRegno(fresvehicle.getId().getRegno());
							tempFvehicledamage.setAdduser(adduser);
							//System.out.println("save damages-------------------------------------");
							em.persist(tempFvehicledamage);////UPDATE THE VEHICLE STATUS
							//create new Fresvehicledamage object from Fvehicledamage
							//System.out.println("em.persist(tempFvehicledamage)");
							Fresvehicledamage fresvehicledamage=new Fresvehicledamage();
							fresvehicledamage.setDamagetype(tempFvehicledamage.getDamagetype());
							fresvehicledamage.setIoflag(1);
							fresvehicledamage.setRegno(tempFvehicledamage.getRegno());
							fresvehicledamage.setResno(refNo);
							fresvehicledamage.setSeq(tempFvehicledamage.getSeq());
							fresvehicledamage.setXvalue(tempFvehicledamage.getXvalue());
							fresvehicledamage.setYvalue(tempFvehicledamage.getYvalue());
							fresvehicledamage.setAdduser(adduser);
							//System.out.println("fffffffffff");
							em.persist(fresvehicledamage);
						}
					//	System.out.println("___________________________");
						Fvehicle fvehicle=em.find(Fvehicle.class, fresvehicle.getId().getRegno());
						fvehicle.setFuellevel(freservation.getCofuellevel());
						fvehicle.setCurmileage(freservation.getComileage());
						fvehicle.setAdduser(adduser);
						em.merge(fvehicle);////UPDATE THE VEHICLE STATUS
						
						//fileUploadSRV.doUpload(fileName, freservation.getCheckoutdata());
						fileUploadSRV.uploadFileToServer(fileName,freservation.getCheckoutdata(), targetFolder);
						
					}
					else break;
				}
		}
*/
		
		//System.out.println("After Save :) RefNo :"+refNo);
		em.flush();
		//call to email details relatonal table,create new fresconfirm object in DB
		fresconfirmDAO.createByFreservation(freservation,freshed.getDebcode());
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("create :"+totalTime);
		log.info("create :"+totalTime);
		
		
		return refNo+","+agrno;
		
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	//design for booking -->checkout,booking -->confirm
	/***
	 * Following method fire when user update reservation
	 * possible state changing are following
	 * book		-->cancel
	 * book		-->confirm
	 * confirm	-->checkout
	 * checkout	-->checkin
	 * checkin	-->complete
	 * checkin	-->noshow
	 * 
	 * 
	 */
	@Transactional(readOnly=false,rollbackFor=RuntimeException.class,propagation=Propagation.REQUIRED)
	@Override
	public synchronized String changeStatus(
			Freservation freservation,
			Freshed freshed, 
			List<Fresvehicle> lstfresvehicle,
			List<Fresaccs> lstfresaccs, 
			List<Fresdriver> lstfresdriver, 
			List<Fresothsrv> lstfresothsrv,
			Fresvehiclerate fresvehiclerate, 
			List<Fresaccrate> lstfresaccrate,
			Fresdriverrate fresdriverrate, 
			List<Fresothersrvrate> lstfresothersrvrate,
			List<Fresaddcharge> lstfresaddcharge,
			List<Fresclientdriver> lstfresclientdriver,
			List<Fvehicledamage> lstCheckInFvehicledamage,
			List<Fresvehinv> lstCheckInFresvehinv) {
		
		
		em.flush();
		System.out.println("damage size:"+lstCheckInFvehicledamage.size());
		//SET ADD DATE AND OTHER USER INFO
		Calendar today=Calendar.getInstance();
		String lsAddUser = userInfoSRV.getUser();
		String lsAddMachine =userInfoSRV.getMachineName();
		
		String refNo=freservation.getResno();
		String serverName = "/resource";//FlexContext.getHttpRequest().getContextPath();
		String targetFolder = "/image/damageinfo/";
		String fileName=refNo.replaceAll("/", "-");
		System.out.println("fileName:"+fileName);
		
		if(freservation.getCohirestsid().equals("BOOKED") || freservation.getCohirestsid().equals("PREPARED") || freservation.getCohirestsid().equals("CONFIRMED"))
		{
			fileName=fileName+"-B.jpg";
			freservation.setCodamage(serverName+targetFolder+fileName);
			freservation.setCidamage(serverName+targetFolder+fileName);
			fileUploadSRV.uploadFileToServer(fileName,freservation.getCheckoutdata(), targetFolder);
		}
		if(freservation.getCohirestsid().equals("CHECKIN") || freservation.getCohirestsid().equals("COMPLETED") || freservation.getCohirestsid().equals("NOSHOW"))
		{
			fileName=fileName+"-CI.jpg";
			freservation.setCidamage(serverName+targetFolder+fileName);
		}
		else if(freservation.getCohirestsid().equals("CHECKOUT"))
		{
			fileName=fileName+"-CO.jpg";
			freservation.setCodamage(serverName+targetFolder+fileName);
			freservation.setCidamage(serverName+targetFolder+fileName);
		}
		else if(freservation.getCohirestsid().equals("CONFIRMED"))
		{
			freservation.setAgrdout(freservation.getDout());
			freservation.setAgrdin(freservation.getDin());
		}
		if (freservation.getUuid()==null || freservation.getUuid().isEmpty())
			freservation.setUuid(UUID.randomUUID().toString());
		freservation.setRatetype(fresvehiclerate.getRatetype());
		freservation.setAdddate(today);
		freservation.setAdduser(lsAddUser);
		freservation.setAddmach(lsAddMachine);
		
		
		//CODE-PERFORM:BEFORE SAVE THE RESERVATION MAKE SURE THAT NO ONE ELSE ALLOCATE THE SAME VEHICLE AT THE SAME TIME.
		//Following code handle the concurrent control
		//---------------------------------------------------------------------------------------------------------------------------------
		int avaialability=0;
		if(lstfresvehicle.size()>0)
		{
			Fresvehicle fresvehicle = lstfresvehicle.get(0);
			avaialability=vehiclesearchDAO.getAvailabilityByResNo(freservation.getDout(),freservation.getDin(),fresvehicle.getId().getRegno(),refNo);
			System.out.println("avaialability:"+avaialability);
			if(avaialability==1 || avaialability>0)
			{
				List<String> lst= vehiclesearchDAO.getUnAvailabileListByResNo(freservation.getDout(),freservation.getDin(),fresvehicle.getId().getRegno(),refNo);
				System.out.println("length:"+lst.size());
				String msg="Sorry,\nHire Extention Failed.\nSelected vehicle has been already allocated for a hire.\nPlease un-allocate vehicle from \nresevation "+lst.toString()+" \nand try again.";
				throw new RuntimeException(msg);
			}
		}
		//---------------------------------------------------------------------------------------------------------------------------------
		
		updateModifiedUser(freservation);
		//UPDATE CHANGES TO RESERVATION MASTER TABLE
		em.merge(freservation);
		//em.flush();
		
		
		
		
		
		
		
		//UPDATE=DELETE+INSERT APPLY FOR RES-VEHICLE
		//START
		em.createNamedQuery("FresvehicleDeleteByResno").setParameter("resno", refNo).executeUpdate();
		for(Fresvehicle fresvehicle : lstfresvehicle)
		{
			if (fresvehicle.getUuid()==null || fresvehicle.getUuid().isEmpty()){
				fresvehicle.setUuid(UUID.randomUUID().toString());
				fresvehicle.setAdduser(lsAddUser);
			}
			
			fresvehicle.getId().setResno(refNo);
			em.persist(fresvehicle);
			//em.flush();
		}
		
		
		
		
		
		//UPDATE=DELETE+INSERT APPLY FOR RES-ACCESSORIES
		//START
		em.createNamedQuery("FresaccsDeleteByResno").setParameter("resno", refNo).executeUpdate();		
		for(Fresaccs fresaccs : lstfresaccs)
		{
			if (fresaccs.getUuid()==null || fresaccs.getUuid().isEmpty()){
				fresaccs.setUuid(UUID.randomUUID().toString());
				fresaccs.setAdduser(lsAddUser);
			}
			//start:test case
/*			SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd/MM/yy");
			String dateAsString = simpleDateFormat.format(new Date());
			System.out.println("START--------------------------------------------");
			System.out.println("resno="+fresaccs.getId().getResno());
			System.out.println("dfrom:"+simpleDateFormat.format(fresaccs.getId().getDfrom().getTime()));
			System.out.println("dto:"+simpleDateFormat.format(fresaccs.getId().getDto().getTime()));
			System.out.println("accid:"+fresaccs.getId().getAccid());
			System.out.println("END--------------------------------------------");*/
			//end test case
			fresaccs.getId().setResno(refNo);
			em.persist(fresaccs);
			//em.flush();
		}
		//END
		
		
		
		
		//UPDATE=DELETE+INSERT APPLY FOR RES-OTHERSERVICE
		//START
		em.createNamedQuery("FresothsrvDeleteByResno").setParameter("resno", refNo).executeUpdate();
		for(Fresothsrv fresothsrv :lstfresothsrv)
		{
			if (fresothsrv.getUuid()==null || fresothsrv.getUuid().isEmpty()){
				fresothsrv.setUuid(UUID.randomUUID().toString());
				fresothsrv.setAdduser(lsAddUser);
			}
			
			fresothsrv.getId().setResno(refNo);
			em.persist(fresothsrv);
			//em.flush();
		}
		//END
		
		
		
		
		
		//UPDATE=DELETE+INSERT APPLY FOR RES-ACCESSORIES-RATE
		//START
		em.createNamedQuery("FresaccrateDeleteByResno").setParameter("resno", refNo).executeUpdate();
		em.flush();
		for(Fresaccrate fresaccrate :lstfresaccrate)
		{
			if (fresaccrate.getUuid()==null || fresaccrate.getUuid().isEmpty()){
				fresaccrate.setUuid(UUID.randomUUID().toString());
				fresaccrate.setAdduser(lsAddUser);
			}
			
			fresaccrate.getId().setResno(refNo);			
			em.persist(fresaccrate);
			//em.flush();
		}
		//END
		
		
		
		
		//UPDATE=DELETE+INSERT APPLY FOR RES-OTHERSERVICE-RATE
		//START
		em.createNamedQuery("FresothersrvrateDeleteByResno").setParameter("resno", refNo).executeUpdate();
		em.flush();
		for(Fresothersrvrate fresothersrvrate:lstfresothersrvrate)
		{
			if (fresothersrvrate.getUuid()==null || fresothersrvrate.getUuid().isEmpty()){
				fresothersrvrate.setUuid(UUID.randomUUID().toString());
				fresothersrvrate.setAdduser(lsAddUser);
			}
			
			fresothersrvrate.getId().setResno(refNo);
			em.persist(fresothersrvrate);
			//em.flush();
		}
		//END
		
		
		
		
		//UPDATE=DELETE+INSERT APPLY FOR RES-ADDITIONAL-CHARGES
		//START
		em.createNamedQuery("FresaddchargeDeleteByresno").setParameter("resno", refNo).executeUpdate();
		for(Fresaddcharge fresaddcharge:lstfresaddcharge)
		{
			if (fresaddcharge.getUuid()==null || fresaddcharge.getUuid().isEmpty()){
				fresaddcharge.setUuid(UUID.randomUUID().toString());
				fresaddcharge.setAdduser(lsAddUser);
			}
			
			fresaddcharge.getId().setResno(refNo);				
			em.persist(fresaddcharge);		
			//em.flush();
		}
		//END
		
		
		
		
		//UPDATE=DELETE+INSERT APPLY FOR RES-VEHICLE-RATE
		//START
		em.createNamedQuery("FresvehiclerateDeleteByResno").setParameter("resno", refNo).executeUpdate();
		if (fresvehiclerate.getUuid()==null || fresvehiclerate.getUuid().isEmpty()){
			fresvehiclerate.setUuid(UUID.randomUUID().toString());
			fresvehiclerate.setAdduser(lsAddUser);
		}
		
		fresvehiclerate.setResno(refNo);
		em.persist(fresvehiclerate);
		//em.flush();
		//END
		
		
		
		
		
		//UPDATE=DELETE+INSERT APPLY FOR RES-DRIVER
		//START
		em.createNamedQuery("FresdriverDeleteByResno").setParameter("resno", refNo).executeUpdate();
		em.flush();
		for(Fresdriver fresdriver:lstfresdriver)
		{
			if (fresdriver.getUuid()==null || fresdriver.getUuid().isEmpty()){
				fresdriver.setUuid(UUID.randomUUID().toString());
				fresdriver.setAdduser(lsAddUser);
			}
			fresdriver.getId().setResno(refNo);
			em.persist(fresdriver);
			//em.flush();
		}
		//END
		
	
		
		//UPDATE=DELETE+INSERT APPLY FOR RES-DRIVER-RATE
		//START
		em.createNamedQuery("FresdriverrateDeleteByResno").setParameter("resno", refNo).executeUpdate();
		em.flush();
		fresdriverrate.getId().setResno(refNo);
		em.persist(fresdriverrate);
		//em.flush();
		//END
		
		//UPDATE=DELETE+INSERT APPLY FOR RES-CLIENT-DRIVER
		//START
		em.createNamedQuery("FresclientdriverDeleteByResno").setParameter("resno", refNo).executeUpdate();
		em.flush();
		for(Fresclientdriver fresclientdriver:lstfresclientdriver)
		{
			fresclientdriver.getId().setResno(refNo);
			em.persist(fresclientdriver);
		}
		em.flush();
		//END
		

			
			
			
			
			
		
		
		
		
		
		
		//IF CHECK OUT OPERATION, THEN FOLLOWING CODE WILL APPLY
		if(freservation.getCohirestsid().equals("CHECKOUT"))
		{
			for(int i=0;i<lstfresvehicle.size();i++)
			{
				if(i==0)
				{
					Fresvehicle fresvehicle = lstfresvehicle.get(i);
					List<Fresvehinv> tempList= fresvehicle.getLstFresvehinv();
					//should update the fvehicleinventry also
					em.createNamedQuery("FvehicleinventryDeleteByRegNo").setParameter("regno",fresvehicle.getId().getRegno() ).executeUpdate();
					//em.flush();
				//	System.out.println("11");
					em.createNamedQuery("FresvehinvDeleteByResno").setParameter("resno",fresvehicle.getId().getResno() ).executeUpdate();
					//em.flush();
					for(Fresvehinv tempFresvehinv : tempList)
					{ 
						tempFresvehinv.getId().setResno(refNo);
						tempFresvehinv.setCheckout(1);
						tempFresvehinv.setCheckin(0);
						
						if (tempFresvehinv.getUuid()==null || tempFresvehinv.getUuid().isEmpty()){
							tempFresvehinv.setUuid(UUID.randomUUID().toString());
							tempFresvehinv.setAdduser(lsAddUser);
						}
						
						em.persist(tempFresvehinv);//UPDATE THE RESERVATION
						//em.flush();
						Fvehicleinventry fvehicleinventry=new Fvehicleinventry();
						FvehicleinventryPK fvehicleinventryPK=new FvehicleinventryPK();
						fvehicleinventryPK.setInvid(tempFresvehinv.getId().getInvid());
						fvehicleinventryPK.setRegno(tempFresvehinv.getId().getRegno());
						fvehicleinventry.setId(fvehicleinventryPK);
						fvehicleinventry.setUuid(tempFresvehinv.getUuid());
						fvehicleinventry.setAdduser(lsAddUser);
						
						em.persist(fvehicleinventry);//UPDATE THE VEHICLE STATUS
						//em.flush();
					}
					 
					//UPDATE=DELETE+INSERT APPLY FOR VEHICLE-DAMAGE(UPDATE THE CURRENT VEHICLE STATUS INTO MASTER TABLE)/RES-VEHICLE-DAMAGE
					//START
					em.createNamedQuery("FvehicledamagedeleteByRegNo").setParameter("regno",fresvehicle.getId().getRegno() ).executeUpdate();
					em.createNamedQuery("FresvehicledamagedeleteByRegNoIO").setParameter("regno",fresvehicle.getId().getRegno() ).setParameter("resno",fresvehicle.getId().getResno() ).setParameter("ioflag",1).executeUpdate();
					//em.createNamedQuery("FresvehicledamagedeleteByRegNo").setParameter("regno",fresvehicle.getId().getRegno() ).setParameter("resno",fresvehicle.getId().getResno() ).executeUpdate();
					List<Fvehicledamage> tempListFvehicledamage= fresvehicle.getLstFvehicledamage();
					//em.flush();
					for(Fvehicledamage tempFvehicledamage : tempListFvehicledamage)
					{
						tempFvehicledamage.setRegno(fresvehicle.getId().getRegno());
						
						if (tempFvehicledamage.getUuid()==null || tempFvehicledamage.getUuid().isEmpty()){
							tempFvehicledamage.setUuid(UUID.randomUUID().toString());
							tempFvehicledamage.setAdduser(lsAddUser);
						}
						
						em.persist(tempFvehicledamage);////UPDATE THE VEHICLE STATUS
						//em.flush();
						//create new Fresvehicledamage object from Fvehicledamage
						//System.out.println("em.persist(tempFvehicledamage)");
						
						Fresvehicledamage fresvehicledamage=new Fresvehicledamage();
						fresvehicledamage.setDamagetype(tempFvehicledamage.getDamagetype());
						fresvehicledamage.setIoflag(1);
						fresvehicledamage.setRegno(tempFvehicledamage.getRegno());
						fresvehicledamage.setResno(refNo);
						fresvehicledamage.setSeq(tempFvehicledamage.getSeq());
						fresvehicledamage.setXvalue(tempFvehicledamage.getXvalue());
						fresvehicledamage.setYvalue(tempFvehicledamage.getYvalue());
						fresvehicledamage.setAdduser(lsAddUser);
						if (tempFvehicledamage.getUuid()==null || tempFvehicledamage.getUuid().isEmpty()){
							fresvehicledamage.setUuid(UUID.randomUUID().toString());
							fresvehicledamage.setAdduser(lsAddUser);
						}
						else
							fresvehicledamage.setUuid(tempFvehicledamage.getUuid());
						
						em.persist(fresvehicledamage);
						//em.flush();
					}
					//END
					
					//UPDATE VEHICLE STATUS
					Fvehicle fvehicle=em.find(Fvehicle.class, fresvehicle.getId().getRegno());
					fvehicle.setFuellevel(freservation.getCofuellevel());
					fvehicle.setCurmileage(freservation.getComileage());
					if(freservation.getCoLocation()!=null)
					fvehicle.setLocationid(freservation.getCoLocation());
					em.merge(fvehicle);////UPDATE THE VEHICLE STATUS
					//em.flush();
					//freservation.setCodamage(fileName);
					try {
						fileUploadSRV.uploadFileToServer(fileName, freservation
								.getCheckoutdata(), targetFolder);
					} catch (Exception e) {
						System.out.println("ererer" + e.getMessage());
					}
				}
				else break;
			}
		}
		//end checkout transaction
		
		
		
		
		
		
		
		
		
		
		
		
		
		//start checking transaction
		if(freservation.getCohirestsid().equals("CHECKIN") || freservation.getCohirestsid().equals("COMPLETED") || freservation.getCohirestsid().equals("NOSHOW"))
		{
			
			
			//UPDATE=DELETE+INSERT APPLY FOR FRES-VEHCLE-INVENTORY,VEHICLE-INVETORY
			//START
			for(int i=0;i<lstfresvehicle.size();i++)
			{
				if(i==0)
				{
					Fresvehicle fresvehicle = lstfresvehicle.get(i);
				//	List<Fresvehinv> tempList= fresvehicle.getLstFresvehinv();
					List<Fresvehinv> tempList= lstCheckInFresvehinv;
					System.out.println("tempList"+tempList.size());
					//should update the fvehicleinventry also
					em.createNamedQuery("FvehicleinventryDeleteByRegNo").setParameter("regno",fresvehicle.getId().getRegno() ).executeUpdate();
					em.createNamedQuery("FresvehinvResetCheckInByResno").setParameter("resno",fresvehicle.getId().getResno() ).executeUpdate();
					em.flush();
					for(Fresvehinv tempFresvehinv : tempList)
					{
						if(tempFresvehinv !=null)
						{
							tempFresvehinv.getId().setResno(refNo);
							tempFresvehinv=em.find(Fresvehinv.class, tempFresvehinv.getId());
							if(tempFresvehinv==null)
							{
								throw new RuntimeException("Unable to change vehicle,After checkout operation occured.");
							}
							else
							{
								tempFresvehinv.setCheckin(1);//set CHECKIN flag
								em.merge(tempFresvehinv);//UPDATE THE RESERVATION
								em.flush();
							}
						}

						Fvehicleinventry fvehicleinventry=new Fvehicleinventry();
						FvehicleinventryPK fvehicleinventryPK=new FvehicleinventryPK();
						fvehicleinventryPK.setInvid(tempFresvehinv.getId().getInvid());
						fvehicleinventryPK.setRegno(tempFresvehinv.getId().getRegno());
						fvehicleinventry.setId(fvehicleinventryPK);
						em.persist(fvehicleinventry);//UPDATE THE VEHICLE STATUS
						//em.flush();
					}
					//END
					
					
					
					//set vehicle status damage update
					//delete existing one in vehicle
					
					//UPDATE=DELETE+INSERT APPLY FOR FRES-VEHICLE-DAMAGE
					//START					
					em.createNamedQuery("FvehicledamagedeleteByRegNo").setParameter("regno",fresvehicle.getId().getRegno() ).executeUpdate();
					em.createNamedQuery("FresvehicledamagedeleteByRegNoIO").setParameter("regno",fresvehicle.getId().getRegno() ).setParameter("resno",fresvehicle.getId().getResno() ).setParameter("ioflag",0).executeUpdate();

					em.flush();
					List<Fvehicledamage> tempListFvehicledamage= lstCheckInFvehicledamage;
					for(Fvehicledamage tempFvehicledamage : tempListFvehicledamage)
					{
						tempFvehicledamage.setRegno(fresvehicle.getId().getRegno());
						//tempFvehicledamage.setAdduser(lsAddUser);
						
						if (tempFvehicledamage.getUuid()==null || tempFvehicledamage.getUuid().isEmpty()){
							tempFvehicledamage.setUuid(UUID.randomUUID().toString());
							tempFvehicledamage.setAdduser(lsAddUser);
						}
						
						em.persist(tempFvehicledamage);////UPDATE THE VEHICLE STATUS
						em.flush();
						//create new Fresvehicledamage object from Fvehicledamage
						Fresvehicledamage fresvehicledamage=new Fresvehicledamage();
						fresvehicledamage.setDamagetype(tempFvehicledamage.getDamagetype());
						fresvehicledamage.setIoflag(0);
						fresvehicledamage.setRegno(tempFvehicledamage.getRegno());
						fresvehicledamage.setResno(refNo);
						fresvehicledamage.setSeq(tempFvehicledamage.getSeq());
						fresvehicledamage.setXvalue(tempFvehicledamage.getXvalue());
						fresvehicledamage.setYvalue(tempFvehicledamage.getYvalue());
						if (fresvehicledamage.getUuid()==null || fresvehicledamage.getUuid().isEmpty()){
							fresvehicledamage.setUuid(UUID.randomUUID().toString());
							fresvehicledamage.setAdduser(lsAddUser);
						}
						em.persist(fresvehicledamage);
						//em.flush();
					}
					//END
					
					
					//UPDATE VEHICLE CURRENT STATUS
					Fvehicle fvehicle=em.find(Fvehicle.class, fresvehicle.getId().getRegno());
					fvehicle.setFuellevel(freservation.getCifuellevel());
					fvehicle.setCurmileage(freservation.getCimileage());
					if(freservation.getCiLocation()!=null)
					fvehicle.setLocationid(freservation.getCiLocation());
					em.merge(fvehicle);////UPDATE THE VEHICLE STATUS
					//em.flush();
					
					
					try {
						Boolean flag = fileUploadSRV.uploadFileToServer(
								fileName, freservation.getCheckindata(),
								targetFolder);
						// System.out.println("is uploaded:"+flag);
					} catch (Exception e) {

						// System.out.println("ererer"+e.getMessage());
					}
					
				}
				else break;
			}
		}
		//end checkin transaction
		
		
		
		em.flush();
		return refNo;
		
	}
	
	
	
	/***
	 * 
	 * 
	 * 
	 * This method call when CONFIRM-->UPGRADE
	 * @param resno :resevation number that going to modify
	 * @param lstFresvehicle :list of revered vehicle
	 * @param fresvehiclerate :vehicle rates
	 * @return reservation number
	 * @author SANKA
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=false,rollbackFor=RuntimeException.class,propagation=Propagation.REQUIRED)
	@Override
	public synchronized String changeVehicle(
			String resno,
			List<Fresvehicle> lstFresvehicle,
			Fresvehiclerate fresvehiclerate)
	{
		em.flush();
		String lsAddUser =userInfoSRV.getUser();//GET USER
		String lsAddMachine =userInfoSRV.getMachineName();//GET MACHINE NAME
		Freservation freservation=em.find(Freservation.class, resno);//RETRIVE ORIGINAL OLD RESERVATION FROM DB
		Calendar today=Calendar.getInstance();//GET TODAY
		freservation.setAdddate(today);		
		//String hireStatus=freservation.getCohirestsid();//KEEP ORIGINAL RESERVATION STATUS IN TEMPORY VARIABLE
		
		//COPY VARIABLES FOR EXTENTIONS
		Freservation freservationCopy=null;
		try{
			freservationCopy=(Freservation)freservation.clone();//GET A COPY OF RESERVATION.
		}
		catch(CloneNotSupportedException e)
		{
			throw new RuntimeException("Error in Coping values");
		}
		freservationCopy.setRatetype(fresvehiclerate.getRatetype());
		freservationCopy.setAdddate(today);	
		freservation.setCohirestsid("VC");//SET HIRE STATUS INTO VEHICLE CHANGED
		String refNo=fcompanysettingDAO.getRefNo("RES", freservationCopy.getAdddate().get(Calendar.YEAR), freservationCopy.getAdddate().get(Calendar.MONTH)+1);
		freservationCopy.setResno(refNo);
		freservationCopy.setAdduser(lsAddUser);
		freservationCopy.setAddmach(lsAddMachine);
		freservationCopy.setUuid(UUID.randomUUID().toString());
		em.persist(freservationCopy);
		em.flush();
		
		//SET DEPOSIT & ADVANCE INTO ZERO
		freservation.setAdvance(new BigDecimal(0));
		freservation.setAdvancebal(new BigDecimal(0));
		freservation.setDeposit(new BigDecimal(0));
		freservation.setDepositbal(new BigDecimal(0));
		freservation.setRemarks("VEHICLE CHANGED AND AGGREMENT TRANSFERED TO REFERENCE NUMBER :"+refNo);
		updateModifiedUser(freservation);
		em.merge(freservation);//UPDATE THE EXISTING RESERVATION
		
		for(Fresvehicle fresvehicle : lstFresvehicle)
		{
			fresvehicle.getId().setResno(refNo);
			fresvehicle.setAdduser(lsAddUser);
			fresvehicle.setUuid(UUID.randomUUID().toString());
			em.persist(fresvehicle);
			//em.flush();
		}
		em.flush();
		
		fresvehiclerate.setResno(refNo);
		fresvehiclerate.setAdduser(lsAddUser);
		fresvehiclerate.setUuid(UUID.randomUUID().toString());
		em.persist(fresvehiclerate);
		em.flush();
		
		
		
		//FOLLOWING CODE DO COPY AND DUPPLICATE THE OTHERSERVICE,ACCESSORIES AND ITS RATES
//STAERT		
		List<Fresaccs> lstfresaccs= em.createNamedQuery("FresaccsListByresNo").setParameter("resno", resno).getResultList();
		List<Fresothsrv> lstfresothsrv= em.createNamedQuery("FresothsrvListAllByResNo").setParameter("resno", resno).getResultList();
		List<Fresdriver> lstfresdriver= em.createNamedQuery("FresdriverListByResno").setParameter("resno", resno).getResultList();
		List<Fresaddcharge> lstfresaddcharge=em.createNamedQuery("FresaddchargeListByresno").setParameter("resno", resno).getResultList();	
		Fresdriverrate fresdriverrate= (Fresdriverrate) em.createNamedQuery("FresdriverrateByResno").setParameter("resno", resno).getSingleResult();

		//START:Fresaccs & Fresaccrate INSERT AS NEW RECORDS
		for(Fresaccs fresaccs : lstfresaccs)
		{
			Fresaccs fresaccsCopy=null;
			try{
				fresaccsCopy=(Fresaccs)fresaccs.clone();//GET A COPY OF RESERVATION.
			}
			catch(CloneNotSupportedException e)
			{
				throw new RuntimeException("Error in Coping values");
			}
			FresaccsPK fresaccsPKCopy= null;
			 try {
				fresaccsPKCopy= (FresaccsPK)(fresaccs.getId()).clone();
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
			try{
			fresaccsPKCopy.setResno(refNo);
			}
			catch(Exception eee)
			{
				System.out.println("ERRRROR:"+eee);
				eee.printStackTrace();
			}
			fresaccsCopy.setId(fresaccsPKCopy);
			fresaccsCopy.setAdduser(lsAddUser);
			fresaccsCopy.setUuid(UUID.randomUUID().toString());
			Fresaccrate fresaccrate=fresaccsCopy.getFresaccrate();	
			Fresaccrate fresaccrateCopy=null;
			FresaccratePK fresaccratePKCopy=null;
			//GET A COPY OF RATE
			try{
				fresaccrateCopy=(Fresaccrate)fresaccrate.clone();//GET A COPY OF RESERVATION.
				fresaccratePKCopy=(FresaccratePK)fresaccrateCopy.getId().clone();//GET A COPY OF RESERVATION.
			}
			catch(CloneNotSupportedException e)
			{
				throw new RuntimeException("Error in Coping values");
			}
			//NOW INSERT RES ACCESSORY
			em.persist(fresaccsCopy);
			em.flush();
			fresaccratePKCopy.setResno(refNo);
			fresaccrateCopy.setId(fresaccratePKCopy);			
			fresaccrateCopy.setAdduser(lsAddUser);
			fresaccrateCopy.setUuid(UUID.randomUUID().toString());
				
			em.persist(fresaccrateCopy);
			em.flush();
		}
		//END:Fresaccs & Fresaccrate INSERT AS NEW RECORDS
		
		
		
		//START:Fresothsrv & Fresothersrvrate INSERT AS NEW RECORDS
		for(Fresothsrv fresothsrv :lstfresothsrv)
		{
			Fresothsrv fresothsrvCopy=null;
			FresothsrvPK fresothsrvPKCopy=null;
			try{
				fresothsrvCopy=(Fresothsrv)fresothsrv.clone();//GET A COPY OF RESERVATION.
				fresothsrvPKCopy=(FresothsrvPK)fresothsrv.getId().clone();//GET A COPY OF RESERVATION.
			}
			catch(CloneNotSupportedException e)
			{
				throw new RuntimeException("Error in Coping values");
			}
			fresothsrvPKCopy.setResno(refNo);
			fresothsrvCopy.setId(fresothsrvPKCopy);
			fresothsrvCopy.setAdduser(lsAddUser);
			fresothsrvCopy.setUuid(UUID.randomUUID().toString());
			Fresothersrvrate fresothersrvrate=fresothsrvCopy.getFresothersrvrate();	
			em.persist(fresothsrvCopy);
			em.flush();
			
			
			Fresothersrvrate fresothersrvrateCopy=null;
			FresothersrvratePK fresothersrvratePKCopy=null;
			try{
				fresothersrvrateCopy=(Fresothersrvrate)fresothersrvrate.clone();//GET A COPY OF RESERVATION.
				fresothersrvratePKCopy=(FresothersrvratePK)(fresothersrvrate.getId()).clone();//GET A COPY OF RESERVATION.
			}
			catch(CloneNotSupportedException e)
			{
				throw new RuntimeException("Error in Coping values");
			}
			fresothersrvratePKCopy.setResno(refNo);
			fresothersrvrateCopy.setId(fresothersrvratePKCopy);
			fresothersrvrateCopy.setAdduser(lsAddUser);
			fresothersrvrateCopy.setUuid(UUID.randomUUID().toString());
				
			em.persist(fresothersrvrateCopy);
			em.flush();
			
		}
		//END:Fresothsrv & Fresothersrvrate INSERT AS NEW RECORDS
		
		


		//START:Fresaddcharge  INSERT AS NEW RECORDS		
		for(Fresaddcharge fresaddcharge:lstfresaddcharge)
		{
			Fresaddcharge fresaddchargeCopy=null;
			FresaddchargePK fresaddchargePKCopy=null;
			try{
				fresaddchargeCopy=(Fresaddcharge)fresaddcharge.clone();//GET A COPY OF RESERVATION.
				fresaddchargePKCopy=(FresaddchargePK)fresaddcharge.getId().clone();//GET A COPY OF RESERVATION.
			}
			catch(CloneNotSupportedException e)
			{
				throw new RuntimeException("Error in Coping values");
			}
			fresaddchargePKCopy.setResno(refNo);	
			fresaddchargeCopy.setId(fresaddchargePKCopy);			
			fresaddchargeCopy.setAdduser(lsAddUser);
			fresaddchargeCopy.setUuid(UUID.randomUUID().toString());
				
			em.persist(fresaddchargeCopy);		
			em.flush();
		}
		//END:Fresaddcharge  INSERT AS NEW RECORDS	
		
		
		

		//START:Fresdriver & fresdriverrate INSERT AS NEW RECORDS
		for(Fresdriver fresdriver:lstfresdriver)
		{
			Fresdriver fresdriverCopy=null;
			FresdriverPK fresdriverPKCopy=null;
			try{
				fresdriverCopy=(Fresdriver)fresdriver.clone();//GET A COPY OF RESERVATION.
				fresdriverPKCopy=(FresdriverPK)fresdriver.getId().clone();//GET A COPY OF RESERVATION.
			}
			catch(CloneNotSupportedException e)
			{
				throw new RuntimeException("Error in Coping values");
			}
			fresdriverPKCopy.setResno(refNo);
			fresdriverCopy.setId(fresdriverPKCopy);
			fresdriverCopy.setAdduser(lsAddUser);
			fresdriverCopy.setUuid(UUID.randomUUID().toString());
			
			em.persist(fresdriverCopy);
			em.flush();
		}
		//END:Fresdriver & fresdriverrate INSERT AS NEW RECORDS
		
		
		
		//START:INSERT DRIVER RATES
		Fresdriverrate fresdriverrateCopy=null;
		FresdriverratePK fresdriverratePKCopy=null;
		try {
			fresdriverrateCopy=(Fresdriverrate)fresdriverrate.clone();
			fresdriverratePKCopy=(FresdriverratePK)fresdriverrate.getId().clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("Error in Coping values");
		}
		fresdriverratePKCopy.setResno(refNo);
		fresdriverrateCopy.setId(fresdriverratePKCopy);
		fresdriverrateCopy.setAdduser(lsAddUser);
		fresdriverrateCopy.setUuid(UUID.randomUUID().toString());
		em.persist(fresdriverrateCopy);
		em.flush();	
		//END:INSERT DRIVER RATES
//END		
		
		em.createNamedQuery("FarfdtfChangeResno").setParameter(1,resno).setParameter(2,refNo).executeUpdate();
		em.flush();
		return refNo;
	}
	
	
	
	
	
	
	
	/***
	 * 
	 * 
	 * 
	 * This method call when CHECKOUT-->UPGRADE
	 * @param resno :resevation number that going to modify
	 * @param lstFresvehicle :list of revered vehicle
	 * @param fresvehiclerate :vehicle rates
	 * @return reservation number
	 * @author SANKA
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=false,rollbackFor=RuntimeException.class,propagation=Propagation.REQUIRED)
	@Override	
	public String changeCheckOutVehicle(
			Freservation freservation,
			Freshed freshed, 
			List<Fresvehicle> lstfresvehicle,
			List<Fresaccs> lstfresaccs, 
			List<Fresdriver> lstfresdriver, 
			List<Fresothsrv> lstfresothsrv,
			Fresvehiclerate fresvehiclerate, 
			List<Fresaccrate> lstfresaccrate,
			Fresdriverrate fresdriverrate, 
			List<Fresothersrvrate> lstfresothersrvrate,
			List<Fresaddcharge> lstfresaddcharge,
			List<Fresclientdriver> lstfresclientdriver,
			List<Fvehicledamage> lstCheckInFvehicledamage,
			List<Fresvehinv> lstCheckInFresvehinv,
			
			Freservation freservationCopy,
			Freshed freshedCopy, 
			List<Fresvehicle> lstfresvehicleCopy,
			List<Fresaccs> lstfresaccsCopy, 
			List<Fresdriver> lstfresdriverCopy, 
			List<Fresothsrv> lstfresothsrvCopy,
			Fresvehiclerate fresvehiclerateCopy, 
			List<Fresaccrate> lstfresaccrateCopy,
			Fresdriverrate fresdriverrateCopy, 
			List<Fresothersrvrate> lstfresothersrvrateCopy,
			List<Fresaddcharge> lstfresaddchargeCopy,
			List<Fresclientdriver> lstfresclientdriverCopy,
			List<Fvehicledamage> lstCheckInFvehicledamageCopy,
			List<Fresvehinv> lstCheckInFresvehinvCopy
			
			
	)
	{
		em.flush();
		String oldresno=freservationCopy.getResno();
		Boolean hasChangeVehicle=true;
		if(lstfresvehicle.get(0).getId().getRegno().equalsIgnoreCase(lstfresvehicleCopy.get(0).getId().getRegno()))
		{
			hasChangeVehicle=false;
		}
		
		changeStatus(freservationCopy, freshedCopy, lstfresvehicleCopy,
				lstfresaccsCopy, lstfresdriverCopy, lstfresothsrvCopy,
				fresvehiclerateCopy, lstfresaccrateCopy, fresdriverrateCopy,
				lstfresothersrvrateCopy, lstfresaddchargeCopy,lstfresclientdriverCopy,
				lstCheckInFvehicledamageCopy,lstCheckInFresvehinvCopy);
		


		String resnoandagrno=create(freservation, freshed, lstfresvehicle, lstfresaccs,
				lstfresdriver, lstfresothsrv, fresvehiclerate, lstfresaccrate,
				fresdriverrate, lstfresothersrvrate, lstfresaddcharge,lstfresclientdriver,!hasChangeVehicle
				);
		String resno=resnoandagrno.substring(0, 13);
		//following code set child reference to parent
		Query query=em.createNativeQuery("UPDATE freservation SET  childref= ?1,parentref=?2 WHERE (resno = ?2)");
		query.setParameter(1,resno);
		query.setParameter(2,oldresno);
		query.executeUpdate();
//		Freservation temp =em.find(Freservation.class, oldresno);
//		temp.setChildref(resno);
//		em.merge(temp);

		return resno;
	}
	
	
	@Transactional
	@Override
	public List<FrentagreementRPT> listRptRentAgreement(String resNo,String lsJustServerName) {
		/**
		 * List Reservation No's relation data
		 */
		//http://localhost:8400
		//http://192.168.0.8400/malkey_server/
		System.out.println("1 lsJustServerName :"+lsJustServerName+" resNo "+resNo);
//		String lsURL = lsJustServerName/*request.getRequestURL().toString()*/;	//http://localhost:8400	
		//String lsURL = "http://220.247.244.141:8400/malkey_server/reports/rentagreement.jsp";/*request.getRequestURL().toString()*/;	//http://localhost:8400	
		String[] aUrl = lsJustServerName.split("/");
		for(int i=0;i<aUrl.length;i++)
		{
			System.out.println(resNo+"aUrl "+i+" "+aUrl[i]);
		}
		lsJustServerName = aUrl[0]+"//"+aUrl[2];
		System.out.println("2 lsJustServerName :"+lsJustServerName+" resNo "+resNo);
		
		em.flush();
		Query query = em.createNamedQuery("FrentagreementRPT.NativeQuery1").setParameter("1", resNo);
		List<FrentagreementRPT> lst = query.getResultList();
		//Query queryTax=em.createNamedQuery("FreservationTaxDetail").setParameter("resno", resNo);
	//	List<Ftaxdet> lstTaxDet = queryTax.getResultList();
		if(lst.get(0).getDebppno()==null){
			lst.get(0).setDebppno(" ");
			}
		if(lst.get(0).getDebnicno()==null){
			lst.get(0).setDebnicno(" ");
		}
		if (lst.size() > 1){
			/**
			 * The Reservation Header record had got duplicated records. This tends to happen when a child table is linked another child table in the same query.
			 * If the report happens to consists of detail band, then the info printed in the detail band would be repeated to the number records in the main data source.
			 * In order avoid printing repeated values in the detail band, re-construct a new return type which would consist of only ONE record
			 */
			
			FrentagreementRPT parentRecord = lst.get(0);		// Top most record
			lst.clear();										// Remove all the existing records
			lst.add(parentRecord);								// Update with the top most record return from the server
			
		}
		
		
		if (lst.size()>0){
			
			if(lst.get(0).getRemarks_customer() !=null)
			{
				String newline = System.getProperty("line.separator");
				lst.get(0).setDebadd (lst.get(0).getDebadd()+newline+"[By Whom Ordered :"+lst.get(0).getRemarks_customer()+"]");
			}
			
			
			System.out.println("URL ="+lst.get(0).getCodamage());
			//http://220.247.244.141:8400/resource/image/damageinfo/RS-1506-00001-CO.jpg
			//lst.get(0).setCodamage("http://220.247.244.141:8400/resource/image/damageinfo/RS-1506-00001-CO.jpg");
			
 			if (lst.get(0).getCodamage()!=null){
				lst.get(0).setCodamage(lsJustServerName + lst.get(0).getCodamage());
				System.out.println(" resNo "+resNo+" TURL ="+lst.get(0).getCodamage());
 			}
			if (lst.get(0).getCidamage()!=null){
				lst.get(0).setCidamage(lsJustServerName + lst.get(0).getCidamage());
				System.out.println(" resNo "+resNo+" TURL ="+lst.get(0).getCodamage());
			}
			try{
				if(lst.get(0).getItinerary()!=null)
				{
					lst.get(0).setItinerary(lst.get(0).getItinerary().replaceAll("\n"," "));
				}
				if(lst.get(0).getRemarks()!=null)
				{
					lst.get(0).setRemarks(lst.get(0).getRemarks().replaceAll("\n"," "));
				}
				if(lst.get(0).getReturnaddress()!=null)
				{
					lst.get(0).setReturnaddress(lst.get(0).getReturnaddress().replaceAll("\n"," "));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		
		return lst;
	}

	
	@Transactional
	@Override
	public List<FrentagrvehinvRPT> listRptRentVehiclesInventry(String resNo) {
		/**
		 * List the inventory items of the Vehicle which is Reservation No and create a new data structure to be used in the report
		 */
		em.flush();
		Query query = em.createNamedQuery("FrentvehinvRPT.NativeQuery1").setParameter("1", resNo);
		List<FrentvehinvRPT> lstVehInvList = query.getResultList();
		
		/**
		 * List the accessories (such as GPS Unit/ Baby Seat/ Cool Box) rented along with the vehicle 
		 */
		query = em.createNamedQuery("FrentvehinvRPT.NativeQuery2").setParameter("1", resNo);
		List<FrentvehinvRPT> lstVehAccList = query.getResultList();
		
		if (lstVehAccList.size()>0){
			// Accessories are rented along with the vehicle
			for (FrentvehinvRPT orderedAcc:lstVehAccList) {
				lstVehInvList.add(orderedAcc);
			}
		}
		
		List<FrentagrvehinvRPT> frentagrvehinvRPT = new ArrayList<FrentagrvehinvRPT>();
		/** 
		 * Loop through the list of records and build a structure with which it would possible to 
		 * print 6 lines of Vehicle Inventory Description records in the Crystal Report.	
		 */
		for (int i = 0; i < lstVehInvList.size(); i++) {
			int lnLastDetailColumnNoUpdate = 0;
						
			FrentagrvehinvRPT newAgrVehInvRec = new FrentagrvehinvRPT();

			// 1st Column
			if (lnLastDetailColumnNoUpdate==0){
				FrentvehinvRPT rentvehinv = lstVehInvList.get(i);
				newAgrVehInvRec.setResno(rentvehinv.getResno());
				newAgrVehInvRec.setDetail1(rentvehinv.getDescription());
				newAgrVehInvRec.setAccessory1(rentvehinv.getAccessory());
				lnLastDetailColumnNoUpdate += 1;
				i += 1;
			}

			// 2nd Column
			if (lnLastDetailColumnNoUpdate==1){
				if (i < lstVehInvList.size()){					
					FrentvehinvRPT rentvehinv = lstVehInvList.get(i);
					newAgrVehInvRec.setDetail2(rentvehinv.getDescription());
					newAgrVehInvRec.setAccessory2(rentvehinv.getAccessory());
				}
				else
					newAgrVehInvRec.setDetail2("");
				
				lnLastDetailColumnNoUpdate += 1;
				i += 1;
			}

			// 3rd Column
			if (lnLastDetailColumnNoUpdate==2){
				if (i < lstVehInvList.size()){					
					FrentvehinvRPT rentvehinv = lstVehInvList.get(i);
					newAgrVehInvRec.setDetail3(rentvehinv.getDescription());
					newAgrVehInvRec.setAccessory3(rentvehinv.getAccessory());
				}
				else 
					newAgrVehInvRec.setDetail3("");
				
				lnLastDetailColumnNoUpdate += 1;
				i += 1;
			}
			// 4rd Column
			if (lnLastDetailColumnNoUpdate==3){
				if (i < lstVehInvList.size()){					
					FrentvehinvRPT rentvehinv = lstVehInvList.get(i);
					newAgrVehInvRec.setDetail4(rentvehinv.getDescription());
					newAgrVehInvRec.setAccessory4(rentvehinv.getAccessory());
				}
				else 
					newAgrVehInvRec.setDetail4("");
				
				lnLastDetailColumnNoUpdate += 1;
				i += 1;
			}
			// 5rd Column
			if (lnLastDetailColumnNoUpdate==4){
				if (i < lstVehInvList.size()){					
					FrentvehinvRPT rentvehinv = lstVehInvList.get(i);
					newAgrVehInvRec.setDetail5(rentvehinv.getDescription());
					newAgrVehInvRec.setAccessory5(rentvehinv.getAccessory());
				}
				else 
					newAgrVehInvRec.setDetail5("");
				
				lnLastDetailColumnNoUpdate += 1;
				i += 1;
			}
		
		// 6rd Column
		if (lnLastDetailColumnNoUpdate==5){
			if (i < lstVehInvList.size()){					
				FrentvehinvRPT rentvehinv = lstVehInvList.get(i);
				newAgrVehInvRec.setDetail6(rentvehinv.getDescription());
				newAgrVehInvRec.setAccessory6(rentvehinv.getAccessory());
			}
			else 
				newAgrVehInvRec.setDetail6("");
			
			lnLastDetailColumnNoUpdate += 1;
			i += 1;
		}
	
	// 7rd Column
	if (lnLastDetailColumnNoUpdate==6){
		if (i < lstVehInvList.size()){					
			FrentvehinvRPT rentvehinv = lstVehInvList.get(i);
			newAgrVehInvRec.setDetail7(rentvehinv.getDescription());
			newAgrVehInvRec.setAccessory7(rentvehinv.getAccessory());
		}
		else 
			newAgrVehInvRec.setDetail7("");
		
		lnLastDetailColumnNoUpdate += 1;
		i += 1;
	}
	// 8rd Column
	if (lnLastDetailColumnNoUpdate==7){
		if (i < lstVehInvList.size()){					
			FrentvehinvRPT rentvehinv = lstVehInvList.get(i);
			newAgrVehInvRec.setDetail8(rentvehinv.getDescription());
			newAgrVehInvRec.setAccessory8(rentvehinv.getAccessory());
		}
		else 
			newAgrVehInvRec.setDetail8("");
		
		lnLastDetailColumnNoUpdate += 1;
		i += 1;
	}

			// 9th Column
			if (lnLastDetailColumnNoUpdate==8){
				if (i < lstVehInvList.size()){
					FrentvehinvRPT rentvehinv = lstVehInvList.get(i);
					newAgrVehInvRec.setDetail9(rentvehinv.getDescription());
					newAgrVehInvRec.setAccessory9(rentvehinv.getAccessory());
				}
				else
					newAgrVehInvRec.setDetail9("");
				
				// Do not increment within this "if" condition, the "for" loop would do the incrementing.
			}
				
			frentagrvehinvRPT.add(newAgrVehInvRec);
		}	
		
		return frentagrvehinvRPT;
	}

	@Transactional
	@Override
	public List<Freservationdiaryrpt> getResDiaryData(String dateFrom,String dateTo,String hireTypeId,String cohireStsId) {
		try {
			em.flush();
			String  query="SELECT r.dout,r.din,r.noofday,r.resno,r.agrno,";
					query += " d.debname,d.debadd,r.itinerary,d.tel,";
					query += " (SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1) AS regno,";
					query += " (SELECT description FROM fvehiclemake WHERE vehimakeid=(SELECT vehimakeid FROM fvehicle WHERE regno=(SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1))) AS make,";
					query += " (SELECT description FROM fvehiclemodel WHERE vehimodelid=(SELECT vehimodelid FROM fvehicle WHERE regno=(SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1))) AS model,";
					//query += " (SELECT name FROM femployee WHERE empid=(SELECT empid FROM fresdriver WHERE resno=r.resno AND priority=1)) AS driver,";
					query += " (SELECT STUFF((SELECT ', ' + RTRIM(name)  +' - ' + RTRIM(mobilephone1) FROM femployee WHERE empid IN (SELECT empid FROM fresdriver WHERE resno=r.resno AND priority=1) FOR XML PATH('')),1, 1, '')) AS driver,";
					query += " r.gotime,";
					//query += " (SELECT description FROM fhiretype WHERE hiretypeid=r.hiretypeid) AS hiretypeid";
					query += " r.hiretypeid,r.cohirestsid";
					//query += " (SELECT STUFF((SELECT ', ' + RTRIM(mobilephone1) FROM femployee WHERE empid IN (SELECT empid FROM fresdriver WHERE resno=r.resno AND priority=1) FOR XML PATH('')),1, 1, '')) AS driverTP";
					query += " FROM freservation AS r,fdebtor AS d";
					query += " WHERE r.debcode=d.debcode ";
					//query += " r.dout>='"+dateFrom+"' AND r.dout<='"+dateTo+"'"; 
					if(dateFrom.trim().length()>0 && dateTo.trim().length()>0){
						if(cohireStsId.trim().equals("'CHECKOUT'")==true){
							query += " AND r.din>='"+dateFrom+"' AND r.din<='"+dateTo+"'"; 
						}else{
							query += " AND r.dout>='"+dateFrom+"' AND r.dout<='"+dateTo+"'"; 
						}
					}
					if(hireTypeId.trim().length()>2){//ignore single quotes
						query+= " AND r.hiretypeid IN(" + hireTypeId + ")";
					}
					if(cohireStsId.trim().length()>0){
						query += " AND r.cohirestsid IN(" + cohireStsId + ")";
					}
					
					if(cohireStsId.trim().equals("'CHECKOUT'")==true){
						query += " ORDER BY r.din,r.resno";	
					}else{
						query += " ORDER BY r.dout,r.gotime";	
					}
					
					System.out.println("query: " + query);
					
					Query Q = em.createNativeQuery(query);
					List<Object[]> l1 = Q.getResultList();
					List<Freservationdiaryrpt> fresdiaryrecs=new ArrayCollection();
					
					Timestamp dout;
					Timestamp din;
					int noofday;
					String resno;
					String debname;
					String debadd;
					String itinerary;
					String tel;
					String regno;
					String agrno;
					String make;
					String model;
					String driver;
					String gotime;
					String hiretype;
					String cohirestsid;
//					String driverTP;
					
					for(Object[] r1 : l1)
					{
						dout=(Timestamp)r1[0];
						din=(Timestamp)r1[1];
						noofday=Integer.parseInt(r1[2].toString());
						resno=(String)r1[3];
						agrno=(String)r1[4];
						debname=(String)r1[5];
						debadd=(String)r1[6];
						String newline = System.getProperty("line.separator");
						//System.out.println("line seprator start: " + newline + " : end");
						//System.out.println("before debadd: " + debadd);
						debadd=debadd.replace("\\r",newline);
						//System.out.println("after debadd: " + debadd);
						itinerary=(String)r1[7];
						tel=(String)r1[8];
						regno=(String)r1[9];
						make=(String)r1[10];
						model=(String)r1[11];
						driver=(String)r1[12];
						gotime=(String)r1[13];
						hiretype=(String)r1[14];
						cohirestsid=(String)r1[15];
//						driverTP=(String)r1[16];
//						if(driver!=null && driver.trim().length()>0)
//							driver="";
//						if(driverTP!=null && driverTP.trim().length()>0)
//							driverTP="";
						if(hiretype.trim().equalsIgnoreCase("LONGTERM"))
							hiretype="LT";
						else if(hiretype.trim().equalsIgnoreCase("WEDDING"))
							hiretype="WDD";
						
						String[] ar=gotime.trim().split(":");
						String dTime="AM";
						if(ar[0].trim().equals("00")==true || ar[0].trim().equals("0")==true){
							gotime="12";
						}
						else if(Integer.parseInt(ar[0].trim())>12){
							gotime=Integer.toString(Integer.parseInt(ar[0].trim())-12);
							dTime="PM";
						}
						else if((Integer.parseInt(ar[0].trim())==12)  ){
							gotime="12";
							dTime="PM";
							if(Integer.parseInt(ar[1].trim())==0){
							dTime=newline+"Noon";
							}
						}
						else{
							gotime=ar[0].trim();
						}
						gotime+=":"+ar[1]+" "+dTime;
						
						Freservationdiaryrpt obj=new Freservationdiaryrpt();
						obj.setDout(dout);
						obj.setDin(din);
						obj.setNoofday(noofday);
						obj.setResno(resno);
						obj.setAgrno(agrno+"\n"+resno);//modified by sanka{add new field for report}
						obj.setDebname(debname);
						obj.setDebadd(debadd+"\n\n\n"+newline+newline);
						obj.setItinerary(itinerary+"\n\n\n"+newline+newline);
						obj.setTel(tel);
						obj.setRegno(regno);
						obj.setMake(make);
						obj.setModel(model);
						obj.setDriver((driver!=null?driver:" ") );
						System.out.println("driver "+driver);
//						System.out.println("driverTP "+driverTP);
						obj.setGotime(gotime);
						obj.setHiretype(hiretype);
						obj.setHirestatus(cohirestsid);
						fresdiaryrecs.add(obj);
					}
					return fresdiaryrecs;
		} catch (Exception e) {
			System.out.println("FreservationDaoImpl getResDiaryData: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Transactional
	@Override
	public List<Freservationdiaryrpt> getResDiaryDataHistory(String dateFrom,String dateTo,String hireTypeId/*,String cohireStsId*/) {
		try {
			em.flush();
			String  query="SELECT r.dout,r.din,r.noofday,r.resno,r.agrno,";
			query += " d.debname,d.debadd,r.itinerary,d.tel,";
			query += " (SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1) AS regno,";
			query += " (SELECT description FROM fvehiclemake WHERE vehimakeid=(SELECT vehimakeid FROM fvehicle WHERE regno=(SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1))) AS make,";
			query += " (SELECT description FROM fvehiclemodel WHERE vehimodelid=(SELECT vehimodelid FROM fvehicle WHERE regno=(SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1))) AS model,";
			query += " (SELECT STUFF((SELECT ', ' + RTRIM(name) FROM femployee WHERE empid IN (SELECT empid FROM fresdriver WHERE resno=r.resno AND priority=1) FOR XML PATH('')),1, 1, '')) AS driver,";
			query += " r.gotime,";
			query += " r.hiretypeid,r.cohirestsid,";
			query += " (SELECT STUFF((SELECT ', ' + RTRIM(mobilephone1) FROM femployee WHERE empid IN (SELECT empid FROM fresdriver WHERE resno=r.resno AND priority=1) FOR XML PATH('')),1, 1, '')) AS driverTP";
			query += " FROM freservation AS r,fdebtor AS d";
			query += " WHERE r.debcode=d.debcode ";
			
			if(dateFrom.trim().length()>0 && dateTo.trim().length()>0){
				query += " AND r.din>='"+dateFrom+"' AND r.din<='"+dateTo+"'"; 
				query += " AND r.dout>='"+dateFrom+"' AND r.dout<='"+dateTo+"'"; 
			}
			if(hireTypeId.trim().length()>2){//ignore single quotes
				query+= " AND r.hiretypeid IN(" + hireTypeId + ")";
			}

			query += " ORDER BY r.dout,r.gotime";	
			
			System.out.println("query: " + query);
			
			Query Q = em.createNativeQuery(query);
			List<Object[]> l1 = Q.getResultList();
			List<Freservationdiaryrpt> fresdiaryrecs=new ArrayCollection();
			
			Timestamp dout;
			Timestamp din;
			int noofday;
			String resno;
			String debname;
			String debadd;
			String itinerary;
			String tel;
			String regno;
			String agrno;
			String make;
			String model;
			String driver;
			String gotime;
			String hiretype;
			String cohirestsid;
			String driverTP;
			
			for(Object[] r1 : l1)
			{
				dout=(Timestamp)r1[0];
				din=(Timestamp)r1[1];
				noofday=Integer.parseInt(r1[2].toString());
				resno=(String)r1[3];
				agrno=(String)r1[4];
				debname=(String)r1[5];
				debadd=(String)r1[6];
				String newline = System.getProperty("line.separator");
				//System.out.println("line seprator start: " + newline + " : end");
				//System.out.println("before debadd: " + debadd);
				debadd=debadd.replace("\\r",newline);
				//System.out.println("after debadd: " + debadd);
				itinerary=(String)r1[7];
				tel=(String)r1[8];
				regno=(String)r1[9];
				make=(String)r1[10];
				model=(String)r1[11];
				driver=(String)r1[12];
				gotime=(String)r1[13];
				hiretype=(String)r1[14];
				cohirestsid=(String)r1[15];
				driverTP=(String)r1[16];
				
				if(driver!=null && driver.trim().length()>0)
					driver="";
				if(driverTP!=null && driverTP.trim().length()>0)
					driverTP="";
				
				if(hiretype.trim().equalsIgnoreCase("LONGTERM"))
					hiretype="LT";
				else if(hiretype.trim().equalsIgnoreCase("WEDDING"))
					hiretype="WDD";
				
				String[] ar=gotime.trim().split(":");
				String dTime="AM";
				if(ar[0].trim().equals("00")==true || ar[0].trim().equals("0")==true){
					gotime="12";
				}
				else if(Integer.parseInt(ar[0].trim())>12){
					gotime=Integer.toString(Integer.parseInt(ar[0].trim())-12);
					dTime="PM";
				}
				else if((Integer.parseInt(ar[0].trim())==12)  ){
					gotime="12";
					dTime="PM";
					if(Integer.parseInt(ar[1].trim())==0){
						dTime=newline+"Noon";
					}
				}
				else{
					gotime=ar[0].trim();
				}
				gotime+=":"+ar[1]+" "+dTime;
				
				Freservationdiaryrpt obj=new Freservationdiaryrpt();
				obj.setDout(dout);
				obj.setDin(din);
				obj.setNoofday(noofday);
				obj.setResno(resno);
				obj.setAgrno(agrno+"\n"+resno);//modified by sanka{add new field for report}
				obj.setDebname(debname);
				obj.setDebadd(debadd+"\n\n\n"+newline+newline);
				obj.setItinerary(itinerary+"\n\n\n"+newline+newline);
				obj.setTel(tel);
				obj.setRegno(regno);
				obj.setMake(make);
				obj.setModel(model);
				obj.setDriver(driver+driverTP);
				obj.setGotime(gotime);
				obj.setHiretype(hiretype);
				obj.setHirestatus(cohirestsid);
				fresdiaryrecs.add(obj);
			}
			return fresdiaryrecs;
		} catch (Exception e) {
			System.out.println("FreservationDaoImpl getResDiaryData: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public List<Freservationdiaryrpt> getRentedVehicleDet(String dateFrom, String dateTo) {
		try {
			em.flush();
			String query="SELECT";
			query += " r.dout,";
			query += " (SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1) AS [regno],";
			query += " (r.cimileage-r.comileage) AS [totalmileage],";
			query += " (SELECT debname FROM fdebtor WHERE debcode=r.debcode) AS [debname],";
			query += " (SELECT debadd FROM fdebtor WHERE debcode=r.debcode) AS [debadd],";
			query += " (SELECT tel FROM fdebtor WHERE debcode=r.debcode) AS [tel],";
			query += " r.resno,r.din,r.noofday,";
			query += " (SELECT description FROM fhirestatus WHERE hirestsid=r.cohirestsid) AS [hirestatus]";
			query += " FROM freservation AS r";
			query += " WHERE cohirestsid IN('COMPLETED','NOSHOW') AND";
			query += " r.dout>='"+dateFrom+"' AND r.dout<='"+dateTo+"'";
			
			Query Q = em.createNativeQuery(query);
			List<Object[]> l = Q.getResultList();
			List<Freservationdiaryrpt> fresdiaryrecs=new ArrayCollection();
			
			Timestamp dout;
			String regno;
			int totalmileage;
			String debname;
			String debadd;
			String tel;
			String resno;
			Timestamp din;
			int noofday;
			String hirestatus;
			
			for(Object[] r : l){
				Freservationdiaryrpt obj=new Freservationdiaryrpt();
				
				dout=(Timestamp)r[0];
				regno=(String)r[1];
				totalmileage=Integer.parseInt(r[2].toString());
				debname=(String)r[3];
				debadd=(String)r[4];
				tel=(String)r[5];
				resno=(String)r[6];
				din=(Timestamp)r[7];
				noofday=Integer.parseInt(r[8].toString());
				hirestatus=(String)r[9];
				
				obj.setDout(dout);
				obj.setRegno(regno);
				obj.setTotalmileage(totalmileage);
				obj.setDebname(debname);
				obj.setDebadd(debadd);
				obj.setTel(tel);
				obj.setResno(resno);
				obj.setDin(din);
				obj.setNoofday(noofday);
				obj.setHirestatus(hirestatus);

				fresdiaryrecs.add(obj);
			}
			return fresdiaryrecs;
		} catch (Exception e) {
			System.out.println("FreservationDaoImpl getRenteredVehicleDet: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public List<Freservationdiaryrpt> getVehicleHireStatus(String dateFrom, String dateTo, String hireTypeId, String cohireStsId, String rateType) {
		try {
			em.flush();
			hireTypeId=hireTypeId.trim();
			cohireStsId=cohireStsId.trim();
			rateType=rateType.trim();
			String newline = System.getProperty("line.separator");
			if(hireTypeId.length()<=0 || cohireStsId.length()<=0 || rateType.length()<=0)
				return  null;
			
			String query="SELECT * FROM";
			query += " (SELECT";
			query += " (SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1) AS regno,";
			query += " (SELECT description FROM fvehiclemake WHERE vehimakeid=(SELECT vehimakeid FROM fvehicle WHERE regno=(SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1))) AS make,";
			query += " r.resno,";
			//query += " (SELECT name FROM femployee WHERE empid=(SELECT empid FROM fresdriver WHERE resno=r.resno AND priority=1)) AS driver,";
			query += " (SELECT STUFF((SELECT ', ' + RTRIM(name) FROM femployee WHERE empid IN (SELECT empid FROM fresdriver WHERE resno=r.resno AND priority=1) FOR XML PATH('')),1, 1, '')) AS driver,";
			query += " r.noofday,r.dout,r.din,";
			query += " (SELECT description FROM fhirestatus WHERE hirestsid=r.cohirestsid) AS hirestatus,";
			query += " (SELECT description FROM fhiretype WHERE hiretypeid=r.hiretypeid) AS hiretype,";
			query += " (SELECT description FROM fratetype WHERE ratetype=r.ratetype) AS ratetype,";
			query += " r.adddate as adddate,r.adduser as adduser,r.agrno, ";
			query += " (select debname from fdebtor where debcode=r.debcode) AS debname  ";
			query += " FROM freservation AS r"; 

			
			
			if(hireTypeId.equals("all")==false || cohireStsId.equals("all")==false || rateType.equals("all") == false){
				query += " WHERE";
			
				if(hireTypeId.equals("all")==false)
					query += " r.hiretypeid='" + hireTypeId + "'";
				
				if(hireTypeId.equals("all")==false && cohireStsId.equals("all")==false)
					query += " AND";
				
				if(cohireStsId.equals("all")==false)
					query += " r.cohirestsid='" + cohireStsId + "'";
				
				if((hireTypeId.equals("all")==false || cohireStsId.equals("all")==false) && rateType.equals("all")==false)
					query += " AND";
				
				if(rateType.equals("all")==false)
					query += " r.ratetype='" + rateType + "'";
			}
			
			query += ") AS r";
			
			if(cohireStsId.equalsIgnoreCase("CHECKIN") || cohireStsId.equalsIgnoreCase("COMPLETED") || cohireStsId.equalsIgnoreCase("INVOICE") || cohireStsId.equalsIgnoreCase("VC") || cohireStsId.equalsIgnoreCase("NOSHOW")){
				query += " WHERE r.din>='"+dateFrom+"' AND r.din<='"+dateTo+"'";
			}
			else if(cohireStsId.equalsIgnoreCase("BOOKED") || cohireStsId.equalsIgnoreCase("CONFIRMED") || cohireStsId.equalsIgnoreCase("PREPARED")){
				query += " WHERE r.adddate>='"+dateFrom+"' AND r.adddate<='"+dateTo+"'";
			}
			else{
				query += " WHERE r.dout>='"+dateFrom+"' AND r.dout<='"+dateTo+"'";
			}
			
			query += " ORDER BY r.hirestatus,r.hiretype,r.din";
			
			System.out.println("query: " + query);
			
			Query Q = em.createNativeQuery(query);
			List<Object[]> l1 = Q.getResultList();
			List<Freservationdiaryrpt> list=new ArrayCollection();
			
			String regno;
			String make;
			String resno;
			String driver;
			int noofday;
			Timestamp dout;
			Timestamp din;
			String hirestatus;
			String hiretype;

			for(Object[] r1 : l1){
				regno=(String)r1[0];
				make=(String)r1[1];
				resno=(String)r1[2] +newline+(String)r1[12];
				//driver=(String)r1[3];
				
				if(  (String)r1[3] == null || ((String)r1[3]).equalsIgnoreCase("null")){r1[3]="No Driver";}
				driver="DRIVER: "+(String)r1[3]+"\nOFFICER: "+(String)r1[11]+"\nCLIENT: "+(String)r1[13];
				
				System.out.println("driver :"+driver);
				System.out.println("(String)r1[13] :"+(String)r1[13]);
				noofday=Integer.parseInt(r1[4].toString());
				dout=(Timestamp)r1[5];
				din=(Timestamp)r1[6];
				hirestatus=(String)r1[7];
				hiretype=(String)r1[8];			
				
				Freservationdiaryrpt obj=new Freservationdiaryrpt();
				obj.setRegno(regno);
				obj.setMake(make);
				obj.setResno(resno);
				obj.setDriver(driver);
				obj.setNoofday(noofday);
				obj.setDout(dout);
				obj.setDin(din);
				obj.setHirestatus(hirestatus);
				obj.setHiretype(hiretype);
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			System.out.println("FreservationDaoImpl getVehicleHireStatus: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	@Override
	public List<Freservationdiaryrpt> getVehicleHireStatusDaily(String dateFrom, String dateTo, String hireTypeId, String cohireStsId, String rateType) {
		try {
			em.flush();
			hireTypeId=hireTypeId.trim();
			cohireStsId=cohireStsId.trim();
			rateType=rateType.trim();
			String newline = System.getProperty("line.separator");
			if(hireTypeId.length()<=0 || cohireStsId.length()<=0 || rateType.length()<=0)
				return  null;
			
			String query="SELECT * FROM";
			query += " (SELECT";
			query += " (SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1) AS regno,";
			query += " (SELECT description FROM fvehiclemake WHERE vehimakeid=(SELECT vehimakeid FROM fvehicle WHERE regno=(SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1))) AS make,";
			query += " r.resno,";
			//query += " (SELECT name FROM femployee WHERE empid=(SELECT empid FROM fresdriver WHERE resno=r.resno AND priority=1)) AS driver,";
			query += " (SELECT STUFF((SELECT ', ' + RTRIM(name) FROM femployee WHERE empid IN (SELECT empid FROM fresdriver WHERE resno=r.resno AND priority=1) FOR XML PATH('')),1, 1, '')) AS driver,";
			query += " r.noofday,r.dout,r.din,";
			query += " (SELECT description FROM fhirestatus WHERE hirestsid=r.cohirestsid) AS hirestatus,";
			query += " (SELECT description FROM fhiretype WHERE hiretypeid=r.hiretypeid) AS hiretype,";
			query += " (SELECT description FROM fratetype WHERE ratetype=r.ratetype) AS ratetype,";
			query += " r.adddate as adddate,r.adduser as adduser, ";
			query += " (select debname from fdebtor where debcode=r.debcode) AS debname, r.agrno ";
			query += " FROM freservation AS r"; 
			
			if(hireTypeId.equals("all")==false || cohireStsId.equals("all")==false || rateType.equals("all") == false){
				query += " WHERE";
				
				if(hireTypeId.equals("all")==false)
					query += " r.hiretypeid='" + hireTypeId + "'";
				
				if(hireTypeId.equals("all")==false && cohireStsId.equals("all")==false)
					query += " AND";
				
				if(cohireStsId.equals("all")==false)
					query += " r.cohirestsid='" + cohireStsId + "'";
				
				if((hireTypeId.equals("all")==false || cohireStsId.equals("all")==false) && rateType.equals("all")==false)
					query += " AND";
				
				if(rateType.equals("all")==false)
					query += " r.ratetype='" + rateType + "'";
			}
			
			query += ") AS r";
			
//			if(cohireStsId.equalsIgnoreCase("CHECKIN") || cohireStsId.equalsIgnoreCase("COMPLETED") || cohireStsId.equalsIgnoreCase("INVOICE") || cohireStsId.equalsIgnoreCase("VC") || cohireStsId.equalsIgnoreCase("NOSHOW")){
//				query += " WHERE r.din>='"+dateFrom+"' AND r.din<='"+dateTo+"'";
//			}
//			else if(cohireStsId.equalsIgnoreCase("BOOKED") || cohireStsId.equalsIgnoreCase("CONFIRMED") || cohireStsId.equalsIgnoreCase("PREPARED")){
				query += " WHERE r.adddate>='"+dateFrom+"' AND r.adddate<='"+dateTo+"'";
//			}
//			else{
//				query += " WHERE r.dout>='"+dateFrom+"' AND r.dout<='"+dateTo+"'";
//			}
			
			query += " ORDER BY r.hirestatus,r.hiretype,r.din";
			
			System.out.println("query: " + query);
			
			Query Q = em.createNativeQuery(query);
			List<Object[]> l1 = Q.getResultList();
			List<Freservationdiaryrpt> list=new ArrayCollection();
			
			String regno;
			String make;
			String resno;
			String driver;
			int noofday;
			Timestamp dout;
			Timestamp din;
			String hirestatus;
			String hiretype;
			
			for(Object[] r1 : l1){
				regno=(String)r1[0];
				make=(String)r1[1];
				resno=(String)r1[2]+newline+(String)r1[13];
				if(  (String)r1[3] == null || ((String)r1[3]).equalsIgnoreCase("null")){r1[3]="No Driver";}
				driver="DRIVER: "+(String)r1[3]+"\nOFFICER: "+(String)r1[11]+"\nCLIENT: "+(String)r1[12];
				noofday=Integer.parseInt(r1[4].toString());
				dout=(Timestamp)r1[5];
				din=(Timestamp)r1[6];
				hirestatus=(String)r1[7];
				hiretype=(String)r1[8];			
				//driver+="\n"+(String)r1[11];
				
				Freservationdiaryrpt obj=new Freservationdiaryrpt();
				obj.setRegno(regno);
				obj.setMake(make);
				obj.setResno(resno);
				obj.setDriver(driver);
				obj.setNoofday(noofday);
				obj.setDout(dout);
				obj.setDin(din);
				obj.setHirestatus(hirestatus);
				obj.setHiretype(hiretype);
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			System.out.println("FreservationDaoImpl getVehicleHireStatus: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Transactional
	@Override
	public List<Freservationdiaryrpt> getVehicleHireStatusCI(String dateFrom, String dateTo, String hireTypeId, String cohireStsId, String rateType) {
		try {
			em.flush();
			hireTypeId=hireTypeId.trim();
			cohireStsId=cohireStsId.trim();
			rateType=rateType.trim();
			String newline = System.getProperty("line.separator");
			if(hireTypeId.length()<=0 || cohireStsId.length()<=0 || rateType.length()<=0)
				return  null;
			
			String query="SELECT * FROM";
			query += " (SELECT";
			query += " (SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1) AS regno,";
			query += " (SELECT description FROM fvehiclemake WHERE vehimakeid=(SELECT vehimakeid FROM fvehicle WHERE regno=(SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1))) AS make,";
			query += " r.resno,";
			//query += " (SELECT name FROM femployee WHERE empid=(SELECT empid FROM fresdriver WHERE resno=r.resno AND priority=1)) AS driver,";
			query += " (SELECT STUFF((SELECT ', ' + RTRIM(name) FROM femployee WHERE empid IN (SELECT empid FROM fresdriver WHERE resno=r.resno AND priority=1) FOR XML PATH('')),1, 1, '')) AS driver,";
			query += " r.noofday,r.dout,r.din,";
			query += " (SELECT description FROM fhirestatus WHERE hirestsid=r.cohirestsid) AS hirestatus,";
			query += " (SELECT description FROM fhiretype WHERE hiretypeid=r.hiretypeid) AS hiretype,";
			query += " (SELECT description FROM fratetype WHERE ratetype=r.ratetype) AS ratetype,";
			query += " r.adddate as adddate,r.adduser as adduser, ";
			query += " (select debname from fdebtor where debcode=r.debcode) AS debname ,r.agrno ";
			query += " FROM freservation AS r"; 
			
			if(hireTypeId.equals("all")==false || cohireStsId.equals("all")==false || rateType.equals("all") == false){
				query += " WHERE";
				
				if(hireTypeId.equals("all")==false)
					query += " r.hiretypeid='" + hireTypeId + "'";
				
				if(hireTypeId.equals("all")==false && cohireStsId.equals("all")==false)
					query += " AND";
				
				if(cohireStsId.equals("all")==false)
					query += " r.cohirestsid='" + cohireStsId + "'";
				
				if((hireTypeId.equals("all")==false || cohireStsId.equals("all")==false) && rateType.equals("all")==false)
					query += " AND";
				
				if(rateType.equals("all")==false)
					query += " r.ratetype='" + rateType + "'";
			}
			
			query += ") AS r";
			
			//if(cohireStsId.equalsIgnoreCase("CHECKIN") || cohireStsId.equalsIgnoreCase("COMPLETED") || cohireStsId.equalsIgnoreCase("INVOICE") || cohireStsId.equalsIgnoreCase("VC") || cohireStsId.equalsIgnoreCase("NOSHOW")){
				query += " WHERE r.din>='"+dateFrom+"' AND r.din<='"+dateTo+"'";
		//	}
/*			else if(cohireStsId.equalsIgnoreCase("BOOKED") || cohireStsId.equalsIgnoreCase("CONFIRMED") || cohireStsId.equalsIgnoreCase("PREPARED")){
				query += " WHERE r.adddate>='"+dateFrom+"' AND r.adddate<='"+dateTo+"'";
			}
			else{
				query += " WHERE r.dout>='"+dateFrom+"' AND r.dout<='"+dateTo+"'";
			}*/
			
			query += " ORDER BY r.hirestatus,r.hiretype,r.din";
			
			System.out.println("query: " + query);
			
			Query Q = em.createNativeQuery(query);
			List<Object[]> l1 = Q.getResultList();
			List<Freservationdiaryrpt> list=new ArrayCollection();
			
			String regno;
			String make;
			String resno;
			String driver;
			int noofday;
			Timestamp dout;
			Timestamp din;
			String hirestatus;
			String hiretype;
			
			for(Object[] r1 : l1){
				regno=(String)r1[0];
				make=(String)r1[1];
				resno=(String)r1[2]+newline+(String)r1[13];
				if(  (String)r1[3] == null || ((String)r1[3]).equalsIgnoreCase("null")){r1[3]="No Driver";}
				driver="DRIVER: "+(String)r1[3]+"\nOFFICER: "+(String)r1[11]+"\nCLIENT: "+(String)r1[12];
				noofday=Integer.parseInt(r1[4].toString());
				dout=(Timestamp)r1[5];
				din=(Timestamp)r1[6];
				hirestatus=(String)r1[7];
				hiretype=(String)r1[8];			
				//driver+="\n"+(String)r1[11];
				
				Freservationdiaryrpt obj=new Freservationdiaryrpt();
				obj.setRegno(regno);
				obj.setMake(make);
				obj.setResno(resno);
				obj.setDriver(driver);
				obj.setNoofday(noofday);
				obj.setDout(dout);
				obj.setDin(din);
				obj.setHirestatus(hirestatus);
				obj.setHiretype(hiretype);
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			System.out.println("FreservationDaoImpl getVehicleHireStatus: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	@Override
	public List<Freservationdiaryrpt> getVehicleHireStatusCO(String dateFrom, String dateTo, String hireTypeId, String cohireStsId, String rateType) {
		try {
			em.flush();
			hireTypeId=hireTypeId.trim();
			cohireStsId=cohireStsId.trim();
			rateType=rateType.trim();
			String newline = System.getProperty("line.separator");
			if(hireTypeId.length()<=0 || cohireStsId.length()<=0 || rateType.length()<=0)
				return  null;
			
			String query="SELECT * FROM";
			query += " (SELECT";
			query += " (SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1) AS regno,";
			query += " (SELECT description FROM fvehiclemake WHERE vehimakeid=(SELECT vehimakeid FROM fvehicle WHERE regno=(SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1))) AS make,";
			query += " r.resno,";
			//query += " (SELECT name FROM femployee WHERE empid=(SELECT empid FROM fresdriver WHERE resno=r.resno AND priority=1)) AS driver,";
			query += " (SELECT STUFF((SELECT ', ' + RTRIM(name) FROM femployee WHERE empid IN (SELECT empid FROM fresdriver WHERE resno=r.resno AND priority=1) FOR XML PATH('')),1, 1, '')) AS driver,";
			query += " r.noofday,r.dout,r.din,";
			query += " (SELECT description FROM fhirestatus WHERE hirestsid=r.cohirestsid) AS hirestatus,";
			query += " (SELECT description FROM fhiretype WHERE hiretypeid=r.hiretypeid) AS hiretype,";
			query += " (SELECT description FROM fratetype WHERE ratetype=r.ratetype) AS ratetype,";
			query += " r.adddate as adddate,r.adduser as adduser, ";
			query += " (select debname from fdebtor where debcode=r.debcode) AS debname, r.agrno ";
			query += " FROM freservation AS r"; 
			
			if(hireTypeId.equals("all")==false || cohireStsId.equals("all")==false || rateType.equals("all") == false){
				query += " WHERE";
				
				if(hireTypeId.equals("all")==false)
					query += " r.hiretypeid='" + hireTypeId + "'";
				
				if(hireTypeId.equals("all")==false && cohireStsId.equals("all")==false)
					query += " AND";
				
				if(cohireStsId.equals("all")==false)
					query += " r.cohirestsid='" + cohireStsId + "'";
				
				if((hireTypeId.equals("all")==false || cohireStsId.equals("all")==false) && rateType.equals("all")==false)
					query += " AND";
				
				if(rateType.equals("all")==false)
					query += " r.ratetype='" + rateType + "'";
			}
			
			query += ") AS r";
			
/*			if(cohireStsId.equalsIgnoreCase("CHECKIN") || cohireStsId.equalsIgnoreCase("COMPLETED") || cohireStsId.equalsIgnoreCase("INVOICE") || cohireStsId.equalsIgnoreCase("VC") || cohireStsId.equalsIgnoreCase("NOSHOW")){
				query += " WHERE r.din>='"+dateFrom+"' AND r.din<='"+dateTo+"'";
			}
			else if(cohireStsId.equalsIgnoreCase("BOOKED") || cohireStsId.equalsIgnoreCase("CONFIRMED") || cohireStsId.equalsIgnoreCase("PREPARED")){
				query += " WHERE r.adddate>='"+dateFrom+"' AND r.adddate<='"+dateTo+"'";
			}
			else{*/
				query += " WHERE r.dout>='"+dateFrom+"' AND r.dout<='"+dateTo+"'";
			//}
			
			query += " ORDER BY r.hirestatus,r.hiretype,r.din";
			
			System.out.println("query: " + query);
			
			Query Q = em.createNativeQuery(query);
			List<Object[]> l1 = Q.getResultList();
			List<Freservationdiaryrpt> list=new ArrayCollection();
			
			String regno;
			String make;
			String resno;
			String driver;
			int noofday;
			Timestamp dout;
			Timestamp din;
			String hirestatus;
			String hiretype;
			
			for(Object[] r1 : l1){
				regno=(String)r1[0];
				make=(String)r1[1];
				resno=(String)r1[2]+newline+(String)r1[13];
				if(  (String)r1[3] == null || ((String)r1[3]).equalsIgnoreCase("null")){r1[3]="No Driver";}
				driver="DRIVER: "+(String)r1[3]+"\nOFFICER: "+(String)r1[11]+"\nCLIENT: "+(String)r1[12];
				noofday=Integer.parseInt(r1[4].toString());
				dout=(Timestamp)r1[5];
				din=(Timestamp)r1[6];
				hirestatus=(String)r1[7];
				hiretype=(String)r1[8];	
				//driver+="\n"+(String)r1[11];
				
				Freservationdiaryrpt obj=new Freservationdiaryrpt();
				obj.setRegno(regno);
				obj.setMake(make);
				obj.setResno(resno);
				obj.setDriver(driver);
				obj.setNoofday(noofday);
				obj.setDout(dout);
				obj.setDin(din);
				obj.setHirestatus(hirestatus);
				obj.setHiretype(hiretype);
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			System.out.println("FreservationDaoImpl getVehicleHireStatus: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	
//following method copied from getVehicleHireStatus method.if there any logic change or bug or modification may be applied both
	@Transactional
	@Override
	public List<Freservationdiaryrpt> getAgrHistory(String dateFrom, String dateTo) {
		try {
			em.flush();
			String query="SELECT";
			query += " r.dout,";
			query += " (SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1) AS [regno],";
			query += " (r.cimileage-r.comileage) AS [totalmileage],";
			query += " (SELECT debname FROM fdebtor WHERE debcode=r.debcode) AS [debname],";
			query += " (SELECT debadd FROM fdebtor WHERE debcode=r.debcode) AS [debadd],";
			query += " (SELECT tel FROM fdebtor WHERE debcode=r.debcode) AS [tel],";
			query += " r.resno,r.agrno,r.din,r.noofday,";
			query += " (SELECT description FROM fhirestatus WHERE hirestsid=r.cohirestsid) AS [hirestatus]";
			query += " FROM freservation AS r";
		//	query += " WHERE cohirestsid IN('COMPLETED','NOSHOW') AND";
			query += " WHERE r.dout>='"+dateFrom+"' AND r.dout<='"+dateTo+"'  ";
			//query += "and  parentref in (select parentref from freservation  group by parentref ) " ;
			query += "order by r.agrno,r.resno";// order by freservation.agrno,freservation.resno";
			
			Query Q = em.createNativeQuery(query);
			List<Object[]> l = Q.getResultList();
			List<Freservationdiaryrpt> fresdiaryrecs=new ArrayCollection();
			
			Timestamp dout;
			String regno;
			int totalmileage;
			String debname;
			String debadd;
			String tel;
			String resno;
			String agrno;
			Timestamp din;
			int noofday;
			String hirestatus;
			
			for(Object[] r : l){
				Freservationdiaryrpt obj=new Freservationdiaryrpt();
				
				dout=(Timestamp)r[0];
				regno=(String)r[1];
				totalmileage=Integer.parseInt(r[2].toString());
				debname=(String)r[3];
				debadd=(String)r[4];
				tel=(String)r[5];
				resno=(String)r[6];
				agrno=(String)r[7];//modified by sanka{add new field for report}
				din=(Timestamp)r[8];
				noofday=Integer.parseInt(r[9].toString());
				hirestatus=(String)r[10];
				
				obj.setDout(dout);
				obj.setRegno(regno);
				obj.setTotalmileage(totalmileage);
				obj.setDebname(debname);
				obj.setDebadd(debadd);
				obj.setTel(tel);
				obj.setResno(resno);
				obj.setAgrno(agrno+"\n"+resno);
				obj.setDin(din);
				obj.setNoofday(noofday);
				obj.setHirestatus(hirestatus);

				fresdiaryrecs.add(obj);
			}
			return fresdiaryrecs;
		} catch (Exception e) {
			System.out.println("FreservationDaoImpl getAgrHistory: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Freservationdiaryrpt> getVehicleHistory(String dateFrom,
			String dateTo, String regno) {
		try {
			em.flush();
			String query="SELECT";
			query += " r.dout,";
			query += " (SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1  AND regno='"+regno+"') AS [regno],";
			query += " (r.cimileage-r.comileage) AS [totalmileage],";
			query += " (SELECT debname FROM fdebtor WHERE debcode=r.debcode) AS [debname],";
			query += " (SELECT debadd FROM fdebtor WHERE debcode=r.debcode) AS [debadd],";
			query += " (SELECT tel FROM fdebtor WHERE debcode=r.debcode) AS [tel],";
			query += " r.resno,r.agrno,r.din,r.noofday,";
			query += " (SELECT description FROM fhirestatus WHERE hirestsid=r.cohirestsid) AS [hirestatus],";
			query += " r.comileage,r.cimileage,r.cofuellevel,r.cifuellevel";
			query += " FROM freservation AS r";
			query += " WHERE resno IN(SELECT resno FROM fresvehicle WHERE regno='"+regno+"') AND";
			query += "  r.dout>='"+dateFrom+"' AND r.dout<='"+dateTo+"' order by r.agrno,r.resno";// order by freservation.agrno,freservation.resno";
/* Menaka			
//			String query="SELECT";
//			query += " r.dout,";
//			query += " (SELECT regno FROM fresvehicle WHERE resno=r.resno AND priority=1  AND regno='"+regno+"') AS [regno],";
//			query += " (r.cimileage-r.comileage) AS [totalmileage],";
//			query += " (SELECT debname FROM fdebtor WHERE debcode=r.debcode) AS [debname],";
//			query += " (SELECT debadd FROM fdebtor WHERE debcode=r.debcode) AS [debadd],";
//			query += " (SELECT tel FROM fdebtor WHERE debcode=r.debcode) AS [tel],";
//			query += " r.resno,r.agrno,r.din,r.noofday,";
//			query += " (SELECT description FROM fhirestatus WHERE hirestsid=r.cohirestsid) AS [hirestatus],";
//			query += " r.comileage,r.cimileage,r.cofuellevel,r.cifuellevel";
//			query += " FROM freservation AS r";
//			query += " WHERE resno IN(SELECT resno FROM fresvehicle) AND";
//			query += "  r.dout>='"+dateFrom+"' AND r.dout<='"+dateTo+"' and ";
//			query += "  parentref in (select parentref from freservation  group by parentref ) order by r.agrno,r.resno";// order by freservation.agrno,freservation.resno";
*/			

			Query Q = em.createNativeQuery(query);
			List<Object[]> l = Q.getResultList();
			List<Freservationdiaryrpt> fresdiaryrecs=new ArrayCollection();
			for(Object[] r : l){
				Freservationdiaryrpt obj=new Freservationdiaryrpt();
				obj.setDout((Timestamp)r[0]);
				obj.setRegno((String)r[1]);
				obj.setTotalmileage(Integer.parseInt(r[2].toString()));
				obj.setDebname((String)r[3]);
				obj.setDebadd((String)r[4]);
				obj.setTel((String)r[5]);
				obj.setResno((String)r[6]);
				obj.setAgrno((String)r[7]+"\n"+(String)r[6]);
				obj.setDin((Timestamp)r[8]);
				obj.setNoofday(Integer.parseInt(r[9].toString()));
				obj.setHirestatus((String)r[10]);
				obj.setComileage(r[11].toString());
				obj.setCimileage(r[12].toString());
				obj.setCofuellevel(r[13].toString());
				obj.setCifuellevel(r[14].toString());
				fresdiaryrecs.add(obj);
				
				System.out.println("DIN: "+obj.getDin());
				System.out.println("DOUT: "+obj.getDout());
				
			}
			return fresdiaryrecs;
		} catch (Exception e) {
			System.out.println("FreservationDaoImpl getVehicleHistory: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional(readOnly=true)
	@Override
	public String getOtherServiceList(String resno)
	{
		em.flush();
		String queryString="select fothersrv.description,fresothsrv.rate,fresothsrv.dfrom,fresothsrv.timeout,fresothsrv.dto,fresothsrv.timein,femployee.name,femployee.dlno from fresothsrv " 
							+"inner join fothersrv on (fresothsrv.srvid=fothersrv.srvid) "
							+"left join fresdriver on (fresothsrv.srvid=fresdriver.srvid and fresdriver.priority=1  and CONVERT(VARCHAR(10), fresdriver.dout, 101) =CONVERT(VARCHAR(10), fresothsrv.dfrom, 101) and fresdriver.timeout=fresothsrv.timeout) "
							+"left join femployee on (fresdriver.empid =femployee.empid and fresdriver.issrv=1 and fresdriver.priority=1) "
							+"where fresothsrv.resno=?1";
		List<Object[]> lst= em.createNativeQuery(queryString).setParameter(1, resno).getResultList();
		String servicemsg="";
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		for(Object[] r : lst)
		{
			servicemsg +=(String)r[0];//description
			servicemsg +=" Rate:"+((BigDecimal)r[1]).intValue()+"/=";//rate
			servicemsg +=" Date:"+format.format(((Timestamp)r[2]));//dfrom
			servicemsg +="@"+(String)r[3];//timeout
			if((String)r[6]!=null)
			{
				System.out.println("-----------Driver"+(String)r[6]);
				servicemsg +=" Driver:"+(String)r[6];//name
				servicemsg +=" Licence:"+(String)r[7];
			}
			servicemsg +="\n";
		}
		return servicemsg;
	}
	
	
	
	@Transactional(readOnly=true)
	@Override
	public String getHistoryByID(String debcode) {
		try {
			em.flush();
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			String servicemsg="Client has made his last booking\n";
			//TOP 1 agrno,resno,dout,din,hiretypeid
			
//			select * from freservation where recordid in (select max(recordid) from freservation where debcode='C000008191' group by debcode )
//			select f.agrno,f.resno,f.dout,f.din from freservation as f  where f.recordid in (select max(recordid) from freservation where debcode=?1 group by debcode )
			
			Query q= em.createNativeQuery("select f.agrno,f.resno,f.dout,f.din from freservation as f  where f.recordid in (select max(recordid) from freservation where debcode=?1 group by debcode )");
//			Query q= em.createQuery("select f.agrno,f.resno,f.dout,f.din from Freservation as f WHERE f.debcode=?1 ORDER BY f.dout");
			List<Object[]> lst= q.setParameter(1,debcode).getResultList();
			System.out.println("size of list:"+lst.size());
			if(lst.size()>0){
			Object[] r=lst.get(0);
				servicemsg +=format.format(new Date(((java.sql.Timestamp)r[2]).getTime()))+" - ";//dout
				servicemsg +=format.format(new Date(((java.sql.Timestamp)r[3]).getTime()));//din
				servicemsg +="\n"+r[0]+" - "+r[1];//agrno
			}
			else
			{
				servicemsg="New Customer";
			}
			System.out.println("servicemsg :"+servicemsg);
			return servicemsg;

		} catch (Exception e) {
			return null;
		} finally {
			em.flush();
		}
		
	}

	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Freservationdiaryrpt> debtorReport(String dateFrom ,String dateTo,String reportType) {
		em.flush();
		String newline = System.getProperty("line.separator");
		//2015-05-01 2015-05-16

		String sqlQuery="SELECT r.resno,r.agrno,r.debcode,d.debname,d.vattyp,h.companyid,r.cohirestsid,r.booked,r.checkout,r.completed,r.invoice,rv.regno,r.checkin,c.description FROM FRESERVATION r JOIN FDEBTOR d on r.debcode=d.debcode "+  
		" JOIN FRESHED h on r.agrno=h.agrno join fresvehicle rv on r.resno=rv.resno and rv.priority=1 join fcompany c on h.companyid=c.companyid "+
		" WHERE r.adddate>'"+dateFrom+"' and r.adddate<'"+dateTo+"'  ";
		
		String sqlQuerySub=" and r.debcode in (SELECT d.debcode FROM FDEBTOR d  WHERE d.adddate>'"+dateFrom+"' and d.adddate<'"+dateTo+"') ";

		if(reportType.equals("1"))
		{
			sqlQuery=sqlQuery+sqlQuerySub;
			System.out.println("debtorReport 1");
		}
		if(reportType.equals("2"))
		{
			System.out.println("debtorReport 2");
		}
		
		List<Object[]> rowObjectlist = new ArrayList();
		try {
			Query query = em.createNativeQuery(sqlQuery);
			rowObjectlist = query.getResultList();
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		List<Freservationdiaryrpt> lstFreservationdiaryrpt = new ArrayList<Freservationdiaryrpt>();
		for (Object[] row : rowObjectlist) {
			try {
				Freservationdiaryrpt view=new Freservationdiaryrpt();
				view.setResno((String) row[1] +newline+ (String) row[0]);
				view.setDebadd((String) row[2] );
				view.setDebname((String) row[3]);
				view.setMake((String) row[4]);//d.vattyp
				view.setModel((String) row[13]);//h.companyid
//				view.setD((String) row[5]);//h.companyid
				view.setTel((String) row[6]);//r.cohirestsid
				view.setDriver((String) row[7]);//r.booked
				view.setGotime((String) row[8]);//r.checkout
				view.setItinerary((String) row[9]);//r.completed
				view.setCifuellevel((String) row[10]);//r.invoice
				view.setRegno((String) row[11]);//rv.regno
				view.setHiretype((String) row[12]);//rv.checkin
				
				if(view.getMake().equals("E"))
				{
					view.setMake("ALL INCLUSIVE");
				}
				else if(view.getMake().equals("X"))
				{
					view.setMake("EXCEMPT");
				}
				else if(view.getMake().equals("S"))
				{
					view.setMake("SVAT");
				}
				else if(view.getMake().equals("S"))
				{
					view.setMake("TAX");
				}
				lstFreservationdiaryrpt.add(view);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lstFreservationdiaryrpt;
	}
	
	
}
