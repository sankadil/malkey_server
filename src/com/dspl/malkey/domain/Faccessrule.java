package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the faccessrules database table.
 * 
 */
@Entity
@Table(name="faccessrules")
@NamedQueries({
@NamedQuery(name="farFindByUserTypeId",query="SELECT c FROM Faccessrule c WHERE c.id.usertypeid=:uti")
})
public class Faccessrule implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FaccessrulePK id;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="recordid",nullable=false,updatable=false)
	private int recordid;

    public Faccessrule() {
    }

	public FaccessrulePK getId() {
		return this.id;
	}

	public void setId(FaccessrulePK id) {
		this.id = id;
	}
	
	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

}