package com.dspl.malkey.report;

import java.io.Serializable;
import java.util.List;

import com.dspl.malkey.domain.Fresaccs;
import com.dspl.malkey.domain.Fresaddcharge;
import com.dspl.malkey.domain.Fresclientdriver;
import com.dspl.malkey.domain.Fresdriver;
import com.dspl.malkey.domain.Fresdriverrate;
import com.dspl.malkey.domain.Freservation;
import com.dspl.malkey.domain.Fresothsrv;
import com.dspl.malkey.domain.Fresvehicle;
import com.dspl.malkey.domain.Fresvehicledamage;
import com.dspl.malkey.domain.Fresvehinv;

public class Reservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Fresvehicle> fresvehicle;
	private Freservation freservation;
	private List<Fresaccs> fresaccs;
	private List<Fresothsrv> fresothsrv;
	private List<Fresdriver> fresdriver;
	private List<Fresclientdriver> fresclientdriver;
	private List<Fresaddcharge> fresaddcharge;
	private List<Fresvehinv> fresvehinv;
	private List<Fresvehicledamage> fresvehicledamage;
	private Fresdriverrate fresdriverrate;
	
	public void setFresvehicle(List<Fresvehicle> fresvehicle) {
		this.fresvehicle = fresvehicle;
	}
	public List<Fresvehicle> getFresvehicle() {
		return fresvehicle;
	}
	public void setFreservation(Freservation freservation) {
		this.freservation = freservation;
	}
	public Freservation getFreservation() {
		return freservation;
	}
	public void setFresothsrv(List<Fresothsrv> fresothsrv) {
		this.fresothsrv = fresothsrv;
	}
	public List<Fresothsrv> getFresothsrv() {
		return fresothsrv;
	}
	public void setFresdriver(List<Fresdriver> fresdriver) {
		this.fresdriver = fresdriver;
	}
	public List<Fresdriver> getFresdriver() {
		return fresdriver;
	}
	public void setFresaddcharge(List<Fresaddcharge> fresaddcharge) {
		this.fresaddcharge = fresaddcharge;
	}
	public List<Fresaddcharge> getFresaddcharge() {
		return fresaddcharge;
	}
	public void setFresaccs(List<Fresaccs> fresaccs) {
		this.fresaccs = fresaccs;
	}
	public List<Fresaccs> getFresaccs() {
		return fresaccs;
	}
	public void setFresdriverrate(Fresdriverrate fresdriverrate) {
		this.fresdriverrate = fresdriverrate;
	}
	public Fresdriverrate getFresdriverrate() {
		return fresdriverrate;
	}
	public void setFresvehinv(List<Fresvehinv> fresvehinv) {
		this.fresvehinv = fresvehinv;
	}
	public List<Fresvehinv> getFresvehinv() {
		return fresvehinv;
	}
	public void setFresvehicledamage(List<Fresvehicledamage> fresvehicledamage) {
		this.fresvehicledamage = fresvehicledamage;
	}
	public List<Fresvehicledamage> getFresvehicledamage() {
		return fresvehicledamage;
	}
	public void setFresclientdriver(List<Fresclientdriver> fresclientdriver) {
		this.fresclientdriver = fresclientdriver;
	}
	public List<Fresclientdriver> getFresclientdriver() {
		return fresclientdriver;
	}
	
}
