package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ftaxdet database table.
 * 
 */
@Entity
@Table(name="ftaxdet")
@NamedQueries({@NamedQuery(name="FtaxdetListAll", query="SELECT f FROM Ftaxdet f")})
public class Ftaxdet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient
	private String taxname;
	
	@Transient
	private BigDecimal taxper;
	
	@EmbeddedId
	private FtaxdetPK id;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(nullable=false, precision=1)
	private BigDecimal taxmode;

	@Column(nullable=false, precision=14, scale=10)
	private BigDecimal taxrate;

	@Column(nullable=false, precision=2)
	private BigDecimal taxseq;


    public Ftaxdet() {
    }

	public FtaxdetPK getId() {
		return this.id;
	}

	public void setId(FtaxdetPK id) {
		this.id = id;
	}
	
	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public BigDecimal getTaxmode() {
		return this.taxmode;
	}

	public void setTaxmode(BigDecimal taxmode) {
		this.taxmode = taxmode;
	}

	public BigDecimal getTaxrate() {
		return this.taxrate;
	}

	public void setTaxrate(BigDecimal taxrate) {
		this.taxrate = taxrate;
	}

	public BigDecimal getTaxseq() {
		return this.taxseq;
	}

	public void setTaxseq(BigDecimal taxseq) {
		this.taxseq = taxseq;
	}

	public void setTaxper(BigDecimal taxper) {
		this.taxper = taxper;
	}

	public BigDecimal getTaxper() {
		return taxper;
	}

	public void setTaxname(String taxname) {
		this.taxname = taxname;
	}

	public String getTaxname() {
		return taxname;
	}

}