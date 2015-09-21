package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;


/**
 * The persistent class for the fusertype database table.
 * 
 */
@Entity
@Table(name="fusertype")
@NamedQueries({@NamedQuery(name="FusertypeListAll", query="SELECT f FROM Fusertype f")})
public class Fusertype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String usertypeid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=50)
	private String adduser;

	@Column(nullable=false, length=254)
	private String description;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="recordid",nullable=false,updatable=false)
	private int recordid;

    public Fusertype() {
    }

	public String getUsertypeid() {
		return this.usertypeid;
	}

	public void setUsertypeid(String usertypeid) {
		this.usertypeid = usertypeid;
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