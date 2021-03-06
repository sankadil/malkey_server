package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Calendar;


/**
 * The persistent class for the fmaintstatus database table.
 * 
 */
@Entity
@Table(name="fmaintstatus")
@NamedQueries({@NamedQuery(name="FmaintstatusListAll", query="SELECT f FROM Fmaintstatus f")})

public class Fmaintstatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String statusid;

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

    public Fmaintstatus() {
    }

	public String getStatusid() {
		return this.statusid;
	}

	public void setStatusid(String statusid) {
		this.statusid = statusid;
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

}