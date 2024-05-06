package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Products {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	String pid;
	String pName;
	String pDescription;
	String pPrice;
	String imgFileName;
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Products(int id, String pid, String pName, String pDescription, String pPrice, String imgFileName) {
		super();
		this.id = id;
		this.pid = pid;
		this.pName = pName;
		this.pDescription = pDescription;
		this.pPrice = pPrice;
		this.imgFileName = imgFileName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpDescription() {
		return pDescription;
	}
	public void setpDescription(String pDescription) {
		this.pDescription = pDescription;
	}
	public String getpPrice() {
		return pPrice;
	}
	public void setpPrice(String pPrice) {
		this.pPrice = pPrice;
	}
	public String getImgFileName() {
		return imgFileName;
	}
	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
	@Override
	public String toString() {
		return "Products [id=" + id + ", pid=" + pid + ", pName=" + pName + ", pDescription=" + pDescription
				+ ", pPrice=" + pPrice + ", imgFileName=" + imgFileName + "]";
	}
	
	
}
