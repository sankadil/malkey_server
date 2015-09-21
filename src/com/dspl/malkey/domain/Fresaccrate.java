package com.dspl.malkey.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the fresaccrate database table.
 * 
 */
@Entity
@Table(name="fresaccrate")
@NamedQueries({
	@NamedQuery(name="FresaccrateListAll123", query="SELECT f FROM Fresaccrate f"),
	@NamedQuery(name="FresaccrateListByResno", query="SELECT f FROM Fresaccrate f WHERE f.id.resno=:resno"),
	@NamedQuery(name="FresaccrateDeleteByResno", query="DELETE FROM Fresaccrate f WHERE f.id.resno=:resno")
	})
public class Fresaccrate implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FresaccratePK id;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(length=50)
	private String addmach;

	@Column(length=10)
	private String adduser;

	@Column(precision=23, scale=8)
	private BigDecimal rate;

	@Column(precision=15, scale=2)
	private BigDecimal standardrate;
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(precision=7, scale=4)
	private BigDecimal discount;
	
	@Transient
	public double total;
	
	@Column(nullable=false, length=50)
	private String uuid;
	
    public Object clone() throws CloneNotSupportedException{
        Cloneable theClone = (Fresaccrate) super.clone();
        return theClone;
    }
    
    public Fresaccrate() {
    }

	public FresaccratePK getId() {
		return this.id;
	}

	public void setId(FresaccratePK id) {
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

}