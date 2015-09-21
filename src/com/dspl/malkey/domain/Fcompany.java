package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;


/**
 * The persistent class for the fcompany database table.
 * 
 */
@Entity
@Table(name="fcompany")
@NamedQueries({@NamedQuery(name="FcompanyListAll", query="SELECT f FROM Fcompany f")})
@SqlResultSetMappings({
	@SqlResultSetMapping(name="compList", entities={@EntityResult(entityClass=Faccessory.class,
			fields={@FieldResult(name="companyid",column="companyid"),
					@FieldResult(name="description",column="description")
					})
	})
})
public class Fcompany implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String companyid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(nullable=false, length=254)
	private String description;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="recordid",nullable=false,updatable=false)
	private int recordid;

    public Fcompany() {
    }

	public String getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public Calendar getAdddate() {
		return this.adddate;
	}

	public void setAdddate(Calendar adddate) {
		this.adddate = adddate;
	}

	public String getAddmach() {
		return this.addmach;
	}

	public void setAddmach(String addmach) {
		this.addmach = addmach;
	}

	public String getAdduser() {
		return this.adduser;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

}