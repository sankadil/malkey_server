package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;
import java.math.BigDecimal;


/**
 * The persistent class for the fresothsrv database table.
 * 
 */
@Entity
@Table(name="fresothsrv")
@NamedQueries({
	@NamedQuery(name="FresothsrvListAll", query="SELECT f FROM Fresothsrv f"),
	@NamedQuery(name="FresothsrvDeleteByResno", query="DELETE FROM Fresothsrv f WHERE f.id.resno=:resno"),
/*	@NamedQuery(name="FresothsrvListAllByResNo", query="SELECT f FROM Fresothsrv f WHERE f.id.resno=:resno ORDER BY f.id.srvid ASC")*/
	@NamedQuery(name="FresothsrvListAllByResNo", query="SELECT NEW com.dspl.malkey.domain.Fresothsrv(f,r) FROM Fresothsrv f,Fresothersrvrate r WHERE f.id.resno=:resno AND f.id.resno=r.id.resno AND f.id.srvid=r.id.srvid AND f.id.dfrom=r.id.dfrom AND f.id.dto=r.id.dto AND f.id.timeout=r.id.timeout AND f.id.timein=r.id.timein ORDER BY f.id.dfrom,f.id.timeout ASC")
	})

public class Fresothsrv implements Serializable,Cloneable  {
	private static final long serialVersionUID = 1L;

	@Transient
	private String description;
	
	@Transient
	private BigDecimal discount;
	
	@Transient
	private String ratetype;
	
	@Transient
	private BigDecimal standardrate;
	
	@EmbeddedId
	private FresothsrvPK id;


	
	@Column(nullable=false)
	private int noofdays;

	@Column(precision=15, scale=2)
	private BigDecimal rate;


	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(length=100)
	private String remarks;

	@Transient
	private Fresothersrvrate fresothersrvrate;
	
	@Column(nullable=false, length=10)
	private String adduser;
	
	@Column(nullable=false, length=50)
	private String uuid;
	
    public Object clone() throws CloneNotSupportedException{
        Cloneable theClone = (Fresothsrv) super.clone();
        return theClone;
    }
    
    public Fresothsrv() {
    }
    
    public Fresothsrv(Fresothsrv f,Fresothersrvrate fresothersrvrate) {
    	this.id=f.id;
    	//this.dfrom=f.dfrom;
    	//this.dto=f.dto;
    	this.noofdays=f.noofdays;
    	this.rate=f.rate;
    	this.ratetype=f.ratetype;
    	this.recordid=f.recordid;
    	this.remarks=f.remarks;
    	//this.timein=f.timein;
    	//this.timeout=f.timeout;
//    	this.tfrommin=f.tfrommin;
    	this.fresothersrvrate=fresothersrvrate;
    	
    }

	public FresothsrvPK getId() {
		return this.id;
	}

	public void setId(FresothsrvPK id) {
		this.id = id;
	}
	


	public int getNoofdays() {
		return this.noofdays;
	}

	public void setNoofdays(int noofdays) {
		this.noofdays = noofdays;
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

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setRatetype(String ratetype) {
		this.ratetype = ratetype;
	}

	public String getRatetype() {
		return ratetype;
	}

	public void setFresothersrvrate(Fresothersrvrate fresothersrvrate) {
		this.fresothersrvrate = fresothersrvrate;
	}

	public Fresothersrvrate getFresothersrvrate() {
		return fresothersrvrate;
	}



	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
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

	public void setStandardrate(BigDecimal standardrate) {
		this.standardrate = standardrate;
	}

	public BigDecimal getStandardrate() {
		return standardrate;
	}


	

	
}