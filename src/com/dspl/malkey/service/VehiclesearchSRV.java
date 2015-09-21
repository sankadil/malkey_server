package com.dspl.malkey.service;

import java.util.ArrayList;
import java.util.List;

import com.dspl.malkey.domain.Fvehicle;
import com.dspl.malkey.view.HireDetailsView;

import flex.messaging.io.ArrayCollection;

public interface VehiclesearchSRV {
	List<Fvehicle> searchVehicles(String dateFrom,String dateTo,ArrayList paraList,Boolean available);
	List<Fvehicle> getVehicleDet();
	ArrayCollection getResList(String dateFrom,String dateTo,ArrayList paraList);
	List<Fvehicle> advancedSearchVehicles(String dateFrom, String dateTo,
			HireDetailsView hireDetailsView, Boolean available);
}
