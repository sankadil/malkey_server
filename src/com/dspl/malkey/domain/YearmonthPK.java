package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the YearMonth database table.
 * 
 */
@Embeddable
public class YearmonthPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	public String getCsettingscode() {
		return csettingscode;
	}

	public void setCsettingscode(String csettingscode) {
		this.csettingscode = csettingscode;
	}

	public int getNyear() {
		return nyear;
	}

	public void setNyear(int nyear) {
		this.nyear = nyear;
	}

	public int getNmonth() {
		return nmonth;
	}

	public void setNmonth(int nmonth) {
		this.nmonth = nmonth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((csettingscode == null) ? 0 : csettingscode.hashCode());
		result = prime * result + nmonth;
		result = prime * result + nyear;
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
		YearmonthPK other = (YearmonthPK) obj;
		if (csettingscode == null) {
			if (other.csettingscode != null)
				return false;
		} else if (!csettingscode.equals(other.csettingscode))
			return false;
		if (nmonth != other.nmonth)
			return false;
		if (nyear != other.nyear)
			return false;
		return true;
	}

	private String csettingscode;

	private int nyear;

	private int nmonth;

    public YearmonthPK() {
    }
	

}