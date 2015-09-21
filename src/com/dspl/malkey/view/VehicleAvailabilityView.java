package com.dspl.malkey.view;

import java.util.ArrayList;
import java.util.List;

public class VehicleAvailabilityView {

	private String regno,vehimakeid,vehimodelid,mainseats,year,fueltypeid,vehitransid,colourid;
	private List<String> availabilityCalander = new ArrayList<String>();
	
	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getVehimakeid() {
		return vehimakeid;
	}

	public void setVehimakeid(String vehimakeid) {
		this.vehimakeid = vehimakeid;
	}

	public String getVehimodelid() {
		return vehimodelid;
	}

	public void setVehimodelid(String vehimodelid) {
		this.vehimodelid = vehimodelid;
	}

	public String getMainseats() {
		return mainseats;
	}

	public void setMainseats(String mainseats) {
		this.mainseats = mainseats;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getFueltypeid() {
		return fueltypeid;
	}

	public void setFueltypeid(String fueltypeid) {
		this.fueltypeid = fueltypeid;
	}

	public String getVehitransid() {
		return vehitransid;
	}

	public void setVehitransid(String vehitransid) {
		this.vehitransid = vehitransid;
	}

	public String getColourid() {
		return colourid;
	}

	public void setColourid(String colourid) {
		this.colourid = colourid;
	}

	public void setAvailabilityCalander(List<String> availabilityCalander) {
		this.availabilityCalander = availabilityCalander;
	}

	public List<String> getAvailabilityCalander() {
		return availabilityCalander;
	}

}
