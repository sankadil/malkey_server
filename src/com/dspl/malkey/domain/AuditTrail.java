package com.dspl.malkey.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class AuditTrail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=1)
	private String type;

	@Column(length=1000)
	private String newvalue;

	@Column
	private Date updatedate;

	@Column(length=10)
	private String adduser;

	public AuditTrail(){
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNewvalue() {
		return newvalue;
	}

	public void setNewvalue(String newvalue) {
		this.newvalue = newvalue;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}

	public String getAdduser() {
		return adduser;
	}	
}
