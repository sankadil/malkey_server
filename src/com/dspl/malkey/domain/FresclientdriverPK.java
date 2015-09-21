package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the fresclientdriver database table.
 * 
 */
@Embeddable
public class FresclientdriverPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=13)
	private String resno;

	@Column(unique=true, nullable=false)
	private int driverid;

    public FresclientdriverPK() {
    }
	public String getResno() {
		return this.resno;
	}
	public void setResno(String resno) {
		this.resno = resno;
	}
	public int getDriverid() {
		return this.driverid;
	}
	public void setDriverid(int driverid) {
		this.driverid = driverid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FresclientdriverPK)) {
			return false;
		}
		FresclientdriverPK castOther = (FresclientdriverPK)other;
		return 
			this.resno.equals(castOther.resno)
			&& (this.driverid == castOther.driverid);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.resno.hashCode();
		hash = hash * prime + this.driverid;
		
		return hash;
    }
}