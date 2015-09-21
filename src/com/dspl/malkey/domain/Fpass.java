package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;


/**
 * The persistent class for the fpass database table.
 * 
 */
@Entity
@Table(name="fpass")
@NamedQueries({@NamedQuery(name="FpassListAll", query="SELECT f FROM Fpass f")})
public class Fpass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient
	private String empname;
	@Transient 
	private String usertype;
	
	@Id
	@Column(unique=true, nullable=false, length=30)
	private String username;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(nullable=false, length=12)
	private String empid;

	@Column(nullable=false, length=24)
	private String password;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="recordid",nullable=false,updatable=false)
	private int recordid;

	@Column(nullable=false, length=1)
	private String status;

	@Column(nullable=false, length=10)
	private String usertypeid;

    public Fpass() {
    }

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getEmpid() {
		return this.empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsertypeid() {
		return this.usertypeid;
	}

	public void setUsertypeid(String usertypeid) {
		this.usertypeid = usertypeid;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getEmpname() {
		return empname;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getUsertype() {
		return usertype;
	}

}