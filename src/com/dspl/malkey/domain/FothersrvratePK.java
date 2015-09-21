package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the fothersrvrate database table.
 * 
 */
@Embeddable
public class FothersrvratePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=10)
	private String srvid;

	@Column(unique=true, nullable=false, length=10)
	private String clienttype;

	@Column(unique=true, nullable=false, length=10)
	private String ratetype;

    public FothersrvratePK() {
    }
	public String getSrvid() {
		return this.srvid;
	}
	public void setSrvid(String srvid) {
		this.srvid = srvid;
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
		if (!(other instanceof FothersrvratePK)) {
			return false;
		}
		FothersrvratePK castOther = (FothersrvratePK)other;
		return 
			this.srvid.equals(castOther.srvid)
			&& this.clienttype.equals(castOther.clienttype)
			&& this.ratetype.equals(castOther.ratetype);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.srvid.hashCode();
		hash = hash * prime + this.clienttype.hashCode();
		hash = hash * prime + this.ratetype.hashCode();
		
		return hash;
    }
}