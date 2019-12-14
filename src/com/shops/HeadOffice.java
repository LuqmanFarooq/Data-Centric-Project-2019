package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class HeadOffice {

	// variables
	int _id;
	String location;

	// getters and setters
	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
