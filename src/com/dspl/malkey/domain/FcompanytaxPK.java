package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the fcompanytax database table.
 * 
 */
@Embeddable
public class FcompanytaxPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=10)
	private String companyid;

	@Column(unique=true, nullable=false, length=10)
	private String hiretypeid;

    public FcompanytaxPK() {
    }
	public String getCompanyid() {
		return this.companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getHiretypeid() {
		return this.hiretypeid;
	}
	public void setHiretypeid(String hiretypeid) {
		this.hiretypeid = hiretypeid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FcompanytaxPK)) {
			return false;
		}
		FcompanytaxPK castOther = (FcompanytaxPK)other;
		return 
			this.companyid.equals(castOther.companyid)
			&& this.hiretypeid.equals(castOther.hiretypeid);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.companyid.hashCode();
		hash = hash * prime + this.hiretypeid.hashCode();
		
		return hash;
    }
}