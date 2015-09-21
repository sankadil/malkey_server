package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the fresvehicledamage database table.
 * 
 */
@Entity
@Table(name="fresvehicledamage")
@NamedQueries(
		{
			@NamedQuery(name="FresvehicledamageListAll", 		query="SELECT f FROM Fresvehicledamage f"),
			@NamedQuery(name="FresvehicledamageListByRegNo", 	query="SELECT f FROM Fresvehicledamage f WHERE f.resno=:resno AND f.regno=:regno ORDER BY f.seq"),
			@NamedQuery(name="FresvehicledamagedeleteByResNo", 	query="DELETE  FROM Fresvehicledamage f WHERE f.resno=:resno"),
			@NamedQuery(name="FresvehicledamagedeleteByRegNo", 	query="DELETE  FROM Fresvehicledamage f WHERE f.resno=:resno AND f.regno=:regno"),
			@NamedQuery(name="FresvehicledamagedeleteByRegNoIO", 	query="DELETE  FROM Fresvehicledamage f WHERE f.resno=:resno AND f.regno=:regno AND f.ioflag=:ioflag")
	})
public class Fresvehicledamage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true,nullable=false,updatable=false)
	private int recordid;

	private int damagetype;

	private int ioflag;

	@Column(length=10)
	private String regno;

	@Column(length=13)
	private String resno;

	private int seq;

	@Column(precision=18)
	private BigDecimal xvalue;

	@Column(precision=18)
	private BigDecimal yvalue;
	
	@Column(nullable=false, length=10)
	private String adduser;
	
	@Column(nullable=false, length=50)
	private String uuid;

    public Fresvehicledamage() {
    }

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public int getDamagetype() {
		return this.damagetype;
	}

	public void setDamagetype(int damagetype) {
		this.damagetype = damagetype;
	}

	public int getIoflag() {
		return this.ioflag;
	}

	public void setIoflag(int ioflag) {
		this.ioflag = ioflag;
	}

	public String getRegno() {
		return this.regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getResno() {
		return this.resno;
	}

	public void setResno(String resno) {
		this.resno = resno;
	}

	public int getSeq() {
		return this.seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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