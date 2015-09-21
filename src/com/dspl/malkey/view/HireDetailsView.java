package com.dspl.malkey.view;
import java.io.Serializable;
public class HireDetailsView  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String clientType;
	private String hireType;
	private String rateType;
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getClientType() {
		return clientType;
	}
	public void setHireType(String hireType) {
		this.hireType = hireType;
	}
	public String getHireType() {
		return hireType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	public String getRateType() {
		return rateType;
	}
	
}
