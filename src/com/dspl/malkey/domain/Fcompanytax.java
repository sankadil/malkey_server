package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;


/**
 * The persistent class for the fcompanytax database table.
 * 
 */
@Entity
@Table(name="fcompanytax")
@NamedQueries({@NamedQuery(name="FcompanytaxListAll", query="SELECT f FROM Fcompanytax f")})
public class Fcompanytax implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient
	private String company;
	
	@Transient
	private String hiretype;
	
	@Transient
	private String taxcombination;
	
	
	@EmbeddedId
	private FcompanytaxPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="recordid",nullable=false,updatable=false)
	private int recordid;

	@Column(nullable=false, length=10)
	private String taxcomcode;

    public Fcompanytax() {
    }

	public FcompanytaxPK getId() {
		return this.id;
	}

	public void setId(FcompanytaxPK id) {
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

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getTaxcomcode() {
		return this.taxcomcode;
	}

	public void setTaxcomcode(String taxcomcode) {
		this.taxcomcode = taxcomcode;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
		return company;
	}

	public void setHiretype(String hiretype) {
		this.hiretype = hiretype;
	}

	public String getHiretype() {
		return hiretype;
	}

	public void setTaxcombination(String taxcombination) {
		this.taxcombination = taxcombination;
	}

	public String getTaxcombination() {
		return taxcombination;
	}

}