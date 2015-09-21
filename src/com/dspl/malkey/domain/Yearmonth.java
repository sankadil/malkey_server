package com.dspl.malkey.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the YearMonth database table.
 * 
 */
@Entity
@Table(name="yearmonth")
public class Yearmonth implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private YearmonthPK id;

	private int nnumval;
	
	public int getNnumval() {
		return nnumval;
	}

	public void setNnumval(int nnumval) {
		this.nnumval = nnumval;
	}

	@Column(name="recordid",insertable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int recordid;

	//private int version;

	//bi-directional many-to-one association to FCompanySetting
    @ManyToOne
	@JoinColumn(name="csettingscode", insertable=false, updatable=false)
	private Fcompanysetting fcompanysetting;

    public Fcompanysetting getFcompanysetting() {
		return fcompanysetting;
	}

	public void setFcompanysetting(Fcompanysetting fcompanysetting) {
		this.fcompanysetting = fcompanysetting;
	}

	public Yearmonth() {
    }

	public YearmonthPK getId() {
		return this.id;
	}

	public void setId(YearmonthPK id) {
		this.id = id;
	}
	
	public int getNNumVal() {
		return this.nnumval;
	}

	public void setNNumVal(int nNumVal) {
		this.nnumval = nNumVal;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

/*	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Fcompanysetting getFcompanySetting() {
		return this.fcompanySetting;
	}

	public void setFcompanySetting(Fcompanysetting fcompanySetting) {
		this.fcompanySetting = fcompanySetting;
	}
	*/
}