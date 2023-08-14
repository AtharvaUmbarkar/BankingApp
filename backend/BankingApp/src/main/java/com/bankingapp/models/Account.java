package com.bankingapp.models;

import java.util.Date;
//import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;

@Entity
public class Account {
	@Id
	@GeneratedValue
	private int accountNumber;
	@Length(max = 10)
	@Pattern(regexp="^[A-Za-z]+$", message="Account Type can only contain Characters") 
	private String accountType;
	
	@Value("${some.key:0}")
	private int accountBalance;
	@Value("$some.key:2023-08-14T01:30:00.000-05:00")
	private Date accountCreationDate;
	private boolean netBankingOpted;
	
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;
	
	@OneToMany(mappedBy="account", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Transaction> transaction;
	
	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
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

