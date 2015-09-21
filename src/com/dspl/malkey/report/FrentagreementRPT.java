package com.dspl.malkey.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Frentagreement
*/

@Entity 
@NamedNativeQueries({
	@NamedNativeQuery(name="FrentagreementRPT.NativeQuery1",
			query="SELECT a.agrno,a.resno,a.parentref,a.cimileage,a.debcode,a.comileage,a.deposit,a.din,a.dout,a.hiretypeid,a.cohirestsid,a.itinerary," +
							"a.codamage,a.cidamage,a.cofuellevel,a.cifuellevel,a.noofday,ISNULL(a.discount_xmile, 0.0) discount_xmile," +
							"a.cidamagers,a.ciother,a.cixsmileage,a.cidetenhrs,a.cinightout,a.cifueldiff,a.total,a.nettotal,a.taxamt," +
							"a.totaltaxable,a.timein,a.timeout,a.advance,a.advancebal,a.depositbal,a.returnaddress,a.total_accessories," +
							"a.total_othersrv,a.total_addcharges,a.total_discount,a.chargdays,a.remarks,a.xhours,a.xhoursamt,a.discount_xhoursamt," +
						"b.regno,b.vehimakeid,b.vehimodelid," +
						"c.debname,c.nicno debnicno,c.passportno debppo,c.debadd,c.tel debtel,c.fax debfax,c.email debemail,c.billmob1 debbillmob1,c.billmob2 debbillmob2,c.vatno debvatno," +
						"d.gname,d.homeadd1 ghomeadd,d.hometele ghometele,d.company gcompany,d.offadd1 goffadd,d.offtele gofftele,d.mobilephone gmobileno," +
						"ISNULL(g.name,'') empname,ISNULL(g.dlno,'') empdlno,ISNULL(g.mobilephone1,'') mobilephone1,"+
						"i.ratetype,n.description,i.rate,i.standardrate,ISNULL(i.discount, 0.0) discount,i.xsmilerate,i.allotedkms,i.xhourrate,i.allotedhours,i.discountxhourrate," +
						"ISNULL(j.rate, 0.0) drate,ISNULL(j.discount, 0.0) dratediscount,ISNULL(j.nightoutrate, 0.0) dnightoutrate," +
							"ISNULL(j.otrate, 0.0) dotrate,ISNULL(j.addcharges, 0.0) daddcharges,ISNULL(j.discount_detention, 0.0) dotratediscount," +
							"ISNULL(j.discount_nightout, 0.0) nightoutdiscount," +
						"k.companyid," +
						"l.description comname," +
						"a.remarks_customer remarks_customer," +
						"ISNULL(m.drivername, '') custdrivername, ISNULL(m.dlno, '') custdriverdlno " +
						"FROM Freservation a " + 
							"LEFT JOIN Fresvehicle e ON a.resno=e.resno AND e.priority=1 " +
							"LEFT JOIN Fvehicle b ON e.regno=b.regno " +
							"LEFT JOIN Fdebtor c ON a.debcode=c.debcode " +
							"LEFT JOIN Fguarantor d ON c.gid=d.gid " +
							"LEFT JOIN Fresdriver f ON a.resno=f.resno " +
							"LEFT JOIN Femployee g ON f.empid=g.empid " +
							"LEFT JOIN Fresvehiclerate i ON a.resno=i.resno " +
							"LEFT JOIN Fratetype n ON i.ratetype=n.ratetype " +
							"LEFT JOIN Fresdriverrate j ON a.resno=j.resno " +
							"LEFT JOIN Freshed k ON a.agrno=k.agrno	" +
							"LEFT JOIN Fcompany l ON k.companyid=l.companyid " +
							"LEFT JOIN Fclientdriver m ON c.debcode=m.debcode " +
							"WHERE a.resno=?1", 
			resultSetMapping="FrentagreementRPT.ResultMap1")
})

