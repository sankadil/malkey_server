package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.dspl.malkey.report.Reservation;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;


/**
 * The persistent class for the freservation database table.
 * 
 */
@Entity
@Table(name="freservation")
@NamedQueries({
	@NamedQuery(name="FreservationListAll", query="SELECT f FROM Freservation f"),
	@NamedQuery(name="FreservationDeleteByResno", query="DELETE FROM Freservation f WHERE f.resno=:resno"),
	@NamedQuery(name="FreservationSelectByAgrno", query="SELECT f FROM Freservation f WHERE f.agrno=:agrno"),
	@NamedQuery(name="FreservationPartSelectByAgrno", 
			query="SELECT f.resno,f.hiretypeid,f.dout,f.din,f.cohirestsid FROM Freservation f WHERE f.agrno=:agrno"),
	@NamedQuery(name="FreservationTaxDetail", 
					query="SELECT t FROM Ftaxdet t WHERE t.id.taxcomcode in (SELECT r.taxcomcode FROM Freservation r WHERE r.resno=:resno)")
	})
@NamedNativeQueries({
	@NamedNativeQuery(name="FreservationPartSelectByAgrnoNative", 
			query="SELECT f.resno,f.hiretypeid,f.dout,f.din,f.cohirestsid FROM Freservation as f WHERE f.agrno=:agrno")
})
public class Freservation implements Serializable,Cloneable  {
	private static final long serialVersionUID = 1L;

	@Transient
	private Boolean selected;
	
	@Transient
	private Reservation details;
	
	@Id
	@Column(unique=true, nullable=false, length=13,name="resno")
	private String resno;
	
    
    @Transient
	private String regno;
	
	@Column( length=13,name="agrno")
	private String agrno;
	
	@Column(nullable=false,name="adddate")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar adddate;

	@Column(nullable=false, length=50,name="addmach")
	private String addmach;

	@Column(nullable=false, length=10,name="adduser")
	private String adduser;
	
	@Column(precision=15, scale=2,name="advance")
	private BigDecimal advance;

	@Column(nullable=false,name="chargdays")
	private int chargdays;

	@Column(name="cidamage")
	private String cidamage;
	
	@Column(precision=15, scale=2,name="cidamagers")
	private BigDecimal cidamagers;
	
	@Column(name="codamage")
	private String codamage;

	@Column(nullable=false,name="cidetenhrs")
	private Double cidetenhrs;

	@Column(precision=15, scale=2,name="cidetenhrsrs")
	private BigDecimal cidetenhrsrs;

	@Column(precision=15, scale=2,name="cifueldiff")
	private BigDecimal cifueldiff;

	@Column(nullable=false,name="cifuellevel")
	private int cifuellevel;

	@Column(nullable=false,name="cimileage")
	private int cimileage;

	@Column(nullable=false,name="cinightout")
	private int cinightout;

	@Column(precision=15, scale=2,name="cinightoutrs")
	private BigDecimal cinightoutrs;

	@Column(precision=15, scale=2,name="ciother")
	private BigDecimal ciother;

	@Column(nullable=false,name="cixsmileage")
	private int cixsmileage;

	@Column(precision=15, scale=2,name="cixsmileagers")
	private BigDecimal cixsmileagers;

	@Column(nullable=false, length=10,name="debcode")
	private String debcode;

	@Column(nullable=false,name="cofuellevel")
	private int cofuellevel;

	@Column(nullable=false, length=10,name="cohirestsid")
	private String cohirestsid;

	@Column(nullable=false, length=10,name="coinspectby")
	private String coinspectby;

	@Column(nullable=false,name="comileage")
	private int comileage;

	@Column(precision=15, scale=2,name="deposit")
	private BigDecimal deposit;

	@Column(nullable=false,name="din")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar din;

	@Column(nullable=false,name="dout")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dout;
	
	@Column(nullable=false,name="gdout")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar gdout;

	@Column(nullable=false, length=10,name="hiretypeid")
	private String hiretypeid;

    @Column(nullable=false, length=8000,name="itinerary")
	private String itinerary;

