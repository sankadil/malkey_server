package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fmasterlist database table.
 * 
 */
@Entity
@Table(name="fmasterlist")
@NamedQueries({@NamedQuery(name="FmasterlistListAll", query="SELECT f FROM Fmasterlist f")})
public class Fmasterlist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false, length=50)
	private String description;

	@Column(nullable=false, length=15)
	private String keyfield;

	@Id
	@Column(nullable=false, length=30)
	private String tablename;

    public Fmasterlist() {
    }

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeyfield() {
		return this.keyfield;
	}

	public void setKeyfield(String keyfield) {
		this.keyfield = keyfield;
	}

	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

}