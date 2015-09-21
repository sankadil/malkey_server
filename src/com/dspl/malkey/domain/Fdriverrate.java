package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Calendar;
import java.math.BigDecimal;


/**
 * The persistent class for the fdriverrate database table.
 * 
 */
@Entity
@Table(name="fdriverrate")
@NamedQueries({@NamedQuery(name="FdriverrateListAll", query="SELECT f FROM Fdriverrate f")})

public class Fdriverrate implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FdriverratePK id;

	@Column(precision=15, scale=2)
	private BigDecimal addcharges;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(precision=15, scale=2)
	private BigDecimal nightoutrate;

	@Column(precision=15, scale=2)
	private BigDecimal otrate;

	@Column(precision=15, scale=2)
	private BigDecimal rate;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

    public Fdriverrate() {
    }

	public FdriverratePK getId() {
		return this.id;
	}

	public void setId(FdriverratePK id) {
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

}