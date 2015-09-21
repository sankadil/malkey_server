package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ftaxdet database table.
 * 
 */
@Embeddable
public class FtaxdetPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=10)
	private String taxcomcode;

	@Column(unique=true, nullable=false, length=10)
	private String taxcode;

    public FtaxdetPK() {
    }
	public String getTaxcomcode() {
		return this.taxcomcode;
	}
	public void setTaxcomcode(String taxcomcode) {
		this.taxcomcode = taxcomcode;
	}
	public String getTaxcode() {
		return this.taxcode;
	}
	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FtaxdetPK)) {
			return false;
		}
		FtaxdetPK castOther = (FtaxdetPK)other;
		return 
			this.taxcomcode.equals(castOther.taxcomcode)
			&& this.taxcode.equals(castOther.taxcode);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.taxcomcode.hashCode();
		hash = hash * prime + this.taxcode.hashCode();
		
		return hash;
    }
}