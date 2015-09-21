package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;
import java.math.BigDecimal;


/**
 * The persistent class for the faccessory database table.
 * 
 */
@Entity
@Table(name="faccessory")
@NamedQueries({@NamedQuery(name="FaccessoryListAll", query="SELECT f FROM Faccessory f")})
@SqlResultSetMappings({
	@SqlResultSetMapping(name="accList", entities={@EntityResult(entityClass=Faccessory.class,
			fields={@FieldResult(name="accid",column="accid"),
					@FieldResult(name="description",column="description"),
					@FieldResult(name="make",column="make"),
					@FieldResult(name="model",column="model")
					})
	})
})

public class Faccessory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient
	private byte[] accimagedata;
	
	@Id
	@Column(unique=true, nullable=false, length=10)
	private String accid;

	@Column(nullable=false, length=10)
	private String acctypeid;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(nullable=false, length=254)
	private String description;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dpurchase;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dwarrantend;

	@Column(nullable=false, length=254)
	private String image;

	@Column(nullable=false, length=10)
	private String locationid;

	@Column(nullable=false, length=20)
	private String make;

	@Column(nullable=false, length=20)
	private String model;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(precision=15, scale=2)
	private BigDecimal value;

	@Column(nullable=false)
	private int qty;
	
    public Faccessory() {
    }

	public String getAccid() {
		return this.accid;
	}

	public void setAccid(String accid) {
		this.accid = accid;
	}

	public String getAcctypeid() {
		return this.acctypeid;
	}

	public void setAcctypeid(String acctypeid) {
		this.acctypeid = acctypeid;
	}

	public Calendar getAdddate() {
		return this.adddate;
	}

	public void setAdddate(Calendar adddate) {
		this.adddate = adddate;
	}

	public String getAddmach() {
		return this.addmach;
	}

	public void setAddmach(String addmach) {
		this.addmach = addmach;
	}

	public String getAdduser() {
		return this.adduser;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getDpurchase() {
		return this.dpurchase;
	}

	public void setDpurchase(Calendar dpurchase) {
		this.dpurchase = dpurchase;
	}

	public Calendar getDwarrantend() {
		return this.dwarrantend;
	}

	public void setDwarrantend(Calendar dwarrantend) {
		this.dwarrantend = dwarrantend;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLocationid() {
		return this.locationid;
	}

	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}

	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getQty() {
		return qty;
	}

	public void setAccimagedata(byte[] accimagedata) {
		this.accimagedata = accimagedata;
	}

	public byte[] getAccimagedata() {
		return accimagedata;
	}
}