@SqlResultSetMappings({
	@SqlResultSetMapping(name="FrentagreementRPT.ResultMap1", entities={@EntityResult(entityClass=FrentagreementRPT.class,
				fields={@FieldResult(name="resno",column="resno"),
						@FieldResult(name="agrno",column="agrno"),
						@FieldResult(name="parentref",column="parentref"),
						@FieldResult(name="cimileage",column="cimileage"),
						@FieldResult(name="debcode",column="debcode"),
						@FieldResult(name="comileage",column="comileage"),
						@FieldResult(name="deposit",column="deposit"),
						@FieldResult(name="din",column="din"),
						@FieldResult(name="dout",column="dout"),
						@FieldResult(name="hiretypeid",column="hiretypeid"),
						@FieldResult(name="cohirestsid",column="cohirestsid"),
						@FieldResult(name="itinerary",column="itinerary"),
						@FieldResult(name="regno",column="regno"),
						@FieldResult(name="vehimakeid",column="vehimakeid"),
						@FieldResult(name="vehimodelid",column="vehimodelid"),
						@FieldResult(name="debname",column="debname"),
						@FieldResult(name="debnicno",column="debnicno"),
						@FieldResult(name="debppno",column="debppno"),
						@FieldResult(name="debadd",column="debadd"),
						@FieldResult(name="debtel",column="debtel"),
						@FieldResult(name="debfax",column="debfax"),									
						@FieldResult(name="debemail",column="debemail"),
						@FieldResult(name="debbillmob1",column="debbillmob1"),
						@FieldResult(name="debbillmob2",column="debbillmob2"),
						@FieldResult(name="debvatno",column="debvatno"),
						@FieldResult(name="gname",column="gname"),
						@FieldResult(name="ghomeadd",column="ghomeadd"),
						@FieldResult(name="ghometele",column="ghometele"),
						@FieldResult(name="gcompany",column="gcompany"),
						@FieldResult(name="goffadd",column="goffadd"),
						@FieldResult(name="gofftele",column="gofftele"),
						@FieldResult(name="gmobileno",column="gmobileno"),
						@FieldResult(name="empname",column="empname"),
						@FieldResult(name="empdlno",column="empdlno"),
						@FieldResult(name="mobilephone1",column="mobilephone1"),
						@FieldResult(name="codamage",column="codamage"),
						@FieldResult(name="cidamage",column="cidamage"),
						@FieldResult(name="cofuellevel",column="cofuellevel"),
						@FieldResult(name="cifuellevel",column="cifuellevel"),
						@FieldResult(name="noofday",column="noofday"),
						@FieldResult(name="discount_xmile",column="discount_xmile"),
						@FieldResult(name="cidamagers",column="cidamagers"),
						@FieldResult(name="ciother",column="ciother"),
						@FieldResult(name="cixsmileage",column="cixsmileage"),
						@FieldResult(name="cidetenhrs",column="cidetenhrs"),
						@FieldResult(name="cinightout",column="cinightout"),
						@FieldResult(name="cifueldiff",column="cifueldiff"),
						@FieldResult(name="total",column="total"),
						@FieldResult(name="nettotal",column="nettotal"),
						@FieldResult(name="totaltaxable",column="totaltaxable"),
						@FieldResult(name="taxamt",column="taxamt"),
						@FieldResult(name="timein",column="timein"),
						@FieldResult(name="timeout",column="timeout"),
						@FieldResult(name="advance",column="advance"),
						@FieldResult(name="advancebal",column="advancebal"),
						@FieldResult(name="depositbal",column="depositbal"),
						@FieldResult(name="returnaddress",column="returnaddress"),
						@FieldResult(name="total_accessories",column="total_accessories"),
						@FieldResult(name="total_othersrv",column="total_othersrv"),
						@FieldResult(name="total_addcharges",column="total_addcharges"),
						@FieldResult(name="total_discount",column="total_discount"),
						@FieldResult(name="chargdays",column="chargdays"),
						@FieldResult(name="remarks",column="remarks"),
						@FieldResult(name="ratetype",column="ratetype"),
						@FieldResult(name="description",column="description"),
						@FieldResult(name="rate",column="rate"),
						@FieldResult(name="standardrate",column="standardrate"),
						@FieldResult(name="discount",column="discount"),
						@FieldResult(name="xsmilerate",column="xsmilerate"),
						@FieldResult(name="allotedkms",column="allotedkms"),
						@FieldResult(name="drate",column="drate"),
						@FieldResult(name="dratediscount",column="dratediscount"),
						@FieldResult(name="dnightoutrate",column="dnightoutrate"),
						@FieldResult(name="dotrate",column="dotrate"),
						@FieldResult(name="daddcharges",column="daddcharges"),
						@FieldResult(name="dotratediscount",column="dotratediscount"),
						@FieldResult(name="nightoutdiscount",column="nightoutdiscount"),
						@FieldResult(name="companyid",column="companyid"),
						@FieldResult(name="comname",column="comname"),
						@FieldResult(name="custdrivername",column="custdrivername"),
						@FieldResult(name="custdriverdlno",column="custdriverdlno"),
						@FieldResult(name="xhours",column="xhours"),
						@FieldResult(name="xhoursamt",column="xhoursamt"),
						@FieldResult(name="discount_xhoursamt",column="discount_xhoursamt"),
						@FieldResult(name="xhourrate",column="xhourrate"),
						@FieldResult(name="allotedhours",column="allotedhours"),
						@FieldResult(name="remarks_customer",column="remarks_customer"),
						@FieldResult(name="discountxhourrate",column="discountxhourrate")
					})
				})
})

