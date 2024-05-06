package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	String pId;
	String pName;
	String pPrice;
	
	@ManyToMany
	List<User> user;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(int id, String pId, String pName, String pPrice, List<User> user) {
		super();
		this.id = id;
		this.pId = pId;
		this.pName = pName;
		this.pPrice = pPrice;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpPrice() {
		return pPrice;
	}

	public void setpPrice(String pPrice) {
		this.pPrice = pPrice;
	}

	public List<User> getUser() {
		if (user == null) {
            user = new ArrayList<>();
        }
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", pId=" + pId + ", pName=" + pName + ", pPrice=" + pPrice + ", user=" + user + "]";
	}

	
}
