package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the freschkoutdamage database table.
 * 
 */
@Entity
@Table(name="freschkoutdamage")
@NamedQueries({@NamedQuery(name="FreschkoutdamageListAll", query="SELECT f FROM Freschkoutdamage f")})

public class Freschkoutdamage implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FreschkoutdamagePK id;

	@Column(nullable=false)
	private int damagetype;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(precision=15, scale=2)
	private BigDecimal xvalue;

	@Column(precision=15, scale=2)
	private BigDecimal yvalue;

    public Freschkoutdamage() {
    }

	public FreschkoutdamagePK getId() {
		return this.id;
	}

	public void setId(FreschkoutdamagePK id) {
		this.id = id;
	}
	
	public int getDamagetype() {
		return this.damagetype;
	}

	public void setDamagetype(int damagetype) {
		this.damagetype = damagetype;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public BigDecimal getXvalue() {
		return this.xvalue;
	}

	public void setXvalue(BigDecimal xvalue) {
		this.xvalue = xvalue;
	}

	public BigDecimal getYvalue() {
		return this.yvalue;
	}

	public void setYvalue(BigDecimal yvalue) {
		this.yvalue = yvalue;
	}

}