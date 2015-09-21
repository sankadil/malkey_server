package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the fvehiclemodel database table.
 * 
 */
@Embeddable
public class FvehiclemodelPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=10)
	private String vehimodelid;

	@Column(unique=true, nullable=false, length=10)
	private String vehimakeid;

    public FvehiclemodelPK() {
    }
	public String getVehimodelid() {
		return this.vehimodelid;
	}
	public void setVehimodelid(String vehimodelid) {
		this.vehimodelid = vehimodelid;
	}
	public String getVehimakeid() {
		return this.vehimakeid;
	}
	public void setVehimakeid(String vehimakeid) {
		this.vehimakeid = vehimakeid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FvehiclemodelPK)) {
			return false;
		}
		FvehiclemodelPK castOther = (FvehiclemodelPK)other;
		return 
			this.vehimodelid.equals(castOther.vehimodelid)
			&& this.vehimakeid.equals(castOther.vehimakeid);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.vehimodelid.hashCode();
		hash = hash * prime + this.vehimakeid.hashCode();
		
		return hash;
    }
}