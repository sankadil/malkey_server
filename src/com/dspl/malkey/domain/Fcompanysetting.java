package com.dspl.malkey.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the fcompanysetting database table.
 * 
 */
@Entity
@Table(name="fcompanysetting")
@NamedQueries({ @NamedQuery(name="FcompanysettingListAll", query="SELECT d FROM Fcompanysetting d")})
public class Fcompanysetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=20)
	private String csettingscode;

	@Column(nullable=false)
	private boolean bupdateflag;

	@Column(nullable=false, length=5)
	private String ccharval;

	@Column(nullable=false, length=10)
	private String ccompanycode;

	@Column(nullable=false, length=5)
	private String clocationchar;

	@Column(nullable=false, length=50)
	private String cremarks;

	@Column(nullable=false, length=15)
	private String csettinggrp;

	@Column(nullable=false, precision=10)
	private BigDecimal nnumval;

	@Column(nullable=false, precision=1)
	private BigDecimal ntype;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	
	
	//bi-directional many-to-one association to YearMonth
	@OneToMany(mappedBy="fcompanysetting")
	private Set<Yearmonth> yearmonths;

	public Set<Yearmonth> getYearmonths() {
		return yearmonths;
	}

	public void setYearmonths(Set<Yearmonth> yearmonths) {
		this.yearmonths = yearmonths;
	}

	public Nonyearmonth getNonyearmonth() {
		return nonyearmonth;
	}

	public void setNonyearmonth(Nonyearmonth nonyearmonth) {
		this.nonyearmonth = nonyearmonth;
	}

	//bi-directional one-to-one association to NonYearMonth
	//					fcompanysetting
	@OneToOne(mappedBy="fcompanysetting")
	private Nonyearmonth nonyearmonth;
	
	
	//private Timestamp timestamp_column;

    public Fcompanysetting() {
    }

	public String getCsettingscode() {
		return this.csettingscode;
	}

	public void setCsettingscode(String csettingscode) {
		this.csettingscode = csettingscode;
	}

	public boolean getBupdateflag() {
		return this.bupdateflag;
	}

	public void setBupdateflag(boolean bupdateflag) {
		this.bupdateflag = bupdateflag;
	}

	public String getCcharval() {
		return this.ccharval;
	}

	public void setCcharval(String ccharval) {
		this.ccharval = ccharval;
	}

	public String getCcompanycode() {
		return this.ccompanycode;
	}

	public void setCcompanycode(String ccompanycode) {
		this.ccompanycode = ccompanycode;
	}

	public String getClocationchar() {
		return this.clocationchar;
	}

	public void setClocationchar(String clocationchar) {
		this.clocationchar = clocationchar;
	}

	public String getCremarks() {
		return this.cremarks;
	}

	public void setCremarks(String cremarks) {
		this.cremarks = cremarks;
	}

	public String getCsettinggrp() {
		return this.csettinggrp;
	}

	public void setCsettinggrp(String csettinggrp) {
		this.csettinggrp = csettinggrp;
	}

	public BigDecimal getNnumval() {
		return this.nnumval;
	}

	public void setNnumval(BigDecimal nnumval) {
		this.nnumval = nnumval;
	}

	public BigDecimal getNtype() {
		return this.ntype;
	}

	public void setNtype(BigDecimal ntype) {
		this.ntype = ntype;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

/*	public Timestamp getTimestamp_column() {
		return this.timestamp_column;
	}

	public void setTimestamp_column(Timestamp timestamp_column) {
		this.timestamp_column = timestamp_column;
	}*/

}