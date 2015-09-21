package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;


/**
 * The persistent class for the fquote database table.
 * 
 */
@Entity
@Table(name="fquote")
@NamedQueries({@NamedQuery(name="FquoteListAll", query="SELECT f FROM Fquote f")})

public class Fquote implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true, nullable=false, length=10)
	private String quoteno;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(precision=18)
	private BigDecimal advance;

	@Column(nullable=false, length=10)
	private String clientid;

	@Column(precision=18)
	private BigDecimal deposit;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar din;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dout;

	@Column(nullable=false, length=10)
	private String hiretypeid;

	@Column(nullable=false, length=8000)
	private String itinerary;

	@Column(nullable=false)
	private int noofday;
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

    public Fquote() {
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

	public BigDecimal getAdvance() {
		return this.advance;
	}

	public void setAdvance(BigDecimal advance) {
		this.advance = advance;
	}

	public String getClientid() {
		return this.clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public BigDecimal getDeposit() {
		return this.deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public Calendar getDin() {
		return this.din;
	}

	public void setDin(Calendar din) {
		this.din = din;
	}

	public Calendar getDout() {
		return this.dout;
	}

	public void setDout(Calendar dout) {
		this.dout = dout;
	}

	public String getHiretypeid() {
		return this.hiretypeid;
	}

	public void setHiretypeid(String hiretypeid) {
		this.hiretypeid = hiretypeid;
	}

	public String getItinerary() {
		return this.itinerary;
	}

	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}

	public int getNoofday() {
		return this.noofday;
	}

	public void setNoofday(int noofday) {
		this.noofday = noofday;
	}

	public String getQuoteno() {
		return this.quoteno;
	}

	public void setQuoteno(String quoteno) {
		this.quoteno = quoteno;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

}