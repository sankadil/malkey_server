package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;
import java.math.BigDecimal;


/**
 * The persistent class for the fdebtor database table.
 * 
 */
@Entity
@Table(name="fdebtor")
@NamedQueries(
		{@NamedQuery(name="FdebtorListAll", query="SELECT f FROM Fdebtor f"),
		@NamedQuery(name="FdebtorListAllDesc", query="SELECT f FROM Fdebtor f ORDER BY f.recordid DESC"),
		@NamedQuery(name="FdebtorListAll.Optimized", query="SELECT f.debcode,f.debname,f.nicno,f.clienttype,f.company,f.billmob1,f.longterm,f.debstat FROM Fdebtor f   ORDER BY f.recordid DESC"),//where f.debstat='A'
		@NamedQuery(name="FdebtorListAll.Optimized.Page", query="SELECT f.debcode,f.debname,f.nicno,f.clienttype,f.company,f.billmob1,f.longterm,f.passportno,f.debstat FROM Fdebtor f   ORDER BY f.recordid DESC"),//where f.debstat='A'
		@NamedQuery(name="FdebtorListAll.NameNIC", query="SELECT f.debcode,f.debname,f.nicno,f.debstat FROM Fdebtor f  ORDER BY f.recordid DESC")
		}
		)

@NamedNativeQueries({
	@NamedNativeQuery(name="Fdebtor.EmailID", query="SELECT f.debcode,f.debname,f.email,f.clienttype,f.recordid FROM Fdebtor f WHERE f.email!=''", resultSetMapping="Fdebtor.EmailID.Rsm")
})

@SqlResultSetMappings({
	@SqlResultSetMapping(name="debList", entities={@EntityResult(entityClass=Fdebtor.class,
			fields={@FieldResult(name="debcode",column="debcode"),
					@FieldResult(name="debname",column="debname"),
					@FieldResult(name="nicno",column="nicno"),
					@FieldResult(name="passportno",column="passportno"),
					@FieldResult(name="company",column="company")})
	}),
	@SqlResultSetMapping(name="Fdebtor.EmailID.Rsm", entities={@EntityResult(entityClass=Fdebtor.class,
			fields={@FieldResult(name="debcode",column="debcode"),
					@FieldResult(name="debname",column="debname"),
					@FieldResult(name="email",column="email"),
					@FieldResult(name="clienttype",column="clienttype"),
					@FieldResult(name="recordid",column="recordid")})
					}),
	@SqlResultSetMapping(name="debtorListRpt", entities={@EntityResult(entityClass=Fdebtor.class,
			fields={@FieldResult(name="debcode",column="debcode"),
					@FieldResult(name="debname",column="debname"),
					@FieldResult(name="nicno",column="nicno"),
					@FieldResult(name="passportno",column="passportno"),
					@FieldResult(name="debadd",column="debadd"),
					@FieldResult(name="tel",column="tel"),
					@FieldResult(name="company",column="company"),
					@FieldResult(name="vatno",column="vatno"),
					@FieldResult(name="clienttype",column="clienttype"),
					@FieldResult(name="debstat",column="debstat")
					})
	})					
})

public class Fdebtor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String debcode;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(length=50)
	private String addmach;

	@Column(length=10)
	private String adduser;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modifieddate")
	private Calendar modifieddate;
	
	public Calendar getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(Calendar modifieddate) {
		this.modifieddate = modifieddate;
	}

	public String getModifiedmach() {
		return modifiedmach;
	}

	public void setModifiedmach(String modifiedmach) {
		this.modifiedmach = modifiedmach;
	}

	public String getModifieduser() {
		return modifieduser;
	}

	public void setModifieduser(String modifieduser) {
		this.modifieduser = modifieduser;
	}

	@Column(length=50,name="modifiedmach")
	private String modifiedmach;
	
	@Column(length=10,name="modifieduser")
	private String modifieduser;

	@Column(length=20)
	private String billmob1;

	@Column(length=20)
	private String billmob2;

	@Column(length=25400)
	private String billname;

	@Column(length=20)
	private String debfax;

	@Column(length=50)
	private String debemail;

	@Column(length=10)
	private String clienttype;

	@Column(length=25400)
	private String company;

	@Column(nullable=false, length=100)
	private String contper;

	@Column(length=3000)
	private String country;

	@Column(nullable=false, precision=3)
	private BigDecimal crepre;

	@Column(nullable=false, precision=15)
	private BigDecimal crlmt;

	@Column(nullable=false, length=5)
	private String curcode;

	@Column(nullable=false, length=12)
	private String debacc;

	@Column(nullable=false, length=25400)
	private String debadd;

	@Column(nullable=false, length=5000)
	private String debname;

	@Column(nullable=false, length=2)
	private String debstat;

	@Column(nullable=false, length=1)
	private String debtype;

	@Column(length=50)
	private String email;

	@Column(length=15)
	private String fax;

	@Column(length=10)
	private String gid;

	@Column(length=254)
	private String homeadd;

	@Column(length=20)
	private String hometele;
	
	@Column(length=20)
	private String homemob;

	@Column(length=254)
	private String nicimage;
	
	@Column(length=254)
	private String nicbackimage;

	@Column(length=10)
	private String nicno;

	@Column(length=254)
	private String officeadd;

	@Column(length=20)
	private String officemob;

	@Column(length=20)
	private String officetele;

	@Column(length=15)
	private String passportno;

	@Column(length=254)
	private String ppimage;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(nullable=false, length=15)
	private String tel;

