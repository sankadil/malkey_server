package com.dspl.malkey.domain;
  
import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;
import java.math.BigDecimal;


/** 
 * The persistent class for the faccrate database table.
 * 
 */
@Entity
@Table(name="faccrate")
@NamedQueries
({
	@NamedQuery(name="FaccrateListAll", query="SELECT f FROM Faccrate f"),
	@NamedQuery(name="FaccrateListByClienttype", query="SELECT NEW com.dspl.malkey.domain.Faccrate(f) FROM Faccrate f WHERE f.id.clienttype=:clienttype"),
	@NamedQuery(name="FaccrateFindByAccId", query="SELECT NEW com.dspl.malkey.domain.Faccrate(f) FROM Faccrate f WHERE f.id.accid=:accid")
	})

public class Faccrate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private FaccratePK id;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(precision=23, scale=8)
	private BigDecimal rate;
	
	@Column(precision=15, scale=2)
	private BigDecimal standardrate;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

    public Faccrate() {
    }

	public FaccratePK getId() {
		return this.id;
	}

	public void setId(FaccratePK id) {
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

	
	/***
	 * Create constructor for light weight data trasfer.set unusable fields as null bcz memory waisting
	 * 
	 * 
	 * */
	
	public Faccrate(Faccrate faccrate) {
		super();
		this.id = faccrate.id;
		this.adddate = null;
		this.addmach = null;
		this.adduser = null;
		this.rate = faccrate.rate;
		this.recordid = 0;
		this.standardrate = faccrate.standardrate;
	}

	public void setStandardrate(BigDecimal standardrate) {
		this.standardrate = standardrate;
	}

	public BigDecimal getStandardrate() {
		return standardrate;
	}
	 
}