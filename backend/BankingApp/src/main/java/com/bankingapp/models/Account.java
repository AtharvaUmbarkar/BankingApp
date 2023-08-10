package com.bankingapp.models;

//import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Account {
	@Id
	@GeneratedValue
	private int account_id;
	private String account_holder;
	private int balance;
	private int account_holder_id;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private List<Transactions> transaction;
	
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public String getAccount_holder() {
		return account_holder;
	}
	public void setAccount_holder(String account_holder) {
		this.account_holder = account_holder;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getAccount_holder_id() {
		return account_holder_id;
	}
	public void setAccount_holder_id(int account_holder_id) {
		this.account_holder_id = account_holder_id;
	}
	
	
	
}

