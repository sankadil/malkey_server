package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;


/**
 * The persistent class for the femails database table.
 * 
 */
@Entity
@Table(name="femails")
@NamedQueries({@NamedQuery(name="FemailsListAll", query="SELECT f FROM Femails f"),
	@NamedQuery(name="FemailsListAllOnFolderType", query="SELECT f FROM Femails f WHERE f.foldertype=:ifolderType ORDER BY f.date,f.tsynched DESC")})

public class Femails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=254)
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

	@Column(nullable=false, length=8000)
	private String attnames;

	@Column(nullable=false, length=8000)
	private String body;
	
	@Column(nullable=false, length=8000)
	private String ccemail;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar date;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dsynched;

	@Column(nullable=false)
	private int foldertype;

	@Column(nullable=false, length=254)
	private String fromemail;

	@Column(nullable=false, length=8000)
	private String htmlbody;

	@Column(nullable=false)
	private int movedfrom;

	@Column(nullable=false)
	private int readstatus;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(nullable=false)
	private int sentstatus;

	@Column(nullable=false, length=254)
	private String subject;

	@Column(nullable=false, length=8000)
	private String toemail;

	@Column(nullable=false, length=10)
	private String tsynched;
	
	@Column(nullable=false, length=50)
	private String uniqattfld;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dmoved;
	
	@Transient
	private String filename;

	@Transient
	private String fileurl;

	@Transient
	private byte[] filedata;
	
	@Transient
	private int newlyadded;

	@Transient
	private Boolean isdeleted;
	

    public Femails() {
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

	public String getAttnames() {
		return this.attnames;
	}

	public void setAttnames(String attnames) {
		this.attnames = attnames;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBody() {
		return body;
	}

	public String getCcemail() {
		return this.ccemail;
	}

	public void setCcemail(String ccemail) {
		this.ccemail = ccemail;
	}

	public Calendar getDate() {
		return this.date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Calendar getDsynched() {
		return this.dsynched;
	}

	public void setDsynched(Calendar dsynched) {
		this.dsynched = dsynched;
	}

	public int getFoldertype() {
		return this.foldertype;
	}

	public void setFoldertype(int foldertype) {
		this.foldertype = foldertype;
	}

	public String getFromemail() {
		return this.fromemail;
	}

	public void setFromemail(String fromemail) {
		this.fromemail = fromemail;
	}

	public void setHtmlbody(String htmlbody) {
		this.htmlbody = htmlbody;
	}

	public String getHtmlbody() {
		return htmlbody;
	}

	public void setMovedfrom(int movedfrom) {
		this.movedfrom = movedfrom;
	}

	public int getMovedfrom() {
		return movedfrom;
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

	public int getSentstatus() {
		return this.sentstatus;
	}

	public void setSentstatus(int sentstatus) {
		this.sentstatus = sentstatus;
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

	public String getTsynched() {
		return this.tsynched;
	}

	public void setTsynched(String tsynched) {
		this.tsynched = tsynched;
	}

	public void setUniqattfld(String uniqattfld) {
		this.uniqattfld = uniqattfld;
	}

	public String getUniqattfld() {
		return uniqattfld;
	}

	public void setDmoved(Calendar dmoved) {
		this.dmoved = dmoved;
	}

	public Calendar getDmoved() {
		return dmoved;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	public byte[] getFiledata() {
		return filedata;
	}

	public void setFiledata(byte[] filedata) {
		this.filedata = filedata;
	}

	public int getNewlyadded() {
		return newlyadded;
	}

	public void setNewlyadded(int newlyadded) {
		this.newlyadded = newlyadded;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public Boolean getIsdeleted() {
		return isdeleted;
	}

}