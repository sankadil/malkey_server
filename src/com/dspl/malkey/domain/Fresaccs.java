package com.dspl.malkey.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the fresaccs database table.
 * 
 */
@Entity
@Table(name="fresaccs")
@NamedQueries({
	@NamedQuery(name="FresaccsListAll", query="SELECT f FROM Fresaccs f"),
	@NamedQuery(name="FresaccsDeleteByResno", query="DELETE FROM Fresaccs f WHERE f.id.resno=:resno"),
	/*@NamedQuery(name="FresaccsListByresNo1", query="SELECT f FROM Fresaccs f WHERE f.id.resno=:resno ORDER BY f.id.accid ASC"),not in use following query is the used one.*/
	@NamedQuery(name="FresaccsListByresNo", query="SELECT NEW com.dspl.malkey.domain.Fresaccs(f,r) FROM Fresaccs f,Fresaccrate r WHERE (f.id.resno=:resno AND r.id.resno=:resno AND f.id.accid=r.id.accid AND f.id.dfrom=r.id.dfrom AND f.id.dto=r.id.dto) ORDER BY f.id.accid ASC")
	})

public class Fresaccs implements Serializable,Cloneable  {
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
	private FresaccsPK id;	

	@Column(nullable=false)
	private int noofdays;
	
	@Column(nullable=false)
	private int qty;
	
	@Column(precision=23, scale=8)
	private BigDecimal rate;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(length=100)
	private String remarks;
	
//	@Transient
//	private String ratetype;

//	@Transient
//	private BigDecimal discount;
	
	@Transient
	private Fresaccrate fresaccrate;
	
	@Column(nullable=false, length=10)
	private String adduser;
	
	@Column(nullable=false, length=50)
	private String uuid;
	
    public Object clone() throws CloneNotSupportedException{
        Cloneable theClone = (Fresaccs) super.clone();
        return theClone;
    }
    
    public Fresaccs() {
    }
    
    public Fresaccs(Fresaccs f,Fresaccrate fresaccrate) {
    	this.id=f.id;
    	this.noofdays=f.noofdays;
    	this.qty=f.qty;
    	this.rate=f.rate;
    	this.recordid=f.recordid;
    	this.remarks=f.remarks;
    	//this.ratetype=f.ratetype;
    	//this.discount=f.discount;
    	this.fresaccrate=fresaccrate;
    }

	public FresaccsPK getId() {
		return this.id;
	}

	public void setId(FresaccsPK id) {
		this.id = id;
	}
	

	public int getNoofdays() {
		return this.noofdays;
	}

	public void setNoofdays(int noofdays) {
		this.noofdays = noofdays;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getQty() {
		return qty;
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

/*	public void setRatetype(String ratetype) {
		this.ratetype = ratetype;
	}

	public String getRatetype() {
		return ratetype;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}*/

	public void setFresaccrate(Fresaccrate fresaccrate) {
		this.fresaccrate = fresaccrate;
	}

	public Fresaccrate getFresaccrate() {
		return fresaccrate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getDiscount() {
		return discount;
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

	public void setRatetype(String ratetype) {
		this.ratetype = ratetype;
	}

	public String getRatetype() {
		return ratetype;
	}

	public void setStandardrate(BigDecimal standardrate) {
		this.standardrate = standardrate;
	}

	public BigDecimal getStandardrate() {
		return standardrate;
	}

	/*	@Column(length=11)
	private String tfrom;

	@Column(length=11)
	private String tto;*/
	
	
/*	public String getTfrom() {
		return this.tfrom;
	}

	public void setTfrom(String tfrom) {
		this.tfrom = tfrom;
	}

	public String getTto() {
		return this.tto;
	}

	public void setTto(String tto) {
		this.tto = tto;
	}*/
}