package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the farfdtf database table.
 * 
 */
@Embeddable
public class FarfdtfPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=16)
	private String dtfref;

	@Column(unique=true, nullable=false, length=16)
	private String dtfbil;

	@Column(unique=true, nullable=false)
	private int seq;

    public FarfdtfPK() {
    }
	public String getDtfref() {
		return this.dtfref;
	}
	public void setDtfref(String dtfref) {
		this.dtfref = dtfref;
	}
	public String getDtfbil() {
		return this.dtfbil;
	}
	public void setDtfbil(String dtfbil) {
		this.dtfbil = dtfbil;
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
		if (!(other instanceof FarfdtfPK)) {
			return false;
		}
		FarfdtfPK castOther = (FarfdtfPK)other;
		return 
			this.dtfref.equals(castOther.dtfref)
			&& this.dtfbil.equals(castOther.dtfbil)
			&& (this.seq == castOther.seq);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.dtfref.hashCode();
		hash = hash * prime + this.dtfbil.hashCode();
		hash = hash * prime + this.seq;
		
		return hash;
    }
}