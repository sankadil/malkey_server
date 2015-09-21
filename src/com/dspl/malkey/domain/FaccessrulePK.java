package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the faccessrules database table.
 * 
 */
@Embeddable
public class FaccessrulePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=10)
	private String usertypeid;

	@Column(unique=true, nullable=false, length=10)
	private String transcode;

	@Column(unique=true, nullable=false, length=10)
	private String funccode;

    public FaccessrulePK() {
    }
	public String getUsertypeid() {
		return this.usertypeid;
	}
	public void setUsertypeid(String usertypeid) {
		this.usertypeid = usertypeid;
	}
	public String getTranscode() {
		return this.transcode;
	}
	public void setTranscode(String transcode) {
		this.transcode = transcode;
	}
	public String getFunccode() {
		return this.funccode;
	}
	public void setFunccode(String funccode) {
		this.funccode = funccode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FaccessrulePK)) {
			return false;
		}
		FaccessrulePK castOther = (FaccessrulePK)other;
		return 
			this.usertypeid.equals(castOther.usertypeid)
			&& this.transcode.equals(castOther.transcode)
			&& this.funccode.equals(castOther.funccode);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.usertypeid.hashCode();
		hash = hash * prime + this.transcode.hashCode();
		hash = hash * prime + this.funccode.hashCode();
		
		return hash;
    }
}