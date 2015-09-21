package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Calendar;
import java.math.BigDecimal;


/**
 * The persistent class for the fothersrvrate database table.
 * 
 */
@Entity
@Table(name="fothersrvrate")
@NamedQueries({
	@NamedQuery(name="FothersrvrateListAll", query="SELECT f FROM Fothersrvrate f"),
//	@NamedQuery(name="FothersrvFindBySrvId", query="SELECT NEW com.dspl.malkey.domain.Fothersrvrate(f) FROM Fothersrvrate f WHERE f.id.srvid=:srvid")
	@NamedQuery(name="FothersrvFindBySrvId", query="SELECT f FROM Fothersrvrate f WHERE f.id.srvid=:srvid")
})
public class Fothersrvrate implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FothersrvratePK id;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(length=50)
	private String addmach;

	@Column(length=10)
	private String adduser;

	@Column(precision=23, scale=8)
	private BigDecimal rate;
	
	@Column(precision=15, scale=2)
	private BigDecimal standardrate;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

    public Fothersrvrate() {
    }

	public FothersrvratePK getId() {
		return this.id;
	}

	public void setId(FothersrvratePK id) {
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

	public void setStandardrate(BigDecimal standardrate) {
		this.standardrate = standardrate;
	}

	public BigDecimal getStandardrate() {
		return standardrate;
	}

}