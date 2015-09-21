package com.dspl.malkey.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.VehiclesearchDAO;
import com.dspl.malkey.domain.Fvehicle;
import com.dspl.malkey.view.HireDetailsView;

import flex.messaging.io.ArrayCollection;

public class VehiclesearchSRVImpl implements VehiclesearchSRV {

	@Resource(name="vehiclesearchDAO")
	VehiclesearchDAO vehiclesearchDAO;
	
	@Override
	public List<Fvehicle> searchVehicles(String dateFrom, String dateTo, ArrayList paraList,Boolean available) 
	{
		return vehiclesearchDAO.searchVehicles(dateFrom, dateTo, paraList,available);
	}

	@Override
	public List<Fvehicle> getVehicleDet() 
	{
		return vehiclesearchDAO.getVehicleDet();
	}

	@Override
	public ArrayCollection getResList(String dateFrom, String dateTo, ArrayList paraList) {
		return vehiclesearchDAO.getResList(dateFrom, dateTo, paraList);
	}

	@Override
	public List<Fvehicle> advancedSearchVehicles(String dateFrom,
			String dateTo, HireDetailsView hireDetailsView, Boolean available) {
		return vehiclesearchDAO.advancedSearchVehicles(dateFrom, dateTo, hireDetailsView, available);
	}

	
}
