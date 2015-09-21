package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;
import java.math.BigDecimal;


/**
 * The persistent class for the fvehiclerate database table.
 * 
 */
@Entity
@Table(name="fvehiclerate")
@NamedQueries({@NamedQuery(name="FvehiclerateListAll", query="SELECT f FROM Fvehiclerate f"),
	@NamedQuery(name="FvehiclerateListByVehiModelID", query="SELECT f FROM Fvehiclerate f WHERE f.id.vehimodelid=:vehimodelid")})

public class Fvehiclerate implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FvehicleratePK id;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(precision=15, scale=2, name="rate")
	private BigDecimal rate;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(precision=15, scale=2, name="xsmilerate")
	private BigDecimal xsmilerate;

	@Column(nullable=false, name="allotedkms")
	private int allotedkms;
	
	@Column(length=50)
	private String uuid;
	
	@Column(precision=15, scale=2, name="standardrate")
	private BigDecimal standardrate;
	
	@Column(precision=15, scale=2, name="xhourrate")
	private BigDecimal xhourrate;
	
	@Column(nullable=false, name="allotedhours")
	private int allotedhours;
	
    public Fvehiclerate() {
    }

	public FvehicleratePK getId() {
		return this.id;
	}

	public void setId(FvehicleratePK id) {
		this.id = id;
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

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public BigDecimal getXsmilerate() {
		return this.xsmilerate;
	}

	public void setXsmilerate(BigDecimal xsmilerate) {
		this.xsmilerate = xsmilerate;
	}

	public void setAllotedkms(int allotedkms) {
		this.allotedkms = allotedkms;
	}

	public int getAllotedkms() {
		return allotedkms;
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

}