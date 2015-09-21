package com.dspl.malkey.dao;

import java.util.ArrayList;
import java.util.List;

import com.dspl.malkey.domain.Fvehicle;
import com.dspl.malkey.view.HireDetailsView;

import flex.messaging.io.ArrayCollection;

public interface VehiclesearchDAO {
	List<Fvehicle> searchVehicles(String dateFrom,String dateTo,ArrayList paraList,Boolean available);
	List<Fvehicle> getVehicleDet();
	ArrayCollection getResList(String dateFrom,String dateTo,ArrayList paraList);
	int getAvailability(java.util.Calendar dateFrom, java.util.Calendar dateTo, String regno);
	int getAvailabilityByResNo(java.util.Calendar dateFrom, java.util.Calendar dateTo,String regno,String resno);
	List<String> getUnAvailabileListByResNo(java.util.Calendar dateFrom, java.util.Calendar dateTo,String regno,String resno);
	List<Fvehicle> advancedSearchVehicles(String dateFrom, String dateTo,
			HireDetailsView hireDetailsView, Boolean available);
}
