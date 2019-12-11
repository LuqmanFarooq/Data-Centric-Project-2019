package com.shops;

import javax.faces.bean.ManagedBean;



/**
 * A Student class ManagedBean model associated with Course class.
 *
 */
@ManagedBean

public class Product {
	private int sid;
	private int pid;
	private String Prodname;
	private double price;
	private Store store;
	
	// === Constructors ===
	
	public Product() {
		
	}
	
	public Product(int sid, int pid, String Prodname, double price, Store store) {
		super();
		this.sid = sid;
		this.pid = pid;
		this.Prodname = Prodname;
		this.price = price;
		this.store = store;
	}
	
	// === Getters and Setters ===

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return Prodname;
	}

	public void setName(String name) {
		this.Prodname = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
}// class
