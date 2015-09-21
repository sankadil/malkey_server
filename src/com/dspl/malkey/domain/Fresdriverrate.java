package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.math.BigDecimal;


/**
 * The persistent class for the fresdriverrate database table.
 * 
 */
@Entity
@Table(name="fresdriverrate")
@NamedQueries({
	@NamedQuery(name="FresdriverrateListAll", 		query="SELECT f FROM Fresdriverrate f"),
	@NamedQuery(name="FresdriverrateDeleteByResno", query="DELETE FROM Fresdriverrate f WHERE f.id.resno=:resno"),
	@NamedQuery(name="FresdriverrateByResno", 		query="SELECT f FROM Fresdriverrate f WHERE f.id.resno=:resno")
	})

public class Fresdriverrate implements Serializable,Cloneable  {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FresdriverratePK id;

	@Column(precision=18)
	private BigDecimal addcharges;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar  adddate;

	@Column(length=50)
	private String addmach;

	@Column(length=10)
	private String adduser;



	@Column(precision=18)
	private BigDecimal nightoutrate;

	@Column(precision=18)
	private BigDecimal otrate;

	@Column(precision=18)
	private BigDecimal rate;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(precision=7, scale=2)
	private BigDecimal discount_nightout;
	
	@Column(precision=7, scale=2)
	private BigDecimal discount_detention;
	
	@Column(nullable=false, length=50)
	private String uuid;
	
	@Transient
	public double total;
	
    public Object clone() throws CloneNotSupportedException{
        Cloneable theClone = (Fresdriverrate) super.clone();
        return theClone;
    }
    
    public Fresdriverrate() {
    }

	public FresdriverratePK getId() {
		return this.id;
	}

	public void setId(FresdriverratePK id) {
		this.id = id;
	}
	
	public BigDecimal getAddcharges() {
		return this.addcharges;
	}

	public void setAddcharges(BigDecimal addcharges) {
		this.addcharges = addcharges;
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

	public BigDecimal getNightoutrate() {
		return this.nightoutrate;
	}

	public void setNightoutrate(BigDecimal nightoutrate) {
		this.nightoutrate = nightoutrate;
	}

	public BigDecimal getOtrate() {
		return this.otrate;
	}

	public void setOtrate(BigDecimal otrate) {
		this.otrate = otrate;
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

	public void setDiscount_nightout(BigDecimal discount_nightout) {
		this.discount_nightout = discount_nightout;
	}

	public BigDecimal getDiscount_nightout() {
		return discount_nightout;
	}

	public void setDiscount_detention(BigDecimal discount_detention) {
		this.discount_detention = discount_detention;
	}

	public BigDecimal getDiscount_detention() {
		return discount_detention;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}



}