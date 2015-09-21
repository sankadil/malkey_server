package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;


/**
 * The persistent class for the fguarantor database table.
 * 
 */
@Entity
@Table(name="fguarantor")
@NamedQueries({@NamedQuery(name="FguarantorListAll", query="SELECT f FROM Fguarantor f"),
	@NamedQuery(name="FguarantorByDebcode", query="SELECT g FROM Fguarantor g WHERE g.gid in (SELECT d.gid FROM Fdebtor d WHERE d.debcode=:debcode)"),
	@NamedQuery(name="FguarantorApprovedList", query="SELECT f FROM Fguarantor f WHERE f.approved='1'")})

public class Fguarantor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable=false, length=10)
	private String gid;
	
	@Column(nullable=false, length=10)
	private String debcode;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(nullable=false, length=254)
	private String gname;

	@Column(nullable=false, length=254)
	private String company;

	@Column(nullable=false, length=254)
	private String homeadd1;

	@Column(nullable=false, length=254)
	private String homeadd2;

	@Column(nullable=false, length=254)
	private String homeadd3;

	@Column(nullable=false, length=20)
	private String hometele;

	@Column(nullable=false, length=20)
	private String mobilephone;

	@Column(nullable=false, length=254)
	private String offadd1;

	@Column(nullable=false, length=254)
	private String offadd2;

	@Column(nullable=false, length=254)
	private String offadd3;

	@Column(nullable=false, length=20)
	private String offtele;
	
	@Column(length=254)
	private String nicimage;

	@Column(length=254)
	private String nicbackimage;

	@Column(length=254)
	private String ppimage;
	
	@Column(length=10)
	private String nicno;

	@Column(length=15)
	private String passportno;
	
	@Transient
	private byte[] nicimagedata;
	
	@Transient
	private byte[] nicbackimagedata;
	
	@Transient
	private byte[] passimagedata;
	
	@Column(length=1)
	private String approved;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

    public Fguarantor() {
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

	public String getGname() {
		return this.gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
		return company;
	}

	public String getHomeadd1() {
		return this.homeadd1;
	}

	public void setHomeadd1(String homeadd1) {
		this.homeadd1 = homeadd1;
	}

	public String getHomeadd2() {
		return this.homeadd2;
	}

	public void setHomeadd2(String homeadd2) {
		this.homeadd2 = homeadd2;
	}

	public String getHomeadd3() {
		return this.homeadd3;
	}

	public void setHomeadd3(String homeadd3) {
		this.homeadd3 = homeadd3;
	}

	public String getHometele() {
		return this.hometele;
	}

	public void setHometele(String hometele) {
		this.hometele = hometele;
	}

	public String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getOffadd1() {
		return this.offadd1;
	}

	public void setOffadd1(String offadd1) {
		this.offadd1 = offadd1;
	}

	public String getOffadd2() {
		return this.offadd2;
	}

	public void setOffadd2(String offadd2) {
		this.offadd2 = offadd2;
	}

	public String getOffadd3() {
		return this.offadd3;
	}

	public void setOffadd3(String offadd3) {
		this.offadd3 = offadd3;
	}

	public String getOfftele() {
		return this.offtele;
	}

	public void setOfftele(String offtele) {
		this.offtele = offtele;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getGid() {
		return gid;
	}

	public void setDebcode(String debcode) {
		this.debcode = debcode;
	}

	public String getDebcode() {
		return debcode;
	}

	public void setNicimage(String nicimage) {
		this.nicimage = nicimage;
	}

	public String getNicimage() {
		return nicimage;
	}

	public void setNicbackimage(String nicbackimage) {
		this.nicbackimage = nicbackimage;
	}

	public String getNicbackimage() {
		return nicbackimage;
	}

	public void setPpimage(String ppimage) {
		this.ppimage = ppimage;
	}

	public String getPpimage() {
		return ppimage;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getApproved() {
		return approved;
	}

	public void setNicimagedata(byte[] nicimagedata) {
		this.nicimagedata = nicimagedata;
	}

	public byte[] getNicimagedata() {
		return nicimagedata;
	}

	public void setPassimagedata(byte[] passimagedata) {
		this.passimagedata = passimagedata;
	}

	public byte[] getPassimagedata() {
		return passimagedata;
	}

	public void setNicno(String nicno) {
		this.nicno = nicno;
	}

	public String getNicno() {
		return nicno;
	}

	public void setPassportno(String passportno) {
		this.passportno = passportno;
	}

	public String getPassportno() {
		return passportno;
	}

	public void setNicbackimagedata(byte[] nicbackimagedata) {
		this.nicbackimagedata = nicbackimagedata;
	}

	public byte[] getNicbackimagedata() {
		return nicbackimagedata;
	}

}