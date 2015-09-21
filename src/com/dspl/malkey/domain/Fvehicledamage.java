package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the fvehicledamage database table.
 * 
 */
@Entity
@Table(name="fvehicledamage")
@NamedQueries({
	@NamedQuery(name="FvehicledamageListAll", 		query="SELECT f FROM Fvehicledamage f"),
	@NamedQuery(name="FvehicledamageListByRegNo", 	query="SELECT f FROM Fvehicledamage f WHERE f.regno=:regno ORDER BY f.seq"),
	@NamedQuery(name="FvehicledamagedeleteByRegNo", query="DELETE  FROM Fvehicledamage f WHERE f.regno=:regno")
	})
public class Fvehicledamage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false, length=10)
	private String regno;

	@Column(nullable=false)
	private int damagetype;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(nullable=false)
	private int seq;

	@Column(precision=15, scale=2)
	private BigDecimal xvalue;

	@Column(precision=15, scale=2)
	private BigDecimal yvalue;
	
	@Column(nullable=false, length=10)
	private String adduser;
	
	@Column(nullable=false, length=50)
	private String uuid;

	
    public Fvehicledamage() {
    }

	public String getRegno() {
		return this.regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public int getDamagetype() {
		return this.damagetype;
	}

	public void setDamagetype(int damagetype) {
		this.damagetype = damagetype;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getSeq() {
		return seq;
	}

	public BigDecimal getXvalue() {
		return this.xvalue;
	}

	public void setXvalue(BigDecimal xvalue) {
		this.xvalue = xvalue;
	}

	public BigDecimal getYvalue() {
		return this.yvalue;
	}

	public void setYvalue(BigDecimal yvalue) {
		this.yvalue = yvalue;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}

	public String getAdduser() {
		return adduser;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

}