package com.dspl.malkey.report;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

@Entity
@NamedNativeQueries({
	@NamedNativeQuery(name="FrentvehinvRPT.NativeQuery1",
						query="SELECT a.resno, a.regno, a.invid, b.description, a.recordid, 0 accessory FROM Fresvehinv a " +
										"LEFT JOIN Fvinventrylist b ON a.invid=b.invid " +
										"WHERE a.resno=?1 and b.active=1 ORDER BY b.priority ", 
						resultSetMapping="FrentvehinvRPT.ResultMap1"),
	@NamedNativeQuery(name="FrentvehinvRPT.NativeQuery2",
						query="SELECT a.resno,a.resno regno,a.accid invid,b.description,b.recordid, 1 accessory FROM fresaccs a " +
										"LEFT JOIN faccessory b ON a.accid=b.accid " +
										"WHERE a.resno=?1", 
						resultSetMapping="FrentvehinvRPT.ResultMap1")
})

@SqlResultSetMappings({
	@SqlResultSetMapping(name="FrentvehinvRPT.ResultMap1", 
							entities={@EntityResult(entityClass=FrentvehinvRPT.class,
							fields={@FieldResult(name="resno",column="resno"),
									@FieldResult(name="regno",column="regno"),
									@FieldResult(name="invid",column="invid"),
									@FieldResult(name="description",column="description"),
									@FieldResult(name="recordid",column="recordid"),
									@FieldResult(name="accessory",column="accessory")
									})})
})

public class FrentvehinvRPT implements Serializable {

	/**
	 * This entity is made use of in the "rentagreement.rpt" Crystal Report
	 */
	private static final long serialVersionUID = 1L;

	// Fields from Fresvehinv
	@Column(length=15)
	private String resno;

	@Column(length=10)
	private String regno;
	
	@Column(length=10)
	private String invid;
	
	// Fields from Fvinventrylist
	@Column(length=254)
	private String description;
	
	@Id
	@Column(nullable=false, length=15)
	private int recordid;
	
	@Column
	private int accessory;

	public String getResno() {
		return resno;
	}

	public void setResno(String resno) {
		this.resno = resno;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getInvid() {
		return invid;
	}

	public void setInvid(String invid) {
		this.invid = invid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRecordid() {
		return recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public void setAccessory(int accessory) {
		this.accessory = accessory;
	}

	public int getAccessory() {
		return accessory;
	}
}
