package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;


/**
 * The persistent class for the ftax database table.
 * 
 */
@Entity
@Table(name="ftax")
@NamedQueries({@NamedQuery(name="FtaxListAll", query="SELECT f FROM Ftax f")})
public class Ftax implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String taxcode;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(nullable=false, precision=3)
	private BigDecimal taxdevd;

	@Column(nullable=false, length=100)
	private String taxname;

	@Column(nullable=false, length=12)
	private String taxpayacc;

	@Column(nullable=false, precision=5, scale=2)
	private BigDecimal taxper;

	@Column(nullable=false, length=12)
	private String taxrecacc;

	@Column(nullable=false, length=20)
	private String taxregno;


    public Ftax() {
    }

	public String getTaxcode() {
		return this.taxcode;
	}

	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
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

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public BigDecimal getTaxdevd() {
		return this.taxdevd;
	}

	public void setTaxdevd(BigDecimal taxdevd) {
		this.taxdevd = taxdevd;
	}

	public String getTaxname() {
		return this.taxname;
	}

	public void setTaxname(String taxname) {
		this.taxname = taxname;
	}

	public String getTaxpayacc() {
		return this.taxpayacc;
	}

	public void setTaxpayacc(String taxpayacc) {
		this.taxpayacc = taxpayacc;
	}

	public BigDecimal getTaxper() {
		return this.taxper;
	}

	public void setTaxper(BigDecimal taxper) {
		this.taxper = taxper;
	}

	public String getTaxrecacc() {
		return this.taxrecacc;
	}

	public void setTaxrecacc(String taxrecacc) {
		this.taxrecacc = taxrecacc;
	}

	public String getTaxregno() {
		return this.taxregno;
	}

	public void setTaxregno(String taxregno) {
		this.taxregno = taxregno;
	}

}