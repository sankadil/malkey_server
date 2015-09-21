package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the fresrates database table.
 * 
 */
@Embeddable
public class FresratesPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=10)
	private String resno;

	@Column(unique=true, nullable=false)
	private int ratetype;

    public FresratesPK() {
    }
	public String getResno() {
		return this.resno;
	}
	public void setResno(String resno) {
		this.resno = resno;
	}
	public int getRatetype() {
		return this.ratetype;
	}
	public void setRatetype(int ratetype) {
		this.ratetype = ratetype;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FresratesPK)) {
			return false;
		}
		FresratesPK castOther = (FresratesPK)other;
		return 
			this.resno.equals(castOther.resno)
			&& (this.ratetype == castOther.ratetype);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.resno.hashCode();
		hash = hash * prime + this.ratetype;
		
		return hash;
    }
}