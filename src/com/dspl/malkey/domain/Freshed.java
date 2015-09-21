package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;


/**
 * The persistent class for the freshed database table.
 * 
 */
@Entity
@Table(name="freshed")
@NamedQueries({
	@NamedQuery(name="FreshedListAll", query="SELECT f FROM Freshed f"),
	@NamedQuery(
			name="FreshedListAllDescription",
			query="SELECT NEW com.dspl.malkey.domain.Freshed(f,d.debname,c.description) FROM Freshed f,Fcompany c,Fdebtor d WHERE (d.debcode=f.debcode AND c.companyid=f.companyid) ORDER BY f.agrno DESC")
	})
public class Freshed implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
    public Freshed(Freshed f,String debname, String company) {
		super();
		this.debname = debname;
		this.company = company;
		this.agrno = f.agrno;
		this.adddate =  f.adddate;
		this.addmach =  f.addmach;
		this.adduser =  f.adduser;
		this.companyid =  f.companyid;
		this.debcode =  f.debcode;
		this.recordid =  f.recordid;
	}

	public Freshed() {
    }
	
	@Transient
	private String debname;
	
	@Transient
	private String company;

	@Id
	@Column(unique=true, nullable=false, length=13)
	private String agrno;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(length=50)
	private String addmach;

	@Column(length=10)
	private String adduser;

	@Column(length=10)
	private String companyid;

	@Column(nullable=false, length=10)
	private String debcode;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false)
	private int recordid;
	
	@Transient
	private Boolean isNew;
	
	@Column(nullable=false, length=50)
	private String uuid;


	public String getAgrno() {
		return this.agrno;
	}

	public void setAgrno(String agrno) {
		this.agrno = agrno;
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

	public String getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getDebcode() {
		return this.debcode;
	}

	public void setDebcode(String debcode) {
		this.debcode = debcode;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setDebname(String debname) {
		this.debname = debname;
	}

	public String getDebname() {
		return debname;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
		return company;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}
}