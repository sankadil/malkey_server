package com.dspl.malkey.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;

/**
 * The primary key class for the fresdriver database table.
 * 
 */
@Embeddable
public class FresdriverPK implements Serializable,Cloneable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="resno",unique=true, nullable=false, length=13)
	private String resno;

	@Column(name="empid",unique=true, nullable=false, length=254)
	private String empid;
	
	@Column(name="dout")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dout;
	
	@Column(name="din")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar din;
	
	@Column(name="timeout",length=10)
	private String timeout;
	
	@Column(name="timein",length=10)
	private String timein;
	
    public Object clone() throws CloneNotSupportedException{
        Cloneable theClone =(FresdriverPK) super.clone();
        return theClone;
    }
    
    public FresdriverPK() {
    }
	public String getResno() {
		return this.resno;
	}
	public void setResno(String resno) {
		this.resno = resno;
	}
	public String getEmpid() {
		return this.empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FresdriverPK)) {
			return false;
		}
		FresdriverPK castOther = (FresdriverPK)other;
		return 
			this.resno.equals(castOther.resno)
			&& this.empid.equals(castOther.empid);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.resno.hashCode();
		hash = hash * prime + this.empid.hashCode();
		
		return hash;
    }
	public void setDout(Calendar dout) {
		this.dout = dout;
	}
	public Calendar getDout() {
		return dout;
	}
	public void setDin(Calendar din) {
		this.din = din;
	}
	public Calendar getDin() {
		return din;
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