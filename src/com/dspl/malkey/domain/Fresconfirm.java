package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;


/**
 * The persistent class for the fresconfirm database table.
 * 
 */
@Entity
@Table(name="fresconfirm")
@NamedQueries({@NamedQuery(name="FresconfirmListAll", query="SELECT f FROM Fresconfirm f")})

public class Fresconfirm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, updatable=false)
	private int recordid;

	@Column(length=8000)
	private String comments;

	@Column(nullable=false)
	private int confemailsent;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dconfemailsent;
	
	@Column(nullable=false)
	private int custconfirmsts;
	
	@Column(nullable=false)
	private int gotfeedback;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar date;

	@Column(nullable=false, length=13)
	private String resno;

	@Column(nullable=false, length=254)
	private String attachment;

	@Column(nullable=false, length=254)
	private String custname;
	
	@Column(nullable=false, length=254)
	private String toemail;

    public Fresconfirm() {
    }

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getConfemailsent() {
		return this.confemailsent;
	}

	public void setConfemailsent(int confemailsent) {
		this.confemailsent = confemailsent;
	}

	public void setDconfemailsent(Calendar dconfemailsent) {
		this.dconfemailsent = dconfemailsent;
	}

	public Calendar getDconfemailsent() {
		return dconfemailsent;
	}

	public void setCustconfirmsts(int custconfirmsts) {
		this.custconfirmsts = custconfirmsts;
	}

	public int getCustconfirmsts() {
		return custconfirmsts;
	}

	public void setGotfeedback(int gotfeedback) {
		this.gotfeedback = gotfeedback;
	}

	public int getGotfeedback() {
		return gotfeedback;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Calendar getDate() {
		return date;
	}

	public String getResno() {
		return this.resno;
	}

	public void setResno(String resno) {
		this.resno = resno;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getCustname() {
		return custname;
	}

	public void setToemail(String toemail) {
		this.toemail = toemail;
	}

	public String getToemail() {
		return toemail;
	}

}