public class FrentagreementRPT implements Serializable { 

	/**
	 * This entity is made use of in the "rentagreement.rpt" Crystal Report
	 */
	private static final long serialVersionUID = 1L;

	// Fields from Freservation
	@Id
	@Column(unique=true, nullable=false, length=15)
	private String resno;

	@Column(nullable=false, length=15)
	private String agrno;
	
	@Column
	private int cimileage;
	
	@Column(length=10)
	private String debcode;
	
	@Column
	private int comileage;
	
	@Column(precision=15, scale=2)
	private BigDecimal deposit;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date din;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dout;
	
	@Column(length=10)
	private String hiretypeid;

	@Column(length=8000)
	private String itinerary;
	
	@Column(length=254)
	private String codamage;
		
	@Column(length=254)
	private String cidamage;
	
	@Column
	private int cofuellevel;
	
	@Column
	private int cifuellevel;

	@Column
	private int noofday;
	
	@Column(precision=7, scale=2)
	private BigDecimal discount_xmile;

	@Column(precision=15, scale=2)
	private BigDecimal cidamagers;
	
	@Column(precision=15, scale=2)
	private BigDecimal ciother;
	
	@Column(precision=15, scale=2)
	private BigDecimal cixsmileage;

	@Column(precision=15, scale=2)
	private BigDecimal cidetenhrs;
	
	@Column(precision=15, scale=2)
	private BigDecimal cinightout;
	
	@Column(precision=15, scale=2)
	private BigDecimal cifueldiff;

	@Column(precision=15, scale=2)
	private BigDecimal total;

	@Column(precision=15, scale=2)
	private BigDecimal nettotal;
	
	@Column(precision=15, scale=2)
	private BigDecimal totaltaxable;
	
	@Column(precision=15, scale=2)
	private BigDecimal taxamt;
	
	@Column(length=10)
	private String timein;
	
	@Column(length=10)
	private String timeout;

	@Column(precision=15, scale=2)
	private BigDecimal advance;

	@Column(precision=15, scale=2)
	private BigDecimal advancebal;

	@Column(precision=15, scale=2)
	private BigDecimal depositbal;
		
	@Column(length=8000)
	private String returnaddress;

	@Column(precision=15, scale=2)
	private BigDecimal total_accessories;

	@Column(precision=15, scale=2)
	private BigDecimal total_othersrv;

	@Column(precision=15, scale=2)
	private BigDecimal total_addcharges;

	@Column(precision=15, scale=2)
	private BigDecimal total_discount;
	
	@Column
	private int chargdays;
	
	@Column(length=8000)
	private String remarks;
	//add by sanka 2012.01.06
    @Column(nullable=true,name="xhours")
	private int xhours;
    
