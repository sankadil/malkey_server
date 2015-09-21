package com.dspl.malkey.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the fresvehicle database table.
 * 
 */
@Entity
@Table(name="fresvehicle")
@NamedQueries({
	@NamedQuery(name="FresvehicleListAll", query="SELECT f FROM Fresvehicle f"),
	@NamedQuery(name="FresvehicleDeleteByResno", query="DELETE FROM Fresvehicle f WHERE f.id.resno=:resno"),
	/*@NamedQuery(name="FresvehicleListByResNo1", query="SELECT f FROM Fresvehicle f WHERE f.id.resno=:resno ORDER BY f.priority ASC"),*/
	@NamedQuery(name="FresvehicleListByResNo", query="SELECT NEW com.dspl.malkey.domain.Fresvehicle(f,r) FROM Fresvehicle f,Fresvehiclerate r WHERE (f.id.resno=:resno AND f.id.resno=r.resno) ORDER BY f.priority ASC")
	})

public class Fresvehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FresvehiclePK id;

	@Column(nullable=false, length=10)
	private int priority;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	
	@Transient
	private Fresvehiclerate fresvehiclerate;
	
	@Transient
	private Fvehicleinventry fvehicleinventry;
	
	@Transient
	private List<Fresvehinv> lstFresvehinv;
	
	@Transient
	private List<Fvehicledamage> lstFvehicledamage;
	
	@Column(nullable=false, length=10)
	private String adduser;
	
	@Column(nullable=false, length=50)
	private String uuid;
				
    public Fresvehicle() {
    }
    
    public Fresvehicle(Fresvehicle f,Fresvehiclerate fresvehiclerate) {
    	this.id=f.id;
    	this.priority=f.priority;
    	this.recordid=f.recordid;
    	this.fresvehiclerate=fresvehiclerate;
    }

	public FresvehiclePK getId() {
		return this.id;
	}

	public void setId(FresvehiclePK id) {
		this.id = id;
	}
	
	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public void setFresvehiclerate(Fresvehiclerate fresvehiclerate) {
		this.fresvehiclerate = fresvehiclerate;
	}

	public Fresvehiclerate getFresvehiclerate() {
		return fresvehiclerate;
	}


	public void setLstFresvehinv(List<Fresvehinv> lstFresvehinv) {
		this.lstFresvehinv = lstFresvehinv;
	}

	public List<Fresvehinv> getLstFresvehinv() {
		return lstFresvehinv;
	}

	public void setLstFvehicledamage(List<Fvehicledamage> lstFvehicledamage) {
		this.lstFvehicledamage = lstFvehicledamage;
	}

	public List<Fvehicledamage> getLstFvehicledamage() {
		return lstFvehicledamage;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}

	public String getAdduser() {
		return adduser;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}





}