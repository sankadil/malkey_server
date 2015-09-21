package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the fresaddcharge database table.
 * 
 */
@Entity
@Table(name="fresaddcharge")
@NamedQueries({
	@NamedQuery(name="FresaddchargeListAll", query="SELECT f FROM Fresaddcharge f"),
	@NamedQuery(name="FresaddchargeListByresno", query="SELECT f FROM Fresaddcharge f WHERE f.id.resno=:resno"),
	@NamedQuery(name="FresaddchargeDeleteByresno", query="DELETE  FROM Fresaddcharge f WHERE f.id.resno=:resno")
	})

public class Fresaddcharge implements Serializable,Cloneable  {
	private static final long serialVersionUID = 1L;

	@Transient
	private String addcharge;
	
	@Transient
	private String ctype;
	
	@EmbeddedId
	private FresaddchargePK id;

	@Column(precision=15, scale=2)
	private BigDecimal amount;

	@Column(precision=2, scale=2)
	private BigDecimal percentage;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;
	
	@Column(nullable=false, length=10)
	private String adduser;
	
	@Column(nullable=false, length=50)
	private String uuid;

    public Object clone() throws CloneNotSupportedException{
        Cloneable theClone =(Fresaddcharge) super.clone();
        return theClone;
    }
    
    public Fresaddcharge() {
    }

	public FresaddchargePK getId() {
		return this.id;
	}

	public void setId(FresaddchargePK id) {
		this.id = id;
	}
	
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPercentage() {
		return this.percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
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

	public void setAddcharge(String addcharge) {
		this.addcharge = addcharge;
	}

	public String getAddcharge() {
		return addcharge;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getCtype() {
		return ctype;
	}

}