package com.dspl.malkey.report;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SqlResultSetMappings({
	@SqlResultSetMapping(name="farfdtfTMPSQL", entities={@EntityResult(entityClass=FarfdtfTMP.class,
			fields={@FieldResult(name="resno",column="resno"),
					@FieldResult(name="regno",column="regno"),
					@FieldResult(name="ratetype",column="ratetype"),
					@FieldResult(name="rate",column="rate"),
					@FieldResult(name="discount",column="discount"),
					@FieldResult(name="xsmilerate",column="xsmilerate"),
					@FieldResult(name="discount_xmile",column="discount_xmile"),
					@FieldResult(name="allotedkms",column="allotedkms"),
					@FieldResult(name="drratetype",column="drratetype"),
					@FieldResult(name="drdiscount",column="drdiscount"),
					@FieldResult(name="drnightoutrate",column="drnightoutrate"),
					@FieldResult(name="drdiscount_nightout",column="drdiscount_nightout"),
					@FieldResult(name="drotrate",column="drotrate"),
					@FieldResult(name="drdiscount_detention",column="drdiscount_detention"),
					@FieldResult(name="hiretypeid",column="hiretypeid"),
					@FieldResult(name="hiretype",column="hiretype"),
					@FieldResult(name="dout",column="dout"),
					@FieldResult(name="din",column="din"),
					@FieldResult(name="noofday",column="noofday"),
					@FieldResult(name="chargdays",column="chargdays"),
					@FieldResult(name="deposit",column="deposit"),
					@FieldResult(name="advance",column="advance"),
					@FieldResult(name="comileage",column="comileage"),
					@FieldResult(name="cimileage",column="cimileage"),
					@FieldResult(name="cixsmileage",column="cixsmileage"),
					@FieldResult(name="cixsmileagers",column="cixsmileagers"),
					@FieldResult(name="cidetenhrs",column="cidetenhrs"),				
					@FieldResult(name="cidetenhrsrs",column="cidetenhrsrs"),
					@FieldResult(name="cinightout",column="cinightout"),
					@FieldResult(name="cinightoutrs",column="cinightoutrs"),
					@FieldResult(name="ciother",column="ciother"),
					@FieldResult(name="othernaration",column="othernaration"),
					@FieldResult(name="cifueldiff",column="cifueldiff"),
					@FieldResult(name="cidamagers",column="cidamagers"),
					@FieldResult(name="vrsrate",column="vrsrate"),
					@FieldResult(name="taxcomcode",column="taxcomcode"),
					@FieldResult(name="vehicle",column="vehicle"),
					
					@FieldResult(name="xhourrate",column="xhourrate"),
					@FieldResult(name="allotedhours",column="allotedhours"),
					@FieldResult(name="discountxhourrate",column="discountxhourrate"),
					
					@FieldResult(name="xhours",column="xhours"),
					@FieldResult(name="xhoursamt",column="xhoursamt"),
					@FieldResult(name="discount_xhoursamt",column="discount_xhoursamt"),
					@FieldResult(name="vattyp",column="vattyp")
					})
	})
})

@Entity
public class FarfdtfTMP {
	@Id
	private String resno;
	private String regno;
	private String ratetype;
	private BigDecimal rate;
	private BigDecimal discount;
	private BigDecimal xsmilerate;
	private BigDecimal discount_xmile;
	private int allotedkms;
	private String drratetype;
	private BigDecimal drdiscount;
	private BigDecimal drnightoutrate;
	private BigDecimal drdiscount_nightout;
	private BigDecimal drotrate;
	private BigDecimal drdiscount_detention;
	private String hiretypeid;
	private String hiretype;
	private String othernaration;
	
	private BigDecimal xhourrate;
	private int allotedhours;
	private BigDecimal discountxhourrate;
	
	private int xhours;
	private BigDecimal xhoursamt;
	private BigDecimal discount_xhoursamt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dout;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar din;
	
	private int noofday;
	private int chargdays;
	private BigDecimal deposit;
	private BigDecimal advance;
	private int comileage;
	private int cimileage;
	private int cixsmileage;
	private BigDecimal cixsmileagers;
	private Double cidetenhrs;
	private BigDecimal cidetenhrsrs;
	private int cinightout;
	private BigDecimal cinightoutrs;
	private BigDecimal ciother;
	private BigDecimal cifueldiff;
	private BigDecimal cidamagers;
	private BigDecimal vrsrate;
	private String taxcomcode;
	private String vehicle;
	private String vattyp;
	
