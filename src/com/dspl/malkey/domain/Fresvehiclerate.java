package com.dspl.malkey.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the fresvehiclerate database table.
 * 
 */
@Entity
@Table(name="fresvehiclerate")
@NamedQueries({
	@NamedQuery(name="FresvehiclerateListAll", query="SELECT f FROM Fresvehiclerate f"),
	@NamedQuery(name="FresvehiclerateListByResno", query="SELECT f FROM Fresvehiclerate f WHERE f.resno=:resno"),
	@NamedQuery(name="FresvehiclerateDeleteByResno", query="DELETE FROM Fresvehiclerate f WHERE f.resno=:resno")
	})

public class Fresvehiclerate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=13)
	private String resno;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar  adddate;

	@Column(length=50)
	private String addmach;

	@Column(length=10)
	private String adduser;

	private int allotedkms;

	@Column(nullable=false, length=10)
	private String clienttype;

	@Column(nullable=false, length=10)
	private String hiretypeid;

	@Column(precision=23, scale=8)
	private BigDecimal rate;

	@Column(nullable=false, length=10)
	private String ratetype;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(nullable=false, length=10)
	private String vehimodelid;

	@Column(precision=15, scale=2)
	private BigDecimal xsmilerate;
	
	@Column(precision=7, scale=4)
	private BigDecimal discount;
	
	@Transient
	public double total;
	
	@Column(nullable=false, length=50)
	private String uuid;
	
	@Column(precision=15, scale=2)
	private BigDecimal standardrate;
	
	@Column(precision=15, scale=2, name="xhourrate")
	private BigDecimal xhourrate;
	
	@Column(nullable=false, name="allotedhours")
	private int allotedhours;
	
	
	@Column(precision=7, scale=4)
	private BigDecimal discountxhourrate;
	
    public Fresvehiclerate() {
    }

	public String getResno() {
		return this.resno;
	}

	public void setResno(String resno) {
		this.resno = resno;
	}

	public Calendar getAdddate() {
		return this.adddate;
	}

	public void setAdddate(Calendar adddate) {
		this.adddate = adddate;
	}

	public String getAddmach() {
		return this.addmach;
	}

	public void setAddmach(String addmach) {
		this.addmach = addmach;
	}

	public String getAdduser() {
		return this.adduser;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}

	public int getAllotedkms() {
		return this.allotedkms;
	}

	public void setAllotedkms(int allotedkms) {
		this.allotedkms = allotedkms;
	}

	public String getClienttype() {
		return this.clienttype;
	}

	public void setClienttype(String clienttype) {
		this.clienttype = clienttype;
	}

	public String getHiretypeid() {
		return this.hiretypeid;
	}

	public void setHiretypeid(String hiretypeid) {
		this.hiretypeid = hiretypeid;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getRatetype() {
		return this.ratetype;
	}

	public void setRatetype(String ratetype) {
		this.ratetype = ratetype;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getVehimodelid() {
		return this.vehimodelid;
	}

	public void setVehimodelid(String vehimodelid) {
		this.vehimodelid = vehimodelid;
	}

	public BigDecimal getXsmilerate() {
		return this.xsmilerate;
	}

	public void setXsmilerate(BigDecimal xsmilerate) {
		this.xsmilerate = xsmilerate;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}
	public void setStandardrate(BigDecimal standardrate) {
		this.standardrate = standardrate;
	}

	public BigDecimal getStandardrate() {
		return standardrate;
	}

	public void setXhourrate(BigDecimal xhourrate) {
		this.xhourrate = xhourrate;
	}

	public BigDecimal getXhourrate() {
		return xhourrate;
	}

	public void setAllotedhours(int allotedhours) {
		this.allotedhours = allotedhours;
	}

	public int getAllotedhours() {
		return allotedhours;
	}

	public void setDiscountxhourrate(BigDecimal discountxhourrate) {
		this.discountxhourrate = discountxhourrate;
	}

	public BigDecimal getDiscountxhourrate() {
		return discountxhourrate;
	}

}