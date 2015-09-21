package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fresvehinv database table.
 * 
 */
@Entity
@Table(name="fresvehinv")
@NamedQueries({
	@NamedQuery(name="FresvehinvListAll", query="SELECT f FROM Fresvehinv f"),
	@NamedQuery(name="FresvehinvDeleteByResno", query="DELETE  FROM Fresvehinv f WHERE f.id.resno=:resno"),
	@NamedQuery(name="FresvehinvResetCheckInByResno", query="UPDATE Fresvehinv f SET f.checkin='0' WHERE f.id.resno=:resno"),
	@NamedQuery(name="FresvehinvListByResno", query="SELECT f FROM Fresvehinv f WHERE f.id.resno=:resno")
	})
public class Fresvehinv implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FresvehinvPK id;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column
	private int checkout;
	
	@Column
	private int checkin;
	
	@Column(nullable=false, length=10)
	private String adduser;
	
	@Column(nullable=false, length=50)
	private String uuid;
	
    public Fresvehinv() {
    }

	public FresvehinvPK getId() {
		return this.id;
	}

	public void setId(FresvehinvPK id) {
		this.id = id;
	}
	
	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public void setCheckout(int checkout) {
		this.checkout = checkout;
	}

	public int getCheckout() {
		return checkout;
	}

	public void setCheckin(int checkin) {
		this.checkin = checkin;
	}

	public int getCheckin() {
		return checkin;
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