	public void setResno(String resno) {
		this.resno = resno;
	}
	public String getResno() {
		return resno;
	}
	public void setRegno(String regno) {
		this.regno = regno;
	}
	public String getRegno() {
		return regno;
	}
	public void setRatetype(String ratetype) {
		this.ratetype = ratetype;
	}
	public String getRatetype() {
		return ratetype;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setXsmilerate(BigDecimal xsmilerate) {
		this.xsmilerate = xsmilerate;
	}
	public BigDecimal getXsmilerate() {
		return xsmilerate;
	}
	public void setAllotedkms(int allotedkms) {
		this.allotedkms = allotedkms;
	}
	public int getAllotedkms() {
		return allotedkms;
	}
	public void setDrratetype(String drratetype) {
		this.drratetype = drratetype;
	}
	public String getDrratetype() {
		return drratetype;
	}
	public void setDrdiscount(BigDecimal drdiscount) {
		this.drdiscount = drdiscount;
	}
	public BigDecimal getDrdiscount() {
		return drdiscount;
	}
	public void setDrnightoutrate(BigDecimal drnightoutrate) {
		this.drnightoutrate = drnightoutrate;
	}
	public BigDecimal getDrnightoutrate() {
		return drnightoutrate;
	}
	public void setDrotrate(BigDecimal drotrate) {
		this.drotrate = drotrate;
	}
	public BigDecimal getDrotrate() {
		return drotrate;
	}
	public void setHiretypeid(String hiretypeid) {
		this.hiretypeid = hiretypeid;
	}
	public String getHiretypeid() {
		return hiretypeid;
	}
	public void setDout(Calendar dout) {
		this.dout = dout;
	}
	public Calendar getDout() {
		return dout;
	}
	public void setDin(Calendar din) {
		this.din = din;
	}
	public Calendar getDin() {
		return din;
	}
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	public BigDecimal getDeposit() {
		return deposit;
	}
	public void setAdvance(BigDecimal advance) {
		this.advance = advance;
	}
	public BigDecimal getAdvance() {
		return advance;
	}
	public void setChargdays(int chargdays) {
		this.chargdays = chargdays;
	}
	public int getChargdays() {
		return chargdays;
	}
	public void setComileage(int comileage) {
		this.comileage = comileage;
	}
	public int getComileage() {
		return comileage;
	}
	public void setCimileage(int cimileage) {
		this.cimileage = cimileage;
	}
	public int getCimileage() {
		return cimileage;
	}
	public void setCixsmileage(int cixsmileage) {
		this.cixsmileage = cixsmileage;
	}
	public int getCixsmileage() {
		return cixsmileage;
	}
	public void setCixsmileagers(BigDecimal cixsmileagers) {
		this.cixsmileagers = cixsmileagers;
	}
	public BigDecimal getCixsmileagers() {
		return cixsmileagers;
	}
	public void setCidetenhrs(Double cidetenhrs) {
		this.cidetenhrs = cidetenhrs;
	}
	public Double getCidetenhrs() {
		return cidetenhrs;
	}
	public void setCidetenhrsrs(BigDecimal cidetenhrsrs) {
		this.cidetenhrsrs = cidetenhrsrs;
	}
	public BigDecimal getCidetenhrsrs() {
		return cidetenhrsrs;
	}
	public void setCinightout(int cinightout) {
		this.cinightout = cinightout;
	}
	public int getCinightout() {
		return cinightout;
	}
	public void setCinightoutrs(BigDecimal cinightoutrs) {
		this.cinightoutrs = cinightoutrs;
	}
	public BigDecimal getCinightoutrs() {
		return cinightoutrs;
	}
	public void setCiother(BigDecimal ciother) {
		this.ciother = ciother;
	}
	public BigDecimal getCiother() {
		return ciother;
	}
	public void setCifueldiff(BigDecimal cifueldiff) {
		this.cifueldiff = cifueldiff;
	}
	public BigDecimal getCifueldiff() {
		return cifueldiff;
	}
	public void setTaxcomcode(String taxcomcode) {
		this.taxcomcode = taxcomcode;
	}
	public String getTaxcomcode() {
		return taxcomcode;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVattyp(String vattyp) {
		this.vattyp = vattyp;
	}
	public String getVattyp() {
		return vattyp;
	}
	public void setCidamagers(BigDecimal cidamagers) {
		this.cidamagers = cidamagers;
	}
	public BigDecimal getCidamagers() {
		return cidamagers;
	}
	public void setNoofday(int noofday) {
		this.noofday = noofday;
	}
	public int getNoofday() {
		return noofday;
	}
	public void setDiscount_xmile(BigDecimal discount_xmile) {
		this.discount_xmile = discount_xmile;
	}
	public BigDecimal getDiscount_xmile() {
		return discount_xmile;
	}
	public void setDrdiscount_nightout(BigDecimal drdiscount_nightout) {
		this.drdiscount_nightout = drdiscount_nightout;
	}
	public BigDecimal getDrdiscount_nightout() {
		return drdiscount_nightout;
	}
	public void setDrdiscount_detention(BigDecimal drdiscount_detention) {
		this.drdiscount_detention = drdiscount_detention;
	}
	public BigDecimal getDrdiscount_detention() {
		return drdiscount_detention;
	}
	public void setHiretype(String hiretype) {
		this.hiretype = hiretype;
	}
	public String getHiretype() {
		return hiretype;
	}
	public void setVrsrate(BigDecimal vrsrate) {
		this.vrsrate = vrsrate;
	}
	public BigDecimal getVrsrate() {
		return vrsrate;
	}
	public void setOthernaration(String othernaration) {
		this.othernaration = othernaration;
	}
	public String getOthernaration() {
		return othernaration;
	}
	public void setXhourrate(BigDecimal xhourrate) {
		this.xhourrate = xhourrate;
	}
	public BigDecimal getXhourrate() {
		return xhourrate;
	}
	public void setAllotedhours(int allotedhours) {
		this.allotedhours = allotedhours;
	}
	public int getAllotedhours() {
		return allotedhours;
	}
	public void setDiscountxhourrate(BigDecimal discountxhourrate) {
		this.discountxhourrate = discountxhourrate;
	}
	public BigDecimal getDiscountxhourrate() {
		return discountxhourrate;
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

}
