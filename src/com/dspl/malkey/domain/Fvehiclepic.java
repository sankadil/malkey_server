package com.dspl.malkey.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fvehiclepics database table.
 * 
 */
@Entity
@Table(name="fvehiclepic")
@NamedQueries({@NamedQuery(name="FvehiclepicListAll", query="SELECT f FROM Fvehiclepic f"),
	@NamedQuery(name="FvehiclepicListByRegNo", query="SELECT f FROM Fvehiclepic f WHERE f.regno=:regno ORDER BY f.defaultimage DESC,f.imageurl")})

public class Fvehiclepic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, updatable=false)
	private int recordid;

	@Column(nullable=false)
	private int defaultimage;

	@Column(nullable=false, length=250)
	private String imageurl;

	@Column(nullable=false, length=10)
	private String regno;
	
	@Transient
	private byte[] imagedata;
	
	@Transient
	private String imagename;

    public Fvehiclepic() {
    }

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public int getDefaultimage() {
		return this.defaultimage;
	}

	public void setDefaultimage(int defaultimage) {
		this.defaultimage = defaultimage;
	}

	public String getImageurl() {
		return this.imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getRegno() {
		return this.regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public void setImagedata(byte[] imagedata) {
		this.imagedata = imagedata;
	}

	public byte[] getImagedata() {
		return imagedata;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getImagename() {
		return imagename;
	}

}