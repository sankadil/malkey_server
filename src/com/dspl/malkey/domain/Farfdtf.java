package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.math.BigDecimal;


/**
 * The persistent class for the farfdtf database table.
 * 
 */
@Entity
@Table(name="farfdtf")
@NamedNativeQueries({
	@NamedNativeQuery(name="FarfdtfChangeResno", 
			query="UPDATE farfdtf SET resno=?2 WHERE resno=?1 AND dtftype=5")
})
public class Farfdtf implements Serializable {
/*
	String companyid, int subseq, FarfdtfPK id, String accodcr,
	String accoddb, String agnno, String coscod, String curcode,
	BigDecimal currate, BigDecimal days, int qty, String debcode,
	BigDecimal disper, BigDecimal dtfamt, BigDecimal dtfbal,
	BigDecimal dtfbal1, String dtfbranch, Calendar dtfchqdat,
	String dtfchqno, Calendar dtfdate, BigDecimal dtffamt,
	BigDecimal dtffbal, BigDecimal dtffbal1, BigDecimal dtffgst,
	BigDecimal dtffnbt, BigDecimal dtfgst, BigDecimal dtfnbt,
	String dtfremarks, String dtftype, String empid,
	BigDecimal gainloss, String gpost, String hiretypeid, int km,
	String minvno, BigDecimal nbtper, String opbal, BigDecimal pbtamt,
	BigDecimal pdisamt, BigDecimal pgamt, int recordid, String regno,
	String resno, String taxcomcode, BigDecimal tbamt,
	Calendar txndate, String txntime, String txnuser, String type,
	BigDecimal vatper, String oldresno
*/	
/*	public Farfdtf(Farfdtf newObject) {
		super();
		this.companyid = newObject.getcompanyid;
		this.subseq = newObject.getsubseq;
		this.id = newObject.getid;
		this.accodcr = newObject.getaccodcr;
		this.accoddb = newObject.getaccoddb;
		this.agnno = newObject.getagnno;
		this.coscod = newObject.getcoscod;
		this.curcode = newObject.getcurcode;
		this.currate = newObject.getcurrate;
		this.days = newObject.getdays;
		this.qty = newObject.getqty;
		this.debcode = newObject.getdebcode;
		this.disper = newObject.getdisper;
		this.dtfamt = newObject.getdtfamt;
		this.dtfbal = newObject.getdtfbal;
		this.dtfbal1 = newObject.getdtfbal1;
		this.dtfbranch = newObject.getdtfbranch;
		this.dtfchqdat = newObject.getdtfchqdat;
		this.dtfchqno = newObject.getdtfchqno;
		this.dtfdate = newObject.getdtfdate;
		this.dtffamt = newObject.getdtffamt;
		this.dtffbal = newObject.getdtffbal;
		this.dtffbal1 = newObject.getdtffbal1;
		this.dtffgst = newObject.getdtffgst;
		this.dtffnbt = newObject.getdtffnbt;
		this.dtfgst = newObject.getdtfgst;
		this.dtfnbt = newObject.getdtfnbt;
		this.dtfremarks = newObject.getdtfremarks;
		this.dtftype = newObject.getdtftype;
		this.empid = empid;
		this.gainloss = gainloss;
		this.gpost = gpost;
		this.hiretypeid = hiretypeid;
		this.km = km;
		this.minvno = minvno;
		this.nbtper = nbtper;
		this.opbal = opbal;
		this.pbtamt = pbtamt;
		this.pdisamt = pdisamt;
		this.pgamt = pgamt;
		this.recordid = recordid;
		this.regno = regno;
		this.resno = resno;
		this.taxcomcode = taxcomcode;
		this.tbamt = tbamt;
		this.txndate = txndate;
		this.txntime = txntime;
		this.txnuser = txnuser;
		this.type = type;
		this.vatper = vatper;
		this.oldresno = oldresno;
	}*/

	private static final long serialVersionUID = 1L;
	
	private String companyid;
	
	//@Transient
	private int subseq;
	
	@EmbeddedId
	private FarfdtfPK id;

	@Column(length=12)
	private String accodcr;

	@Column(length=12)
	private String accoddb;

	@Column(length=13)
	private String agnno;

	@Column(length=3)
	private String coscod;

	@Column(length=5)
	private String curcode;

	@Column(precision=6, scale=2)
	private BigDecimal currate;

	private BigDecimal days;
	
	private int qty;

	@Column(length=10)
	private String debcode;

	@Column(precision=4, scale=2)
	private BigDecimal disper;

	@Column(precision=15, scale=2)
	private BigDecimal dtfamt;

	@Column(precision=15, scale=2)
	private BigDecimal dtfbal;

	@Column(precision=15, scale=2)
	private BigDecimal dtfbal1;

