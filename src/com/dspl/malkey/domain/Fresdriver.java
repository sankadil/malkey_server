package com.dspl.malkey.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.*;


/**
 * The persistent class for the fresdriver database table.
 * 
 */
@Entity
@Table(name="fresdriver")
@NamedQueries({
	@NamedQuery(name="FresdriverListAll", 			query="SELECT f FROM Fresdriver f"),
	@NamedQuery(name="FresdriverDeleteByResno", 	query="DELETE FROM Fresdriver f WHERE f.id.resno=:resno"),
	@NamedQuery(name="FresdriverListByResno", 		query="SELECT f FROM Fresdriver f WHERE (f.id.resno=:resno) ORDER BY f.srvid,f.id.dout,f.id.timeout,f.priority ASC")
	/*@NamedQuery(name="FresdriverListByResno1", query="SELECT f FROM Fresdriver f WHERE f.id.resno=:resno ORDER BY f.id.empid ASC"),*/
/*	@NamedQuery(name="FresdriverListByResno", query="SELECT NEW com.dspl.malkey.domain.Fresdriver(f,r) FROM Fresdriver f,Fresdriverrate r WHERE (f.id.resno=:resno AND f.id.resno=r.id.resno AND f.id.empid=r.id.empid) ORDER BY f.id.empid ASC")
*/	
	})

public class Fresdriver implements Serializable,Cloneable  {
	private static final long serialVersionUID = 1L;

	@Transient
	private BigDecimal days;
	
	@EmbeddedId
	private FresdriverPK id;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(name="priority",nullable=false, length=10)
	private int priority;
	
	@Column(name="srvid",length=10)
	private String srvid;
	
	@Column(name="issrv")
	private Boolean issrv;
	
	@Transient
	private int isavailabile;
	
	@Transient
	private Fresdriverrate fresdriverrate;
	
	@Column(nullable=false, length=10)
	private String adduser;
	
	@Column(nullable=false, length=50)
	private String uuid;
	
	@Column(name="dispatch")
	private int dispatch;
	
	
	@Column(name="dmalkeyout")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dmalkeyout;
	
	@Column(name="timemalkeyout",length=10)
	private String timemalkeyout;
	
    @Column(nullable=false, length=8000,name="remarks")
	private String remarks;
	
    public Fresdriver(Fresdriver f,Fresdriverrate fresdriverrate) {
    	this.id=f.id;
    	this.recordid=f.recordid;
    	this.priority=f.priority;
    	this.fresdriverrate=fresdriverrate;
    }
    
    public Object clone() throws CloneNotSupportedException{
        Cloneable theClone = (Fresdriver) super.clone();
        return theClone;
    }
    
    public Fresdriver() {
    }

	public FresdriverPK getId() {
		return this.id;
	}

	public void setId(FresdriverPK id) {
		this.id = id;
	}
	
	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}

	public void setFresdriverrate(Fresdriverrate fresdriverrate) {
		this.fresdriverrate = fresdriverrate;
	}

	public Fresdriverrate getFresdriverrate() {
		return fresdriverrate;
	}

	public void setIsavailabile(int isavailabile) {
		this.isavailabile = isavailabile;
	}

	public int getIsavailabile() {
		return isavailabile;
	}

	public void setSrvid(String srvid) {
		this.srvid = srvid;
	}

	public String getSrvid() {
		return srvid;
	}

	public void setIssrv(Boolean issrv) {
		this.issrv = issrv;
	}

	public Boolean getIssrv() {
		return issrv;
	}

	public void setDays(BigDecimal days) {
		this.days = days;
	}

	public BigDecimal getDays() {
		return days;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}

	public String getAdduser() {
		return adduser;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setDispatch(int dispatch) {
		this.dispatch = dispatch;
	}

	public int getDispatch() {
		return dispatch;
	}

	public void setDmalkeyout(Calendar dmalkeyout) {
		this.dmalkeyout = dmalkeyout;
	}

	public Calendar getDmalkeyout() {
		return dmalkeyout;
	}

	public void setTimemalkeyout(String timemalkeyout) {
		this.timemalkeyout = timemalkeyout;
	}

	public String getTimemalkeyout() {
		return timemalkeyout;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return remarks;
	}


}