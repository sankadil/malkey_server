package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the fvehiclerate database table.
 * 
 */
@Embeddable
public class FvehicleratePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=10, name="vehimodelid")
	private String vehimodelid;

	@Column(unique=true, nullable=false, length=10, name="clienttype")
	private String clienttype;

	@Column(unique=true, nullable=false, length=10, name="hiretypeid")
	private String hiretypeid;

	@Column(unique=true, nullable=false, length=10, name="ratetype")
	private String ratetype;

    public FvehicleratePK() {
    }
	public String getVehimodelid() {
		return this.vehimodelid;
	}
	public void setVehimodelid(String vehimodelid) {
		this.vehimodelid = vehimodelid;
	}
	public String getClienttype() {
		return this.clienttype;
	}
	public void setClienttype(String clienttype) {
		this.clienttype = clienttype;
	}
	public String getHiretypeid() {
		return this.hiretypeid;
	}
	public void setHiretypeid(String hiretypeid) {
		this.hiretypeid = hiretypeid;
	}
	public String getRatetype() {
		return this.ratetype;
	}
	public void setRatetype(String ratetype) {
		this.ratetype = ratetype;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FvehicleratePK)) {
			return false;
		}
		FvehicleratePK castOther = (FvehicleratePK)other;
		return 
			this.vehimodelid.equals(castOther.vehimodelid)
			&& this.clienttype.equals(castOther.clienttype)
			&& this.hiretypeid.equals(castOther.hiretypeid)
			&& this.ratetype.equals(castOther.ratetype);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.vehimodelid.hashCode();
		hash = hash * prime + this.clienttype.hashCode();
		hash = hash * prime + this.hiretypeid.hashCode();
		hash = hash * prime + this.ratetype.hashCode();
		
		return hash;
    }
}