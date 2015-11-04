package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.math.BigDecimal;


/**
 * The persistent class for the fsupplier2 database table.
 * 
 */
@Entity
@Table(name="fsupplier2")
@NamedQueries({
	@NamedQuery(name="Fsupplier2ListAll", query="SELECT f FROM Fsupplier2 f")
	})
	
@NamedNativeQueries({
	@NamedNativeQuery(name="Fsupplier2.EmailID", query="SELECT f.supcode,f.supname,f.supemail,f.typ,f.recordid FROM Fsupplier2 f WHERE f.supemail!=''", resultSetMapping="Fsupplier2.EmailID.Rsm")
})

@SqlResultSetMappings({
	@SqlResultSetMapping(name="Fsupplier2.EmailID.Rsm", entities={@EntityResult(entityClass=Fsupplier2.class,
			fields={@FieldResult(name="supcode",column="supcode"),
					@FieldResult(name="supname",column="supname"),
					@FieldResult(name="supemail",column="supemail"),
					@FieldResult(name="typ",column="typ"),
					@FieldResult(name="recordid",column="recordid")})
	})
})	

public class Fsupplier2 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String supcode;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(length=50)
	private String addmach;

	@Column(length=10)
	private String adduser;

	@Column(length=254)
	private String company;

	private int conflg;

	@Column(length=50)
	private String conperson;

	@Column(length=30)
	private String contact;

	@Column(precision=15, scale=2)
	private BigDecimal crlimit;

	@Column(precision=5)
	private BigDecimal crperiod;

	@Column(length=5)
	private String curcode;

	@Column(length=100)
	private String infavor;

	@Column(length=1)
	private String nbtstat;

	@Column(length=254)
	private String officeadd;

	@Column(length=20)
	private String officemobile;

	@Column(length=20)
	private String officetele;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, updatable=false)
	private int recordid;

	@Column(length=12)
	private String supacc;

	@Column(length=50)
	private String supadd1;

	@Column(length=30)
	private String supadd2;

	@Column(length=30)
	private String supadd3;

	@Column(length=30)
	private String supemail;

	@Column(length=10)
	private String supfax;

	@Column(length=1)
	private String supflg;

	@Column(length=20)
	private String supmobile;

	@Column(nullable=false, length=50)
	private String supname;

	@Column(length=10)
	private String suptele;

	@Column(length=1)
	private String suptyp;

	@Column(length=10)
	private String taxcode;

	@Column(length=1)
	private String typ;

	@Column(length=30)
	private String vatregno;

	@Column(length=1)
	private String vatstat;
	
	@Transient
	private Boolean isSelected=false;

	@Column(length=50,name="modifiedmach")
	private String modifiedmach;
	
	@Column(length=10,name="modifieduser")
	private String modifieduser;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modifieddate")
	private Calendar modifieddate;
	
    public Fsupplier2() {
    }

	public String getSupcode() {
		return this.supcode;
	}

	public void setSupcode(String supcode) {
		this.supcode = supcode;
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

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getConflg() {
		return this.conflg;
	}

	public void setConflg(int conflg) {
		this.conflg = conflg;
	}

	public String getConperson() {
		return this.conperson;
	}

	public void setConperson(String conperson) {
		this.conperson = conperson;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public BigDecimal getCrlimit() {
		return this.crlimit;
	}

	public void setCrlimit(BigDecimal crlimit) {
		this.crlimit = crlimit;
	}

	public BigDecimal getCrperiod() {
		return this.crperiod;
	}

	public void setCrperiod(BigDecimal crperiod) {
		this.crperiod = crperiod;
	}

	public String getCurcode() {
		return this.curcode;
	}

	public void setCurcode(String curcode) {
		this.curcode = curcode;
	}

	public String getInfavor() {
		return this.infavor;
	}

	public void setInfavor(String infavor) {
		this.infavor = infavor;
	}

	public String getNbtstat() {
		return this.nbtstat;
	}

	public void setNbtstat(String nbtstat) {
		this.nbtstat = nbtstat;
	}

	public String getOfficeadd() {
		return this.officeadd;
	}

	public void setOfficeadd(String officeadd) {
		this.officeadd = officeadd;
	}
	
	public String getOfficemobile() {
		return this.officemobile;
	}

	public void setOfficemobile(String officemobile) {
		this.officemobile = officemobile;
	}

	public String getOfficetele() {
		return this.officetele;
	}

	public void setOfficetele(String officetele) {
		this.officetele = officetele;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getSupacc() {
		return this.supacc;
	}

	public void setSupacc(String supacc) {
		this.supacc = supacc;
	}

	public String getSupadd1() {
		return this.supadd1;
	}

	public void setSupadd1(String supadd1) {
		this.supadd1 = supadd1;
	}

	public String getSupadd2() {
		return this.supadd2;
	}

	public void setSupadd2(String supadd2) {
		this.supadd2 = supadd2;
	}

	public String getSupadd3() {
		return this.supadd3;
	}

	public void setSupadd3(String supadd3) {
		this.supadd3 = supadd3;
	}

	public String getSupemail() {
		return this.supemail;
	}

	public void setSupemail(String supemail) {
		this.supemail = supemail;
	}

	public String getSupfax() {
		return this.supfax;
	}

	public void setSupfax(String supfax) {
		this.supfax = supfax;
	}

	public String getSupflg() {
		return this.supflg;
	}

	public void setSupflg(String supflg) {
		this.supflg = supflg;
	}

	public String getSupmobile() {
		return this.supmobile;
	}

	public void setSupmobile(String supmobile) {
		this.supmobile = supmobile;
	}

	public String getSupname() {
		return this.supname;
	}

	public void setSupname(String supname) {
		this.supname = supname;
	}

	public String getSuptele() {
		return this.suptele;
	}

	public void setSuptele(String suptele) {
		this.suptele = suptele;
	}

	public void setSuptyp(String suptyp) {
		this.suptyp = suptyp;
	}

	public String getSuptyp() {
		return suptyp;
	}

	public String getTaxcode() {
		return this.taxcode;
	}

	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}

	public String getTyp() {
		return this.typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getVatregno() {
		return this.vatregno;
	}

	public void setVatregno(String vatregno) {
		this.vatregno = vatregno;
	}

	public String getVatstat() {
		return this.vatstat;
	}

	public void setVatstat(String vatstat) {
		this.vatstat = vatstat;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Boolean getIsSelected() {
		return isSelected;
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

	public Calendar getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(Calendar modifieddate) {
		this.modifieddate = modifieddate;
	}

}