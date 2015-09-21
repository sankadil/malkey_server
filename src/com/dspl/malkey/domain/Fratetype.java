package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Calendar;


/**
 * The persistent class for the fratetype database table.
 * 
 */
@Entity
@Table(name="fratetype")
@NamedQueries({@NamedQuery(name="FratetypeListAll", query="SELECT f FROM Fratetype f")})

public class Fratetype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String ratetype;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(nullable=false, length=254)
	private String description;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;
	
	@Column(nullable=false)
	private int mindays;
	
	@Column(nullable=false)
	private int maxdays;

	@Column(nullable=false)
	private int days;
	
    public Fratetype() {
    }

	public String getRatetype() {
		return this.ratetype;
	}

	public void setRatetype(String ratetype) {
		this.ratetype = ratetype;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public int getMindays() {
		return mindays;
	}

	public void setMindays(int mindays) {
		this.mindays = mindays;
	}

	public int getMaxdays() {
		return maxdays;
	}

	public void setMaxdays(int maxdays) {
		this.maxdays = maxdays;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getDays() {
		return days;
	}

}