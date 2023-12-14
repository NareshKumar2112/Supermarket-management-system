package com.market.entity;

public class Store {

	private String name;
	private String phoneNumber;
	private String address;
	private String gstNumber;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGstNumber() {
		return gstNumber;
	}
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}
	
	@Override
	public String toString() {
		return "Store [name=" + name + ", phoneNumber=" + phoneNumber + ", address=" + address + ", gstNumber="
				+ gstNumber + "]";
	}
	
}
