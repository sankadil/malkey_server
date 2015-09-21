package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the faccrate database table.
 * 
 */
@Embeddable
public class FaccratePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=10)
	private String accid;

	@Column(unique=true, nullable=false, length=10)
	private String clienttype;

	@Column(unique=true, nullable=false, length=10)
	private String ratetype;

    public FaccratePK() {
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
		if (!(other instanceof FaccratePK)) {
			return false;
		}
		FaccratePK castOther = (FaccratePK)other;
		return 
			this.accid.equals(castOther.accid)
			&& this.clienttype.equals(castOther.clienttype)
			&& this.ratetype.equals(castOther.ratetype);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.accid.hashCode();
		hash = hash * prime + this.clienttype.hashCode();
		hash = hash * prime + this.ratetype.hashCode();
		
		return hash;
    }
}