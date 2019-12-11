package com.shops;

import javax.faces.bean.ManagedBean;

/**
 * A Course class ManagedBean model.
 *
 */
@ManagedBean
public class Store {
	private int sID;
	private String sName;
	private String founded;
	
	// === Constructors ===
	public Store() {}
	
	public Store(int sID, String sName, String founded) {
		super();
		this.sID = sID;
		this.sName = sName;
		this.founded = founded;
	}
	// === Getters and Setters ===

	public int getsID() {
		return sID;
	}

	public void setsID(int sID) {
		this.sID = sID;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getFounded() {
		return founded;
	}

	public void setFounded(String founded) {
		this.founded = founded;
	}
	
}// class
