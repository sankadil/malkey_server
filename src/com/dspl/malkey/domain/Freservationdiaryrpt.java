package com.dspl.malkey.domain;

import java.sql.Timestamp;

public class Freservationdiaryrpt {
	private Timestamp dout;
	private Timestamp din;
	private int noofday;
	private String resno;
	private String agrno;
	private String debname;
	private String debadd;
	private String itinerary;
	private String tel; 
	private String regno;
	private String make;
	private String model;
	private String driver; 
	private String gotime;
	private String hiretype;
	private String comileage;
	private String cimileage;
	private String cofuellevel;
	private String cifuellevel;
	
	private int totalmileage;
	private String hirestatus;
	public void setDout(Timestamp dout) {
		this.dout = dout;
	}
	public Timestamp getDout() {
		return dout;
	}
	public void setResno(String resno) {
		this.resno = resno;
	}
	public String getResno() {
		return resno;
	}
	public void setDebname(String debname) {
		this.debname = debname;
	}
	public String getDebname() {
		return debname;
	}
	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}
	public String getItinerary() {
		return itinerary;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTel() {
		return tel;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getMake() {
		return make;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getModel() {
		return model;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getDriver() {
		return driver;
	}
	public void setGotime(String gotime) {
		this.gotime = gotime;
	}
	public String getGotime() {
		return gotime;
	}
	public void setHiretype(String hiretype) {
		this.hiretype = hiretype;
	}
	public String getHiretype() {
		return hiretype;
	}
	public void setRegno(String regno) {
		this.regno = regno;
	}
	public String getRegno() {
		return regno;
	}
	public void setDin(Timestamp din) {
		this.din = din;
	}
	public Timestamp getDin() {
		return din;
	}
	public void setNoofday(int noofday) {
		this.noofday = noofday;
	}
	public int getNoofday() {
		return noofday;
	}
	public void setDebadd(String debadd) {
		this.debadd = debadd;
	}
	public String getDebadd() {
		return debadd;
	}
	public void setHirestatus(String hirestatus) {
		this.hirestatus = hirestatus;
	}
	public String getHirestatus() {
		return hirestatus;
	}
	public void setTotalmileage(int totalmileage) {
		this.totalmileage = totalmileage;
	}
	public int getTotalmileage() {
		return totalmileage;
	}
	public void setAgrno(String agrno) {
		this.agrno = agrno;
	}
	public String getAgrno() {
		return agrno;
	}
	public void setComileage(String comileage) {
		this.comileage = comileage;
	}
	public String getComileage() {
		return comileage;
	}
	public void setCimileage(String cimileage) {
		this.cimileage = cimileage;
	}
	public String getCimileage() {
		return cimileage;
	}
	public void setCofuellevel(String cofuellevel) {
		this.cofuellevel = cofuellevel;
	}
	public String getCofuellevel() {
		return cofuellevel;
	}
	public void setCifuellevel(String cifuellevel) {
		this.cifuellevel = cifuellevel;
	}
	public String getCifuellevel() {
		return cifuellevel;
	}
}
