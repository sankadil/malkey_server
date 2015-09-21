package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Calendar;


/**
 * The persistent class for the femailinbox database table.
 * 
 */
@Entity
@Table(name="femailinbox")
@NamedQueries({@NamedQuery(name="FemailinboxListAll", query="SELECT f FROM Femailinbox f")})

public class Femailinbox implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String emailid;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(nullable=false, length=8000)
	private String attachment;

	@Column(nullable=false, length=254)
	private String ccemail;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dreceived;

	@Column(nullable=false, length=254)
	private String fromemail;

	@Column(nullable=false, length=8000)
	private String message;

	@Column(nullable=false)
	private int readstatus;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(nullable=false, length=254)
	private String subject;

	@Column(nullable=false, length=254)
	private String toemail;

    public Femailinbox() {
    }

	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
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

	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getCcemail() {
		return this.ccemail;
	}

	public void setCcemail(String ccemail) {
		this.ccemail = ccemail;
	}

	public Calendar getDreceived() {
		return this.dreceived;
	}

	public void setDreceived(Calendar dreceived) {
		this.dreceived = dreceived;
	}

	public String getFromemail() {
		return this.fromemail;
	}

	public void setFromemail(String fromemail) {
		this.fromemail = fromemail;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getReadstatus() {
		return this.readstatus;
	}

	public void setReadstatus(int readstatus) {
		this.readstatus = readstatus;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getToemail() {
		return this.toemail;
	}

	public void setToemail(String toemail) {
		this.toemail = toemail;
	}

}