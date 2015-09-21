package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the NonYearMonth database table.
 * 
 */
@Entity
@Table(name="nonyearmonth")
public class Nonyearmonth implements Serializable {
	private static final long serialVersionUID = 1L;

	public String getCsettingscode() {
		return csettingscode;
	}

	public void setCsettingscode(String csettingscode) {
		this.csettingscode = csettingscode;
	}

	public int getNnumval() {
		return nnumval;
	}

	public void setNnumval(int nnumval) {
		this.nnumval = nnumval;
	}

	public int getRecordid() {
		return recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public Fcompanysetting getFcompanysetting() {
		return fcompanysetting;
	}

	public void setFcompanysetting(Fcompanysetting fcompanysetting) {
		this.fcompanysetting = fcompanysetting;
	}

	@Id
	private String csettingscode;

	private int nnumval;
	
	@Column(name="recordid",insertable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int recordid;


	//bi-directional one-to-one association to FCompanySetting
	@OneToOne
	@JoinColumn(name="csettingscode", insertable=false, updatable=false)
	private Fcompanysetting fcompanysetting;

    public Nonyearmonth() {
    }


	
}