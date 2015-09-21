package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Calendar;


/**
 * The persistent class for the fmaintenance database table.
 * 
 */
@Entity
@Table(name="fmaintenance")
@NamedQueries({@NamedQuery(name="FmaintenanceListAll", query="SELECT f FROM Fmaintenance f"),
	@NamedQuery(name="FmaintenanceListByStatusRegnoType", query="SELECT f FROM Fmaintenance f WHERE f.statusid=:statusid AND f.regno=:regno AND f.typeid=:typeid")})

@SqlResultSetMappings({
	@SqlResultSetMapping(name="mList", entities={@EntityResult(entityClass=Fmaintenance.class,
			fields={@FieldResult(name="refno",column="refno"),
					@FieldResult(name="regno",column="regno"),
					@FieldResult(name="frequency",column="frequency"),
					@FieldResult(name="typeid",column="typeid"),
					@FieldResult(name="ddue",column="ddue"),
					@FieldResult(name="raisedby",column="assignedtoid"),
					@FieldResult(name="assignedto",column="assignedto"),
					@FieldResult(name="priority",column="priority"),
					@FieldResult(name="comment",column="comment"),
					@FieldResult(name="statusid",column="statusid"),
					@FieldResult(name="actiontaken",column="actiontaken")
					})
	})
})

public class Fmaintenance implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Transient
	private String make;
	@Transient
	private String transmission;
	@Transient
	private String fueltype;
	@Transient
	private Timestamp lastdonedate;
	@Transient
	private Timestamp startdate;
	@Transient
	private Timestamp duedate;
	@Transient
	private Timestamp completeddate;
	@Transient 
	private Boolean sysgen;
	
	private int curmileage;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="DD/MM/YYYY")
	private Calendar compdate;
	
	private int compmileage;
	
	@Id
	@Column(unique=true, nullable=false, length=13)
	private String refno;
	
	@Column(nullable=false, length=10)
	private String actionedby;

	@Column(nullable=false, length=8000)
	private String actiontaken;

    @Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(nullable=false, length=12)
	private String assignedto;

	@Column(nullable=false, length=8000)
	private String comment;

    @Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="DD/MM/YYYY")
	private Calendar ddue;
    
    @Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="DD/MM/YYYY")
	private Calendar notifyon;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dstart;

	@Column(nullable=false, length=10)
	private String frequency;

	@Column(nullable=false)
	private int mileage;

	@Column(nullable=false)
	private int period;

	@Column(nullable=false, length=10)
	private String priority;

	@Column(nullable=false, length=10)
	private String raisedbytype;
	
	@Column(nullable=false, length=10)
	private String raisedby;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(nullable=false, length=10)
	private String regno;

	@Column(nullable=false, length=10)
	private String statusid;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar txndate;

	@Column(nullable=false, length=10)
	private String typeid;

    public Fmaintenance() {
    }
    
    public String getRefno() {
		return refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
	}

	public String getActionedby() {
		return this.actionedby;
	}

	public void setActionedby(String actionedby) {
		this.actionedby = actionedby;
	}

	public String getActiontaken() {
		return this.actiontaken;
	}

	public void setActiontaken(String actiontaken) {
		this.actiontaken = actiontaken;
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

	public String getAssignedto() {
		return this.assignedto;
	}

	public void setAssignedto(String assignedto) {
		this.assignedto = assignedto;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Calendar getDdue() {
		return this.ddue;
	}

	public void setDdue(Calendar ddue) {
		this.ddue = ddue;
	}

	public Calendar getDstart() {
		return this.dstart;
	}

	public void setDstart(Calendar dstart) {
		this.dstart = dstart;
	}

	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public int getMileage() {
		return this.mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getPeriod() {
		return this.period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getRaisedby() {
		return this.raisedby;
	}

	public void setRaisedby(String raisedby) {
		this.raisedby = raisedby;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getRegno() {
		return this.regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getStatusid() {
		return this.statusid;
	}

	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}

	public Calendar getTxndate() {
		return this.txndate;
	}

	public void setTxndate(Calendar txndate) {
		this.txndate = txndate;
	}

	public String getTypeid() {
		return this.typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public void setRaisedbytype(String raisedbytype) {
		this.raisedbytype = raisedbytype;
	}

	public String getRaisedbytype() {
		return raisedbytype;
	}

	public void setCurmileage(int curmileage) {
		this.curmileage = curmileage;
	}

	public int getCurmileage() {
		return curmileage;
	}

	public void setCompmileage(int compmileage) {
		this.compmileage = compmileage;
	}

	public int getCompmileage() {
		return compmileage;
	}

	public void setCompdate(Calendar compdate) {
		this.compdate = compdate;
	}

	public Calendar getCompdate() {
		return compdate;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getMake() {
		return make;
	}

	public void setLastdonedate(Timestamp lastdonedate) {
		this.lastdonedate = lastdonedate;
	}

	public Timestamp getLastdonedate() {
		return lastdonedate;
	}

	public void setDuedate(Timestamp duedate) {
		this.duedate = duedate;
	}

	public Timestamp getDuedate() {
		return duedate;
	}

	public void setCompleteddate(Timestamp completeddate) {
		this.completeddate = completeddate;
	}

	public Timestamp getCompleteddate() {
		return completeddate;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setFueltype(String fueltype) {
		this.fueltype = fueltype;
	}

	public String getFueltype() {
		return fueltype;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	public Timestamp getStartdate() {
		return startdate;
	}

	public void setNotifyon(Calendar notifyon) {
		this.notifyon = notifyon;
	}

	public Calendar getNotifyon() {
		return notifyon;
	}

	public void setSysgen(Boolean sysgen) {
		this.sysgen = sysgen;
	}

	public Boolean getSysgen() {
		return sysgen;
	}

}