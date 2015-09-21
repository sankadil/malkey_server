package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the fdtftax database table.
 * 
 */
@Entity
@Table(name="fdtftax")
public class Fdtftax implements Serializable {
	private static final long serialVersionUID = 1L;

	private String resno;
	private String type;
	private int subseq;
	
	@EmbeddedId
	private FdtftaxPK id;

	@Column(nullable=false, precision=18, scale=4)
	private BigDecimal taxamt;

//	@Column(nullable=false, length=10)
//	private String taxcode;

	@Column(nullable=false, length=10)
	private String taxcomcode;

	@Column(nullable=false, precision=18, scale=4)
	private BigDecimal taxper;

	@Column(nullable=false, precision=18, scale=4)
	private BigDecimal taxrate;

	@Column(nullable=false)
	private int taxseq;

    public Fdtftax() {
    }

	public FdtftaxPK getId() {
		return this.id;
	}

	public void setId(FdtftaxPK id) {
		this.id = id;
	}
	
	public BigDecimal getTaxamt() {
		return this.taxamt;
	}

	public void setTaxamt(BigDecimal taxamt) {
		this.taxamt = taxamt;
	}

//	public String getTaxcode() {
//		return this.taxcode;
//	}
//
//	public void setTaxcode(String taxcode) {
//		this.taxcode = taxcode;
//	}

	public String getTaxcomcode() {
		return this.taxcomcode;
	}

	public void setTaxcomcode(String taxcomcode) {
		this.taxcomcode = taxcomcode;
	}

	public BigDecimal getTaxper() {
		return this.taxper;
	}

	public void setTaxper(BigDecimal taxper) {
		this.taxper = taxper;
	}

	public BigDecimal getTaxrate() {
		return this.taxrate;
	}

	public void setTaxrate(BigDecimal taxrate) {
		this.taxrate = taxrate;
	}

	public int getTaxseq() {
		return this.taxseq;
	}

	public void setTaxseq(int taxseq) {
		this.taxseq = taxseq;
	}

	public void setSubseq(int subseq) {
		this.subseq = subseq;
	}

	public int getSubseq() {
		return subseq;
	}

	public void setResno(String resno) {
		this.resno = resno;
	}

	public String getResno() {
		return resno;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}