package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;


/**
 * The persistent class for the fsmssent database table.
 * 
 */
@Entity
@Table(name="fsmssent")
@NamedQueries({@NamedQuery(name="FsmssentListAll", query="SELECT f FROM Fsmssent f")})

public class Fsmssent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dsynched;

	@Column(nullable=false, length=8000)
	private String message;

	@Column(nullable=false, length=10)
	private String tsynched;

	@Column(nullable=false, length=256)
	private String sentstatus;
	
	@Column(nullable=false)
	private int schtosend;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar schdate;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(nullable=false, length=10)
	private String remindertype;

	@Column(nullable=false, length=20)
	private String tomobile;

    public Fsmssent() {
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

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getRemindertype() {
		return this.remindertype;
	}

	public void setRemindertype(String remindertype) {
		this.remindertype = remindertype;
	}

	public String getTomobile() {
		return this.tomobile;
	}

	public void setTomobile(String tomobile) {
		this.tomobile = tomobile;
	}

	public void setDsynched(Calendar dsynched) {
		this.dsynched = dsynched;
	}

	public Calendar getDsynched() {
		return dsynched;
	}

	public void setTsynched(String tsynched) {
		this.tsynched = tsynched;
	}

	public String getTsynched() {
		return tsynched;
	}

	public void setSentstatus(String sentstatus) {
		this.sentstatus = sentstatus;
	}

	public String getSentstatus() {
		return sentstatus;
	}

	public void setSchtosend(int schtosend) {
		this.schtosend = schtosend;
	}

	public int getSchtosend() {
		return schtosend;
	}

	public void setSchdate(Calendar schdate) {
		this.schdate = schdate;
	}

	public Calendar getSchdate() {
		return schdate;
	}
}