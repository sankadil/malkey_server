package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the fresvehinv database table.
 * 
 */
@Embeddable
public class FresvehinvPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=10)
	private String resno;

	@Column(unique=true, nullable=false, length=10)
	private String regno;

	@Column(unique=true, nullable=false, length=10)
	private String invid;

    public FresvehinvPK() {
    }
	public String getResno() {
		return this.resno;
	}
	public void setResno(String resno) {
		this.resno = resno;
	}
	public String getRegno() {
		return this.regno;
	}
	public void setRegno(String regno) {
		this.regno = regno;
	}
	public String getInvid() {
		return this.invid;
	}
	public void setInvid(String invid) {
		this.invid = invid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FresvehinvPK)) {
			return false;
		}
		FresvehinvPK castOther = (FresvehinvPK)other;
		return 
			this.resno.equals(castOther.resno)
			&& this.regno.equals(castOther.regno)
			&& this.invid.equals(castOther.invid);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.resno.hashCode();
		hash = hash * prime + this.regno.hashCode();
		hash = hash * prime + this.invid.hashCode();
		
		return hash;
    }
}