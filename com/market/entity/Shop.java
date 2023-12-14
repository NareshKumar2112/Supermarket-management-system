package com.market.entity;

import java.util.ArrayList;

public class Shop {

	private String name;
	private String phoneNo;
	private ArrayList<String> productName;
	private ArrayList<Integer>quantity;
	private ArrayList<Integer>cost;
	private ArrayList<Integer>cost_qnt;
	private int total;
	private String date;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public ArrayList<String> getProductName() {
		return productName;
	}
	public void setProductName(ArrayList<String> productName) {
		this.productName = productName;
	}
	public ArrayList<Integer> getQuantity() {
		return quantity;
	}
	public void setQuantity(ArrayList<Integer> quantity) {
		this.quantity = quantity;
	}
	public ArrayList<Integer> getCost() {
		return cost;
	}
	public void setCost(ArrayList<Integer> cost) {
		this.cost = cost;
	}
	public ArrayList<Integer> getCost_qnt() {
		return cost_qnt;
	}
	public void setCost_qnt(ArrayList<Integer> cost_qnt) {
		this.cost_qnt = cost_qnt;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
