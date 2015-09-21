package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;


/**
 * The persistent class for the fgatepass database table.
 * 
 */
@Entity
@Table(name="fgatepass")
@NamedQueries({
	@NamedQuery(name="FgatepassListAll", query="SELECT f FROM Fgatepass f"),
	@NamedQuery(name="FgatepassVehicleMovement1", query="SELECT f FROM Fgatepass f WHERE f.outdate>=:df AND f.outdate<=:dt ORDER BY f.outdate,f.regno"),
	@NamedQuery(name="FgatepassVehicleMovement2", query="SELECT f FROM Fgatepass f WHERE f.outdate>=:df AND f.outdate<=:dt AND f.status=:st ORDER BY f.outdate,f.regno")
})

public class Fgatepass implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Transient
	private String vehimakeid;
	@Transient
	private String vehimodelid;
	@Transient
	private String name;
	@Transient
	private String locationid;
	@Transient
	private String locationtype;
	@Transient
	private String authorisedbyname;	
	@Transient
	private Timestamp tsoutdate;	
	@Transient
	private Timestamp tsindate;	
	@Transient
	private String bcode;	
	@Transient
	private String outfromlocdes;
	@Transient
	private String outtolocdes;
	
	
	@Column(nullable=false, length=10)
	private String outfromloc;
	
	@Column(nullable=false, length=10)
	private String outtoloc;
	
	@Column(nullable=false, length=10)
	private String infromloc;
	
	@Column(nullable=false, length=10)
	private String intoloc;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String passno;

	@Column(nullable=false, length=1)
	private String status;
	
	@Column(nullable=false, length=10)
	private String outtime;
	
	@Column(nullable=false, length=10)
	private String intime;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(nullable=false, length=50)
	private String addmach;

	@Column(nullable=false, length=10)
	private String adduser;

	@Column(nullable=false, length=12)
	private String authorisedby;

	@Column(nullable=false, length=15)
	private String empid;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar indate;

	@Column(nullable=false)
	private int infuellevel;

	@Column(nullable=false)
	private int inmileage;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar outdate;

	@Column(nullable=false)
	private int outfuellevel;

	@Column(nullable=false)
	private int outmileage;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(nullable=false, length=10)
	private String regno;

	@Column(nullable=false, length=50)
	private String remarks;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar txndate;

    public Fgatepass() {
    }

	public String getPassno() {
		return this.passno;
	}

	public void setPassno(String passno) {
		this.passno = passno;
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

	public String getAuthorisedby() {
		return this.authorisedby;
	}

	public void setAuthorisedby(String authorisedby) {
		this.authorisedby = authorisedby;
	}

	public String getEmpid() {
		return this.empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public Calendar getIndate() {
		return this.indate;
	}

	public void setIndate(Calendar indate) {
		this.indate = indate;
	}

	public int getInfuellevel() {
		return this.infuellevel;
	}

	public void setInfuellevel(int infuellevel) {
		this.infuellevel = infuellevel;
	}

	public int getInmileage() {
		return this.inmileage;
	}

	public void setInmileage(int inmileage) {
		this.inmileage = inmileage;
	}

	public Calendar getOutdate() {
		return this.outdate;
	}

	public void setOutdate(Calendar outdate) {
		this.outdate = outdate;
	}

	public int getOutfuellevel() {
		return this.outfuellevel;
	}

	public void setOutfuellevel(int outfuellevel) {
		this.outfuellevel = outfuellevel;
	}

	public int getOutmileage() {
		return this.outmileage;
	}

	public void setOutmileage(int outmileage) {
		this.outmileage = outmileage;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getRegno() {
		return this.regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Calendar getTxndate() {
		return this.txndate;
	}

	public void setTxndate(Calendar txndate) {
		this.txndate = txndate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setVehimakeid(String vehimakeid) {
		this.vehimakeid = vehimakeid;
	}

	public String getVehimakeid() {
		return vehimakeid;
	}

	public void setVehimodelid(String vehimodelid) {
		this.vehimodelid = vehimodelid;
	}

	public String getVehimodelid() {
		return vehimodelid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}

	public String getOuttime() {
		return outtime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	public String getIntime() {
		return intime;
	}

	public void setOutfromloc(String outfromloc) {
		this.outfromloc = outfromloc;
	}

	public String getOutfromloc() {
		return outfromloc;
	}

	public void setOuttoloc(String outtoloc) {
		this.outtoloc = outtoloc;
	}

	public String getOuttoloc() {
		return outtoloc;
	}

	public void setInfromloc(String infromloc) {
		this.infromloc = infromloc;
	}

	public String getInfromloc() {
		return infromloc;
	}

	public void setIntoloc(String intoloc) {
		this.intoloc = intoloc;
	}

	public String getIntoloc() {
		return intoloc;
	}

	public void setLocationtype(String locationtype) {
		this.locationtype = locationtype;
	}

	public String getLocationtype() {
		return locationtype;
	}

	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}

	public String getLocationid() {
		return locationid;
	}

	public void setAuthorisedbyname(String authorisedbyname) {
		this.authorisedbyname = authorisedbyname;
	}

	public String getAuthorisedbyname() {
		return authorisedbyname;
	}

	public void setTsoutdate(Timestamp tsoutdate) {
		this.tsoutdate = tsoutdate;
	}

	public Timestamp getTsoutdate() {
		return tsoutdate;
	}

	public void setBcode(String bcode) {
		this.bcode = bcode;
	}

	public String getBcode() {
		return bcode;
	}

	public void setOutfromlocdes(String outfromlocdes) {
		this.outfromlocdes = outfromlocdes;
	}

	public String getOutfromlocdes() {
		return outfromlocdes;
	}

	public void setOuttolocdes(String outtolocdes) {
		this.outtolocdes = outtolocdes;
	}

	public String getOuttolocdes() {
		return outtolocdes;
	}

	public void setTsindate(Timestamp tsindate) {
		this.tsindate = tsindate;
	}

	public Timestamp getTsindate() {
		return tsindate;
	}
}