	@Column(precision=15, scale=2,name="xhoursamt")
	private BigDecimal xhoursamt;
	
	@Column(precision=15, scale=2,name="discount_xhoursamt")
	private BigDecimal discount_xhoursamt;
	//add by sanka 2012.01.06	
	
	// Fields from Fvehicle
	@Column(length=10)
	private String regno;
	
	@Column(length=10)
	private String vehimakeid;
	
	@Column(length=10)
	private String vehimodelid;

	
	// Fields from Fdebtor
	@Column(length=500)
	private String debname;
	
	@Column(length=10)
	private String debnicno;
	
	@Column(length=15)
	private String debppno;
	
	@Column(length=254)
	private String debadd;
	
	@Column(length=15)
	private String debtel;
	
	@Column(length=15)
	private String debfax;
	
	@Column(length=50)
	private String debemail;
	
	@Column(length=20)
	private String debbillmob1;

	@Column(length=20)
	private String debbillmob2;
	
	@Column(length=20)
	private String debvatno;
	
	
	// Fields from Fguarantor
	@Column(length=254)
	private String gname;
	
	@Column(length=254)
	private String ghomeadd;
	
	@Column(length=20)
	private String ghometele;
	
	@Column(length=254)
	private String gcompany;

	@Column(length=254)
	private String goffadd;
	
	@Column(length=20)
	private String gofftele;
	
	@Column(length=20)
	private String gmobileno;
	
	// Fields from Femployee
	@Column(length=254)
	private String empname;
	
	@Column(length=20)
	private String empdlno;
	@Column(length=20)
	private String mobilephone1;
	
	public String getMobilephone1() {
		return mobilephone1;
	}

	public void setMobilephone1(String mobilephone1) {
		this.mobilephone1 = mobilephone1;
	}

	// Fields from Fresvehiclerate
	@Column(length=10)
	private String ratetype;

	@Column(length=254)
	private String description;
	
	@Column(precision=15, scale=2)
	private BigDecimal rate;	

	@Column(precision=15, scale=2)
	private BigDecimal standardrate;	

	@Column(precision=7, scale=4)
	private BigDecimal discount;
	
	@Column(precision=15, scale=2)
	private BigDecimal xsmilerate;
	
	@Column
	private int allotedkms;
	
	//s-add by sanka 2012.01.06
	@Column(precision=15, scale=2, name="xhourrate")
	private BigDecimal xhourrate;
	
	@Column(nullable=false, name="allotedhours")
	private int allotedhours;
	
	@Column(precision=7, scale=4)
	private BigDecimal discountxhourrate;
	//e-add by sanka 2012.01.06
	
	// Fields from Fresdriverrate
	@Column(precision=18)
	private BigDecimal dnightoutrate;

	@Column(precision=18)
	private BigDecimal dotrate;

	@Column(precision=7, scale=4)
	private BigDecimal dratediscount;

	@Column(precision=18)
	private BigDecimal drate;
	
	@Column(precision=18)
	private BigDecimal daddcharges;

	@Column(precision=7, scale=4)
	private BigDecimal dotratediscount;

	@Column(precision=7, scale=4)
	private BigDecimal nightoutdiscount;
	
	// Fields from fCompany
	@Column(length=10)
	private String companyid;
	
	@Column(length=254)
	private String comname;
	
	// Fields from Fclientdriver
	@Column(length=254)
	private String custdrivername;
	
	@Column(length=20)
	private String custdriverdlno;
	
	private String parentref;
	
	private String cohirestsid;
	@Column(length=254)
	private String remarks_customer;
	
	//@Column(length=254)
	//private String tax_rate;
	
	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getVehimakeid() {
		return vehimakeid;
	}

	public void setVehimakeid(String vehimakeid) {
		this.vehimakeid = vehimakeid;
	}

	public String getVehimodelid() {
		return vehimodelid;
	}

	public void setVehimodelid(String vehimodelid) {
		this.vehimodelid = vehimodelid;
	}

	public String getDebname() {
		return debname;
	}

