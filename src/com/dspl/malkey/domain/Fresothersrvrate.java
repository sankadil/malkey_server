package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.math.BigDecimal;


/**
 * The persistent class for the fresothersrvrate database table.
 * 
 */
@Entity
@Table(name="fresothersrvrate")
@NamedQueries({
	@NamedQuery(name="FresothersrvrateListAll", query="SELECT f FROM Fresothersrvrate f"),
		@NamedQuery(name="FresothersrvrateListByResno", query="SELECT f FROM Fresothersrvrate f WHERE f.id.resno=:resno"),
		@NamedQuery(name="FresothersrvrateDeleteByResno", query="DELETE FROM Fresothersrvrate f WHERE f.id.resno=:resno")
	})

public class Fresothersrvrate implements Serializable,Cloneable  {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FresothersrvratePK id;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar  adddate;

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
	
	@Column(nullable=false, length=50)
	private String uuid;
	
	@Transient
	public double total;
	
    public Object clone() throws CloneNotSupportedException{
        Cloneable theClone =( Fresothersrvrate) super.clone();
        return theClone;
    }
    
    public Fresothersrvrate() {
    }

	public FresothersrvratePK getId() {
		return this.id;
	}

	public void setId(FresothersrvratePK id) {
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