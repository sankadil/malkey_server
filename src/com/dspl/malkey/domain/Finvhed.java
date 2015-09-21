package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;


/**
 * The persistent class for the finvhed database table.
 * 
 */
@Entity
@Table(name="finvhed")
@NamedQueries({
	@NamedQuery(name="FinvhedListAll", query="SELECT f FROM Finvhed f")
	//@NamedQuery(name="FinvhedFindByInvno", query="SELECT f FROM Finvhed f WHERE f.statusid=:statusid AND f.regno=:regno AND f.typeid=:typeid")
})
public class Finvhed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient
	private String companyid;
	@Transient 
	private String company;
	
	@Transient
	private String debinfo;
	
	@Transient
	private String cusvatno;
	
	@Transient
	private String svatno;
	@Transient
	private String vattyp;
	
	@Transient
	private Timestamp txndatets;
	
	@Transient
	private String debname;
	
	@Transient
	private String debadd;
	
	@Transient
	private String tel;
	@Transient
	private String byWhomOrdered;
	@Transient
	private String agentText;
	@Transient
	private String chequeText;
	@Transient
	private String taxOrderText;	
	@Id
	@Column(unique=true, nullable=false, length=13)
	private String invno;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(nullable=false, length=13)
	private String agrno;

	@Column(nullable=false, length=10)
	private String debcode;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(nullable=false)
	private int recordid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Calendar txndate;

    public Finvhed() {
    }

	public String getInvno() {
		return this.invno;
	}

	public void setInvno(String invno) {
		this.invno = invno;
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

	public String getAgrno() {
		return this.agrno;
	}

	public void setAgrno(String agrno) {
		this.agrno = agrno;
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

	public Calendar getTxndate() {
		return this.txndate;
	}

	public void setTxndate(Calendar txndate) {
		this.txndate = txndate;
	}

	public void setTxndatets(Timestamp txndatets) {
		this.txndatets = txndatets;
	}

	public Timestamp getTxndatets() {
		return txndatets;
	}

	public void setDebname(String debname) {
		this.debname = debname;
	}

	public String getDebname() {
		return debname;
	}

	public void setDebadd(String debadd) {
		this.debadd = debadd;
	}

	public String getDebadd() {
		return debadd;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTel() {
		return tel;
	}

	public void setDebinfo(String debinfo) {
		this.debinfo = debinfo;
	}

	public String getDebinfo() {
		return debinfo;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
		return company;
	}

	public void setCusvatno(String cusvatno) {
		this.cusvatno = cusvatno;
	}

	public String getCusvatno() {
		return cusvatno;
	}

	public void setVattyp(String vattyp) {
		this.vattyp = vattyp;
	}

	public String getVattyp() {
		return vattyp;
	}

	public void setSvatno(String svatno) {
		this.svatno = svatno;
	}

	public String getSvatno() {
		return svatno;
	}

	public void setByWhomOrdered(String byWhomOrdered) {
		this.byWhomOrdered = byWhomOrdered;
	}

	public String getByWhomOrdered() {
		return byWhomOrdered;
	}

	public void setAgentText(String agentText) {
		this.agentText = agentText;
	}

	public String getAgentText() {
		return agentText;
	}

	public void setChequeText(String chequeText) {
		this.chequeText = chequeText;
	}

	public String getChequeText() {
		return chequeText;
	}

	public void setTaxOrderText(String taxOrderText) {
		this.taxOrderText = taxOrderText;
	}

	public String getTaxOrderText() {
		return taxOrderText;
	}

}