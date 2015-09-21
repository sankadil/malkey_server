package com.dspl.malkey.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;

/**
 * The primary key class for the fresothsrv database table.
 * 
 */
@Embeddable
public class FresothsrvPK implements Serializable,Cloneable  {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=13)
	private String resno;

	@Column(unique=true, nullable=false, length=10)
	private String srvid;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dfrom;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dto;

	@Column( length=10)
	private String timeout;
	
	@Column( length=10)
	private String timein;
	
    public Object clone() throws CloneNotSupportedException{
        Cloneable theClone = (FresothsrvPK) super.clone();
        return theClone;
    }
    
    public FresothsrvPK() {
    }
	public String getResno() {
		return this.resno;
	}
	public void setResno(String resno) {
		this.resno = resno;
	}
	public String getSrvid() {
		return this.srvid;
	}
	public void setSrvid(String srvid) {
		this.srvid = srvid;
	}

	public Calendar getDfrom() {
		return this.dfrom;
	}

	public void setDfrom(Calendar dfrom) {
		this.dfrom = dfrom;
	}

	public Calendar getDto() {
		return this.dto;
	}

	public void setDto(Calendar dto) {
		this.dto = dto;
	}
	
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimein(String timein) {
		this.timein = timein;
	}

	public String getTimein() {
		return timein;
	}
}