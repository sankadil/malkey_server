package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the fanalysis database table.
 * 
 */
@Entity
@Table(name="fanalysis")
@NamedQueries({@NamedQuery(name="FanalysisListAll", query="SELECT f FROM Fanalysis f"),
	@NamedQuery(name="FanalysisByAnCodeOfVehicle", query="SELECT f FROM Fanalysis f WHERE f.typ='V' AND f.ancode=:ancode")})

public class Fanalysis implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String ancode;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(nullable=false, length=100)
	private String adec;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, updatable=false)
	private int recordid;

	@Column(nullable=false, length=1)
	private String typ;

    public Fanalysis() {
    }

	public String getAncode() {
		return this.ancode;
	}

	public void setAncode(String ancode) {
		this.ancode = ancode;
	}

	public String getAdduser() {
		return this.adduser;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}

	public String getAdec() {
		return this.adec;
	}

	public void setAdec(String adec) {
		this.adec = adec;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getTyp() {
		return this.typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

}