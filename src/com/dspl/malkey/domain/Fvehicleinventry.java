package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fvehicleinventry database table.
 * 
 */
@Entity
@Table(name="fvehicleinventry")
@NamedQueries({@NamedQuery(name="FvehicleinventryListAll", query="SELECT f FROM Fvehicleinventry f"),
			@NamedQuery(name="FvehicleinventryByRegNo", query="SELECT f FROM Fvehicleinventry f WHERE f.id.regno=:regno"),
			@NamedQuery(name="FvehicleinventryDeleteByRegNo", query="DELETE  FROM Fvehicleinventry f WHERE f.id.regno=:regno")
			})

public class Fvehicleinventry implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FvehicleinventryPK id;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;
	
	@Column(nullable=false, length=10)
	private String adduser;
	
	@Column(nullable=false, length=50)
	private String uuid;

    public Fvehicleinventry() {
    }

	public FvehicleinventryPK getId() {
		return this.id;
	}

	public void setId(FvehicleinventryPK id) {
		this.id = id;
	}
	
	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
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