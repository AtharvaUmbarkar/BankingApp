package com.bankingapp.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	@Id
	@GeneratedValue
	private int customer_id;
	private String customer_name;
	private Date customer_dob;
	private int customer_phone;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private List<Transaction> transaction;
	
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public Date getCustomer_dob() {
		return customer_dob;
	}
	public void setCustomer_dob(Date customer_dob) {
		this.customer_dob = customer_dob;
	}
	public int getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(int customer_phone) {
		this.customer_phone = customer_phone;
	}
	public int getCustomer_aadhar() {
		return customer_aadhar;
	}
	public void setCustomer_aadhar(int customer_aadhar) {
		this.customer_aadhar = customer_aadhar;
	}
	private int customer_aadhar;
	
}
