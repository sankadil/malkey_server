package com.dspl.malkey.domain;

import java.sql.Timestamp;

public class Fexpirationlistingrpt {
	private String regno;
	private String make;
	private String model;
	private Timestamp periodfrom;
	private Timestamp periodto;
	private int daysleft;
	private String ownership;
	private String rentstatus;
	public void setRegno(String regno) {
		this.regno = regno;
	}
	public String getRegno() {
		return regno;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getMake() {
		return make;
	}
	public void setPeriodfrom(Timestamp periodfrom) {
		this.periodfrom = periodfrom;
	}
	public Timestamp getPeriodfrom() {
		return periodfrom;
	}
	public void setPeriodto(Timestamp periodto) {
		this.periodto = periodto;
	}
	public Timestamp getPeriodto() {
		return periodto;
	}
	public void setDaysleft(int daysleft) {
		this.daysleft = daysleft;
	}
	public int getDaysleft() {
		return daysleft;
	}
	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}
	public String getOwnership() {
		return ownership;
	}
	public void setRentstatus(String rentstatus) {
		this.rentstatus = rentstatus;
	}
	public String getRentstatus() {
		return rentstatus;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getModel() {
		return model;
	}
}
