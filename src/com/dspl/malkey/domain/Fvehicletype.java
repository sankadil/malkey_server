package com.dspl.malkey.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;


/**
 * The persistent class for the fvehicletype database table.
 * 
 */
@Entity
@Table(name="fvehicletype")
@NamedQueries({@NamedQuery(name="FvehicletypeListAll", query="SELECT f FROM Fvehicletype f")})

public class Fvehicletype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient
	private byte[] vehicletypeimagedata;
	
	@Id
	@Column(unique=true, nullable=false, length=10)
	private String vehitypeid;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(nullable=false, length=254)
	private String description;

	@Column(nullable=false, length=254)
	private String image;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

    public Fvehicletype() {
    }

	public String getVehitypeid() {
		return this.vehitypeid;
	}

	public void setVehitypeid(String vehitypeid) {
		this.vehitypeid = vehitypeid;
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

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public void setVehicletypeimagedata(byte[] vehicletypeimagedata) {
		this.vehicletypeimagedata = vehicletypeimagedata;
	}

	public byte[] getVehicletypeimagedata() {
		return vehicletypeimagedata;
	}

}