	public void setDebname(String debname) {
		this.debname = debname;
	}

	public String getDebnicno() {
		return debnicno;
	}

	public void setDebnicno(String debnicno) {
		this.debnicno = debnicno;
	}

	public String getDebppno() {
		return debppno;
	}

	public void setDebppno(String debppno) {
		this.debppno = debppno;
	}

	public String getDebadd() {
		return debadd;
	}

	public void setDebadd(String debadd) {
		this.debadd = debadd;
	}

	public String getDebtel() {
		return debtel;
	}

	public void setDebtel(String debtel) {
		this.debtel = debtel;
	}

	public String getDebfax() {
		return debfax;
	}

	public void setDebfax(String debfax) {
		this.debfax = debfax;
	}

	public String getDebemail() {
		return debemail;
	}

	public void setDebemail(String debemail) {
		this.debemail = debemail;
	}

	public String getDebbillmob1() {
		return debbillmob1;
	}

	public void setDebbillmob1(String debbillmob1) {
		this.debbillmob1 = debbillmob1;
	}

	public String getDebbillmob2() {
		return debbillmob2;
	}

	public void setDebbillmob2(String debbillmob2) {
		this.debbillmob2 = debbillmob2;
	}

	public String getDebvatno() {
		return debvatno;
	}

	public void setDebvatno(String debvatno) {
		this.debvatno = debvatno;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getGhomeadd() {
		return ghomeadd;
	}

	public void setGhomeadd(String ghomeadd) {
		this.ghomeadd = ghomeadd;
	}

	public String getGhometele() {
		return ghometele;
	}

	public void setGhometele(String ghometele) {
		this.ghometele = ghometele;
	}

	public String getGcompany() {
		return gcompany;
	}

	public void setGcompany(String gcompany) {
		this.gcompany = gcompany;
	}

	public String getGoffadd() {
		return goffadd;
	}

	public void setGoffadd(String goffadd) {
		this.goffadd = goffadd;
	}

	public String getGofftele() {
		return gofftele;
	}

	public void setGofftele(String gofftele) {
		this.gofftele = gofftele;
	}

	public String getGmobileno() {
		return gmobileno;
	}

	public void setGmobileno(String gmobileno) {
		this.gmobileno = gmobileno;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getEmpdlno() {
		return empdlno;
	}

	public void setEmpdlno(String empdlno) {
		this.empdlno = empdlno;
	}

	public void setDin(Date din) {
		this.din = din;
	}

	public void setDout(Date dout) {
		this.dout = dout;
	}
   
	public String getResno() {
		return resno;
	}

	public void setResno(String resno) {
		this.resno = resno;
	}

	public int getCimileage() {
		return cimileage;
	}

	public void setCimileage(int cimileage) {
		this.cimileage = cimileage;
	}

	public int getComileage() {
		return comileage;
	}

	public void setComileage(int comileage) {
		this.comileage = comileage;
	}

	public void setDebcode(String debcode) {
		this.debcode = debcode;
	}

	public String getDebcode() {
		return debcode;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public Date getDin() {
		return din;
	}

	public void setDin(Calendar din) {
		this.din = new java.sql.Date(din.getTime().getTime());
	}

	public Date getDout() {
		return dout;
	}

	public void setDout(Calendar dout) {
		this.dout = new java.sql.Date(dout.getTime().getTime());
	}

	public String getHiretypeid() {
		return hiretypeid;
	}

	public void setHiretypeid(String hiretypeid) {
		this.hiretypeid = hiretypeid;
	}

	public void setCodamage(String codamage) {
		this.codamage = codamage;
	}

	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}

	public String getItinerary() {
		return itinerary;
	}

	public String getCodamage() {
		return codamage;
	}

	public String getCidamage() {
		return cidamage;
	}

	public void setCidamage(String cidamage) {
		this.cidamage = cidamage;
	}

	public int getCofuellevel() {
		return cofuellevel;
	}

	public void setCofuellevel(int cofuellevel) {
		this.cofuellevel = cofuellevel;
	}

	public int getCifuellevel() {
		return cifuellevel;
	}

	public void setCifuellevel(int cifuellevel) {
		this.cifuellevel = cifuellevel;
	}

	public void setNoofday(int noofday) {
		this.noofday = noofday;
	}

	public int getNoofday() {
		return noofday;
	}

	public String getRatetype() {
		return ratetype;
	}

	public void setRatetype(String ratetype) {
		this.ratetype = ratetype;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getXsmilerate() {
		return xsmilerate;
	}

	public void setXsmilerate(BigDecimal xsmilerate) {
		this.xsmilerate = xsmilerate;
	}

	public int getAllotedkms() {
		return allotedkms;
	}

	public void setAllotedkms(int allotedkms) {
		this.allotedkms = allotedkms;
	}

	public BigDecimal getDnightoutrate() {
		return dnightoutrate;
	}

	public void setDnightoutrate(BigDecimal dnightoutrate) {
		this.dnightoutrate = dnightoutrate;
	}

	public BigDecimal getDotrate() {
		return dotrate;
	}

	public void setDotrate(BigDecimal dotrate) {
		this.dotrate = dotrate;
	}

	public BigDecimal getDrate() {
		return drate;
	}

	public void setDrate(BigDecimal drate) {
		this.drate = drate;
	}

	public BigDecimal getDaddcharges() {
		return daddcharges;
	}

	public void setDaddcharges(BigDecimal daddcharges) {
		this.daddcharges = daddcharges;
	}

	public BigDecimal getDiscount_xmile() {
		return discount_xmile;
	}

	public void setDiscount_xmile(BigDecimal discountXmile) {
		discount_xmile = discountXmile;
	}

	public BigDecimal getDratediscount() {
		return dratediscount;
	}

	public void setDratediscount(BigDecimal dratediscount) {
		this.dratediscount = dratediscount;
	}

	public BigDecimal getDotratediscount() {
		return dotratediscount;
	}

	public void setDotratediscount(BigDecimal dotratediscount) {
		this.dotratediscount = dotratediscount;
	}

	public BigDecimal getNightoutdiscount() {
		return nightoutdiscount;
	}

	public void setNightoutdiscount(BigDecimal nightoutdiscount) {
		this.nightoutdiscount = nightoutdiscount;
	}

	public BigDecimal getCidamagers() {
		return cidamagers;
	}

	public void setCidamagers(BigDecimal cidamagers) {
		this.cidamagers = cidamagers;
	}

	public BigDecimal getCiother() {
		return ciother;
	}

	public void setCiother(BigDecimal ciother) {
		this.ciother = ciother;
	}

	public BigDecimal getCixsmileage() {
		return cixsmileage;
	}

	public void setCixsmileage(BigDecimal cixsmileage) {
		this.cixsmileage = cixsmileage;
	}

	public BigDecimal getCidetenhrs() {
		return cidetenhrs;
	}

	public void setCidetenhrs(BigDecimal cidetenhrs) {
		this.cidetenhrs = cidetenhrs;
	}

	public BigDecimal getCinightout() {
		return cinightout;
	}

	public void setCinightout(BigDecimal cinightout) {
		this.cinightout = cinightout;
	}

	public BigDecimal getCifueldiff() {
		return cifueldiff;
	}

	public void setCifueldiff(BigDecimal cifueldiff) {
		this.cifueldiff = cifueldiff;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getNettotal() {
		return nettotal;
	}

	public void setNettotal(BigDecimal nettotal) {
		this.nettotal = nettotal;
	}

	public BigDecimal getTotaltaxable() {
		return totaltaxable;
	}

	public void setTotaltaxable(BigDecimal totaltaxable) {
		this.totaltaxable = totaltaxable;
	}

	public BigDecimal getTaxamt() {
		return taxamt;
	}

	public void setTaxamt(BigDecimal taxamt) {
		this.taxamt = taxamt;
	}

	public void setAgrno(String agrno) {
		this.agrno = agrno;
	}

	public String getAgrno() {
		return agrno;
	}
	
	public String getTimein() {
		return timein;
	}

	public void setTimein(String timein) {
		this.timein = timein;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getComname() {
		return comname;
	}

	public void setComname(String comname) {
		this.comname = comname;
	}

	public void setAdvance(BigDecimal advance) {
		this.advance = advance;
	}

	public BigDecimal getAdvance() {
		return advance;
	}

	public void setAdvancebal(BigDecimal advancebal) {
		this.advancebal = advancebal;
	}

	public BigDecimal getAdvancebal() {
		return advancebal;
	}

	public void setDepositbal(BigDecimal depositbal) {
		this.depositbal = depositbal;
	}

	public BigDecimal getDepositbal() {
		return depositbal;
	}

	public void setStandardrate(BigDecimal standardrate) {
		this.standardrate = standardrate;
	}

	public BigDecimal getStandardrate() {
		return standardrate;
	}

	public void setReturnaddress(String returnaddress) {
		this.returnaddress = returnaddress;
	}

	public String getReturnaddress() {
		return returnaddress;
	}

	public void setCustdrivername(String custdrivername) {
		this.custdrivername = custdrivername;
	}

	public String getCustdrivername() {
		return custdrivername;
	}

	public void setCustdriverdlno(String custdriverdlno) {
		this.custdriverdlno = custdriverdlno;
	}

	public String getCustdriverdlno() {
		return custdriverdlno;
	}

	public BigDecimal getTotal_accessories() {
		return total_accessories;
	}

	public void setTotal_accessories(BigDecimal totalAccessories) {
		total_accessories = totalAccessories;
	}

	public BigDecimal getTotal_othersrv() {
		return total_othersrv;
	}

	public void setTotal_othersrv(BigDecimal totalOthersrv) {
		total_othersrv = totalOthersrv;
	}

	public BigDecimal getTotal_addcharges() {
		return total_addcharges;
	}

	public void setTotal_addcharges(BigDecimal totalAddcharges) {
		total_addcharges = totalAddcharges;
	}

	public BigDecimal getTotal_discount() {
		return total_discount;
	}

	public void setTotal_discount(BigDecimal totalDiscount) {
		total_discount = totalDiscount;
	}

	public void setChargdays(int chargdays) {
		this.chargdays = chargdays;
	}

	public int getChargdays() {
		return chargdays;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setDiscountxhourrate(BigDecimal discountxhourrate) {
		this.discountxhourrate = discountxhourrate;
	}

	public BigDecimal getDiscountxhourrate() {
		return discountxhourrate;
	}

	public void setAllotedhours(int allotedhours) {
		this.allotedhours = allotedhours;
	}

	public int getAllotedhours() {
		return allotedhours;
	}

	public void setXhourrate(BigDecimal xhourrate) {
		this.xhourrate = xhourrate;
	}

	public BigDecimal getXhourrate() {
		return xhourrate;
	}

	public void setXhours(int xhours) {
		this.xhours = xhours;
	}

	public int getXhours() {
		return xhours;
	}

	public void setXhoursamt(BigDecimal xhoursamt) {
		this.xhoursamt = xhoursamt;
	}

	public BigDecimal getXhoursamt() {
		return xhoursamt;
	}

	public void setDiscount_xhoursamt(BigDecimal discount_xhoursamt) {
		this.discount_xhoursamt = discount_xhoursamt;
	}

	public BigDecimal getDiscount_xhoursamt() {
		return discount_xhoursamt;
	}

	public void setParentref(String parentref) {
		this.parentref = parentref;
	}

	public String getParentref() {
		return parentref;
	}

	public void setCohirestsid(String cohirestsid) {
		this.cohirestsid = cohirestsid;
	}

	public String getCohirestsid() {
		return cohirestsid;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setRemarks_customer(String remarks_customer) {
		this.remarks_customer = remarks_customer;
	}

	public String getRemarks_customer() {
		return remarks_customer;
	}

//	public void setTax_rate(String tax_rate) {
//		this.tax_rate = tax_rate;
//	}
//
//	public String getTax_rate() {
//		return tax_rate;
//	}

}
