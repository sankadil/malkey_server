package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;


/**
 * The persistent class for the femployee database table.
 * 
 */
@Entity
@Table(name="femployee")
@NamedQueries({
	
			@NamedQuery(name="FemployeeListAll", query="SELECT f FROM Femployee f"),
			@NamedQuery(name="FemployeeDriverListAll", query="SELECT f FROM Femployee f WHERE f.emptype=:emptype"),
			
		})

@NamedNativeQueries({
	@NamedNativeQuery(name="Femployee.EmailID", query="SELECT f.empid,f.name,f.email,f.emptype,f.recordid FROM Femployee f WHERE f.email!=''", resultSetMapping="Femployee.EmailID.Rsm"),
	@NamedNativeQuery(
			name="FemployeeDriverListByDate",
			query="SELECT e.empid FROM femployee AS e WHERE  e.empid NOT IN (SELECT DISTINCT empid FROM fresdriver WHERE priority=1 AND resno IN (SELECT resno FROM freservation WHERE resno IN (SELECT resno FROM fresdriver WHERE ?1 BETWEEN dout AND din OR ?2 BETWEEN dout AND din AND dout BETWEEN ?1 AND ?2 OR din BETWEEN ?1 AND ?2) AND cohirestsid IN ('BOOKED','CHECKIN','CHECKOUT','CONFIRMED','PREPARED')) ) and emptype='DRIVER'",
			resultClass=Femployee.class)	
})
		
@SqlResultSetMappings({
	@SqlResultSetMapping(name="empList", entities={@EntityResult(entityClass=Femployee.class,
			fields={@FieldResult(name="empid",column="empid"),
					@FieldResult(name="name",column="name"),
					@FieldResult(name="emptype",column="description"),
					@FieldResult(name="nicno",column="nicno"),
					@FieldResult(name="ppno",column="ppno"),
					@FieldResult(name="dlno",column="dlno")})
	}),
	@SqlResultSetMapping(name="Femployee.EmailID.Rsm", entities={@EntityResult(entityClass=Femployee.class,
			fields={@FieldResult(name="empid",column="empid"),
					@FieldResult(name="name",column="name"),
					@FieldResult(name="email",column="email"),
					@FieldResult(name="emptype",column="emptype"),
					@FieldResult(name="recordid",column="recordid")})
					})
})	

