package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fdebtorstatus database table.
 * 
 */
@Entity
@Table(name="fdebtorstatus")
@NamedQueries({@NamedQuery(name="FdebtorstatusListAll", query="SELECT f FROM Fdebtorstatus f")})
public class Fdebtorstatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=2)
	private String id;

	@Column(nullable=false, length=20)
	private String description;

    public Fdebtorstatus() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}