package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Calendar;


/**
 * The persistent class for the faddcharge database table.
 * 
 */
@Entity
@Table(name="faddcharge")
@NamedQueries({@NamedQuery(name="FaddchargeListAll", query="SELECT f FROM Faddcharge f")})

public class Faddcharge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String addchargeid;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(length=1)
	private String ctype;
	
	@Column(length=50)
	private String addmach;

	@Column(length=10)
	private String adduser;

	@Column(precision=15, scale=2)
	private BigDecimal amount;

	@Column(length=254)
	private String description;

	@Column(precision=2, scale=2)
	private BigDecimal percentage;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Transient
	private Boolean isSelected;
	
    public Faddcharge() {
    }

	public String getAddchargeid() {
		return this.addchargeid;
	}

	public void setAddchargeid(String addchargeid) {
		this.addchargeid = addchargeid;
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

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPercentage() {
		return this.percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getCtype() {
		return ctype;
	}

}