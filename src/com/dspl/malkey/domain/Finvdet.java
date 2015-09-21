package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;
import java.math.BigDecimal;


/**
 * The persistent class for the finvdet database table.
 * 
 */
@Entity
@Table(name="finvdet")
@NamedQueries({
	@NamedQuery(name="FinvdetListAll", query="SELECT f FROM Finvdet f"),
	@NamedQuery(name="FinvdetFindById", query="SELECT f FROM Finvdet f WHERE f.id.invno=:ino ORDER BY f.id.seq,f.subseq")
	//@NamedQuery(name="FinvdetFindByInvno", query="SELECT f FROM Finvdet f WHERE f.statusid=:statusid AND f.regno=:regno AND f.typeid=:typeid")
})
public class Finvdet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient
	private String hiretype;
	
	@EmbeddedId
	private FinvdetPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="adddate",nullable=false)
	private Calendar adddate;

	@Column(name="addmach",nullable=false, length=50)
	private String addmach;

	@Column(name="adduser",nullable=false, length=10)
	private String adduser;

	@Column(name="amount",nullable=false, precision=18, scale=2)
	private BigDecimal amount;

	@Column(name="days",nullable=false, precision=8, scale=2)
	private BigDecimal days;

	@Column(name="description",nullable=false, length=1000)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dfrom",nullable=false)
	private Calendar dfrom;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dto",nullable=false)
	private Calendar dto;

	@Column(name="hiretypeid",nullable=false, length=10)
	private String hiretypeid;

	@Column(name="km",nullable=false, precision=18, scale=2)
	private BigDecimal km;

	@Column(name="qty",nullable=false)
	private int qty;

	@Column(name="rate",nullable=false, precision=18, scale=2)
	private BigDecimal rate;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="recordid",nullable=false)
	private int recordid;

	@Column(name="regno",nullable=false, length=10)
	private String regno;

	@Column(name="resno",nullable=false, length=13)
	private String resno;

	@Column(name="subseq",nullable=false)
	private int subseq;

	@Column(name="taxcomcode",nullable=false, length=10)
	private String taxcomcode;

	@Column(name="type",nullable=false, length=10)
	private String type;

    public Finvdet() {
    }

	public FinvdetPK getId() {
		return this.id;
	}

	public void setId(FinvdetPK id) {
		this.id = id;
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

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getDays() {
		return this.days;
	}

	public void setDays(BigDecimal days) {
		this.days = days;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getDfrom() {
		return this.dfrom;
	}

	public void setDfrom(Calendar dfrom) {
		this.dfrom = dfrom;
	}

	public Calendar getDto() {
		return this.dto;
	}

	public void setDto(Calendar dto) {
		this.dto = dto;
	}

	public String getHiretypeid() {
		return this.hiretypeid;
	}

	public void setHiretypeid(String hiretypeid) {
		this.hiretypeid = hiretypeid;
	}

	public BigDecimal getKm() {
		return this.km;
	}

	public void setKm(BigDecimal km) {
		this.km = km;
	}

	public int getQty() {
		return this.qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getRegno() {
		return this.regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getResno() {
		return this.resno;
	}

	public void setResno(String resno) {
		this.resno = resno;
	}

	public int getSubseq() {
		return this.subseq;
	}

	public void setSubseq(int subseq) {
		this.subseq = subseq;
	}

	public String getTaxcomcode() {
		return this.taxcomcode;
	}

	public void setTaxcomcode(String taxcomcode) {
		this.taxcomcode = taxcomcode;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setHiretype(String hiretype) {
		this.hiretype = hiretype;
	}

	public String getHiretype() {
		return hiretype;
	}

}