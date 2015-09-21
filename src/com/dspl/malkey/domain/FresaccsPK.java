package com.dspl.malkey.domain;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the fresaccs database table.
 * 
 */
@Embeddable
public class FresaccsPK implements Serializable,Cloneable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=13)
	private String resno;

	@Column(unique=true, nullable=false, length=10)
	private String accid;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dfrom;


	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dto;
	
    public Object clone() throws CloneNotSupportedException{
        Cloneable theClone =(FresaccsPK) super.clone();
        return theClone;
    }
    
    public FresaccsPK() {
    }
	public String getResno() {
		return this.resno;
	}
	public void setResno(String resno) {
		this.resno = resno;
	}
	public String getAccid() {
		return this.accid;
	}
	public void setAccid(String accid) {
		this.accid = accid;
	}

	public Calendar getDfrom() {
		return this.dfrom;
	}

	public void setDfrom(Calendar dfrom) {
		this.dfrom = dfrom;
	}

	public Calendar getDto() {
		return this.dto;
	}

	public void setDto(Calendar dto) {
		this.dto = dto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accid == null) ? 0 : accid.hashCode());
		result = prime * result + ((dfrom == null) ? 0 : dfrom.hashCode());
		result = prime * result + ((dto == null) ? 0 : dto.hashCode());
		result = prime * result + ((resno == null) ? 0 : resno.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FresaccsPK other = (FresaccsPK) obj;
		if (accid == null) {
			if (other.accid != null)
				return false;
		} else if (!accid.equals(other.accid))
			return false;
		if (dfrom == null) {
			if (other.dfrom != null)
				return false;
		} else if (!dfrom.equals(other.dfrom))
			return false;
		if (dto == null) {
			if (other.dto != null)
				return false;
		} else if (!dto.equals(other.dto))
			return false;
		if (resno == null) {
			if (other.resno != null)
				return false;
		} else if (!resno.equals(other.resno))
			return false;
		return true;
	}

	
}