public class Femployee implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Transient
	private byte[] photodata;
	
	@Transient
	private byte[] nicimagedata;
	
	@Transient
	private byte[] nicbackimagedata;
	
	@Transient
	private byte[] dlfrontimagedata;
	
	@Transient
	private byte[] dlbackimagedata;
	
	
	@Id
	@Column(name="empid",unique=true, nullable=false, length=12)
	private String empid;
	
	@Column(name="adddate",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;
	
	@Column(name="joindate",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar joindate;

	@Column(name="addmach",nullable=false, length=50)
	private String addmach;

	@Column(name="adduser",nullable=false, length=10)
	private String adduser;

	@Column(name="curadd1",nullable=false, length=254)
	private String curadd1;

	@Column(name="curadd2",nullable=false, length=254)
	private String curadd2;

	@Column(name="curadd3",nullable=false, length=254)
	private String curadd3;

	@Column(name="curtele",nullable=false, length=20)
	private String curtele;

	@Column(name="dlbackimage",nullable=false, length=254)
	private String dlbackimage;

	@Column(name="dldexp",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dldexp;

	@Column(name="dldissue",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dldissue;

	@Column(name="dlfrontimage",nullable=false, length=254)
	private String dlfrontimage;

	@Column(name="dlno",nullable=false, length=20)
	private String dlno;

	@Column(name="dob",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dob;

	@Column(name="email",nullable=false, length=254)
	private String email;
	
	@Column(name="emptype",nullable=false, length=10)
	private String emptype;

	@Column(name="epfno",nullable=false, length=10)
	private String epfno;

	@Column(name="etfno",nullable=false, length=10)
	private String etfno;

	@Column(name="homeadd1",nullable=false, length=254)
	private String homeadd1;

	@Column(name="homeadd2",nullable=false, length=254)
	private String homeadd2;

	@Column(name="homeadd3",nullable=false, length=254)
	private String homeadd3;

	@Column(name="hometele",nullable=false, length=20)
	private String hometele;

	@Column(name="mobilephone1",nullable=false, length=20)
	private String mobilephone1;

	@Column(name="mobilephone2",nullable=false, length=20)
	private String mobilephone2;

	@Column(name="name",nullable=false, length=254)
	private String name;

	@Column(name="nicimage",nullable=false, length=254)
	private String nicimage;
	
	@Column(name="nicbackimage",nullable=false, length=217)
	private String nicbackimage;

	@Column(name="nicno",nullable=false, length=10)
	private String nicno;

	@Column(name="photo",nullable=false, length=254)
	private String photo;

	@Column(name="ppimage",nullable=false, length=254)
	private String ppimage;

	@Column(name="ppno",nullable=false, length=15)
	private String ppno;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="recordid",nullable=false,updatable=false)
	private int recordid;

	@Column(name="remarks",nullable=false, length=254)
	private String remarks;
	
	@Column(name="empstat",nullable=false, length=2)
	private String empstat;
	
	@Transient
	private Boolean isSelected=false;

    public Femployee() {
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

	public String getCuradd1() {
		return this.curadd1;
	}

	public void setCuradd1(String curadd1) {
		this.curadd1 = curadd1;
	}

	public String getCuradd2() {
		return this.curadd2;
	}

	public void setCuradd2(String curadd2) {
		this.curadd2 = curadd2;
	}

	public String getCuradd3() {
		return this.curadd3;
	}

	public void setCuradd3(String curadd3) {
		this.curadd3 = curadd3;
	}

	public String getCurtele() {
		return this.curtele;
	}

	public void setCurtele(String curtele) {
		this.curtele = curtele;
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

	public Calendar getDldissue() {
		return this.dldissue;
	}

	public void setDldissue(Calendar dldissue) {
		this.dldissue = dldissue;
	}

	public String getDlfrontimage() {
		return this.dlfrontimage;
	}

	public void setDlfrontimage(String dlfrontimage) {
		this.dlfrontimage = dlfrontimage;
	}

	public String getDlno() {
		return this.dlno;
	}

	public void setDlno(String dlno) {
		this.dlno = dlno;
	}

	public Calendar getDob() {
		return this.dob;
	}

	public void setDob(Calendar dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpid() {
		return this.empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmptype() {
		return this.emptype;
	}

	public void setEmptype(String emptype) {
		this.emptype = emptype;
	}

	public String getEpfno() {
		return this.epfno;
	}

	public void setEpfno(String epfno) {
		this.epfno = epfno;
	}

	public String getEtfno() {
		return this.etfno;
	}

	public void setEtfno(String etfno) {
		this.etfno = etfno;
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

	public String getMobilephone1() {
		return this.mobilephone1;
	}

	public void setMobilephone1(String mobilephone1) {
		this.mobilephone1 = mobilephone1;
	}

	public String getMobilephone2() {
		return this.mobilephone2;
	}

	public void setMobilephone2(String mobilephone2) {
		this.mobilephone2 = mobilephone2;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNicimage() {
		return this.nicimage;
	}

	public void setNicimage(String nicimage) {
		this.nicimage = nicimage;
	}

	public String getNicno() {
		return this.nicno;
	}

	public void setNicno(String nicno) {
		this.nicno = nicno;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPpimage() {
		return this.ppimage;
	}

	public void setPpimage(String ppimage) {
		this.ppimage = ppimage;
	}

	public String getPpno() {
		return this.ppno;
	}

	public void setPpno(String ppno) {
		this.ppno = ppno;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void setEmpstat(String empstat) {
		this.empstat = empstat;
	}

	public String getEmpstat() {
		return empstat;
	}

	public void setJoindate(Calendar joindate) {
		this.joindate = joindate;
	}

	public Calendar getJoindate() {
		return joindate;
	}

	public void setPhotodata(byte[] photodata) {
		this.photodata = photodata;
	}

	public byte[] getPhotodata() {
		return photodata;
	}

	public void setNicimagedata(byte[] nicimagedata) {
		this.nicimagedata = nicimagedata;
	}

	public byte[] getNicimagedata() {
		return nicimagedata;
	}

	public void setDlfrontimagedata(byte[] dlfrontimagedata) {
		this.dlfrontimagedata = dlfrontimagedata;
	}

	public byte[] getDlfrontimagedata() {
		return dlfrontimagedata;
	}

	public void setDlbackimagedata(byte[] dlbackimagedata) {
		this.dlbackimagedata = dlbackimagedata;
	}

	public byte[] getDlbackimagedata() {
		return dlbackimagedata;
	}

	public void setNicbackimage(String nicbackimage) {
		this.nicbackimage = nicbackimage;
	}

	public String getNicbackimage() {
		return nicbackimage;
	}

	public void setNicbackimagedata(byte[] nicbackimagedata) {
		this.nicbackimagedata = nicbackimagedata;
	}

	public byte[] getNicbackimagedata() {
		return nicbackimagedata;
	}

}