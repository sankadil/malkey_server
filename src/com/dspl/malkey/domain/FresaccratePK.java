package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the fresaccrate database table.
 * 
 */
@Embeddable
public class FresaccratePK implements Serializable,Cloneable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=13)
	private String resno;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(unique=true, nullable=false)
	private java.util.Date dfrom;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(unique=true, nullable=false)
	private java.util.Date dto;

	@Column(unique=true, nullable=false, length=10)
	private String accid;

	@Column(unique=true, nullable=false, length=10)
	private String clienttype;

	@Column(unique=true, nullable=false, length=10)
	private String ratetype;

    public Object clone() throws CloneNotSupportedException{
        Cloneable theClone = (FresaccratePK) super.clone();
        return theClone;
    }
    
    public FresaccratePK() {
    }
	public String getResno() {
		return this.resno;
	}
	public void setResno(String resno) {
		this.resno = resno;
	}
	public java.util.Date getDfrom() {
		return this.dfrom;
	}
	public void setDfrom(java.util.Date dfrom) {
		this.dfrom = dfrom;
	}
	public java.util.Date getDto() {
		return this.dto;
	}
	public void setDto(java.util.Date dto) {
		this.dto = dto;
	}
	public String getAccid() {
		return this.accid;
	}
	public void setAccid(String accid) {
		this.accid = accid;
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
		if (!(other instanceof FresaccratePK)) {
			return false;
		}
		FresaccratePK castOther = (FresaccratePK)other;
		return 
			this.resno.equals(castOther.resno)
			&& this.dfrom.equals(castOther.dfrom)
			&& this.dto.equals(castOther.dto)
			&& this.accid.equals(castOther.accid)
			&& this.clienttype.equals(castOther.clienttype)
			&& this.ratetype.equals(castOther.ratetype);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.resno.hashCode();
		hash = hash * prime + this.dfrom.hashCode();
		hash = hash * prime + this.dto.hashCode();
		hash = hash * prime + this.accid.hashCode();
		hash = hash * prime + this.clienttype.hashCode();
		hash = hash * prime + this.ratetype.hashCode();
		
		return hash;
    }
}