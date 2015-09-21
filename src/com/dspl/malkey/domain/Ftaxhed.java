package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;


/**
 * The persistent class for the ftaxhed database table.
 * 
 */
@Entity
@Table(name="ftaxhed")
@NamedQueries({@NamedQuery(name="FtaxhedListAll", query="SELECT f FROM Ftaxhed f")})
public class Ftaxhed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String taxcomcode;

	@Column(nullable=false, precision=1)
	private BigDecimal active;

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

	@Column(nullable=false, length=50)
	private String taxcomname;

	
    public Ftaxhed() {
    }

	public String getTaxcomcode() {
		return this.taxcomcode;
	}

	public void setTaxcomcode(String taxcomcode) {
		this.taxcomcode = taxcomcode;
	}

	public BigDecimal getActive() {
		return this.active;
	}

	public void setActive(BigDecimal active) {
		this.active = active;
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

	public String getTaxcomname() {
		return this.taxcomname;
	}

	public void setTaxcomname(String taxcomname) {
		this.taxcomname = taxcomname;
	}

}