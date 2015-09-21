package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the fdtftax database table.
 * 
 */
@Embeddable
public class FdtftaxPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=16)
	private String dtfref;

	@Column(unique=true, nullable=false)
	private int seq;
	
	@Column(unique=true, nullable=false)
	private String taxcode;

    public FdtftaxPK() {
    }
	public String getDtfref() {
		return this.dtfref;
	}
	public void setDtfref(String dtfref) {
		this.dtfref = dtfref;
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
		if (!(other instanceof FdtftaxPK)) {
			return false;
		}
		FdtftaxPK castOther = (FdtftaxPK)other;
		return 
			this.dtfref.equals(castOther.dtfref)
			&& (this.seq == castOther.seq);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.dtfref.hashCode();
		hash = hash * prime + this.seq;
		
		return hash;
    }
	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}
	public String getTaxcode() {
		return taxcode;
	}
}