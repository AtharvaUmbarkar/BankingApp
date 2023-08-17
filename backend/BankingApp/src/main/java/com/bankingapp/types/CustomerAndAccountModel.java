package com.bankingapp.types;

import com.bankingapp.models.Account;
import com.bankingapp.models.Customer;

public class CustomerAndAccountModel {
	
	private Customer customer;
	private Account account;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
