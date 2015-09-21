package com.dspl.malkey.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;

/**
 * The primary key class for the fresdriverrate database table.
 * 
 */
@Embeddable
public class FresdriverratePK implements Serializable,Cloneable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=13)
	private String resno;

	@Column(unique=true, nullable=false, length=10)
	private String clienttype;

	@Column(unique=true, nullable=false, length=10)
	private String ratetype;

/*	
  
   @Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dout;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar din;
	
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

*/
	
/*	@Column(length=254)
	private String empid;
	public String getEmpid() {
		return this.empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}*/
    public Object clone() throws CloneNotSupportedException{
        Cloneable theClone =(FresdriverratePK) super.clone();
        return theClone;
    }
	
    public FresdriverratePK() {
    }
	public String getResno() {
		return this.resno;
	}
	public void setResno(String resno) {
		this.resno = resno;
	}
	public String getClienttype() {
		return this.clienttype;
	}
	public void setClienttype(String clienttype) {
		this.clienttype = clienttype;
	}
	public String getRatetype() {
		return this.ratetype;
	}
	public void setRatetype(String ratetype) {
		this.ratetype = ratetype;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FresdriverratePK)) {
			return false;
		}
		FresdriverratePK castOther = (FresdriverratePK)other;
		return 
			this.resno.equals(castOther.resno)
			&& this.clienttype.equals(castOther.clienttype)
			&& this.ratetype.equals(castOther.ratetype);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.resno.hashCode();
		hash = hash * prime + this.clienttype.hashCode();
		hash = hash * prime + this.ratetype.hashCode();
		
		return hash;
    }

}