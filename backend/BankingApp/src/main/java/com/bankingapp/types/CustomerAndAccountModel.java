package com.bankingapp.types;

import com.bankingapp.dto.AccountDTO;
import com.bankingapp.dto.CustomerDTO;

public class CustomerAndAccountModel {
	
	private CustomerDTO customer;
	private AccountDTO account;
	public CustomerDTO getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
	public AccountDTO getAccount() {
		return account;
	}
	public void setAccount(AccountDTO account) {
		this.account = account;
	}
	
}
