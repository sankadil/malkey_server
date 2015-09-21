package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tabletype database table.
 * 
 */
@Entity
@Table(name="tabletype")
@NamedQueries({ @NamedQuery(name="TabletypeFindByType", query="SELECT t FROM Tabletype t WHERE t.id=:type")})
public class Tabletype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false,unique=true)
	private int id;

	@Column(length=50)
	private String type;

    public Tabletype() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}