package com.bankingapp.dto;

import java.util.Date;
import java.util.List;

import com.bankingapp.models.Customer;

public class AccountDTO {
	private long accountNumber;
	private String accountType = "Savings";
	private double accountBalance = 1000d;
	private Date accountCreationDate = new Date();
	private boolean active = false; 
	private boolean debitCardAvailed=true;
	
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Date getAccountCreationDate() {
		return accountCreationDate;
	}
	public void setAccountCreationDate(Date accountCreationDate) {
		this.accountCreationDate = accountCreationDate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isDebitCardAvailed() {
		return debitCardAvailed;
	}
	public void setDebitCardAvailed(boolean debitCardAvailed) {
		this.debitCardAvailed = debitCardAvailed;
	}	
}