    @Column(nullable=false,name="noofday")
	private int noofday;

	@Column(nullable=false, length=10,name="paytypeid")
	private String paytypeid;

	@Column(nullable=false, length=10,name="quoteno")
	private String quoteno;
	
	@Column( length=10,name="timeout")
	private String timeout;
	
	@Column( length=10,name="timein")
	private String timein;
	
	@Column( length=10,name="gotime")
	private String gotime;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false,name="recordid")
	private int recordid;

    @Column(nullable=false, length=8000,name="remarks")
	private String remarks;
    @Column(nullable=false, length=8000,name="remarks_customer")
    private String remarks_customer;
    
    @Column(nullable=false, length=8000,name="returnaddress")
    private String returnaddress;
    
    @Transient
    private byte[] checkindata;
    
    @Transient
    private byte[] checkoutdata;
    
    
	@Column(precision=7, scale=2,name="discount_xmile")
	private BigDecimal discount_xmile;

	@Column(precision=15, scale=2,name="advancebal")
	private BigDecimal advancebal;
	
	@Column(precision=15, scale=2,name="depositbal")
	private BigDecimal depositbal;
	
	@Column(precision=15, scale=2,name="total")
	private BigDecimal total;
	
	@Column(precision=15, scale=2,name="totaltaxable")
	private BigDecimal totaltaxable;
	
	@Column(precision=15, scale=2,name="taxamt")
	private BigDecimal taxamt;
	
	@Column(precision=15, scale=2,name="nettotal")
	private BigDecimal nettotal;
	
	@Column(nullable=false, length=50,name="uuid")
	private String uuid;
	
	@Column(nullable=false, length=10,name="taxcomcode")
	private String taxcomcode;
	
	@Column(nullable=true, length=10,name="billbasis")
	private String billbasis;
	
	
	@Column(length=15,name="parentref")
	private String parentref;
	
	@Column(length=15,name="childref")
	private String childref;
	
	@Column(nullable=false,name="agrdin")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar agrdin;

	@Column(nullable=false,name="agrdout")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar agrdout;
	
    @Column(length=500,name="copyreason")
	private String copyreason;
    
    @Column(length=500,name="othernaration")
    private String othernaration;
    
    /***
     * Following field introduce to keep rate type of hire example,DAILY,WEEKLY,MONTHLY,LONGTERM
     * AND DUPPLICATED IN Fresvehiclerate
     */
	@Column(nullable=false, length=10)
	private String ratetype;
	
	
	@Column(precision=15, scale=2,name="total_accessories")
	private BigDecimal total_accessories;
	
	@Column(precision=15, scale=2,name="total_othersrv")
	private BigDecimal total_othersrv;
	
	@Column(precision=15, scale=2,name="total_addcharges")
	private BigDecimal total_addcharges;
	
	@Column(precision=15, scale=2,name="total_discount")
	private BigDecimal total_discount;
	
    @Column(nullable=true,name="xhours")
	private int xhours;
    
	@Column(precision=15, scale=2,name="xhoursamt")
	private BigDecimal xhoursamt;
	
	@Column(precision=15, scale=2,name="discount_xhoursamt")
	private BigDecimal discount_xhoursamt;
	
	@Column(name="oc_details")
	private String otherChargersDetails;
	
	@Column(name="BOOKED")
	private String booked;
	
	@Column(name="CONFIRMED")
	private String confirmed;
	
	@Column(name="CANCELLED")
	private String cancelled;
	
	@Column(name="CHECKOUT")
	private String checkout;
	
	@Column(name="CHECKIN")
	private String checkin;
	
	@Column(name="COMPLETED")
	private String completed;
	
	@Column(name="INVOICE")
	private String invoiced;
	
	/***
	 * flag need to keep in track which application generate invoice Front/Back office ?
	 * FO for front office
	 * BO or null for back office
	 * 
	 */
	@Column(name="inapp")
	private String invoicedApplication;
	
	public String getCoLocation() {
		return coLocation;
	}
	public void setCoLocation(String coLocation) {
		this.coLocation = coLocation;
	}
	public String getCiLocation() {
		return ciLocation;
	}
	public void setCiLocation(String ciLocation) {
		this.ciLocation = ciLocation;
	}
	@Column(name="CO_LOCATION")
	private String coLocation;
	@Column(name="CI_LOCATION")
	private String ciLocation;
	
	
	public Calendar getBookdate() {
		return bookdate;
	}
	public void setBookdate(Calendar bookdate) {
		this.bookdate = bookdate;
	}
	public Calendar getConfirmdate() {
		return confirmdate;
	}
	public void setConfirmdate(Calendar confirmdate) {
		this.confirmdate = confirmdate;
	}
	public Calendar getCanceldate() {
		return canceldate;
	}
	public void setCanceldate(Calendar canceldate) {
		this.canceldate = canceldate;
	}
	public Calendar getCodate() {
		return codate;
	}
	public void setCodate(Calendar codate) {
		this.codate = codate;
	}
	public Calendar getCidate() {
		return cidate;
	}
	public void setCidate(Calendar cidate) {
		this.cidate = cidate;
	}
	public Calendar getCompletedate() {
		return completedate;
	}
	public void setCompletedate(Calendar completedate) {
		this.completedate = completedate;
	}
	@Column(nullable=false,name="bookdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar bookdate;
	
	@Column(nullable=false,name="confirmdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar confirmdate;
	
	@Column(nullable=false,name="canceldate")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar canceldate;
	
	@Column(nullable=false,name="codate")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar codate;
	
	@Column(nullable=false,name="cidate")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar cidate;
	
	@Column(nullable=false,name="completedate")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar completedate;
	
	@Column(nullable=false,name="invoicedate")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar invoicedate;
	
	public String getBooked() {
		return booked;
	}
	public void setBooked(String booked) {
		this.booked = booked;
	}
	public String getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}
	public String getCancelled() {
		return cancelled;
	}
	public void setCancelled(String cancelled) {
		this.cancelled = cancelled;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCompleted() {
		return completed;
	}
	public void setCompleted(String completed) {
		this.completed = completed;
	}

	
	
    public Object clone() throws CloneNotSupportedException{
        Cloneable theClone = (Freservation) super.clone();
        return theClone;
    }
    public Freservation() {
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

	public BigDecimal getAdvance() {
		return this.advance;
	}

	public void setAdvance(BigDecimal advance) {
		this.advance = advance;
	}

	public int getChargdays() {
		return this.chargdays;
	}

	public void setChargdays(int chargdays) {
		this.chargdays = chargdays;
	}



	public Double getCidetenhrs() {
		return this.cidetenhrs;
	}

	public void setCidetenhrs(Double cidetenhrs) {
		this.cidetenhrs = cidetenhrs;
	}

	public BigDecimal getCidetenhrsrs() {
		return this.cidetenhrsrs;
	}

	public void setCidetenhrsrs(BigDecimal cidetenhrsrs) {
		this.cidetenhrsrs = cidetenhrsrs;
	}

	public BigDecimal getCifueldiff() {
		return this.cifueldiff;
	}

	public void setCifueldiff(BigDecimal cifueldiff) {
		this.cifueldiff = cifueldiff;
	}

	public int getCifuellevel() {
		return this.cifuellevel;
	}

	public void setCifuellevel(int cifuellevel) {
		this.cifuellevel = cifuellevel;
	}

	public int getCimileage() {
		return this.cimileage;
	}

	public void setCimileage(int cimileage) {
		this.cimileage = cimileage;
	}

	public int getCinightout() {
		return this.cinightout;
	}

	public void setCinightout(int cinightout) {
		this.cinightout = cinightout;
	}

	public BigDecimal getCinightoutrs() {
		return this.cinightoutrs;
	}

	public void setCinightoutrs(BigDecimal cinightoutrs) {
		this.cinightoutrs = cinightoutrs;
	}

	public BigDecimal getCiother() {
		return this.ciother;
	}

	public void setCiother(BigDecimal ciother) {
		this.ciother = ciother;
	}

	public int getCixsmileage() {
		return this.cixsmileage;
	}

	public void setCixsmileage(int cixsmileage) {
		this.cixsmileage = cixsmileage;
	}

	public BigDecimal getCixsmileagers() {
		return this.cixsmileagers;
	}

	public void setCixsmileagers(BigDecimal cixsmileagers) {
		this.cixsmileagers = cixsmileagers;
	}


	public int getCofuellevel() {
		return this.cofuellevel;
	}

	public void setCofuellevel(int cofuellevel) {
		this.cofuellevel = cofuellevel;
	}

	public String getCohirestsid() {
		return this.cohirestsid;
	}

	public void setCohirestsid(String cohirestsid) {
		this.cohirestsid = cohirestsid;
	}

	public String getCoinspectby() {
		return this.coinspectby;
	}

	public void setCoinspectby(String coinspectby) {
		this.coinspectby = coinspectby;
	}

	public int getComileage() {
		return this.comileage;
	}

	public void setComileage(int comileage) {
		this.comileage = comileage;
	}

	public BigDecimal getDeposit() {
		return this.deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public Calendar getDin() {
		return this.din;
	}

	public void setDin(Calendar din) {
		this.din = din;
	}

	public Calendar getDout() {
		return this.dout;
	}

	public void setDout(Calendar dout) {
		this.dout = dout;
	}

	public String getHiretypeid() {
		return this.hiretypeid;
	}

	public void setHiretypeid(String hiretypeid) {
		this.hiretypeid = hiretypeid;
	}

	public String getItinerary() {
		return this.itinerary;
	}

	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}

	public int getNoofday() {
		return this.noofday;
	}

	public void setNoofday(int noofday) {
		this.noofday = noofday;
	}

	public String getPaytypeid() {
		return this.paytypeid;
	}

	public void setPaytypeid(String paytypeid) {
		this.paytypeid = paytypeid;
	}

	public String getQuoteno() {
		return this.quoteno;
	}

	public void setQuoteno(String quoteno) {
		this.quoteno = quoteno;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getResno() {
		return this.resno;
	}

	public void setResno(String resno) {
		this.resno = resno;
	}

	public void setDebcode(String debcode) {
		this.debcode = debcode;
	}

	public String getDebcode() {
		return debcode;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setCidamage(String cidamage) {
		this.cidamage = cidamage;
	}

	public String getCidamage() {
		return cidamage;
	}

	public void setCodamage(String codamage) {
		this.codamage = codamage;
	}

	public String getCodamage() {
		return codamage;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimein(String timein) {
		this.timein = timein;
	}

	public String getTimein() {
		return timein;
	}

	public void setGotime(String gotime) {
		this.gotime = gotime;
	}

	public String getGotime() {
		return gotime;
	}

	public void setCidamagers(BigDecimal cidamagers) {
		this.cidamagers = cidamagers;
	}

	public BigDecimal getCidamagers() {
		return cidamagers;
	}

	public void setCheckindata(byte[] checkindata) {
		this.checkindata = checkindata;
	}

	public byte[] getCheckindata() {
		return checkindata;
	}

	public void setCheckoutdata(byte[] checkoutdata) {
		this.checkoutdata = checkoutdata;
	}

	public byte[] getCheckoutdata() {
		return checkoutdata;
	}

	public void setAgrno(String agrno) {
		this.agrno = agrno;
	}

	public String getAgrno() {
		return agrno;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public Boolean getSelected() {
		return selected;
	}


	public void setDiscount_xmile(BigDecimal discount_xmile) {
		this.discount_xmile = discount_xmile;
	}

	public BigDecimal getDiscount_xmile() {
		return discount_xmile;
	}

	public void setGdout(Calendar gdout) {
		this.gdout = gdout;
	}

	public Calendar getGdout() {
		return gdout;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}


	public void setDepositbal(BigDecimal depositbal) {
		this.depositbal = depositbal;
	}

	public BigDecimal getDepositbal() {
		return depositbal;
	}

	public void setAdvancebal(BigDecimal advancebal) {
		this.advancebal = advancebal;
	}

	public BigDecimal getAdvancebal() {
		return advancebal;
	}

	public void setTaxcomcode(String taxcomcode) {
		this.taxcomcode = taxcomcode;
	}

	public String getTaxcomcode() {
		return taxcomcode;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotaltaxable(BigDecimal totaltaxable) {
		this.totaltaxable = totaltaxable;
	}

	public BigDecimal getTotaltaxable() {
		return totaltaxable;
	}

	public void setTaxamt(BigDecimal taxamt) {
		this.taxamt = taxamt;
	}

	public BigDecimal getTaxamt() {
		return taxamt;
	}

	public void setNettotal(BigDecimal nettotal) {
		this.nettotal = nettotal;
	}

	public BigDecimal getNettotal() {
		return nettotal;
	}
	public void setBillbasis(String billbasis) {
		this.billbasis = billbasis;
	}
	public String getBillbasis() {
		return billbasis;
	}
	public void setDetails(Reservation details) {
		this.details = details;
	}
	public Reservation getDetails() {
		return details;
	}
	public void setParentref(String parentref) {
		this.parentref = parentref;
	}
	public String getParentref() {
		return parentref;
	}
	public void setChildref(String childref) {
		this.childref = childref;
	}
	public String getChildref() {
		return childref;
	}
	public void setAgrdin(Calendar agrdin) {
		this.agrdin = agrdin;
	}
	public Calendar getAgrdin() {
		return agrdin;
	}
	public void setAgrdout(Calendar agrdout) {
		this.agrdout = agrdout;
	}
	public Calendar getAgrdout() {
		return agrdout;
	}
	public void setCopyreason(String copyreason) {
		this.copyreason = copyreason;
	}
	public String getCopyreason() {
		return copyreason;
	}
	public void setOthernaration(String othernaration) {
		this.othernaration = othernaration;
	}
	public String getOthernaration() {
		return othernaration;
	}
	public void setRatetype(String ratetype) {
		this.ratetype = ratetype;
	}
	public String getRatetype() {
		return ratetype;
	}
	public void setReturnaddress(String returnaddress) {
		this.returnaddress = returnaddress;
	}
	public String getReturnaddress() {
		return returnaddress;
	}
	public void setTotal_accessories(BigDecimal total_accessories) {
		this.total_accessories = total_accessories;
	}
	public BigDecimal getTotal_accessories() {
		return total_accessories;
	}
	public void setTotal_othersrv(BigDecimal total_othersrv) {
		this.total_othersrv = total_othersrv;
	}
	public BigDecimal getTotal_othersrv() {
		return total_othersrv;
	}
	public void setTotal_addcharges(BigDecimal total_addcharges) {
		this.total_addcharges = total_addcharges;
	}
	public BigDecimal getTotal_addcharges() {
		return total_addcharges;
	}
	public void setTotal_discount(BigDecimal total_discount) {
		this.total_discount = total_discount;
	}
	public BigDecimal getTotal_discount() {
		return total_discount;
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
	public void setOtherChargersDetails(String otherChargersDetails) {
		this.otherChargersDetails = otherChargersDetails;
	}
	public String getOtherChargersDetails() {
		return otherChargersDetails;
	}
	public void setInvoiced(String invoiced) {
		this.invoiced = invoiced;
	}
	public String getInvoiced() {
		return invoiced;
	}
	public void setInvoicedate(Calendar invoicedate) {
		this.invoicedate = invoicedate;
	}
	public Calendar getInvoicedate() {
		return invoicedate;
	}
	public void setRemarks_customer(String remarks_customer) {
		this.remarks_customer = remarks_customer;
	}
	public String getRemarks_customer() {
		return remarks_customer;
	}
	public void setInvoicedApplication(String invoicedApplication) {
		this.invoicedApplication = invoicedApplication;
	}
	public String getInvoicedApplication() {
		return invoicedApplication;
	}
	public void setRegno(String regno) {
		this.regno = regno;
	}
	public String getRegno() {
		return regno;
	}



}