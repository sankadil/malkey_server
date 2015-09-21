package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;
import java.math.BigDecimal;

@Entity
@Table(name="fcontrol")

@NamedQueries({@NamedQuery(name="FcontrolListAll", query="SELECT f FROM Fcontrol f")})

public class Fcontrol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false, length=12)
	private String adcntacc;

	@Column(nullable=false, length=12)
	private String aretearn;

	@Column(length=256)
	private String attdownloadfld;

	@Column(length=20)
	private String attfldonserver;

	@Column(nullable=false, precision=10)
	private BigDecimal autoast;

	@Column(nullable=false, precision=15, scale=2)
	private BigDecimal balgcrlm;

	@Column(nullable=false, length=5)
	private String basecur;

	@Column(nullable=false, length=50)
	private String comadd1;

	@Column(nullable=false, length=50)
	private String comadd2;

	@Column(nullable=false, length=50)
	private String comadd3;

	@Column(nullable=false, length=30)
	private String comemail;

	@Column(nullable=false, length=10)
	private String comfax1;

	@Column(nullable=false, length=12)
	private String comgstac;

	@Column(nullable=false, length=60)
	private String comname;

	@Column(nullable=false, length=15)
	private String comtel1;

	@Column(nullable=false, length=15)
	private String comtel2;

	@Column(nullable=false, length=30)
	private String comweb;

	@Column(nullable=false, precision=5)
	private BigDecimal conage1;

	@Column(nullable=false, precision=5)
	private BigDecimal conage10;

	@Column(nullable=false, precision=5)
	private BigDecimal conage11;

	@Column(nullable=false, precision=5)
	private BigDecimal conage12;

	@Column(nullable=false, precision=5)
	private BigDecimal conage13;

	@Column(nullable=false, precision=5)
	private BigDecimal conage14;

	@Column(nullable=false, precision=5)
	private BigDecimal conage2;

	@Column(nullable=false, precision=5)
	private BigDecimal conage3;

	@Column(nullable=false, precision=5)
	private BigDecimal conage4;

	@Column(nullable=false, precision=5)
	private BigDecimal conage5;

	@Column(nullable=false, precision=5)
	private BigDecimal conage6;

	@Column(nullable=false, precision=5)
	private BigDecimal conage7;

	@Column(nullable=false, precision=5)
	private BigDecimal conage8;

	@Column(nullable=false, precision=5)
	private BigDecimal conage9;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar conftxn;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar confyear;

	@Column(nullable=false, length=75)
	private String conglpath;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar conttxn;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar contyear;

	@Column(nullable=false, precision=6, scale=2)
	private BigDecimal convatper;

	@Column(nullable=false)
	private boolean crosaccflg;

	@Column(nullable=false, length=100)
	private String crystalpath;

	@Column(length=4)
	private String integprefx;

	private int integseqno;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,updatable=false)
	private int recordid;

	@Column(nullable=false, precision=5)
	private BigDecimal sconage1;

	@Column(nullable=false, precision=5)
	private BigDecimal sconage10;

	@Column(nullable=false, precision=5)
	private BigDecimal sconage11;

	@Column(nullable=false, precision=5)
	private BigDecimal sconage12;

	@Column(nullable=false, precision=5)
	private BigDecimal sconage13;

	@Column(nullable=false, precision=5)
	private BigDecimal sconage14;

	@Column(nullable=false, precision=5)
	private BigDecimal sconage2;

	@Column(nullable=false, precision=5)
	private BigDecimal sconage3;

	@Column(nullable=false, precision=5)
	private BigDecimal sconage4;

	@Column(nullable=false, precision=5)
	private BigDecimal sconage5;

	@Column(nullable=false, precision=5)
	private BigDecimal sconage6;

	@Column(nullable=false, precision=5)
	private BigDecimal sconage7;

	@Column(nullable=false, precision=5)
	private BigDecimal sconage8;

	@Column(nullable=false, precision=5)
	private BigDecimal sconage9;

	@Column(nullable=false, length=10)
	private String systype;

	@Column(nullable=false, length=256)
	private String serverurl;

	@Column(nullable=false, length=50)
	private String clickatell_userid;

	@Column(nullable=false, length=50)
	private String clickatell_pwd;

	@Column(nullable=false, length=50)
	private String clickatell_apiid;
	
	@Column(nullable=false, length=8000)
	private String resconfmsgbody;

	@Column(nullable=false, length=254)
	private String resconfirmurl;

	@Column(nullable=false, length=254)
	private String resconfmsgsubj;

	@Column(nullable=false, length=10)
	private String terbiaccid;

	@Column(nullable=false, length=25)
	private String terbiusername;

	@Column(nullable=false, length=25)
	private String terbipwd;
	
	@Column(nullable=false, length=250)
	private String terbismsurl;
	

    public Fcontrol() {
    }

	public String getAdcntacc() {
		return this.adcntacc;
	}

	public void setAdcntacc(String adcntacc) {
		this.adcntacc = adcntacc;
	}

	public String getAretearn() {
		return this.aretearn;
	}

	public void setAretearn(String aretearn) {
		this.aretearn = aretearn;
	}

	public String getAttdownloadfld() {
		return this.attdownloadfld;
	}

	public void setAttdownloadfld(String attdownloadfld) {
		this.attdownloadfld = attdownloadfld;
	}

	public String getAttfldonserver() {
		return this.attfldonserver;
	}

	public void setAttfldonserver(String attfldonserver) {
		this.attfldonserver = attfldonserver;
	}

	public BigDecimal getAutoast() {
		return this.autoast;
	}

	public void setAutoast(BigDecimal autoast) {
		this.autoast = autoast;
	}

	public BigDecimal getBalgcrlm() {
		return this.balgcrlm;
	}

	public void setBalgcrlm(BigDecimal balgcrlm) {
		this.balgcrlm = balgcrlm;
	}

	public String getBasecur() {
		return this.basecur;
	}

	public void setBasecur(String basecur) {
		this.basecur = basecur;
	}

	public String getComadd1() {
		return this.comadd1;
	}

	public void setComadd1(String comadd1) {
		this.comadd1 = comadd1;
	}

	public String getComadd2() {
		return this.comadd2;
	}

	public void setComadd2(String comadd2) {
		this.comadd2 = comadd2;
	}

	public String getComadd3() {
		return this.comadd3;
	}

	public void setComadd3(String comadd3) {
		this.comadd3 = comadd3;
	}

	public String getComemail() {
		return this.comemail;
	}

	public void setComemail(String comemail) {
		this.comemail = comemail;
	}

	public String getComfax1() {
		return this.comfax1;
	}

	public void setComfax1(String comfax1) {
		this.comfax1 = comfax1;
	}

	public String getComgstac() {
		return this.comgstac;
	}

	public void setComgstac(String comgstac) {
		this.comgstac = comgstac;
	}

	public String getComname() {
		return this.comname;
	}

	public void setComname(String comname) {
		this.comname = comname;
	}

	public String getComtel1() {
		return this.comtel1;
	}

	public void setComtel1(String comtel1) {
		this.comtel1 = comtel1;
	}

	public String getComtel2() {
		return this.comtel2;
	}

	public void setComtel2(String comtel2) {
		this.comtel2 = comtel2;
	}

	public String getComweb() {
		return this.comweb;
	}

	public void setComweb(String comweb) {
		this.comweb = comweb;
	}

	public BigDecimal getConage1() {
		return this.conage1;
	}

	public void setConage1(BigDecimal conage1) {
		this.conage1 = conage1;
	}

	public BigDecimal getConage10() {
		return this.conage10;
	}

	public void setConage10(BigDecimal conage10) {
		this.conage10 = conage10;
	}

	public BigDecimal getConage11() {
		return this.conage11;
	}

	public void setConage11(BigDecimal conage11) {
		this.conage11 = conage11;
	}

	public BigDecimal getConage12() {
		return this.conage12;
	}

	public void setConage12(BigDecimal conage12) {
		this.conage12 = conage12;
	}

	public BigDecimal getConage13() {
		return this.conage13;
	}

	public void setConage13(BigDecimal conage13) {
		this.conage13 = conage13;
	}

	public BigDecimal getConage14() {
		return this.conage14;
	}

	public void setConage14(BigDecimal conage14) {
		this.conage14 = conage14;
	}

	public BigDecimal getConage2() {
		return this.conage2;
	}

	public void setConage2(BigDecimal conage2) {
		this.conage2 = conage2;
	}

	public BigDecimal getConage3() {
		return this.conage3;
	}

	public void setConage3(BigDecimal conage3) {
		this.conage3 = conage3;
	}

	public BigDecimal getConage4() {
		return this.conage4;
	}

	public void setConage4(BigDecimal conage4) {
		this.conage4 = conage4;
	}

	public BigDecimal getConage5() {
		return this.conage5;
	}

	public void setConage5(BigDecimal conage5) {
		this.conage5 = conage5;
	}

	public BigDecimal getConage6() {
		return this.conage6;
	}

	public void setConage6(BigDecimal conage6) {
		this.conage6 = conage6;
	}

	public BigDecimal getConage7() {
		return this.conage7;
	}

	public void setConage7(BigDecimal conage7) {
		this.conage7 = conage7;
	}

	public BigDecimal getConage8() {
		return this.conage8;
	}

	public void setConage8(BigDecimal conage8) {
		this.conage8 = conage8;
	}

	public BigDecimal getConage9() {
		return this.conage9;
	}

	public void setConage9(BigDecimal conage9) {
		this.conage9 = conage9;
	}

	public Calendar getConftxn() {
		return this.conftxn;
	}

	public void setConftxn(Calendar conftxn) {
		this.conftxn = conftxn;
	}

	public Calendar getConfyear() {
		return this.confyear;
	}

	public void setConfyear(Calendar confyear) {
		this.confyear = confyear;
	}

	public String getConglpath() {
		return this.conglpath;
	}

	public void setConglpath(String conglpath) {
		this.conglpath = conglpath;
	}

	public Calendar getConttxn() {
		return this.conttxn;
	}

	public void setConttxn(Calendar conttxn) {
		this.conttxn = conttxn;
	}

	public Calendar getContyear() {
		return this.contyear;
	}

	public void setContyear(Calendar contyear) {
		this.contyear = contyear;
	}

	public BigDecimal getConvatper() {
		return this.convatper;
	}

	public void setConvatper(BigDecimal convatper) {
		this.convatper = convatper;
	}

	public boolean getCrosaccflg() {
		return this.crosaccflg;
	}

	public void setCrosaccflg(boolean crosaccflg) {
		this.crosaccflg = crosaccflg;
	}

	public String getCrystalpath() {
		return this.crystalpath;
	}

	public void setCrystalpath(String crystalpath) {
		this.crystalpath = crystalpath;
	}

	public String getIntegprefx() {
		return this.integprefx;
	}

	public void setIntegprefx(String integprefx) {
		this.integprefx = integprefx;
	}

	public int getIntegseqno() {
		return this.integseqno;
	}

	public void setIntegseqno(int integseqno) {
		this.integseqno = integseqno;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public BigDecimal getSconage1() {
		return this.sconage1;
	}

	public void setSconage1(BigDecimal sconage1) {
		this.sconage1 = sconage1;
	}

	public BigDecimal getSconage10() {
		return this.sconage10;
	}

	public void setSconage10(BigDecimal sconage10) {
		this.sconage10 = sconage10;
	}

	public BigDecimal getSconage11() {
		return this.sconage11;
	}

	public void setSconage11(BigDecimal sconage11) {
		this.sconage11 = sconage11;
	}

	public BigDecimal getSconage12() {
		return this.sconage12;
	}

	public void setSconage12(BigDecimal sconage12) {
		this.sconage12 = sconage12;
	}

	public BigDecimal getSconage13() {
		return this.sconage13;
	}

	public void setSconage13(BigDecimal sconage13) {
		this.sconage13 = sconage13;
	}

	public BigDecimal getSconage14() {
		return this.sconage14;
	}

	public void setSconage14(BigDecimal sconage14) {
		this.sconage14 = sconage14;
	}

	public BigDecimal getSconage2() {
		return this.sconage2;
	}

	public void setSconage2(BigDecimal sconage2) {
		this.sconage2 = sconage2;
	}

	public BigDecimal getSconage3() {
		return this.sconage3;
	}

	public void setSconage3(BigDecimal sconage3) {
		this.sconage3 = sconage3;
	}

	public BigDecimal getSconage4() {
		return this.sconage4;
	}

	public void setSconage4(BigDecimal sconage4) {
		this.sconage4 = sconage4;
	}

	public BigDecimal getSconage5() {
		return this.sconage5;
	}

	public void setSconage5(BigDecimal sconage5) {
		this.sconage5 = sconage5;
	}

	public BigDecimal getSconage6() {
		return this.sconage6;
	}

	public void setSconage6(BigDecimal sconage6) {
		this.sconage6 = sconage6;
	}

	public BigDecimal getSconage7() {
		return this.sconage7;
	}

	public void setSconage7(BigDecimal sconage7) {
		this.sconage7 = sconage7;
	}

	public BigDecimal getSconage8() {
		return this.sconage8;
	}

	public void setSconage8(BigDecimal sconage8) {
		this.sconage8 = sconage8;
	}

	public BigDecimal getSconage9() {
		return this.sconage9;
	}

	public void setSconage9(BigDecimal sconage9) {
		this.sconage9 = sconage9;
	}

	public String getSystype() {
		return this.systype;
	}

	public void setSystype(String systype) {
		this.systype = systype;
	}

	public String getServerurl() {
		return serverurl;
	}

	public void setServerurl(String serverurl) {
		this.serverurl = serverurl;
	}

	public String getClickatell_userid() {
		return clickatell_userid;
	}

	public void setClickatell_userid(String clickatellUserid) {
		clickatell_userid = clickatellUserid;
	}

	public String getClickatell_pwd() {
		return clickatell_pwd;
	}

	public void setClickatell_pwd(String clickatellPwd) {
		clickatell_pwd = clickatellPwd;
	}

	public String getClickatell_apiid() {
		return clickatell_apiid;
	}

	public void setClickatell_apiid(String clickatellApiid) {
		clickatell_apiid = clickatellApiid;
	}

	public void setResconfmsgbody(String resconfmsgbody) {
		this.resconfmsgbody = resconfmsgbody;
	}

	public String getResconfmsgbody() {
		return resconfmsgbody;
	}

	public void setResconfirmurl(String resconfirmurl) {
		this.resconfirmurl = resconfirmurl;
	}

	public String getResconfirmurl() {
		return resconfirmurl;
	}

	public void setResconfmsgsubj(String resconfmsgsubj) {
		this.resconfmsgsubj = resconfmsgsubj;
	}

	public String getResconfmsgsubj() {
		return resconfmsgsubj;
	}

	public void setTerbiaccid(String terbiaccid) {
		this.terbiaccid = terbiaccid;
	}

	public String getTerbiaccid() {
		return terbiaccid;
	}

	public void setTerbiusername(String terbiusername) {
		this.terbiusername = terbiusername;
	}

	public String getTerbiusername() {
		return terbiusername;
	}

	public void setTerbipwd(String terbipwd) {
		this.terbipwd = terbipwd;
	}

	public String getTerbipwd() {
		return terbipwd;
	}

	public void setTerbismsurl(String terbismsurl) {
		this.terbismsurl = terbismsurl;
	}

	public String getTerbismsurl() {
		return terbismsurl;
	}

}