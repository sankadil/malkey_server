package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.math.BigDecimal;


/**
 * The persistent class for the fvehicle database table.
 * 
 */

@Entity
@Table(name="fvehicle")
@NamedQueries({@NamedQuery(name="FvehicleListAll", query="SELECT f FROM Fvehicle f")})
@SqlResultSetMappings({
	@SqlResultSetMapping(name="vList1", entities={@EntityResult(entityClass=Fvehicle.class,
			fields={@FieldResult(name="regno",column="regno"),
					@FieldResult(name="vehiclassid",column="vehiclassid"),
					@FieldResult(name="vehitypeid",column="vehitypeid"),
					@FieldResult(name="vehimakeid",column="vehimakeid"),
					@FieldResult(name="vehimodelid",column="vehimodelid"),
					@FieldResult(name="inscompany",column="inscompany"),
					@FieldResult(name="vehitransid",column="vehitransid"),
					@FieldResult(name="fueltypeid",column="fueltypeid"),
					@FieldResult(name="colourid",column="colourid"),
					@FieldResult(name="jumpseats",column="colcode"),
					@FieldResult(name="image",column="image"),
					@FieldResult(name="orgmileage",column="imagecnt"),
					@FieldResult(name="curmileage",column="curmileage"),
					@FieldResult(name="ownertype",column="ownertype")
					})
	}),
	@SqlResultSetMapping(name="vList2", entities={@EntityResult(entityClass=Fvehicle.class,
			fields={@FieldResult(name="regno",column="resno"),
					@FieldResult(name="addmach",column="regno"),
					@FieldResult(name="adduser",column="cohirestsid")
					})
	})
})				
public class Fvehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String regno;
	
	@Transient
	private String make;
	@Transient
	private Timestamp tsdpurchase;
	@Transient 
	private String seatingcapacity;
	@Transient
	private String status;
	@Transient
	private Boolean selected;

	public String getModifiedmach() {
		return modifiedmach;
	}

	public void setModifiedmach(String modifiedmach) {
		this.modifiedmach = modifiedmach;
	}

	public String getModifieduser() {
		return modifieduser;
	}

	public void setModifieduser(String modifieduser) {
		this.modifieduser = modifieduser;
	}

	public Calendar getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(Calendar modifieddate) {
		this.modifieddate = modifieddate;
	}

	@Column(length=50,name="modifiedmach")
	private String modifiedmach;
	
	@Column(length=10,name="modifieduser")
	private String modifieduser;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modifieddate")
	private Calendar modifieddate;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(nullable=false, length=50)
	private String chassisno;

	@Column(nullable=false, length=10)
	private String colourid;

	@Column(nullable=false)
	private int curmileage;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar demission;

	@Column(nullable=false, length=254)
	private String description;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dfitness;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dpurchase;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar drevlicense;

	@Column(nullable=false, length=50)
	private String engineno;

	@Column(nullable=false, length=10)
	private String engsizeid;

	@Column(nullable=false, length=10)
	private String fleetid;

	@Column(precision=15, scale=2)
	private BigDecimal fuelconsump;
	
	@Column(nullable=false)
	private int fuellevel;
	
	@Column(precision=15, scale=2)
	private BigDecimal fueltankval;

	@Column(nullable=false, length=10)
	private String fueltypeid;

	@Column(nullable=false, length=254)
	private String image;

	@Column(precision=15, scale=2)
	private BigDecimal insamount;

	@Column(nullable=false, length=254)
	private String inscompany;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar insdexpiry;

	@Column(precision=15, scale=2)
	private BigDecimal insncb;

	@Column(precision=15, scale=2)
	private BigDecimal inspolexces;

	@Column(nullable=false, length=10)
	private String policyid;

	@Column(nullable=false, length=50)
	private String inspolicyno;

	@Column(nullable=false)
	private int jumpseats;

	@Column(precision=15, scale=2)
	private BigDecimal leaseamt;

	@Column(nullable=false, length=10)
	private String leasecomid;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar leasedlastpay;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar leasedstart;

	@Column(precision=15, scale=2)
	private BigDecimal leasemonthlyamt;

	@Column(nullable=false)
	private int leasenoofmonth;

	@Column(nullable=false, length=10)
	private String locationid;

	@Column(nullable=false)
	private int mainseats;

	@Column(nullable=false)
	private int orgmileage;

	@Column(nullable=false, length=254)
	private String ownercompanyid;

	@Column(nullable=false, length=10)
	private String ownerid;

	@Column(nullable=false, length=10)
	private String ownertype;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(nullable=false)
	private int sparekeys;

	@Column(precision=15, scale=2)
	private BigDecimal value;

	@Column(nullable=false, length=10)
	private String vehiclassid;

	@Column(nullable=false, length=10)
	private String vehimakeid;

	@Column(nullable=false, length=10)
	private String vehimodelid;

	@Column(nullable=false, length=10)
	private String vehistsid;

	@Column(nullable=false, length=10)
	private String vehitransid;

	@Column(nullable=false, length=10)
	private String vehitypeid;
	
	@Column(nullable=false)
	private int year;
	
	@Column(nullable=false, length=50)
	private String uuid;

	@Column(nullable=false)
	private int dummyvehi;
	
	
    public Fvehicle() {
    }

	public String getRegno() {
		return this.regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
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

	public String getChassisno() {
		return this.chassisno;
	}

	public void setChassisno(String chassisno) {
		this.chassisno = chassisno;
	}

	public String getColourid() {
		return this.colourid;
	}

	public void setColourid(String colourid) {
		this.colourid = colourid;
	}

	public int getCurmileage() {
		return this.curmileage;
	}

	public void setCurmileage(int curmileage) {
		this.curmileage = curmileage;
	}

	public Calendar getDemission() {
		return this.demission;
	}

	public void setDemission(Calendar demission) {
		this.demission = demission;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getDfitness() {
		return this.dfitness;
	}

	public void setDfitness(Calendar dfitness) {
		this.dfitness = dfitness;
	}

	public Calendar getDpurchase() {
		return this.dpurchase;
	}

	public void setDpurchase(Calendar dpurchase) {
		this.dpurchase = dpurchase;
	}

	public Calendar getDrevlicense() {
		return this.drevlicense;
	}

	public void setDrevlicense(Calendar drevlicense) {
		this.drevlicense = drevlicense;
	}

	public String getEngineno() {
		return this.engineno;
	}

	public void setEngineno(String engineno) {
		this.engineno = engineno;
	}

	public String getEngsizeid() {
		return this.engsizeid;
	}

	public void setEngsizeid(String engsizeid) {
		this.engsizeid = engsizeid;
	}

	public String getFleetid() {
		return this.fleetid;
	}

	public void setFleetid(String fleetid) {
		this.fleetid = fleetid;
	}

	public BigDecimal getFuelconsump() {
		return this.fuelconsump;
	}

	public void setFuelconsump(BigDecimal fuelconsump) {
		this.fuelconsump = fuelconsump;
	}

	public void setFuellevel(int fuellevel) {
		this.fuellevel = fuellevel;
	}

	public int getFuellevel() {
		return fuellevel;
	}

	public BigDecimal getFueltankval() {
		return this.fueltankval;
	}

	public void setFueltankval(BigDecimal fueltankval) {
		this.fueltankval = fueltankval;
	}

	public String getFueltypeid() {
		return this.fueltypeid;
	}

	public void setFueltypeid(String fueltypeid) {
		this.fueltypeid = fueltypeid;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getInsamount() {
		return this.insamount;
	}

	public void setInsamount(BigDecimal insamount) {
		this.insamount = insamount;
	}

	public String getInscompany() {
		return this.inscompany;
	}

	public void setInscompany(String inscompany) {
		this.inscompany = inscompany;
	}

	public Calendar getInsdexpiry() {
		return this.insdexpiry;
	}

	public void setInsdexpiry(Calendar insdexpiry) {
		this.insdexpiry = insdexpiry;
	}

	public BigDecimal getInsncb() {
		return this.insncb;
	}

	public void setInsncb(BigDecimal insncb) {
		this.insncb = insncb;
	}

	public BigDecimal getInspolexces() {
		return this.inspolexces;
	}

	public void setInspolexces(BigDecimal inspolexces) {
		this.inspolexces = inspolexces;
	}

	public void setPolicyid(String policyid) {
		this.policyid = policyid;
	}

	public String getPolicyid() {
		return policyid;
	}

	public String getInspolicyno() {
		return this.inspolicyno;
	}

	public void setInspolicyno(String inspolicyno) {
		this.inspolicyno = inspolicyno;
	}

	public int getJumpseats() {
		return this.jumpseats;
	}

	public void setJumpseats(int jumpseats) {
		this.jumpseats = jumpseats;
	}

	public BigDecimal getLeaseamt() {
		return this.leaseamt;
	}

	public void setLeaseamt(BigDecimal leaseamt) {
		this.leaseamt = leaseamt;
	}

	public String getLeasecomid() {
		return this.leasecomid;
	}

	public void setLeasecomid(String leasecomid) {
		this.leasecomid = leasecomid;
	}

	public Calendar getLeasedlastpay() {
		return this.leasedlastpay;
	}

	public void setLeasedlastpay(Calendar leasedlastpay) {
		this.leasedlastpay = leasedlastpay;
	}

	public Calendar getLeasedstart() {
		return this.leasedstart;
	}

	public void setLeasedstart(Calendar leasedstart) {
		this.leasedstart = leasedstart;
	}

	public BigDecimal getLeasemonthlyamt() {
		return this.leasemonthlyamt;
	}

	public void setLeasemonthlyamt(BigDecimal leasemonthlyamt) {
		this.leasemonthlyamt = leasemonthlyamt;
	}

	public int getLeasenoofmonth() {
		return this.leasenoofmonth;
	}

	public void setLeasenoofmonth(int leasenoofmonth) {
		this.leasenoofmonth = leasenoofmonth;
	}

	public String getLocationid() {
		return this.locationid;
	}

	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}

	public int getMainseats() {
		return this.mainseats;
	}

	public void setMainseats(int mainseats) {
		this.mainseats = mainseats;
	}

	public int getOrgmileage() {
		return this.orgmileage;
	}

	public void setOrgmileage(int orgmileage) {
		this.orgmileage = orgmileage;
	}

	public void setOwnercompanyid(String ownercompanyid) {
		this.ownercompanyid = ownercompanyid;
	}

	public String getOwnercompanyid() {
		return ownercompanyid;
	}

	public String getOwnerid() {
		return this.ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	public String getOwnertype() {
		return this.ownertype;
	}

	public void setOwnertype(String ownertype) {
		this.ownertype = ownertype;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public int getSparekeys() {
		return this.sparekeys;
	}

	public void setSparekeys(int sparekeys) {
		this.sparekeys = sparekeys;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getVehiclassid() {
		return this.vehiclassid;
	}

	public void setVehiclassid(String vehiclassid) {
		this.vehiclassid = vehiclassid;
	}

	public String getVehimakeid() {
		return this.vehimakeid;
	}

	public void setVehimakeid(String vehimakeid) {
		this.vehimakeid = vehimakeid;
	}

	public String getVehimodelid() {
		return this.vehimodelid;
	}

	public void setVehimodelid(String vehimodelid) {
		this.vehimodelid = vehimodelid;
	}

	public String getVehistsid() {
		return this.vehistsid;
	}

	public void setVehistsid(String vehistsid) {
		this.vehistsid = vehistsid;
	}

	public String getVehitransid() {
		return this.vehitransid;
	}

	public void setVehitransid(String vehitransid) {
		this.vehitransid = vehitransid;
	}

	public String getVehitypeid() {
		return this.vehitypeid;
	}

	public void setVehitypeid(String vehitypeid) {
		this.vehitypeid = vehitypeid;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setTsdpurchase(Timestamp tsdpurchase) {
		this.tsdpurchase = tsdpurchase;
	}

	public Timestamp getTsdpurchase() {
		return tsdpurchase;
	}

	public void setSeatingcapacity(String seatingcapacity) {
		this.seatingcapacity = seatingcapacity;
	}

	public String getSeatingcapacity() {
		return seatingcapacity;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getMake() {
		return make;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setDummyvehi(int dummyvehi) {
		this.dummyvehi = dummyvehi;
	}

	public int getDummyvehi() {
		return dummyvehi;
	}

}