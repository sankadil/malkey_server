package com.dspl.malkey.view;

import com.dspl.malkey.domain.Freservation;
import com.dspl.malkey.domain.Freshed;
import com.dspl.malkey.domain.Fvehicle;

public class FreservationView extends Freservation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5740557061848817005L;
	private String dateFormaterOut;
	private String dateFormaterIn;
	private String customerType;
	private String companyid;
	private String regno;
	private String model;
	//		//r.agrno,r.resno,r.cohirestsid,r.debcode,r.hiretypeid,r.dout,r.din,r.timein,r.timeout,r.taxcomcode,r.gdout,r.gotime,v.regno

	public FreservationView()
	{
		
		
	}
	public FreservationView(Freservation freservation,Freshed freshed,Fvehicle fvehicle)
	{
		
		
	}
	
	public void setDateFormaterOut(String dateFormaterOut) {
		this.dateFormaterOut = dateFormaterOut;
	}
	public String getDateFormaterOut() {
		return dateFormaterOut;
	}

	public void setDateFormaterIn(String dateFormaterIn) {
		this.dateFormaterIn = dateFormaterIn;
	}

	public String getDateFormaterIn() {
		return dateFormaterIn;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerType() {
		return customerType;
	}
	public void setRegno(String regno) {
		this.regno = regno;
	}
	public String getRegno() {
		return regno;
	}


}