	@Column(length=3)
	private String dtfbranch;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtfchqdat;

	@Column(length=10)
	private String dtfchqno;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtfdate;

	@Column(precision=15, scale=4)
	private BigDecimal dtffamt;

	@Column(precision=15, scale=2)
	private BigDecimal dtffbal;

	@Column(precision=15, scale=4)
	private BigDecimal dtffbal1;

	@Column(precision=15, scale=4)
	private BigDecimal dtffgst;

	@Column(precision=15, scale=4)
	private BigDecimal dtffnbt;

	@Column(precision=15, scale=2)
	private BigDecimal dtfgst;

	@Column(precision=15)
	private BigDecimal dtfnbt;

	@Column(length=100)
	private String dtfremarks;

	@Column(length=1)
	private String dtftype;

	@Column(length=12)
	private String empid;

	@Column(precision=15, scale=2)
	private BigDecimal gainloss;

	@Column(length=1)
	private String gpost;

	@Column(length=10)
	private String hiretypeid;

	private int km;

	@Column(length=10)
	private String minvno;

	@Column(precision=7, scale=4)
	private BigDecimal nbtper;

	@Column(length=1)
	private String opbal;

	@Column(precision=15, scale=2)
	private BigDecimal pbtamt;

	@Column(precision=15, scale=2)
	private BigDecimal pdisamt;

	@Column(precision=15, scale=2)
	private BigDecimal pgamt;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false)
	private int recordid;

	@Column(length=10)
	private String regno;

	@Column(length=13)
	private String resno;

	@Column(length=10)
	private String taxcomcode;

	@Column(precision=15, scale=2)
	private BigDecimal tbamt;

