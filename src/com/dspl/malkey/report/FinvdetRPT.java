package com.dspl.malkey.report;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Transient;

public class FinvdetRPT implements Cloneable {
	private String resno;
	private String hiretypeid;
	private String hiretype;
	private Timestamp dfrom;
	private Timestamp dto;
	private String description;
	private String regno;
	private BigDecimal rate;
	private BigDecimal km;
	private BigDecimal days;
	private BigDecimal qty;
	private BigDecimal amount;
	private int taxFlag;//tax1 nontax0
	
	@Transient
	private String taxOrderText;
	
	public void setResno(String resno) {
		this.resno = resno;
	}
	public String getResno() {
		return resno;
	}
	public void setHiretypeid(String hiretypeid) {
		this.hiretypeid = hiretypeid;
	}
	public String getHiretypeid() {
		return hiretypeid;
	}
	public void setHiretype(String hiretype) {
		this.hiretype = hiretype;
	}
	public String getHiretype() {
		return hiretype;
	}
	public void setDfrom(Timestamp dfrom) {
		this.dfrom = dfrom;
	}
	public Timestamp getDfrom() {
		return dfrom;
	}
	public void setDto(Timestamp dto) {
		this.dto = dto;
	}
	public Timestamp getDto() {
		return dto;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setRegno(String regno) {
		this.regno = regno;
	}
	public String getRegno() {
		return regno;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setKm(BigDecimal km) {
		this.km = km;
	}
	public BigDecimal getKm() {
		return km;
	}
	public void setDays(BigDecimal days) {
		this.days = days;
	}
	public BigDecimal getDays() {
		return days;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getAmount() {
		return amount;
	}

	
	@Override
	public FinvdetRPT clone() throws CloneNotSupportedException {
	return (FinvdetRPT)super.clone();
	}
	public void setTaxFlag(int taxFlag) {
		this.taxFlag = taxFlag;
	}
	public int getTaxFlag() {
		return taxFlag;
	}
	public void setTaxOrderText(String taxOrderText) {
		this.taxOrderText = taxOrderText;
	}
	public String getTaxOrderText() {
		return taxOrderText;
	}
	
}
