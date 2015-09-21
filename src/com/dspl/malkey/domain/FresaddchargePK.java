package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the fresaddcharge database table.
 * 
 */
@Embeddable
public class FresaddchargePK implements Serializable,Cloneable  {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=10)
	private String resno;

	@Column(unique=true, nullable=false, length=10)
	private String addchargeid;

	
    public Object clone() throws CloneNotSupportedException{
        Cloneable theClone =(FresaddchargePK) super.clone();
        return theClone;
    }
    
    public FresaddchargePK() {
    }
	public String getResno() {
		return this.resno;
	}
	public void setResno(String resno) {
		this.resno = resno;
	}
	public String getAddchargeid() {
		return this.addchargeid;
	}
	public void setAddchargeid(String addchargeid) {
		this.addchargeid = addchargeid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FresaddchargePK)) {
			return false;
		}
		FresaddchargePK castOther = (FresaddchargePK)other;
		return 
			this.resno.equals(castOther.resno)
			&& this.addchargeid.equals(castOther.addchargeid);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.resno.hashCode();
		hash = hash * prime + this.addchargeid.hashCode();
		
		return hash;
    }
}