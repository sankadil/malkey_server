package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the fresrates database table.
 * 
 */
@Entity
@Table(name="fresrates")
@NamedQueries({@NamedQuery(name="FresratesListAll", query="SELECT f FROM Fresrates f")})

public class Fresrates implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FresratesPK id;

	@Column(precision=15, scale=2)
	private BigDecimal addcharges;

	@Column(precision=15, scale=2)
	private BigDecimal nightoutrate;

	@Column(precision=15, scale=2)
	private BigDecimal otrate;

	@Column(precision=15, scale=2)
	private BigDecimal rate;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(precision=15, scale=2)
	private BigDecimal xsmilerate;

    public Fresrates() {
    }

	public FresratesPK getId() {
		return this.id;
	}

	public void setId(FresratesPK id) {
		this.id = id;
	}
	
	public BigDecimal getAddcharges() {
		return this.addcharges;
	}

	public void setAddcharges(BigDecimal addcharges) {
		this.addcharges = addcharges;
	}

	public BigDecimal getNightoutrate() {
		return this.nightoutrate;
	}

	public void setNightoutrate(BigDecimal nightoutrate) {
		this.nightoutrate = nightoutrate;
	}

	public BigDecimal getOtrate() {
		return this.otrate;
	}

	public void setOtrate(BigDecimal otrate) {
		this.otrate = otrate;
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

	public BigDecimal getXsmilerate() {
		return this.xsmilerate;
	}

	public void setXsmilerate(BigDecimal xsmilerate) {
		this.xsmilerate = xsmilerate;
	}

}