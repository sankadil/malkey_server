package com.dspl.malkey.domain;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;


/**
 * The persistent class for the fvehicleclass database table.
 * 
 */

@Entity
@Table(name="fvehicleclass")
@NamedQueries({@NamedQuery(name="FvehicleclassListAll", query="SELECT f FROM Fvehicleclass f")})

public class Fvehicleclass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String vehiclassid;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(nullable=false, length=254)
	private String description;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

    public Fvehicleclass() {
    }

	public String getVehiclassid() {
		return this.vehiclassid;
	}

	public void setVehiclassid(String vehiclassid) {
		this.vehiclassid = vehiclassid;
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