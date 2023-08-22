package com.bankingapp.dto;

import java.util.Date;
import java.util.List;

import com.bankingapp.models.Customer;

public class AccountDTO {
	private long accountNumber;
	private String accountType;
	private double accountBalance;
	private Date accountCreationDate;
	private boolean active; 
	private boolean debitCardAvailed;
//	private Customer customer;
//	private List<Transaction> debitTransactions;
//	private List<Transaction> creditTransactions;
	
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
