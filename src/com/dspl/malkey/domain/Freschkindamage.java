package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the freschkindamage database table.
 * 
 */
@Entity
@Table(name="freschkindamage")
@NamedQueries({@NamedQuery(name="FreschkindamageListAll", query="SELECT f FROM Freschkindamage f")})

public class Freschkindamage implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FreschkindamagePK id;

	@Column(nullable=false, length=10)
	private String damagetype;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(precision=15, scale=2)
	private BigDecimal xvalue;

	@Column(precision=15, scale=2)
	private BigDecimal yvalue;

    public Freschkindamage() {
    }

	public FreschkindamagePK getId() {
		return this.id;
	}

	public void setId(FreschkindamagePK id) {
		this.id = id;
	}
	
	public String getDamagetype() {
		return this.damagetype;
	}

	public void setDamagetype(String damagetype) {
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