//	@Column(nullable=false)
//	private Timestamp timestamp_column;

	@Column(nullable=false, length=20)
	private String vatno;
	
	@Column(nullable=false, length=20)
	private String svatno;

	@Column(nullable=false, length=1)
	private String vattyp;
	
	@Transient
	private byte[] nicimagedata;
	
	@Transient
	private byte[] nicbackimagedata;
	
	@Transient
	private byte[] passimagedata;

	@Transient
	private Boolean isSelected=false;

	
	@Column(nullable=true)
	private String longterm;
	
	@Column
	private Boolean emailClient;
	
    public Fdebtor() {
    }

	public String getDebcode() {
		return this.debcode;
	}

	public void setDebcode(String debcode) {
		this.debcode = debcode;
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

	public String getBillmob1() {
		return this.billmob1;
	}

	public void setBillmob1(String billmob1) {
		this.billmob1 = billmob1;
	}

	public String getBillmob2() {
		return this.billmob2;
	}

	public void setBillmob2(String billmob2) {
		this.billmob2 = billmob2;
	}

	public String getBillname() {
		return this.billname;
	}

	public void setBillname(String billname) {
		this.billname = billname;
	}

	public String getClienttype() {
		return this.clienttype;
	}

	public void setClienttype(String clienttype) {
		this.clienttype = clienttype;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getContper() {
		return this.contper;
	}

	public void setContper(String contper) {
		this.contper = contper;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public BigDecimal getCrepre() {
		return this.crepre;
	}

	public void setCrepre(BigDecimal crepre) {
		this.crepre = crepre;
	}

	public BigDecimal getCrlmt() {
		return this.crlmt;
	}

	public void setCrlmt(BigDecimal crlmt) {
		this.crlmt = crlmt;
	}

	public String getCurcode() {
		return this.curcode;
	}

	public void setCurcode(String curcode) {
		this.curcode = curcode;
	}

	public String getDebacc() {
		return this.debacc;
	}

	public void setDebacc(String debacc) {
		this.debacc = debacc;
	}

	public String getDebadd() {
		return this.debadd;
	}

	public void setDebadd(String debadd) {
		this.debadd = debadd;
	}

	public String getDebname() {
		return this.debname;
	}

	public void setDebname(String debname) {
		this.debname = debname;
	}

	public String getDebstat() {
		return this.debstat;
	}

	public void setDebstat(String debstat) {
		this.debstat = debstat;
	}

	public String getDebtype() {
		return this.debtype;
	}

	public void setDebtype(String debtype) {
		this.debtype = debtype;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getGid() {
		return this.gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getHomeadd() {
		return this.homeadd;
	}

	public void setHomeadd(String homeadd) {
		this.homeadd = homeadd;
	}

	public String getHomemob() {
		return this.homemob;
	}

	public void setHomemob(String homemob) {
		this.homemob = homemob;
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

	public String getOfficeadd() {
		return this.officeadd;
	}

	public void setOfficeadd(String officeadd) {
		this.officeadd = officeadd;
	}

	public String getOfficemob() {
		return this.officemob;
	}

	public void setOfficemob(String officemob) {
		this.officemob = officemob;
	}

	public String getOfficetele() {
		return this.officetele;
	}

	public void setOfficetele(String officetele) {
		this.officetele = officetele;
	}

	public String getPassportno() {
		return this.passportno;
	}

	public void setPassportno(String passportno) {
		this.passportno = passportno;
	}

	public String getPpimage() {
		return this.ppimage;
	}

	public void setPpimage(String ppimage) {
		this.ppimage = ppimage;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

//	public Timestamp getTimestamp_column() {
//		return this.timestamp_column;
//	}
//
//	public void setTimestamp_column(Timestamp timestamp_column) {
//		this.timestamp_column = timestamp_column;
//	}

	public String getVatno() {
		return this.vatno;
	}

	public void setVatno(String vatno) {
		this.vatno = vatno;
	}

	public String getVattyp() {
		return this.vattyp;
	}

	public void setVattyp(String vattyp) {
		this.vattyp = vattyp;
	}

	public void setHometele(String hometele) {
		this.hometele = hometele;
	}

	public String getHometele() {
		return hometele;
	}

	public void setDebfax(String debfax) {
		this.debfax = debfax;
	}

	public String getDebfax() {
		return debfax;
	}

	public void setDebemail(String debemail) {
		this.debemail = debemail;
	}

	public String getDebemail() {
		return debemail;
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

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Boolean getIsSelected() {
		return isSelected;
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



	public void setLongterm(String longterm) {
		this.longterm = longterm;
	}

	public String getLongterm() {
		return longterm;
	}

	public void setEmailClient(Boolean emailClient) {
		this.emailClient = emailClient;
	}

	public Boolean getEmailClient() {
		return emailClient;
	}

	public void setSvatno(String svatno) {
		this.svatno = svatno;
	}

	public String getSvatno() {
		return svatno;
	}
}