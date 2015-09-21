package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the finvdet database table.
 * 
 */
@Embeddable
public class FinvdetPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="invno",unique=true, nullable=false, length=13)
	private String invno;

	@Column(name="seq",unique=true, nullable=false)
	private int seq;

    public FinvdetPK() {
    }
	public String getInvno() {
		return this.invno;
	}
	public void setInvno(String invno) {
		this.invno = invno;
	}
	public int getSeq() {
		return this.seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FinvdetPK)) {
			return false;
		}
		FinvdetPK castOther = (FinvdetPK)other;
		return 
			this.invno.equals(castOther.invno)
			&& (this.seq == castOther.seq);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.invno.hashCode();
		hash = hash * prime + this.seq;
		
		return hash;
    }
}