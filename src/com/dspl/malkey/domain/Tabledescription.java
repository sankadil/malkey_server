package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tabledescription database table.
 * 
 */
@Entity
@Table(name="tabledescription")
@NamedQueries({ @NamedQuery(name="TabledescriptionFindByTablename", query="SELECT t FROM Tabledescription t WHERE t.tablename=:tablename")})
public class Tabledescription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false,unique=true)
	private int recordid;

	@Column(length=200)
	private String description;

	@Column(length=100)
	private String tablename;

	private int type;

    public Tabledescription() {
    }

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

}