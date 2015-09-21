package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fresaccrate;
import com.dspl.malkey.domain.Fresaccs;
import com.dspl.malkey.domain.Fresaddcharge;
import com.dspl.malkey.domain.Fresclientdriver;
import com.dspl.malkey.domain.Fresdriver;
import com.dspl.malkey.domain.Fresdriverrate;
import com.dspl.malkey.domain.Freservation;
import com.dspl.malkey.domain.Freservationdiaryrpt;
import com.dspl.malkey.domain.Freshed;
import com.dspl.malkey.domain.Fresothersrvrate;
import com.dspl.malkey.domain.Fresothsrv;
import com.dspl.malkey.domain.Fresvehicle;
import com.dspl.malkey.domain.Fresvehiclerate;
import com.dspl.malkey.domain.Fresvehinv;
import com.dspl.malkey.domain.Fvehicledamage;
import com.dspl.malkey.report.FOtherServiceRPT;
import com.dspl.malkey.report.FrentagreementRPT;
import com.dspl.malkey.report.FrentagrvehinvRPT;
import com.dspl.malkey.report.Reservation;
import com.dspl.malkey.view.FreservationView;
import com.dspl.malkey.view.VehicleAvailabilityView;

public interface FreservationDAO {
	int count();
	//void create(Freservation freservation);
	Freservation findByID(String resno);
	List<Freservation> List(int startIndex, int numItems);
	List<Freservation> ListAll();
	void removeByID(String resno);
	String create(
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
			Boolean ignoreAvailability);
/*	void update(Freservation freservation, 
			List<Fresvehicle> lstfresvehicle,
			List<Fresaccs> lstfresaccs, 
			List<Fresdriver> lstfresdriver,
			List<Fresothsrv> lstfresothsrv, 
			Fresvehiclerate fresvehiclerate,
			List<Fresaccrate> lstfresaccrate, 
			Fresdriverrate fresdriverrate,
			List<Fresothersrvrate> lstfresothersrvrate,
			List<Fresaddcharge> lstfresaddcharge);*/
	
	List<FrentagreementRPT> listRptRentAgreement(String resNo,String lsJustServerName);	
	List<FrentagrvehinvRPT> listRptRentVehiclesInventry(String resNo);
	
	String changeStatus(
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
			List<Fvehicledamage> lstFvehicledamage,
			List<Fresvehinv> lstFresvehinv);
	
	List<Freservationdiaryrpt> getVehicleHireStatus(String dateFrom,String dateTo,String hireTypeId,String cohireStsId,String rateType);
	List<Freservationdiaryrpt> getVehicleHireStatusCO(String dateFrom,String dateTo,String hireTypeId,String cohireStsId,String rateType);
	List<Freservationdiaryrpt> getVehicleHireStatusCI(String dateFrom,String dateTo,String hireTypeId,String cohireStsId,String rateType);
	List<Freservationdiaryrpt> getVehicleHireStatusDaily(String dateFrom,String dateTo,String hireTypeId,String cohireStsId,String rateType);
	List<Freservationdiaryrpt> getResDiaryData(String dateFrom,String dateTo,String hireTypeId,String cohireStsId);
	List<Freservationdiaryrpt> getRentedVehicleDet(String dateFrom,String dateTo);
	Reservation listAllByResno(String resno);
	List<Freservation> listByHedAgrno(String agrno);
	List<Freservation> listPartByHedAgrno(String agrno);
	List<Freservation> listDetailsByHedAgrno(String agrno);
	String changeVehicle(String resno, List<Fresvehicle> lstFresvehicle,
			Fresvehiclerate fresvehiclerate);
	List<FreservationView> advanceSearch(String hireStatus,String hireType,String debcode,String regno,String dateFrom ,String dateTo);
	String changeCheckOutVehicle(Freservation freservation, Freshed freshed,
			List<Fresvehicle> lstfresvehicle, List<Fresaccs> lstfresaccs,
			List<Fresdriver> lstfresdriver, List<Fresothsrv> lstfresothsrv,
			Fresvehiclerate fresvehiclerate, List<Fresaccrate> lstfresaccrate,
			Fresdriverrate fresdriverrate,
			List<Fresothersrvrate> lstfresothersrvrate,
			List<Fresaddcharge> lstfresaddcharge,
			List<Fresclientdriver> lstfresclientdriver,
			List<Fvehicledamage> lstCheckInFvehicledamage,
			List<Fresvehinv> lstCheckInFresvehinv,
			Freservation freservationCopy, Freshed freshedCopy,
			List<Fresvehicle> lstfresvehicleCopy,
			List<Fresaccs> lstfresaccsCopy, List<Fresdriver> lstfresdriverCopy,
			List<Fresothsrv> lstfresothsrvCopy,
			Fresvehiclerate fresvehiclerateCopy,
			List<Fresaccrate> lstfresaccrateCopy,
			Fresdriverrate fresdriverrateCopy,
			List<Fresothersrvrate> lstfresothersrvrateCopy,
			List<Fresaddcharge> lstfresaddchargeCopy,
			List<Fresclientdriver> lstfresclientdriverCopy,
			List<Fvehicledamage> lstCheckInFvehicledamageCopy,
			List<Fresvehinv> lstCheckInFresvehinvCopy);
	List<Freservationdiaryrpt> getAgrHistory(String dateFrom, String dateTo);
	List<Freservationdiaryrpt> getVehicleHistory(String dateFrom, String dateTo,String regno);
	String getOtherServiceList(String resno);
	List<FreservationView> loadClientHistory(String debcode);
	String getHistoryByID(String debcode);
	List<FreservationView> loadCHD(String agrno);
	List<FreservationView> loadCHD2();
	List<FreservationView> loadCAH(String debcode);
	List<FreservationView> loadCHDAlert();
	List<VehicleAvailabilityView> loadVehicleAvailability(String dfrom, String dto);
	String createCopy(String resno);
	List<FreservationView> loadCAH(String debcode, String fromDate,
			String toDate);
	List<Freservationdiaryrpt> getResDiaryDataHistory(String dateFrom,
			String dateTo, String hireTypeId);
	List<Freservationdiaryrpt> debtorReport(String dateFrom, String dateTo,
			String reportType);
}
