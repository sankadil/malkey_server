package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fresclientdriver database table.
 * 
 */
@Entity
@Table(name="fresclientdriver")
@NamedQueries({
	@NamedQuery(name="FresclientdriverListAll", 			query="SELECT f FROM Fresclientdriver f"),
	@NamedQuery(name="FresclientdriverDeleteByResno", 		query="DELETE FROM Fresclientdriver f WHERE f.id.resno=:resno"),
	@NamedQuery(name="FresclientdriverListByResno", 		query="SELECT f FROM Fresclientdriver f WHERE (f.id.resno=:resno) ORDER BY f.id.driverid ASC")
	})
public class Fresclientdriver implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FresclientdriverPK id;

    public Fresclientdriver() {
    }

	public FresclientdriverPK getId() {
		return this.id;
	}

	public void setId(FresclientdriverPK id) {
		this.id = id;
	}
	
}