//	private Timestamp timestamp_column;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar txndate;

	@Column(length=10)
	private String txntime;

	@Column(length=10)
	private String txnuser;

	@Column(length=10)
	private String type;

	@Column(precision=7, scale=4)
	private BigDecimal vatper;

	@Column(length=13)
	private String oldresno;
	
    public Farfdtf() {
    }

	public FarfdtfPK getId() {
		return this.id;
	}

	public void setId(FarfdtfPK id) {
		this.id = id;
	}
	
	public String getAccodcr() {
		return this.accodcr;
	}

	public void setAccodcr(String accodcr) {
		this.accodcr = accodcr;
	}

	public String getAccoddb() {
		return this.accoddb;
	}

	public void setAccoddb(String accoddb) {
		this.accoddb = accoddb;
	}

	public String getAgnno() {
		return this.agnno;
	}

	public void setAgnno(String agnno) {
		this.agnno = agnno;
	}

	public String getCoscod() {
		return this.coscod;
	}

	public void setCoscod(String coscod) {
		this.coscod = coscod;
	}

	public String getCurcode() {
		return this.curcode;
	}

	public void setCurcode(String curcode) {
		this.curcode = curcode;
	}

	public BigDecimal getCurrate() {
		return this.currate;
	}

	public void setCurrate(BigDecimal currate) {
		this.currate = currate;
	}

	public String getDebcode() {
		return this.debcode;
	}

	public void setDebcode(String debcode) {
		this.debcode = debcode;
	}

	public BigDecimal getDisper() {
		return this.disper;
	}

	public void setDisper(BigDecimal disper) {
		this.disper = disper;
	}

	public BigDecimal getDtfamt() {
		return this.dtfamt;
	}

	public void setDtfamt(BigDecimal dtfamt) {
		this.dtfamt = dtfamt;
	}

	public BigDecimal getDtfbal() {
		return this.dtfbal;
	}

	public void setDtfbal(BigDecimal dtfbal) {
		this.dtfbal = dtfbal;
	}

	public BigDecimal getDtfbal1() {
		return this.dtfbal1;
	}

	public void setDtfbal1(BigDecimal dtfbal1) {
		this.dtfbal1 = dtfbal1;
	}

	public String getDtfbranch() {
		return this.dtfbranch;
	}

	public void setDtfbranch(String dtfbranch) {
		this.dtfbranch = dtfbranch;
	}

	public Calendar getDtfchqdat() {
		return this.dtfchqdat;
	}

	public void setDtfchqdat(Calendar dtfchqdat) {
		this.dtfchqdat = dtfchqdat;
	}

	public String getDtfchqno() {
		return this.dtfchqno;
	}

	public void setDtfchqno(String dtfchqno) {
		this.dtfchqno = dtfchqno;
	}

	public Calendar getDtfdate() {
		return this.dtfdate;
	}

	public void setDtfdate(Calendar dtfdate) {
		this.dtfdate = dtfdate;
	}

	public BigDecimal getDtffamt() {
		return this.dtffamt;
	}

	public void setDtffamt(BigDecimal dtffamt) {
		this.dtffamt = dtffamt;
	}

	public BigDecimal getDtffbal() {
		return this.dtffbal;
	}

	public void setDtffbal(BigDecimal dtffbal) {
		this.dtffbal = dtffbal;
	}

	public BigDecimal getDtffbal1() {
		return this.dtffbal1;
	}

	public void setDtffbal1(BigDecimal dtffbal1) {
		this.dtffbal1 = dtffbal1;
	}

	public BigDecimal getDtffgst() {
		return this.dtffgst;
	}

	public void setDtffgst(BigDecimal dtffgst) {
		this.dtffgst = dtffgst;
	}

	public BigDecimal getDtffnbt() {
		return this.dtffnbt;
	}

	public void setDtffnbt(BigDecimal dtffnbt) {
		this.dtffnbt = dtffnbt;
	}

	public BigDecimal getDtfgst() {
		return this.dtfgst;
	}

	public void setDtfgst(BigDecimal dtfgst) {
		this.dtfgst = dtfgst;
	}

	public BigDecimal getDtfnbt() {
		return this.dtfnbt;
	}

	public void setDtfnbt(BigDecimal dtfnbt) {
		this.dtfnbt = dtfnbt;
	}

	public String getDtfremarks() {
		return this.dtfremarks;
	}

	public void setDtfremarks(String dtfremarks) {
		this.dtfremarks = dtfremarks;
	}

	public String getDtftype() {
		return this.dtftype;
	}

	public void setDtftype(String dtftype) {
		this.dtftype = dtftype;
	}

	public String getEmpid() {
		return this.empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public BigDecimal getGainloss() {
		return this.gainloss;
	}

	public void setGainloss(BigDecimal gainloss) {
		this.gainloss = gainloss;
	}

	public String getGpost() {
		return this.gpost;
	}

	public void setGpost(String gpost) {
		this.gpost = gpost;
	}

	public String getHiretypeid() {
		return this.hiretypeid;
	}

	public void setHiretypeid(String hiretypeid) {
		this.hiretypeid = hiretypeid;
	}

	public int getKm() {
		return this.km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public String getMinvno() {
		return this.minvno;
	}

	public void setMinvno(String minvno) {
		this.minvno = minvno;
	}

	public BigDecimal getNbtper() {
		return this.nbtper;
	}

	public void setNbtper(BigDecimal nbtper) {
		this.nbtper = nbtper;
	}

	public String getOpbal() {
		return this.opbal;
	}

	public void setOpbal(String opbal) {
		this.opbal = opbal;
	}

	public BigDecimal getPbtamt() {
		return this.pbtamt;
	}

	public void setPbtamt(BigDecimal pbtamt) {
		this.pbtamt = pbtamt;
	}

	public BigDecimal getPdisamt() {
		return this.pdisamt;
	}

	public void setPdisamt(BigDecimal pdisamt) {
		this.pdisamt = pdisamt;
	}

	public BigDecimal getPgamt() {
		return this.pgamt;
	}

	public void setPgamt(BigDecimal pgamt) {
		this.pgamt = pgamt;
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

	public String getResno() {
		return this.resno;
	}

	public void setResno(String resno) {
		this.resno = resno;
	}

	public String getTaxcomcode() {
		return this.taxcomcode;
	}

	public void setTaxcomcode(String taxcomcode) {
		this.taxcomcode = taxcomcode;
	}

	public BigDecimal getTbamt() {
		return this.tbamt;
	}

	public void setTbamt(BigDecimal tbamt) {
		this.tbamt = tbamt;
	}

//	public Timestamp getTimestamp_column() {
//		return this.timestamp_column;
//	}
//
//	public void setTimestamp_column(Timestamp timestamp_column) {
//		this.timestamp_column = timestamp_column;
//	}

	public Calendar getTxndate() {
		return this.txndate;
	}

	public void setTxndate(Calendar txndate) {
		this.txndate = txndate;
	}

	public String getTxntime() {
		return this.txntime;
	}

	public void setTxntime(String txntime) {
		this.txntime = txntime;
	}

	public String getTxnuser() {
		return this.txnuser;
	}

	public void setTxnuser(String txnuser) {
		this.txnuser = txnuser;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getVatper() {
		return this.vatper;
	}

	public void setVatper(BigDecimal vatper) {
		this.vatper = vatper;
	}

	public void setDays(BigDecimal days) {
		this.days = days;
	}

	public BigDecimal getDays() {
		return days;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getQty() {
		return qty;
	}

	public void setSubseq(int subseq) {
		this.subseq = subseq;
	}

	public int getSubseq() {
		return subseq;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setOldresno(String oldresno) {
		this.oldresno = oldresno;
	}

	public String getOldresno() {
		return oldresno;
	}
}