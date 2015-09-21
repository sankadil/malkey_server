package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;


/**
 * The persistent class for the fclientdriver database table.
 * 
 */
@Entity
@Table(name="fclientdriver")
@NamedQueries({
	@NamedQuery(name="FclientdriverListAll", query="SELECT f FROM Fclientdriver f"),
	@NamedQuery(name="FclientdriverFindByDebcode", query="SELECT f FROM Fclientdriver f WHERE f.debcode=:debcode")
	})

public class Fclientdriver implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(nullable=false, length=10)
	private String debcode;

	@Column(nullable=false, length=254)
	private String dlbackimage;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dldissued;
	
	@Column(nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dldexp;

	@Column(nullable=false, length=254)
	private String dlfontimage;

	@Column(nullable=false, length=20)
	private String dlno;

	@Column(nullable=false, length=254)
	private String drivername;

	@Column(nullable=false, length=20)
	private String mobilephone;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Transient
	private Boolean isSelected=false;
	
	@Transient
	private byte[] licfrontdata;
	
	@Transient
	private byte[] licbackdata;
	
    public Fclientdriver() {
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



	public String getDlbackimage() {
		return this.dlbackimage;
	}

	public void setDlbackimage(String dlbackimage) {
		this.dlbackimage = dlbackimage;
	}

	public Calendar getDldexp() {
		return this.dldexp;
	}

	public void setDldexp(Calendar dldexp) {
		this.dldexp = dldexp;
	}

	public String getDlfontimage() {
		return this.dlfontimage;
	}

	public void setDlfontimage(String dlfontimage) {
		this.dlfontimage = dlfontimage;
	}

	public String getDlno() {
		return this.dlno;
	}

	public void setDlno(String dlno) {
		this.dlno = dlno;
	}

	public String getDrivername() {
		return this.drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	public String getDebcode() {
		return debcode;
	}

	public void setDebcode(String debcode) {
		this.debcode = debcode;
	}

	public void setDldissued(Calendar dldissued) {
		this.dldissued = dldissued;
	}

	public Calendar getDldissued() {
		return dldissued;
	}

	public void setLicfrontdata(byte[] licfrontdata) {
		this.licfrontdata = licfrontdata;
	}

	public byte[] getLicfrontdata() {
		return licfrontdata;
	}

	public void setLicbackdata(byte[] licbackdata) {
		this.licbackdata = licbackdata;
	}

	public byte[] getLicbackdata() {
		return licbackdata;
	}
	

}