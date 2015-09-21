package com.dspl.malkey.report;

import java.io.Serializable;

import javax.persistence.Column;

public class FrentagrvehinvRPT implements Serializable {

	/**
	 * This entity is made use of in the "rentagreement.rpt" Crystal Report
	 */
	private static final long serialVersionUID = 1L;

	@Column(length=15)
	private String resno;
	@Column(length=10)
	private String regno;
	@Column(length=254)
	private String detail1;
	@Column(length=254)
	private String detail2;
	@Column(length=254)
	private String detail3;
	@Column(length=254)
	private String detail4;
	@Column(length=254)
	private String detail5;
	@Column(length=254)
	private String detail6;
	@Column(length=254)
	private String detail7;
	@Column(length=254)
	private String detail8;
	@Column(length=254)
	private String detail9;
	@Column
	private int accessory1;
	@Column
	private int accessory2;
	@Column
	private int accessory3;
	@Column
	private int accessory4;
	@Column
	private int accessory5;
	@Column
	private int accessory6;
	@Column
	private int accessory7;
	@Column
	private int accessory8;
	@Column
	private int accessory9;
	
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

	public String getDetail1() {
		return detail1;
	}

	public void setDetail1(String detail1) {
		this.detail1 = detail1;
	}

	public String getDetail2() {
		return detail2;
	}

	public void setDetail2(String detail2) {
		this.detail2 = detail2;
	}

	public String getDetail3() {
		return detail3;
	}

	public void setDetail3(String detail3) {
		this.detail3 = detail3;
	}

	public void setDetail4(String detail4) {
		this.detail4 = detail4;
	}

	public String getDetail4() {
		return detail4;
	}

	public void setAccessory1(int accessory1) {
		this.accessory1 = accessory1;
	}

	public int getAccessory1() {
		return accessory1;
	}

	public void setAccessory2(int accessory2) {
		this.accessory2 = accessory2;
	}

	public int getAccessory2() {
		return accessory2;
	}

	public void setAccessory3(int accessory3) {
		this.accessory3 = accessory3;
	}

	public int getAccessory3() {
		return accessory3;
	}

	public void setAccessory4(int accessory4) {
		this.accessory4 = accessory4;
	}

	public int getAccessory4() {
		return accessory4;
	}

	public void setDetail5(String detail5) {
		this.detail5 = detail5;
	}

	public String getDetail5() {
		return detail5;
	}

	public void setDetail6(String detail6) {
		this.detail6 = detail6;
	}

	public String getDetail6() {
		return detail6;
	}

	public void setAccessory5(int accessory5) {
		this.accessory5 = accessory5;
	}

	public int getAccessory5() {
		return accessory5;
	}

	public void setAccessory6(int accessory6) {
		this.accessory6 = accessory6;
	}

	public int getAccessory6() {
		return accessory6;
	}

	public void setDetail7(String detail7) {
		this.detail7 = detail7;
	}

	public String getDetail7() {
		return detail7;
	}

	public void setDetail8(String detail8) {
		this.detail8 = detail8;
	}

	public String getDetail8() {
		return detail8;
	}

	public void setAccessory7(int accessory7) {
		this.accessory7 = accessory7;
	}

	public int getAccessory7() {
		return accessory7;
	}

	public void setAccessory8(int accessory8) {
		this.accessory8 = accessory8;
	}

	public int getAccessory8() {
		return accessory8;
	}

	public void setDetail9(String detail9) {
		this.detail9 = detail9;
	}

	public String getDetail9() {
		return detail9;
	}

	public void setAccessory9(int accessory9) {
		this.accessory9 = accessory9;
	}

	public int getAccessory9() {
		return accessory9;
	}
}
