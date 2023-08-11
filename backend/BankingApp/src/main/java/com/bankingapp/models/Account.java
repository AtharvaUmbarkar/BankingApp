package com.bankingapp.models;

import java.util.Date;
//import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;

@Entity
public class Account {
	@Id
	private int accountNumber;
	@Length(max = 10)
	@Pattern(regexp = "^[A-Za-z]+$", message="Account Type can only contain Characters") 
	private String accountType;
	@Pattern(regexp = "^[0-9]+$", message = "Account Balance should only contain Numbers")
	private int accountBalance;
	private Date accountCreationDate;
	private boolean netBankingOpted;
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Date getAccountCreationDate() {
		return accountCreationDate;
	}
	public void setAccountCreationDate(Date accountCreationDate) {
		this.accountCreationDate = accountCreationDate;
	}
	public boolean isNetBankingOpted() {
		return netBankingOpted;
	}
	public void setNetBankingOpted(boolean netBankingOpted) {
		this.netBankingOpted = netBankingOpted;
	}
	
	
}

