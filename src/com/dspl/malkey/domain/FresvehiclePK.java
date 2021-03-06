package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the fresvehicle database table.
 * 
 */
@Embeddable
public class FresvehiclePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=13)
	private String resno;

	@Column(unique=true, nullable=false, length=10)
	private String regno;

    public FresvehiclePK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FresvehiclePK)) {
			return false;
		}
		FresvehiclePK castOther = (FresvehiclePK)other;
		return 
			this.resno.equals(castOther.resno)
			&& this.regno.equals(castOther.regno);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.resno.hashCode();
		hash = hash * prime + this.regno.hashCode();
		
		return hash;
    }
}